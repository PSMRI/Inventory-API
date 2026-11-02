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
package com.iemr.inventory.controller.stockEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.service.stockEntry.StockEntryServiceImpl;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
@DisplayName("StockEntryController Test Suite")
class StockEntryControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private StockEntryServiceImpl stockEntryService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked stock entry service")
	void setUp() {
		StockEntryController controller = new StockEntryController();
		ReflectionTestUtils.setField(controller, "stockEntryService", stockEntryService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static PhysicalStockEntry physical() {
		PhysicalStockEntry physical = new PhysicalStockEntry();
		physical.setPhyEntryID(77L);
		physical.setFacilityID(7);
		return physical;
	}

	private static ItemStockEntry batch() {
		ItemStockEntry entry = new ItemStockEntry();
		entry.setItemStockEntryID(601);
		entry.setBatchNo("B-1");
		return entry;
	}

	@Test
	@DisplayName("physicalStockEntry should book the posted stock in and answer with the saved header")
	void physicalStockEntry_shouldBookStockIn() throws Exception {
		when(stockEntryService.savePhysicalStockEntry(any(PhysicalStockEntry.class))).thenReturn(physical());

		mockMvc.perform(post("/physicalStockEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"itemStockEntry\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<PhysicalStockEntry> captor = ArgumentCaptor.forClass(PhysicalStockEntry.class);
		verify(stockEntryService).savePhysicalStockEntry(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("physicalStockEntry should surface the duplicate-batch refusal from the service")
	void physicalStockEntry_shouldSurfaceDuplicateRefusal() throws Exception {
		when(stockEntryService.savePhysicalStockEntry(any(PhysicalStockEntry.class)))
				.thenThrow(new InventoryException("Duplicate stock entry: Item ID 11 with batch 'B-1' already exists"));

		mockMvc.perform(post("/physicalStockEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5010));
	}

	@Test
	@DisplayName("getItemBatchForStoreID should pass the posted probe batch to the service")
	void getItemBatchForStoreID_shouldPassProbeThrough() throws Exception {
		when(stockEntryService.getItemBatchForStoreID(any(ItemStockEntry.class))).thenReturn(List.of(batch()));

		mockMvc.perform(post("/getItemBatchForStoreID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7,\"itemID\":11}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<ItemStockEntry> captor = ArgumentCaptor.forClass(ItemStockEntry.class);
		verify(stockEntryService).getItemBatchForStoreID(captor.capture());
		assertEquals(11, captor.getValue().getItemID());
	}

	@Test
	@DisplayName("getItemBatchForStoreID should report the failure when the lookup throws")
	void getItemBatchForStoreID_shouldReportServiceFailure() throws Exception {
		when(stockEntryService.getItemBatchForStoreID(any(ItemStockEntry.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getItemBatchForStoreID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("allocateStockFromItemID should allocate for the facility named in the path")
	void allocateStockFromItemID_shouldAllocateForPathFacility() throws Exception {
		when(stockEntryService.getItemStockFromItemID(anyInt(), anyList()))
				.thenReturn(List.of(new AllocateItemMap()));

		mockMvc.perform(post("/allocateStockFromItemID/7").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{\"itemID\":11,\"quantity\":6}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<List<ItemStockExit>> captor = ArgumentCaptor.forClass(List.class);
		verify(stockEntryService).getItemStockFromItemID(anyInt(), captor.capture());
		assertEquals(11, captor.getValue().get(0).getItemID());
	}

	@Test
	@DisplayName("allocateStockFromItemID should report the failure when the allocation throws")
	void allocateStockFromItemID_shouldReportServiceFailure() throws Exception {
		when(stockEntryService.getItemStockFromItemID(anyInt(), anyList()))
				.thenThrow(new InventoryException("no stock"));

		mockMvc.perform(post("/allocateStockFromItemID/7").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5010));
	}

	@Test
	@DisplayName("getPhysicalStockEntry should return the entries in the posted window")
	void getPhysicalStockEntry_shouldReturnEntriesInWindow() throws Exception {
		when(stockEntryService.getPhysicalStockEntry(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(physical()));

		mockMvc.perform(post("/getPhysicalStockEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"fromDate\":1735706400000,\"toDate\":1738298400000}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getPhysicalStockEntry should report the failure when the lookup throws")
	void getPhysicalStockEntry_shouldReportServiceFailure() throws Exception {
		when(stockEntryService.getPhysicalStockEntry(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getPhysicalStockEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("itemPartialSearch should pass the item name and facility from the posted probe item")
	void itemPartialSearch_shouldPassNameAndFacility() throws Exception {
		when(stockEntryService.getItemMastersPartialSearch("Para", 7)).thenReturn(List.of(batch()));

		mockMvc.perform(post("/itemBatchPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(stockEntryService).getItemMastersPartialSearch("Para", 7);
	}

	@Test
	@DisplayName("itemPartialSearch should report the failure when the search throws")
	void itemPartialSearch_shouldReportServiceFailure() throws Exception {
		when(stockEntryService.getItemMastersPartialSearch(anyString(), anyInt()))
				.thenThrow(new RuntimeException("search failed"));

		mockMvc.perform(post("/itemBatchPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("itemBatchWithZeroPartialSearch should include the batches that hold no stock left")
	void itemBatchWithZeroPartialSearch_shouldIncludeEmptyBatches() throws Exception {
		when(stockEntryService.getItemMastersPartialSearchWithZero("Para", 7)).thenReturn(List.of(batch()));

		mockMvc.perform(post("/itemBatchWithZeroPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(stockEntryService).getItemMastersPartialSearchWithZero("Para", 7);
	}

	@Test
	@DisplayName("itemBatchWithZeroPartialSearch should report the failure when the search throws")
	void itemBatchWithZeroPartialSearch_shouldReportServiceFailure() throws Exception {
		when(stockEntryService.getItemMastersPartialSearchWithZero(anyString(), anyInt()))
				.thenThrow(new RuntimeException("search failed"));

		mockMvc.perform(post("/itemBatchWithZeroPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getPhysicalStockEntryItems should load the batches booked under the posted header id")
	void getPhysicalStockEntryItems_shouldLoadBatchesOfHeader() throws Exception {
		when(stockEntryService.getPhysicalStockEntryItems(77L)).thenReturn(List.of(batch()));

		mockMvc.perform(post("/getPhysicalStockEntryItems").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"phyEntryID\":77}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(stockEntryService).getPhysicalStockEntryItems(77L);
	}

	@Test
	@DisplayName("getPhysicalStockEntryItems should report the failure when the header is missing")
	void getPhysicalStockEntryItems_shouldReportFailureWhenHeaderMissing() throws Exception {
		when(stockEntryService.getPhysicalStockEntryItems(anyLong()))
				.thenThrow(new java.util.NoSuchElementException("No value present"));

		mockMvc.perform(post("/getPhysicalStockEntryItems").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"phyEntryID\":77}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItemwithQuantityPartialSearch should pass the item name and facility from the posted probe")
	void getItemwithQuantityPartialSearch_shouldPassNameAndFacility() throws Exception {
		when(stockEntryService.getItemwithQuantityPartialSearch("Para", 7))
				.thenReturn(List.of(new ItemMasterWithQuantityMap()));

		mockMvc.perform(post("/getItemwithQuantityPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(stockEntryService).getItemwithQuantityPartialSearch("Para", 7);
	}

	@Test
	@DisplayName("getItemwithQuantityPartialSearch should report the failure when the search throws")
	void getItemwithQuantityPartialSearch_shouldReportServiceFailure() throws Exception {
		when(stockEntryService.getItemwithQuantityPartialSearch(anyString(), anyInt()))
				.thenThrow(new RuntimeException("search failed"));

		mockMvc.perform(post("/getItemwithQuantityPartialSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"Para\",\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}
}
