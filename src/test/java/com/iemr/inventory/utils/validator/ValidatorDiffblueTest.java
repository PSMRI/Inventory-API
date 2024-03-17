package com.iemr.inventory.utils.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.redis.RedisSessionException;
import com.iemr.inventory.utils.sessionobject.SessionObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {Validator.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ValidatorTest {
    @Autowired
    private Validator validator;

    @MockBean
    private SessionObject sessionObject;

    /**
     * Method under test: {@link Validator#getBoolean(String)}
     */
    @Test
    void testGetBoolean() {
        // Arrange, Act and Assert
        assertFalse(Validator.getBoolean("Some String"));
    }

    /**
     * Method under test: {@link Validator#updateCacheObj(JSONObject)}
     */
    @Test
    void testUpdateCacheObj() {
        // Arrange
        JSONObject responseObj = new JSONObject();

        // Act and Assert
        assertSame(responseObj, validator.updateCacheObj(responseObj));
    }

    /**
     * Method under test: {@link Validator#getSessionObject(String)}
     */
    @Test
    void testGetSessionObject() throws RedisSessionException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any())).thenReturn("Session Object");

        // Act
        String actualSessionObject = validator.getSessionObject("Key");

        // Assert
        verify(sessionObject).getSessionObject(eq("Key"));
        assertEquals("Session Object", actualSessionObject);
    }

    /**
     * Method under test: {@link Validator#getSessionObject(String)}
     */
    @Test
    void testGetSessionObject2() throws RedisSessionException {
        // Arrange
        when(sessionObject.getSessionObject(Mockito.<String>any()))
                .thenThrow(new RedisSessionException("An error occurred"));

        // Act and Assert
        assertThrows(RedisSessionException.class, () -> validator.getSessionObject("Key"));
        verify(sessionObject).getSessionObject(eq("Key"));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Validator#setSessionObject(SessionObject)}
     *   <li>{@link Validator#checkKeyExists()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws IEMRException {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     Validator.enableIPValidation
        //     Validator.logger
        //     Validator.session

        // Arrange
        Validator validator = new Validator();

        // Act
        validator.setSessionObject(new SessionObject());
        validator.checkKeyExists();
    }
}
