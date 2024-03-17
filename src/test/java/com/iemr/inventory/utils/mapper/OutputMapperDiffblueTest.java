package com.iemr.inventory.utils.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OutputMapper.class})
@ExtendWith(SpringExtension.class)
class OutputMapperTest {
    @Autowired
    private OutputMapper outputMapper;

    /**
     * Method under test: {@link OutputMapper#gson()}
     */
    @Test
    void testGson() {
        // Arrange and Act
        Gson actualGsonResult = OutputMapper.gson();

        // Assert
        assertTrue(actualGsonResult.htmlSafe());
        assertTrue(actualGsonResult.serializeNulls());
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link OutputMapper}
     */
    @Test
    void testNewOutputMapper() {
        // TODO:  Cover was only able to create a partial test for this method:
        //    AI was unable to find a test

        // Arrange and Act
        new OutputMapper();
    }
}
