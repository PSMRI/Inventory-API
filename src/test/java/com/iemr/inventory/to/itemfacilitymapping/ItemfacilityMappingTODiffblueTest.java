package com.iemr.inventory.to.itemfacilitymapping;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class ItemfacilityMappingTOTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemfacilityMappingTO#setItemID1(Integer[])}
     *   <li>{@link ItemfacilityMappingTO#getItemID1()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ItemfacilityMappingTO itemfacilityMappingTO = new ItemfacilityMappingTO();
        Integer[] itemID1 = new Integer[]{1};

        // Act
        itemfacilityMappingTO.setItemID1(itemID1);

        // Assert that nothing has changed
        assertSame(itemID1, itemfacilityMappingTO.getItemID1());
    }
}
