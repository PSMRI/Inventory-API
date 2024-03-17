package com.iemr.inventory.service.stockEntry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemBatchList;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.mapper.stockExit.ItemBatchListMap;
import com.iemr.inventory.mapper.stockExit.ItemMasterWithQuantityMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockEntry.PhysicalStockEntryRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;
import com.iemr.inventory.service.item.ItemService;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
class StockEntryServiceImplTest {

	@InjectMocks
	StockEntryServiceImpl stockEntryServiceImpl;

	@Mock
	PhysicalStockEntryRepo physicalStockEntryRepo;

	@Mock
	ItemStockEntryRepo itemStockEntryRepo;

	@Mock
	ItemService itemService;

	@Mock
	M_itemfacilitymappingRepo m_itemfacilitymappingRepo;

	@Mock
	ItemMasterWithQuantityMapper itemMasterWithQuantityMapper;

	@Mock
	ItemBatchListMap itemBatchListMap;

	@Test
	public void savePhysicalStockEntryTest() throws Exception {

		ItemStockEntry stock = new ItemStockEntry();

		stock.setEntryType("physicalStockEntry");
		stock.setQuantityInHand(100);
		stock.setVanID(3L);
		stock.setParkingPlaceID(6L);
		stock.setCreatedBy("ytyty");
		stock.setSyncFacilityID(8);

		stock.toString();

		List<ItemStockEntry> stockList = new ArrayList<ItemStockEntry>();

		stockList.add(stock);

		PhysicalStockEntry physicalStockEntry = new PhysicalStockEntry();

		physicalStockEntry.setSyncFacilityID(3);

		physicalStockEntry.setPhyEntryID(2L);
		physicalStockEntry.setVanID(3L);
		physicalStockEntry.setParkingPlaceID(6L);
		physicalStockEntry.setCreatedBy("ytyty");
		physicalStockEntry.setFacilityID(8);
		physicalStockEntry.setItemStockEntry(stockList);
		physicalStockEntry.toString();

		when(physicalStockEntryRepo.save(physicalStockEntry)).thenReturn(physicalStockEntry);
		when(itemStockEntryRepo.saveAll(physicalStockEntry.getItemStockEntry())).thenReturn(stockList);

		// Assertions
		Assertions.assertEquals(physicalStockEntry, stockEntryServiceImpl.savePhysicalStockEntry(physicalStockEntry));

	}

	public Integer updateStocks(List<ItemStockExit> itemissueListUpdated) {
		Integer cnt = 0;
		for (ItemStockExit itemStockExit : itemissueListUpdated) {
			cnt = cnt + itemStockEntryRepo.updateStock(itemStockExit.getFacilityID(),
					itemStockExit.getItemStockEntryID(), itemStockExit.getQuantity());
		}

		return cnt;

	}
	
	@Test
    void testGetItemBatchForStoreID() {
        // Setup
        final ItemStockEntry itemStockEntry = new ItemStockEntry();
        itemStockEntry.setItemStockEntryID(0L);
        itemStockEntry.setFacilityID(0);
        itemStockEntry.setSyncFacilityID(0);
        itemStockEntry.setItemID(0);
        itemStockEntry.setQuantity(0);
        itemStockEntry.setQuantityInHand(0);
        itemStockEntry.setBatchNo("batchNo");
        itemStockEntry.setExpiryDate(new java.util.Date());
        itemStockEntry.setEntryTypeID(0L);
        itemStockEntry.setEntryType("insertType");
        itemStockEntry.setCreatedBy("createdBy");
        itemStockEntry.setVanID(0L);
        itemStockEntry.setParkingPlaceID(0L);

        final ItemStockEntry itemStockEntry1 = new ItemStockEntry();
        itemStockEntry1.setItemStockEntryID(0L);
        itemStockEntry1.setFacilityID(0);
        itemStockEntry1.setSyncFacilityID(0);
        itemStockEntry1.setItemID(0);
        itemStockEntry1.setQuantity(0);
        itemStockEntry1.setQuantityInHand(0);
        itemStockEntry1.setBatchNo("batchNo");
        itemStockEntry1.setExpiryDate(new java.util.Date());
        itemStockEntry1.setEntryTypeID(0L);
        itemStockEntry1.setEntryType("insertType");
        itemStockEntry1.setCreatedBy("createdBy");
        itemStockEntry1.setVanID(0L);
        itemStockEntry1.setParkingPlaceID(0L);
       
        itemStockEntry1.toString();
        
        final List<ItemStockEntry> expectedResult = List.of(itemStockEntry1);

        // Configure ItemStockEntryRepo.findByFacilityIDAndItemIDAndQuantityInHandGreaterThanAndDeletedAndExpiryDateAfter(...).
        final ItemStockEntry itemStockEntry2 = new ItemStockEntry();
        itemStockEntry2.setItemStockEntryID(0L);
        itemStockEntry2.setFacilityID(0);
        itemStockEntry2.setSyncFacilityID(0);
        itemStockEntry2.setItemID(0);
        itemStockEntry2.setQuantity(0);
        itemStockEntry2.setQuantityInHand(0);
        itemStockEntry2.setBatchNo("batchNo");
        itemStockEntry2.setExpiryDate(new java.util.Date());
        itemStockEntry2.setEntryTypeID(0L);
        itemStockEntry2.setEntryType("insertType");
        itemStockEntry2.setCreatedBy("createdBy");
        itemStockEntry2.setVanID(0L);
        itemStockEntry2.setParkingPlaceID(0L);
        
        itemStockEntry2.toString();
        
        final List<ItemStockEntry> itemStockEntries = List.of(itemStockEntry2);
        when(itemStockEntryRepo.findByFacilityIDAndItemIDAndQuantityInHandGreaterThanAndDeletedAndExpiryDateAfter(0,
                0, 0, false, new java.util.Date())).thenReturn(itemStockEntries);

        // Run the test
        final List<ItemStockEntry> result = stockEntryServiceImpl.getItemBatchForStoreID(itemStockEntry);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

	@Test
	public void getAllItemBatchForStoreIDTest() {

		Integer storeID = 9;
		Long[] itemStockID = new Long[] { 1L, 2L, 3L };

		ArrayList<Object[]> itemObjList = new ArrayList<Object[]>();
		itemObjList.add(itemStockID);

		when(itemStockEntryRepo.getQuantityOfStock(itemStockID, storeID)).thenReturn(itemObjList);

		// Assertions
		Assertions.assertEquals(itemObjList, stockEntryServiceImpl.getAllItemBatchForStoreID(storeID, itemStockID));
	}

	@Test
	public void updateStocksTest() {

		ItemStockExit itemStockExit = new ItemStockExit();

		itemStockExit.setFacilityID(5);
		itemStockExit.setItemStockEntryID(5L);
		itemStockExit.setQuantity(100);
		itemStockExit.toString();
		List<ItemStockExit> itemissueListUpdated = new ArrayList<ItemStockExit>();

		itemissueListUpdated.add(itemStockExit);

		Integer cnt = 0;

		when(cnt + itemStockEntryRepo.updateStock(itemStockExit.getFacilityID(), itemStockExit.getItemStockEntryID(),
				itemStockExit.getQuantity())).thenReturn(cnt);

		// Assertions
		Assertions.assertEquals(cnt, stockEntryServiceImpl.updateStocks(itemissueListUpdated));

	}

	@Test
	public void getItemStockForStoreIDOrderByEntryDateAscTest() {

		Integer facilityID = 5;
		Integer itemID = 3;
		java.util.Date d1 = new java.util.Date();
		java.util.Date nowdate = d1;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);

		itemStockEntry.toString();

		List<ItemStockEntry> itemStockEntryList = new ArrayList<ItemStockEntry>();

		itemStockEntryList.add(itemStockEntry);

		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						facilityID, itemID, false, 0, nowdate))
				.thenReturn(itemStockEntryList);

		// Assertions
		Assertions.assertEquals(itemStockEntryList,
				stockEntryServiceImpl.getItemStockForStoreIDOrderByEntryDateAsc(facilityID, itemID, nowdate));

	}

	@Test
	public void getItemStockForStoreIDOrderByEntryDateDescTest() {

		Integer facilityID = 5;
		Integer itemID = 3;
		java.util.Date d1 = new java.util.Date();
		java.util.Date nowdate = d1;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);

		itemStockEntry.toString();

		List<ItemStockEntry> itemStockEntryList = new ArrayList<ItemStockEntry>();

		itemStockEntryList.add(itemStockEntry);

		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByDesc(
						facilityID, itemID, false, 0, nowdate))
				.thenReturn(itemStockEntryList);

		// Assertions
		Assertions.assertEquals(itemStockEntryList,
				stockEntryServiceImpl.getItemStockForStoreIDOrderByEntryDateDesc(facilityID, itemID, nowdate));

	}

	@Test
	public void getItemStockForStoreIDOrderByExpiryDateAscTest() {

		Integer facilityID = 5;
		Integer itemID = 3;
		java.util.Date d1 = new java.util.Date();
		java.util.Date nowdate = d1;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);

		itemStockEntry.toString();

		List<ItemStockEntry> itemStockEntryList = new ArrayList<ItemStockEntry>();

		itemStockEntryList.add(itemStockEntry);

		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
						facilityID, itemID, false, 0, nowdate))
				.thenReturn(itemStockEntryList);

		// Assertions
		Assertions.assertEquals(itemStockEntryList,
				stockEntryServiceImpl.getItemStockForStoreIDOrderByExpiryDateAsc(facilityID, itemID, nowdate));

	}

	@Test
	public void testGetItemStockFromItemID_Success() throws InventoryException {
		// Define necessary objects and mock data
		Integer facilityID = 1;

		ItemStockExit itemStockExit = new ItemStockExit();

		itemStockExit.setItemID(1);
		itemStockExit.setFacilityID(5);
		itemStockExit.setItemStockEntryID(5L);
		itemStockExit.setQuantity(100);
		itemStockExit.setDuration(24);
		itemStockExit.setDurationUnit("Unit");

		itemStockExit.toString();

		List<ItemStockExit> itemStockExitList = new ArrayList<>();
		itemStockExitList.add(itemStockExit);

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setItemCategoryID(50);
		m_ItemCategory.setAlertBeforeDays(151);
		m_ItemCategory.setIssueType("Expiry");
		m_ItemCategory.setItemCategoryName("Paracitamol2");
		m_ItemCategory.setItemCategoryDesc("It is a medicine for headache");
		m_ItemCategory.setItemCategoryCode("P01C78L91112");
		m_ItemCategory.setStatus("Approved and Useable");
		m_ItemCategory.setProviderServiceMapID(89);
		m_ItemCategory.setDeleted(false);
		m_ItemCategory.setProcessed('a');
		m_ItemCategory.setCreatedBy("P Medical Company");
		m_ItemCategory.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemCategory.setModifiedBy("C Medical Company");
		m_ItemCategory.setLastModDate(Date.valueOf("2001-09-09"));

		m_ItemCategory.toString();

		ItemMaster itemMaster = new ItemMaster();
		itemMaster.setFacilityID(1);
		itemMaster.setItemID(1);
		itemMaster.setItemName("Item1");
		itemMaster.setItemCategory(m_ItemCategory);

		itemMaster.toString();

		when(itemService.getItemMasterCatByID(itemStockExit.getItemID())).thenReturn(itemMaster);

		java.util.Date d1 = new java.util.Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(1);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");
		itemStockEntry.setItem(itemMaster);

		itemStockEntry.toString();

		List<ItemStockEntry> itemStockList = new ArrayList<>();
		itemStockList.add(itemStockEntry);

		ItemBatchList itemBatchList = new ItemBatchList();

		itemBatchList.setItemStockEntryID(1L);
		itemBatchList.setFacilityID(5);
		itemBatchList.setItemID(1);
		itemBatchList.setItemStockEntryID(5L);
		itemBatchList.setQuantity(100);
		itemBatchList.setQuantityInHand(60);
		itemBatchList.setBatchNo("Bwretfh");
		itemBatchList.setExpiryDate(null);
		itemBatchList.setExpiresIn(12L);
		itemBatchList.toString();

		itemBatchList.toString();

		List<ItemStockEntry> stockList = new ArrayList<ItemStockEntry>();
		stockList.add(itemStockEntry);

		Integer itemID = itemStockExit.getItemID();

		java.util.Date nowdate = new java.util.Date();

		Mockito.when(itemBatchListMap.getItemStockExitMapList(Mockito.anyList()))
				.thenReturn(Arrays.asList(itemBatchList));

		// Perform the test
		List<AllocateItemMap> result = stockEntryServiceImpl.getItemStockFromItemID(facilityID, itemStockExitList);

		// Verify the results
		assertNotNull(result);
		assertEquals(1, result.size());
	}

	@Test
	public void testGetItemStockFromItemID_Exception() throws InventoryException {
		// Define necessary objects and mock data
		Integer facilityID = 1;
		List<ItemStockExit> itemStockExitList = new ArrayList<>();
		ItemMaster itemMaster = new ItemMaster();
		itemMaster.setFacilityID(1);
		itemMaster.setItemID(1);
		itemMaster.setItemName("Item1");

		// Perform the test
		stockEntryServiceImpl.getItemStockFromItemID(facilityID, itemStockExitList);
	}

	@Test
	public void testSaveItemStockFromStockTransfer_Success() {
		// Define necessary objects and mock data
		java.util.Date d1 = new java.util.Date();

		Date date2 = new Date(System.currentTimeMillis());

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);

		itemStockExitO.toString();

		List<ItemStockExit> itemStockExit = new ArrayList<ItemStockExit>();

		itemStockExit.add(itemStockExitO);

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
		m_Pharmacologicalcategory.setCreatedDate(date2);
		m_Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI");
		m_Pharmacologicalcategory.setLastModDate(Date.valueOf("2010-01-10"));

		m_Pharmacologicalcategory.toString();

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

		m_Manufacturer.toString();

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

		m_Uom.toString();

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

		m_Route.toString();

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

		item.toString();

		Long insertID = 7L;
		String insertType = "Select type";
		Integer facilityFromID = 7;
		Integer facilityToID = 8;
		Long toVanID = 9L;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		itemStockEntry.toString();

		List<ItemStockEntry> stockInHand = new ArrayList<ItemStockEntry>();
		stockInHand.add(itemStockEntry);

		ItemStockExit itemStockExitsingle = new ItemStockExit();

		itemStockExitsingle.setItemID(3);
		itemStockExitsingle.setFacilityID(5);
		itemStockExitsingle.setItemStockEntryID(5L);
		itemStockExitsingle.setQuantity(100);
		itemStockExitsingle.setQuantityInHand(0);
		itemStockExitsingle.setCreatedBy("A Kundu");
		itemStockExitsingle.setCreatedBy("B Kundu");
		itemStockExitsingle.setVanID(6L);

		itemStockExitsingle.toString();

		List<ItemStockEntry> itemStockEntryupList = new ArrayList<>();

		ItemStockEntry itemStockEntryup = new ItemStockEntry();

		itemStockEntryup.setItemStockEntryID(itemStockEntry.getItemStockEntryID());
		itemStockEntryup.setVanID(2L);
		itemStockEntryup.setFacilityID(facilityToID);
		itemStockEntryup.setSyncFacilityID(facilityToID);
		itemStockEntryup.setQuantity(itemStockExitsingle.getQuantity());
		itemStockEntryup.setQuantityInHand(itemStockExitsingle.getQuantity());
		itemStockEntryup.setTotalCostPrice(Double.valueOf("12"));
		itemStockEntryup.setItemID(itemStockEntry.getItemID());
		itemStockEntryup.setBatchNo(itemStockEntry.getBatchNo());
		itemStockEntryup.setExpiryDate(itemStockEntry.getExpiryDate());
		itemStockEntryup.setEntryType(insertType);
		itemStockEntryup.setEntryTypeID(insertID);
		itemStockEntryup.setSyncFacilityID(facilityFromID);
		itemStockEntryup.setCreatedBy(itemStockExitsingle.getCreatedBy());
		itemStockEntryup.setCreatedBy(itemStockExitsingle.getCreatedBy());
		itemStockEntryup.setVanID(toVanID);
		itemStockEntryup.setItem(item);
		itemStockEntryup.toString();

		itemStockEntryupList.add(itemStockEntryup);

		// Perform the test
		List<ItemStockEntry> result = stockEntryServiceImpl.saveItemStockFromStockTransfer(itemStockExit, insertID,
				insertType, facilityFromID, facilityToID, toVanID);

		// Verify the results
		assertNotNull(result);

		// Assertions
		Assertions.assertEquals(result, stockEntryServiceImpl.saveItemStockFromStockTransfer(itemStockExit, insertID,
				insertType, facilityFromID, facilityToID, toVanID));
	}

	@Test
	public void getItemStockEntryForStoreIDinStockEntryIDTest() {

		java.util.Date d1 = new java.util.Date();

		Integer facilityID = 8;
		Long[] itemstockIDs = new Long[] { 1L, 2L, 3L };

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		itemStockEntry.toString();

		List<ItemStockEntry> ItemStockEntryList = new ArrayList<ItemStockEntry>();
		ItemStockEntryList.add(itemStockEntry);

		when(itemStockEntryRepo.findByFacilityIDAndItemStockEntryIDIn(facilityID, itemstockIDs))
				.thenReturn(ItemStockEntryList);

		// Assertions
		Assertions.assertEquals(ItemStockEntryList,
				stockEntryServiceImpl.getItemStockEntryForStoreIDinStockEntryID(facilityID, itemstockIDs));

	}

	@Test
	public void getPhysicalStockEntryTest() {

		java.util.Date d1 = new java.util.Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		itemStockEntry.toString();

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
		physicalStockEntry.setCreatedDate(Date.valueOf("2000-08-08"));
		physicalStockEntry.setCreatedBy("A Kundu");
		physicalStockEntry.setModifiedBy("C Medical Company");
		physicalStockEntry.setLastModDate(Date.valueOf("2001-09-09"));
		physicalStockEntry.setItemStockEntry(ItemStockEntryList);

		physicalStockEntry.toString();

		ItemStockEntryinput itemStockEntryinput = new ItemStockEntryinput();

		itemStockEntryinput.setStockAdjustmentDraftID(12L);
		itemStockEntryinput.setStockAdjustmentID(2L);
		itemStockEntryinput.setConsumptionID(3L);
		itemStockEntryinput.setPatientIssueID(4L);
		itemStockEntryinput.setStockTransferID(5L);
		itemStockEntryinput.setPhyEntryID(6L);
		itemStockEntryinput.setFacilityID(7);
		itemStockEntryinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));

		itemStockEntryinput.toString();

		List<PhysicalStockEntry> data = new ArrayList<PhysicalStockEntry>();

		data.add(physicalStockEntry);

		when(physicalStockEntryRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
				itemStockEntryinput.getToDate())).thenReturn(data);

		// Assertions
		Assertions.assertNotNull(itemStockEntryinput.getFacilityID());
		Assertions.assertNotNull(itemStockEntryinput.getFromDate());
		Assertions.assertEquals(data, stockEntryServiceImpl.getPhysicalStockEntry(itemStockEntryinput));

	}

	@Test
	public void getItemMastersPartialSearchTest() {

		java.util.Date d1 = new java.util.Date();

		String itemName = "Medicine";
		Integer facilityID = 8;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		itemStockEntry.toString();

		List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();
		itemForsubStore.add(itemStockEntry);

		Object[] obj = new Object[] { facilityID, itemName };

		ArrayList<Object[]> resultSet = new ArrayList<Object[]>();

		resultSet.add(obj);

		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facilityID, itemName)).thenReturn(resultSet);

		Integer[] itemID = new Integer[resultSet.size()];
		Object[] objects;

		objects = resultSet.get(0);

		itemID[0] = (Integer) objects[0];

		when(itemStockEntryRepo.findByItemIDInAndQuantityInHandGreaterThanAndFacilityID(itemID, 0, facilityID))
				.thenReturn(itemForsubStore);

		// Assertions
		Assertions.assertNotEquals(0, resultSet.size());
		Assertions.assertNotNull(objects);
		Assertions.assertEquals(itemForsubStore,
				stockEntryServiceImpl.getItemMastersPartialSearch(itemName, facilityID));

	}

	@Test
	public void getPhysicalStockEntryItemsTest() {

		java.util.Date d1 = new java.util.Date();

		Long phySEID = 9L;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");
		itemStockEntry.setEntryType("physicalStockEntry");

		itemStockEntry.toString();

		List<ItemStockEntry> ItemStockEntryList = new ArrayList<ItemStockEntry>();
		ItemStockEntryList.add(itemStockEntry);

		PhysicalStockEntry pse = new PhysicalStockEntry();

		pse.setPhyEntryID(9L);
		pse.setVanID(1L);
		pse.setVanSerialNo(3L);
		pse.setRefNo("Reference One");
		pse.setFacilityID(56);
		pse.setParkingPlaceID(5L);
		pse.setProviderServiceMapID(14);
		pse.setSyncFacilityID(848);
		pse.setStatus("Approved");
		pse.setDeleted(false);
		pse.setProcessed('P');
		pse.setCreatedDate(Date.valueOf("2000-08-08"));
		pse.setCreatedBy("A Kundu");
		pse.setModifiedBy("C Medical Company");
		pse.setLastModDate(Date.valueOf("2001-09-09"));
		pse.setItemStockEntry(ItemStockEntryList);

		pse.toString();

		when(physicalStockEntryRepo.findById(phySEID)).thenReturn(Optional.of(pse));

		when(itemStockEntryRepo.findByEntryTypeIDAndSyncFacilityIDAndEntryType(pse.getVanSerialNo(),
				pse.getSyncFacilityID(), "physicalStockEntry")).thenReturn(ItemStockEntryList);

		// Assertions
		Assertions.assertEquals(ItemStockEntryList, stockEntryServiceImpl.getPhysicalStockEntryItems(phySEID));

	}

	@Test
	public void testGetItemwithQuantityPartialSearch_Success() {
		// Define necessary objects and mock data
		String itemName = "itemName";
		Integer facilityID = 1;
		ArrayList<Object[]> resultSet = new ArrayList<>();
		Object[] objects = { 1, "itemName" };
		resultSet.add(objects);
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facilityID, itemName)).thenReturn(resultSet);
		List<Object[]> itemForsubStoreObj = new ArrayList<>();
		Mockito.when(itemStockEntryRepo.findByItemIDInQuantityInHandGreaterThanForFacilityID(
				Mockito.any(Integer[].class), Mockito.anyLong(), Mockito.anyInt(), Mockito.any()))
				.thenReturn(itemForsubStoreObj);

		java.util.Date now = new java.util.Date();

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(1);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(now);
		itemStockEntry.setBatchNo("Bdfhfgdh");
		itemStockEntry.setEntryType("physicalStockEntry");

		itemStockEntry.toString();

		List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();

		itemForsubStore.add(itemStockEntry);

		ItemMasterWithQuantityMap itemMasterWithQuantityMap = new ItemMasterWithQuantityMap();

		itemMasterWithQuantityMap.setItemStockEntryID(1L);
		itemMasterWithQuantityMap.setQuantityInHand(13);
		itemMasterWithQuantityMap.setTotalQuantity(16);
		itemMasterWithQuantityMap.setFacilityID(1);
		itemMasterWithQuantityMap.setItemID(3);
		itemMasterWithQuantityMap.setQuantityInHand(0);
		itemMasterWithQuantityMap.setExpiryDate(now);

		List<ItemMasterWithQuantityMap> getData = new ArrayList<ItemMasterWithQuantityMap>();

		getData.add(itemMasterWithQuantityMap);

		// Perform the test
		List<ItemMasterWithQuantityMap> result = stockEntryServiceImpl.getItemwithQuantityPartialSearch(itemName,
				facilityID);

		// Assertions
		Assertions.assertNotEquals(0, resultSet.size());
		Assertions.assertNotNull(objects);
		Assertions.assertEquals(result, stockEntryServiceImpl.getItemwithQuantityPartialSearch(itemName, facilityID));
		assertNotNull(result);
	}

	@Test
	public void getItemMastersPartialSearchWithZeroTest() {

		java.util.Date d1 = new java.util.Date();

		String itemName = "Medicine";
		Integer facilityID = 8;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setExpiryDate(d1);
		itemStockEntry.setBatchNo("Bdfhfgdh");
		itemStockEntry.setEntryType("physicalStockEntry");

		itemStockEntry.toString();

		List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();

		itemForsubStore.add(itemStockEntry);

		Object[] obj = new Object[] { facilityID, itemName };

		ArrayList<Object[]> resultSet = new ArrayList<Object[]>();

		resultSet.add(obj);

		Integer[] itemID = new Integer[resultSet.size()];
		Object[] objects;

		objects = resultSet.get(0);

		itemID[0] = (Integer) objects[0];

		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facilityID, itemName)).thenReturn(resultSet);

		when(itemStockEntryRepo.findByItemIDInAndFacilityIDOrderByItemStockEntryIDDesc(itemID, facilityID))
				.thenReturn(itemForsubStore);

		// Assertions
		Assertions.assertNotEquals(0, resultSet.size());
		Assertions.assertNotNull(objects);
		Assertions.assertEquals(itemForsubStore,
				stockEntryServiceImpl.getItemMastersPartialSearchWithZero(itemName, facilityID));

	}
}
