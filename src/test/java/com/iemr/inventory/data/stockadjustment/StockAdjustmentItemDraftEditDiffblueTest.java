package com.iemr.inventory.data.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockAdjustmentItemDraftEditDiffblueTest {
    /**
     * Method under test: {@link StockAdjustmentItemDraftEdit#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"sADraftItemMapID\":null,\"sAItemMapID\":null,\"itemStockEntryID\":null,\"itemName\":null,\"batchID\":null"
                        + ",\"quantityInHand\":null,\"isAdded\":null,\"adjustedQuantity\":null,\"reason\":null,\"providerServiceMapID\""
                        + ":null,\"status\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null,\"deleted"
                        + "\":null}",
                (new StockAdjustmentItemDraftEdit()).toString());
    }
}
