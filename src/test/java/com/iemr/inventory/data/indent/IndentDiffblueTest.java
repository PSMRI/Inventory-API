package com.iemr.inventory.data.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IndentTest {
    /**
     * Method under test: {@link Indent#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"indentID\":null,\"fromFacilityID\":null,\"syncFacilityID\":null,\"fromFacilityName\":null,\"toFacilityID"
                        + "\":null,\"refNo\":null,\"orderDate\":null,\"reason\":null,\"userID\":null,\"providerServiceMapID\":null,\"status"
                        + "\":null,\"statusReason\":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,"
                        + "\"modifiedBy\":null,\"lastModDate\":null,\"vanID\":null,\"parkingPlaceID\":null,\"vanSerialNo\":null,\"indentOrder"
                        + "\":null}",
                (new Indent()).toString());
    }
}
