package com.iemr.inventory.data.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class M_ServiceProviderDiffblueTest {
    /**
     * Method under test: {@link M_ServiceProvider#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{}", (new M_ServiceProvider()).toString());
    }
}
