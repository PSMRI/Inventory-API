package com.iemr.inventory.data.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StockAdjustmentDraftDiffblueTest {
    /**
     * Method under test: {@link StockAdjustmentDraft#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"stockAdjustmentDraftID\":null,\"facilityID\":null,\"draftDesc\":null,\"draftName\":null,\"refNo\":null,"
                        + "\"isCompleted\":null,\"providerServiceMapID\":null,\"deleted\":null,\"vanID\":null,\"processed\":null,\"createdBy"
                        + "\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null,\"stockAdjustmentItemDraft\":null,"
                        + "\"stockAdjustmentItemDraftEdit\":null}",
                (new StockAdjustmentDraft()).toString());
    }
}
