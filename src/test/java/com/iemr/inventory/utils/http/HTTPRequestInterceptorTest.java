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
package com.iemr.inventory.utils.http;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.mock.web.DelegatingServletOutputStream;
import org.springframework.test.util.ReflectionTestUtils;

import com.iemr.inventory.utils.redis.RedisStorage;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("HTTPRequestInterceptor Test Suite")
class HTTPRequestInterceptorTest {

	@Mock
	private RedisStorage redisStorage;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;

	private HTTPRequestInterceptor interceptor;
	private ByteArrayOutputStream responseBody;

	@BeforeEach
	@DisplayName("Wire the interceptor with a mocked Redis store and a capturing response stream")
	void setUp() throws Exception {
		interceptor = new HTTPRequestInterceptor();
		ReflectionTestUtils.setField(interceptor, "redisStorage", redisStorage);
		ReflectionTestUtils.setField(interceptor, "allowedOrigins", "http://localhost:*,https://*.piramalswasthya.org");

		responseBody = new ByteArrayOutputStream();
		when(response.getOutputStream()).thenReturn(new DelegatingServletOutputStream(responseBody));
	}

	@Test
	@DisplayName("preHandle should let swagger-ui requests through without touching Redis")
	void preHandle_shouldAllowSwaggerUi() throws Exception {
		when(request.getRequestURI()).thenReturn("/inventory/swagger-ui/index.html");

		assertTrue(interceptor.preHandle(request, response, new Object()));
		verify(redisStorage, never()).getSessionObject(anyString());
	}

	@Test
	@DisplayName("preHandle should allow the request through when no Authorization header is supplied")
	void preHandle_shouldAllowWhenAuthorizationMissing() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn(null);

		assertTrue(interceptor.preHandle(request, response, new Object()));
		verify(redisStorage, never()).getSessionObject(anyString());
	}

	@Test
	@DisplayName("preHandle should allow the request through when the Authorization header is empty")
	void preHandle_shouldAllowWhenAuthorizationEmpty() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("");

		assertTrue(interceptor.preHandle(request, response, new Object()));
	}

	@Test
	@DisplayName("preHandle should skip validation for OPTIONS pre-flight requests")
	void preHandle_shouldSkipValidationForOptions() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("OPTIONS");

		assertTrue(interceptor.preHandle(request, response, new Object()));
		verify(redisStorage, never()).getSessionObject(anyString());
	}

	@Test
	@DisplayName("preHandle should strip the Bearer prefix before looking the session up in Redis")
	void preHandle_shouldStripBearerPrefix() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("Bearer session-key");
		when(request.getMethod()).thenReturn("POST");
		when(redisStorage.getSessionObject("session-key")).thenReturn("{\"userName\":\"john\"}");

		assertTrue(interceptor.preHandle(request, response, new Object()));
		verify(redisStorage).getSessionObject("session-key");
	}

	@Test
	@DisplayName("preHandle should accept a raw session key with no Bearer prefix")
	void preHandle_shouldAcceptRawSessionKey() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("POST");
		when(redisStorage.getSessionObject("session-key")).thenReturn("{\"userName\":\"john\"}");

		assertTrue(interceptor.preHandle(request, response, new Object()));
	}

	@Test
	@DisplayName("preHandle should reject the request and write an error payload when Redis has no session")
	void preHandle_shouldRejectWhenSessionMissing() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("POST");
		when(redisStorage.getSessionObject("session-key")).thenReturn(null);

		assertFalse(interceptor.preHandle(request, response, new Object()));
		assertTrue(responseBody.toString().contains("5002"));
	}

	@Test
	@DisplayName("preHandle should reject the request when Redis lookup blows up")
	void preHandle_shouldRejectWhenRedisThrows() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("POST");
		when(redisStorage.getSessionObject("session-key")).thenThrow(new RuntimeException("redis down"));

		assertFalse(interceptor.preHandle(request, response, new Object()));
	}

	@Test
	@DisplayName("preHandle should reject requests routed to /error")
	void preHandle_shouldRejectErrorEndpoint() throws Exception {
		when(request.getRequestURI()).thenReturn("/error");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("POST");

		assertFalse(interceptor.preHandle(request, response, new Object()));
		verify(redisStorage, never()).getSessionObject(anyString());
	}

	@Test
	@DisplayName("preHandle should let the documented swagger endpoints through untouched")
	void preHandle_shouldAllowSwaggerRelatedEndpoints() throws Exception {
		for (String uri : new String[] { "/swagger-ui.html", "/index.html", "/a/swagger-initializer.js",
				"/v3/swagger-config", "/ui", "/swagger-resources", "/v3/api-docs" }) {
			when(request.getRequestURI()).thenReturn(uri);
			when(request.getHeader("Authorization")).thenReturn("session-key");
			when(request.getMethod()).thenReturn("GET");

			assertTrue(interceptor.preHandle(request, response, new Object()), "expected " + uri + " to be allowed");
		}
		verify(redisStorage, never()).getSessionObject(anyString());
	}

	@Test
	@DisplayName("preHandle should echo CORS headers back for an allowed origin on the error response")
	void preHandle_shouldAddCorsHeadersForAllowedOrigin() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("POST");
		when(request.getHeader("Origin")).thenReturn("http://localhost:4200");
		when(redisStorage.getSessionObject("session-key")).thenReturn(null);

		assertFalse(interceptor.preHandle(request, response, new Object()));
		verify(response).setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		verify(response).setHeader("Access-Control-Allow-Credentials", "true");
	}

	@Test
	@DisplayName("preHandle should not add CORS headers for an origin outside the allow-list")
	void preHandle_shouldNotAddCorsHeadersForDisallowedOrigin() throws Exception {
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("POST");
		when(request.getHeader("Origin")).thenReturn("http://evil.example.com");
		when(redisStorage.getSessionObject("session-key")).thenReturn(null);

		assertFalse(interceptor.preHandle(request, response, new Object()));
		verify(response, never()).setHeader(anyString(), anyString());
	}

	@Test
	@DisplayName("preHandle should not add CORS headers when the allow-list is blank")
	void preHandle_shouldNotAddCorsHeadersWhenAllowListBlank() throws Exception {
		ReflectionTestUtils.setField(interceptor, "allowedOrigins", "   ");
		when(request.getRequestURI()).thenReturn("/getUom");
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getMethod()).thenReturn("POST");
		when(request.getHeader("Origin")).thenReturn("http://localhost:4200");
		when(redisStorage.getSessionObject("session-key")).thenReturn(null);

		assertFalse(interceptor.preHandle(request, response, new Object()));
		verify(response, never()).setHeader(anyString(), anyString());
	}

	@Test
	@DisplayName("postHandle should refresh both the concurrent and the plain session entries")
	void postHandle_shouldRefreshSession() throws Exception {
		when(request.getHeader("Authorization")).thenReturn("Bearer session-key");
		when(request.getRequestURI()).thenReturn("/getUom");
		when(redisStorage.getSessionObject("session-key")).thenReturn("{\"userName\":\"john\"}");

		interceptor.postHandle(request, response, new Object(), null);

		verify(redisStorage).updateConcurrentSessionObject("{\"userName\":\"john\"}");
		verify(redisStorage).updateSessionObject("session-key");
	}

	@Test
	@DisplayName("postHandle should do nothing when there is no Authorization header")
	void postHandle_shouldSkipWhenAuthorizationMissing() throws Exception {
		when(request.getHeader("Authorization")).thenReturn(null);
		when(request.getRequestURI()).thenReturn("/getUom");

		interceptor.postHandle(request, response, new Object(), null);

		verify(redisStorage, never()).updateSessionObject(anyString());
	}

	@Test
	@DisplayName("postHandle should swallow Redis failures rather than propagating them")
	void postHandle_shouldSwallowRedisFailures() throws Exception {
		when(request.getHeader("Authorization")).thenReturn("session-key");
		when(request.getRequestURI()).thenReturn("/getUom");
		when(redisStorage.getSessionObject("session-key")).thenThrow(new RuntimeException("redis down"));

		interceptor.postHandle(request, response, new Object(), null);

		verify(redisStorage, times(1)).getSessionObject("session-key");
	}

	@Test
	@DisplayName("afterCompletion should complete without side effects")
	void afterCompletion_shouldDoNothing() throws Exception {
		interceptor.afterCompletion(request, response, new Object(), null);
		verify(redisStorage, never()).updateSessionObject(anyString());
	}
}
