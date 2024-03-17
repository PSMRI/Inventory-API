package com.iemr.inventory.utils.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InputMapper.class})
@ExtendWith(SpringExtension.class)
class InputMapperTest {
    @Autowired
    private InputMapper inputMapper;

    /**
     * Method under test: {@link InputMapper#gson()}
     */
    @Test
    void testGson() {
        // Arrange, Act and Assert
        assertTrue(InputMapper.gson().logger instanceof ch.qos.logback.classic.Logger);
    }

    /**
     * Method under test: {@link InputMapper#fromJson(String, Class)}
     */
    @Test
    void testFromJson() {
        // Arrange
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertEquals("Json", inputMapper.fromJson("Json", classOfT));
    }

    /**
     * Method under test: {@link InputMapper#fromJson(String, Class)}
     */
    @Test
    void testFromJson2() {
        // Arrange
        Class<Object> classOfT = Object.class;

        // Act and Assert
        assertNull(inputMapper.fromJson("", classOfT));
    }
}
