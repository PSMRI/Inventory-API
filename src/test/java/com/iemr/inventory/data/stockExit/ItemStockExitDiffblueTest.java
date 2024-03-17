package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ItemStockExitDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemStockExit#toString()}
     *   <li>{@link ItemStockExit#getFacilityID()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ItemStockExit itemStockExit = new ItemStockExit();

        // Act
        String actualToStringResult = itemStockExit.toString();

        // Assert
        assertEquals(
                "{\"itemStockExitID\":null,\"vanSerialNo\":null,\"itemStockEntryID\":null,\"itemStockEntry\":null,\"facilityID"
                        + "\":null,\"syncFacilityID\":null,\"itemID\":null,\"quantityInHand\":null,\"quantity\":null,\"exitTypeID\":null,"
                        + "\"exitType\":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null"
                        + ",\"lastModDate\":null,\"vanID\":null,\"parkingPlaceID\":null,\"duration\":null,\"durationUnit\":null}",
                actualToStringResult);
        assertNull(itemStockExit.getFacilityID());
    }
}
