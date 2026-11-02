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
package com.iemr.inventory.config;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.iemr.inventory.utils.http.HTTPRequestInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ExtendWith(MockitoExtension.class)
@DisplayName("Web MVC configuration Test Suite")
class HttpInterceptorConfigTest {

	@Mock
	private HTTPRequestInterceptor httpInterceptor;
	@Mock
	private InterceptorRegistry registry;
	@Mock
	private InterceptorRegistration registration;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;

	@Test
	@DisplayName("addInterceptors should register the session-checking interceptor")
	void addInterceptors_shouldRegisterSessionInterceptor() {
		HttpInterceptorConfig config = new HttpInterceptorConfig();
		ReflectionTestUtils.setField(config, "httpInterceptor", httpInterceptor);
		when(registry.addInterceptor(httpInterceptor)).thenReturn(registration);

		config.addInterceptors(registry);

		verify(registry).addInterceptor(httpInterceptor);
	}

	@Test
	@DisplayName("BlockingMethodInterceptor should complete its post-handle and after-completion hooks quietly")
	void blockingMethodInterceptor_shouldCompleteHooksQuietly() {
		BlockingMethodInterceptor interceptor = new BlockingMethodInterceptor();

		assertDoesNotThrow(() -> interceptor.postHandle(request, response, new Object(), null));
		assertDoesNotThrow(() -> interceptor.afterCompletion(request, response, new Object(), null));
	}
}
