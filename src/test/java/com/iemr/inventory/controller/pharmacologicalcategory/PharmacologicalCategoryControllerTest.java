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
package com.iemr.inventory.controller.pharmacologicalcategory;

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

import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.service.pharmacologicalcategory.PharmacologicalcategoryInter;

@ExtendWith(MockitoExtension.class)
@DisplayName("PharmacologicalCategoryController Test Suite")
class PharmacologicalCategoryControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private PharmacologicalcategoryInter pharmacologicalcategoryInter;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked pharmacological category service")
	void setUp() {
		PharmacologicalCategoryController controller = new PharmacologicalCategoryController();
		ReflectionTestUtils.setField(controller, "pharmacologicalcategoryInter", pharmacologicalcategoryInter);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static M_Pharmacologicalcategory row(Integer id, String name) {
		M_Pharmacologicalcategory row = new M_Pharmacologicalcategory();
		row.setPharmCategoryID(id);
		row.setPharmCategoryName(name);
		row.setProviderServiceMapID(3);
		return row;
	}

	private static ArrayList<M_Pharmacologicalcategory> rows(M_Pharmacologicalcategory... items) {
		return new ArrayList<>(List.of(items));
	}

	@Test
	@DisplayName("createPharmacologicalcategory should persist the posted array and answer with the saved rows")
	void createPharmacologicalcategory_shouldPersistPostedArray() throws Exception {
		when(pharmacologicalcategoryInter.createPharmacologicalcategory(anyList())).thenReturn(rows(row(1, "Analgesic")));

		mockMvc.perform(post("/createPharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"pharmCategoryName\":\"Analgesic\",\"providerServiceMapID\":3}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].pharmCategoryName").value("Analgesic"));

		ArgumentCaptor<List<M_Pharmacologicalcategory>> captor = ArgumentCaptor.forClass(List.class);
		verify(pharmacologicalcategoryInter).createPharmacologicalcategory(captor.capture());
		assertEquals(1, captor.getValue().size());
	}

	@Test
	@DisplayName("createPharmacologicalcategory should report the failure when the service blows up")
	void createPharmacologicalcategory_shouldReportServiceFailure() throws Exception {
		when(pharmacologicalcategoryInter.createPharmacologicalcategory(anyList()))
				.thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/createPharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("db unavailable"));
	}

	@Test
	@DisplayName("getPharmacologicalcategory should look the rows up by the posted provider service map id")
	void getPharmacologicalcategory_shouldLookUpByProviderServiceMapId() throws Exception {
		when(pharmacologicalcategoryInter.getPharmacologicalcategory(3))
				.thenReturn(rows(row(1, "Analgesic"), row(2, "Antibiotic")));

		mockMvc.perform(post("/getPharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.length()").value(2));

		verify(pharmacologicalcategoryInter).getPharmacologicalcategory(3);
	}

	@Test
	@DisplayName("getPharmacologicalcategory should report the failure when the lookup throws")
	void getPharmacologicalcategory_shouldReportServiceFailure() throws Exception {
		when(pharmacologicalcategoryInter.getPharmacologicalcategory(anyInt()))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getPharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("editPharmacologicalcategory should copy the description and modifier onto the stored row")
	void editPharmacologicalcategory_shouldCopyEditableFields() throws Exception {
		M_Pharmacologicalcategory stored = row(1, "Analgesic");
		when(pharmacologicalcategoryInter.editPharmacologicalcategory(1)).thenReturn(stored);
		when(pharmacologicalcategoryInter.saveEditedPharData(any(M_Pharmacologicalcategory.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/editPharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"pharmacologyCategoryID\":1,\"pharmCategoryDesc\":\"new desc\",\"modifiedBy\":\"tester\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.pharmCategoryDesc").value("new desc"));

		ArgumentCaptor<M_Pharmacologicalcategory> captor =
				ArgumentCaptor.forClass(M_Pharmacologicalcategory.class);
		verify(pharmacologicalcategoryInter).saveEditedPharData(captor.capture());
		assertEquals("tester", captor.getValue().getModifiedBy());
	}

	@Test
	@DisplayName("editPharmacologicalcategory should report the failure when the row cannot be found")
	void editPharmacologicalcategory_shouldReportFailureWhenRowMissing() throws Exception {
		when(pharmacologicalcategoryInter.editPharmacologicalcategory(1)).thenReturn(null);

		mockMvc.perform(post("/editPharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"pharmacologyCategoryID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("deletePharmacologicalcategory should flip the deleted flag on the stored row and save it")
	void deletePharmacologicalcategory_shouldFlipDeletedFlag() throws Exception {
		M_Pharmacologicalcategory stored = row(1, "Analgesic");
		when(pharmacologicalcategoryInter.editPharmacologicalcategory(1)).thenReturn(stored);
		when(pharmacologicalcategoryInter.saveEditedPharData(any(M_Pharmacologicalcategory.class)))
				.thenAnswer(invocation -> invocation.getArgument(0));

		mockMvc.perform(post("/deletePharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"pharmacologyCategoryID\":1,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_Pharmacologicalcategory> captor =
				ArgumentCaptor.forClass(M_Pharmacologicalcategory.class);
		verify(pharmacologicalcategoryInter).saveEditedPharData(captor.capture());
		assertEquals(Boolean.TRUE, captor.getValue().getDeleted());
	}

	@Test
	@DisplayName("deletePharmacologicalcategory should report the failure when the row cannot be found")
	void deletePharmacologicalcategory_shouldReportFailureWhenRowMissing() throws Exception {
		when(pharmacologicalcategoryInter.editPharmacologicalcategory(1)).thenReturn(null);

		mockMvc.perform(post("/deletePharmacologicalcategory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"pharmacologyCategoryID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}
}
