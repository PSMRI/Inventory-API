package com.iemr.inventory.data.stockentry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemMasterWithQuantityMapDiffblueTest {
    /**
     * Method under test: {@link ItemMasterWithQuantityMap#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemStockEntryID\":null,\"facilityID\":null,\"itemID\":null,\"quantityInHand\":null,\"totalQuantity\":null,"
                        + "\"item\":null,\"expiryDate\":null}",
                (new ItemMasterWithQuantityMap()).toString());
    }
}
