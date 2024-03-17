package com.iemr.inventory.data.itemfacilitymapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {V_fetchItemFacilityMap.class})
@ExtendWith(SpringExtension.class)
class V_fetchItemFacilityMapTest {
    @Autowired
    private V_fetchItemFacilityMap v_fetchItemFacilityMap;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link V_fetchItemFacilityMap#setFacilityCode(String)}
     *   <li>{@link V_fetchItemFacilityMap#setFacilityDesc(String)}
     *   <li>{@link V_fetchItemFacilityMap#setFacilityID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setFacilityName(String)}
     *   <li>{@link V_fetchItemFacilityMap#setFacilityTypeCode(String)}
     *   <li>{@link V_fetchItemFacilityMap#setFacilityTypeDesc(String)}
     *   <li>{@link V_fetchItemFacilityMap#setFacilityTypeID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setFacilityTypeName(String)}
     *   <li>{@link V_fetchItemFacilityMap#setIsMainFacility(Boolean)}
     *   <li>{@link V_fetchItemFacilityMap#setIsMedical(Boolean)}
     *   <li>{@link V_fetchItemFacilityMap#setIsScheduledDrug(Boolean)}
     *   <li>{@link V_fetchItemFacilityMap#setItemCategoryCode(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemCategoryDesc(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemCategoryID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setItemCategoryName(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemCode(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemDesc(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemFacilityMapID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setItemFacilityMappingDeleted(Boolean)}
     *   <li>{@link V_fetchItemFacilityMap#setItemFormCode(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemFormDesc(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemFormID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setItemFormName(String)}
     *   <li>{@link V_fetchItemFacilityMap#setItemName(String)}
     *   <li>{@link V_fetchItemFacilityMap#setLocation(String)}
     *   <li>{@link V_fetchItemFacilityMap#setManufacturerID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setPharmCategoryCode(String)}
     *   <li>{@link V_fetchItemFacilityMap#setPharmCategoryDesc(String)}
     *   <li>{@link V_fetchItemFacilityMap#setPharmCategoryName(String)}
     *   <li>{@link V_fetchItemFacilityMap#setPharmacologyCategoryID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setPhysicalLocation(String)}
     *   <li>{@link V_fetchItemFacilityMap#setProviderServiceMapID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setRouteID(Integer)}
     *   <li>{@link V_fetchItemFacilityMap#setStatus(String)}
     *   <li>{@link V_fetchItemFacilityMap#setStoreType(String)}
     *   <li>{@link V_fetchItemFacilityMap#setStrength(String)}
     *   <li>{@link V_fetchItemFacilityMap#toString()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityCode()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityDesc()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityID()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityName()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityTypeCode()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityTypeDesc()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityTypeID()}
     *   <li>{@link V_fetchItemFacilityMap#getFacilityTypeName()}
     *   <li>{@link V_fetchItemFacilityMap#getIsMainFacility()}
     *   <li>{@link V_fetchItemFacilityMap#getIsMedical()}
     *   <li>{@link V_fetchItemFacilityMap#getIsScheduledDrug()}
     *   <li>{@link V_fetchItemFacilityMap#getItemCategoryCode()}
     *   <li>{@link V_fetchItemFacilityMap#getItemCategoryDesc()}
     *   <li>{@link V_fetchItemFacilityMap#getItemCategoryID()}
     *   <li>{@link V_fetchItemFacilityMap#getItemCategoryName()}
     *   <li>{@link V_fetchItemFacilityMap#getItemCode()}
     *   <li>{@link V_fetchItemFacilityMap#getItemDesc()}
     *   <li>{@link V_fetchItemFacilityMap#getItemFacilityMapID()}
     *   <li>{@link V_fetchItemFacilityMap#getItemFacilityMappingDeleted()}
     *   <li>{@link V_fetchItemFacilityMap#getItemFormCode()}
     *   <li>{@link V_fetchItemFacilityMap#getItemFormDesc()}
     *   <li>{@link V_fetchItemFacilityMap#getItemFormID()}
     *   <li>{@link V_fetchItemFacilityMap#getItemFormName()}
     *   <li>{@link V_fetchItemFacilityMap#getItemName()}
     *   <li>{@link V_fetchItemFacilityMap#getLocation()}
     *   <li>{@link V_fetchItemFacilityMap#getManufacturerID()}
     *   <li>{@link V_fetchItemFacilityMap#getPharmCategoryCode()}
     *   <li>{@link V_fetchItemFacilityMap#getPharmCategoryDesc()}
     *   <li>{@link V_fetchItemFacilityMap#getPharmCategoryName()}
     *   <li>{@link V_fetchItemFacilityMap#getPharmacologyCategoryID()}
     *   <li>{@link V_fetchItemFacilityMap#getPhysicalLocation()}
     *   <li>{@link V_fetchItemFacilityMap#getProviderServiceMapID()}
     *   <li>{@link V_fetchItemFacilityMap#getRouteID()}
     *   <li>{@link V_fetchItemFacilityMap#getStatus()}
     *   <li>{@link V_fetchItemFacilityMap#getStoreType()}
     *   <li>{@link V_fetchItemFacilityMap#getStrength()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        V_fetchItemFacilityMap v_fetchItemFacilityMap = new V_fetchItemFacilityMap();

        // Act
        v_fetchItemFacilityMap.setFacilityCode("Facility Code");
        v_fetchItemFacilityMap.setFacilityDesc("Facility Desc");
        v_fetchItemFacilityMap.setFacilityID(1);
        v_fetchItemFacilityMap.setFacilityName("Facility Name");
        v_fetchItemFacilityMap.setFacilityTypeCode("Facility Type Code");
        v_fetchItemFacilityMap.setFacilityTypeDesc("Facility Type Desc");
        v_fetchItemFacilityMap.setFacilityTypeID(1);
        v_fetchItemFacilityMap.setFacilityTypeName("Facility Type Name");
        v_fetchItemFacilityMap.setIsMainFacility(true);
        v_fetchItemFacilityMap.setIsMedical(true);
        v_fetchItemFacilityMap.setIsScheduledDrug(true);
        v_fetchItemFacilityMap.setItemCategoryCode("Item Category Code");
        v_fetchItemFacilityMap.setItemCategoryDesc("Item Category Desc");
        v_fetchItemFacilityMap.setItemCategoryID(1);
        v_fetchItemFacilityMap.setItemCategoryName("Item Category Name");
        v_fetchItemFacilityMap.setItemCode("Item Code");
        v_fetchItemFacilityMap.setItemDesc("Item Desc");
        v_fetchItemFacilityMap.setItemFacilityMapID(1);
        v_fetchItemFacilityMap.setItemFacilityMappingDeleted(true);
        v_fetchItemFacilityMap.setItemFormCode("Item Form Code");
        v_fetchItemFacilityMap.setItemFormDesc("Item Form Desc");
        v_fetchItemFacilityMap.setItemFormID(1);
        v_fetchItemFacilityMap.setItemFormName("Item Form Name");
        v_fetchItemFacilityMap.setItemName("Item Name");
        v_fetchItemFacilityMap.setLocation("Location");
        v_fetchItemFacilityMap.setManufacturerID(1);
        v_fetchItemFacilityMap.setPharmCategoryCode("Pharm Category Code");
        v_fetchItemFacilityMap.setPharmCategoryDesc("Pharm Category Desc");
        v_fetchItemFacilityMap.setPharmCategoryName("Pharm Category Name");
        v_fetchItemFacilityMap.setPharmacologyCategoryID(1);
        v_fetchItemFacilityMap.setPhysicalLocation("Physical Location");
        v_fetchItemFacilityMap.setProviderServiceMapID(1);
        v_fetchItemFacilityMap.setRouteID(1);
        v_fetchItemFacilityMap.setStatus("Status");
        v_fetchItemFacilityMap.setStoreType("Store Type");
        v_fetchItemFacilityMap.setStrength("Strength");
        String actualToStringResult = v_fetchItemFacilityMap.toString();
        String actualFacilityCode = v_fetchItemFacilityMap.getFacilityCode();
        String actualFacilityDesc = v_fetchItemFacilityMap.getFacilityDesc();
        Integer actualFacilityID = v_fetchItemFacilityMap.getFacilityID();
        String actualFacilityName = v_fetchItemFacilityMap.getFacilityName();
        String actualFacilityTypeCode = v_fetchItemFacilityMap.getFacilityTypeCode();
        String actualFacilityTypeDesc = v_fetchItemFacilityMap.getFacilityTypeDesc();
        Integer actualFacilityTypeID = v_fetchItemFacilityMap.getFacilityTypeID();
        String actualFacilityTypeName = v_fetchItemFacilityMap.getFacilityTypeName();
        Boolean actualIsMainFacility = v_fetchItemFacilityMap.getIsMainFacility();
        Boolean actualIsMedical = v_fetchItemFacilityMap.getIsMedical();
        Boolean actualIsScheduledDrug = v_fetchItemFacilityMap.getIsScheduledDrug();
        String actualItemCategoryCode = v_fetchItemFacilityMap.getItemCategoryCode();
        String actualItemCategoryDesc = v_fetchItemFacilityMap.getItemCategoryDesc();
        Integer actualItemCategoryID = v_fetchItemFacilityMap.getItemCategoryID();
        String actualItemCategoryName = v_fetchItemFacilityMap.getItemCategoryName();
        String actualItemCode = v_fetchItemFacilityMap.getItemCode();
        String actualItemDesc = v_fetchItemFacilityMap.getItemDesc();
        Integer actualItemFacilityMapID = v_fetchItemFacilityMap.getItemFacilityMapID();
        Boolean actualItemFacilityMappingDeleted = v_fetchItemFacilityMap.getItemFacilityMappingDeleted();
        String actualItemFormCode = v_fetchItemFacilityMap.getItemFormCode();
        String actualItemFormDesc = v_fetchItemFacilityMap.getItemFormDesc();
        Integer actualItemFormID = v_fetchItemFacilityMap.getItemFormID();
        String actualItemFormName = v_fetchItemFacilityMap.getItemFormName();
        String actualItemName = v_fetchItemFacilityMap.getItemName();
        String actualLocation = v_fetchItemFacilityMap.getLocation();
        Integer actualManufacturerID = v_fetchItemFacilityMap.getManufacturerID();
        String actualPharmCategoryCode = v_fetchItemFacilityMap.getPharmCategoryCode();
        String actualPharmCategoryDesc = v_fetchItemFacilityMap.getPharmCategoryDesc();
        String actualPharmCategoryName = v_fetchItemFacilityMap.getPharmCategoryName();
        Integer actualPharmacologyCategoryID = v_fetchItemFacilityMap.getPharmacologyCategoryID();
        String actualPhysicalLocation = v_fetchItemFacilityMap.getPhysicalLocation();
        Integer actualProviderServiceMapID = v_fetchItemFacilityMap.getProviderServiceMapID();
        Integer actualRouteID = v_fetchItemFacilityMap.getRouteID();
        String actualStatus = v_fetchItemFacilityMap.getStatus();
        String actualStoreType = v_fetchItemFacilityMap.getStoreType();

        // Assert that nothing has changed
        assertEquals("Facility Code", actualFacilityCode);
        assertEquals("Facility Desc", actualFacilityDesc);
        assertEquals("Facility Name", actualFacilityName);
        assertEquals("Facility Type Code", actualFacilityTypeCode);
        assertEquals("Facility Type Desc", actualFacilityTypeDesc);
        assertEquals("Facility Type Name", actualFacilityTypeName);
        assertEquals("Item Category Code", actualItemCategoryCode);
        assertEquals("Item Category Desc", actualItemCategoryDesc);
        assertEquals("Item Category Name", actualItemCategoryName);
        assertEquals("Item Code", actualItemCode);
        assertEquals("Item Desc", actualItemDesc);
        assertEquals("Item Form Code", actualItemFormCode);
        assertEquals("Item Form Desc", actualItemFormDesc);
        assertEquals("Item Form Name", actualItemFormName);
        assertEquals("Item Name", actualItemName);
        assertEquals("Location", actualLocation);
        assertEquals("Pharm Category Code", actualPharmCategoryCode);
        assertEquals("Pharm Category Desc", actualPharmCategoryDesc);
        assertEquals("Pharm Category Name", actualPharmCategoryName);
        assertEquals("Physical Location", actualPhysicalLocation);
        assertEquals("Status", actualStatus);
        assertEquals("Store Type", actualStoreType);
        assertEquals("Strength", v_fetchItemFacilityMap.getStrength());
        assertEquals(
                "{\"itemFacilityMapID\":1,\"providerServiceMapID\":1,\"status\":\"Status\",\"itemFacilityMappingDeleted\":true,"
                        + "\"facilityID\":1,\"facilityName\":\"Facility Name\",\"facilityDesc\":\"Facility Desc\",\"facilityCode\":\"Facility"
                        + " Code\",\"facilityTypeID\":1,\"facilityTypeName\":\"Facility Type Name\",\"facilityTypeDesc\":\"Facility Type"
                        + " Desc\",\"facilityTypeCode\":\"Facility Type Code\",\"location\":\"Location\",\"physicalLocation\":\"Physical"
                        + " Location\",\"storeType\":\"Store Type\",\"isMainFacility\":true,\"itemID\":null,\"itemName\":\"Item Name\",\"itemDesc"
                        + "\":\"Item Desc\",\"itemCode\":\"Item Code\",\"itemCategoryID\":1,\"itemCategoryName\":\"Item Category Name\","
                        + "\"itemCategoryDesc\":\"Item Category Desc\",\"itemCategoryCode\":\"Item Category Code\",\"isMedical\":true,"
                        + "\"itemFormID\":1,\"itemFormName\":\"Item Form Name\",\"itemFormDesc\":\"Item Form Desc\",\"itemFormCode\":\"Item"
                        + " Form Code\",\"pharmacologyCategoryID\":1,\"pharmCategoryName\":\"Pharm Category Name\",\"pharmCategoryDesc\":\"Pharm"
                        + " Category Desc\",\"pharmCategoryCode\":\"Pharm Category Code\",\"manufacturerID\":1,\"strength\":\"Strength\","
                        + "\"isScheduledDrug\":true,\"routeID\":1}",
                actualToStringResult);
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualFacilityTypeID.intValue());
        assertEquals(1, actualItemCategoryID.intValue());
        assertEquals(1, actualItemFacilityMapID.intValue());
        assertEquals(1, actualItemFormID.intValue());
        assertEquals(1, actualManufacturerID.intValue());
        assertEquals(1, actualPharmacologyCategoryID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualRouteID.intValue());
        assertTrue(actualIsMainFacility);
        assertTrue(actualIsMedical);
        assertTrue(actualIsScheduledDrug);
        assertTrue(actualItemFacilityMappingDeleted);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link V_fetchItemFacilityMap}
     */
    @Test
    void testNewV_fetchItemFacilityMap() {
        // Arrange and Act
        V_fetchItemFacilityMap actualV_fetchItemFacilityMap = new V_fetchItemFacilityMap();

        // Assert
        assertNull(actualV_fetchItemFacilityMap.getIsMainFacility());
        assertNull(actualV_fetchItemFacilityMap.getIsMedical());
        assertNull(actualV_fetchItemFacilityMap.getIsScheduledDrug());
        assertNull(actualV_fetchItemFacilityMap.getItemFacilityMappingDeleted());
        assertNull(actualV_fetchItemFacilityMap.getFacilityID());
        assertNull(actualV_fetchItemFacilityMap.getFacilityTypeID());
        assertNull(actualV_fetchItemFacilityMap.getItemCategoryID());
        assertNull(actualV_fetchItemFacilityMap.getItemFacilityMapID());
        assertNull(actualV_fetchItemFacilityMap.getItemFormID());
        assertNull(actualV_fetchItemFacilityMap.getManufacturerID());
        assertNull(actualV_fetchItemFacilityMap.getPharmacologyCategoryID());
        assertNull(actualV_fetchItemFacilityMap.getProviderServiceMapID());
        assertNull(actualV_fetchItemFacilityMap.getRouteID());
        assertNull(actualV_fetchItemFacilityMap.getFacilityCode());
        assertNull(actualV_fetchItemFacilityMap.getFacilityDesc());
        assertNull(actualV_fetchItemFacilityMap.getFacilityName());
        assertNull(actualV_fetchItemFacilityMap.getFacilityTypeCode());
        assertNull(actualV_fetchItemFacilityMap.getFacilityTypeDesc());
        assertNull(actualV_fetchItemFacilityMap.getFacilityTypeName());
        assertNull(actualV_fetchItemFacilityMap.getItemCategoryCode());
        assertNull(actualV_fetchItemFacilityMap.getItemCategoryDesc());
        assertNull(actualV_fetchItemFacilityMap.getItemCategoryName());
        assertNull(actualV_fetchItemFacilityMap.getItemCode());
        assertNull(actualV_fetchItemFacilityMap.getItemDesc());
        assertNull(actualV_fetchItemFacilityMap.getItemFormCode());
        assertNull(actualV_fetchItemFacilityMap.getItemFormDesc());
        assertNull(actualV_fetchItemFacilityMap.getItemFormName());
        assertNull(actualV_fetchItemFacilityMap.getItemName());
        assertNull(actualV_fetchItemFacilityMap.getLocation());
        assertNull(actualV_fetchItemFacilityMap.getPharmCategoryCode());
        assertNull(actualV_fetchItemFacilityMap.getPharmCategoryDesc());
        assertNull(actualV_fetchItemFacilityMap.getPharmCategoryName());
        assertNull(actualV_fetchItemFacilityMap.getPhysicalLocation());
        assertNull(actualV_fetchItemFacilityMap.getStatus());
        assertNull(actualV_fetchItemFacilityMap.getStoreType());
        assertNull(actualV_fetchItemFacilityMap.getStrength());
    }
}
