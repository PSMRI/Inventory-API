package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class StockTransferItemDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link StockTransferItem#toString()}
     *   <li>{@link StockTransferItem#getItemName()}
     *   <li>{@link StockTransferItem#getTransferFromFacilityID()}
     *   <li>{@link StockTransferItem#getTransferToFacilityID()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        StockTransferItem stockTransferItem = new StockTransferItem();

        // Act
        String actualToStringResult = stockTransferItem.toString();
        String actualItemName = stockTransferItem.getItemName();
        Integer actualTransferFromFacilityID = stockTransferItem.getTransferFromFacilityID();

        // Assert
        assertEquals(
                "{\"transferFromFacilityID\":null,\"transferFromFacility\":null,\"transferToFacilityID\":null,\"itemName"
                        + "\":null}",
                actualToStringResult);
        assertNull(actualTransferFromFacilityID);
        assertNull(stockTransferItem.getTransferToFacilityID());
        assertNull(actualItemName);
    }
}
