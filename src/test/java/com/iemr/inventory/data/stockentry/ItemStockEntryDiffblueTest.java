package com.iemr.inventory.data.stockentry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemStockEntryDiffblueTest {
    /**
     * Method under test: {@link ItemStockEntry#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemStockEntryID\":null,\"vanSerialNo\":null,\"facilityID\":null,\"syncFacilityID\":null,\"itemID\":null,"
                        + "\"quantity\":null,\"quantityInHand\":null,\"totalCostPrice\":null,\"batchNo\":null,\"expiryDate\":null,\"entryTypeID"
                        + "\":null,\"item\":null,\"entryType\":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate\""
                        + ":null,\"modifiedBy\":null,\"lastModDate\":null,\"vanID\":null,\"parkingPlaceID\":null}",
                (new ItemStockEntry()).toString());
    }
}
