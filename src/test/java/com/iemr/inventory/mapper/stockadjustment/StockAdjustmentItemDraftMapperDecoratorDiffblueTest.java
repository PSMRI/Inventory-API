package com.iemr.inventory.mapper.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_ItemForm;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.data.locationmaster.M_District;
import com.iemr.inventory.data.locationmaster.State;
import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;
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

@ContextConfiguration(classes = {StockAdjustmentItemDraftMapperImpl.class})
@ExtendWith(SpringExtension.class)
class StockAdjustmentItemDraftMapperDecoratorTest {
    @Autowired
    private StockAdjustmentItemDraftMapperDecorator stockAdjustmentItemDraftMapperDecorator;

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapperDecorator#getStockAdjustmentItemDraftEdit(StockAdjustmentItemDraft)}
     */
    @Test
    void testGetStockAdjustmentItemDraftEdit() {
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
        providerServiceMap.setM_district(new M_District());
        providerServiceMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        providerServiceMap.setOutputMapper(new OutputMapper());
        providerServiceMap.setProviderServiceMapID(1);
        providerServiceMap.setServiceID(new Integer[]{1});
        providerServiceMap.setServiceProviderID(1);
        providerServiceMap.setState(new State());
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

        StockAdjustmentItemDraft stockAdjustmentItemDraftEdit = new StockAdjustmentItemDraft();
        stockAdjustmentItemDraftEdit.setAdjustedQuantity(1);
        stockAdjustmentItemDraftEdit.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stockAdjustmentItemDraftEdit.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItemDraftEdit.setDeleted(true);
        stockAdjustmentItemDraftEdit.setIsAdded(true);
        stockAdjustmentItemDraftEdit.setItemStockEntry(itemStockEntry);
        stockAdjustmentItemDraftEdit.setItemStockEntryID(1L);
        stockAdjustmentItemDraftEdit.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItemDraftEdit.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stockAdjustmentItemDraftEdit.setOutputMapper(new OutputMapper());
        stockAdjustmentItemDraftEdit.setProcessed('A');
        stockAdjustmentItemDraftEdit.setProviderServiceMapID(1);
        stockAdjustmentItemDraftEdit.setReason("Just cause");
        stockAdjustmentItemDraftEdit.setSADraftItemMapID(1L);
        stockAdjustmentItemDraftEdit.setStatus("Status");
        stockAdjustmentItemDraftEdit.setStockAdjustmentDraftID(1L);

        // Act
        StockAdjustmentItemDraftEdit actualStockAdjustmentItemDraftEdit = stockAdjustmentItemDraftMapperDecorator
                .getStockAdjustmentItemDraftEdit(stockAdjustmentItemDraftEdit);

        // Assert
        assertEquals("Batch No", actualStockAdjustmentItemDraftEdit.getBatchID());
        assertEquals("Item Name", actualStockAdjustmentItemDraftEdit.getItemName());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualStockAdjustmentItemDraftEdit.getCreatedBy());
        assertEquals("Just cause", actualStockAdjustmentItemDraftEdit.getReason());
        assertEquals(1, actualStockAdjustmentItemDraftEdit.getAdjustedQuantity().intValue());
        assertEquals(1, actualStockAdjustmentItemDraftEdit.getProviderServiceMapID().intValue());
        assertEquals(1, actualStockAdjustmentItemDraftEdit.getQuantityInHand().intValue());
        assertEquals(1L, actualStockAdjustmentItemDraftEdit.getItemStockEntryID().longValue());
        assertEquals(1L, actualStockAdjustmentItemDraftEdit.getSADraftItemMapID().longValue());
        assertTrue(actualStockAdjustmentItemDraftEdit.getDeleted());
        assertTrue(actualStockAdjustmentItemDraftEdit.getIsAdded());
    }

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapperDecorator#getStockAdjustmentItemDraftEditList(List)}
     */
    @Test
    void testGetStockAdjustmentItemDraftEditList() {
        // Arrange, Act and Assert
        assertTrue(
                stockAdjustmentItemDraftMapperDecorator.getStockAdjustmentItemDraftEditList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapperDecorator#getStockAdjustmentItemDraftEditList(List)}
     */
    @Test
    void testGetStockAdjustmentItemDraftEditList2() {
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
        providerServiceMap.setM_district(new M_District());
        providerServiceMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        providerServiceMap.setOutputMapper(new OutputMapper());
        providerServiceMap.setProviderServiceMapID(1);
        providerServiceMap.setServiceID(new Integer[]{1});
        providerServiceMap.setServiceProviderID(1);
        providerServiceMap.setState(new State());
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

        StockAdjustmentItemDraft stockAdjustmentItemDraft = new StockAdjustmentItemDraft();
        stockAdjustmentItemDraft.setAdjustedQuantity(1);
        stockAdjustmentItemDraft.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stockAdjustmentItemDraft.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItemDraft.setDeleted(true);
        stockAdjustmentItemDraft.setIsAdded(true);
        stockAdjustmentItemDraft.setItemStockEntry(itemStockEntry);
        stockAdjustmentItemDraft.setItemStockEntryID(1L);
        stockAdjustmentItemDraft.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItemDraft.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stockAdjustmentItemDraft.setOutputMapper(new OutputMapper());
        stockAdjustmentItemDraft.setProcessed('A');
        stockAdjustmentItemDraft.setProviderServiceMapID(1);
        stockAdjustmentItemDraft.setReason("Just cause");
        stockAdjustmentItemDraft.setSADraftItemMapID(1L);
        stockAdjustmentItemDraft.setStatus("Status");
        stockAdjustmentItemDraft.setStockAdjustmentDraftID(1L);

        ArrayList<StockAdjustmentItemDraft> stockAdjustmentItemDraftEdit = new ArrayList<>();
        stockAdjustmentItemDraftEdit.add(stockAdjustmentItemDraft);

        // Act and Assert
        assertTrue(stockAdjustmentItemDraftMapperDecorator.getStockAdjustmentItemDraftEditList(stockAdjustmentItemDraftEdit)
                .isEmpty());
    }

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapperDecorator#getStockAdjustmentItemEdit(StockAdjustmentItem)}
     */
    @Test
    void testGetStockAdjustmentItemEdit() {
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
        providerServiceMap.setM_district(new M_District());
        providerServiceMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        providerServiceMap.setOutputMapper(new OutputMapper());
        providerServiceMap.setProviderServiceMapID(1);
        providerServiceMap.setServiceID(new Integer[]{1});
        providerServiceMap.setServiceProviderID(1);
        providerServiceMap.setState(new State());
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

        StockAdjustmentItem stockAdjustmentItemDraftEdit = new StockAdjustmentItem();
        stockAdjustmentItemDraftEdit.setAdjustedQuantity(1);
        stockAdjustmentItemDraftEdit.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stockAdjustmentItemDraftEdit.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItemDraftEdit.setDeleted(true);
        stockAdjustmentItemDraftEdit.setFacilityID(1);
        stockAdjustmentItemDraftEdit.setIsAdded(true);
        stockAdjustmentItemDraftEdit.setItemStockEntry(itemStockEntry);
        stockAdjustmentItemDraftEdit.setItemStockEntryID(1L);
        stockAdjustmentItemDraftEdit.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItemDraftEdit.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stockAdjustmentItemDraftEdit.setOutputMapper(new OutputMapper());
        stockAdjustmentItemDraftEdit.setProcessed('A');
        stockAdjustmentItemDraftEdit.setProviderServiceMapID(1);
        stockAdjustmentItemDraftEdit.setReason("Just cause");
        stockAdjustmentItemDraftEdit.setSAItemMapID(1L);
        stockAdjustmentItemDraftEdit.setStatus("Status");
        stockAdjustmentItemDraftEdit.setStockAdjustmentID(1L);
        stockAdjustmentItemDraftEdit.setSyncFacilityID(1);
        stockAdjustmentItemDraftEdit.setVanID(1L);
        stockAdjustmentItemDraftEdit.setVanSerialNo(1L);

        // Act
        StockAdjustmentItemDraftEdit actualStockAdjustmentItemEdit = stockAdjustmentItemDraftMapperDecorator
                .getStockAdjustmentItemEdit(stockAdjustmentItemDraftEdit);

        // Assert
        assertEquals("Batch No", actualStockAdjustmentItemEdit.getBatchID());
        assertEquals("Item Name", actualStockAdjustmentItemEdit.getItemName());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualStockAdjustmentItemEdit.getCreatedBy());
        assertEquals("Just cause", actualStockAdjustmentItemEdit.getReason());
        assertEquals(1, actualStockAdjustmentItemEdit.getAdjustedQuantity().intValue());
        assertEquals(1, actualStockAdjustmentItemEdit.getProviderServiceMapID().intValue());
        assertEquals(1, actualStockAdjustmentItemEdit.getQuantityInHand().intValue());
        assertEquals(1L, actualStockAdjustmentItemEdit.getItemStockEntryID().longValue());
        assertEquals(1L, actualStockAdjustmentItemEdit.getSAItemMapID().longValue());
        assertTrue(actualStockAdjustmentItemEdit.getDeleted());
        assertTrue(actualStockAdjustmentItemEdit.getIsAdded());
    }

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapperDecorator#getStockAdjustmentItemEditList(List)}
     */
    @Test
    void testGetStockAdjustmentItemEditList() {
        // Arrange, Act and Assert
        assertTrue(stockAdjustmentItemDraftMapperDecorator.getStockAdjustmentItemEditList(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapperDecorator#getStockAdjustmentItemEditList(List)}
     */
    @Test
    void testGetStockAdjustmentItemEditList2() {
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
        providerServiceMap.setM_district(new M_District());
        providerServiceMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        providerServiceMap.setOutputMapper(new OutputMapper());
        providerServiceMap.setProviderServiceMapID(1);
        providerServiceMap.setServiceID(new Integer[]{1});
        providerServiceMap.setServiceProviderID(1);
        providerServiceMap.setState(new State());
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

        StockAdjustmentItem stockAdjustmentItem = new StockAdjustmentItem();
        stockAdjustmentItem.setAdjustedQuantity(1);
        stockAdjustmentItem.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stockAdjustmentItem.setCreatedDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItem.setDeleted(true);
        stockAdjustmentItem.setFacilityID(1);
        stockAdjustmentItem.setIsAdded(true);
        stockAdjustmentItem.setItemStockEntry(itemStockEntry);
        stockAdjustmentItem.setItemStockEntryID(1L);
        stockAdjustmentItem.setLastModDate(
                java.util.Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        stockAdjustmentItem.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stockAdjustmentItem.setOutputMapper(new OutputMapper());
        stockAdjustmentItem.setProcessed('A');
        stockAdjustmentItem.setProviderServiceMapID(1);
        stockAdjustmentItem.setReason("Just cause");
        stockAdjustmentItem.setSAItemMapID(1L);
        stockAdjustmentItem.setStatus("Status");
        stockAdjustmentItem.setStockAdjustmentID(1L);
        stockAdjustmentItem.setSyncFacilityID(1);
        stockAdjustmentItem.setVanID(1L);
        stockAdjustmentItem.setVanSerialNo(1L);

        ArrayList<StockAdjustmentItem> stockAdjustmentItemDraftEdit = new ArrayList<>();
        stockAdjustmentItemDraftEdit.add(stockAdjustmentItem);

        // Act and Assert
        assertEquals(1,
                stockAdjustmentItemDraftMapperDecorator.getStockAdjustmentItemEditList(stockAdjustmentItemDraftEdit).size());
    }
}
