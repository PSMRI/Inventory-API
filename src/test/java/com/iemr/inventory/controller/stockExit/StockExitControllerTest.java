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
package com.iemr.inventory.controller.stockExit;

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

import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockExit.StoreSelfConsumption;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.data.stockExit.T_StockTransfer;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.service.stockExit.StockExitServiceImpl;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
@DisplayName("StockExitController Test Suite")
class StockExitControllerTest {

	private static final String AUTH = "test-session-key";
	private static final String WINDOW =
			"{\"facilityID\":7,\"fromDate\":1735706400000,\"toDate\":1738298400000}";

	@Mock
	private StockExitServiceImpl stockExitService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked stock exit service")
	void setUp() {
		StockExitController controller = new StockExitController();
		ReflectionTestUtils.setField(controller, "stockExitService", stockExitService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@DisplayName("patientIssue should confirm the dispense when the service books it")
	void patientIssue_shouldConfirmDispense() throws Exception {
		when(stockExitService.issuePatientDrugs(any(T_PatientIssue.class))).thenReturn(1);

		mockMvc.perform(post("/patientIssue").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"benRegID\":101,\"itemStockExit\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.response").value("Successfully Created"));

		ArgumentCaptor<T_PatientIssue> captor = ArgumentCaptor.forClass(T_PatientIssue.class);
		verify(stockExitService).issuePatientDrugs(captor.capture());
		assertEquals(101L, captor.getValue().getBenRegID());
	}

	@Test
	@DisplayName("patientIssue should report a failure when the service books nothing")
	void patientIssue_shouldReportFailureWhenNothingBooked() throws Exception {
		when(stockExitService.issuePatientDrugs(any(T_PatientIssue.class))).thenReturn(0);

		mockMvc.perform(post("/patientIssue").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("Error occured while saving the request"));
	}

	@Test
	@DisplayName("patientIssue should surface the inventory failure raised by the service")
	void patientIssue_shouldSurfaceInventoryFailure() throws Exception {
		when(stockExitService.issuePatientDrugs(any(T_PatientIssue.class)))
				.thenThrow(new InventoryException("No item found to dispense."));

		mockMvc.perform(post("/patientIssue").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5010));
	}

	@Test
	@DisplayName("storeSelfConsumption should confirm the consumption when the service books it")
	void storeSelfConsumption_shouldConfirmConsumption() throws Exception {
		when(stockExitService.storeSelfConsumption(any(StoreSelfConsumption.class))).thenReturn(1);

		mockMvc.perform(post("/storeSelfConsumption").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityID\":7,\"itemStockExit\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.response").value("Successfully Created"));
	}

	@Test
	@DisplayName("storeSelfConsumption should report a failure when the service books nothing")
	void storeSelfConsumption_shouldReportFailureWhenNothingBooked() throws Exception {
		when(stockExitService.storeSelfConsumption(any(StoreSelfConsumption.class))).thenReturn(0);

		mockMvc.perform(post("/storeSelfConsumption").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("storeTransfer should confirm the transfer when the service books it")
	void storeTransfer_shouldConfirmTransfer() throws Exception {
		when(stockExitService.storeTransfer(any(T_StockTransfer.class))).thenReturn(1);

		mockMvc.perform(post("/storeTransfer").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"transferFromFacilityID\":1,\"transferToFacilityID\":2,\"itemStockExit\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.response").value("Successfully Created"));
	}

	@Test
	@DisplayName("storeTransfer should report a failure when the service books nothing")
	void storeTransfer_shouldReportFailureWhenNothingBooked() throws Exception {
		when(stockExitService.storeTransfer(any(T_StockTransfer.class))).thenReturn(0);

		mockMvc.perform(post("/storeTransfer").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"transferFromFacilityID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getPatientissue should return the patient issues in the posted window")
	void getPatientissue_shouldReturnIssuesInWindow() throws Exception {
		when(stockExitService.getpatientIssue(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(new T_PatientIssue()));

		mockMvc.perform(post("/getPatientissue").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getPatientissue should report the failure when the lookup throws")
	void getPatientissue_shouldReportServiceFailure() throws Exception {
		when(stockExitService.getpatientIssue(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getPatientissue").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getSelfConsumption should return the consumptions in the posted window")
	void getSelfConsumption_shouldReturnConsumptionsInWindow() throws Exception {
		when(stockExitService.getstoreSelfConsumption(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(new StoreSelfConsumption()));

		mockMvc.perform(post("/getSelfConsumption").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getSelfConsumption should report the failure when the lookup throws")
	void getSelfConsumption_shouldReportServiceFailure() throws Exception {
		when(stockExitService.getstoreSelfConsumption(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getSelfConsumption").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getStoreTransfer should return the transfers in the posted window")
	void getStoreTransfer_shouldReturnTransfersInWindow() throws Exception {
		when(stockExitService.getStoreTransfer(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(new T_StockTransfer()));

		mockMvc.perform(post("/getStoreTransfer").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getStoreTransfer should report the failure when the lookup throws")
	void getStoreTransfer_shouldReportServiceFailure() throws Exception {
		when(stockExitService.getStoreTransfer(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getStoreTransfer").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getPatientissueAllDetail should load the issue named by the posted id")
	void getPatientissueAllDetail_shouldLoadIssueById() throws Exception {
		when(stockExitService.getPatientissueAllDetail(88L)).thenReturn(new T_PatientIssue());

		mockMvc.perform(post("/getPatientissueAllDetail").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"patientIssueID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(stockExitService).getPatientissueAllDetail(88L);
	}

	@Test
	@DisplayName("getPatientissueAllDetail should report the failure when the issue is missing")
	void getPatientissueAllDetail_shouldReportFailureWhenIssueMissing() throws Exception {
		when(stockExitService.getPatientissueAllDetail(anyLong())).thenReturn(null);

		mockMvc.perform(post("/getPatientissueAllDetail").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"patientIssueID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("getPatientissueItemEntry should project the lines booked under the posted issue")
	void getPatientissueItemEntry_shouldProjectBookedLines() throws Exception {
		when(stockExitService.getpatientIssueItemLIst(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(new ItemStockExitMap()));

		mockMvc.perform(post("/getPatientissueItemEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"patientIssueID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getPatientissueItemEntry should report the failure when the lookup throws")
	void getPatientissueItemEntry_shouldReportServiceFailure() throws Exception {
		when(stockExitService.getpatientIssueItemLIst(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getPatientissueItemEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"patientIssueID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getSelfConsumptionItemEntry should project the lines booked under the posted consumption")
	void getSelfConsumptionItemEntry_shouldProjectBookedLines() throws Exception {
		when(stockExitService.getstoreSelfConsumptionItemList(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(new ItemStockExitMap()));

		mockMvc.perform(post("/getSelfConsumptionItemEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"consumptionID\":66}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<ItemStockEntryinput> captor = ArgumentCaptor.forClass(ItemStockEntryinput.class);
		verify(stockExitService).getstoreSelfConsumptionItemList(captor.capture());
		assertEquals(66L, captor.getValue().getConsumptionID());
	}

	@Test
	@DisplayName("getSelfConsumptionItemEntry should report the failure when the lookup throws")
	void getSelfConsumptionItemEntry_shouldReportServiceFailure() throws Exception {
		when(stockExitService.getstoreSelfConsumptionItemList(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getSelfConsumptionItemEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"consumptionID\":66}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getStoreTransferItemEntry should project the batches booked under the posted transfer")
	void getStoreTransferItemEntry_shouldProjectBookedBatches() throws Exception {
		when(stockExitService.getStoreTransferItemEntry(any(ItemStockEntryinput.class)))
				.thenReturn(List.of(new ItemStockExitMap()));

		mockMvc.perform(post("/getStoreTransferItemEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"stockTransferID\":99}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));
	}

	@Test
	@DisplayName("getStoreTransferItemEntry should report the failure when the lookup throws")
	void getStoreTransferItemEntry_shouldReportServiceFailure() throws Exception {
		when(stockExitService.getStoreTransferItemEntry(any(ItemStockEntryinput.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getStoreTransferItemEntry").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"stockTransferID\":99}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}
}
