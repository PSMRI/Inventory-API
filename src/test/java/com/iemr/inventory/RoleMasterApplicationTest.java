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
package com.iemr.inventory;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.iemr.inventory.utils.IEMRApplBeans;

@ExtendWith(MockitoExtension.class)
@DisplayName("RoleMasterApplication Test Suite")
class RoleMasterApplicationTest {

	@Mock
	private RedisConnectionFactory connectionFactory;

	private final RoleMasterApplication application = new RoleMasterApplication();

	@Test
	@DisplayName("configProperties should supply a fresh properties holder on each call")
	void configProperties_shouldSupplyFreshHolder() {
		assertNotNull(application.configProperties());
		assertNotSame(application.configProperties(), application.configProperties());
	}

	@Test
	@DisplayName("instantiateBeans should supply the shared bean configuration")
	void instantiateBeans_shouldSupplyBeanConfiguration() {
		assertInstanceOf(IEMRApplBeans.class, application.instantiateBeans());
	}

	@Test
	@DisplayName("configure should point the servlet container at this application class")
	void configure_shouldPointAtApplicationClass() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();

		assertSame(builder, application.configure(builder));
	}

	@Test
	@DisplayName("redisTemplate should bind the connection factory and serialise keys as plain strings")
	void redisTemplate_shouldBindFactoryAndConfigureSerializers() {
		RedisTemplate<String, Object> template = application.redisTemplate(connectionFactory);

		assertSame(connectionFactory, template.getConnectionFactory());
		assertInstanceOf(StringRedisSerializer.class, template.getKeySerializer());
		assertInstanceOf(Jackson2JsonRedisSerializer.class, template.getValueSerializer());
	}

	@Test
	@DisplayName("ServletInitializer should point the WAR deployment at the same application class")
	void servletInitializer_shouldPointAtApplicationClass() {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();

		assertSame(builder, new ServletInitializer().configure(builder));
	}

	@Test
	@DisplayName("ServletInitializer onStartup should complete without touching the servlet context")
	void servletInitializer_onStartupShouldCompleteQuietly() throws Exception {
		jakarta.servlet.ServletContext servletContext =
				org.mockito.Mockito.mock(jakarta.servlet.ServletContext.class);

		new ServletInitializer().onStartup(servletContext);

		org.mockito.Mockito.verifyNoInteractions(servletContext);
	}
}
