package com.iemr.inventory.data.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class M_ItemFormTest {
    /**
     * Method under test: {@link M_ItemForm#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"itemFormID\":null,\"itemForm\":null,\"itemFormDesc\":null,\"providerServiceMapID\":null,\"deleted\":null,"
                        + "\"processed\":null,\"CreatedBy\":null,\"CreatedDate\":null,\"ModifiedBy\":null,\"LastModDate\":null}",
                (new M_ItemForm()).toString());
    }
}
