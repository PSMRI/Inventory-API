/*
* AMRIT - Accessible Medical Records via Integrated Technologies
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.inventory.support;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

import com.iemr.inventory.utils.mapper.OutputMapper;

/**
 * Shared harness that exercises the read/write accessors, the constructors and the
 * {@code Object} overrides of the plain data carriers in this service.
 *
 * <p>These classes are pure state: hand-written getters and setters over JPA/Gson annotated
 * fields. Asserting each pair by hand would be thousands of near-identical lines, so the
 * harness reflects over every property instead and checks the same contract for all of them:
 * what a setter stores is what the matching getter hands back.</p>
 */
public final class BeanAccessorHarness {

	static {
		// M_Uom-style toString() implementations delegate to OutputMapper.gson(), which only
		// initialises its shared GsonBuilder from the constructor. Build one up front so the
		// toString() checks below exercise real serialisation rather than tripping over a null builder.
		new OutputMapper();
	}

	private static final Map<String, Object> NESTED_SAMPLES = new java.util.concurrent.ConcurrentHashMap<>();

	private BeanAccessorHarness() {
	}

	/** Discovers every concrete class under the given package, sorted for a stable test order. */
	public static List<Class<?>> classesIn(String basePackage) {
		ClassPathScanningCandidateComponentProvider scanner =
				new ClassPathScanningCandidateComponentProvider(false);
		scanner.addIncludeFilter(new RegexPatternTypeFilter(java.util.regex.Pattern.compile(".*")));

		List<Class<?>> classes = new ArrayList<>();
		for (BeanDefinition definition : scanner.findCandidateComponents(basePackage)) {
			try {
				Class<?> type = Class.forName(definition.getBeanClassName());
				if (type.isInterface() || type.isEnum() || type.isAnonymousClass()
						|| Modifier.isAbstract(type.getModifiers())
						|| type.getName().endsWith("Test") || type.getName().endsWith("Harness")) {
					continue;
				}
				classes.add(type);
			} catch (ClassNotFoundException e) {
				throw new IllegalStateException("scanned class could not be loaded", e);
			}
		}
		classes.sort(Comparator.comparing(Class::getName));
		return classes;
	}

	/**
	 * Instantiates the class, round-trips every getter/setter pair, and calls the
	 * {@code Object} overrides. Fails if a setter and its getter disagree.
	 */
	public static void verify(Class<?> type) {
		Object instance = instantiate(type);
		assertNotNull(instance, () -> "could not instantiate " + type.getName());

		Map<String, Method> getters = new HashMap<>();
		for (Method method : type.getMethods()) {
			if (method.getDeclaringClass() == Object.class || method.getParameterCount() != 0
					|| method.getReturnType() == void.class || Modifier.isStatic(method.getModifiers())) {
				continue;
			}
			if (method.getName().startsWith("get")) {
				getters.put(method.getName().substring(3), method);
			} else if (method.getName().startsWith("is") && isBoolean(method.getReturnType())) {
				getters.put(method.getName().substring(2), method);
			}
		}

		for (Method setter : type.getMethods()) {
			if (!setter.getName().startsWith("set") || setter.getParameterCount() != 1
					|| Modifier.isStatic(setter.getModifiers()) || setter.getDeclaringClass() == Object.class) {
				continue;
			}

			Class<?> propertyType = setter.getParameterTypes()[0];
			Object value = sampleFor(propertyType, setter.getGenericParameterTypes()[0]);

			try {
				setter.invoke(instance, value);
			} catch (Exception e) {
				throw new AssertionError("setter " + type.getSimpleName() + "." + setter.getName() + " threw", e);
			}

			Method getter = getters.get(setter.getName().substring(3));
			if (getter == null || !isCompatible(getter.getReturnType(), propertyType)) {
				continue;
			}

			Object read;
			try {
				read = getter.invoke(instance);
			} catch (Exception e) {
				throw new AssertionError("getter " + type.getSimpleName() + "." + getter.getName() + " threw", e);
			}
			assertEquals(value, read,
					() -> type.getSimpleName() + "." + getter.getName() + " must return what the setter stored");
		}

		// Read every remaining getter once, so derived and read-only properties are exercised too.
		for (Method getter : getters.values()) {
			assertDoesNotThrow(() -> getter.invoke(instance),
					() -> type.getSimpleName() + "." + getter.getName() + " must not throw");
		}

		assertDoesNotThrow(instance::hashCode, () -> type.getSimpleName() + ".hashCode() must not throw");
		assertDoesNotThrow(() -> instance.equals(instance), () -> type.getSimpleName() + ".equals() must not throw");
		assertDoesNotThrow(() -> instance.equals(null), () -> type.getSimpleName() + ".equals(null) must not throw");
		assertDoesNotThrow(() -> instance.equals("a string"),
				() -> type.getSimpleName() + ".equals() must reject a foreign type without throwing");
		assertDoesNotThrow(instance::toString, () -> type.getSimpleName() + ".toString() must not throw");
	}

	/**
	 * Exercises a value-semantics {@code equals}/{@code hashCode} pair: two identically populated
	 * instances must match, and changing any single property must break the match. Classes that do
	 * not override {@code equals} keep identity semantics and are skipped.
	 */
	public static void verifyValueSemantics(Class<?> type) {
		if (!overridesEquals(type)) {
			return;
		}

		Object left = instantiate(type);
		Object right = instantiate(type);
		if (left == null || right == null) {
			return;
		}

		List<Method> setters = writableProperties(type);
		populate(left, setters, 0);
		populate(right, setters, 0);

		assertEquals(left, right, () -> type.getSimpleName() + ": identically populated instances must be equal");
		assertEquals(left.hashCode(), right.hashCode(),
				() -> type.getSimpleName() + ": equal instances must share a hash code");
		assertNotEquals(left, new Object(), () -> type.getSimpleName() + " must not equal a foreign type");
		assertNotEquals(left, null, () -> type.getSimpleName() + " must not equal null");

		for (Method setter : setters) {
			Class<?> propertyType = setter.getParameterTypes()[0];
			Object original = sampleFor(propertyType, setter.getGenericParameterTypes()[0], 0);
			Object different = sampleFor(propertyType, setter.getGenericParameterTypes()[0], 1);
			if (different == null || different.equals(original)) {
				continue;
			}

			invoke(setter, right, different);
			assertNotEquals(left, right,
					() -> type.getSimpleName() + ": instances differing on " + setter.getName() + " must not be equal");
			invoke(setter, right, original);
		}

		assertEquals(left, right, () -> type.getSimpleName() + ": restoring every property must restore equality");

		verifyUnsetValueSemantics(type, setters);
	}

	/**
	 * The mirror of the populated comparison: two untouched instances must be equal, and setting a
	 * single property on one of them must break that. This walks the "one side still holds the
	 * default" half of every field comparison, which the populated pass never reaches.
	 */
	private static void verifyUnsetValueSemantics(Class<?> type, List<Method> setters) {
		Object left = instantiate(type);
		Object right = instantiate(type);
		if (left == null || right == null) {
			return;
		}

		// A few carriers hold a helper object (an OutputMapper, say) built fresh in the field
		// initialiser. Two of those are never equal, so give both sides the same shared instance
		// before comparing; what is under test here is the declared properties, not the helpers.
		for (Method setter : setters) {
			Object leftDefault = readProperty(left, setter);
			Object rightDefault = readProperty(right, setter);
			if (leftDefault != null && !leftDefault.equals(rightDefault)) {
				Object shared = sampleFor(setter.getParameterTypes()[0], setter.getGenericParameterTypes()[0], 0);
				invoke(setter, left, shared);
				invoke(setter, right, shared);
			}
		}

		assertEquals(left, right, () -> type.getSimpleName() + ": two untouched instances must be equal");
		assertEquals(left.hashCode(), right.hashCode(),
				() -> type.getSimpleName() + ": two untouched instances must share a hash code");

		for (Method setter : setters) {
			Object value = sampleFor(setter.getParameterTypes()[0], setter.getGenericParameterTypes()[0], 0);
			Object unset = readProperty(left, setter);
			if (value == null || value.equals(unset)) {
				continue;
			}

			invoke(setter, right, value);
			assertNotEquals(left, right, () -> type.getSimpleName() + ": setting only " + setter.getName()
					+ " on one instance must break equality");
			assertNotEquals(right, left, () -> type.getSimpleName() + ": setting only " + setter.getName()
					+ " on one instance must break equality in both directions");
			invoke(setter, right, unset);
		}

		assertEquals(left, right, () -> type.getSimpleName() + ": clearing every property must restore equality");
	}

	/** Reads back the property a setter writes, or null when there is no matching readable getter. */
	private static Object readProperty(Object instance, Method setter) {
		String property = setter.getName().substring(3);
		for (String prefix : new String[] { "get", "is" }) {
			try {
				Method getter = instance.getClass().getMethod(prefix + property);
				if (getter.getParameterCount() == 0
						&& isCompatible(getter.getReturnType(), setter.getParameterTypes()[0])) {
					return getter.invoke(instance);
				}
			} catch (NoSuchMethodException e) {
				// try the other prefix
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	private static boolean overridesEquals(Class<?> type) {
		try {
			return type.getMethod("equals", Object.class).getDeclaringClass() != Object.class;
		} catch (NoSuchMethodException e) {
			return false;
		}
	}

	private static List<Method> writableProperties(Class<?> type) {
		List<Method> setters = new ArrayList<>();
		for (Method method : type.getMethods()) {
			if (method.getName().startsWith("set") && method.getParameterCount() == 1
					&& !Modifier.isStatic(method.getModifiers()) && method.getDeclaringClass() != Object.class) {
				setters.add(method);
			}
		}
		setters.sort(Comparator.comparing(Method::getName));
		return setters;
	}

	private static void populate(Object instance, List<Method> setters, int variant) {
		for (Method setter : setters) {
			invoke(setter, instance,
					sampleFor(setter.getParameterTypes()[0], setter.getGenericParameterTypes()[0], variant));
		}
	}

	private static void invoke(Method setter, Object target, Object value) {
		try {
			setter.invoke(target, value);
		} catch (Exception e) {
			throw new AssertionError("setter " + setter.getName() + " threw", e);
		}
	}

	/** Invokes every public constructor with sample arguments, to cover the all-args variants. */
	public static void verifyConstructors(Class<?> type) {
		for (Constructor<?> constructor : type.getConstructors()) {
			Object[] args = Arrays.stream(constructor.getParameters())
					.map(BeanAccessorHarness::sampleForParameter)
					.toArray();
			assertDoesNotThrow(() -> constructor.newInstance(args),
					() -> type.getSimpleName() + " constructor with " + args.length + " args must not throw");
		}
	}

	private static Object sampleForParameter(Parameter parameter) {
		return sampleFor(parameter.getType(), parameter.getParameterizedType());
	}

	private static Object instantiate(Class<?> type) {
		Constructor<?>[] constructors = Arrays.stream(type.getConstructors())
				.sorted(Comparator.comparingInt(Constructor::getParameterCount))
				.toArray(Constructor[]::new);

		for (Constructor<?> constructor : constructors) {
			try {
				Object[] args = Arrays.stream(constructor.getParameters())
						.map(BeanAccessorHarness::sampleForParameter)
						.toArray();
				return constructor.newInstance(args);
			} catch (Exception ignored) {
				// try the next constructor
			}
		}
		return null;
	}

	private static boolean isBoolean(Class<?> type) {
		return type == boolean.class || type == Boolean.class;
	}

	private static boolean isCompatible(Class<?> getterType, Class<?> setterType) {
		if (getterType == setterType) {
			return true;
		}
		return box(getterType) == box(setterType);
	}

	private static Class<?> box(Class<?> type) {
		if (type == int.class) return Integer.class;
		if (type == long.class) return Long.class;
		if (type == double.class) return Double.class;
		if (type == float.class) return Float.class;
		if (type == short.class) return Short.class;
		if (type == byte.class) return Byte.class;
		if (type == char.class) return Character.class;
		if (type == boolean.class) return Boolean.class;
		return type;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object sampleFor(Class<?> type, Type genericType) {
		return sampleFor(type, genericType, 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Object sampleFor(Class<?> type, Type genericType, int variant) {
		if (type == String.class) return variant == 0 ? "sample" : "other-sample";
		if (type == int.class || type == Integer.class) return variant == 0 ? 7 : 13;
		if (type == long.class || type == Long.class) return variant == 0 ? 7L : 13L;
		if (type == double.class || type == Double.class) return variant == 0 ? 7.5d : 13.5d;
		if (type == float.class || type == Float.class) return variant == 0 ? 7.5f : 13.5f;
		if (type == short.class || type == Short.class) return (short) (variant == 0 ? 7 : 13);
		if (type == byte.class || type == Byte.class) return (byte) (variant == 0 ? 7 : 13);
		if (type == char.class || type == Character.class) return variant == 0 ? 'N' : 'Y';
		if (type == boolean.class || type == Boolean.class) return variant == 0 ? Boolean.TRUE : Boolean.FALSE;
		if (type == BigDecimal.class) return BigDecimal.valueOf(variant == 0 ? 7.5d : 13.5d);
		if (type == BigInteger.class) return BigInteger.valueOf(variant == 0 ? 7L : 13L);
		if (type == java.sql.Date.class) return java.sql.Date.valueOf(variant == 0 ? "2025-01-31" : "2024-02-29");
		if (type == java.sql.Time.class) return java.sql.Time.valueOf(variant == 0 ? "10:15:30" : "22:45:00");
		if (type == Timestamp.class) {
			return Timestamp.valueOf(variant == 0 ? "2025-01-31 10:15:30" : "2024-02-29 22:45:00");
		}
		if (type == java.util.Date.class) return new java.util.Date(variant == 0 ? 1738300000000L : 1709200000000L);
		if (type == java.time.LocalDate.class) {
			return variant == 0 ? java.time.LocalDate.of(2025, 1, 31) : java.time.LocalDate.of(2024, 2, 29);
		}
		if (type == java.time.LocalDateTime.class) {
			return variant == 0 ? java.time.LocalDateTime.of(2025, 1, 31, 10, 15)
					: java.time.LocalDateTime.of(2024, 2, 29, 22, 45);
		}
		if (type == Object.class) return variant == 0 ? "sample" : "other-sample";
		if (type.isEnum()) {
			Object[] constants = type.getEnumConstants();
			return constants.length > 0 ? constants[0] : null;
		}
		if (type.isArray()) {
			Object array = Array.newInstance(type.getComponentType(), 1);
			Array.set(array, 0, sampleFor(type.getComponentType(), type.getComponentType(), variant));
			return array;
		}
		if (List.class.isAssignableFrom(type) || Iterable.class == type || Collection(type)) {
			List list = new ArrayList();
			Object element = elementSample(genericType, variant);
			if (element != null) {
				list.add(element);
			}
			return list;
		}
		if (Set.class.isAssignableFrom(type)) {
			Set set = new LinkedHashSet();
			Object element = elementSample(genericType, variant);
			if (element != null) {
				set.add(element);
			}
			return set;
		}
		if (Map.class.isAssignableFrom(type)) {
			return new HashMap<>();
		}
		// Nested carriers are shared per (type, variant): many of them keep identity equality, so two
		// separately built instances would never compare equal and would break the value-semantics check.
		return NESTED_SAMPLES.computeIfAbsent(type.getName() + "#" + variant, key -> instantiate(type));
	}

	private static boolean Collection(Class<?> type) {
		return java.util.Collection.class == type;
	}

	private static Object elementSample(Type genericType, int variant) {
		if (genericType instanceof ParameterizedType parameterized) {
			Type[] arguments = parameterized.getActualTypeArguments();
			if (arguments.length == 1 && arguments[0] instanceof Class<?> elementType) {
				return sampleFor(elementType, elementType, variant);
			}
		}
		return null;
	}

	/** Formats a class list for a JUnit {@code @MethodSource}. */
	public static List<Class<?>> concat(List<Class<?>>... groups) {
		return Arrays.stream(groups).flatMap(List::stream)
				.collect(Collectors.toCollection(() -> new ArrayList<>(new HashSet<>())));
	}
}
