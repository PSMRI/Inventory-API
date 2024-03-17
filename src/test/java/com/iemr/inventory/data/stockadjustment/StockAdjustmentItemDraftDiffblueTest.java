package com.iemr.inventory.data.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockAdjustmentItemDraftDiffblueTest {
    /**
     * Method under test: {@link StockAdjustmentItemDraft#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"sADraftItemMapID\":null,\"itemStockEntry\":null,\"itemStockEntryID\":null,\"isAdded\":null,\"adjustedQuantity"
                        + "\":null,\"reason\":null,\"providerServiceMapID\":null,\"status\":null,\"stockAdjustmentDraftID\":null,\"deleted"
                        + "\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new StockAdjustmentItemDraft()).toString());
    }
}
