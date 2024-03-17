package com.iemr.inventory.utils.http;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;

class HttpUtilsTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link HttpUtils#setStatus(HttpStatusCode)}
     *   <li>{@link HttpUtils#getStatus()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        HttpUtils httpUtils = new HttpUtils();

        // Act
        httpUtils.setStatus(null);

        // Assert that nothing has changed
        assertNull(httpUtils.getStatus());
    }
}
