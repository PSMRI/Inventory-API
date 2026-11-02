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
package com.iemr.inventory.controller.visit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.service.visit.VisitService;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
@DisplayName("VisitController Test Suite")
class VisitControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private VisitService visitService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked visit service")
	void setUp() {
		VisitController controller = new VisitController();
		ReflectionTestUtils.setField(controller, "visitService", visitService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static BeneficiaryModel beneficiary(Long regID) {
		BeneficiaryModel model = new BeneficiaryModel();
		model.setBeneficiaryRegID(regID);
		return model;
	}

	@Test
	@DisplayName("getVisitFromBenRegID should forward the beneficiary id, provider service map and auth header")
	void getVisitFromBenRegID_shouldForwardRequestDetails() throws Exception {
		when(visitService.getVisitDetail(eq("12345"), eq(3), eq(AUTH))).thenReturn(beneficiary(77L));

		mockMvc.perform(post("/getVisitFromBenID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"beneficiaryID\":\"12345\",\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(visitService).getVisitDetail("12345", 3, AUTH);
	}

	@Test
	@DisplayName("getVisitFromBenRegID should report the failure when the beneficiary id is blank")
	void getVisitFromBenRegID_shouldReportFailureForBlankBeneficiaryId() throws Exception {
		when(visitService.getVisitDetail(eq("   "), anyInt(), anyString())).thenReturn(beneficiary(77L));

		mockMvc.perform(post("/getVisitFromBenID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"beneficiaryID\":\"   \",\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("Beneficiary ID cannot be null or empty"));
	}

	@Test
	@DisplayName("getVisitFromBenRegID should report the inventory failure raised by the service")
	void getVisitFromBenRegID_shouldReportInventoryFailure() throws Exception {
		when(visitService.getVisitDetail(anyString(), anyInt(), anyString()))
				.thenThrow(new InventoryException("Invalid Beneficiary ID"));

		mockMvc.perform(post("/getVisitFromBenID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"beneficiaryID\":\"12345\",\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5010))
				.andExpect(jsonPath("$.errorMessage").value("Invalid Beneficiary ID"));
	}

	@Test
	@DisplayName("getVisitFromAdvanceSearch should forward the raw search payload and the auth header")
	void getVisitFromAdvanceSearch_shouldForwardSearchPayload() throws Exception {
		when(visitService.getVisitFromAdvanceSearch(anyString(), eq(AUTH)))
				.thenReturn(List.of(beneficiary(77L)));

		mockMvc.perform(post("/getVisitFromAdvanceSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("12345"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(visitService).getVisitFromAdvanceSearch("12345", AUTH);
	}

	@Test
	@DisplayName("getVisitFromAdvanceSearch should report the failure when the search throws")
	void getVisitFromAdvanceSearch_shouldReportServiceFailure() throws Exception {
		when(visitService.getVisitFromAdvanceSearch(anyString(), any()))
				.thenThrow(new RuntimeException("search unavailable"));

		mockMvc.perform(post("/getVisitFromAdvanceSearch").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("12345"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("search unavailable"));
	}
}
