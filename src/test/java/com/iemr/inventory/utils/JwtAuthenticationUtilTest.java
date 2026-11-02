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
package com.iemr.inventory.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.inventory.data.user.M_User;
import com.iemr.inventory.repo.users.UserLoginRepo;
import com.iemr.inventory.utils.exception.IEMRException;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("JwtAuthenticationUtil Test Suite")
class JwtAuthenticationUtilTest {

	@Mock
	private CookieUtil cookieUtil;
	@Mock
	private JwtUtil jwtUtil;
	@Mock
	private UserLoginRepo userLoginRepo;
	@Mock
	private RedisTemplate<String, Object> redisTemplate;
	@Mock
	private ValueOperations<String, Object> valueOperations;
	@Mock
	private HttpServletRequest request;
	@Mock
	private Claims claims;

	private JwtAuthenticationUtil jwtAuthenticationUtil;

	@BeforeEach
	@DisplayName("Wire the util with mocked collaborators")
	void setUp() {
		jwtAuthenticationUtil = new JwtAuthenticationUtil(cookieUtil, jwtUtil);
		ReflectionTestUtils.setField(jwtAuthenticationUtil, "userLoginRepo", userLoginRepo);
		ReflectionTestUtils.setField(jwtAuthenticationUtil, "redisTemplate", redisTemplate);
	}

	@Test
	@DisplayName("validateJwtToken should return 401 when the Jwttoken cookie is absent")
	void validateJwtToken_shouldReturnUnauthorizedWhenCookieMissing() {
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.empty());

		ResponseEntity<String> result = jwtAuthenticationUtil.validateJwtToken(request);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
		assertEquals("Error 401: Unauthorized - JWT Token is not set!", result.getBody());
		verify(jwtUtil, never()).validateToken(anyString());
	}

	@Test
	@DisplayName("validateJwtToken should return 401 when the token fails validation")
	void validateJwtToken_shouldReturnUnauthorizedWhenTokenInvalid() {
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.of("bad-token"));
		when(jwtUtil.validateToken("bad-token")).thenReturn(null);

		ResponseEntity<String> result = jwtAuthenticationUtil.validateJwtToken(request);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
		assertEquals("Error 401: Unauthorized - Invalid JWT Token!", result.getBody());
	}

	@Test
	@DisplayName("validateJwtToken should return 401 when the subject claim is null")
	void validateJwtToken_shouldReturnUnauthorizedWhenSubjectNull() {
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.of("token"));
		when(jwtUtil.validateToken("token")).thenReturn(claims);
		when(claims.getSubject()).thenReturn(null);

		ResponseEntity<String> result = jwtAuthenticationUtil.validateJwtToken(request);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
		assertEquals("Error 401: Unauthorized - Username is missing!", result.getBody());
	}

	@Test
	@DisplayName("validateJwtToken should return 401 when the subject claim is blank")
	void validateJwtToken_shouldReturnUnauthorizedWhenSubjectEmpty() {
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.of("token"));
		when(jwtUtil.validateToken("token")).thenReturn(claims);
		when(claims.getSubject()).thenReturn("");

		ResponseEntity<String> result = jwtAuthenticationUtil.validateJwtToken(request);

		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

	@Test
	@DisplayName("validateJwtToken should return 200 with the username for a valid token")
	void validateJwtToken_shouldReturnUsernameWhenValid() {
		when(cookieUtil.getCookieValue(request, "Jwttoken")).thenReturn(Optional.of("token"));
		when(jwtUtil.validateToken("token")).thenReturn(claims);
		when(claims.getSubject()).thenReturn("john.doe");

		ResponseEntity<String> result = jwtAuthenticationUtil.validateJwtToken(request);

		assertEquals(HttpStatus.OK, result.getStatusCode());
		assertEquals("john.doe", result.getBody());
	}

	@Test
	@DisplayName("validateUserIdAndJwtToken should pass when the user is already cached in Redis")
	void validateUserIdAndJwtToken_shouldReturnTrueWhenUserCached() throws Exception {
		when(jwtUtil.validateToken("token")).thenReturn(claims);
		when(claims.get("userId", String.class)).thenReturn("11");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("user_11")).thenReturn(new M_User());

		assertTrue(jwtAuthenticationUtil.validateUserIdAndJwtToken("token"));
		verify(userLoginRepo, never()).getUserByUserID(anyLong());
	}

	@Test
	@DisplayName("validateUserIdAndJwtToken should fall back to the DB and cache the user when Redis misses")
	void validateUserIdAndJwtToken_shouldFetchFromDbAndCache() throws Exception {
		M_User dbUser = new M_User();
		dbUser.setUserID(11);
		dbUser.setUserName("john.doe");

		when(jwtUtil.validateToken("token")).thenReturn(claims);
		when(claims.get("userId", String.class)).thenReturn("11");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("user_11")).thenReturn(null);
		when(userLoginRepo.getUserByUserID(11L)).thenReturn(dbUser);

		assertTrue(jwtAuthenticationUtil.validateUserIdAndJwtToken("token"));
		verify(valueOperations).set(eq("user_11"), any(M_User.class), eq(30L), eq(TimeUnit.MINUTES));
	}

	@Test
	@DisplayName("validateUserIdAndJwtToken should throw IEMRException when the token is invalid")
	void validateUserIdAndJwtToken_shouldThrowWhenTokenInvalid() {
		when(jwtUtil.validateToken("token")).thenReturn(null);

		IEMRException ex = assertThrows(IEMRException.class,
				() -> jwtAuthenticationUtil.validateUserIdAndJwtToken("token"));
		assertTrue(ex.getMessage().contains("Invalid JWT token."));
	}

	@Test
	@DisplayName("validateUserIdAndJwtToken should throw IEMRException when neither Redis nor the DB knows the user")
	void validateUserIdAndJwtToken_shouldThrowWhenUserUnknown() {
		when(jwtUtil.validateToken("token")).thenReturn(claims);
		when(claims.get("userId", String.class)).thenReturn("99");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("user_99")).thenReturn(null);
		when(userLoginRepo.getUserByUserID(99L)).thenReturn(null);

		IEMRException ex = assertThrows(IEMRException.class,
				() -> jwtAuthenticationUtil.validateUserIdAndJwtToken("token"));
		assertTrue(ex.getMessage().contains("Invalid User ID."));
	}

	@Test
	@DisplayName("validateUserIdAndJwtToken should wrap a non-numeric userId claim in an IEMRException")
	void validateUserIdAndJwtToken_shouldThrowWhenUserIdNotNumeric() {
		when(jwtUtil.validateToken("token")).thenReturn(claims);
		when(claims.get("userId", String.class)).thenReturn("not-a-number");
		when(redisTemplate.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get("user_not-a-number")).thenReturn(null);

		assertThrows(IEMRException.class, () -> jwtAuthenticationUtil.validateUserIdAndJwtToken("token"));
	}
}
