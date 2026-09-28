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
package com.iemr.inventory.service.health;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("HealthService Test Suite")
class HealthServiceTest {

	@Mock
	private DataSource dataSource;
	@Mock
	private RedisTemplate<String, Object> redisTemplate;
	@Mock
	private Connection connection;
	@Mock
	private PreparedStatement statement;
	@Mock
	private ResultSet resultSet;

	private HealthService healthService;

	@BeforeEach
	@DisplayName("Wire a service over a healthy MySQL and Redis by default")
	void setUp() throws Exception {
		when(dataSource.getConnection()).thenReturn(connection);
		when(connection.prepareStatement(anyString())).thenReturn(statement);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true);
		when(resultSet.getInt(1)).thenReturn(0);
		when(redisTemplate.execute(any(RedisCallback.class))).thenReturn("PONG");

		healthService = new HealthService(dataSource, redisTemplate);
	}

	@SuppressWarnings("unchecked")
	private Map<String, Object> component(Map<String, Object> response, String name) {
		Map<String, Map<String, Object>> components =
				(Map<String, Map<String, Object>>) response.get("components");
		return components.get(name);
	}

	@Nested
	@DisplayName("Overall status aggregation")
	class OverallStatusTests {

		@Test
		@DisplayName("checkHealth should report UP when MySQL and Redis are both healthy")
		void checkHealth_shouldReportUpWhenAllComponentsHealthy() {
			Map<String, Object> response = healthService.checkHealth();

			assertEquals("UP", response.get("status"));
			assertNotNull(response.get("timestamp"));
			assertEquals("UP", component(response, "mysql").get("status"));
			assertEquals("UP", component(response, "redis").get("status"));
			assertEquals("OK", component(response, "mysql").get("severity"));
		}

		@Test
		@DisplayName("checkHealth should report DOWN when MySQL cannot be reached")
		void checkHealth_shouldReportDownWhenMysqlUnreachable() throws Exception {
			when(dataSource.getConnection()).thenThrow(new SQLException("connection refused"));

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("DOWN", response.get("status"));
			assertEquals("DOWN", component(response, "mysql").get("status"));
			assertEquals("CRITICAL", component(response, "mysql").get("severity"));
			assertEquals("MySQL connection failed", component(response, "mysql").get("error"));
		}

		@Test
		@DisplayName("checkHealth should report DOWN when the MySQL probe returns no row")
		void checkHealth_shouldReportDownWhenProbeReturnsNoRow() throws Exception {
			when(resultSet.next()).thenReturn(false);

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("DOWN", response.get("status"));
			assertEquals("No result from health check query", component(response, "mysql").get("error"));
		}

		@Test
		@DisplayName("checkHealth should always expose a response time for each component")
		void checkHealth_shouldExposeResponseTimePerComponent() {
			Map<String, Object> response = healthService.checkHealth();

			assertNotNull(component(response, "mysql").get("responseTimeMs"));
			assertNotNull(component(response, "redis").get("responseTimeMs"));
		}
	}

	@Nested
	@DisplayName("Redis health")
	class RedisHealthTests {

		@Test
		@DisplayName("checkHealth should treat an unconfigured Redis as healthy and say so")
		void checkHealth_shouldSkipRedisWhenNotConfigured() {
			HealthService serviceWithoutRedis = new HealthService(dataSource, null);

			Map<String, Object> response = serviceWithoutRedis.checkHealth();

			assertEquals("UP", response.get("status"));
			assertEquals("UP", component(response, "redis").get("status"));
			assertEquals("Redis not configured — skipped", component(response, "redis").get("message"));
		}

		@Test
		@DisplayName("checkHealth should report DOWN when Redis answers something other than PONG")
		void checkHealth_shouldReportDownWhenRedisPingFails() {
			when(redisTemplate.execute(any(RedisCallback.class))).thenReturn("NOPE");

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("DOWN", response.get("status"));
			assertEquals("Redis PING failed", component(response, "redis").get("error"));
		}

		@Test
		@DisplayName("checkHealth should report DOWN when the Redis call throws")
		void checkHealth_shouldReportDownWhenRedisThrows() {
			when(redisTemplate.execute(any(RedisCallback.class)))
					.thenThrow(new IllegalStateException("redis unavailable"));

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("DOWN", response.get("status"));
			assertEquals("Redis connection failed", component(response, "redis").get("error"));
		}
	}

	@Nested
	@DisplayName("Advanced MySQL diagnostics")
	class AdvancedDiagnosticsTests {

		@Test
		@DisplayName("checkHealth should flag DEGRADED when MySQL reports lock waits")
		void checkHealth_shouldFlagDegradedOnLockWaits() throws Exception {
			when(resultSet.getInt(1)).thenReturn(4);

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("DEGRADED", response.get("status"));
			assertEquals("DEGRADED", component(response, "mysql").get("status"));
			assertEquals("WARNING", component(response, "mysql").get("severity"));
		}

		@Test
		@DisplayName("checkHealth should stay UP when the diagnostic counters are all clear")
		void checkHealth_shouldStayUpWhenDiagnosticsClear() throws Exception {
			when(resultSet.getInt(1)).thenReturn(0);

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("UP", response.get("status"));
		}

		@Test
		@DisplayName("checkHealth should stay UP when the advanced diagnostics cannot get a connection")
		void checkHealth_shouldStayUpWhenAdvancedConnectionUnavailable() throws Exception {
			when(dataSource.getConnection()).thenReturn(connection).thenThrow(new SQLException("pool exhausted"));

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("UP", response.get("status"));
		}

		@Test
		@DisplayName("checkHealth should throttle the advanced diagnostics to one run per window")
		void checkHealth_shouldThrottleAdvancedDiagnostics() throws Exception {
			// The first check takes two connections: one for the basic probe, one for the diagnostics.
			healthService.checkHealth();
			verify(dataSource, times(2)).getConnection();

			// The second check reuses the cached diagnostic result, so only the basic probe connects.
			healthService.checkHealth();
			verify(dataSource, times(3)).getConnection();
		}

		@Test
		@DisplayName("checkHealth should flag DEGRADED when the HikariCP pool is nearly exhausted")
		void checkHealth_shouldFlagDegradedWhenHikariPoolNearlyExhausted() throws Exception {
			HikariDataSource hikariDataSource = mock(HikariDataSource.class);
			HikariPoolMXBean poolMXBean = mock(HikariPoolMXBean.class);
			when(hikariDataSource.getConnection()).thenReturn(connection);
			when(hikariDataSource.getHikariPoolMXBean()).thenReturn(poolMXBean);
			when(hikariDataSource.getMaximumPoolSize()).thenReturn(10);
			when(poolMXBean.getActiveConnections()).thenReturn(9);

			HealthService service = new HealthService(hikariDataSource, redisTemplate);
			Map<String, Object> response = service.checkHealth();

			assertEquals("DEGRADED", response.get("status"));
		}

		@Test
		@DisplayName("checkHealth should stay UP when the HikariCP pool still has headroom")
		void checkHealth_shouldStayUpWhenHikariPoolHasHeadroom() throws Exception {
			HikariDataSource hikariDataSource = mock(HikariDataSource.class);
			HikariPoolMXBean poolMXBean = mock(HikariPoolMXBean.class);
			when(hikariDataSource.getConnection()).thenReturn(connection);
			when(hikariDataSource.getHikariPoolMXBean()).thenReturn(poolMXBean);
			when(hikariDataSource.getMaximumPoolSize()).thenReturn(10);
			when(poolMXBean.getActiveConnections()).thenReturn(2);

			HealthService service = new HealthService(hikariDataSource, redisTemplate);

			assertEquals("UP", service.checkHealth().get("status"));
		}

		@Test
		@DisplayName("checkHealth should stay UP when the HikariCP MX bean is unavailable")
		void checkHealth_shouldStayUpWhenHikariMxBeanUnavailable() throws Exception {
			HikariDataSource hikariDataSource = mock(HikariDataSource.class);
			when(hikariDataSource.getConnection()).thenReturn(connection);
			when(hikariDataSource.getHikariPoolMXBean()).thenReturn(null);

			HealthService service = new HealthService(hikariDataSource, redisTemplate);

			assertEquals("UP", service.checkHealth().get("status"));
		}

		@Test
		@DisplayName("checkHealth should stay UP when the diagnostic queries themselves fail")
		void checkHealth_shouldStayUpWhenDiagnosticQueriesFail() throws Exception {
			when(connection.prepareStatement(anyString()))
					.thenReturn(statement)
					.thenThrow(new SQLException("information_schema unavailable"));

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("UP", response.get("status"));
		}
	}

	@Nested
	@DisplayName("Executor lifecycle")
	class ShutdownTests {

		@Test
		@DisplayName("shutdown should stop the executor so no further checks are submitted")
		void shutdown_shouldStopTheExecutor() throws Exception {
			healthService.shutdown();

			Map<String, Object> response = healthService.checkHealth();

			assertEquals("DOWN", response.get("status"));
			assertEquals("MySQL health check did not complete in time", component(response, "mysql").get("error"));
			assertEquals("Redis health check did not complete in time", component(response, "redis").get("error"));
			verify(dataSource, never()).getConnection();
		}

		@Test
		@DisplayName("shutdown should be safe to call more than once")
		void shutdown_shouldBeIdempotent() {
			healthService.shutdown();

			healthService.shutdown();

			assertTrue(true, "a repeated shutdown must not throw");
		}
	}

	@Test
	@DisplayName("checkHealth should set the MySQL query timeout so a hung DB cannot stall the probe")
	void checkHealth_shouldSetQueryTimeout() throws Exception {
		healthService.checkHealth();

		verify(statement, atLeastOnce()).setQueryTimeout(anyInt());
	}

	@Test
	@DisplayName("checkHealth should close the JDBC resources it opens")
	void checkHealth_shouldCloseJdbcResources() throws Exception {
		healthService.checkHealth();

		verify(connection, atLeastOnce()).close();
		verify(statement, atLeastOnce()).close();
	}

	@Test
	@DisplayName("checkHealth should not report a false DEGRADED after a clean run")
	void checkHealth_shouldNotReportFalseDegraded() {
		assertFalse("DEGRADED".equals(healthService.checkHealth().get("status")));
		verify(redisTemplate, times(1)).execute(any(RedisCallback.class));
	}
}
