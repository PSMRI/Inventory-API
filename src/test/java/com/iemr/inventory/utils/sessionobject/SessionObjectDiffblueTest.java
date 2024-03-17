package com.iemr.inventory.utils.sessionobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.inventory.utils.redis.RedisSessionException;
import com.iemr.inventory.utils.redis.RedisStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {SessionObject.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SessionObjectTest {
    @MockBean
    private RedisStorage redisStorage;

    @Autowired
    private SessionObject sessionObject;

    /**
     * Method under test: {@link SessionObject#setObjectStore(RedisStorage)}
     */
    @Test
    void testSetObjectStore() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SessionObject.extendExpirationTime
        //     SessionObject.objectStore
        //     SessionObject.sessionExpiryTime

        // Arrange
        SessionObject sessionObject = new SessionObject();

        // Act
        sessionObject.setObjectStore(new RedisStorage());
    }

    /**
     * Method under test: {@link SessionObject#getSessionObject(String)}
     */
    @Test
    void testGetSessionObject() throws RedisSessionException {
        // Arrange
        when(redisStorage.getObject(Mockito.<String>any(), Mockito.<Boolean>any(), anyInt())).thenReturn("Object");

        // Act
        String actualSessionObject = sessionObject.getSessionObject("Key");

        // Assert
        verify(redisStorage).getObject(eq("Key"), Mockito.<Boolean>any(), eq(1800));
        assertEquals("Object", actualSessionObject);
    }

    /**
     * Method under test: {@link SessionObject#getSessionObject(String)}
     */
    @Test
    void testGetSessionObject2() throws RedisSessionException {
        // Arrange
        when(redisStorage.getObject(Mockito.<String>any(), Mockito.<Boolean>any(), anyInt()))
                .thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(RedisSessionException.class, () -> sessionObject.getSessionObject("Key"));
        verify(redisStorage).getObject(eq("Key"), Mockito.<Boolean>any(), eq(1800));
    }

    /**
     * Method under test: {@link SessionObject#setSessionObject(String, String)}
     */
    @Test
    void testSetSessionObject() throws RedisSessionException {
        // Arrange
        when(redisStorage.setObject(Mockito.<String>any(), Mockito.<String>any(), anyInt())).thenReturn("Object");

        // Act
        String actualSetSessionObjectResult = sessionObject.setSessionObject("Key", "42");

        // Assert
        verify(redisStorage).setObject(eq("Key"), eq("42"), eq(1800));
        assertEquals("Object", actualSetSessionObjectResult);
    }

    /**
     * Method under test: {@link SessionObject#setSessionObject(String, String)}
     */
    @Test
    void testSetSessionObject2() throws RedisSessionException {
        // Arrange
        when(redisStorage.setObject(Mockito.<String>any(), Mockito.<String>any(), anyInt()))
                .thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(RedisSessionException.class, () -> sessionObject.setSessionObject("Key", "42"));
        verify(redisStorage).setObject(eq("Key"), eq("42"), eq(1800));
    }

    /**
     * Method under test: {@link SessionObject#updateSessionObject(String, String)}
     */
    @Test
    void testUpdateSessionObject() throws RedisSessionException {
        // Arrange
        when(redisStorage.updateObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Boolean>any(), anyInt()))
                .thenReturn("2020-03-01");

        // Act
        String actualUpdateSessionObjectResult = sessionObject.updateSessionObject("Key", "42");

        // Assert
        verify(redisStorage).updateObject(eq("Key"), eq("42"), Mockito.<Boolean>any(), eq(1800));
        assertEquals("2020-03-01", actualUpdateSessionObjectResult);
    }

    /**
     * Method under test: {@link SessionObject#updateSessionObject(String, String)}
     */
    @Test
    void testUpdateSessionObject2() throws RedisSessionException {
        // Arrange
        when(redisStorage.updateObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Boolean>any(), anyInt()))
                .thenReturn("2020-03-01");

        // Act
        String actualUpdateSessionObjectResult = sessionObject.updateSessionObject("Key", "0.9E9");

        // Assert
        verify(redisStorage).updateObject(eq("Key"), eq("0.9E9"), Mockito.<Boolean>any(), eq(1800));
        assertEquals("2020-03-01", actualUpdateSessionObjectResult);
    }

    /**
     * Method under test: {@link SessionObject#updateSessionObject(String, String)}
     */
    @Test
    void testUpdateSessionObject3() throws RedisSessionException {
        // Arrange
        when(redisStorage.updateObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Boolean>any(), anyInt()))
                .thenReturn("2020-03-01");

        // Act
        String actualUpdateSessionObjectResult = sessionObject.updateSessionObject("Key", "Value");

        // Assert
        verify(redisStorage).updateObject(eq("Key"), eq("Value"), Mockito.<Boolean>any(), eq(1800));
        assertEquals("2020-03-01", actualUpdateSessionObjectResult);
    }

    /**
     * Method under test: {@link SessionObject#updateSessionObject(String, String)}
     */
    @Test
    void testUpdateSessionObject4() throws RedisSessionException {
        // Arrange
        when(redisStorage.updateObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Boolean>any(), anyInt()))
                .thenReturn("2020-03-01");

        // Act
        String actualUpdateSessionObjectResult = sessionObject.updateSessionObject("Key", "");

        // Assert
        verify(redisStorage).updateObject(eq("Key"), eq(""), Mockito.<Boolean>any(), eq(1800));
        assertEquals("2020-03-01", actualUpdateSessionObjectResult);
    }

    /**
     * Method under test: {@link SessionObject#updateSessionObject(String, String)}
     */
    @Test
    void testUpdateSessionObject5() throws RedisSessionException {
        // Arrange
        when(redisStorage.updateObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Boolean>any(), anyInt()))
                .thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(RedisSessionException.class, () -> sessionObject.updateSessionObject("Key", "42"));
        verify(redisStorage).updateObject(eq("Key"), eq("42"), Mockito.<Boolean>any(), eq(1800));
    }

    /**
     * Method under test: {@link SessionObject#updateSessionObject(String, String)}
     */
    @Test
    void testUpdateSessionObject6() throws RedisSessionException {
        // Arrange
        when(redisStorage.updateObject(Mockito.<String>any(), Mockito.<String>any(), Mockito.<Boolean>any(), anyInt()))
                .thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(RedisSessionException.class, () -> sessionObject.updateSessionObject("Key", ""));
        verify(redisStorage).updateObject(eq("Key"), eq(""), Mockito.<Boolean>any(), eq(1800));
    }

    /**
     * Method under test: {@link SessionObject#deleteSessionObject(String)}
     */
    @Test
    void testDeleteSessionObject() throws RedisSessionException {
        // Arrange
        when(redisStorage.deleteObject(Mockito.<String>any())).thenReturn(1L);

        // Act
        sessionObject.deleteSessionObject("Key");

        // Assert that nothing has changed
        verify(redisStorage).deleteObject(eq("Key"));
    }

    /**
     * Method under test: {@link SessionObject#deleteSessionObject(String)}
     */
    @Test
    void testDeleteSessionObject2() throws RedisSessionException {
        // Arrange
        when(redisStorage.deleteObject(Mockito.<String>any())).thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(RedisSessionException.class, () -> sessionObject.deleteSessionObject("Key"));
        verify(redisStorage).deleteObject(eq("Key"));
    }
}
