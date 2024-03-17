package com.iemr.inventory.data.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DistrictsTest {
    /**
     * Method under test: {@link Districts#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{\"districtID\":null,\"districtName\":null}", (new Districts()).toString());
    }
}
