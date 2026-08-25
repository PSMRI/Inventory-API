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
package com.iemr.inventory.controller.manufacturer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.service.manufacturer.ManufacturerInter;

@ExtendWith(MockitoExtension.class)
@DisplayName("ManufacturerController Test Suite")
class ManufacturerControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private ManufacturerInter manufacturerInter;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked manufacturer service")
	void setUp() {
		ManufacturerController controller = new ManufacturerController();
		ReflectionTestUtils.setField(controller, "manufacturerInter", manufacturerInter);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static M_Manufacturer row(Integer id, String name) {
		M_Manufacturer row = new M_Manufacturer();
		row.setManufacturerID(id);
		row.setManufacturerName(name);
		row.setProviderServiceMapID(3);
		return row;
	}

	private static ArrayList<M_Manufacturer> rows(M_Manufacturer... items) {
		return new ArrayList<>(List.of(items));
	}

	@Test
	@DisplayName("createManufacturer should persist the posted array and answer with the saved rows")
	void createManufacturer_shouldPersistPostedArray() throws Exception {
		when(manufacturerInter.createManufacturer(anyList())).thenReturn(rows(row(1, "first")));

		mockMvc.perform(post("/createManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{\"manufacturerName\":\"first\",\"providerServiceMapID\":3}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].manufacturerName").value("first"));

		ArgumentCaptor<List<M_Manufacturer>> captor = ArgumentCaptor.forClass(List.class);
		verify(manufacturerInter).createManufacturer(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	@DisplayName("createManufacturer should report the failure when the service blows up")
	void createManufacturer_shouldReportServiceFailure() throws Exception {
		when(manufacturerInter.createManufacturer(anyList())).thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/createManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("db unavailable"));
	}

	@Test
	@DisplayName("createManufacturer should report a parse failure for a malformed body")
	void createManufacturer_shouldReportParseFailure() throws Exception {
		mockMvc.perform(post("/createManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("not-json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getManufacturer should look the rows up by the posted provider service map id")
	void getManufacturer_shouldLookUpByProviderServiceMapId() throws Exception {
		when(manufacturerInter.createManufacturer(3)).thenReturn(rows(row(1, "first"), row(2, "second")));

		mockMvc.perform(post("/getManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.length()").value(2));

		verify(manufacturerInter).createManufacturer(3);
	}

	@Test
	@DisplayName("getManufacturer should report the failure when the lookup throws")
	void getManufacturer_shouldReportServiceFailure() throws Exception {
		when(manufacturerInter.createManufacturer(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("editManufacturer should copy the editable fields onto the stored row before saving")
	void editManufacturer_shouldCopyEditableFieldsBeforeSaving() throws Exception {
		M_Manufacturer stored = row(1, "old name");
		when(manufacturerInter.editManufacturer(1)).thenReturn(stored);
		when(manufacturerInter.saveEditedData(any(M_Manufacturer.class))).thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/editManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"manufacturerID\":1,\"manufacturerName\":\"new name\",\"manufacturerDesc\":\"new desc\","
						+ "\"manufacturerCode\":\"NEW\",\"status\":\"Inactive\",\"contactPerson\":\"Alex\","
						+ "\"cST_GST_No\":\"GST-9\",\"modifiedBy\":\"tester\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.manufacturerName").value("new name"));

		ArgumentCaptor<M_Manufacturer> captor = ArgumentCaptor.forClass(M_Manufacturer.class);
		verify(manufacturerInter).saveEditedData(captor.capture());
		assertEquals("tester", captor.getValue().getModifiedBy());
	}

	@Test
	@DisplayName("editManufacturer should report the failure when the row cannot be found")
	void editManufacturer_shouldReportFailureWhenRowMissing() throws Exception {
		when(manufacturerInter.editManufacturer(1)).thenReturn(null);

		mockMvc.perform(post("/editManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"manufacturerID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("deleteManufacturer should flip the deleted flag on the stored row and save it")
	void deleteManufacturer_shouldFlipDeletedFlag() throws Exception {
		M_Manufacturer stored = row(1, "first");
		when(manufacturerInter.editManufacturer(1)).thenReturn(stored);
		when(manufacturerInter.saveEditedData(any(M_Manufacturer.class))).thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/deleteManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"manufacturerID\":1,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_Manufacturer> captor = ArgumentCaptor.forClass(M_Manufacturer.class);
		verify(manufacturerInter).saveEditedData(captor.capture());
		assertEquals(Boolean.TRUE, captor.getValue().getDeleted());
	}

	@Test
	@DisplayName("deleteManufacturer should report the failure when the row cannot be found")
	void deleteManufacturer_shouldReportFailureWhenRowMissing() throws Exception {
		when(manufacturerInter.editManufacturer(1)).thenReturn(null);

		mockMvc.perform(post("/deleteManufacturer").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"manufacturerID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}
}
