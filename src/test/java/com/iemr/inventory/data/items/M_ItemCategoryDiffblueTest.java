package com.iemr.inventory.data.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class M_ItemCategoryTest {
    /**
     * Method under test: {@link M_ItemCategory#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemCategoryID\":null,\"alertBeforeDays\":null,\"issueType\":null,\"itemCategoryName\":null,\"itemCategoryDesc"
                        + "\":null,\"itemCategoryCode\":null,\"status\":null,\"providerServiceMapID\":null,\"deleted\":null,\"processed\""
                        + ":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new M_ItemCategory()).toString());
    }
}
