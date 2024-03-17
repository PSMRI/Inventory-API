package com.iemr.inventory.utils.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;

@DisabledInAotMode
class RedisSessionExceptionTest {
    @MockBean
    private RedisSessionException redisSessionException;

    /**
     * Method under test:
     * {@link RedisSessionException#RedisSessionException(String)}
     */
    @Test
    void testNewRedisSessionException() {
        // Arrange and Act
        RedisSessionException actualRedisSessionException = new RedisSessionException("An error occurred");

        // Assert
        assertEquals("An error occurred", actualRedisSessionException.getMessage());
        assertEquals("An error occurred", actualRedisSessionException.toString());
        assertEquals("An error occurred", actualRedisSessionException.getLocalizedMessage());
        assertNull(actualRedisSessionException.getCause());
        assertEquals(0, actualRedisSessionException.getSuppressed().length);
    }

    /**
     * Method under test:
     * {@link RedisSessionException#RedisSessionException(String, Throwable)}
     */
    @Test
    void testNewRedisSessionException2() {
        // Arrange, Act and Assert
        assertEquals("An error occurred",
                (new RedisSessionException("An error occurred", new Throwable())).getLocalizedMessage());
    }
}
