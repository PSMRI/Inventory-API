package com.iemr.inventory.controller.store;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.store.M_Van;
import com.iemr.inventory.service.store.StoreService;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

	@Mock
	private StoreService storeService;

	@InjectMocks
	private StoreController storeController;

	@Mock
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void testCreateStore() {

		OutputResponse response = new OutputResponse();

		M_Facility m_Facility = new M_Facility();

		m_Facility.setFacilityID(1);
		m_Facility.setFacilityName("TestFacility");
		m_Facility.setFacilityDesc("Test  Desc");
		m_Facility.setFacilityCode("TestCode");
		m_Facility.setFacilityTypeID(123);
		m_Facility.setLocation("Pune");
		m_Facility.setPhysicalLocation("Mumbai");
		m_Facility.setStoreType("Day And Night");
		m_Facility.setStatus("Active");
		m_Facility.setIsMainFacility(true);
		m_Facility.setMainFacilityID(1111);
		m_Facility.setProviderServiceMapID(2222);

		List<M_Facility> addMainStore = new ArrayList<M_Facility>();

		addMainStore.add(m_Facility);

		String saveData = addMainStore.toString();

		String request = saveData;

		when(storeService.addAllMainStore(addMainStore)).thenReturn(addMainStore);

		String resp = storeController.createStore(request);

		response.setResponse(saveData);

		Assertions.assertEquals(resp, storeController.createStore(request));
	}

	@Test
	public void testCreateStoreException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String resp = storeController.createStore(request);

		Assertions.assertEquals(resp, storeController.createStore(request));
	}

	@Test
	public void testEditStore() {

		M_Facility mainStore = new M_Facility();

		mainStore.setFacilityID(1);
		mainStore.setFacilityDesc("Test  Desc");
		mainStore.setModifiedBy("Admin1");

		M_Facility mainStoreUpdate = mainStore;

		when(storeService.getMainStore(mainStore.getFacilityID())).thenReturn(mainStoreUpdate);

		mainStoreUpdate.setFacilityDesc(mainStore.getFacilityDesc());

		mainStoreUpdate.setModifiedBy(mainStore.getModifiedBy());

		String saveData = mainStoreUpdate.toString();

		when(storeService.createMainStore(mainStoreUpdate)).thenReturn(mainStoreUpdate);

		String store = saveData;

		String resp = storeController.editStore(store);

		OutputResponse response = new OutputResponse();

		mainStoreUpdate.setFacilityDesc(mainStore.getFacilityDesc());

		mainStoreUpdate.setModifiedBy(mainStore.getModifiedBy());

		response.setResponse(saveData);

		Assertions.assertEquals(response.toString(), storeController.editStore(store));

	}

	@Test
	public void testEditStoreException() {

		String store = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(storeService.getMainStore(any())).thenThrow(NotFoundException.class);

		String resp = storeController.editStore(store);

		Assertions.assertEquals(resp, storeController.editStore(store));
	}

	@Test
	public void testGetAllStore() {
		OutputResponse expectedResponse = new OutputResponse();

		M_Facility m_Facility = new M_Facility();

		m_Facility.setFacilityID(1);
		m_Facility.setFacilityName("TestFacility");
		m_Facility.setFacilityDesc("Test  Desc");
		m_Facility.setFacilityCode("TestCode");
		m_Facility.setFacilityTypeID(123);
		m_Facility.setLocation("Pune");
		m_Facility.setPhysicalLocation("Mumbai");
		m_Facility.setStoreType("Day And Night");
		m_Facility.setStatus("Active");
		m_Facility.setIsMainFacility(true);
		m_Facility.setMainFacilityID(1111);
		m_Facility.setProviderServiceMapID(2222);

		List<M_Facility> saveData = new ArrayList<M_Facility>();

		Integer providerServiceMapID = 1;

		saveData.add(m_Facility);

		when(storeService.getAllMainStore(providerServiceMapID)).thenReturn(saveData);

		expectedResponse.setResponse(saveData.toString());

		Assertions.assertEquals(expectedResponse.toString(), storeController.getAllStore(providerServiceMapID));
	}

	@Test
	public void testGetAllStoreException() {

		when(storeService.getAllMainStore(any())).thenThrow(NotFoundException.class);

		String resp = storeController.getAllStore(any());

		Assertions.assertEquals(resp, storeController.getAllStore(any()));
	}

	@Test
	public void testGetAllActiveStore() {

		OutputResponse expectedResponse = new OutputResponse();

		M_Facility m_Facility = new M_Facility();

		m_Facility.setFacilityID(1);
		m_Facility.setFacilityName("TestFacility");
		m_Facility.setFacilityDesc("Test  Desc");
		m_Facility.setFacilityCode("TestCode");
		m_Facility.setFacilityTypeID(123);
		m_Facility.setLocation("Pune");
		m_Facility.setPhysicalLocation("Mumbai");
		m_Facility.setStoreType("Day And Night");
		m_Facility.setStatus("Active");
		m_Facility.setIsMainFacility(true);
		m_Facility.setMainFacilityID(1111);
		m_Facility.setProviderServiceMapID(2222);

		M_Facility providerServiceMapID = new M_Facility();

		List<M_Facility> saveData = new ArrayList<>();

		saveData.add(m_Facility);

		when(storeService.getAllActiveStore(providerServiceMapID)).thenReturn(saveData);

		expectedResponse.setResponse(saveData.toString());

		Assertions.assertEquals(expectedResponse.toString(), storeController.getAllActiveStore(providerServiceMapID));

	}

	@Test
	public void testGetAllActiveStoreException() {

		when(storeService.getAllActiveStore(any())).thenThrow(NotFoundException.class);

		String resp = storeController.getAllActiveStore(any());

		Assertions.assertEquals(resp, storeController.getAllActiveStore(any()));
	}

	@Test
	public void testGetMainFacility() {

		M_Facility m_Facility = new M_Facility();

		m_Facility.setFacilityID(1);
		m_Facility.setFacilityName("TestFacility");
		m_Facility.setFacilityDesc("Test  Desc");
		m_Facility.setFacilityCode("TestCode");
		m_Facility.setFacilityTypeID(123);
		m_Facility.setLocation("Pune");
		m_Facility.setPhysicalLocation("Mumbai");
		m_Facility.setStoreType("Day And Night");
		m_Facility.setStatus("Active");
		m_Facility.setIsMainFacility(true);
		m_Facility.setMainFacilityID(1111);
		m_Facility.setProviderServiceMapID(2222);

		ArrayList<M_Facility> mainStoreUpdate = new ArrayList<M_Facility>();

		String getMainFacility = mainStoreUpdate.toString();

		String resp = storeController.getMainFacility(getMainFacility);

		OutputResponse response = new OutputResponse();

		response.setResponse(mainStoreUpdate.toString());

		Assertions.assertEquals(resp, storeController.getMainFacility(getMainFacility));
	}

	@Test
	public void testGetMainFacilityException() {

		String getMainFacility = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String resp = storeController.getMainFacility(getMainFacility);

		Assertions.assertEquals(resp, storeController.getMainFacility(getMainFacility));
	}

	@Test
	public void testGetsubFacility() {
		String getSubFacility = "test";

		M_Facility m_Facility = new M_Facility();

		m_Facility.setFacilityID(1);
		m_Facility.setFacilityName("TestFacility");
		m_Facility.setFacilityDesc("Test  Desc");
		m_Facility.setFacilityCode("TestCode");
		m_Facility.setFacilityTypeID(123);
		m_Facility.setLocation("Pune");
		m_Facility.setPhysicalLocation("Mumbai");
		m_Facility.setStoreType("Day And Night");
		m_Facility.setStatus("Active");
		m_Facility.setIsMainFacility(true);
		m_Facility.setMainFacilityID(1111);
		m_Facility.setProviderServiceMapID(2222);

		List<M_Facility> mainStoreUpdate = new ArrayList<M_Facility>();

		mainStoreUpdate.add(m_Facility);

		String request = getSubFacility.toString();

		String resp = storeController.getsubFacility(request);

		OutputResponse response = new OutputResponse();

		response.setResponse(mainStoreUpdate.toString());

		Assertions.assertEquals(resp, storeController.getsubFacility(request));

	}

	@Test
	public void testGetsubFacilityException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String resp = storeController.getsubFacility(request);

		Assertions.assertEquals(resp, storeController.getsubFacility(request));
	}

	@Test
	public void testDeleteStore() throws IEMRException {
		OutputResponse expectedResponse = new OutputResponse();

		M_Facility facility = new M_Facility();

		M_Facility mainStoreUpdate = new M_Facility();

		when(storeService.deleteStore(facility)).thenReturn(mainStoreUpdate);

		expectedResponse.setResponse(mainStoreUpdate.toString());

		Assertions.assertEquals(expectedResponse.toString(), storeController.deleteStore(facility));
	}

	@Test
	public void testDeleteStoreException() throws IEMRException {

		String facility = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String resp = storeController.getsubFacility(facility);

		Assertions.assertEquals(resp, storeController.getsubFacility(facility));
	}

	@Test
	public void testGetStoreByID() {
		OutputResponse expectedResponse = new OutputResponse();

		M_Facility facility = new M_Facility();

		M_Facility mainStoreUpdate = new M_Facility();

		when(storeService.getStoreByID(facility.getFacilityID())).thenReturn(mainStoreUpdate);

		expectedResponse.setResponse(mainStoreUpdate.toString());

		Assertions.assertEquals(expectedResponse.toString(), storeController.getStoreByID(facility));

	}

	@Test
	public void testGetStoreByIDException() {

		M_Facility facility = new M_Facility();

		when(storeService.getStoreByID(facility.getFacilityID())).thenThrow(NotFoundException.class);

		String resp = storeController.getStoreByID(facility);

		Assertions.assertEquals(resp, storeController.getStoreByID(facility));
	}

	@Test
	public void testGetVanByStoreID() {

		OutputResponse expectedResponse = new OutputResponse();

		M_Van saveData = new M_Van();

		Integer storeID = 1;

		when(storeService.getVanByStoreID(storeID)).thenReturn(saveData);

		expectedResponse.setResponse(saveData.toString());

		Assertions.assertEquals(expectedResponse.toString(), storeController.getVanByStoreID(storeID));
	}
	
	@Test
	public void testGetVanByStoreIDAsNULL() {

		OutputResponse expectedResponse = new OutputResponse();

		M_Van saveData = null;

		Integer storeID = null;

		when(storeService.getVanByStoreID(storeID)).thenReturn(saveData);

		expectedResponse.setResponse((new M_Van()).toString());

		Assertions.assertNull(saveData);
		Assertions.assertEquals(expectedResponse.toString(), storeController.getVanByStoreID(storeID));
	}
	
	
	
	
	@Test
	public void testGetVanByStoreIDException() {

		Integer storeID = 1;

		when(storeService.getVanByStoreID(storeID)).thenThrow(NotFoundException.class);

		String resp = storeController.getVanByStoreID(storeID);

		Assertions.assertEquals(resp, storeController.getVanByStoreID(storeID));
	}

}
