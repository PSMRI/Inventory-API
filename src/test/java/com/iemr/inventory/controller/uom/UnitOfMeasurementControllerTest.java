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
package com.iemr.inventory.controller.uom;

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

import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.service.uom.UomInter;

@ExtendWith(MockitoExtension.class)
@DisplayName("UnitOfMeasurementController Test Suite")
class UnitOfMeasurementControllerTest {

	@Mock
	private UomInter uomInter;

	private static final String AUTH = "test-session-key";

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked UoM service")
	void setUp() {
		UnitOfMeasurementController controller = new UnitOfMeasurementController();
		ReflectionTestUtils.setField(controller, "uomInter", uomInter);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static M_Uom uom(Integer id, String name) {
		M_Uom uom = new M_Uom();
		uom.setuOMID(id);
		uom.setuOMName(name);
		uom.setuOMDesc("a description");
		uom.setuOMCode("CODE");
		uom.setStatus("Active");
		uom.setProviderServiceMapID(3);
		return uom;
	}

	private static ArrayList<M_Uom> uomList(M_Uom... items) {
		return new ArrayList<>(List.of(items));
	}

	@Test
	@DisplayName("createUom should persist the posted array and answer with the saved rows")
	void createUom_shouldPersistPostedArray() throws Exception {
		when(uomInter.createDrugtypeData(anyList())).thenReturn(uomList(uom(1, "Tablet")));

		mockMvc.perform(post("/createUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{\"uOMName\":\"Tablet\",\"providerServiceMapID\":3}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].uOMName").value("Tablet"));

		ArgumentCaptor<List<M_Uom>> captor = ArgumentCaptor.forClass(List.class);
		verify(uomInter).createDrugtypeData(captor.capture());
		org.junit.jupiter.api.Assertions.assertEquals(1, captor.getValue().size());
	}

	@Test
	@DisplayName("createUom should report the failure when the service blows up")
	void createUom_shouldReportServiceFailure() throws Exception {
		when(uomInter.createDrugtypeData(anyList())).thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/createUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON).content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("db unavailable"));
	}

	@Test
	@DisplayName("createUom should report a parse failure for a malformed body")
	void createUom_shouldReportParseFailure() throws Exception {
		mockMvc.perform(post("/createUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON).content("not-json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getUom should look the rows up by the posted provider service map id")
	void getUom_shouldLookUpByProviderServiceMapId() throws Exception {
		when(uomInter.createDrugtypeData(3)).thenReturn(uomList(uom(1, "Tablet"), uom(2, "Syrup")));

		mockMvc.perform(post("/getUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.length()").value(2));

		verify(uomInter).createDrugtypeData(3);
	}

	@Test
	@DisplayName("getUom should report the failure when the lookup throws")
	void getUom_shouldReportServiceFailure() throws Exception {
		when(uomInter.createDrugtypeData(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("editUom should copy the editable fields onto the stored row before saving")
	void editUom_shouldCopyEditableFieldsBeforeSaving() throws Exception {
		M_Uom stored = uom(1, "old name");
		when(uomInter.editDrugtypeData(1)).thenReturn(stored);
		when(uomInter.saveeditedData(any(M_Uom.class))).thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/editUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"uomID\":1,\"uOMName\":\"new name\",\"uOMDesc\":\"new desc\","
						+ "\"uOMCode\":\"NEW\",\"status\":\"Inactive\",\"modifiedBy\":\"tester\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.uOMName").value("new name"));

		ArgumentCaptor<M_Uom> captor = ArgumentCaptor.forClass(M_Uom.class);
		verify(uomInter).saveeditedData(captor.capture());
		M_Uom saved = captor.getValue();
		org.junit.jupiter.api.Assertions.assertEquals("new desc", saved.getuOMDesc());
		org.junit.jupiter.api.Assertions.assertEquals("NEW", saved.getuOMCode());
		org.junit.jupiter.api.Assertions.assertEquals("Inactive", saved.getStatus());
		org.junit.jupiter.api.Assertions.assertEquals("tester", saved.getModifiedBy());
	}

	@Test
	@DisplayName("editUom should report the failure when the row cannot be found")
	void editUom_shouldReportFailureWhenRowMissing() throws Exception {
		when(uomInter.editDrugtypeData(1)).thenReturn(null);

		mockMvc.perform(post("/editUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON).content("{\"uomID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("deleteUom should flip the deleted flag on the stored row and save it")
	void deleteUom_shouldFlipDeletedFlag() throws Exception {
		M_Uom stored = uom(1, "Tablet");
		when(uomInter.editDrugtypeData(1)).thenReturn(stored);
		when(uomInter.saveeditedData(any(M_Uom.class))).thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/deleteUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"uomID\":1,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_Uom> captor = ArgumentCaptor.forClass(M_Uom.class);
		verify(uomInter).saveeditedData(captor.capture());
		org.junit.jupiter.api.Assertions.assertEquals(Boolean.TRUE, captor.getValue().getDeleted());
	}

	@Test
	@DisplayName("deleteUom should report the failure when the row cannot be found")
	void deleteUom_shouldReportFailureWhenRowMissing() throws Exception {
		when(uomInter.editDrugtypeData(1)).thenReturn(null);

		mockMvc.perform(post("/deleteUom").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON).content("{\"uomID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}
}
