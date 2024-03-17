package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class T_PatientIssueDetailsDiffblueTest {
    /**
     * Method under test: {@link T_PatientIssueDetails#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"patientIssueID\":null,\"benRegID\":null,\"facilityID\":null,\"vanID\":null,\"parkingPlaceID\":null,\"visitID"
                        + "\":null,\"patientName\":null,\"age\":null,\"gender\":null,\"doctorName\":null,\"prescriptionID\":null,\"reference"
                        + "\":null,\"issueType\":null,\"itemStockExit\":null,\"providerServiceMapID\":null,\"deleted\":null,\"processed\""
                        + ":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new T_PatientIssueDetails()).toString());
    }
}
