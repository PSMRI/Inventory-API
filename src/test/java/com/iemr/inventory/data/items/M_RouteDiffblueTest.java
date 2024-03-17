package com.iemr.inventory.data.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class M_RouteTest {
    /**
     * Method under test: {@link M_Route#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"routeID\":null,\"routeName\":null,\"routeDesc\":null,\"providerServiceMapID\":null,\"deleted\":null,\"processed"
                        + "\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new M_Route()).toString());
    }
}
