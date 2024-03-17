package com.iemr.inventory.data.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemIndentTest {
    /**
     * Method under test: {@link ItemIndent#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemID\":null,\"itemName\":null,\"itemDesc\":null,\"itemCode\":null,\"itemCategoryID\":null,\"itemCategory\""
                        + ":null,\"isMedical\":null,\"itemFormID\":null,\"itemForm\":null,\"pharmacologyCategoryID\":null,\"pharmacologyCategory"
                        + "\":null,\"manufacturerID\":null,\"manufacturer\":null,\"strength\":null,\"uomID\":null,\"uom\":null,\"isScheduledDrug"
                        + "\":null,\"composition\":null,\"routeID\":null,\"route\":null,\"providerServiceMapID\":null,\"providerServiceMap"
                        + "\":null,\"status\":null,\"discontinued\":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate"
                        + "\":null,\"modifiedBy\":null,\"lastModDate\":null,\"quantity\":null}",
                (new ItemIndent()).toString());
    }
}
