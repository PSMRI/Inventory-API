package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class YearlyReportTest {
    /**
     * Method under test: {@link YearlyReport#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"year\":null,\"facilityName\":null,\"itemName\":null,\"itemCategory\":null,\"batchNo\":null,"
                        + "\"unitCostPrice\":null,\"expiryDate\":null,\"quantityReceived\":null,\"openingStock\":null,\"dispensedQuantity"
                        + "\":null,\"adjustmentReceipt\":null,\"adjustmentIssue\":null,\"closingStock\":null}",
                (new YearlyReport()).toString());
    }
}
