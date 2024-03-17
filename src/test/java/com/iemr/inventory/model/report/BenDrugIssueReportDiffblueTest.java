package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BenDrugIssueReportTest {
    /**
     * Method under test: {@link BenDrugIssueReport#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"date\":null,\"beneficiaryName\":null,\"gender\":null,\"age\":null,\"itemName\":null,\"itemCategory"
                        + "\":null,\"batchNo\":null,\"expiryDate\":null,\"strength\":null,\"dispensedQuantity\":null}",
                (new BenDrugIssueReport()).toString());
    }
}
