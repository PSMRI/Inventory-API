package com.iemr.inventory.mapper.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_ItemForm;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.data.locationmaster.M_District;
import com.iemr.inventory.data.locationmaster.State;
import com.iemr.inventory.data.locationmaster.StateServiceMapping1;
import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.data.stockentry.ItemBatchList;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.to.provider.ProviderServiceMappingTO;
import com.iemr.inventory.utils.mapper.OutputMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemBatchListMapImpl.class})
@ExtendWith(SpringExtension.class)
class ItemBatchListMapImplTest {
    @Autowired
    private ItemBatchListMapImpl itemBatchListMapImpl;

    /**
     * Method under test:
     * {@link ItemBatchListMapImpl#getItemStockExitMap(ItemStockEntry)}
     */
    @Test
    void testGetItemStockExitMap() {
        // Arrange
        M_ItemCategory itemCategory = new M_ItemCategory();
        itemCategory.setAlertBeforeDays(1);
        itemCategory.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemCategory.setCreatedDate(mock(java.sql.Date.class));
        itemCategory.setDeleted(true);
        itemCategory.setIssueType("Issue Type");
        itemCategory.setItemCategoryCode("Item Category Code");
        itemCategory.setItemCategoryDesc("Item Category Desc");
        itemCategory.setItemCategoryID(1);
        itemCategory.setItemCategoryName("Item Category Name");
        itemCategory.setLastModDate(mock(java.sql.Date.class));
        itemCategory.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemCategory.setOutputMapper(new OutputMapper());
        itemCategory.setProcessed('A');
        itemCategory.setProviderServiceMapID(1);
        itemCategory.setStatus("Status");

        M_ItemForm itemForm = new M_ItemForm();
        itemForm.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemForm.setCreatedDate(mock(java.sql.Date.class));
        itemForm.setDeleted(true);
        itemForm.setItemForm("Item Form");
        itemForm.setItemFormDesc("Item Form Desc");
        itemForm.setItemFormID(1);
        itemForm.setLastModDate(mock(java.sql.Date.class));
        itemForm.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemForm.setOutputMapper(new OutputMapper());
        itemForm.setProcessed('A');
        itemForm.setProviderServiceMapID(1);

        M_Manufacturer manufacturer = new M_Manufacturer();
        manufacturer.setContactPerson("Contact Person");
        manufacturer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        manufacturer.setCreatedDate(mock(java.sql.Date.class));
        manufacturer.setDeleted(true);
        manufacturer.setLastModDate(mock(java.sql.Date.class));
        manufacturer.setManufacturerCode("Manufacturer Code");
        manufacturer.setManufacturerDesc("Manufacturer Desc");
        manufacturer.setManufacturerID(1);
        manufacturer.setManufacturerName("Manufacturer Name");
        manufacturer.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        manufacturer.setOutputMapper(new OutputMapper());
        manufacturer.setProviderServiceMapID(1);
        manufacturer.setStatus("Status");
        manufacturer.setcST_GST_No("C ST GST No");

        M_Pharmacologicalcategory pharmacologyCategory = new M_Pharmacologicalcategory();
        pharmacologyCategory.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        pharmacologyCategory.setCreatedDate(mock(java.sql.Date.class));
        pharmacologyCategory.setDeleted(true);
        pharmacologyCategory.setLastModDate(mock(java.sql.Date.class));
        pharmacologyCategory.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        pharmacologyCategory.setOutputMapper(new OutputMapper());
        pharmacologyCategory.setPharmCategoryCode("Pharm Category Code");
        pharmacologyCategory.setPharmCategoryDesc("Pharm Category Desc");
        pharmacologyCategory.setPharmCategoryID(1);
        pharmacologyCategory.setPharmCategoryName("Pharm Category Name");
        pharmacologyCategory.setPharmacologyCategoryID(1);
        pharmacologyCategory.setProviderServiceMapID(1);
        pharmacologyCategory.setStatus("Status");

        M_District m_district = new M_District();
        m_district.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        m_district.setCreatedDate(mock(java.sql.Date.class));
        m_district.setDeleted(true);
        m_district.setDistrictID(1);
        m_district.setDistrictName("District Name");
        m_district.setLastModDate(mock(java.sql.Date.class));
        m_district.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_district.setStateID(1);

        State state = new State();
        state.setCountryID(1);
        state.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        state.setCreatedDate(mock(java.sql.Date.class));
        state.setDeleted(true);
        state.setLastModDate(mock(java.sql.Date.class));
        state.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        state.setRoleMapping(new StateServiceMapping1());
        state.setStateCode('A');
        state.setStateID(1);
        state.setStateName("MD");

        ProviderServiceMappingTO providerServiceMap = new ProviderServiceMappingTO();
        providerServiceMap.setAddress("42 Main St");
        providerServiceMap.setCityID(1);
        providerServiceMap.setCountryID(1);
        providerServiceMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        providerServiceMap.setCreatedDate(mock(Timestamp.class));
        providerServiceMap.setDeleted(true);
        providerServiceMap.setDistrictBlockID(1);
        providerServiceMap.setDistrictID(1);
        providerServiceMap.setLastModDate(mock(Timestamp.class));
        providerServiceMap.setM_district(m_district);
        providerServiceMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        providerServiceMap.setOutputMapper(new OutputMapper());
        providerServiceMap.setProviderServiceMapID(1);
        providerServiceMap.setServiceID(new Integer[]{1});
        providerServiceMap.setServiceProviderID(1);
        providerServiceMap.setState(state);
        providerServiceMap.setStateID(1);
        providerServiceMap.setStatusID(1);

        M_Route route = new M_Route();
        route.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        route.setCreatedDate(mock(java.sql.Date.class));
        route.setDeleted(true);
        route.setLastModDate(mock(java.sql.Date.class));
        route.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        route.setOutputMapper(new OutputMapper());
        route.setProcessed('A');
        route.setProviderServiceMapID(1);
        route.setRouteDesc("Route Desc");
        route.setRouteID(1);
        route.setRouteName("Route Name");

        M_Uom uom = new M_Uom();
        uom.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        uom.setCreatedDate(mock(java.sql.Date.class));
        uom.setDeleted(true);
        uom.setLastModDate(mock(java.sql.Date.class));
        uom.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        uom.setProcessed('A');
        uom.setProviderServiceMapID(1);
        uom.setStatus("Status");
        uom.setuOMCode("U OMCode");
        uom.setuOMDesc("U OMDesc");
        uom.setuOMID(1);
        uom.setuOMName("U OMName");

        ItemMaster item = new ItemMaster();
        item.setComposition("Composition");
        item.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        item.setCreatedDate(mock(java.sql.Date.class));
        item.setDeleted(true);
        item.setDiscontinued(true);
        item.setFacilityID(1);
        item.setIsMedical(true);
        item.setIsScheduledDrug(true);
        item.setItemCategory(itemCategory);
        item.setItemCategoryID(1);
        item.setItemCode("Item Code");
        item.setItemDesc("Item Desc");
        item.setItemForm(itemForm);
        item.setItemFormID(1);
        item.setItemID(1);
        item.setItemName("Item Name");
        item.setLastModDate(mock(java.sql.Date.class));
        item.setManufacturer(manufacturer);
        item.setManufacturerID(1);
        item.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        item.setOutputMapper(new OutputMapper());
        item.setPharmacologyCategory(pharmacologyCategory);
        item.setPharmacologyCategoryID(1);
        item.setProcessed('A');
        item.setProviderServiceMap(providerServiceMap);
        item.setProviderServiceMapID(1);
        item.setQuantity(1);
        item.setRoute(route);
        item.setRouteID(1);
        item.setStatus("Status");
        item.setStrength("Strength");
        item.setUom(uom);
        item.setUomID(1);

        PhysicalStockEntry physicalStockEntry = new PhysicalStockEntry();
        physicalStockEntry.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        physicalStockEntry.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        physicalStockEntry.setDeleted(true);
        physicalStockEntry.setFacilityID(1);
        physicalStockEntry.setItemStockEntry(new ArrayList<>());
        physicalStockEntry.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        physicalStockEntry.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        physicalStockEntry.setOutputMapper(new OutputMapper());
        physicalStockEntry.setParkingPlaceID(1L);
        physicalStockEntry.setPhyEntryID(1L);
        physicalStockEntry.setProcessed('A');
        physicalStockEntry.setProviderServiceMapID(1);
        physicalStockEntry.setRefNo("Ref No");
        physicalStockEntry.setStatus("Status");
        physicalStockEntry.setSyncFacilityID(1);
        physicalStockEntry.setVanID(1L);
        physicalStockEntry.setVanSerialNo(1L);

        ItemStockEntry itemStockExit = new ItemStockEntry();
        itemStockExit.setBatchNo("Batch No");
        itemStockExit.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockExit.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        itemStockExit.setDeleted(true);
        itemStockExit.setEntryType("Entry Type");
        itemStockExit.setEntryTypeID(1L);
        java.util.Date expiryDate = java.util.Date
                .from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        itemStockExit.setExpiryDate(expiryDate);
        itemStockExit.setFacilityID(1);
        itemStockExit.setItem(item);
        itemStockExit.setItemID(1);
        itemStockExit.setItemStockEntryID(1L);
        itemStockExit.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        itemStockExit.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        OutputMapper outputMapper = new OutputMapper();
        itemStockExit.setOutputMapper(outputMapper);
        itemStockExit.setParkingPlaceID(1L);
        itemStockExit.setPhysicalStockEntry(physicalStockEntry);
        itemStockExit.setProcessed('A');
        itemStockExit.setQuantity(1);
        itemStockExit.setQuantityInHand(1);
        itemStockExit.setSyncFacilityID(1);
        itemStockExit.setTotalCostPrice(10.0d);
        itemStockExit.setTotalQuantity(1L);
        itemStockExit.setVanID(1L);
        itemStockExit.setVanSerialNo(1L);

        // Act
        ItemBatchList actualItemStockExitMap = itemBatchListMapImpl.getItemStockExitMap(itemStockExit);

        // Assert
        assertEquals("Batch No", actualItemStockExitMap.getBatchNo());
        assertEquals(1, actualItemStockExitMap.getFacilityID().intValue());
        assertEquals(1, actualItemStockExitMap.getItemID().intValue());
        assertEquals(1, actualItemStockExitMap.getQuantity().intValue());
        assertEquals(1, actualItemStockExitMap.getQuantityInHand().intValue());
        assertEquals(1L, actualItemStockExitMap.getItemStockEntryID().longValue());
        assertSame(outputMapper, actualItemStockExitMap.getOutputMapper());
        assertSame(expiryDate, actualItemStockExitMap.getExpiryDate());
    }

    /**
     * Method under test: {@link ItemBatchListMapImpl#getItemStockExitMapList(List)}
     */
    @Test
    void testGetItemStockExitMapList() {
        // Arrange, Act and Assert
        assertTrue(itemBatchListMapImpl.getItemStockExitMapList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link ItemBatchListMapImpl#getItemStockExitMapList(List)}
     */
    @Test
    void testGetItemStockExitMapList2() {
        // Arrange
        M_ItemCategory itemCategory = new M_ItemCategory();
        itemCategory.setAlertBeforeDays(1);
        itemCategory.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemCategory.setCreatedDate(mock(java.sql.Date.class));
        itemCategory.setDeleted(true);
        itemCategory.setIssueType("Issue Type");
        itemCategory.setItemCategoryCode("Item Category Code");
        itemCategory.setItemCategoryDesc("Item Category Desc");
        itemCategory.setItemCategoryID(1);
        itemCategory.setItemCategoryName("Item Category Name");
        itemCategory.setLastModDate(mock(java.sql.Date.class));
        itemCategory.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemCategory.setOutputMapper(new OutputMapper());
        itemCategory.setProcessed('A');
        itemCategory.setProviderServiceMapID(1);
        itemCategory.setStatus("Status");

        M_ItemForm itemForm = new M_ItemForm();
        itemForm.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemForm.setCreatedDate(mock(java.sql.Date.class));
        itemForm.setDeleted(true);
        itemForm.setItemForm("Item Form");
        itemForm.setItemFormDesc("Item Form Desc");
        itemForm.setItemFormID(1);
        itemForm.setLastModDate(mock(java.sql.Date.class));
        itemForm.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemForm.setOutputMapper(new OutputMapper());
        itemForm.setProcessed('A');
        itemForm.setProviderServiceMapID(1);

        M_Manufacturer manufacturer = new M_Manufacturer();
        manufacturer.setContactPerson("Contact Person");
        manufacturer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        manufacturer.setCreatedDate(mock(java.sql.Date.class));
        manufacturer.setDeleted(true);
        manufacturer.setLastModDate(mock(java.sql.Date.class));
        manufacturer.setManufacturerCode("Manufacturer Code");
        manufacturer.setManufacturerDesc("Manufacturer Desc");
        manufacturer.setManufacturerID(1);
        manufacturer.setManufacturerName("Manufacturer Name");
        manufacturer.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        manufacturer.setOutputMapper(new OutputMapper());
        manufacturer.setProviderServiceMapID(1);
        manufacturer.setStatus("Status");
        manufacturer.setcST_GST_No("C ST GST No");

        M_Pharmacologicalcategory pharmacologyCategory = new M_Pharmacologicalcategory();
        pharmacologyCategory.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        pharmacologyCategory.setCreatedDate(mock(java.sql.Date.class));
        pharmacologyCategory.setDeleted(true);
        pharmacologyCategory.setLastModDate(mock(java.sql.Date.class));
        pharmacologyCategory.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        pharmacologyCategory.setOutputMapper(new OutputMapper());
        pharmacologyCategory.setPharmCategoryCode("Pharm Category Code");
        pharmacologyCategory.setPharmCategoryDesc("Pharm Category Desc");
        pharmacologyCategory.setPharmCategoryID(1);
        pharmacologyCategory.setPharmCategoryName("Pharm Category Name");
        pharmacologyCategory.setPharmacologyCategoryID(1);
        pharmacologyCategory.setProviderServiceMapID(1);
        pharmacologyCategory.setStatus("Status");

        M_District m_district = new M_District();
        m_district.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        m_district.setCreatedDate(mock(java.sql.Date.class));
        m_district.setDeleted(true);
        m_district.setDistrictID(1);
        m_district.setDistrictName("District Name");
        m_district.setLastModDate(mock(java.sql.Date.class));
        m_district.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_district.setStateID(1);

        State state = new State();
        state.setCountryID(1);
        state.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        state.setCreatedDate(mock(java.sql.Date.class));
        state.setDeleted(true);
        state.setLastModDate(mock(java.sql.Date.class));
        state.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        state.setRoleMapping(new StateServiceMapping1());
        state.setStateCode('A');
        state.setStateID(1);
        state.setStateName("MD");

        ProviderServiceMappingTO providerServiceMap = new ProviderServiceMappingTO();
        providerServiceMap.setAddress("42 Main St");
        providerServiceMap.setCityID(1);
        providerServiceMap.setCountryID(1);
        providerServiceMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        providerServiceMap.setCreatedDate(mock(Timestamp.class));
        providerServiceMap.setDeleted(true);
        providerServiceMap.setDistrictBlockID(1);
        providerServiceMap.setDistrictID(1);
        providerServiceMap.setLastModDate(mock(Timestamp.class));
        providerServiceMap.setM_district(m_district);
        providerServiceMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        providerServiceMap.setOutputMapper(new OutputMapper());
        providerServiceMap.setProviderServiceMapID(1);
        providerServiceMap.setServiceID(new Integer[]{1});
        providerServiceMap.setServiceProviderID(1);
        providerServiceMap.setState(state);
        providerServiceMap.setStateID(1);
        providerServiceMap.setStatusID(1);

        M_Route route = new M_Route();
        route.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        route.setCreatedDate(mock(java.sql.Date.class));
        route.setDeleted(true);
        route.setLastModDate(mock(java.sql.Date.class));
        route.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        route.setOutputMapper(new OutputMapper());
        route.setProcessed('A');
        route.setProviderServiceMapID(1);
        route.setRouteDesc("Route Desc");
        route.setRouteID(1);
        route.setRouteName("Route Name");

        M_Uom uom = new M_Uom();
        uom.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        uom.setCreatedDate(mock(java.sql.Date.class));
        uom.setDeleted(true);
        uom.setLastModDate(mock(java.sql.Date.class));
        uom.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        uom.setProcessed('A');
        uom.setProviderServiceMapID(1);
        uom.setStatus("Status");
        uom.setuOMCode("U OMCode");
        uom.setuOMDesc("U OMDesc");
        uom.setuOMID(1);
        uom.setuOMName("U OMName");

        ItemMaster item = new ItemMaster();
        item.setComposition("Composition");
        item.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        item.setCreatedDate(mock(java.sql.Date.class));
        item.setDeleted(true);
        item.setDiscontinued(true);
        item.setFacilityID(1);
        item.setIsMedical(true);
        item.setIsScheduledDrug(true);
        item.setItemCategory(itemCategory);
        item.setItemCategoryID(1);
        item.setItemCode("Item Code");
        item.setItemDesc("Item Desc");
        item.setItemForm(itemForm);
        item.setItemFormID(1);
        item.setItemID(1);
        item.setItemName("Item Name");
        item.setLastModDate(mock(java.sql.Date.class));
        item.setManufacturer(manufacturer);
        item.setManufacturerID(1);
        item.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        item.setOutputMapper(new OutputMapper());
        item.setPharmacologyCategory(pharmacologyCategory);
        item.setPharmacologyCategoryID(1);
        item.setProcessed('A');
        item.setProviderServiceMap(providerServiceMap);
        item.setProviderServiceMapID(1);
        item.setQuantity(1);
        item.setRoute(route);
        item.setRouteID(1);
        item.setStatus("Status");
        item.setStrength("Strength");
        item.setUom(uom);
        item.setUomID(1);

        PhysicalStockEntry physicalStockEntry = new PhysicalStockEntry();
        physicalStockEntry.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        physicalStockEntry.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        physicalStockEntry.setDeleted(true);
        physicalStockEntry.setFacilityID(1);
        physicalStockEntry.setItemStockEntry(new ArrayList<>());
        physicalStockEntry.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        physicalStockEntry.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        physicalStockEntry.setOutputMapper(new OutputMapper());
        physicalStockEntry.setParkingPlaceID(1L);
        physicalStockEntry.setPhyEntryID(1L);
        physicalStockEntry.setProcessed('A');
        physicalStockEntry.setProviderServiceMapID(1);
        physicalStockEntry.setRefNo("Ref No");
        physicalStockEntry.setStatus("Status");
        physicalStockEntry.setSyncFacilityID(1);
        physicalStockEntry.setVanID(1L);
        physicalStockEntry.setVanSerialNo(1L);

        ItemStockEntry itemStockEntry = new ItemStockEntry();
        itemStockEntry.setBatchNo("Batch No");
        itemStockEntry.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntry.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        itemStockEntry.setDeleted(true);
        itemStockEntry.setEntryType("Entry Type");
        itemStockEntry.setEntryTypeID(1L);
        itemStockEntry
                .setExpiryDate(java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        itemStockEntry.setFacilityID(1);
        itemStockEntry.setItem(item);
        itemStockEntry.setItemID(1);
        itemStockEntry.setItemStockEntryID(1L);
        itemStockEntry.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        itemStockEntry.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemStockEntry.setOutputMapper(new OutputMapper());
        itemStockEntry.setParkingPlaceID(1L);
        itemStockEntry.setPhysicalStockEntry(physicalStockEntry);
        itemStockEntry.setProcessed('A');
        itemStockEntry.setQuantity(1);
        itemStockEntry.setQuantityInHand(1);
        itemStockEntry.setSyncFacilityID(1);
        itemStockEntry.setTotalCostPrice(10.0d);
        itemStockEntry.setTotalQuantity(1L);
        itemStockEntry.setVanID(1L);
        itemStockEntry.setVanSerialNo(1L);

        ArrayList<ItemStockEntry> itemStockExit = new ArrayList<>();
        itemStockExit.add(itemStockEntry);

        // Act and Assert
        assertEquals(1, itemBatchListMapImpl.getItemStockExitMapList(itemStockExit).size());
    }
}
