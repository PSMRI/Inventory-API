package com.iemr.inventory.exceptionhandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class DataNotFoundTest {
    /**
     * Method under test: {@link DataNotFound#DataNotFound(String)}
     */
    @Test
    void testNewDataNotFound() {
        // Arrange and Act
        DataNotFound actualDataNotFound = new DataNotFound("Msg");

        // Assert
        assertEquals("Msg", actualDataNotFound.getLocalizedMessage());
        assertEquals("Msg", actualDataNotFound.getMessage());
        assertNull(actualDataNotFound.getCause());
        assertEquals(0, actualDataNotFound.getSuppressed().length);
    }
}
