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
package com.iemr.inventory.controller.itemfacilitymapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.service.itemfacilitymapping.M_itemfacilitymappingInter;

@ExtendWith(MockitoExtension.class)
@DisplayName("ItemfacilitymappingController Test Suite")
class ItemfacilitymappingControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private M_itemfacilitymappingInter m_itemfacilitymappingInter;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked item-facility mapping service")
	void setUp() {
		ItemfacilitymappingController controller = new ItemfacilitymappingController();
		ReflectionTestUtils.setField(controller, "M_itemfacilitymappingInter", m_itemfacilitymappingInter);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static M_itemfacilitymapping mapping(Integer id) {
		M_itemfacilitymapping mapping = new M_itemfacilitymapping();
		mapping.setItemStoreMapID(id);
		mapping.setFacilityID(7);
		mapping.setItemID(11);
		return mapping;
	}

	@Test
	@DisplayName("mapItemtoStrore should fan the posted item id array out into one mapping row per item")
	void mapItemtoStrore_shouldFanItemArrayIntoRows() throws Exception {
		when(m_itemfacilitymappingInter.mapItemtoStore(anyList()))
				.thenReturn(new ArrayList<>(List.of(mapping(1), mapping(2))));

		mockMvc.perform(post("/mapItemtoStrore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"facilityID\":7,\"mappingType\":\"Main\",\"providerServiceMapID\":3,"
						+ "\"status\":\"Active\",\"createdBy\":\"tester\",\"itemID1\":[11,12,13]}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<List<M_itemfacilitymapping>> captor = ArgumentCaptor.forClass(List.class);
		verify(m_itemfacilitymappingInter).mapItemtoStore(captor.capture());
		List<M_itemfacilitymapping> sent = captor.getValue();
		assertEquals(3, sent.size());
		assertEquals(11, sent.get(0).getItemID());
		assertEquals(13, sent.get(2).getItemID());
		assertEquals(7, sent.get(0).getFacilityID());
		assertEquals("Main", sent.get(0).getMappingType());
		assertEquals("tester", sent.get(0).getCreatedBy());
	}

	@Test
	@DisplayName("mapItemtoStrore should report the failure when the posted payload carries no item id array")
	void mapItemtoStrore_shouldReportFailureWhenItemArrayMissing() throws Exception {
		mockMvc.perform(post("/mapItemtoStrore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{\"facilityID\":7}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("editItemtoStrore should copy the editable fields onto the stored mapping before saving")
	void editItemtoStrore_shouldCopyEditableFields() throws Exception {
		M_itemfacilitymapping stored = mapping(5);
		when(m_itemfacilitymappingInter.editdata(5)).thenReturn(stored);
		when(m_itemfacilitymappingInter.saveEditedItem(any(M_itemfacilitymapping.class)))
				.thenAnswer(inv -> inv.getArgument(0));

		mockMvc.perform(post("/editItemtoStrore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"itemFacilityMapID\":5,\"facilityID\":9,\"itemID\":22,\"mappingType\":\"Sub\","
						+ "\"providerServiceMapID\":3,\"status\":\"Inactive\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_itemfacilitymapping> captor = ArgumentCaptor.forClass(M_itemfacilitymapping.class);
		verify(m_itemfacilitymappingInter).saveEditedItem(captor.capture());
		assertEquals(9, captor.getValue().getFacilityID());
		assertEquals(22, captor.getValue().getItemID());
		assertEquals("Sub", captor.getValue().getMappingType());
		assertEquals("Inactive", captor.getValue().getStatus());
	}

	@Test
	@DisplayName("editItemtoStrore should report the failure when the mapping cannot be found")
	void editItemtoStrore_shouldReportFailureWhenMappingMissing() throws Exception {
		when(m_itemfacilitymappingInter.editdata(5)).thenReturn(null);

		mockMvc.perform(post("/editItemtoStrore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemFacilityMapID\":5}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("deleteItemtoStrore should flip the deleted flag on the stored mapping and save it")
	void deleteItemtoStrore_shouldFlipDeletedFlag() throws Exception {
		M_itemfacilitymapping stored = mapping(5);
		when(m_itemfacilitymappingInter.editdata(5)).thenReturn(stored);
		when(m_itemfacilitymappingInter.saveEditedItem(any(M_itemfacilitymapping.class)))
				.thenAnswer(inv -> inv.getArgument(0));

		mockMvc.perform(post("/deleteItemtoStrore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"itemFacilityMapID\":5,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_itemfacilitymapping> captor = ArgumentCaptor.forClass(M_itemfacilitymapping.class);
		verify(m_itemfacilitymappingInter).saveEditedItem(captor.capture());
		assertEquals(Boolean.TRUE, captor.getValue().getDeleted());
	}

	@Test
	@DisplayName("deleteItemtoStrore should report the failure when the mapping cannot be found")
	void deleteItemtoStrore_shouldReportFailureWhenMappingMissing() throws Exception {
		when(m_itemfacilitymappingInter.editdata(5)).thenReturn(null);

		mockMvc.perform(post("/deleteItemtoStrore").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemFacilityMapID\":5}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("getSubStroreitem should look the sub-store items up by provider service map and facility")
	void getSubStroreitem_shouldLookUpByProviderServiceMapAndFacility() throws Exception {
		when(m_itemfacilitymappingInter.getsubitemforsubStote(3, 7))
				.thenReturn(new ArrayList<>(List.of(mapping(1))));

		mockMvc.perform(post("/getSubStoreitem").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(m_itemfacilitymappingInter).getsubitemforsubStote(3, 7);
	}

	@Test
	@DisplayName("getSubStroreitem should report the failure when the lookup throws")
	void getSubStroreitem_shouldReportServiceFailure() throws Exception {
		when(m_itemfacilitymappingInter.getsubitemforsubStote(anyInt(), anyInt()))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getSubStoreitem").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getAllFacilityMappedData should look the mapped rows up by provider service map")
	void getAllFacilityMappedData_shouldLookUpByProviderServiceMap() throws Exception {
		when(m_itemfacilitymappingInter.getAllFacilityMappedData(3))
				.thenReturn(new ArrayList<>(List.of(new V_fetchItemFacilityMap())));

		mockMvc.perform(post("/getAllFacilityMappedData").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(m_itemfacilitymappingInter).getAllFacilityMappedData(3);
	}

	@Test
	@DisplayName("getAllFacilityMappedData should report the failure when the lookup throws")
	void getAllFacilityMappedData_shouldReportServiceFailure() throws Exception {
		when(m_itemfacilitymappingInter.getAllFacilityMappedData(anyInt()))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getAllFacilityMappedData").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItemFromStoreID should return the items the store holds")
	void getItemFromStoreID_shouldReturnItemsOfStore() throws Exception {
		when(m_itemfacilitymappingInter.getItemMastersFromStoreID(7))
				.thenReturn(List.of(new ItemInStore(7, 11, "Paracetamol", 40L)));

		mockMvc.perform(post("/getItemFromStoreID/7").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(m_itemfacilitymappingInter).getItemMastersFromStoreID(7);
	}

	@Test
	@DisplayName("getItemFromStoreID should report the failure when the lookup throws")
	void getItemFromStoreID_shouldReportServiceFailure() throws Exception {
		when(m_itemfacilitymappingInter.getItemMastersFromStoreID(anyInt()))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getItemFromStoreID/7").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("itemPartialSearch should pass the item name and facility from the posted probe item")
	void itemPartialSearch_shouldPassNameAndFacility() throws Exception {
		when(m_itemfacilitymappingInter.getItemMastersPartialSearch("Para", 7))
				.thenReturn(List.of(new ItemMaster()));

		mockMvc.perform(post("/itemPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(m_itemfacilitymappingInter).getItemMastersPartialSearch("Para", 7);
	}

	@Test
	@DisplayName("itemPartialSearch should report the failure when the search throws")
	void itemPartialSearch_shouldReportServiceFailure() throws Exception {
		when(m_itemfacilitymappingInter.getItemMastersPartialSearch(anyString(), anyInt()))
				.thenThrow(new RuntimeException("search failed"));

		mockMvc.perform(post("/itemPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItemBatchForStoreTransfer should pass the two facilities and the item name straight through")
	void getItemBatchForStoreTransfer_shouldPassTransferDetails() throws Exception {
		when(m_itemfacilitymappingInter.getItemBatchForStoreTransfer(1, 2, "Para"))
				.thenReturn(List.of(new ItemStockEntry()));

		mockMvc.perform(post("/getItemBatchForStoreTransfer").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"transferFromFacilityID\":1,\"transferToFacilityID\":2,\"itemName\":\"Para\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(m_itemfacilitymappingInter).getItemBatchForStoreTransfer(1, 2, "Para");
	}

	@Test
	@DisplayName("getItemBatchForStoreTransfer should report the failure when the lookup throws")
	void getItemBatchForStoreTransfer_shouldReportServiceFailure() throws Exception {
		when(m_itemfacilitymappingInter.getItemBatchForStoreTransfer(anyInt(), anyInt(), anyString()))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getItemBatchForStoreTransfer").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"transferFromFacilityID\":1,\"transferToFacilityID\":2,\"itemName\":\"Para\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}
}
