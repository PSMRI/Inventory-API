package com.iemr.inventory.data.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockAdjustmentDiffblueTest {
    /**
     * Method under test: {@link StockAdjustment#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{\"stockAdjustmentID\":null,\"stockAdjustmentDraftID\":null,\"vanSerialNo\":null,\"facilityID\":null,"
                + "\"syncFacilityID\":null,\"refNo\":null,\"status\":null,\"providerServiceMapID\":null,\"deleted\":null,\"vanID\""
                + ":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null,"
                + "\"stockAdjustmentItem\":null,\"stockAdjustmentItemDraftEdit\":null}", (new StockAdjustment()).toString());
    }
}
