package com.iemr.inventory.utils.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ConfigProperties.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ConfigPropertiesTest {
    @Autowired
    private ConfigProperties configProperties;

    @MockBean
    private Environment environment;

    /**
     * Method under test: {@link ConfigProperties#getExtendExpiryTime()}
     */
    @Test
    void testGetExtendExpiryTime() {
        // Arrange, Act and Assert
        assertFalse(ConfigProperties.getExtendExpiryTime());
    }

    /**
     * Method under test: {@link ConfigProperties#getSessionExpiryTime()}
     */
    @Test
    void testGetSessionExpiryTime() {
        // Arrange, Act and Assert
        assertEquals(1800, ConfigProperties.getSessionExpiryTime());
    }

    /**
     * Method under test: {@link ConfigProperties#getPropertyByName(String)}
     */
    @Test
    void testGetPropertyByName() {
        // Arrange, Act and Assert
        assertNull(ConfigProperties.getPropertyByName("Property Name"));
        assertNull(ConfigProperties.getPropertyByName(null));
    }

    /**
     * Method under test: {@link ConfigProperties#getBoolean(String)}
     */
    @Test
    void testGetBoolean() {
        // Arrange, Act and Assert
        assertFalse(ConfigProperties.getBoolean("Property Name"));
        assertFalse(ConfigProperties.getBoolean(null));
    }

    /**
     * Method under test: {@link ConfigProperties#getInteger(String)}
     */
    @Test
    void testGetInteger() {
        // Arrange, Act and Assert
        assertEquals(0, ConfigProperties.getInteger("Property Name").intValue());
        assertEquals(0, ConfigProperties.getInteger(null).intValue());
        assertEquals(1800, ConfigProperties.getInteger("iemr.session.expiry.time").intValue());
    }

    /**
     * Method under test: {@link ConfigProperties#setEnvironment(Environment)}
     */
    @Test
    void testSetEnvironment() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ConfigProperties.environment
        //     ConfigProperties.extendExpiryTime
        //     ConfigProperties.logger
        //     ConfigProperties.properties
        //     ConfigProperties.redisport
        //     ConfigProperties.redisurl
        //     ConfigProperties.sessionExpiryTime

        // Arrange
        ConfigProperties configProperties = new ConfigProperties();

        // Act
        configProperties.setEnvironment(new StandardReactiveWebEnvironment());
    }

    /**
     * Method under test: {@link ConfigProperties#getRedisUrl()}
     */
    @Test
    void testGetRedisUrl() {
        // Arrange, Act and Assert
        assertEquals("localhost", configProperties.getRedisUrl());
    }

    /**
     * Method under test: {@link ConfigProperties#getRedisPort()}
     */
    @Test
    void testGetRedisPort() {
        // Arrange, Act and Assert
        assertEquals(0, configProperties.getRedisPort());
    }

    /**
     * Method under test: {@link ConfigProperties#getLong(String)}
     */
    @Test
    void testGetLong() {
        // Arrange, Act and Assert
        assertEquals(0L, configProperties.getLong("Property Name").longValue());
        assertEquals(0L, configProperties.getLong(null).longValue());
        assertEquals(1800L, configProperties.getLong("iemr.session.expiry.time").longValue());
    }

    /**
     * Method under test: {@link ConfigProperties#getFloat(String)}
     */
    @Test
    void testGetFloat() {
        // Arrange, Act and Assert
        assertEquals(1800.0f, configProperties.getFloat("iemr.session.expiry.time").floatValue());
    }

    /**
     * Method under test: {@link ConfigProperties#getPassword(String)}
     */
    @Test
    void testGetPassword() {
        // Arrange, Act and Assert
        assertNull(configProperties.getPassword("Key"));
        assertNull(configProperties.getPassword(null));
        assertEquals("1800", configProperties.getPassword("iemr.session.expiry.time"));
    }
}
