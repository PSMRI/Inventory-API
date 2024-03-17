package com.iemr.inventory.data.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemfacilitymappingIndentTest {
    /**
     * Method under test: {@link ItemfacilitymappingIndent#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemFacilityMapID\":null,\"facilityID\":null,\"itemID\":null,\"qoh\":null,\"itemName\":null,\"itemCode\":null"
                        + ",\"itemCategoryID\":null,\"itemCategory\":null,\"facility\":null,\"mappingType\":null,\"status\":null,"
                        + "\"providerServiceMapID\":null,\"deleted\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,"
                        + "\"lastModDate\":null,\"itemIndent\":null,\"itemStockEntryIndent\":null,\"discontinued\":null,\"itemForm\":null"
                        + ",\"pharmacologicalCategoryName\":null,\"strength\":null,\"uomName\":null,\"composition\":null,\"isMedical\":null"
                        + "}",
                (new ItemfacilitymappingIndent()).toString());
    }
}
