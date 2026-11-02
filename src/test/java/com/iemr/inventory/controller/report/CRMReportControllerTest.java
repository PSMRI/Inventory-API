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
package com.iemr.inventory.controller.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.service.report.CRMReportService;

@ExtendWith(MockitoExtension.class)
@DisplayName("CRMReportController Test Suite")
class CRMReportControllerTest {

	private static final String AUTH = "test-session-key";
	private static final String WINDOW =
			"{\"facilityID\":7,\"startDate\":\"2025-01-01T00:00:00.000\",\"endDate\":\"2025-01-31T23:59:00.000\"}";

	@Mock
	private CRMReportService crmReportService;

	private MockMvc mockMvc;

	@BeforeEach
	@DisplayName("Stand the controller up with a mocked report service")
	void setUp() {
		CRMReportController controller = new CRMReportController();
		ReflectionTestUtils.setField(controller, "crmReportService", crmReportService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	@DisplayName("getInwardStockReport should hand the posted window to the service and answer with the report")
	void getInwardStockReport_shouldReturnReport() throws Exception {
		when(crmReportService.getInwardStockReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getInwardStockReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getInwardStockReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getInwardStockReport should report the failure when the report generation throws")
	void getInwardStockReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getInwardStockReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getInwardStockReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getExpiryReport should hand the posted window to the service and answer with the report")
	void getExpiryReport_shouldReturnReport() throws Exception {
		when(crmReportService.getExpiryReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getExpiryReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getExpiryReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getExpiryReport should report the failure when the report generation throws")
	void getExpiryReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getExpiryReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getExpiryReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getConsumptionReport should hand the posted window to the service and answer with the report")
	void getConsumptionReport_shouldReturnReport() throws Exception {
		when(crmReportService.getConsumptionReport(any(ItemStockExitReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getConsumptionReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockExitReport> captor = ArgumentCaptor.forClass(ItemStockExitReport.class);
		verify(crmReportService).getConsumptionReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getConsumptionReport should report the failure when the report generation throws")
	void getConsumptionReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getConsumptionReport(any(ItemStockExitReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getConsumptionReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getBenDrugIssueReport should hand the posted window to the service and answer with the report")
	void getBenDrugIssueReport_shouldReturnReport() throws Exception {
		when(crmReportService.getBenDrugIssueReport(any(PatientIssueExitReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getBenDrugIssueReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<PatientIssueExitReport> captor = ArgumentCaptor.forClass(PatientIssueExitReport.class);
		verify(crmReportService).getBenDrugIssueReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getBenDrugIssueReport should report the failure when the report generation throws")
	void getBenDrugIssueReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getBenDrugIssueReport(any(PatientIssueExitReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getBenDrugIssueReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getDailyStockDetailReport should hand the posted window to the service and answer with the report")
	void getDailyStockDetailReport_shouldReturnReport() throws Exception {
		when(crmReportService.getDailyStockDetailsReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getDailyStockDetailReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getDailyStockDetailsReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getDailyStockDetailReport should report the failure when the report generation throws")
	void getDailyStockDetailReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getDailyStockDetailsReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getDailyStockDetailReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getDailyStockSummaryReport should hand the posted window to the service and answer with the report")
	void getDailyStockSummaryReport_shouldReturnReport() throws Exception {
		when(crmReportService.getDailyStockSummaryReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getDailyStockSummaryReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getDailyStockSummaryReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getDailyStockSummaryReport should report the failure when the report generation throws")
	void getDailyStockSummaryReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getDailyStockSummaryReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getDailyStockSummaryReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getMonthlyReport should hand the posted window to the service and answer with the report")
	void getMonthlyReport_shouldReturnReport() throws Exception {
		when(crmReportService.getMonthlyReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getMonthlyReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getMonthlyReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getMonthlyReport should report the failure when the report generation throws")
	void getMonthlyReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getMonthlyReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getMonthlyReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getYearlyReport should hand the posted window to the service and answer with the report")
	void getYearlyReport_shouldReturnReport() throws Exception {
		when(crmReportService.getYearlyReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getYearlyReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getYearlyReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getYearlyReport should report the failure when the report generation throws")
	void getYearlyReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getYearlyReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getYearlyReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getShortExpiryReport should hand the posted window to the service and answer with the report")
	void getShortExpiryReport_shouldReturnReport() throws Exception {
		when(crmReportService.getShortExpiryReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getShortExpiryReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getShortExpiryReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getShortExpiryReport should report the failure when the report generation throws")
	void getShortExpiryReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getShortExpiryReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getShortExpiryReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}

	@Test
	@DisplayName("getTransitReport should hand the posted window to the service and answer with the report")
	void getTransitReport_shouldReturnReport() throws Exception {
		when(crmReportService.getTransitReport(any(ItemStockEntryReport.class))).thenReturn("[{\"slNo\":1}]");

		mockMvc.perform(post("/crmReportController/getTransitReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(200))
				.andExpect(jsonPath("$.data[0].slNo").value(1));

		ArgumentCaptor<ItemStockEntryReport> captor = ArgumentCaptor.forClass(ItemStockEntryReport.class);
		verify(crmReportService).getTransitReport(captor.capture());
		assertEquals(7, captor.getValue().getFacilityID());
	}

	@Test
	@DisplayName("getTransitReport should report the failure when the report generation throws")
	void getTransitReport_shouldReportServiceFailure() throws Exception {
		when(crmReportService.getTransitReport(any(ItemStockEntryReport.class))).thenThrow(new RuntimeException("report failed"));

		mockMvc.perform(post("/crmReportController/getTransitReport").header("Authorization", AUTH)
				.contentType(MediaType.APPLICATION_JSON).content(WINDOW))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.statusCode").value(5000))
				.andExpect(jsonPath("$.errorMessage").value("report failed"));
	}
}
