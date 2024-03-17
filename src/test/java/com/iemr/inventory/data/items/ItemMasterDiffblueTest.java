package com.iemr.inventory.data.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemMaster.class})
@ExtendWith(SpringExtension.class)
class ItemMasterTest {
    @Autowired
    private ItemMaster itemMaster;

    /**
     * Method under test: {@link ItemMaster#ItemMaster()}
     */
    @Test
    void testNewItemMaster() {
        // Arrange and Act
        ItemMaster actualItemMaster = new ItemMaster();

        // Assert
        assertNull(actualItemMaster.getItemCategory());
        assertNull(actualItemMaster.getItemForm());
        assertNull(actualItemMaster.getRoute());
        assertNull(actualItemMaster.getManufacturer());
        assertNull(actualItemMaster.getPharmacologyCategory());
        assertNull(actualItemMaster.getUom());
        assertNull(actualItemMaster.getProviderServiceMap());
        assertNull(actualItemMaster.getDeleted());
        assertNull(actualItemMaster.getDiscontinued());
        assertNull(actualItemMaster.getIsMedical());
        assertNull(actualItemMaster.getIsScheduledDrug());
        assertNull(actualItemMaster.getProcessed());
        assertNull(actualItemMaster.getFacilityID());
        assertNull(actualItemMaster.getItemCategoryID());
        assertNull(actualItemMaster.getItemFormID());
        assertNull(actualItemMaster.getItemID());
        assertNull(actualItemMaster.getManufacturerID());
        assertNull(actualItemMaster.getPharmacologyCategoryID());
        assertNull(actualItemMaster.getProviderServiceMapID());
        assertNull(actualItemMaster.getQuantity());
        assertNull(actualItemMaster.getRouteID());
        assertNull(actualItemMaster.getUomID());
        assertNull(actualItemMaster.getComposition());
        assertNull(actualItemMaster.getCreatedBy());
        assertNull(actualItemMaster.getItemCode());
        assertNull(actualItemMaster.getItemDesc());
        assertNull(actualItemMaster.getItemName());
        assertNull(actualItemMaster.getModifiedBy());
        assertNull(actualItemMaster.getStatus());
        assertNull(actualItemMaster.getStrength());
        assertNull(actualItemMaster.getCreatedDate());
        assertNull(actualItemMaster.getLastModDate());
    }

    /**
     * Method under test: {@link ItemMaster#ItemMaster(Integer, String)}
     */
    @Test
    void testNewItemMaster2() {
        // Arrange and Act
        ItemMaster actualItemMaster = new ItemMaster(1, "Item Name");

        // Assert
        assertEquals("Item Name", actualItemMaster.getItemName());
        assertEquals(1, actualItemMaster.getItemID().intValue());
    }

    /**
     * Method under test: {@link ItemMaster#toString()}
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
                (new ItemMaster()).toString());
    }
}
