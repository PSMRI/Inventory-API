package com.iemr.inventory.controller.stockEntry;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.service.stockEntry.StockEntryServiceImpl;
import com.iemr.inventory.utils.exception.InventoryException;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class StockEntryControllerTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@InjectMocks
	StockEntryController stockEntryController;

	@Mock
	StockEntryServiceImpl stockEntryService;

	@Test
	public void physicalStockEntryTest() throws Exception {

		java.util.Date d1 = new java.util.Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		List<ItemStockEntry> ItemStockEntryList = new ArrayList<ItemStockEntry>();
		ItemStockEntryList.add(itemStockEntry);

		PhysicalStockEntry physicalStockEntry = new PhysicalStockEntry();

		physicalStockEntry.setPhyEntryID(7L);
		physicalStockEntry.setVanID(1L);
		physicalStockEntry.setVanSerialNo(3L);
		physicalStockEntry.setRefNo("Reference One");
		physicalStockEntry.setFacilityID(56);
		physicalStockEntry.setParkingPlaceID(5L);
		physicalStockEntry.setProviderServiceMapID(14);
		physicalStockEntry.setSyncFacilityID(848);
		physicalStockEntry.setStatus("Approved");
		physicalStockEntry.setDeleted(false);
		physicalStockEntry.setProcessed('P');
		physicalStockEntry.setCreatedBy("A Kundu");
		physicalStockEntry.setModifiedBy("C Medical Company");
		physicalStockEntry.setItemStockEntry(ItemStockEntryList);

		when(stockEntryService.savePhysicalStockEntry(physicalStockEntry)).thenReturn(physicalStockEntry);

		String response = stockEntryController.physicalStockEntry(physicalStockEntry);

		OutputResponse output = new OutputResponse();

		output.setResponse(physicalStockEntry.toString());

		Assertions.assertEquals(response, stockEntryController.physicalStockEntry(physicalStockEntry));
	}

	@Test
	public void physicalStockEntryTest_Exception() throws Exception {

		PhysicalStockEntry physicalStockEntry = new PhysicalStockEntry();

		when(stockEntryService.savePhysicalStockEntry(physicalStockEntry)).thenThrow(NotFoundException.class);

		String response = stockEntryController.physicalStockEntry(physicalStockEntry);

		Assertions.assertEquals(response, stockEntryController.physicalStockEntry(physicalStockEntry));
	}

	@Test
	public void getItemBatchForStoreIDTest() {

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		List<ItemStockEntry> getData = new ArrayList<ItemStockEntry>();
		getData.add(itemStockEntry);

		when(stockEntryService.getItemBatchForStoreID(itemStockEntry)).thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockEntryController.getItemBatchForStoreID(itemStockEntry));

	}

	@Test
	public void getItemBatchForStoreIDTest_Exception() throws Exception {

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		when(stockEntryService.getItemBatchForStoreID(itemStockEntry)).thenThrow(NotFoundException.class);

		String response = stockEntryController.getItemBatchForStoreID(itemStockEntry);

		Assertions.assertEquals(response, stockEntryController.getItemBatchForStoreID(itemStockEntry));

	}

	@Test
	public void allocateStockFromItemIDTest() throws InventoryException {

		Integer facilityID = 5;

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);

		List<ItemStockExit> itemStockExitList = new ArrayList<ItemStockExit>();
		itemStockExitList.add(itemStockExitO);

		AllocateItemMap allocateItemMap = new AllocateItemMap();
		allocateItemMap.setFacilityID(12);
		allocateItemMap.setItemID(13);
		allocateItemMap.setItemName("itemName");

		List<AllocateItemMap> getData = new ArrayList<AllocateItemMap>();
		getData.add(allocateItemMap);

		when(stockEntryService.getItemStockFromItemID(facilityID, itemStockExitList)).thenReturn(getData);

		String resp = stockEntryController.allocateStockFromItemID(itemStockExitO.getFacilityID(), itemStockExitList);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(),
				stockEntryController.allocateStockFromItemID(facilityID, itemStockExitList));
	}

	@Test
	public void allocateStockFromItemIDTest_Exception() throws Exception {

		List<ItemStockExit> itemStockExitList = new ArrayList<ItemStockExit>();

		Integer facilityID = null;

		when(stockEntryService.getItemStockFromItemID(facilityID, itemStockExitList))
				.thenThrow(NotFoundException.class);

		String response = stockEntryController.allocateStockFromItemID(facilityID, itemStockExitList);

		Assertions.assertEquals(response, stockEntryController.allocateStockFromItemID(facilityID, itemStockExitList));

	}

	@Test
	public void getPhysicalStockEntryTest() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentID(1L);
		itemStockinput.setStockAdjustmentDraftID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(3);

		PhysicalStockEntry physicalStockEntry = new PhysicalStockEntry();

		physicalStockEntry.setPhyEntryID(7L);
		physicalStockEntry.setVanID(1L);
		physicalStockEntry.setVanSerialNo(3L);
		physicalStockEntry.setRefNo("Reference One");
		physicalStockEntry.setFacilityID(56);
		physicalStockEntry.setParkingPlaceID(5L);
		physicalStockEntry.setProviderServiceMapID(14);
		physicalStockEntry.setSyncFacilityID(848);
		physicalStockEntry.setStatus("Approved");
		physicalStockEntry.setDeleted(false);
		physicalStockEntry.setProcessed('P');
		physicalStockEntry.setCreatedBy("A Kundu");
		physicalStockEntry.setModifiedBy("C Medical Company");

		List<PhysicalStockEntry> getData = new ArrayList<PhysicalStockEntry>();

		when(stockEntryService.getPhysicalStockEntry(itemStockinput)).thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockEntryController.getPhysicalStockEntry(itemStockinput));
	}

	@Test
	public void getPhysicalStockEntryTest_Exception() throws Exception {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockEntryService.getPhysicalStockEntry(itemStockinput)).thenThrow(NotFoundException.class);

		String response = stockEntryController.getPhysicalStockEntry(itemStockinput);

		Assertions.assertEquals(response, stockEntryController.getPhysicalStockEntry(itemStockinput));

	}

	@Test
	public void itemPartialSearchTest() {

		ItemMaster getItem = new ItemMaster();
		getItem.setItemCategoryID(12);
		getItem.setQuantity(100);
		getItem.setLastModDate(Date.valueOf("2000-08-08"));
		getItem.setModifiedBy("A Kundu");
		getItem.setCreatedDate(Date.valueOf("2000-08-08"));
		getItem.setCreatedBy("A Kundu");
		getItem.setItemID(1);
		getItem.setItemName("gxbb");
		getItem.setItemDesc("ggkdvjxdhgkjfh");
		getItem.setItemCode("dfjhghdj657");
		getItem.setItemCategoryID(5);
		getItem.setIsMedical(true);
		getItem.setPharmacologyCategoryID(6);
		getItem.setManufacturerID(12);
		getItem.setStrength("Very Strong");
		getItem.setUomID(13);
		getItem.setRouteID(7);
		getItem.setIsScheduledDrug(true);
		getItem.setComposition("Paracetamole");
		getItem.setProviderServiceMapID(14);
		getItem.setProviderServiceMap(null);
		getItem.setStatus("Ready");
		getItem.setDiscontinued(false);
		getItem.setDeleted(false);
		getItem.setProcessed('P');

		java.util.Date d1 = new java.util.Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setFacilityID(3);

		List<ItemStockEntry> getData = new ArrayList<ItemStockEntry>();
		getData.add(itemStockEntry);

		when(stockEntryService.getItemMastersPartialSearch(getItem.getItemName(), getItem.getFacilityID()))
				.thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockEntryController.itemPartialSearch(getItem));
	}

	@Test
	public void itemPartialSearchTest_Exception() throws Exception {

		ItemMaster getItem = new ItemMaster();

		when(stockEntryService.getItemMastersPartialSearch(getItem.getItemName(), getItem.getFacilityID()))
				.thenThrow(NotFoundException.class);

		String response = stockEntryController.itemPartialSearch(getItem);

		Assertions.assertEquals(response, stockEntryController.itemPartialSearch(getItem));

	}

	@Test
	public void itemBatchWithZeroPartialSearchTest() {

		ItemMaster getItem = new ItemMaster();
		getItem.setItemCategoryID(12);
		getItem.setQuantity(100);
		getItem.setLastModDate(Date.valueOf("2000-08-08"));
		getItem.setModifiedBy("A Kundu");
		getItem.setCreatedDate(Date.valueOf("2000-08-08"));
		getItem.setCreatedBy("A Kundu");
		getItem.setItemID(1);
		getItem.setItemName("gxbb");
		getItem.setItemDesc("ggkdvjxdhgkjfh");
		getItem.setItemCode("dfjhghdj657");
		getItem.setItemCategoryID(5);
		getItem.setIsMedical(true);
		getItem.setPharmacologyCategoryID(6);
		getItem.setManufacturerID(12);
		getItem.setStrength("Very Strong");
		getItem.setUomID(13);
		getItem.setRouteID(7);
		getItem.setIsScheduledDrug(true);
		getItem.setComposition("Paracetamole");
		getItem.setProviderServiceMapID(14);
		getItem.setProviderServiceMap(null);
		getItem.setStatus("Ready");
		getItem.setDiscontinued(false);
		getItem.setDeleted(false);
		getItem.setProcessed('P');

		java.util.Date d1 = new java.util.Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setFacilityID(3);

		List<ItemStockEntry> getData = new ArrayList<ItemStockEntry>();
		getData.add(itemStockEntry);

		when(stockEntryService.getItemMastersPartialSearchWithZero(getItem.getItemName(), getItem.getFacilityID()))
				.thenReturn(getData);

		String resp = stockEntryController.itemBatchWithZeroPartialSearch(getItem);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockEntryController.itemBatchWithZeroPartialSearch(getItem));

	}

	@Test
	public void itemBatchWithZeroPartialSearchTest_Exception() throws Exception {

		ItemMaster getItem = new ItemMaster();

		when(stockEntryService.getItemMastersPartialSearchWithZero(getItem.getItemName(), getItem.getFacilityID()))
				.thenThrow(NotFoundException.class);

		String response = stockEntryController.itemBatchWithZeroPartialSearch(getItem);

		Assertions.assertEquals(response, stockEntryController.itemBatchWithZeroPartialSearch(getItem));

	}

	@Test
	public void getPhysicalStockEntryItemsTest() {

		ItemStockEntryinput getItem = new ItemStockEntryinput();

		getItem.setStockAdjustmentDraftID(12L);
		getItem.setStockAdjustmentID(2L);
		getItem.setConsumptionID(3L);
		getItem.setPatientIssueID(4L);
		getItem.setStockTransferID(5L);
		getItem.setPhyEntryID(6L);
		getItem.setFacilityID(7);
		getItem.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		getItem.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));

		java.util.Date d1 = new java.util.Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setFacilityID(3);

		List<ItemStockEntry> getData = new ArrayList<ItemStockEntry>();
		getData.add(itemStockEntry);

		when(stockEntryService.getPhysicalStockEntryItems(getItem.getPhyEntryID())).thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockEntryController.getPhysicalStockEntryItems(getItem));

	}

	@Test
	public void getPhysicalStockEntryItemsTest_Exception() throws Exception {

		ItemStockEntryinput getItem = new ItemStockEntryinput();

		when(stockEntryService.getPhysicalStockEntryItems(getItem.getPhyEntryID())).thenThrow(NotFoundException.class);

		String response = stockEntryController.getPhysicalStockEntryItems(getItem);

		Assertions.assertEquals(response, stockEntryController.getPhysicalStockEntryItems(getItem));

	}

	@Test
	public void getItemwithQuantityPartialSearchTest() {

		ItemMaster item = new ItemMaster();
		item.setItemCategoryID(12);
		item.setQuantity(100);
		item.setModifiedBy("A Kundu");
		item.setCreatedBy("A Kundu");
		item.setItemID(1);
		item.setItemName("gxbb");
		item.setItemDesc("ggkdvjxdhgkjfh");
		item.setItemCode("dfjhghdj657");
		item.setItemCategoryID(5);
		item.setIsMedical(true);
		item.setPharmacologyCategoryID(6);
		item.setManufacturerID(12);
		item.setStrength("Very Strong");
		item.setUomID(13);
		item.setRouteID(7);
		item.setIsScheduledDrug(true);
		item.setComposition("Paracetamole");
		item.setProviderServiceMapID(14);
		item.setProviderServiceMap(null);
		item.setStatus("Ready");
		item.setDiscontinued(false);
		item.setDeleted(false);
		item.setProcessed('P');

		ItemMasterWithQuantityMap itemMasterWithQuantityMap = new ItemMasterWithQuantityMap();

		itemMasterWithQuantityMap.setItemStockEntryID(1L);
		itemMasterWithQuantityMap.setFacilityID(2);
		itemMasterWithQuantityMap.setItemID(12);
		itemMasterWithQuantityMap.setQuantityInHand(13);
		itemMasterWithQuantityMap.setTotalQuantity(16);
		itemMasterWithQuantityMap.setItem(item);

		List<ItemMasterWithQuantityMap> getData = new ArrayList<ItemMasterWithQuantityMap>();

		getData.add(itemMasterWithQuantityMap);

		when(stockEntryService.getItemwithQuantityPartialSearch(item.getItemName(), item.getFacilityID()))
				.thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockEntryController.getItemwithQuantityPartialSearch(item));

	}
	
	
	
	@Test
	public void getItemwithQuantityPartialSearchTest_Exception() throws Exception {

		ItemMaster item = new ItemMaster();

		when(stockEntryService.getItemwithQuantityPartialSearch(item.getItemName(), item.getFacilityID())).thenThrow(NotFoundException.class);

		String response = stockEntryController.getItemwithQuantityPartialSearch(item);

		Assertions.assertEquals(response, stockEntryController.getItemwithQuantityPartialSearch(item));

	}
}
