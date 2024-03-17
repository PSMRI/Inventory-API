package com.iemr.inventory.data.stockentry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemBatchListDiffblueTest {
    /**
     * Method under test: {@link ItemBatchList#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemStockEntryID\":null,\"facilityID\":null,\"itemID\":null,\"quantity\":null,\"quantityInHand\":null,\"batchNo"
                        + "\":null,\"expiryDate\":null,\"expiresIn\":null}",
                (new ItemBatchList()).toString());
    }
}
