package com.iemr.inventory.data.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IndentIssueTest {
    /**
     * Method under test: {@link IndentIssue#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"indentIssueID\":null,\"indentOrderID\":null,\"indentID\":null,\"itemID\":null,\"itemName\":null,\"issuedQty\""
                        + ":null,\"issueDate\":null,\"remarks\":null,\"providerServiceMapID\":null,\"status\":null,\"deleted\":null,\"processed"
                        + "\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null,\"vanID\":null,"
                        + "\"parkingPlaceID\":null,\"vanSerialNo\":null,\"unitCostPrice\":null,\"batchNo\":null,\"expiryDate\":null,"
                        + "\"fromFacilityID\":null,\"toFacilityID\":null,\"syncFacilityID\":null,\"isManual\":null,\"rejectedReason\":null"
                        + "}",
                (new IndentIssue()).toString());
    }
}
