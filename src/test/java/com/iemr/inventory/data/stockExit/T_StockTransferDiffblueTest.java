package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class T_StockTransferDiffblueTest {
    /**
     * Method under test: {@link T_StockTransfer#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"stockTransferID\":null,\"vanSerialNo\":null,\"transferFromFacilityID\":null,\"syncFacilityID\":null,"
                        + "\"transferFromFacility\":null,\"transferToFacilityID\":null,\"transferToFacility\":null,\"refNo\":null,\"issueType"
                        + "\":null,\"itemStockExit\":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"vanID\":null,\"toVanID\""
                        + ":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new T_StockTransfer()).toString());
    }
}
