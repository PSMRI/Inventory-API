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
package com.iemr.inventory.service.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.mapper.report.InventoryReportMapper;
import com.iemr.inventory.model.report.BenDrugIssueReport;
import com.iemr.inventory.model.report.InwardStockReport;
import com.iemr.inventory.repo.report.ItemStockReportRepo;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("CRMReportServiceImpl Test Suite")
class CRMReportServiceImplTest {

	private static final Timestamp START = Timestamp.valueOf("2025-01-01 00:00:00");
	private static final Timestamp END = Timestamp.valueOf("2025-01-31 23:59:00");
	private static final Date EXPIRY = Date.valueOf("2026-01-31");

	@Mock
	private ItemStockReportRepo itemStockReportRepo;
	@Mock
	private InventoryReportMapper mapper;

	@InjectMocks
	private CRMReportServiceImpl service;

	private static ArrayList<Object[]> rows(Object[]... values) {
		ArrayList<Object[]> list = new ArrayList<>();
		for (Object[] value : values) {
			list.add(value);
		}
		return list;
	}

	private static ItemStockEntryReport request(Integer facilityID) {
		ItemStockEntryReport request = new ItemStockEntryReport();
		request.setFacilityID(facilityID);
		request.setStartDate(START);
		request.setEndDate(END);
		return request;
	}

	/** Builds a row in the 19-column shape the daily stock detail query returns. */
	private static Object[] stockDetailRow() {
		Object[] row = new Object[19];
		row[3] = "B-1";
		row[4] = 100;
		row[5] = 2.5d;
		row[6] = EXPIRY;
		row[9] = Timestamp.valueOf("2025-01-05 09:00:00");
		row[10] = 40;
		row[11] = 6;
		row[12] = "Paracetamol";
		row[13] = "Main store";
		row[14] = "Analgesic";
		row[15] = 1;
		row[16] = 2;
		row[17] = 3;
		row[18] = 131;
		return row;
	}

	@Nested
	@DisplayName("Inward stock report")
	class InwardStockReportTests {

		@Test
		@DisplayName("getInwardStockReport should number the rows of one facility in order")
		void getInwardStockReport_shouldNumberRowsOfOneFacility() {
			when(itemStockReportRepo.getItemStockEntryReportByFacilityID(START, END, 7))
					.thenReturn(List.of(new ItemStockEntryReport(), new ItemStockEntryReport()));
			when(mapper.mapInwardStockReport(any(ItemStockEntryReport.class)))
					.thenAnswer(inv -> new InwardStockReport());

			assertTrue(service.getInwardStockReport(request(7)).contains("slNo"));
			verify(itemStockReportRepo, never()).getItemStockEntryReport(any(), any());
		}

		@Test
		@DisplayName("getInwardStockReport should cover every facility when none is named")
		void getInwardStockReport_shouldCoverEveryFacilityWhenNoneNamed() {
			when(itemStockReportRepo.getItemStockEntryReport(START, END))
					.thenReturn(List.of(new ItemStockEntryReport()));
			when(mapper.mapInwardStockReport(any(ItemStockEntryReport.class)))
					.thenAnswer(inv -> new InwardStockReport());

			service.getInwardStockReport(request(null));

			verify(itemStockReportRepo).getItemStockEntryReport(START, END);
		}
	}

	@Nested
	@DisplayName("Expiry reports")
	class ExpiryReportTests {

		private Object[] expiryRow() {
			return new Object[] { "Main store", "Paracetamol", "Analgesic", "500mg", "B-1",
					BigDecimal.valueOf(2.5d), EXPIRY, 40 };
		}

		@Test
		@DisplayName("getExpiryReport should describe every batch expiring in the window at one facility")
		void getExpiryReport_shouldDescribeBatchesOfOneFacility() {
			when(itemStockReportRepo.getExpiryReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(expiryRow()));

			String report = service.getExpiryReport(request(7));

			assertTrue(report.contains("Paracetamol"));
			assertTrue(report.contains("B-1"));
		}

		@Test
		@DisplayName("getExpiryReport should cover every facility when none is named")
		void getExpiryReport_shouldCoverEveryFacilityWhenNoneNamed() {
			when(itemStockReportRepo.getExpiryReport(any(Date.class), any(Date.class)))
					.thenReturn(rows(expiryRow()));

			service.getExpiryReport(request(null));

			verify(itemStockReportRepo).getExpiryReport(any(Date.class), any(Date.class));
		}

		@Test
		@DisplayName("getExpiryReport should skip an absent result row")
		void getExpiryReport_shouldSkipAbsentRow() {
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getExpiryReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(results);

			assertEquals("[]", service.getExpiryReport(request(7)));
		}

		@Test
		@DisplayName("getExpiryReport should tolerate a row whose every column is null")
		void getExpiryReport_shouldTolerateAllNullRow() {
			when(itemStockReportRepo.getExpiryReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(new Object[8]));

			assertTrue(service.getExpiryReport(request(7)).contains("slNo"));
		}

		@Test
		@DisplayName("getShortExpiryReport should look ninety days ahead at one facility")
		void getShortExpiryReport_shouldLookNinetyDaysAheadAtOneFacility() {
			when(itemStockReportRepo.getShortExpiryReportByFacilityID(any(Date.class), eq(7)))
					.thenReturn(rows(expiryRow()));

			assertTrue(service.getShortExpiryReport(request(7)).contains("Paracetamol"));
		}

		@Test
		@DisplayName("getShortExpiryReport should cover every facility when none is named")
		void getShortExpiryReport_shouldCoverEveryFacilityWhenNoneNamed() {
			when(itemStockReportRepo.getShortExpiryReport(any(Date.class))).thenReturn(rows(expiryRow()));

			service.getShortExpiryReport(request(null));

			verify(itemStockReportRepo).getShortExpiryReport(any(Date.class));
		}

		@Test
		@DisplayName("getShortExpiryReport should skip an absent result row")
		void getShortExpiryReport_shouldSkipAbsentRow() {
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getShortExpiryReportByFacilityID(any(Date.class), eq(7)))
					.thenReturn(results);

			assertEquals("[]", service.getShortExpiryReport(request(7)));
		}
	}

	@Nested
	@DisplayName("Consumption report")
	class ConsumptionReportTests {

		private ItemStockExitReport exitRequest(Integer facilityID) {
			ItemStockExitReport request = new ItemStockExitReport();
			request.setFacilityID(facilityID);
			request.setStartDate(START);
			request.setEndDate(END);
			return request;
		}

		private Object[] consumptionRow() {
			Object[] row = new Object[40];
			row[4] = "Main store";
			row[11] = "Paracetamol";
			row[14] = "Analgesic";
			row[16] = "B-1";
			row[19] = BigDecimal.valueOf(2.5d);
			row[20] = EXPIRY;
			row[23] = 6;
			row[26] = "PatientIssue";
			row[32] = Timestamp.valueOf("2025-01-15 11:00:00");
			row[38] = BigInteger.valueOf(101L);
			row[39] = "Jane Doe";
			return row;
		}

		@Test
		@DisplayName("getConsumptionReport should describe every consumption at one facility")
		void getConsumptionReport_shouldDescribeConsumptionsOfOneFacility() {
			when(itemStockReportRepo.getItemStockExitReportByFacilityID(START, END, 7))
					.thenReturn(rows(consumptionRow()));

			String report = service.getConsumptionReport(exitRequest(7));

			assertTrue(report.contains("Paracetamol"));
			assertTrue(report.contains("Jane Doe"));
			assertTrue(report.contains("101"));
		}

		@Test
		@DisplayName("getConsumptionReport should cover every facility when none is named")
		void getConsumptionReport_shouldCoverEveryFacilityWhenNoneNamed() {
			when(itemStockReportRepo.getItemStockExitReport(START, END)).thenReturn(rows(consumptionRow()));

			service.getConsumptionReport(exitRequest(null));

			verify(itemStockReportRepo).getItemStockExitReport(START, END);
		}

		@Test
		@DisplayName("getConsumptionReport should skip a row that is too short to read")
		void getConsumptionReport_shouldSkipShortRow() {
			when(itemStockReportRepo.getItemStockExitReportByFacilityID(START, END, 7))
					.thenReturn(rows(new Object[10]));

			assertEquals("[]", service.getConsumptionReport(exitRequest(7)));
		}

		@Test
		@DisplayName("getConsumptionReport should report a blank beneficiary id when the row carries none")
		void getConsumptionReport_shouldReportBlankBeneficiaryIdWhenAbsent() {
			Object[] row = consumptionRow();
			row[38] = null;
			when(itemStockReportRepo.getItemStockExitReportByFacilityID(START, END, 7)).thenReturn(rows(row));

			assertTrue(service.getConsumptionReport(exitRequest(7)).contains("\"beneficiaryID\":\"\""));
		}
	}

	@Nested
	@DisplayName("Beneficiary drug issue report")
	class BenDrugIssueReportTests {

		private PatientIssueExitReport issueRequest(Integer facilityID) {
			PatientIssueExitReport request = new PatientIssueExitReport();
			request.setFacilityID(facilityID);
			request.setStartDate(START);
			request.setEndDate(END);
			return request;
		}

		private Object[] issueRow() {
			Object[] row = new Object[36];
			row[0] = 1L;
			row[1] = 501L;
			row[2] = 601L;
			row[3] = 11;
			row[4] = "Paracetamol";
			row[7] = "Analgesic";
			row[8] = "500mg";
			row[9] = "B-1";
			row[13] = EXPIRY;
			row[14] = 6;
			row[22] = 7;
			row[23] = "Jane Doe";
			row[24] = 34;
			row[25] = "Female";
			row[35] = Timestamp.valueOf("2025-01-15 11:00:00");
			return row;
		}

		@Test
		@DisplayName("getBenDrugIssueReport should map every dispensed line at one facility")
		void getBenDrugIssueReport_shouldMapDispensedLinesOfOneFacility() {
			when(itemStockReportRepo.getPatientIssueExitReportByFacilityID(START, END, 7))
					.thenReturn(rows(issueRow()));
			when(mapper.mapBenDrugIssueReport(any(PatientIssueExitReport.class)))
					.thenAnswer(inv -> new BenDrugIssueReport());

			assertTrue(service.getBenDrugIssueReport(issueRequest(7)).contains("slNo"));
			verify(mapper).mapBenDrugIssueReport(any(PatientIssueExitReport.class));
		}

		@Test
		@DisplayName("getBenDrugIssueReport should cover every facility when none is named")
		void getBenDrugIssueReport_shouldCoverEveryFacilityWhenNoneNamed() {
			when(itemStockReportRepo.getPatientIssueExitReport(START, END)).thenReturn(rows(issueRow()));
			when(mapper.mapBenDrugIssueReport(any(PatientIssueExitReport.class)))
					.thenAnswer(inv -> new BenDrugIssueReport());

			service.getBenDrugIssueReport(issueRequest(null));

			verify(itemStockReportRepo).getPatientIssueExitReport(START, END);
		}

		@Test
		@DisplayName("getBenDrugIssueReport should skip an absent result row")
		void getBenDrugIssueReport_shouldSkipAbsentRow() {
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getPatientIssueExitReportByFacilityID(START, END, 7)).thenReturn(results);

			assertEquals("[]", service.getBenDrugIssueReport(issueRequest(7)));
		}
	}

	@Nested
	@DisplayName("Daily, monthly and yearly stock reports")
	class PeriodicStockReportTests {

		@Test
		@DisplayName("getDailyStockDetailsReport should describe each batch movement of the day")
		void getDailyStockDetailsReport_shouldDescribeBatchMovements() {
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(stockDetailRow()));

			String report = service.getDailyStockDetailsReport(request(7));

			assertTrue(report.contains("Paracetamol"));
			assertTrue(report.contains("Main store"));
			assertTrue(report.contains("B-1"));
		}

		@Test
		@DisplayName("getDailyStockDetailsReport should default every absent count to zero")
		void getDailyStockDetailsReport_shouldDefaultAbsentCountsToZero() {
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(new Object[19]));

			String report = service.getDailyStockDetailsReport(request(7));

			assertTrue(report.contains("\"openingStock\":\"0\""));
			assertTrue(report.contains("\"closingStock\":\"0\""));
		}

		@Test
		@DisplayName("getDailyStockDetailsReport should skip an absent result row")
		void getDailyStockDetailsReport_shouldSkipAbsentRow() {
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(results);

			assertEquals("[]", service.getDailyStockDetailsReport(request(7)));
		}

		@Test
		@DisplayName("getDailyStockSummaryReport should roll the day up per item")
		void getDailyStockSummaryReport_shouldRollUpPerItem() {
			Object[] row = new Object[13];
			row[2] = "Paracetamol";
			row[3] = "Main store";
			row[4] = "Analgesic";
			row[5] = 100;
			row[6] = 40;
			row[7] = 1;
			row[8] = 6;
			row[9] = 1;
			row[10] = 2;
			row[11] = 3;
			row[12] = 131;
			when(itemStockReportRepo.getDailyStockSummaryReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(row));

			String report = service.getDailyStockSummaryReport(request(7));

			assertTrue(report.contains("Paracetamol"));
			assertTrue(report.contains("\"closingStock\":\"131\""));
		}

		@Test
		@DisplayName("getDailyStockSummaryReport should default every absent count to zero")
		void getDailyStockSummaryReport_shouldDefaultAbsentCountsToZero() {
			when(itemStockReportRepo.getDailyStockSummaryReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(new Object[13]));

			assertTrue(service.getDailyStockSummaryReport(request(7)).contains("\"openingStock\":\"0\""));
		}

		@Test
		@DisplayName("getDailyStockSummaryReport should skip an absent result row")
		void getDailyStockSummaryReport_shouldSkipAbsentRow() {
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getDailyStockSummaryReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(results);

			assertEquals("[]", service.getDailyStockSummaryReport(request(7)));
		}

		@Test
		@DisplayName("getMonthlyReport should cover the whole of the requested month")
		void getMonthlyReport_shouldCoverWholeMonth() {
			ItemStockEntryReport monthRequest = request(7);
			monthRequest.setYear(2025);
			monthRequest.setMonth(0);
			monthRequest.setMonthName("January");
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(stockDetailRow()));

			String report = service.getMonthlyReport(monthRequest);

			assertTrue(report.contains("January"));
			assertTrue(report.contains("Paracetamol"));
		}

		@Test
		@DisplayName("getMonthlyReport should default every absent count to zero")
		void getMonthlyReport_shouldDefaultAbsentCountsToZero() {
			ItemStockEntryReport monthRequest = request(7);
			monthRequest.setYear(2025);
			monthRequest.setMonth(0);
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(new Object[19]));

			assertTrue(service.getMonthlyReport(monthRequest).contains("\"openingStock\":\"0\""));
		}

		@Test
		@DisplayName("getMonthlyReport should skip an absent result row")
		void getMonthlyReport_shouldSkipAbsentRow() {
			ItemStockEntryReport monthRequest = request(7);
			monthRequest.setYear(2025);
			monthRequest.setMonth(0);
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(results);

			assertEquals("[]", service.getMonthlyReport(monthRequest));
		}

		@Test
		@DisplayName("getYearlyReport should cover the whole of the requested year")
		void getYearlyReport_shouldCoverWholeYear() {
			ItemStockEntryReport yearRequest = request(7);
			yearRequest.setYear(2025);
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(stockDetailRow()));

			String report = service.getYearlyReport(yearRequest);

			assertTrue(report.contains("2025"));
			assertTrue(report.contains("Paracetamol"));
		}

		@Test
		@DisplayName("getYearlyReport should default every absent count to zero")
		void getYearlyReport_shouldDefaultAbsentCountsToZero() {
			ItemStockEntryReport yearRequest = request(7);
			yearRequest.setYear(2025);
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(rows(new Object[19]));

			assertTrue(service.getYearlyReport(yearRequest).contains("\"openingStock\":\"0\""));
		}

		@Test
		@DisplayName("getYearlyReport should skip an absent result row")
		void getYearlyReport_shouldSkipAbsentRow() {
			ItemStockEntryReport yearRequest = request(7);
			yearRequest.setYear(2025);
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(any(Date.class), any(Date.class), eq(7)))
					.thenReturn(results);

			assertEquals("[]", service.getYearlyReport(yearRequest));
		}
	}

	@Nested
	@DisplayName("Transit report")
	class TransitReportTests {

		private Object[] transitRow() {
			return new Object[] { "Paracetamol", "B-1", BigDecimal.valueOf(2.5d), EXPIRY, "Main store", "Sub store",
					Timestamp.valueOf("2025-01-10 09:00:00"), Timestamp.valueOf("2025-01-11 09:00:00") };
		}

		@Test
		@DisplayName("getTransitReport should describe every transfer touching one facility")
		void getTransitReport_shouldDescribeTransfersOfOneFacility() {
			when(itemStockReportRepo.getTransitReportByFacilityID(START, END, 7)).thenReturn(rows(transitRow()));

			String report = service.getTransitReport(request(7));

			assertTrue(report.contains("Paracetamol"));
			assertTrue(report.contains("Sub store"));
		}

		@Test
		@DisplayName("getTransitReport should cover every facility when none is named")
		void getTransitReport_shouldCoverEveryFacilityWhenNoneNamed() {
			when(itemStockReportRepo.getTransitReport(START, END)).thenReturn(rows(transitRow()));

			service.getTransitReport(request(null));

			verify(itemStockReportRepo).getTransitReport(START, END);
		}

		@Test
		@DisplayName("getTransitReport should skip an absent result row")
		void getTransitReport_shouldSkipAbsentRow() {
			ArrayList<Object[]> results = rows();
			results.add(null);
			when(itemStockReportRepo.getTransitReportByFacilityID(START, END, 7)).thenReturn(results);

			assertEquals("[]", service.getTransitReport(request(7)));
		}
	}
}
