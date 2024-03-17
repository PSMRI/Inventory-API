package com.iemr.inventory.data.stockentry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemStockEntryinputDiffblueTest {
    /**
     * Method under test: {@link ItemStockEntryinput#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{}", (new ItemStockEntryinput()).toString());
    }
}
