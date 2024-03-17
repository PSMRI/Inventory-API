package com.iemr.inventory.data.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BenVisitDetailTest {
    /**
     * Method under test: {@link BenVisitDetail#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"benVisitID\":null,\"beneficiaryRegID\":null,\"providerServiceMapID\":null,\"serviceProviderName\":null,"
                        + "\"visitDateTime\":null,\"visitCode\":null,\"visitNo\":null,\"visitReasonID\":null,\"visitReason\":null,"
                        + "\"visitCategoryID\":null,\"visitCategory\":null,\"pregnancyStatus\":null,\"rCHID\":null,\"healthFacilityType\""
                        + ":null,\"healthFacilityLocation\":null,\"reportFilePath\":null,\"deleted\":null,\"processed\":null,\"createdBy"
                        + "\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null,\"visitFlowStatusFlag\":null,\"vanSerialNo"
                        + "\":null,\"vehicalNo\":null,\"parkingPlaceID\":null,\"syncedBy\":null,\"syncedDate\":null,\"reservedForChange\""
                        + ":null,\"beneficiaryID\":null}",
                (new BenVisitDetail()).toString());
    }
}
