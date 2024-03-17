package com.iemr.inventory.service.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.store.M_Van;
import com.iemr.inventory.repository.store.MainStoreRepo;
import com.iemr.inventory.repository.store.VanMasterRepository;
import com.iemr.inventory.utils.exception.IEMRException;

@ExtendWith(MockitoExtension.class)
class StoreServiceImplTest {

	@InjectMocks
	private StoreServiceImpl storeServiceImpl;

	@Mock
	private MainStoreRepo mainStoreRepo;

	@Mock
	private VanMasterRepository vanMasterRepository;

	@Test
	public void testCreateMainStore() {

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		when(mainStoreRepo.save(mainStoreFacility)).thenReturn(mainStoreFacility);

		// Assertions
		Assertions.assertEquals(mainStoreFacility, storeServiceImpl.createMainStore(mainStoreFacility));

	}

	@Test
	public void testGetMainStore() {

		Integer mainStoreID = Integer.valueOf(1);

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		when(mainStoreRepo.findById(mainStoreID)).thenReturn(Optional.of(mainStoreFacility));
		// Assertions
		Assertions.assertEquals(mainStoreFacility, storeServiceImpl.getMainStore(mainStoreID));

	}

	@Test
	public void testGetAllMainStore() {
		Integer providerServiceMapID = 1;

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		List<M_Facility> mainStoreFacilityList = new ArrayList<M_Facility>();
		mainStoreFacilityList.add(mainStoreFacility);
		when(mainStoreRepo.findByProviderServiceMapID(providerServiceMapID)).thenReturn(mainStoreFacilityList);

		// Assertions
		Assertions.assertEquals(mainStoreFacilityList, storeServiceImpl.getAllMainStore(providerServiceMapID));

	}

	@Test
	public void testaddAllMainStore() {

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		List<M_Facility> maniStore = new ArrayList<M_Facility>();

		maniStore.add(mainStoreFacility);

		when(mainStoreRepo.saveAll(maniStore)).thenReturn(maniStore);

		// Assertions
		Assertions.assertEquals(maniStore, storeServiceImpl.addAllMainStore(maniStore));

	}

	@Test
	public void testgetMainFacility() {

		Integer providerServiceMapID = 12;
		Boolean isMainFacility = true;

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		ArrayList<M_Facility> data = new ArrayList<M_Facility>();

		data.add(mainStoreFacility);

		when(mainStoreRepo.getAllMainFacility(providerServiceMapID, isMainFacility)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, storeServiceImpl.getMainFacility(providerServiceMapID, isMainFacility));

	}

	@Test
	public void testgetMainFacility2() {

		Integer providerServiceMapID = 12;
		Boolean isMainFacility = true;
		Integer mainFacilityID = 45;

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		ArrayList<M_Facility> data = new ArrayList<M_Facility>();

		data.add(mainStoreFacility);

		when(mainStoreRepo.getAllMainFacility(providerServiceMapID, isMainFacility, mainFacilityID)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data,
				storeServiceImpl.getMainFacility(providerServiceMapID, isMainFacility, mainFacilityID));

	}

	@Test
	public void testGetChildFacility() {

		Integer providerServiceMapID = 12;
		Integer mainFacilityID = 45;

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		ArrayList<M_Facility> data = new ArrayList<M_Facility>();

		data.add(mainStoreFacility);

		when(mainStoreRepo.getChildFacility(providerServiceMapID, mainFacilityID)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, storeServiceImpl.getChildFacility(providerServiceMapID, mainFacilityID));

	}

	@Test
	public void testDeleteParentStore() throws IEMRException {

		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(false);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		facility.setCreatedDate(Date.valueOf("2024-09-01"));
		facility.setModifiedBy("Vidya");
		facility.setLastModDate(Date.valueOf("2025-01-01"));

		facility.toString();

		M_Facility stores = facility;
		
		when(mainStoreRepo.findById(facility.getFacilityID())).thenReturn(Optional.of(stores));
		
		M_Facility parentStore = facility;
		
		when(mainStoreRepo.findByFacilityIDAndDeleted(stores.getMainFacilityID(),
				false)).thenReturn(parentStore);
		
		stores.setDeleted(false);
		when(mainStoreRepo.save(stores)).thenReturn(stores);

		// Assertions
		Assertions.assertNotNull(stores);
		Assertions.assertNotNull(facility.getDeleted());
		Assertions.assertNotNull(stores.getMainFacilityID());
		assertTrue(parentStore != null);
		assertTrue(stores.getMainFacilityID() != null);
		assertEquals(stores, storeServiceImpl.deleteStore(facility));

	}
	
	
	@Test
	public void testDeleteChildStore() throws IEMRException {

		M_Facility facility = new M_Facility();

		facility.toString();

		M_Facility stores = facility;
		
		when(mainStoreRepo.findById(facility.getFacilityID())).thenReturn(Optional.of(stores));

		ArrayList<M_Facility> childStore = new ArrayList<M_Facility>();
		
		when(mainStoreRepo.findByMainFacilityIDAndDeleted(facility.getFacilityID(),
				false)).thenReturn(childStore);
		
		stores.setDeleted(true);
		when(mainStoreRepo.save(stores)).thenReturn(stores);

		// Assertions
		Assertions.assertNotNull(stores);
		assertTrue(childStore.size() == 0);
		assertEquals(stores, storeServiceImpl.deleteStore(facility));

	}
	
	
	@Test
	public void testDeleteStore() throws IEMRException {
		
		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setMainFacilityID(null);
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(true);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		facility.setCreatedDate(Date.valueOf("2024-09-01"));
		facility.setModifiedBy("Vidya");
		facility.setLastModDate(Date.valueOf("2025-01-01"));

		facility.toString();

		M_Facility stores = facility;	
		
		when(mainStoreRepo.findById(facility.getFacilityID())).thenReturn(Optional.of(stores));
		
		when(mainStoreRepo.save(stores)).thenReturn(stores);

		// Assertions
		assertEquals(stores, storeServiceImpl.deleteStore(facility));

	}


	@Test
	public void testGetAllActiveStore() {
		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(false);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		facility.setCreatedDate(Date.valueOf("2024-09-01"));
		facility.setModifiedBy("Vidya");
		facility.setLastModDate(Date.valueOf("2025-01-01"));

		facility.toString();

		List<M_Facility> mainStoreFacilityList = new ArrayList<M_Facility>();

		mainStoreFacilityList.add(facility);

		when(mainStoreRepo.findByProviderServiceMapIDAndDeleted(facility.getProviderServiceMapID(),
				facility.getDeleted())).thenReturn(mainStoreFacilityList);

		// Assertions
		Assertions.assertEquals(mainStoreFacilityList, storeServiceImpl.getAllActiveStore(facility));

	}

	@Test
	public void testGetStoreByID() {
		Integer facility = 1;

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setCreatedDate(Date.valueOf("2024-09-01"));
		mainStoreFacility.setModifiedBy("Vidya");
		mainStoreFacility.setLastModDate(Date.valueOf("2025-01-01"));

		mainStoreFacility.toString();

		when(mainStoreRepo.findByFacilityIDAndDeleted(facility, false)).thenReturn(mainStoreFacility);

		// Assertions
		Assertions.assertEquals(mainStoreFacility, storeServiceImpl.getStoreByID(facility));

	}

	@Test
	public void testGetVanByStoreID() {

		Integer storeID = 1;

		M_Van m_Van = new M_Van();

		m_Van.setVanID(3);
		m_Van.setVanName("Aulto");
		m_Van.setVehicalNo("WB2626G");
		m_Van.setVanTypeID(9);
		m_Van.setVanType("Shuttle Type");
		m_Van.setProviderServiceMapID(7);
		m_Van.setCountryID(91);
		m_Van.setCountryName("India");
		m_Van.setStateID(700);
		m_Van.setStateName("West Bengal");
		m_Van.setParkingPlaceID(8);
		m_Van.setParkingPlaceName("Kolkata Bidhananagar");
		m_Van.setDeleted(false);
		m_Van.setDistrictBlockID(12);

		m_Van.toString();

		when(vanMasterRepository.findOneByFacilityIDAndDeleted(storeID, false)).thenReturn(m_Van);

		// Assertions
		Assertions.assertEquals(m_Van, storeServiceImpl.getVanByStoreID(storeID));

	}

}
