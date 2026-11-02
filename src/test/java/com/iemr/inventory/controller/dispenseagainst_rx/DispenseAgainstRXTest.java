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
package com.iemr.inventory.controller.dispenseagainst_rx;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.iemr.inventory.service.dispenseagainst_rx.DispenseAgainstRXimpl;

@ExtendWith(MockitoExtension.class)
@DisplayName("DispenseAgainstRX controller Test Suite")
class DispenseAgainstRXTest {

	private static final String AUTH = "test-session-key";
	private static final String REQUEST = "{\"beneficiaryRegID\":101,\"visitCode\":5001,\"facilityID\":7}";

	@Mock
	private DispenseAgainstRXimpl dispenseAgainstRXimpl;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked dispensing service")
	void setUp() {
		DispenseAgainstRX controller = new DispenseAgainstRX();
		ReflectionTestUtils.setField(controller, "dispenseAgainstRXimpl", dispenseAgainstRXimpl);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@DisplayName("getPrescribedMedicines should hand the service payload back to the caller")
	void getPrescribedMedicines_shouldReturnServicePayload() throws Exception {
		when(dispenseAgainstRXimpl.getPrescribedMedicines(anyString()))
				.thenReturn("{\"prescriptionID\":9001}");

		mockMvc.perform(post("/RX/getPrescribedMedicines").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(REQUEST))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.prescriptionID").value(9001));

		verify(dispenseAgainstRXimpl).getPrescribedMedicines(REQUEST);
	}

	@Test
	@DisplayName("getPrescribedMedicines should leave the generic failure standing when the service returns nothing")
	void getPrescribedMedicines_shouldLeaveGenericFailureWhenServiceReturnsNothing() throws Exception {
		when(dispenseAgainstRXimpl.getPrescribedMedicines(anyString())).thenReturn(null);

		mockMvc.perform(post("/RX/getPrescribedMedicines").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(REQUEST))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.status").value("FAILURE"));
	}

	@Test
	@DisplayName("getPrescribedMedicines should report the failure when the service throws")
	void getPrescribedMedicines_shouldReportServiceFailure() throws Exception {
		when(dispenseAgainstRXimpl.getPrescribedMedicines(anyString()))
				.thenThrow(new RuntimeException("prescription lookup failed"));

		mockMvc.perform(post("/RX/getPrescribedMedicines").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(REQUEST))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("prescription lookup failed"));
	}
}
