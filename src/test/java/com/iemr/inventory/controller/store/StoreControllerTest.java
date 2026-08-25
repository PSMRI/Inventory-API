/*
* AMRIT - Accessible Medical Records via Integrated Technologies
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.inventory.controller.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.store.M_Van;
import com.iemr.inventory.service.store.StoreService;
import com.iemr.inventory.utils.exception.IEMRException;

@ExtendWith(MockitoExtension.class)
@DisplayName("StoreController Test Suite")
class StoreControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private StoreService storeService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked store service")
	void setUp() {
		StoreController controller = new StoreController();
		ReflectionTestUtils.setField(controller, "storeService", storeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static M_Facility facility(Integer id, String name) {
		M_Facility facility = new M_Facility();
		facility.setFacilityID(id);
		facility.setFacilityName(name);
		facility.setProviderServiceMapID(3);
		return facility;
	}

	@Test
	@DisplayName("createStore should save the posted batch of stores")
	void createStore_shouldSavePostedBatch() throws Exception {
		when(storeService.addAllMainStore(anyList())).thenReturn(List.of(facility(7, "Main store")));

		mockMvc.perform(post("/createStore").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{\"facilityName\":\"Main store\",\"providerServiceMapID\":3}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<List<M_Facility>> captor = ArgumentCaptor.forClass(List.class);
		verify(storeService).addAllMainStore(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	@DisplayName("createStore should report the failure when the save throws")
	void createStore_shouldReportServiceFailure() throws Exception {
		when(storeService.addAllMainStore(anyList())).thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/createStore").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("editStore should copy the description and modifier onto the stored facility before saving")
	void editStore_shouldCopyEditableFields() throws Exception {
		M_Facility stored = facility(7, "Main store");
		when(storeService.getMainStore(7)).thenReturn(stored);
		when(storeService.createMainStore(any(M_Facility.class))).thenAnswer(inv -> inv.getArgument(0));

		mockMvc.perform(post("/editStore").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"facilityDesc\":\"new desc\",\"modifiedBy\":\"tester\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_Facility> captor = ArgumentCaptor.forClass(M_Facility.class);
		verify(storeService).createMainStore(captor.capture());
		assertEquals("new desc", captor.getValue().getFacilityDesc());
		assertEquals("tester", captor.getValue().getModifiedBy());
	}

	@Test
	@DisplayName("editStore should report the failure when the store cannot be found")
	void editStore_shouldReportFailureWhenStoreMissing() throws Exception {
		when(storeService.getMainStore(7)).thenReturn(null);

		mockMvc.perform(post("/editStore").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("getAllStore should return every store of the provider service map named in the path")
	void getAllStore_shouldReturnStoresOfProviderServiceMap() throws Exception {
		when(storeService.getAllMainStore(3)).thenReturn(List.of(facility(7, "Main store")));

		mockMvc.perform(post("/getAllStore/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(storeService).getAllMainStore(3);
	}

	@Test
	@DisplayName("getAllStore should report the failure when the lookup throws")
	void getAllStore_shouldReportServiceFailure() throws Exception {
		when(storeService.getAllMainStore(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getAllStore/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getAllActiveStore should pass the posted probe facility straight to the service")
	void getAllActiveStore_shouldPassProbeThrough() throws Exception {
		when(storeService.getAllActiveStore(any(M_Facility.class)))
				.thenReturn(List.of(facility(7, "Main store")));

		mockMvc.perform(post("/getAllActiveStore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"deleted\":false}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_Facility> captor = ArgumentCaptor.forClass(M_Facility.class);
		verify(storeService).getAllActiveStore(captor.capture());
		assertEquals(Boolean.FALSE, captor.getValue().getDeleted());
	}

	@Test
	@DisplayName("getAllActiveStore should report the failure when the lookup throws")
	void getAllActiveStore_shouldReportServiceFailure() throws Exception {
		when(storeService.getAllActiveStore(any(M_Facility.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getAllActiveStore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getMainFacility should pass the provider service map and the main-facility flag through")
	void getMainFacility_shouldPassFlagThrough() throws Exception {
		when(storeService.getMainFacility(3, true))
				.thenReturn(new ArrayList<>(List.of(facility(7, "Main store"))));

		mockMvc.perform(post("/getMainFacility").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"isMainFacility\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(storeService).getMainFacility(3, true);
	}

	@Test
	@DisplayName("getMainFacility should report the failure when the lookup throws")
	void getMainFacility_shouldReportServiceFailure() throws Exception {
		when(storeService.getMainFacility(anyInt(), anyBoolean())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getMainFacility").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"isMainFacility\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getsubFacility should look the sub-stores up under the posted parent facility")
	void getsubFacility_shouldLookUpUnderParent() throws Exception {
		when(storeService.getChildFacility(3, 7))
				.thenReturn(new ArrayList<>(List.of(facility(8, "Sub store"))));

		mockMvc.perform(post("/getsubFacility").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"mainFacilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(storeService).getChildFacility(3, 7);
	}

	@Test
	@DisplayName("getsubFacility should report the failure when the lookup throws")
	void getsubFacility_shouldReportServiceFailure() throws Exception {
		when(storeService.getChildFacility(anyInt(), anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getsubFacility").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"mainFacilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("deleteStore should answer with the facility the service deactivated")
	void deleteStore_shouldReturnDeactivatedFacility() throws Exception {
		when(storeService.deleteStore(any(M_Facility.class))).thenReturn(facility(7, "Main store"));

		mockMvc.perform(post("/deleteStore").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("deleteStore should answer 200 with the refusal text when the service rejects the request")
	void deleteStore_shouldReturnRefusalTextAsSuccessfulResponse() throws Exception {
		when(storeService.deleteStore(any(M_Facility.class)))
				.thenThrow(new IEMRException("Child Stores are still active"));

		mockMvc.perform(post("/deleteStore").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.response").value("Child Stores are still active"));
	}

	@Test
	@DisplayName("getStoreByID should return the store behind the posted facility id")
	void getStoreByID_shouldReturnStore() throws Exception {
		when(storeService.getStoreByID(7)).thenReturn(facility(7, "Main store"));

		mockMvc.perform(post("/getStoreByID").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(storeService).getStoreByID(7);
	}

	@Test
	@DisplayName("getStoreByID should answer 200 with the failure text when the lookup throws")
	void getStoreByID_shouldReturnFailureTextAsSuccessfulResponse() throws Exception {
		when(storeService.getStoreByID(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getStoreByID").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getVanByStoreID should return the van attached to the store")
	void getVanByStoreID_shouldReturnVan() throws Exception {
		M_Van van = new M_Van();
		van.setVanID(4);
		when(storeService.getVanByStoreID(7)).thenReturn(van);

		mockMvc.perform(post("/getVanByStoreID/7").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(storeService).getVanByStoreID(7);
	}

	@Test
	@DisplayName("getVanByStoreID should answer with an empty van when the store has none attached")
	void getVanByStoreID_shouldReturnEmptyVanWhenNoneAttached() throws Exception {
		when(storeService.getVanByStoreID(7)).thenReturn(null);

		mockMvc.perform(post("/getVanByStoreID/7").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getVanByStoreID should report the failure when the lookup throws")
	void getVanByStoreID_shouldReportServiceFailure() throws Exception {
		when(storeService.getVanByStoreID(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getVanByStoreID/7").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}
}
