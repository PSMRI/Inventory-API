package com.iemr.inventory.utils.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OutputResponse.class})
@ExtendWith(SpringExtension.class)
class OutputResponseTest {
    @Autowired
    private OutputResponse outputResponse;

    /**
     * Method under test: {@link OutputResponse#setResponse(String)}
     */
    @Test
    void testSetResponse() {
        // Arrange and Act
        outputResponse.setResponse("Not all who wander are lost");

        // Assert
        assertEquals("Success", outputResponse.getErrorMessage());
        assertEquals("Success", outputResponse.getStatus());
        assertEquals("{\"response\":\"Not all who wander are lost\"}", outputResponse.getData());
        assertEquals(OutputResponse.SUCCESS, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setResponse(String)}
     */
    @Test
    void testSetResponse2() {
        // Arrange and Act
        outputResponse.setResponse("{\"response\":\"$$STRING\"}");

        // Assert
        assertEquals("Success", outputResponse.getErrorMessage());
        assertEquals("Success", outputResponse.getStatus());
        assertEquals("{\"response\":\"$$STRING\"}", outputResponse.getData());
        assertEquals(OutputResponse.SUCCESS, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setResponse(String)}
     */
    @Test
    void testSetResponse3() {
        // Arrange and Act
        outputResponse.setResponse("$$STRING");

        // Assert
        assertEquals("Success", outputResponse.getErrorMessage());
        assertEquals("Success", outputResponse.getStatus());
        assertEquals("{\"response\":\"$$STRING\"}", outputResponse.getData());
        assertEquals(OutputResponse.SUCCESS, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setResponse(String)}
     */
    @Test
    void testSetResponse4() {
        // Arrange and Act
        outputResponse.setResponse("42");

        // Assert
        assertEquals("Success", outputResponse.getErrorMessage());
        assertEquals("Success", outputResponse.getStatus());
        assertEquals("{\"response\":\"42\"}", outputResponse.getData());
        assertEquals(OutputResponse.SUCCESS, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setResponse(String)}
     */
    @Test
    void testSetResponse5() {
        // Arrange and Act
        outputResponse.setResponse("");

        // Assert
        assertEquals("Success", outputResponse.getErrorMessage());
        assertEquals("Success", outputResponse.getStatus());
        assertEquals("{\"response\":\"\"}", outputResponse.getData());
        assertEquals(OutputResponse.SUCCESS, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setResponse(String)}
     */
    @Test
    void testSetResponse6() {
        // Arrange and Act
        outputResponse.setResponse("FAILURE");

        // Assert
        assertEquals("Success", outputResponse.getErrorMessage());
        assertEquals("Success", outputResponse.getStatus());
        assertEquals("{\"response\":\"FAILURE\"}", outputResponse.getData());
        assertEquals(OutputResponse.SUCCESS, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setResponse(String)}
     */
    @Test
    void testSetResponse7() {
        // Arrange and Act
        outputResponse.setResponse("NullPointerException");

        // Assert
        assertEquals("Success", outputResponse.getErrorMessage());
        assertEquals("Success", outputResponse.getStatus());
        assertEquals("{\"response\":\"NullPointerException\"}", outputResponse.getData());
        assertEquals(OutputResponse.SUCCESS, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setError(int, String)}
     */
    @Test
    void testSetError() {
        // Arrange and Act
        outputResponse.setError(-1, "Not all who wander are lost");

        // Assert
        assertEquals("Not all who wander are lost", outputResponse.getErrorMessage());
        assertEquals("Not all who wander are lost", outputResponse.getStatus());
        assertEquals(-1, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setError(int, String, String)}
     */
    @Test
    void testSetError2() {
        // Arrange and Act
        outputResponse.setError(-1, "Not all who wander are lost", "Status");

        // Assert
        assertEquals("Not all who wander are lost", outputResponse.getErrorMessage());
        assertEquals("Status", outputResponse.getStatus());
        assertEquals(-1, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setError(Throwable)}
     */
    @Test
    void testSetError3() {
        // Arrange and Act
        outputResponse.setError(new Throwable());

        // Assert
        assertNull(outputResponse.getErrorMessage());
        assertEquals(OutputResponse.GENERIC_FAILURE, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setError(Throwable)}
     */
    @Test
    void testSetError4() {
        // Arrange and Act
        outputResponse.setError(new IOException("foo"));

        // Assert
        assertEquals("foo", outputResponse.getErrorMessage());
        assertEquals(OutputResponse.ENVIRONMENT_EXCEPTION, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#setError(Throwable)}
     */
    @Test
    void testSetError5() {
        // Arrange and Act
        outputResponse.setError(new ArrayIndexOutOfBoundsException(-1));

        // Assert
        assertEquals("Array index out of range: -1", outputResponse.getErrorMessage());
        assertEquals(OutputResponse.CODE_EXCEPTION, outputResponse.getStatusCode());
    }

    /**
     * Method under test: {@link OutputResponse#isSuccess()}
     */
    @Test
    void testIsSuccess() {
        // Arrange, Act and Assert
        assertFalse(outputResponse.isSuccess());
    }

    /**
     * Method under test: {@link OutputResponse#getData()}
     */
    @Test
    void testGetData() {
        // Arrange, Act and Assert
        assertNull(outputResponse.getData());
    }

    /**
     * Method under test: {@link OutputResponse#toStringWithSerialization()}
     */
    @Test
    void testToStringWithSerialization() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"data\":null,\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}",
                outputResponse.toStringWithSerialization());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link OutputResponse#toString()}
     *   <li>{@link OutputResponse#getErrorMessage()}
     *   <li>{@link OutputResponse#getStatus()}
     *   <li>{@link OutputResponse#getStatusCode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        OutputResponse outputResponse = new OutputResponse();

        // Act
        String actualToStringResult = outputResponse.toString();
        String actualErrorMessage = outputResponse.getErrorMessage();
        String actualStatus = outputResponse.getStatus();

        // Assert
        assertEquals("FAILURE", actualStatus);
        assertEquals("Failed with generic error", actualErrorMessage);
        assertEquals("{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}",
                actualToStringResult);
        assertEquals(OutputResponse.GENERIC_FAILURE, outputResponse.getStatusCode());
    }
}
