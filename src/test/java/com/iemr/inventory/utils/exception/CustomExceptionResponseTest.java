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
package com.iemr.inventory.utils.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.text.ParseException;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.dao.InvalidDataAccessResourceUsageException;

@DisplayName("CustomExceptionResponse Test Suite")
class CustomExceptionResponseTest {

	private CustomExceptionResponse response;

	@BeforeEach
	@DisplayName("Start from a fresh response before each test")
	void setUp() {
		response = new CustomExceptionResponse();
	}

	/** setError(Throwable) branches on the cause, so every failure has to arrive wrapped. */
	private static RuntimeException wrapping(Throwable cause) {
		return new RuntimeException("wrapper message", cause);
	}

	@Nested
	@DisplayName("Default state")
	class DefaultStateTests {

		@Test
		@DisplayName("a fresh response should read as a generic failure")
		void freshResponse_shouldReadAsGenericFailure() {
			assertEquals(CustomExceptionResponse.GENERIC_FAILURE, response.getStatusCode());
			assertEquals("Failed with generic error", response.getErrorMessage());
			assertEquals("FAILURE", response.getStatus());
			assertFalse(response.isSuccess());
		}

		@Test
		@DisplayName("getData should return null while no payload has been set")
		void getData_shouldReturnNullWithoutPayload() {
			assertNull(response.getData());
		}

		@Test
		@DisplayName("toStringWithSerialization should keep the null data field visible")
		void toStringWithSerialization_shouldKeepNullData() {
			assertTrue(response.toStringWithSerialization().contains("\"data\":null"));
		}
	}

	@Nested
	@DisplayName("Successful payloads")
	class SuccessPayloadTests {

		@Test
		@DisplayName("setResponse should keep a JSON object payload as an object")
		void setResponse_shouldKeepJsonObjectPayload() {
			response.setResponse("{\"itemID\":11}");

			assertTrue(response.isSuccess());
			assertEquals(CustomExceptionResponse.SUCCESS, response.getStatusCode());
			assertEquals("Success", response.getErrorMessage());
			assertEquals("{\"itemID\":11}", response.getData());
		}

		@Test
		@DisplayName("setResponse should keep a JSON array payload as an array")
		void setResponse_shouldKeepJsonArrayPayload() {
			response.setResponse("[{\"itemID\":11}]");

			assertTrue(response.getData().startsWith("["));
		}

		@Test
		@DisplayName("setResponse should wrap a plain string payload in a response envelope")
		void setResponse_shouldWrapPlainStringPayload() {
			response.setResponse("all good");

			assertTrue(response.getData().contains("all good"));
			assertTrue(response.getData().contains("response"));
		}

		@Test
		@DisplayName("toString should serialise long values as strings")
		void toString_shouldSerialiseLongsAsStrings() {
			response.setResponse("{\"itemID\":11}");

			assertTrue(response.toString().contains("\"statusCode\":200"));
		}
	}

	@Nested
	@DisplayName("Explicit error codes")
	class ExplicitErrorTests {

		@Test
		@DisplayName("setError(code, message, status) should set all three fields")
		void setError_shouldSetCodeMessageAndStatus() {
			response.setError(CustomExceptionResponse.NOT_FOUND, "no such item", CustomExceptionResponse.NOT_FOUND_SC);

			assertEquals(404, response.getStatusCode());
			assertEquals("no such item", response.getErrorMessage());
			assertEquals("NOT_FOUND", response.getStatus());
		}

		@Test
		@DisplayName("setError(code, message) should reuse the message as the status")
		void setError_shouldReuseMessageAsStatus() {
			response.setError(CustomExceptionResponse.BAD_REQUEST, "bad request");

			assertEquals(400, response.getStatusCode());
			assertEquals("bad request", response.getStatus());
		}
	}

	@Nested
	@DisplayName("Mapping a thrown cause to a status code")
	class CauseMappingTests {

		@Test
		@DisplayName("an IEMRException cause should map to the user-id failure code")
		void setError_shouldMapIemrException() {
			response.setError(wrapping(new IEMRException("bad session")));

			assertEquals(CustomExceptionResponse.USERID_FAILURE, response.getStatusCode());
			assertEquals("User login failed", response.getStatus());
		}

		@Test
		@DisplayName("a JSONException cause should map to the object failure code")
		void setError_shouldMapJsonException() {
			response.setError(wrapping(new JSONException("bad json")));

			assertEquals(CustomExceptionResponse.OBJECT_FAILURE, response.getStatusCode());
			assertEquals("Invalid object conversion", response.getErrorMessage());
		}

		@Test
		@DisplayName("a plain SQLException cause should map to the DB exception code")
		void setError_shouldMapSqlException() {
			response.setError(wrapping(new SQLException("deadlock")));

			assertEquals(CustomExceptionResponse.DB_EXCEPTION, response.getStatusCode());
			assertEquals(CustomExceptionResponse.DB_EXCEPTION_SC, response.getStatus());
		}

		@Test
		@DisplayName("every Hibernate data-access cause should map to the DB exception code")
		void setError_shouldMapHibernateCauses() {
			SQLException sqlException = new SQLException("underlying");
			Throwable[] causes = {
					new SQLGrammarException("bad grammar", sqlException),
					new DataException("bad data", sqlException),
					new ConstraintViolationException("duplicate key", sqlException, "uk_item"),
					new GenericJDBCException("jdbc trouble", sqlException),
					new JDBCConnectionException("connection lost", sqlException),
					new LockAcquisitionException("lock timeout", sqlException),
					new InvalidDataAccessResourceUsageException("bad resource use")
			};

			for (Throwable cause : causes) {
				CustomExceptionResponse fresh = new CustomExceptionResponse();
				fresh.setError(wrapping(cause));

				assertEquals(CustomExceptionResponse.DB_EXCEPTION, fresh.getStatusCode(),
						() -> cause.getClass().getSimpleName() + " must map to the DB exception code");
			}
		}

		@Test
		@DisplayName("every environmental cause should map to the environment exception code")
		void setError_shouldMapEnvironmentalCauses() {
			Throwable[] causes = {
					new ParseException("bad date", 0),
					new NullPointerException("null field"),
					new ArrayIndexOutOfBoundsException("index 5"),
					new IOException("disk full"),
					new ConnectException("refused")
			};

			for (Throwable cause : causes) {
				CustomExceptionResponse fresh = new CustomExceptionResponse();
				fresh.setError(wrapping(cause));

				assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, fresh.getStatusCode(),
						() -> cause.getClass().getSimpleName() + " must map to the environment exception code");
				assertTrue(fresh.getStatus().startsWith("Failed with connection issues"));
			}
		}

		@Test
		@DisplayName("an unrecognised cause should fall back to the generic failure code")
		void setError_shouldFallBackToGenericFailure() {
			response.setError(wrapping(new IllegalStateException("something else")));

			assertEquals(CustomExceptionResponse.GENERIC_FAILURE, response.getStatusCode());
			assertEquals("wrapper message", response.getErrorMessage());
			assertTrue(response.getStatus().startsWith("Failed with wrapper message"));
		}
	}
}
