package com.iemr.inventory.data.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemStockEntryIndentTest {
    /**
     * Method under test: {@link ItemStockEntryIndent#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemStockEntryID\":null,\"facilityID\":null,\"itemID\":null,\"quantity\":null,\"quantityInHand\":null,"
                        + "\"totalCostPrice\":null,\"batchNo\":null,\"expiryDate\":null,\"entryTypeID\":null,\"vanID\":null,\"entryType\""
                        + ":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate"
                        + "\":null}",
                (new ItemStockEntryIndent()).toString());
    }
}
