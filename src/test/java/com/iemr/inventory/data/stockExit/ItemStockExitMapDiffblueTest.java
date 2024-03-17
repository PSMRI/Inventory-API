package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemStockExitMapDiffblueTest {
    /**
     * Method under test: {@link ItemStockExitMap#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemName\":null,\"batchNo\":null,\"createdDate\":null,\"createdBy\":null,\"quantity\":null,\"deleted\":null,"
                        + "\"expiryDate\":null}",
                (new ItemStockExitMap()).toString());
    }
}
