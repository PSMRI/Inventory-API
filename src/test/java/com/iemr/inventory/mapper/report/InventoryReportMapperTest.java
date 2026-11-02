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
package com.iemr.inventory.mapper.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.model.report.BenDrugIssueReport;
import com.iemr.inventory.model.report.ExpiryReport;
import com.iemr.inventory.model.report.InwardStockReport;

@DisplayName("InventoryReportMapper Test Suite")
class InventoryReportMapperTest {

	private static final Date EXPIRY = Date.valueOf("2026-01-31");
	private static final Timestamp CREATED = Timestamp.valueOf("2025-01-31 10:15:30");

	private final InventoryReportMapper mapper = InventoryReportMapper.INSTANCE;

	private static ItemStockEntryReport entryReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFacilityName("Main store");
		report.setItemName("Paracetamol");
		report.setItemCategoryName("Analgesic");
		report.setStrength("500mg");
		report.setBatchNo("B-1");
		report.setUnitCostPrice(2.5d);
		report.setExpiryDate(EXPIRY);
		report.setCreatedDate(CREATED);
		report.setEntryType("Purchase");
		report.setQuantity(100);
		report.setQuantityInHand(40);
		return report;
	}

	private static PatientIssueExitReport exitReport() {
		PatientIssueExitReport report = new PatientIssueExitReport();
		report.setCreatedDate(CREATED);
		report.setPatientName("Jane Doe");
		report.setGender("Female");
		report.setAge(34);
		report.setItemName("Paracetamol");
		report.setItemCategoryName("Analgesic");
		report.setBatchNo("B-1");
		report.setExpiryDate(EXPIRY);
		report.setStrength("500mg");
		report.setQuantityGiven(6);
		return report;
	}

	@Test
	@DisplayName("mapInwardStockReport should carry the inward columns across, with the created date as inward date")
	void mapInwardStockReport_shouldCarryInwardColumns() {
		InwardStockReport report = mapper.mapInwardStockReport(entryReport());

		assertEquals("Main store", report.getFacilityName());
		assertEquals("Paracetamol", report.getItemName());
		assertEquals("Analgesic", report.getItemCategory());
		assertEquals("B-1", report.getBatchNo());
		assertEquals(2.5d, report.getUnitCostPrice());
		assertEquals(EXPIRY, report.getExpiryDate());
		assertEquals(CREATED, report.getInwardDate());
		assertEquals("Purchase", report.getEntryType());
		assertEquals(100, report.getQuantity());
	}

	@Test
	@DisplayName("mapInwardStockReport should return null for a null entry report")
	void mapInwardStockReport_shouldReturnNullForNull() {
		assertNull(mapper.mapInwardStockReport(null));
	}

	@Test
	@DisplayName("mapExpiryReport should carry the expiry columns across, including the quantity still in hand")
	void mapExpiryReport_shouldCarryExpiryColumns() {
		ExpiryReport report = mapper.mapExpiryReport(entryReport());

		assertEquals("Main store", report.getFacilityName());
		assertEquals("Paracetamol", report.getItemName());
		assertEquals("Analgesic", report.getItemCategory());
		assertEquals("500mg", report.getStrength());
		assertEquals("B-1", report.getBatchNo());
		assertEquals(2.5d, report.getUnitCostPrice());
		assertEquals(EXPIRY, report.getExpiryDate());
		assertEquals(40, report.getQuantityInHand());
	}

	@Test
	@DisplayName("mapExpiryReport should return null for a null entry report")
	void mapExpiryReport_shouldReturnNullForNull() {
		assertNull(mapper.mapExpiryReport(null));
	}

	@Test
	@DisplayName("mapBenDrugIssueReport should carry the beneficiary and the dispensed quantity across")
	void mapBenDrugIssueReport_shouldCarryBeneficiaryAndQuantity() {
		BenDrugIssueReport report = mapper.mapBenDrugIssueReport(exitReport());

		assertEquals(CREATED, report.getDate());
		assertEquals("Jane Doe", report.getBeneficiaryName());
		assertEquals("Female", report.getGender());
		assertEquals(34, report.getAge());
		assertEquals("Paracetamol", report.getItemName());
		assertEquals("Analgesic", report.getItemCategory());
		assertEquals("B-1", report.getBatchNo());
		assertEquals(EXPIRY, report.getExpiryDate());
		assertEquals("500mg", report.getStrength());
		assertEquals(6, report.getDispensedQuantity());
	}

	@Test
	@DisplayName("mapBenDrugIssueReport should return null for a null exit report")
	void mapBenDrugIssueReport_shouldReturnNullForNull() {
		assertNull(mapper.mapBenDrugIssueReport(null));
	}
}
