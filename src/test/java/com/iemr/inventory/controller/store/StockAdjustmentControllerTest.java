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
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.iemr.inventory.data.stockadjustment.StockAdjustment;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.service.stockadjustment.StockAdjustmentServiceImpl;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
@DisplayName("StockAdjustmentController Test Suite")
class StockAdjustmentControllerTest {

	private static final String AUTH = "test-session-key";
	private static final String WINDOW =
			"{\"facilityID\":7,\"fromDate\":1735706400000,\"toDate\":1738298400000}";

	@Mock
	private StockAdjustmentServiceImpl stockAdjustmentServiceImpl;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked stock adjustment service")
	void setUp() {
		StockAdjustmentController controller = new StockAdjustmentController();
		ReflectionTestUtils.setField(controller, "stockAdjustmentServiceImpl", stockAdjustmentServiceImpl);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static StockAdjustmentDraft draft(Long id) {
		StockAdjustmentDraft draft = new StockAdjustmentDraft();
		draft.setStockAdjustmentDraftID(id);
		draft.setFacilityID(7);
		return draft;
	}

	private static StockAdjustment adjustment(Long id) {
		StockAdjustment adjustment = new StockAdjustment();
		adjustment.setStockAdjustmentID(id);
		adjustment.setFacilityID(7);
		return adjustment;
	}

	@Test
	@DisplayName("stockadjustmentdraft should save the posted draft and answer with the stored row")
	void stockadjustmentdraft_shouldSavePostedDraft() throws Exception {
		when(stockAdjustmentServiceImpl.saveDraft(any(StockAdjustmentDraft.class))).thenReturn(draft(55L));

		mockMvc.perform(post("/stockadjustmentdraft").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"draftName\":\"a name\",\"stockAdjustmentItemDraft\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<StockAdjustmentDraft> captor = ArgumentCaptor.forClass(StockAdjustmentDraft.class);
		verify(stockAdjustmentServiceImpl).saveDraft(captor.capture());
		assertEquals("a name", captor.getValue().getDraftName());
	}

	@Test
	@DisplayName("stockadjustmentdraft should report the failure when the save throws")
	void stockadjustmentdraft_shouldReportServiceFailure() throws Exception {
		when(stockAdjustmentServiceImpl.saveDraft(any(StockAdjustmentDraft.class)))
				.thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/stockadjustmentdraft").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getstockadjustmentdraftTransaction should return the drafts in the posted window")
	void getstockadjustmentdraftTransaction_shouldReturnDraftsInWindow() throws Exception {
		when(stockAdjustmentServiceImpl.getStockAjustmentDraftTransaction(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(draft(55L)));

		mockMvc.perform(post("/getstockadjustmentdraftTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<ItemStockEntryinput> captor = ArgumentCaptor.forClass(ItemStockEntryinput.class);
		verify(stockAdjustmentServiceImpl).getStockAjustmentDraftTransaction(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getstockadjustmentdraftTransaction should report the failure when the lookup throws")
	void getstockadjustmentdraftTransaction_shouldReportServiceFailure() throws Exception {
		when(stockAdjustmentServiceImpl.getStockAjustmentDraftTransaction(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getstockadjustmentdraftTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getforEditsSockAdjustmentdraftTransaction should load the draft named by the posted id")
	void getforEditsSockAdjustmentdraftTransaction_shouldLoadDraftById() throws Exception {
		when(stockAdjustmentServiceImpl.getforeditStockAjustmentDraftTransaction(55L)).thenReturn(draft(55L));

		mockMvc.perform(post("/getforEditsStockAdjustmentdraftTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"stockAdjustmentDraftID\":55}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(stockAdjustmentServiceImpl).getforeditStockAjustmentDraftTransaction(55L);
	}

	@Test
	@DisplayName("getforEditsSockAdjustmentdraftTransaction should report the failure when the draft is missing")
	void getforEditsSockAdjustmentdraftTransaction_shouldReportFailureWhenDraftMissing() throws Exception {
		when(stockAdjustmentServiceImpl.getforeditStockAjustmentDraftTransaction(anyLong())).thenReturn(null);

		mockMvc.perform(post("/getforEditsStockAdjustmentdraftTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"stockAdjustmentDraftID\":55}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("stockadjustment should post the adjustment and answer with the stored row")
	void stockadjustment_shouldPostAdjustment() throws Exception {
		when(stockAdjustmentServiceImpl.savetransaction(any(StockAdjustment.class))).thenReturn(adjustment(88L));

		mockMvc.perform(post("/stockadjustment").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"stockAdjustmentItem\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("stockadjustment should surface the inventory failure raised by the service")
	void stockadjustment_shouldSurfaceInventoryFailure() throws Exception {
		when(stockAdjustmentServiceImpl.savetransaction(any(StockAdjustment.class)))
				.thenThrow(new InventoryException("Adjustment Quantity for issue should be more than available quantity"));

		mockMvc.perform(post("/stockadjustment").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5010));
	}

	@Test
	@DisplayName("getforeditStockAdjustmentTransaction should return the adjustments in the posted window")
	void getforeditStockAdjustmentTransaction_shouldReturnAdjustmentsInWindow() throws Exception {
		when(stockAdjustmentServiceImpl.getStockAjustmentTransaction(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(adjustment(88L)));

		mockMvc.perform(post("/getStockAdjustmentTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getforeditStockAdjustmentTransaction should report the failure when the lookup throws")
	void getforeditStockAdjustmentTransaction_shouldReportServiceFailure() throws Exception {
		when(stockAdjustmentServiceImpl.getStockAjustmentTransaction(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getStockAdjustmentTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getforEditsStockAdjustmentTransaction should load the adjustment named by the posted id")
	void getforEditsStockAdjustmentTransaction_shouldLoadAdjustmentById() throws Exception {
		when(stockAdjustmentServiceImpl.getforeditStockAjustmentTransaction(88L)).thenReturn(adjustment(88L));

		mockMvc.perform(post("/getforEditsStockAdjustmentTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"stockAdjustmentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(stockAdjustmentServiceImpl).getforeditStockAjustmentTransaction(88L);
	}

	@Test
	@DisplayName("getforEditsStockAdjustmentTransaction should report the failure when the adjustment is missing")
	void getforEditsStockAdjustmentTransaction_shouldReportFailureWhenAdjustmentMissing() throws Exception {
		when(stockAdjustmentServiceImpl.getforeditStockAjustmentTransaction(anyLong())).thenReturn(null);

		mockMvc.perform(post("/getforEditsStockAdjustmentTransaction").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"stockAdjustmentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}
}
