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
package com.iemr.inventory.controller.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
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

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.service.indent.IndentService;

@ExtendWith(MockitoExtension.class)
@DisplayName("IndentController Test Suite")
class IndentControllerTest {

	private static final String AUTH = "test-session-key";

	@Mock
	private IndentService indentService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked indent service")
	void setUp() {
		IndentController controller = new IndentController();
		ReflectionTestUtils.setField(controller, "IndentService", indentService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@DisplayName("partialsearchindentitems should trim the posted item name before searching")
	void partialsearchindentitems_shouldTrimItemName() throws Exception {
		when(indentService.findItemIndent(1, "Para")).thenReturn(List.of(new ItemfacilitymappingIndent()));

		mockMvc.perform(post("/indentController/partialsearchindentitems").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"  Para  \",\"facilityID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(indentService).findItemIndent(1, "Para");
	}

	@Test
	@DisplayName("partialsearchindentitems should report the failure when the search throws")
	void partialsearchindentitems_shouldReportServiceFailure() throws Exception {
		when(indentService.findItemIndent(anyInt(), anyString())).thenThrow(new RuntimeException("search failed"));

		mockMvc.perform(post("/indentController/partialsearchindentitems").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"itemName\":\"Para\",\"facilityID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("createIndentRequest should hand the posted indent to the service")
	void createIndentRequest_shouldHandIndentToService() throws Exception {
		when(indentService.createIndentRequest(any(Indent.class))).thenReturn("{\"indentID\":88}");

		mockMvc.perform(post("/indentController/createIndentRequest").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"fromFacilityID\":1,\"toFacilityID\":2,\"indentOrder\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.indentID").value(88));

		ArgumentCaptor<Indent> captor = ArgumentCaptor.forClass(Indent.class);
		verify(indentService).createIndentRequest(captor.capture());
		assertEquals(1, captor.getValue().getFromFacilityID());
	}

	@Test
	@DisplayName("createIndentRequest should report the failure when the service throws")
	void createIndentRequest_shouldReportServiceFailure() throws Exception {
		when(indentService.createIndentRequest(any(Indent.class))).thenThrow(new RuntimeException("db unavailable"));

		mockMvc.perform(post("/indentController/createIndentRequest").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"fromFacilityID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getIndentHistory should hand the posted indent probe to the service")
	void getIndentHistory_shouldHandProbeToService() throws Exception {
		when(indentService.getIndentHistory(any(Indent.class))).thenReturn("[]");

		mockMvc.perform(post("/indentController/getIndentHistory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"fromFacilityID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<Indent> captor = ArgumentCaptor.forClass(Indent.class);
		verify(indentService).getIndentHistory(captor.capture());
		assertEquals(1, captor.getValue().getFromFacilityID());
	}

	@Test
	@DisplayName("getIndentHistory should report the failure when the lookup throws")
	void getIndentHistory_shouldReportServiceFailure() throws Exception {
		when(indentService.getIndentHistory(any(Indent.class))).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/indentController/getIndentHistory").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"fromFacilityID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getOrdersByIndentID should hand the posted order probe to the service")
	void getOrdersByIndentID_shouldHandProbeToService() throws Exception {
		when(indentService.getOrdersByIndentID(any(IndentOrder.class))).thenReturn("[]");

		mockMvc.perform(post("/indentController/getOrdersByIndentID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<IndentOrder> captor = ArgumentCaptor.forClass(IndentOrder.class);
		verify(indentService).getOrdersByIndentID(captor.capture());
		assertEquals(88L, captor.getValue().getIndentID());
	}

	@Test
	@DisplayName("getOrdersByIndentID should report the failure when the lookup throws")
	void getOrdersByIndentID_shouldReportServiceFailure() throws Exception {
		when(indentService.getOrdersByIndentID(any(IndentOrder.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/indentController/getOrdersByIndentID").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getIndentWorklist should hand the posted worklist filter to the service")
	void getIndentWorklist_shouldHandFilterToService() throws Exception {
		when(indentService.getIndentWorklist(any(IndentOrder.class))).thenReturn("[]");

		mockMvc.perform(post("/indentController/getIndentWorklist").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":2}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		ArgumentCaptor<IndentOrder> captor = ArgumentCaptor.forClass(IndentOrder.class);
		verify(indentService).getIndentWorklist(captor.capture());
		assertEquals(2, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getIndentWorklist should report the failure when the lookup throws")
	void getIndentWorklist_shouldReportServiceFailure() throws Exception {
		when(indentService.getIndentWorklist(any(IndentOrder.class))).thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/indentController/getIndentWorklist").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"facilityID\":2}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("getIndentOrderWorklist should hand the posted order probe to the service")
	void getIndentOrderWorklist_shouldHandProbeToService() throws Exception {
		when(indentService.getIndentOrderWorklist(any(IndentOrder.class))).thenReturn("[]");

		mockMvc.perform(post("/indentController/getIndentOrderWorklist").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200));

		verify(indentService).getIndentOrderWorklist(any(IndentOrder.class));
	}

	@Test
	@DisplayName("getIndentOrderWorklist should report the failure when the lookup throws")
	void getIndentOrderWorklist_shouldReportServiceFailure() throws Exception {
		when(indentService.getIndentOrderWorklist(any(IndentOrder.class)))
				.thenThrow(new RuntimeException("lookup failed"));

		mockMvc.perform(post("/indentController/getIndentOrderWorklist").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("issueIndent should hand the posted array of issue lines to the service")
	void issueIndent_shouldHandIssueLinesToService() throws Exception {
		when(indentService.issueIndent(any(IndentIssue[].class))).thenReturn("Dispensed successfully");

		mockMvc.perform(post("/indentController/issueIndent").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("[{\"indentID\":88,\"action\":\"Issued\",\"issuedQty\":6}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data.response").value("Dispensed successfully"));

		ArgumentCaptor<IndentIssue[]> captor = ArgumentCaptor.forClass(IndentIssue[].class);
		verify(indentService).issueIndent(captor.capture());
		assertEquals("Issued", captor.getValue()[0].getAction());
	}

	@Test
	@DisplayName("issueIndent should report the failure when the issue throws")
	void issueIndent_shouldReportServiceFailure() throws Exception {
		when(indentService.issueIndent(any(IndentIssue[].class))).thenThrow(new RuntimeException("issue failed"));

		mockMvc.perform(post("/indentController/issueIndent").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("[{}]"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("cancelIndentOrder should hand the posted indent to the service")
	void cancelIndentOrder_shouldHandIndentToService() throws Exception {
		when(indentService.cancelIndentOrder(any(Indent.class))).thenReturn("Cancelled successfully");

		mockMvc.perform(post("/indentController/cancelIndentOrder").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.response").value("Cancelled successfully"));
	}

	@Test
	@DisplayName("cancelIndentOrder should report the failure when the cancellation throws")
	void cancelIndentOrder_shouldReportServiceFailure() throws Exception {
		when(indentService.cancelIndentOrder(any(Indent.class))).thenThrow(new RuntimeException("cancel failed"));

		mockMvc.perform(post("/indentController/cancelIndentOrder").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("receiveIndent should hand the posted indent to the service")
	void receiveIndent_shouldHandIndentToService() throws Exception {
		when(indentService.receiveIndent(any(Indent.class))).thenReturn("Received successfully");

		mockMvc.perform(post("/indentController/receiveIndent").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88,\"fromFacilityID\":1}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.response").value("Received successfully"));
	}

	@Test
	@DisplayName("receiveIndent should report the failure when the receipt throws")
	void receiveIndent_shouldReportServiceFailure() throws Exception {
		when(indentService.receiveIndent(any(Indent.class))).thenThrow(new RuntimeException("receive failed"));

		mockMvc.perform(post("/indentController/receiveIndent").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}

	@Test
	@DisplayName("updateIndentOrder should hand the posted indent to the service")
	void updateIndentOrder_shouldHandIndentToService() throws Exception {
		when(indentService.updateIndentOrder(any(Indent.class))).thenReturn("Updated successfully");

		mockMvc.perform(post("/indentController/updateIndentOrder").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"indentID\":88,\"fromFacilityID\":1,\"indentOrder\":[]}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.response").value("Updated successfully"));
	}

	@Test
	@DisplayName("updateIndentOrder should report the failure when the update throws")
	void updateIndentOrder_shouldReportServiceFailure() throws Exception {
		when(indentService.updateIndentOrder(any(Indent.class))).thenThrow(new RuntimeException("update failed"));

		mockMvc.perform(post("/indentController/updateIndentOrder").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content("{\"indentID\":88}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000));
	}
}
