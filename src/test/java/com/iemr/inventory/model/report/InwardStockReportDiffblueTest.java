package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InwardStockReportTest {
    /**
     * Method under test: {@link InwardStockReport#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"facilityName\":null,\"itemName\":null,\"itemCategory\":null,\"batchNo\":null,\"unitCostPrice\""
                        + ":null,\"expiryDate\":null,\"inwardDate\":null,\"quantity\":null,\"entryType\":null}",
                (new InwardStockReport()).toString());
    }
}
