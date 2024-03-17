package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DailyStockDetailsTest {
    /**
     * Method under test: {@link DailyStockDetails#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"date\":null,\"facilityName\":null,\"itemName\":null,\"itemCategory\":null,\"batchNo\":null,"
                        + "\"unitCostPrice\":null,\"expiryDate\":null,\"quantityReceived\":null,\"openingStock\":null,\"dispensedQuantity"
                        + "\":null,\"adjustmentReceipt\":null,\"adjustmentIssue\":null,\"closingStock\":null,\"itemEnteredDate\":null}",
                (new DailyStockDetails()).toString());
    }
}
