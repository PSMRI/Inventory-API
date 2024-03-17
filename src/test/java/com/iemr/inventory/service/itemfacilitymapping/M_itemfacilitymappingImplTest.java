package com.iemr.inventory.service.itemfacilitymapping;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repository.item.ItemRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;
import com.iemr.inventory.repository.itemfacilitymapping.V_fetchItemFacilityMapRepo;

@ExtendWith(MockitoExtension.class)
public class M_itemfacilitymappingImplTest {

	@InjectMocks
	M_itemfacilitymappingImpl m_itemfacilitymappingImpl;

	@Mock
	private V_fetchItemFacilityMapRepo v_fetchItemFacilityMapRepo;

	@Mock
	private M_itemfacilitymappingRepo m_itemfacilitymappingRepo;

	@Mock
	ItemStockEntryRepo itemStockEntryRepo;

	@Mock
	ItemRepo itemRepo;

	@Test
	void mapItemtoStoreTest() {

		M_itemfacilitymapping m_itemfacilitymapping = new M_itemfacilitymapping();

		m_itemfacilitymapping.setItemID(12);
		m_itemfacilitymapping.setItemStoreMapID(13);
		m_itemfacilitymapping.setFacilityID(12);
		m_itemfacilitymapping.setMappingType("Many to One");
		m_itemfacilitymapping.setStatus("Ready");
		m_itemfacilitymapping.setProviderServiceMapID(19);
		m_itemfacilitymapping.setDeleted(false);
		m_itemfacilitymapping.setCreatedBy("Kanika Mondal");
		m_itemfacilitymapping.setCreatedDate(null);
		m_itemfacilitymapping.setModifiedBy("Mamata Sarkar");
		m_itemfacilitymapping.setLastModDate(null);

		m_itemfacilitymapping.toString();

		ArrayList<M_itemfacilitymapping> data = new ArrayList<M_itemfacilitymapping>();

		data.add(m_itemfacilitymapping);

		when(m_itemfacilitymappingRepo.saveAll(data)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_itemfacilitymappingImpl.mapItemtoStore(data));

	}

	@Test
	void editdataTest() {

		int itemStoreMapID = 12;

		M_itemfacilitymapping data = new M_itemfacilitymapping();

		data.setItemStoreMapID(itemStoreMapID);

		data.toString();

		when(m_itemfacilitymappingRepo.findById(itemStoreMapID)).thenReturn(Optional.of(data));

		// Assertions
		Assertions.assertEquals(data, m_itemfacilitymappingImpl.editdata(itemStoreMapID));

	}

	@Test
	void saveEditedItemTest() {

		M_itemfacilitymapping m_itemfacilitymapping = new M_itemfacilitymapping();

		m_itemfacilitymapping.setItemID(12);
		m_itemfacilitymapping.setItemStoreMapID(13);
		m_itemfacilitymapping.setFacilityID(12);
		m_itemfacilitymapping.setMappingType("Many to One");
		m_itemfacilitymapping.setStatus("Ready");
		m_itemfacilitymapping.setProviderServiceMapID(19);
		m_itemfacilitymapping.setDeleted(false);
		m_itemfacilitymapping.setCreatedBy("Kanika Mondal");
		m_itemfacilitymapping.setCreatedDate(null);
		m_itemfacilitymapping.setModifiedBy("Mamata Sarkar");
		m_itemfacilitymapping.setLastModDate(null);

		m_itemfacilitymapping.toString();

		M_itemfacilitymapping getdataforedit = m_itemfacilitymapping;

		M_itemfacilitymapping data = new M_itemfacilitymapping();

		data = getdataforedit;

		when(m_itemfacilitymappingRepo.save(getdataforedit)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_itemfacilitymappingImpl.saveEditedItem(getdataforedit));

	}

	
	 @Test
	  void testGetsubitemforsubStote() {
	    // Arrange
	    when(m_itemfacilitymappingRepo.getItemforSubstore(Mockito.<Integer>any(), Mockito.<Integer>any()))
	            .thenReturn(new ArrayList<>());

	    // Act
	    ArrayList<M_itemfacilitymapping> actualGetsubitemforsubStoteResult = m_itemfacilitymappingImpl
	            .getsubitemforsubStote(1, 1);

	    // Assert
	    verify(m_itemfacilitymappingRepo).getItemforSubstore(Mockito.<Integer>any(), Mockito.<Integer>any());
	    assertTrue(actualGetsubitemforsubStoteResult.isEmpty());
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getsubitemforsubStote(Integer, Integer)}
	   */
	  @Test
	  void testGetsubitemforsubStote2() {
	    // Arrange
	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(new Object[]{"42"});
	    when(m_itemfacilitymappingRepo.getItemforSubstore(Mockito.<Integer>any(), Mockito.<Integer>any()))
	            .thenReturn(objectArrayList);

	    // Act
	    ArrayList<M_itemfacilitymapping> actualGetsubitemforsubStoteResult = m_itemfacilitymappingImpl
	            .getsubitemforsubStote(1, 1);

	    // Assert
	    verify(m_itemfacilitymappingRepo).getItemforSubstore(Mockito.<Integer>any(), Mockito.<Integer>any());
	    assertTrue(actualGetsubitemforsubStoteResult.isEmpty());
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getsubitemforsubStote(Integer, Integer)}
	   */
	  @Test
	  void testGetsubitemforsubStote3() {
	    // Arrange
	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(null);
	    when(m_itemfacilitymappingRepo.getItemforSubstore(Mockito.<Integer>any(), Mockito.<Integer>any()))
	            .thenReturn(objectArrayList);

	    // Act
	    ArrayList<M_itemfacilitymapping> actualGetsubitemforsubStoteResult = m_itemfacilitymappingImpl
	            .getsubitemforsubStote(1, 1);

	    // Assert
	    verify(m_itemfacilitymappingRepo).getItemforSubstore(Mockito.<Integer>any(), Mockito.<Integer>any());
	    assertTrue(actualGetsubitemforsubStoteResult.isEmpty());
	  }

	@Test
	void getAllFacilityMappedDataTest() {

		Integer providerServiceMapID = 10;

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

		itemDetails.toString();

		ArrayList<V_fetchItemFacilityMap> data = new ArrayList<V_fetchItemFacilityMap>();

		data.add(itemDetails);

		when(v_fetchItemFacilityMapRepo.getAllFacilityMappedData(providerServiceMapID)).thenReturn(data);

		Assertions.assertEquals(data, m_itemfacilitymappingImpl.getAllFacilityMappedData(providerServiceMapID));

	}

	
	@Test
	  void testGetItemMastersFromStoreID() {
	    // Arrange
	    when(m_itemfacilitymappingRepo.getItemforStore(Mockito.<Integer>any())).thenReturn(new ArrayList<>());

	    // Act
	    List<ItemInStore> actualItemMastersFromStoreID = m_itemfacilitymappingImpl.getItemMastersFromStoreID(1);

	    // Assert
	    verify(m_itemfacilitymappingRepo).getItemforStore(Mockito.<Integer>any());
	    assertTrue(actualItemMastersFromStoreID.isEmpty());
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getItemMastersFromStoreID(Integer)}
	   */
	  @Test
	  void testGetItemMastersFromStoreID2() {
	    // Arrange
	    when(itemStockEntryRepo.getQuantity(Mockito.<Integer[]>any(), Mockito.<Integer>any()))
	            .thenReturn(new ArrayList<>());

	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(new Object[]{"42"});
	    when(m_itemfacilitymappingRepo.getItemforStore(Mockito.<Integer>any())).thenReturn(objectArrayList);

	    // Act
	    List<ItemInStore> actualItemMastersFromStoreID = m_itemfacilitymappingImpl.getItemMastersFromStoreID(1);

	    // Assert
	    verify(itemStockEntryRepo).getQuantity(Mockito.<Integer[]>any(), Mockito.<Integer>any());
	    verify(m_itemfacilitymappingRepo).getItemforStore(Mockito.<Integer>any());
	    assertTrue(actualItemMastersFromStoreID.isEmpty());
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getItemMastersFromStoreID(Integer)}
	   */
	  @Test
	  void testGetItemMastersFromStoreID3() {
	    // Arrange
	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(new Object[]{"42"});
	    when(itemStockEntryRepo.getQuantity(Mockito.<Integer[]>any(), Mockito.<Integer>any())).thenReturn(objectArrayList);

	    ArrayList<Object[]> objectArrayList2 = new ArrayList<>();
	    objectArrayList2.add(new Object[]{"42"});
	    when(m_itemfacilitymappingRepo.getItemforStore(Mockito.<Integer>any())).thenReturn(objectArrayList2);

	    // Act
	    List<ItemInStore> actualItemMastersFromStoreID = m_itemfacilitymappingImpl.getItemMastersFromStoreID(1);

	    // Assert
	    verify(itemStockEntryRepo).getQuantity(Mockito.<Integer[]>any(), Mockito.<Integer>any());
	    verify(m_itemfacilitymappingRepo).getItemforStore(Mockito.<Integer>any());
	    assertTrue(actualItemMastersFromStoreID.isEmpty());
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getItemMastersFromStoreID(Integer)}
	   */
	  @Test
	  void testGetItemMastersFromStoreID4() {
	    // Arrange
	    when(itemStockEntryRepo.getQuantity(Mockito.<Integer[]>any(), Mockito.<Integer>any()))
	            .thenReturn(new ArrayList<>());

	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(null);
	    when(m_itemfacilitymappingRepo.getItemforStore(Mockito.<Integer>any())).thenReturn(objectArrayList);

	    // Act
	    List<ItemInStore> actualItemMastersFromStoreID = m_itemfacilitymappingImpl.getItemMastersFromStoreID(1);

	    // Assert
	    verify(itemStockEntryRepo).getQuantity(Mockito.<Integer[]>any(), Mockito.<Integer>any());
	    verify(m_itemfacilitymappingRepo).getItemforStore(Mockito.<Integer>any());
	    assertTrue(actualItemMastersFromStoreID.isEmpty());
	  }


	  @Test
	  void testGetItemMastersPartialSearch() {
	    // Arrange
	    when(m_itemfacilitymappingRepo.getItemforStorePartialSearch(Mockito.<Integer>any(), Mockito.<String>any()))
	            .thenReturn(new ArrayList<>());

	    // Act
	    List<ItemMaster> actualItemMastersPartialSearch = m_itemfacilitymappingImpl.getItemMastersPartialSearch("Item", 1);

	    // Assert
	    verify(m_itemfacilitymappingRepo).getItemforStorePartialSearch(Mockito.<Integer>any(), eq("Item"));
	    assertTrue(actualItemMastersPartialSearch.isEmpty());
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getItemMastersPartialSearch(String, Integer)}
	   */
	  @Test
	  void testGetItemMastersPartialSearch2() {
	    // Arrange
	    ArrayList<ItemMaster> itemMasterList = new ArrayList<>();
	    when(itemRepo.findByItemIDIn(Mockito.<Integer[]>any())).thenReturn(itemMasterList);

	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(new Object[]{"42"});
	    when(m_itemfacilitymappingRepo.getItemforStorePartialSearch(Mockito.<Integer>any(), Mockito.<String>any()))
	            .thenReturn(objectArrayList);

	    // Act
	    List<ItemMaster> actualItemMastersPartialSearch = m_itemfacilitymappingImpl.getItemMastersPartialSearch("Item", 1);

	    // Assert
	    verify(itemRepo).findByItemIDIn(Mockito.<Integer[]>any());
	    verify(m_itemfacilitymappingRepo).getItemforStorePartialSearch(Mockito.<Integer>any(), eq("Item"));
	    assertTrue(actualItemMastersPartialSearch.isEmpty());
	    assertSame(itemMasterList, actualItemMastersPartialSearch);
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getItemMastersPartialSearch(String, Integer)}
	   */
	  @Test
	  void testGetItemMastersPartialSearch3() {
	    // Arrange
	    ArrayList<ItemMaster> itemMasterList = new ArrayList<>();
	    when(itemRepo.findByItemIDIn(Mockito.<Integer[]>any())).thenReturn(itemMasterList);

	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(null);
	    when(m_itemfacilitymappingRepo.getItemforStorePartialSearch(Mockito.<Integer>any(), Mockito.<String>any()))
	            .thenReturn(objectArrayList);

	    // Act
	    List<ItemMaster> actualItemMastersPartialSearch = m_itemfacilitymappingImpl.getItemMastersPartialSearch("Item", 1);

	    // Assert
	    verify(itemRepo).findByItemIDIn(Mockito.<Integer[]>any());
	    verify(m_itemfacilitymappingRepo).getItemforStorePartialSearch(Mockito.<Integer>any(), eq("Item"));
	    assertTrue(actualItemMastersPartialSearch.isEmpty());
	    assertSame(itemMasterList, actualItemMastersPartialSearch);
	  }

	  /**
	   * Method under test:
	   * {@link M_itemfacilitymappingImpl#getItemMastersPartialSearch(String, Integer)}
	   */
	  @Test
	  void testGetItemMastersPartialSearch4() {
	    // Arrange
	    ArrayList<ItemMaster> itemMasterList = new ArrayList<>();
	    when(itemRepo.findByItemIDIn(Mockito.<Integer[]>any())).thenReturn(itemMasterList);

	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(new Object[]{2, "42"});
	    when(m_itemfacilitymappingRepo.getItemforStorePartialSearch(Mockito.<Integer>any(), Mockito.<String>any()))
	            .thenReturn(objectArrayList);

	    // Act
	    List<ItemMaster> actualItemMastersPartialSearch = m_itemfacilitymappingImpl.getItemMastersPartialSearch("Item", 1);

	    // Assert
	    verify(itemRepo).findByItemIDIn(Mockito.<Integer[]>any());
	    verify(m_itemfacilitymappingRepo).getItemforStorePartialSearch(Mockito.<Integer>any(), eq("Item"));
	    assertTrue(actualItemMastersPartialSearch.isEmpty());
	    assertSame(itemMasterList, actualItemMastersPartialSearch);
	  }
	
	
	@Test
	  void testGetItemBatchForStoreTransfer() {
	    // Arrange
	    when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(Mockito.<Integer>any(), Mockito.<String>any()))
	            .thenReturn(new ArrayList<>());

	    // Act
	    List<ItemStockEntry> actualItemBatchForStoreTransfer = m_itemfacilitymappingImpl.getItemBatchForStoreTransfer(1, 1,
	            "Item");

	    // Assert
	    verify(m_itemfacilitymappingRepo).getItemforStoreLikeItemName(Mockito.<Integer>any(), eq("Item"));
	    assertTrue(actualItemBatchForStoreTransfer.isEmpty());
	  }
	
	  @Test
	  void testGetItemBatchForStoreTransfer2() {
	    // Arrange
	    ArrayList<ItemStockEntry> itemStockEntryList = new ArrayList<>();
	    when(itemStockEntryRepo.findByFacilityIDAndItemIDInAndQuantityInHandGreaterThanAndExpiryDateAfter(
	            Mockito.<Integer>any(), Mockito.<Integer[]>any(), Mockito.<Integer>any(), Mockito.<java.util.Date>any()))
	            .thenReturn(itemStockEntryList);

	    ArrayList<Object[]> objectArrayList = new ArrayList<>();
	    objectArrayList.add(new Object[]{"42"});
	    when(m_itemfacilitymappingRepo.getItemforStoreAndItemID(Mockito.<Integer>any(), Mockito.<Integer[]>any()))
	            .thenReturn(new ArrayList<>());
	    when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(Mockito.<Integer>any(), Mockito.<String>any()))
	            .thenReturn(objectArrayList);

	    // Act
	    List<ItemStockEntry> actualItemBatchForStoreTransfer = m_itemfacilitymappingImpl.getItemBatchForStoreTransfer(1, 1,
	            "Item");

	    // Assert
	    verify(itemStockEntryRepo).findByFacilityIDAndItemIDInAndQuantityInHandGreaterThanAndExpiryDateAfter(
	            Mockito.<Integer>any(), Mockito.<Integer[]>any(), Mockito.<Integer>any(), Mockito.<java.util.Date>any());
	    verify(m_itemfacilitymappingRepo).getItemforStoreAndItemID(Mockito.<Integer>any(), Mockito.<Integer[]>any());
	    verify(m_itemfacilitymappingRepo).getItemforStoreLikeItemName(Mockito.<Integer>any(), eq("Item"));
	    assertTrue(actualItemBatchForStoreTransfer.isEmpty());
	    assertSame(itemStockEntryList, actualItemBatchForStoreTransfer);
	  }


}
