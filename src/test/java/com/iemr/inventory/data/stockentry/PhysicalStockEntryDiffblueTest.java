package com.iemr.inventory.data.stockentry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PhysicalStockEntryDiffblueTest {
    /**
     * Method under test: {@link PhysicalStockEntry#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"phyEntryID\":null,\"vanSerialNo\":null,\"refNo\":null,\"facilityID\":null,\"vanID\":null,\"parkingPlaceID\""
                        + ":null,\"itemStockEntry\":null,\"providerServiceMapID\":null,\"syncFacilityID\":null,\"status\":null,\"deleted"
                        + "\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new PhysicalStockEntry()).toString());
    }
}
