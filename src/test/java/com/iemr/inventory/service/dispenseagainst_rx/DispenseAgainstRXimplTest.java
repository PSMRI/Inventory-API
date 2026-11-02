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
package com.iemr.inventory.service.dispenseagainst_rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.iemr.inventory.repo.dispenseagainst_rx.PrescribedDrugDetailsRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("DispenseAgainstRXimpl Test Suite")
class DispenseAgainstRXimplTest {

	private static final String REQUEST =
			"{\"beneficiaryRegID\":101,\"visitCode\":5001,\"facilityID\":7}";

	@Mock
	private PrescribedDrugDetailsRepo prescribedDrugDetailsRepo;

	@InjectMocks
	private DispenseAgainstRXimpl service;

	/**
	 * Builds one result-set row in the column order the native query returns, with a batch that
	 * expires the given number of days from now and the given quantity in hand.
	 */
	private static Object[] row(Integer drugID, String batchNo, Integer stockEntryID, int qtyInHand,
			int expiresInDays) {
		Timestamp expiry = new Timestamp(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(expiresInDays));
		return new Object[] {
				101L, 5001L, 9001L, drugID,
				"Paracetamol", "Tablet", "500mg", "1", "Oral", "BD",
				"5", "Days", "After food", "with water",
				Timestamp.valueOf("2025-01-31 10:15:30"), "dr.smith",
				stockEntryID, batchNo, qtyInHand, expiry, 10, Boolean.TRUE
		};
	}

	@SuppressWarnings("unchecked")
	private static Map<String, Object> parse(String json) {
		return new Gson().fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());
	}

	@SuppressWarnings("unchecked")
	private static List<Map<String, Object>> itemList(Map<String, Object> response) {
		return (List<Map<String, Object>>) response.get("itemList");
	}

	@SuppressWarnings("unchecked")
	private static List<Map<String, Object>> batchList(Map<String, Object> item) {
		return (List<Map<String, Object>>) item.get("batchList");
	}

	private void givenRows(Object[]... rows) {
		when(prescribedDrugDetailsRepo.getPrescribedMedicinesWithDetails(101L, 5001L, 7))
				.thenReturn(new ArrayList<>(List.of(rows)));
	}

	@Test
	@DisplayName("getPrescribedMedicines should return null when the request cannot be read as a utility object")
	void getPrescribedMedicines_shouldReturnNullForUnreadableRequest() {
		assertNull(service.getPrescribedMedicines("null"));
	}

	@Test
	@DisplayName("getPrescribedMedicines should report the prescription header from the first row")
	void getPrescribedMedicines_shouldReportPrescriptionHeader() {
		givenRows(row(1, "B-1", 501, 20, 30));

		Map<String, Object> response = parse(service.getPrescribedMedicines(REQUEST));

		assertEquals(9001.0, response.get("prescriptionID"));
		assertEquals(101.0, response.get("beneficiaryRegID"));
		assertEquals(5001.0, response.get("visitCode"));
		assertEquals("dr.smith", response.get("consultantName"));
	}

	@Test
	@DisplayName("getPrescribedMedicines should describe the prescribed drug alongside its dispensable batch")
	void getPrescribedMedicines_shouldDescribeDrugAndBatch() {
		givenRows(row(1, "B-1", 501, 20, 30));

		Map<String, Object> item = itemList(parse(service.getPrescribedMedicines(REQUEST))).get(0);

		assertEquals(1.0, item.get("drugID"));
		assertEquals("Paracetamol", item.get("genericDrugName"));
		assertEquals("Tablet", item.get("drugForm"));
		assertEquals("500mg", item.get("drugStrength"));
		assertEquals("Oral", item.get("route"));
		assertEquals("BD", item.get("frequency"));
		assertEquals("Days", item.get("durationUnit"));
		assertEquals("with water", item.get("specialInstruction"));
		assertEquals(10.0, item.get("qtyPrescribed"));
		assertEquals(Boolean.TRUE, item.get("isEDL"));

		Map<String, Object> batch = batchList(item).get(0);
		assertEquals("B-1", batch.get("batchNo"));
		assertEquals(501.0, batch.get("itemStockEntryID"));
		assertEquals(20.0, batch.get("qty"));
	}

	@Test
	@DisplayName("getPrescribedMedicines should collect several batches of the same drug under one item")
	void getPrescribedMedicines_shouldCollectSeveralBatchesUnderOneItem() {
		givenRows(row(1, "B-1", 501, 20, 30), row(1, "B-2", 502, 5, 60));

		List<Map<String, Object>> items = itemList(parse(service.getPrescribedMedicines(REQUEST)));

		assertEquals(1, items.size());
		assertEquals(2, batchList(items.get(0)).size());
	}

	@Test
	@DisplayName("getPrescribedMedicines should start a new item when the drug changes")
	void getPrescribedMedicines_shouldStartNewItemWhenDrugChanges() {
		givenRows(row(1, "B-1", 501, 20, 30), row(2, "B-9", 502, 8, 45));

		List<Map<String, Object>> items = itemList(parse(service.getPrescribedMedicines(REQUEST)));

		assertEquals(2, items.size());
		assertEquals(1.0, items.get(0).get("drugID"));
		assertEquals(2.0, items.get(1).get("drugID"));
	}

	@Test
	@DisplayName("getPrescribedMedicines should leave an expired batch out of the dispensable list")
	void getPrescribedMedicines_shouldExcludeExpiredBatch() {
		givenRows(row(1, "B-old", 501, 20, -10));

		Map<String, Object> item = itemList(parse(service.getPrescribedMedicines(REQUEST))).get(0);

		assertTrue(batchList(item).isEmpty());
	}

	@Test
	@DisplayName("getPrescribedMedicines should leave an exhausted batch out of the dispensable list")
	void getPrescribedMedicines_shouldExcludeExhaustedBatch() {
		givenRows(row(1, "B-empty", 501, 0, 30));

		Map<String, Object> item = itemList(parse(service.getPrescribedMedicines(REQUEST))).get(0);

		assertTrue(batchList(item).isEmpty());
	}

	@Test
	@DisplayName("getPrescribedMedicines should return an empty payload when nothing was prescribed")
	void getPrescribedMedicines_shouldReturnEmptyPayloadWhenNothingPrescribed() {
		when(prescribedDrugDetailsRepo.getPrescribedMedicinesWithDetails(101L, 5001L, 7))
				.thenReturn(new ArrayList<>());

		Map<String, Object> response = parse(service.getPrescribedMedicines(REQUEST));

		assertTrue(response.isEmpty());
	}

	@Test
	@DisplayName("getPrescribedMedicines should read a row that carries no stock entry through the short constructor")
	void getPrescribedMedicines_shouldHandleRowWithoutStockEntry() {
		Object[] row = row(1, "B-1", null, 20, 30);
		givenRows(row);

		Map<String, Object> item = itemList(parse(service.getPrescribedMedicines(REQUEST))).get(0);

		assertEquals(1.0, item.get("drugID"));
		assertFalse(item.containsKey("nonexistent"));
	}
}
