package com.iemr.inventory.controller.itemfacilitymapping;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.data.stockExit.StockTransferItem;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.service.itemfacilitymapping.M_itemfacilitymappingInter;
import com.iemr.inventory.to.itemfacilitymapping.ItemfacilityMappingTO;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class ItemfacilitymappingControllerTest {

	@InjectMocks
	ItemfacilitymappingController itemfacilitymappingController;

	@Mock
	M_itemfacilitymappingInter M_itemfacilitymappingInter;

	@Test
	public void mapItemtoStroreTest() {

		M_itemfacilitymapping itemDetailsO = new M_itemfacilitymapping();

		itemDetailsO.setItemID(12);
		itemDetailsO.setItemStoreMapID(13);
		itemDetailsO.setFacilityID(12);
		itemDetailsO.setMappingType("Many to One");
		itemDetailsO.setStatus("Ready");
		itemDetailsO.setProviderServiceMapID(19);
		itemDetailsO.setDeleted(false);
		itemDetailsO.setCreatedBy("Kanika Mondal");
		itemDetailsO.setCreatedDate(null);
		itemDetailsO.setModifiedBy("Mamata Sarkar");

		List<M_itemfacilitymapping> itemDetails = new ArrayList<M_itemfacilitymapping>();

		itemDetails.add(itemDetailsO);

		ItemfacilityMappingTO itemfacilityMappingTO = new ItemfacilityMappingTO();

		itemfacilityMappingTO.setItemID1(new Integer[] { 1, 2, 3, 4, 5, 6 });

		List<ItemfacilityMappingTO> itemdeatails2 = new ArrayList<ItemfacilityMappingTO>();

		itemdeatails2.add(itemfacilityMappingTO);

		List<M_itemfacilitymapping> resList = new ArrayList<M_itemfacilitymapping>();

		int itemDetailsIndex = 0;

		Integer[] itemID = null;

		itemID = itemfacilityMappingTO.getItemID1();

		M_itemfacilitymapping mappingDetails = itemDetails.get(itemDetailsIndex);

		M_itemfacilitymapping resDataMap = new M_itemfacilitymapping();
		resDataMap.setFacilityID(mappingDetails.getFacilityID());
		resDataMap.setItemID(itemID[0]);
		resDataMap.setMappingType(mappingDetails.getMappingType());
		resDataMap.setProviderServiceMapID(mappingDetails.getProviderServiceMapID());
		resDataMap.setStatus(mappingDetails.getStatus());
		resDataMap.setCreatedBy(mappingDetails.getCreatedBy());

		resList.add(resDataMap);

		String mapItemtoStrore = new Gson().toJson(resList);

		ArrayList<M_itemfacilitymapping> data = new ArrayList<M_itemfacilitymapping>();

		data.add(resDataMap);

		String response = itemfacilitymappingController.mapItemtoStrore(mapItemtoStrore);

		Assertions.assertEquals(response, itemfacilitymappingController.mapItemtoStrore(mapItemtoStrore));
	}

	@Test
	public void mapItemtoStroreTest_Exception() {

		String mapItemtoStrore = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = itemfacilitymappingController.mapItemtoStrore(mapItemtoStrore);

		Assertions.assertEquals(response, itemfacilitymappingController.mapItemtoStrore(mapItemtoStrore));
	}

	@Test
	public void editItemtoStroreTest() {

		M_itemfacilitymapping itemDetails = new M_itemfacilitymapping();

		itemDetails.setItemID(12);
		itemDetails.setItemStoreMapID(13);
		itemDetails.setFacilityID(12);
		itemDetails.setMappingType("Many to One");
		itemDetails.setStatus("Ready");
		itemDetails.setProviderServiceMapID(19);
		itemDetails.setDeleted(false);
		itemDetails.setCreatedBy("Kanika Mondal");
		itemDetails.setCreatedDate(null);
		itemDetails.setModifiedBy("Mamata Sarkar");

		when(M_itemfacilitymappingInter.editdata(itemDetails.getItemStoreMapID())).thenReturn(itemDetails);

		M_itemfacilitymapping getdataforedit = itemDetails;

		getdataforedit.setFacilityID(itemDetails.getFacilityID());
		getdataforedit.setItemID(itemDetails.getItemID());
		getdataforedit.setMappingType(itemDetails.getMappingType());
		getdataforedit.setProviderServiceMapID(itemDetails.getProviderServiceMapID());
		getdataforedit.setStatus(itemDetails.getStatus());

		String editItemtoStrore = new Gson().toJson(getdataforedit);

		when(M_itemfacilitymappingInter.saveEditedItem(getdataforedit)).thenReturn(getdataforedit);

		String response = itemfacilitymappingController.editItemtoStrore(editItemtoStrore);

		Assertions.assertEquals(response, itemfacilitymappingController.editItemtoStrore(editItemtoStrore));

	}

	@Test
	public void editItemtoStroreTest_Exception() {

		String editItemtoStrore = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = itemfacilitymappingController.editItemtoStrore(editItemtoStrore);

		Assertions.assertEquals(response, itemfacilitymappingController.editItemtoStrore(editItemtoStrore));
	}

	@Test
	public void deleteItemtoStroreTest() {

		M_itemfacilitymapping itemDetails = new M_itemfacilitymapping();

		itemDetails.setItemID(12);
		itemDetails.setItemStoreMapID(13);
		itemDetails.setFacilityID(12);
		itemDetails.setMappingType("Many to One");
		itemDetails.setStatus("Ready");
		itemDetails.setProviderServiceMapID(19);
		itemDetails.setDeleted(false);
		itemDetails.setCreatedBy("Kanika Mondal");
		itemDetails.setCreatedDate(null);
		itemDetails.setModifiedBy("Mamata Sarkar");
		itemDetails.setDeleted(true);

		when(M_itemfacilitymappingInter.editdata(itemDetails.getItemStoreMapID())).thenReturn(itemDetails);

		M_itemfacilitymapping getdataforedit = itemDetails;

		getdataforedit.setDeleted(itemDetails.getDeleted());

		String deleteItemtoStrore = new Gson().toJson(getdataforedit);

		when(M_itemfacilitymappingInter.saveEditedItem(getdataforedit)).thenReturn(getdataforedit);

		String response = itemfacilitymappingController.deleteItemtoStrore(deleteItemtoStrore);

		Assertions.assertNotNull(response, itemfacilitymappingController.deleteItemtoStrore(deleteItemtoStrore));

	}

	@Test
	public void deleteItemtoStroreTest_Exception() {

		String deleteItemtoStrore = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = itemfacilitymappingController.deleteItemtoStrore(deleteItemtoStrore);

		Assertions.assertEquals(response, itemfacilitymappingController.deleteItemtoStrore(deleteItemtoStrore));
	}

	@Test
	public void getSubStroreitemTest() {

		M_itemfacilitymapping itemDetails = new M_itemfacilitymapping();

		itemDetails.setItemID(12);
		itemDetails.setItemStoreMapID(13);
		itemDetails.setFacilityID(12);
		itemDetails.setMappingType("Many to One");
		itemDetails.setStatus("Ready");
		itemDetails.setProviderServiceMapID(19);
		itemDetails.setDeleted(false);
		itemDetails.setCreatedBy("Kanika Mondal");
		itemDetails.setCreatedDate(null);
		itemDetails.setModifiedBy("Mamata Sarkar");
		itemDetails.setDeleted(true);
		itemDetails.setProviderServiceMapID(12);

		ArrayList<M_itemfacilitymapping> getsubstoreData = new ArrayList<M_itemfacilitymapping>();

		getsubstoreData.add(itemDetails);

		String deleteItemtoStrore = getsubstoreData.toString();

		OutputResponse response = new OutputResponse();

		response.setResponse(getsubstoreData.toString());

		String resp = itemfacilitymappingController.getSubStroreitem(deleteItemtoStrore);

		Assertions.assertEquals(resp, itemfacilitymappingController.getSubStroreitem(deleteItemtoStrore));
	}

	@Test
	public void getSubStroreitemTest_Exception() {

		String deleteItemtoStrore = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = itemfacilitymappingController.getSubStroreitem(deleteItemtoStrore);

		Assertions.assertEquals(response, itemfacilitymappingController.getSubStroreitem(deleteItemtoStrore));
	}

	@Test
	public void getAllFacilityMappedDataTest() {

		V_fetchItemFacilityMap itemDetails = new V_fetchItemFacilityMap();

		itemDetails.setItemFacilityMapID(3);
		itemDetails.setProviderServiceMapID(19);
		itemDetails.setStatus("Ready");
		itemDetails.setItemFacilityMappingDeleted(false);
		itemDetails.setFacilityID(12);
		itemDetails.setFacilityName("V");
		itemDetails.setFacilityDesc("For V");
		itemDetails.setFacilityCode("V1E");
		itemDetails.setFacilityTypeID(21);
		itemDetails.setFacilityTypeDesc("VWType");
		itemDetails.setFacilityTypeName("For V and W");
		itemDetails.setFacilityTypeCode("E1VW");
		itemDetails.setLocation("Kolkata");
		itemDetails.setPhysicalLocation("Kolkata");
		itemDetails.setStoreType("Local");
		itemDetails.setIsMainFacility(true);
		itemDetails.setIsMedical(true);
		itemDetails.setIsScheduledDrug(true);
		itemDetails.setItemCategoryID(12);
		itemDetails.setItemCategoryName("V");
		itemDetails.setItemCategoryDesc("V");
		itemDetails.setItemCategoryCode("V");
		itemDetails.setItemFormID(2);
		itemDetails.setItemFormName("W");
		itemDetails.setItemFormDesc("W");
		itemDetails.setItemFormCode("W");
		itemDetails.setPharmacologyCategoryID(346);
		itemDetails.setPharmCategoryName("dfgd");
		itemDetails.setPharmCategoryDesc("gdshjsjhdg");
		itemDetails.setPharmCategoryCode("bfhhd");
		itemDetails.setManufacturerID(3);
		itemDetails.setStrength("Strong");
		itemDetails.setRouteID(3);

		ArrayList<V_fetchItemFacilityMap> getAllMappedData = new ArrayList<V_fetchItemFacilityMap>();

		getAllMappedData.add(itemDetails);

		String getAllFacilityMappedData = getAllMappedData.toString();

		String response = itemfacilitymappingController.getAllFacilityMappedData(getAllFacilityMappedData);

		Assertions.assertEquals(response,
				itemfacilitymappingController.getAllFacilityMappedData(getAllFacilityMappedData));
	}

	@Test
	public void getAllFacilityMappedDataTest_Exception() {

		String getAllFacilityMappedData = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = itemfacilitymappingController.getAllFacilityMappedData(getAllFacilityMappedData);

		Assertions.assertEquals(response,
				itemfacilitymappingController.getAllFacilityMappedData(getAllFacilityMappedData));
	}

	@Test
	public void getItemFromStoreIDTest() {

		Integer storeID = 12;

		int facilityID = 12;
		int itemID = 13;
		String itemName = "Acer";
		Long quantity = 5L;

		ItemInStore itemInStore = new ItemInStore(facilityID, itemID, itemName, quantity);

		List<ItemInStore> getData = new ArrayList<ItemInStore>();

		getData.add(itemInStore);

		when(M_itemfacilitymappingInter.getItemMastersFromStoreID(storeID)).thenReturn(getData);

		String response = itemfacilitymappingController.getItemFromStoreID(storeID);

		Assertions.assertEquals(response, itemfacilitymappingController.getItemFromStoreID(storeID));
	}

	@Test
	public void getItemFromStoreIDTest_Exception() {

		Integer storeID = null;

		when(M_itemfacilitymappingInter.getItemMastersFromStoreID(storeID)).thenThrow(NotFoundException.class);

		String response = itemfacilitymappingController.getItemFromStoreID(storeID);

		Assertions.assertEquals(response, itemfacilitymappingController.getItemFromStoreID(storeID));
	}

	@Test
	public void itemPartialSearchTest() {

		M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

		m_Pharmacologicalcategory.setPharmCategoryID(6);
		m_Pharmacologicalcategory.setPharmCategoryName("Cancer Type");
		m_Pharmacologicalcategory
				.setPharmCategoryDesc("For cancer patients all the required medicines can be found here");
		m_Pharmacologicalcategory.setPharmCategoryCode("CAN123");
		m_Pharmacologicalcategory.setStatus("Approved and Ready");
		m_Pharmacologicalcategory.setProviderServiceMapID(67);
		m_Pharmacologicalcategory.setDeleted(false);
		m_Pharmacologicalcategory.setCreatedBy("TATA CANC MUMBAI");
		m_Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI");
		m_Pharmacologicalcategory.setLastModDate(Date.valueOf("2010-01-10"));

		M_Manufacturer m_Manufacturer = new M_Manufacturer();

		m_Manufacturer.setManufacturerID(12);
		m_Manufacturer.setManufacturerName("Piramal PSMRI");
		m_Manufacturer.setManufacturerDesc("PSMRI Desc");
		m_Manufacturer.setStatus("Copyright Company CMMI 3");
		m_Manufacturer.setContactPerson("Mithun");
		m_Manufacturer.setcST_GST_No("143467374dfgsfhdghsgf876978");
		m_Manufacturer.setProviderServiceMapID(13);
		m_Manufacturer.setDeleted(false);
		m_Manufacturer.setCreatedBy("Piramal Sen");
		m_Manufacturer.setCreatedDate(Date.valueOf("1940-09-09"));
		m_Manufacturer.setModifiedBy("Piramal Sen");
		m_Manufacturer.setLastModDate(Date.valueOf("1990-09-09"));

		M_Uom m_Uom = new M_Uom();

		m_Uom.setuOMID(13);
		m_Uom.setuOMName("UOMN");
		m_Uom.setuOMDesc("Used for umon purpose");
		m_Uom.setuOMCode("U888M999N000N");
		m_Uom.setStatus("Ready");
		m_Uom.setProviderServiceMapID(3);
		m_Uom.setCreatedBy(null);
		m_Uom.setCreatedBy("P Medical Company");
		m_Uom.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Uom.setModifiedBy("C Medical Company");
		m_Uom.setLastModDate(Date.valueOf("2001-09-09"));

		M_Route m_Route = new M_Route();

		m_Route.setRouteID(7);
		m_Route.setRouteName("Route of Medicine");
		m_Route.setRouteDesc("It is a route for Medicines");
		m_Route.setProviderServiceMapID(8);
		m_Route.setDeleted(false);
		m_Route.setProcessed('a');
		m_Route.setCreatedBy("P Medical Company");
		m_Route.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Route.setModifiedBy("C Medical Company");
		m_Route.setLastModDate(Date.valueOf("2001-09-09"));

		ItemMaster item = new ItemMaster();
		item.setItemCategoryID(12);
		item.setQuantity(100);
		item.setLastModDate(Date.valueOf("2000-08-08"));
		item.setModifiedBy("A Kundu");
		item.setCreatedDate(Date.valueOf("2000-08-08"));
		item.setCreatedBy("A Kundu");
		item.setItemID(1);
		item.setItemName("gxbb");
		item.setItemDesc("ggkdvjxdhgkjfh");
		item.setItemCode("dfjhghdj657");
		item.setItemCategoryID(5);
		item.setIsMedical(true);
		item.setPharmacologyCategoryID(6);
		item.setPharmacologyCategory(m_Pharmacologicalcategory);
		item.setManufacturer(m_Manufacturer);
		item.setManufacturerID(12);
		item.setStrength("Very Strong");
		item.setUom(m_Uom);
		item.setUomID(13);
		item.setRoute(m_Route);
		item.setRouteID(7);
		item.setIsScheduledDrug(true);
		item.setComposition("Paracetamole");
		item.setProviderServiceMapID(14);
		item.setProviderServiceMap(null);
		item.setStatus("Ready");
		item.setDiscontinued(false);
		item.setDeleted(false);
		item.setProcessed('P');

		ItemMaster getItem = item;

		List<ItemMaster> getData = new ArrayList<ItemMaster>();

		getData.add(item);

		when(M_itemfacilitymappingInter.getItemMastersPartialSearch(getItem.getItemName(), getItem.getFacilityID()))
				.thenReturn(getData);

		String response = itemfacilitymappingController.itemPartialSearch(getItem);

		Assertions.assertEquals(response, itemfacilitymappingController.itemPartialSearch(getItem));
	}

	@Test
	public void itemPartialSearchTest_Exception() {

		ItemMaster getItem = new ItemMaster();

		when(M_itemfacilitymappingInter.getItemMastersPartialSearch(getItem.getItemName(), getItem.getFacilityID()))
				.thenThrow(NotFoundException.class);

		String response = itemfacilitymappingController.itemPartialSearch(getItem);

		Assertions.assertEquals(response, itemfacilitymappingController.itemPartialSearch(getItem));
	}

	@Test
	public void getItemBatchForStoreTransfer() {

		M_Facility m_facilitytype = new M_Facility();

		m_facilitytype.setFacilityTypeID(2);
		m_facilitytype.setStatus("Approved");
		m_facilitytype.setProviderServiceMapID(123);
		m_facilitytype.setDeleted(false);
		m_facilitytype.setCreatedBy("Dr Adil Adnan");
		m_facilitytype.setModifiedBy("Dr Adil Adnan");
		m_facilitytype.setCreatedDate(Date.valueOf("2024-01-01"));
		m_facilitytype.setLastModDate(Date.valueOf("2024-01-22"));

		StockTransferItem stores = new StockTransferItem();

		stores.setTransferFromFacilityID(12);
		stores.setTransferFromFacility(m_facilitytype);
		stores.setTransferToFacilityID(13);
		stores.setItemName("Medicine");

		ItemStockEntry itemStockEntry = new ItemStockEntry();
		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);

		List<ItemStockEntry> getData = new ArrayList<ItemStockEntry>();
		getData.add(itemStockEntry);

		when(M_itemfacilitymappingInter.getItemBatchForStoreTransfer(stores.getTransferFromFacilityID(),
				stores.getTransferToFacilityID(), stores.getItemName())).thenReturn(getData);

		String response = itemfacilitymappingController.getItemBatchForStoreTransfer(stores);

		Assertions.assertEquals(response, itemfacilitymappingController.getItemBatchForStoreTransfer(stores));
	}

	@Test
	public void getItemBatchForStoreTransfer_Exception() {

		StockTransferItem stores = new StockTransferItem();

		when(M_itemfacilitymappingInter.getItemBatchForStoreTransfer(stores.getTransferFromFacilityID(),
				stores.getTransferToFacilityID(), stores.getItemName())).thenThrow(NotFoundException.class);

		String response = itemfacilitymappingController.getItemBatchForStoreTransfer(stores);

		Assertions.assertEquals(response, itemfacilitymappingController.getItemBatchForStoreTransfer(stores));

	}
}
