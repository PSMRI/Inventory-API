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
package com.iemr.inventory.utils.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("RedisStorage Test Suite")
class RedisStorageTest {

	@Mock
	private LettuceConnectionFactory connectionFactory;
	@Mock
	private RedisConnection redisConnection;
	@Mock
	private RedisStringCommands stringCommands;

	private RedisStorage redisStorage;

	@BeforeEach
	@DisplayName("Wire the store with a mocked Lettuce connection")
	void setUp() {
		redisStorage = new RedisStorage();
		ReflectionTestUtils.setField(redisStorage, "connection", connectionFactory);
		ReflectionTestUtils.setField(redisStorage, "sessionExpiryTimeInSec", 1800);

		when(connectionFactory.getConnection()).thenReturn(redisConnection);
		when(redisConnection.stringCommands()).thenReturn(stringCommands);
	}

	private void storedValue(String key, String value) {
		when(stringCommands.get(key.getBytes()))
				.thenReturn(value == null ? null : value.getBytes(StandardCharsets.UTF_8));
	}

	@Test
	@DisplayName("getSessionObject should return the stored session payload")
	void getSessionObject_shouldReturnStoredPayload() throws Exception {
		storedValue("session-key", "{\"userName\":\"john\"}");

		assertEquals("{\"userName\":\"john\"}", redisStorage.getSessionObject("session-key"));
	}

	@Test
	@DisplayName("getSessionObject should throw when the key is absent from Redis")
	void getSessionObject_shouldThrowWhenKeyAbsent() {
		storedValue("session-key", null);

		Exception ex = assertThrows(Exception.class, () -> redisStorage.getSessionObject("session-key"));
		assertEquals("Unable to fetch session object from Redis server,"
				+ "either session key is invalid or expired.", ex.getMessage());
	}

	@Test
	@DisplayName("getSessionObject should throw when the stored payload is blank")
	void getSessionObject_shouldThrowWhenPayloadBlank() {
		storedValue("session-key", "   ");

		assertThrows(Exception.class, () -> redisStorage.getSessionObject("session-key"));
	}

	@Test
	@DisplayName("updateSessionObject should refresh the TTL and echo the key back")
	void updateSessionObject_shouldRefreshTtl() throws Exception {
		storedValue("session-key", "{\"userName\":\"john\"}");

		assertEquals("session-key", redisStorage.updateSessionObject("session-key"));
		verify(stringCommands).set(eq("session-key".getBytes()), any(byte[].class),
				eq(Expiration.seconds(1800)), eq(SetOption.UPSERT));
	}

	@Test
	@DisplayName("updateSessionObject should throw when the session is missing")
	void updateSessionObject_shouldThrowWhenSessionMissing() {
		storedValue("session-key", null);

		Exception ex = assertThrows(Exception.class, () -> redisStorage.updateSessionObject("session-key"));
		assertEquals("Unable to fetch session object from Redis server", ex.getMessage());
	}

	@Test
	@DisplayName("updateConcurrentSessionObject should refresh the lower-cased username key")
	void updateConcurrentSessionObject_shouldRefreshUsernameKey() {
		storedValue("john", "{\"userName\":\"John\"}");

		redisStorage.updateConcurrentSessionObject("{\"userName\":\" John \"}");

		verify(stringCommands).set(eq("john".getBytes()), any(byte[].class), any(Expiration.class), any(SetOption.class));
	}

	@Test
	@DisplayName("updateConcurrentSessionObject should swallow malformed JSON")
	void updateConcurrentSessionObject_shouldSwallowMalformedJson() {
		redisStorage.updateConcurrentSessionObject("not-json-at-all");

		verify(stringCommands, never()).set(any(), any(), any(), any());
	}

	@Test
	@DisplayName("updateConcurrentSessionObject should do nothing when the payload has no userName")
	void updateConcurrentSessionObject_shouldIgnorePayloadWithoutUserName() {
		redisStorage.updateConcurrentSessionObject("{\"other\":\"value\"}");

		verify(stringCommands, never()).set(any(), any(), any(), any());
	}

	@Test
	@DisplayName("updateConcurrentSessionObject should swallow a null payload")
	void updateConcurrentSessionObject_shouldSwallowNullPayload() {
		redisStorage.updateConcurrentSessionObject(null);

		verify(stringCommands, never()).set(any(), any(), any(), any());
	}
}
