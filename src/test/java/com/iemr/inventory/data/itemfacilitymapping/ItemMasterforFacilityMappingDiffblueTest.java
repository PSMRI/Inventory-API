package com.iemr.inventory.data.itemfacilitymapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemMasterforFacilityMappingTest {
    /**
     * Method under test: {@link ItemMasterforFacilityMapping#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemID\":null,\"itemName\":null,\"itemDesc\":null,\"itemCode\":null,\"itemCategoryID\":null,\"isMedical\":null"
                        + ",\"itemFormID\":null,\"pharmacologyCategoryID\":null,\"manufacturerID\":null,\"strength\":null,\"uomID\":null,"
                        + "\"IsScheduledDrug\":null,\"composition\":null,\"routeID\":null,\"providerServiceMapID\":null,\"status\":null,"
                        + "\"discontinued\":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\""
                        + ":null,\"lastModDate\":null}",
                (new ItemMasterforFacilityMapping()).toString());
    }
}
