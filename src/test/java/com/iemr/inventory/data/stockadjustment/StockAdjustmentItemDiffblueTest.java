package com.iemr.inventory.data.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockAdjustmentItemDiffblueTest {
    /**
     * Method under test: {@link StockAdjustmentItem#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"sAItemMapID\":null,\"vanSerialNo\":null,\"itemStockEntry\":null,\"itemStockEntryID\":null,\"facilityID\":null"
                        + ",\"syncFacilityID\":null,\"isAdded\":null,\"adjustedQuantity\":null,\"reason\":null,\"providerServiceMapID\""
                        + ":null,\"status\":null,\"stockAdjustmentID\":null,\"vanID\":null,\"deleted\":null,\"processed\":null,\"createdBy"
                        + "\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new StockAdjustmentItem()).toString());
    }
}
