package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MonthlyReportTest {
    /**
     * Method under test: {@link MonthlyReport#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"month\":null,\"year\":null,\"facilityName\":null,\"itemName\":null,\"itemCategory\":null,\"batchNo"
                        + "\":null,\"unitCostPrice\":null,\"expiryDate\":null,\"quantityReceived\":null,\"openingStock\":null,\"dispensedQuantity"
                        + "\":null,\"adjustmentReceipt\":null,\"adjustmentIssue\":null,\"closingStock\":null,\"itemEnteredDate\":null}",
                (new MonthlyReport()).toString());
    }
}
