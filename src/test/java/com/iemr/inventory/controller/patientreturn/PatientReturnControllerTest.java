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
package com.iemr.inventory.controller.patientreturn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
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

import com.iemr.inventory.data.patientreturn.ItemDetailModel;
import com.iemr.inventory.data.patientreturn.PatientReturnModel;
import com.iemr.inventory.data.patientreturn.ReturnHistoryModel;
import com.iemr.inventory.data.stockExit.ItemReturnEntry;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.service.patientreturn.PatientReturnService;

@ExtendWith(MockitoExtension.class)
@DisplayName("PatientReturnController Test Suite")
class PatientReturnControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private PatientReturnService patientReturnService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked patient return service")
	void setUp() {
		PatientReturnController controller = new PatientReturnController();
		ReflectionTestUtils.setField(controller, "patientReturnService", patientReturnService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@DisplayName("getItemNameByRegID should forward the beneficiary and facility from the posted payload")
	void getItemNameByRegID_shouldForwardBeneficiaryAndFacility() throws Exception {
		when(patientReturnService.getItemNameByRegID(any(T_PatientIssue.class)))
				.thenReturn(List.of(new PatientReturnModel(101L, 7, 11, "Paracetamol")));

		mockMvc.perform(post("/patientReturnController/getItemNameByRegID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"benRegID\":101,\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].itemName").value("Paracetamol"));

		ArgumentCaptor<T_PatientIssue> captor = ArgumentCaptor.forClass(T_PatientIssue.class);
		verify(patientReturnService).getItemNameByRegID(captor.capture());
		assertEquals(101L, captor.getValue().getBenRegID());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getItemNameByRegID should report the failure when the lookup throws")
	void getItemNameByRegID_shouldReportServiceFailure() throws Exception {
		when(patientReturnService.getItemNameByRegID(any(T_PatientIssue.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/patientReturnController/getItemNameByRegID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"benRegID\":101,\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getItemDetailByBen should forward the beneficiary, item and facility from the posted payload")
	void getItemDetailByBen_shouldForwardProbe() throws Exception {
		when(patientReturnService.getItemDetailByBen(any(ItemDetailModel.class)))
				.thenReturn(List.of(new ItemDetailModel(11, "Paracetamol", "B-1", 20,
						Timestamp.valueOf("2025-01-31 10:15:30"), false, false, 501L, 601L, 701L, 801L, 101L, 3, 7)));

		mockMvc.perform(post("/patientReturnController/getItemDetailByBen").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"benRegID\":101,\"itemID\":11,\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].batchNo").value("B-1"));

		ArgumentCaptor<ItemDetailModel> captor = ArgumentCaptor.forClass(ItemDetailModel.class);
		verify(patientReturnService).getItemDetailByBen(captor.capture());
		assertEquals(11, captor.getValue().getItemID());
	}

	@Test
	@DisplayName("getItemDetailByBen should report the failure when the lookup throws")
	void getItemDetailByBen_shouldReportServiceFailure() throws Exception {
		when(patientReturnService.getItemDetailByBen(any(ItemDetailModel.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/patientReturnController/getItemDetailByBen").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"benRegID\":101}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("updateQuantityReturned should pass the posted array of returned lines to the service")
	void updateQuantityReturned_shouldPassReturnedLines() throws Exception {
		when(patientReturnService.updateQuantityReturned(any(ItemDetailModel[].class)))
				.thenReturn("Quantity updated successfully");

		mockMvc.perform(post("/patientReturnController/updateQuantityReturned").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"itemStockEntryID\":601,\"itemStockExitID\":501,\"returnQuantity\":4}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.response").value("Quantity updated successfully"));

		ArgumentCaptor<ItemDetailModel[]> captor = ArgumentCaptor.forClass(ItemDetailModel[].class);
		verify(patientReturnService).updateQuantityReturned(captor.capture());
		assertEquals(1, captor.getValue().length);
		assertEquals(4, captor.getValue()[0].getReturnQuantity());
	}

	@Test
	@DisplayName("updateQuantityReturned should report the failure when the update throws")
	void updateQuantityReturned_shouldReportServiceFailure() throws Exception {
		when(patientReturnService.updateQuantityReturned(any(ItemDetailModel[].class)))
				.thenThrow(new RuntimeException("update failed"));

		mockMvc.perform(post("/patientReturnController/updateQuantityReturned").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getBenReturnHistory should forward the facility and the date window from the posted payload")
	void getBenReturnHistory_shouldForwardWindow() throws Exception {
		when(patientReturnService.getBenReturnHistory(any(ItemReturnEntry.class)))
				.thenReturn(List.of(new ReturnHistoryModel("Paracetamol", "B-1", 20,
						Timestamp.valueOf("2025-01-31 10:15:30"), 701L, 801L, "Jane Doe", 34, "Female",
						Timestamp.valueOf("2025-02-01 09:00:00"))));

		mockMvc.perform(post("/patientReturnController/getBenReturnHistory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].patientName").value("Jane Doe"));

		ArgumentCaptor<ItemReturnEntry> captor = ArgumentCaptor.forClass(ItemReturnEntry.class);
		verify(patientReturnService).getBenReturnHistory(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getBenReturnHistory should report the failure when the lookup throws")
	void getBenReturnHistory_shouldReportServiceFailure() throws Exception {
		when(patientReturnService.getBenReturnHistory(any(ItemReturnEntry.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/patientReturnController/getBenReturnHistory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":7}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}
}
