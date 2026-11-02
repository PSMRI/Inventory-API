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
package com.iemr.inventory.controller.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_ItemForm;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.service.item.ItemService;

@ExtendWith(MockitoExtension.class)
@DisplayName("ItemController Test Suite")
class ItemControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private ItemService itemService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked item service")
	void setUp() {
		ItemController controller = new ItemController();
		ReflectionTestUtils.setField(controller, "itemService", itemService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static ItemMaster item(Integer id, String name) {
		ItemMaster item = new ItemMaster();
		item.setItemID(id);
		item.setItemName(name);
		item.setProviderServiceMapID(3);
		return item;
	}

	@Test
	@DisplayName("getItemForm should return the forms configured for the provider service map")
	void getItemForm_shouldReturnFormsForProviderServiceMap() throws Exception {
		when(itemService.getItemFormProviderServiceMapID(3)).thenReturn(List.of(new M_ItemForm()));

		mockMvc.perform(get("/getItemForm/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.length()").value(1));
	}

	@Test
	@DisplayName("getItemForm should report the failure when the lookup throws")
	void getItemForm_shouldReportServiceFailure() throws Exception {
		when(itemService.getItemFormProviderServiceMapID(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(get("/getItemForm/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItemRoute should return the routes configured for the provider service map")
	void getItemRoute_shouldReturnRoutesForProviderServiceMap() throws Exception {
		when(itemService.getItemRouteProviderServiceMapID(3)).thenReturn(List.of(new M_Route()));

		mockMvc.perform(get("/getItemRoute/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getItemRoute should report the failure when the lookup throws")
	void getItemRoute_shouldReportServiceFailure() throws Exception {
		when(itemService.getItemRouteProviderServiceMapID(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(get("/getItemRoute/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItemCategory should ask for every category when the flag path segment is zero")
	void getItemCategory_shouldAskForEveryCategoryWhenFlagIsZero() throws Exception {
		when(itemService.getItemCategory(true, 3)).thenReturn(List.of(new M_ItemCategory()));

		mockMvc.perform(get("/getItemCategory/3/0").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(itemService).getItemCategory(true, 3);
	}

	@Test
	@DisplayName("getItemCategory should ask for the live categories only when the flag path segment is not zero")
	void getItemCategory_shouldAskForLiveCategoriesWhenFlagIsNotZero() throws Exception {
		when(itemService.getItemCategory(false, 3)).thenReturn(List.of(new M_ItemCategory()));

		mockMvc.perform(get("/getItemCategory/3/1").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(itemService).getItemCategory(false, 3);
	}

	@Test
	@DisplayName("getItemCategory should report the failure when the lookup throws")
	void getItemCategory_shouldReportServiceFailure() throws Exception {
		when(itemService.getItemCategory(true, 3)).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(get("/getItemCategory/3/0").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("createItemMaster should save the posted batch and answer with the stored rows")
	void createItemMaster_shouldSavePostedBatch() throws Exception {
		when(itemService.addAllItemMaster(anyList())).thenReturn(List.of(item(1, "Paracetamol")));

		mockMvc.perform(post("/createItemMaster").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"itemName\":\"Paracetamol\",\"providerServiceMapID\":3}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].itemName").value("Paracetamol"));

		ArgumentCaptor<List<ItemMaster>> captor = ArgumentCaptor.forClass(List.class);
		verify(itemService).addAllItemMaster(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	@DisplayName("createItemMaster should report the failure when the save throws")
	void createItemMaster_shouldReportServiceFailure() throws Exception {
		when(itemService.addAllItemMaster(anyList())).thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/createItemMaster").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItemMaster should return the items of the provider service map")
	void getItemMaster_shouldReturnItemsOfProviderServiceMap() throws Exception {
		when(itemService.getItemMaster(3)).thenReturn(List.of(item(1, "Paracetamol")));

		mockMvc.perform(get("/getItemMaster/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].itemName").value("Paracetamol"));
	}

	@Test
	@DisplayName("getItemMaster should report the failure when the lookup throws")
	void getItemMaster_shouldReportServiceFailure() throws Exception {
		when(itemService.getItemMaster(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(get("/getItemMaster/3").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getActiveItemMaster should pass the posted probe item to the service")
	void getActiveItemMaster_shouldPassProbeItemThrough() throws Exception {
		when(itemService.getActiveItemMaster(any(ItemMaster.class))).thenReturn(List.of(item(1, "Paracetamol")));

		mockMvc.perform(post("/getActiveItemMaster").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"deleted\":false,\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<ItemMaster> captor = ArgumentCaptor.forClass(ItemMaster.class);
		verify(itemService).getActiveItemMaster(captor.capture());
		assertEquals(Boolean.FALSE, captor.getValue().getDeleted());
		assertEquals(3, captor.getValue().getProviderServiceMapID());
	}

	@Test
	@DisplayName("getActiveItemMaster should report the failure when the lookup throws")
	void getActiveItemMaster_shouldReportServiceFailure() throws Exception {
		when(itemService.getActiveItemMaster(any(ItemMaster.class))).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getActiveItemMaster").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("blockItemMaster should pass the item id and the delete flag straight through")
	void blockItemMaster_shouldPassIdAndFlagThrough() throws Exception {
		when(itemService.blockItemMaster(9, true)).thenReturn(1);

		mockMvc.perform(get("/blockItemMaster/9/true").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.response").value("1"));

		verify(itemService).blockItemMaster(9, true);
	}

	@Test
	@DisplayName("blockItemMaster should report the failure when the update throws")
	void blockItemMaster_shouldReportServiceFailure() throws Exception {
		when(itemService.blockItemMaster(9, true)).thenThrow(new RuntimeException("update failed"));

		mockMvc.perform(get("/blockItemMaster/9/true").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("discontinueItemMaster should pass the item id and the discontinue flag straight through")
	void discontinueItemMaster_shouldPassIdAndFlagThrough() throws Exception {
		when(itemService.discontinueItemMaster(9, false)).thenReturn(1);

		mockMvc.perform(get("/discontinueItemMaster/9/false").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(itemService).discontinueItemMaster(9, false);
	}

	@Test
	@DisplayName("discontinueItemMaster should report the failure when the update throws")
	void discontinueItemMaster_shouldReportServiceFailure() throws Exception {
		when(itemService.discontinueItemMaster(9, false)).thenThrow(new RuntimeException("update failed"));

		mockMvc.perform(get("/discontinueItemMaster/9/false").header("Authorization", AUTH))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("editItemMaster should copy the description and modifier onto the stored item before saving")
	void editItemMaster_shouldCopyEditableFields() throws Exception {
		ItemMaster stored = item(9, "Paracetamol");
		when(itemService.getItemMasterByID(9)).thenReturn(stored);
		when(itemService.createItemMaster(any(ItemMaster.class))).thenAnswer(inv -> inv.getArgument(0));

		mockMvc.perform(post("/editItemMaster").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"itemID\":9,\"itemDesc\":\"new desc\",\"modifiedBy\":\"tester\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<ItemMaster> captor = ArgumentCaptor.forClass(ItemMaster.class);
		verify(itemService).createItemMaster(captor.capture());
		assertEquals("new desc", captor.getValue().getItemDesc());
		assertEquals("tester", captor.getValue().getModifiedBy());
	}

	@Test
	@DisplayName("editItemMaster should report the failure when the item cannot be found")
	void editItemMaster_shouldReportFailureWhenItemMissing() throws Exception {
		when(itemService.getItemMasterByID(9)).thenReturn(null);

		mockMvc.perform(post("/editItemMaster").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemID\":9}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("configItemIssue should push the posted categories into the issue configuration")
	void configItemIssue_shouldUpdateIssueConfiguration() throws Exception {
		when(itemService.updateItemIssueConfig(anyList())).thenReturn(2);

		mockMvc.perform(post("/configItemIssue").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"itemCategoryID\":1,\"issueType\":\"Bulk\"},{\"itemCategoryID\":2,\"issueType\":\"Single\"}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.response").value("2"));
	}

	@Test
	@DisplayName("configItemIssue should report the failure when the update throws")
	void configItemIssue_shouldReportServiceFailure() throws Exception {
		when(itemService.updateItemIssueConfig(anyList())).thenThrow(new RuntimeException("update failed"));

		mockMvc.perform(post("/configItemIssue").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItem should narrow the items to the posted provider service map and category")
	void getItem_shouldNarrowByProviderServiceMapAndCategory() throws Exception {
		when(itemService.getItemMasters(3, 5)).thenReturn(List.of(item(1, "Paracetamol")));

		mockMvc.perform(post("/getItem").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"itemCategoryID\":5}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(itemService).getItemMasters(3, 5);
	}

	@Test
	@DisplayName("getItem should report the failure when the lookup throws")
	void getItem_shouldReportServiceFailure() throws Exception {
		when(itemService.getItemMasters(anyInt(), anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getItem").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3,\"itemCategoryID\":5}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}
}
