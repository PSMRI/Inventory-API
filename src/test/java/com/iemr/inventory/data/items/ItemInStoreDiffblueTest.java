package com.iemr.inventory.data.items;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemInStore.class, String.class, Long.class})
@ExtendWith(SpringExtension.class)
class ItemInStoreTest {
    @Autowired
    private ItemInStore itemInStore;

    /**
     * Method under test:
     * {@link ItemInStore#ItemInStore(Integer, Integer, String, Long)}
     */
    @Test
    void testNewItemInStore() {
        //    Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ItemInStore actualItemInStore = new ItemInStore(1, 1, "Item Name", 1L);

        // Assert
        assertEquals("Item Name", actualItemInStore.getItemName());
        assertEquals(1, actualItemInStore.getFacilityID().intValue());
        assertEquals(1, actualItemInStore.getItemID().intValue());
        assertEquals(1L, actualItemInStore.getQuantity().longValue());
    }

    /**
     * Method under test:
     * {@link ItemInStore#ItemInStore(Integer, Integer, String, Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewItemInStore2() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.lang.Integer
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        new ItemInStore(1, 1, "Item Name", 1L);

    }

    /**
     * Method under test: {@link ItemInStore#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{\"itemID\":1,\"itemName\":\"Item Name\",\"quantity\":1,\"facilityID\":1}",
                (new ItemInStore(1, 1, "Item Name", 1L)).toString());
    }
}
