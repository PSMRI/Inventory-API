package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class T_PatientIssueDiffblueTest {
    /**
     * Method under test: {@link T_PatientIssue#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"patientIssueID\":null,\"vanSerialNo\":null,\"benRegID\":null,\"facilityID\":null,\"syncFacilityID\":null,"
                        + "\"visitID\":null,\"patientName\":null,\"age\":null,\"gender\":null,\"doctorName\":null,\"prescriptionID\":null,"
                        + "\"reference\":null,\"issueType\":null,\"issuedBy\":null,\"itemStockExit\":null,\"itemStockExitMap\":null,"
                        + "\"providerServiceMapID\":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,"
                        + "\"modifiedBy\":null,\"lastModDate\":null,\"visitCode\":null,\"vanID\":null,\"parkingPlaceID\":null}",
                (new T_PatientIssue()).toString());
    }
}
