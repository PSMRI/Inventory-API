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
package com.iemr.inventory.controller.facilitytype;

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

import com.iemr.inventory.data.facilitytype.M_facilitytype;
import com.iemr.inventory.service.facilitytype.M_facilitytypeInter;

@ExtendWith(MockitoExtension.class)
@DisplayName("FacilitytypeController Test Suite")
class FacilitytypeControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private M_facilitytypeInter m_facilitytypeInter;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked facility type service")
	void setUp() {
		FacilitytypeController controller = new FacilitytypeController();
		ReflectionTestUtils.setField(controller, "m_facilitytypeInter", m_facilitytypeInter);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static M_facilitytype row(Integer id, String name) {
		M_facilitytype row = new M_facilitytype();
		row.setFacilityTypeID(id);
		row.setFacilityTypeName(name);
		row.setProviderServiceMapID(3);
		return row;
	}

	private static ArrayList<M_facilitytype> rows(M_facilitytype... items) {
		return new ArrayList<>(List.of(items));
	}

	@Test
	@DisplayName("addFacility should persist the posted array and answer with the saved rows")
	void addFacility_shouldPersistPostedArray() throws Exception {
		when(m_facilitytypeInter.addAllFicilityData(anyList())).thenReturn(rows(row(1, "first")));

		mockMvc.perform(post("/addFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{\"facilityTypeName\":\"first\",\"providerServiceMapID\":3}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].facilityTypeName").value("first"));

		ArgumentCaptor<List<M_facilitytype>> captor = ArgumentCaptor.forClass(List.class);
		verify(m_facilitytypeInter).addAllFicilityData(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	@DisplayName("addFacility should report the failure when the service blows up")
	void addFacility_shouldReportServiceFailure() throws Exception {
		when(m_facilitytypeInter.addAllFicilityData(anyList())).thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/addFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("db unavailable"));
	}

	@Test
	@DisplayName("addFacility should report a parse failure for a malformed body")
	void addFacility_shouldReportParseFailure() throws Exception {
		mockMvc.perform(post("/addFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("not-json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getFacility should look the rows up by the posted provider service map id")
	void getFacility_shouldLookUpByProviderServiceMapId() throws Exception {
		when(m_facilitytypeInter.getAllFicilityData(3)).thenReturn(rows(row(1, "first"), row(2, "second")));

		mockMvc.perform(post("/getFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.length()").value(2));

		verify(m_facilitytypeInter).getAllFicilityData(3);
	}

	@Test
	@DisplayName("getFacility should report the failure when the lookup throws")
	void getFacility_shouldReportServiceFailure() throws Exception {
		when(m_facilitytypeInter.getAllFicilityData(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("editFacility should copy the editable fields onto the stored row before saving")
	void editFacility_shouldCopyEditableFieldsBeforeSaving() throws Exception {
		M_facilitytype stored = row(1, "old name");
		when(m_facilitytypeInter.editAllFicilityData(1)).thenReturn(stored);
		when(m_facilitytypeInter.updateFacilityData(any(M_facilitytype.class))).thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/editFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityTypeID\":1,\"facilityTypeName\":\"new name\",\"facilityTypeDesc\":\"new desc\","
						+ "\"facilityTypeCode\":\"NEW\",\"modifiedBy\":\"tester\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.facilityTypeName").value("new name"));

		ArgumentCaptor<M_facilitytype> captor = ArgumentCaptor.forClass(M_facilitytype.class);
		verify(m_facilitytypeInter).updateFacilityData(captor.capture());
		assertEquals("tester", captor.getValue().getModifiedBy());
	}

	@Test
	@DisplayName("editFacility should report the failure when the row cannot be found")
	void editFacility_shouldReportFailureWhenRowMissing() throws Exception {
		when(m_facilitytypeInter.editAllFicilityData(1)).thenReturn(null);

		mockMvc.perform(post("/editFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityTypeID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("deleteFacility should flip the deleted flag on the stored row and save it")
	void deleteFacility_shouldFlipDeletedFlag() throws Exception {
		M_facilitytype stored = row(1, "first");
		when(m_facilitytypeInter.editAllFicilityData(1)).thenReturn(stored);
		when(m_facilitytypeInter.updateFacilityData(any(M_facilitytype.class))).thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/deleteFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityTypeID\":1,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_facilitytype> captor = ArgumentCaptor.forClass(M_facilitytype.class);
		verify(m_facilitytypeInter).updateFacilityData(captor.capture());
		assertEquals(Boolean.TRUE, captor.getValue().getDeleted());
	}

	@Test
	@DisplayName("deleteFacility should report the failure when the row cannot be found")
	void deleteFacility_shouldReportFailureWhenRowMissing() throws Exception {
		when(m_facilitytypeInter.editAllFicilityData(1)).thenReturn(null);

		mockMvc.perform(post("/deleteFacility").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"facilityTypeID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}
}
