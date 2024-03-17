package com.iemr.inventory.data.itemfacilitymapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.iemr.inventory.utils.mapper.OutputMapper;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {M_itemfacilitymapping.class})
@ExtendWith(SpringExtension.class)
class M_itemfacilitymappingTest {
    @Autowired
    private M_itemfacilitymapping m_itemfacilitymapping;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_itemfacilitymapping#setCreatedBy(String)}
     *   <li>{@link M_itemfacilitymapping#setCreatedDate(Date)}
     *   <li>{@link M_itemfacilitymapping#setDeleted(Boolean)}
     *   <li>{@link M_itemfacilitymapping#setFacilityID(Integer)}
     *   <li>{@link M_itemfacilitymapping#setItemID(Integer)}
     *   <li>
     * {@link M_itemfacilitymapping#setItemMasterforFacilityMapping(ItemMasterforFacilityMapping)}
     *   <li>{@link M_itemfacilitymapping#setItemStoreMapID(Integer)}
     *   <li>{@link M_itemfacilitymapping#setLastModDate(Date)}
     *   <li>{@link M_itemfacilitymapping#setMappingType(String)}
     *   <li>{@link M_itemfacilitymapping#setModifiedBy(String)}
     *   <li>{@link M_itemfacilitymapping#setProviderServiceMapID(Integer)}
     *   <li>{@link M_itemfacilitymapping#setStatus(String)}
     *   <li>{@link M_itemfacilitymapping#toString()}
     *   <li>{@link M_itemfacilitymapping#getCreatedBy()}
     *   <li>{@link M_itemfacilitymapping#getCreatedDate()}
     *   <li>{@link M_itemfacilitymapping#getDeleted()}
     *   <li>{@link M_itemfacilitymapping#getFacilityID()}
     *   <li>{@link M_itemfacilitymapping#getItemID()}
     *   <li>{@link M_itemfacilitymapping#getItemMasterforFacilityMapping()}
     *   <li>{@link M_itemfacilitymapping#getItemStoreMapID()}
     *   <li>{@link M_itemfacilitymapping#getLastModDate()}
     *   <li>{@link M_itemfacilitymapping#getMappingType()}
     *   <li>{@link M_itemfacilitymapping#getModifiedBy()}
     *   <li>{@link M_itemfacilitymapping#getProviderServiceMapID()}
     *   <li>{@link M_itemfacilitymapping#getStatus()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_itemfacilitymapping m_itemfacilitymapping = new M_itemfacilitymapping();

        // Act
        m_itemfacilitymapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_itemfacilitymapping.setCreatedDate(createdDate);
        m_itemfacilitymapping.setDeleted(true);
        m_itemfacilitymapping.setFacilityID(1);
        m_itemfacilitymapping.setItemID(1);
        ItemMasterforFacilityMapping itemMasterforFacilityMapping = new ItemMasterforFacilityMapping();
        itemMasterforFacilityMapping.setComposition("Composition");
        itemMasterforFacilityMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemMasterforFacilityMapping.setCreatedDate(mock(Date.class));
        itemMasterforFacilityMapping.setDeleted(true);
        itemMasterforFacilityMapping.setDiscontinued(true);
        itemMasterforFacilityMapping.setIsMedical(true);
        itemMasterforFacilityMapping.setIsScheduledDrug(true);
        itemMasterforFacilityMapping.setItemCategoryID(1);
        itemMasterforFacilityMapping.setItemCode("Item Code");
        itemMasterforFacilityMapping.setItemDesc("Item Desc");
        itemMasterforFacilityMapping.setItemFormID(1);
        itemMasterforFacilityMapping.setItemID(1);
        itemMasterforFacilityMapping.setItemName("Item Name");
        itemMasterforFacilityMapping.setLastModDate(mock(Date.class));
        itemMasterforFacilityMapping.setManufacturerID(1);
        itemMasterforFacilityMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemMasterforFacilityMapping.setOutputMapper(new OutputMapper());
        itemMasterforFacilityMapping.setPharmacologyCategoryID(1);
        itemMasterforFacilityMapping.setProcessed('A');
        itemMasterforFacilityMapping.setProviderServiceMapID(1);
        itemMasterforFacilityMapping.setRouteID(1);
        itemMasterforFacilityMapping.setStatus("Status");
        itemMasterforFacilityMapping.setStrength("Strength");
        itemMasterforFacilityMapping.setUomID(1);
        m_itemfacilitymapping.setItemMasterforFacilityMapping(itemMasterforFacilityMapping);
        m_itemfacilitymapping.setItemStoreMapID(1);
        Date lastModDate = mock(Date.class);
        m_itemfacilitymapping.setLastModDate(lastModDate);
        m_itemfacilitymapping.setMappingType("Mapping Type");
        m_itemfacilitymapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_itemfacilitymapping.setProviderServiceMapID(1);
        m_itemfacilitymapping.setStatus("Status");
        m_itemfacilitymapping.toString();
        String actualCreatedBy = m_itemfacilitymapping.getCreatedBy();
        Date actualCreatedDate = m_itemfacilitymapping.getCreatedDate();
        Boolean actualDeleted = m_itemfacilitymapping.getDeleted();
        Integer actualFacilityID = m_itemfacilitymapping.getFacilityID();
        Integer actualItemID = m_itemfacilitymapping.getItemID();
        ItemMasterforFacilityMapping actualItemMasterforFacilityMapping = m_itemfacilitymapping
                .getItemMasterforFacilityMapping();
        Integer actualItemStoreMapID = m_itemfacilitymapping.getItemStoreMapID();
        Date actualLastModDate = m_itemfacilitymapping.getLastModDate();
        String actualMappingType = m_itemfacilitymapping.getMappingType();
        String actualModifiedBy = m_itemfacilitymapping.getModifiedBy();
        Integer actualProviderServiceMapID = m_itemfacilitymapping.getProviderServiceMapID();

        // Assert that nothing has changed
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Mapping Type", actualMappingType);
        assertEquals("Status", m_itemfacilitymapping.getStatus());
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualItemID.intValue());
        assertEquals(1, actualItemStoreMapID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertTrue(actualDeleted);
        assertSame(itemMasterforFacilityMapping, actualItemMasterforFacilityMapping);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: {@link M_itemfacilitymapping#M_itemfacilitymapping()}
     */
    @Test
    void testNewM_itemfacilitymapping() {
        // Arrange and Act
        M_itemfacilitymapping actualM_itemfacilitymapping = new M_itemfacilitymapping();

        // Assert
        assertNull(actualM_itemfacilitymapping.getItemMasterforFacilityMapping());
        assertNull(actualM_itemfacilitymapping.getFacility());
        assertNull(actualM_itemfacilitymapping.getDeleted());
        assertNull(actualM_itemfacilitymapping.getDiscontinued());
        assertNull(actualM_itemfacilitymapping.getFacilityID());
        assertNull(actualM_itemfacilitymapping.getItemCategoryID());
        assertNull(actualM_itemfacilitymapping.getItemFacilityMapID());
        assertNull(actualM_itemfacilitymapping.getItemID());
        assertNull(actualM_itemfacilitymapping.getItemStoreMapID());
        assertNull(actualM_itemfacilitymapping.getProviderServiceMapID());
        assertNull(actualM_itemfacilitymapping.getCreatedBy());
        assertNull(actualM_itemfacilitymapping.getItemName());
        assertNull(actualM_itemfacilitymapping.getMappingType());
        assertNull(actualM_itemfacilitymapping.getModifiedBy());
        assertNull(actualM_itemfacilitymapping.getStatus());
        assertNull(actualM_itemfacilitymapping.getCreatedDate());
        assertNull(actualM_itemfacilitymapping.getLastModDate());
    }

    /**
     * Method under test:
     * {@link M_itemfacilitymapping#M_itemfacilitymapping(Integer, String)}
     */
    @Test
    void testNewM_itemfacilitymapping2() {
        // Arrange and Act
        M_itemfacilitymapping actualM_itemfacilitymapping = new M_itemfacilitymapping(1, "Item Name");

        // Assert
        assertEquals("Item Name", actualM_itemfacilitymapping.getItemName());
        assertEquals(1, actualM_itemfacilitymapping.getItemID().intValue());
    }

    /**
     * Method under test:
     * {@link M_itemfacilitymapping#M_itemfacilitymapping(Integer, String, Boolean, Integer)}
     */
    @Test
    void testNewM_itemfacilitymapping3() {
        // Arrange and Act
        M_itemfacilitymapping actualM_itemfacilitymapping = new M_itemfacilitymapping(1, "Item Name", true, 1);

        // Assert
        assertEquals("Item Name", actualM_itemfacilitymapping.getItemName());
        assertEquals(1, actualM_itemfacilitymapping.getItemCategoryID().intValue());
        assertEquals(1, actualM_itemfacilitymapping.getItemID().intValue());
        assertTrue(actualM_itemfacilitymapping.getDiscontinued());
    }
}
