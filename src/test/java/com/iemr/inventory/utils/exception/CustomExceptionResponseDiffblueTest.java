package com.iemr.inventory.utils.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomExceptionResponse.class})
@ExtendWith(SpringExtension.class)
class CustomExceptionResponseTest {
    @Autowired
    private CustomExceptionResponse customExceptionResponse;

    /**
     * Method under test: {@link CustomExceptionResponse#setResponse(String)}
     */
    @Test
    void testSetResponse() {
        // Arrange and Act
        customExceptionResponse.setResponse("An error occurred");

        // Assert
        assertEquals("Success", customExceptionResponse.getErrorMessage());
        assertEquals("Success", customExceptionResponse.getStatus());
        assertEquals("{\"response\":\"An error occurred\"}", customExceptionResponse.getData());
        assertEquals(CustomExceptionResponse.SUCCESS, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setResponse(String)}
     */
    @Test
    void testSetResponse2() {
        // Arrange and Act
        customExceptionResponse.setResponse("Not all who wander are lost");

        // Assert
        assertEquals("Success", customExceptionResponse.getErrorMessage());
        assertEquals("Success", customExceptionResponse.getStatus());
        assertEquals("{\"response\":\"Not all who wander are lost\"}", customExceptionResponse.getData());
        assertEquals(CustomExceptionResponse.SUCCESS, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setResponse(String)}
     */
    @Test
    void testSetResponse3() {
        // Arrange and Act
        customExceptionResponse.setResponse("{\"response\":\"$$STRING\"}");

        // Assert
        assertEquals("Success", customExceptionResponse.getErrorMessage());
        assertEquals("Success", customExceptionResponse.getStatus());
        assertEquals("{\"response\":\"$$STRING\"}", customExceptionResponse.getData());
        assertEquals(CustomExceptionResponse.SUCCESS, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setResponse(String)}
     */
    @Test
    void testSetResponse4() {
        // Arrange and Act
        customExceptionResponse.setResponse("$$STRING");

        // Assert
        assertEquals("Success", customExceptionResponse.getErrorMessage());
        assertEquals("Success", customExceptionResponse.getStatus());
        assertEquals("{\"response\":\"$$STRING\"}", customExceptionResponse.getData());
        assertEquals(CustomExceptionResponse.SUCCESS, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setResponse(String)}
     */
    @Test
    void testSetResponse5() {
        // Arrange and Act
        customExceptionResponse.setResponse("42");

        // Assert
        assertEquals("Success", customExceptionResponse.getErrorMessage());
        assertEquals("Success", customExceptionResponse.getStatus());
        assertEquals("{\"response\":\"42\"}", customExceptionResponse.getData());
        assertEquals(CustomExceptionResponse.SUCCESS, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setResponse(String)}
     */
    @Test
    void testSetResponse6() {
        // Arrange and Act
        customExceptionResponse.setResponse("");

        // Assert
        assertEquals("Success", customExceptionResponse.getErrorMessage());
        assertEquals("Success", customExceptionResponse.getStatus());
        assertEquals("{\"response\":\"\"}", customExceptionResponse.getData());
        assertEquals(CustomExceptionResponse.SUCCESS, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setResponse(String)}
     */
    @Test
    void testSetResponse7() {
        // Arrange and Act
        customExceptionResponse.setResponse("FAILURE");

        // Assert
        assertEquals("Success", customExceptionResponse.getErrorMessage());
        assertEquals("Success", customExceptionResponse.getStatus());
        assertEquals("{\"response\":\"FAILURE\"}", customExceptionResponse.getData());
        assertEquals(CustomExceptionResponse.SUCCESS, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setError(int, String)}
     */
    @Test
    void testSetError() {
        // Arrange and Act
        customExceptionResponse.setError(-1, "An error occurred");

        // Assert
        assertEquals("An error occurred", customExceptionResponse.getErrorMessage());
        assertEquals("An error occurred", customExceptionResponse.getStatus());
        assertEquals(-1, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test:
     * {@link CustomExceptionResponse#setError(int, String, String)}
     */
    @Test
    void testSetError2() {
        // Arrange and Act
        customExceptionResponse.setError(-1, "An error occurred", "Status");

        // Assert
        assertEquals("An error occurred", customExceptionResponse.getErrorMessage());
        assertEquals("Status", customExceptionResponse.getStatus());
        assertEquals(-1, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setError(Throwable)}
     */
    @Test
    void testSetError3() {
        // Arrange and Act
        customExceptionResponse.setError(new IOException("foo", new Throwable()));

        // Assert
        assertEquals("foo", customExceptionResponse.getErrorMessage());
        assertEquals(CustomExceptionResponse.GENERIC_FAILURE, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setError(Throwable)}
     */
    @Test
    void testSetError4() {
        // Arrange and Act
        customExceptionResponse.setError(new IOException("foo", new IOException("foo")));

        // Assert
        assertEquals("foo", customExceptionResponse.getErrorMessage());
        assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#setError(Throwable)}
     */
    @Test
    void testSetError5() {
        // Arrange and Act
        customExceptionResponse.setError(new IOException("foo", new ArrayIndexOutOfBoundsException(-1)));

        // Assert
        assertEquals("foo", customExceptionResponse.getErrorMessage());
        assertEquals(CustomExceptionResponse.ENVIRONMENT_EXCEPTION, customExceptionResponse.getStatusCode());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#isSuccess()}
     */
    @Test
    void testIsSuccess() {
        // Arrange, Act and Assert
        assertFalse(customExceptionResponse.isSuccess());
    }

    /**
     * Method under test: {@link CustomExceptionResponse#getData()}
     */
    @Test
    void testGetData() {
        // Arrange, Act and Assert
        assertNull(customExceptionResponse.getData());
    }

    /**
     * Method under test:
     * {@link CustomExceptionResponse#toStringWithSerialization()}
     */
    @Test
    void testToStringWithSerialization() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"data\":null,\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}",
                customExceptionResponse.toStringWithSerialization());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CustomExceptionResponse#toString()}
     *   <li>{@link CustomExceptionResponse#getErrorMessage()}
     *   <li>{@link CustomExceptionResponse#getStatus()}
     *   <li>{@link CustomExceptionResponse#getStatusCode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        CustomExceptionResponse customExceptionResponse = new CustomExceptionResponse();

        // Act
        String actualToStringResult = customExceptionResponse.toString();
        String actualErrorMessage = customExceptionResponse.getErrorMessage();
        String actualStatus = customExceptionResponse.getStatus();

        // Assert
        assertEquals("FAILURE", actualStatus);
        assertEquals("Failed with generic error", actualErrorMessage);
        assertEquals("{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}",
                actualToStringResult);
        assertEquals(CustomExceptionResponse.GENERIC_FAILURE, customExceptionResponse.getStatusCode());
    }
}
