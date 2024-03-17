package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StoreSelfConsumptionDiffblueTest {
    /**
     * Method under test: {@link StoreSelfConsumption#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"consumptionID\":null,\"vanSerialNo\":null,\"facilityID\":null,\"syncFacilityID\":null,\"vanID\":null,"
                        + "\"parkingPlaceID\":null,\"refNo\":null,\"reason\":null,\"itemStockExit\":null,\"deleted\":null,\"processed\":null"
                        + ",\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new StoreSelfConsumption()).toString());
    }
}
