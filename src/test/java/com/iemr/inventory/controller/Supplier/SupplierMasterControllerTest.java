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
package com.iemr.inventory.controller.Supplier;

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

import com.iemr.inventory.data.supplier.M_Supplier;
import com.iemr.inventory.data.supplier.M_Supplieraddress;
import com.iemr.inventory.service.supplier.SupplierInter;

@ExtendWith(MockitoExtension.class)
@DisplayName("SupplierMasterController Test Suite")
class SupplierMasterControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private SupplierInter supplierInter;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked supplier service")
	void setUp() {
		SupplierMasterController controller = new SupplierMasterController();
		ReflectionTestUtils.setField(controller, "supplierInter", supplierInter);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private static M_Supplier supplier(Integer id, String name) {
		M_Supplier supplier = new M_Supplier();
		supplier.setSupplierID(id);
		supplier.setSupplierName(name);
		supplier.setProviderServiceMapID(3);
		return supplier;
	}

	@Test
	@DisplayName("createSupplier should persist the supplier and derive its address row from the same payload")
	void createSupplier_shouldPersistSupplierAndAddress() throws Exception {
		when(supplierInter.createSupplier(anyList())).thenReturn(new ArrayList<>(List.of(supplier(11, "Acme"))));
		when(supplierInter.createAddress(anyList())).thenReturn(new ArrayList<>());

		mockMvc.perform(post("/createSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{\"supplierName\":\"Acme\",\"providerServiceMapID\":3,\"addressLine1\":\"12 Mill Road\","
						+ "\"addressLine2\":\"Suite 4\",\"district\":\"Pune\",\"state\":\"MH\",\"country\":\"IN\","
						+ "\"pinCode\":\"411001\",\"createdBy\":\"tester\"}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].supplierName").value("Acme"));

		ArgumentCaptor<List<M_Supplieraddress>> captor = ArgumentCaptor.forClass(List.class);
		verify(supplierInter).createAddress(captor.capture());
		M_Supplieraddress address = captor.getValue().get(0);
		assertEquals(11, address.getSupplierID());
		assertEquals("12 Mill Road", address.getAddressLine1());
		assertEquals("Pune", address.getDistrict());
		assertEquals("411001", address.getPinCode());
		assertEquals("tester", address.getCreatedBy());
	}

	@Test
	@DisplayName("createSupplier should report the failure when the service blows up")
	void createSupplier_shouldReportServiceFailure() throws Exception {
		when(supplierInter.createSupplier(anyList())).thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/createSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("db unavailable"));
	}

	@Test
	@DisplayName("createSupplier should report the failure when the supplier save returns nothing to map")
	void createSupplier_shouldReportFailureWhenNothingSaved() throws Exception {
		when(supplierInter.createSupplier(anyList())).thenReturn(null);

		mockMvc.perform(post("/createSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("[{\"supplierName\":\"Acme\"}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("getSupplier should look the rows up by the posted provider service map id")
	void getSupplier_shouldLookUpByProviderServiceMapId() throws Exception {
		when(supplierInter.getSupplier(3))
				.thenReturn(new ArrayList<>(List.of(supplier(11, "Acme"), supplier(12, "Beta"))));

		mockMvc.perform(post("/getSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.length()").value(2));

		verify(supplierInter).getSupplier(3);
	}

	@Test
	@DisplayName("getSupplier should report the failure when the lookup throws")
	void getSupplier_shouldReportServiceFailure() throws Exception {
		when(supplierInter.getSupplier(anyInt())).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/getSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"providerServiceMapID\":3}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("editSupplier should copy every editable field onto the stored row before saving")
	void editSupplier_shouldCopyEditableFieldsBeforeSaving() throws Exception {
		M_Supplier stored = supplier(11, "old name");
		when(supplierInter.editSupplier(11)).thenReturn(stored);
		when(supplierInter.saveEditedData(any(M_Supplier.class))).thenAnswer(inv -> inv.getArgument(0));

		mockMvc.perform(post("/editSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"supplierID\":11,\"supplierName\":\"new name\",\"supplierDesc\":\"new desc\","
						+ "\"supplierCode\":\"NEW\",\"status\":\"Inactive\",\"contactPerson\":\"Alex\","
						+ "\"drugLicenseNo\":\"DL-1\",\"cST_GST_No\":\"GST-9\",\"tIN_No\":\"TIN-4\","
						+ "\"email\":\"acme@example.org\",\"phoneNo1\":\"111\",\"phoneNo2\":\"222\","
						+ "\"modifiedBy\":\"tester\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.supplierName").value("new name"));

		ArgumentCaptor<M_Supplier> captor = ArgumentCaptor.forClass(M_Supplier.class);
		verify(supplierInter).saveEditedData(captor.capture());
		M_Supplier saved = captor.getValue();
		assertEquals("DL-1", saved.getDrugLicenseNo());
		assertEquals("GST-9", saved.getcST_GST_No());
		assertEquals("TIN-4", saved.gettIN_No());
		assertEquals("acme@example.org", saved.getEmail());
		assertEquals("111", saved.getPhoneNo1());
		assertEquals("222", saved.getPhoneNo2());
		assertEquals("tester", saved.getModifiedBy());
	}

	@Test
	@DisplayName("editSupplier should report the failure when the row cannot be found")
	void editSupplier_shouldReportFailureWhenRowMissing() throws Exception {
		when(supplierInter.editSupplier(11)).thenReturn(null);

		mockMvc.perform(post("/editSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"supplierID\":11}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}

	@Test
	@DisplayName("deleteSupplier should flip the deleted flag on the stored row and save it")
	void deleteSupplier_shouldFlipDeletedFlag() throws Exception {
		M_Supplier stored = supplier(11, "Acme");
		when(supplierInter.editSupplier(11)).thenReturn(stored);
		when(supplierInter.saveEditedData(any(M_Supplier.class))).thenAnswer(inv -> inv.getArgument(0));

		mockMvc.perform(post("/deleteSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"supplierID\":11,\"deleted\":true}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<M_Supplier> captor = ArgumentCaptor.forClass(M_Supplier.class);
		verify(supplierInter).saveEditedData(captor.capture());
		assertEquals(Boolean.TRUE, captor.getValue().getDeleted());
	}

	@Test
	@DisplayName("deleteSupplier should report the failure when the row cannot be found")
	void deleteSupplier_shouldReportFailureWhenRowMissing() throws Exception {
		when(supplierInter.editSupplier(11)).thenReturn(null);

		mockMvc.perform(post("/deleteSupplier").header("Authorization", AUTH).contentType(MediaType.APPLICATION_JSON)
				.content("{\"supplierID\":11}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5005));
	}
}
