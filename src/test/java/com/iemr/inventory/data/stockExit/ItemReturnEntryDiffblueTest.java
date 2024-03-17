package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemReturnEntryDiffblueTest {
    /**
     * Method under test: {@link ItemReturnEntry#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"patientReturnID\":null,\"benRegID\":null,\"visitID\":null,\"visitCode\":null,\"facilityID\":null,\"itemStockExitID"
                        + "\":null,\"providerServiceMapID\":null,\"deleted\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\""
                        + ":null,\"lastModDate\":null}",
                (new ItemReturnEntry()).toString());
    }
}
