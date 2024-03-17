package com.iemr.inventory.data.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class I_bendemographicsTest {
    /**
     * Method under test: {@link I_bendemographics#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{\"stateID\":null,\"districtID\":null,\"m_state\":null,\"m_district\":null}",
                (new I_bendemographics()).toString());
    }
}
