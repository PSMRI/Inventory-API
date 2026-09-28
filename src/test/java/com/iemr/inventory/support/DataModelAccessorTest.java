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

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


/**
 * Covers the plain data carriers of the service - the JPA entities, the transfer objects and
 * the report models - by round-tripping every accessor pair on every one of them.
 */
@DisplayName("Data model accessor Test Suite")
class DataModelAccessorTest {

	private static final String[] MODEL_PACKAGES = {
			"com.iemr.inventory.data",
			"com.iemr.inventory.model",
			"com.iemr.inventory.to"
	};

	static Stream<Class<?>> modelClasses() {
		return Stream.of(MODEL_PACKAGES)
				.map(BeanAccessorHarness::classesIn)
				.flatMap(List::stream);
	}

	@Test
	@DisplayName("the scan should actually find the model classes it is meant to cover")
	void modelScan_shouldFindClasses() {
		assertFalse(modelClasses().toList().isEmpty(), "no model classes were discovered on the test classpath");
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("modelClasses")
	@DisplayName("every model should return from its getters exactly what its setters stored")
	void model_shouldRoundTripAccessors(Class<?> type) {
		BeanAccessorHarness.verify(type);
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("modelClasses")
	@DisplayName("every model that defines value equality should honour it property by property")
	void model_shouldHonourValueSemantics(Class<?> type) {
		BeanAccessorHarness.verifyValueSemantics(type);
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("modelClasses")
	@DisplayName("every model constructor should build an instance without throwing")
	void model_shouldConstructWithoutThrowing(Class<?> type) {
		BeanAccessorHarness.verifyConstructors(type);
	}
}
