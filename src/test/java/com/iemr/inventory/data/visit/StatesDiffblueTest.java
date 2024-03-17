package com.iemr.inventory.data.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StatesTest {
    /**
     * Method under test: {@link States#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{\"stateID\":null,\"stateName\":null}", (new States()).toString());
    }
}
