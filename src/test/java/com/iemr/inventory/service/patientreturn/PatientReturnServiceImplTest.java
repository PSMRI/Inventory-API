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
package com.iemr.inventory.service.patientreturn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.patientreturn.ItemDetailModel;
import com.iemr.inventory.data.patientreturn.PatientReturnModel;
import com.iemr.inventory.data.patientreturn.ReturnHistoryModel;
import com.iemr.inventory.data.stockExit.ItemReturnEntry;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.repository.patientreturn.ItemReturnEntryRepo;
import com.iemr.inventory.repository.patientreturn.PatientReturnRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("PatientReturnServiceImpl Test Suite")
class PatientReturnServiceImplTest {

	private static final Timestamp ISSUE_DATE = Timestamp.valueOf("2025-01-31 10:15:30");

	@Mock
	private PatientReturnRepo patientReturnRepo;
	@Mock
	private ItemReturnEntryRepo itemReturnEntryRepo;

	@InjectMocks
	private PatientReturnServiceImpl service;

	/** ItemDetailModel has no no-arg constructor, so probes go through the all-args one. */
	private static ItemDetailModel probe(Long benRegID, Integer itemID, Integer facilityID) {
		return new ItemDetailModel(itemID, null, null, null, null, null, null, null, null, null, null,
				benRegID, null, facilityID);
	}

	/** Wraps native-query rows, which are Object arrays that List.of() would otherwise flatten. */
	private static List<Object[]> rows(Object[]... values) {
		List<Object[]> list = new ArrayList<>();
		for (Object[] value : values) {
			list.add(value);
		}
		return list;
	}

	@Test
	@DisplayName("getItemNameByRegID should build one item row per result, over a ninety-day look-back")
	void getItemNameByRegID_shouldBuildOneRowPerResult() {
		T_PatientIssue issue = new T_PatientIssue();
		issue.setBenRegID(101L);
		issue.setFacilityID(7);
		when(patientReturnRepo.getItemNameByRegID(eqLong(101L), eqInt(7), any(Timestamp.class)))
				.thenReturn(rows(new Object[] { 101L, 7, 11, "Paracetamol" },
						new Object[] { 101L, 7, 12, "Ibuprofen" }));

		List<PatientReturnModel> result = service.getItemNameByRegID(issue);

		assertEquals(2, result.size());
		assertEquals(101L, result.get(0).getBenRegID());
		assertEquals(7, result.get(0).getFacilityID());
		assertEquals(11, result.get(0).getItemID());
		assertEquals("Ibuprofen", result.get(1).getItemName());
	}

	private static Long eqLong(long value) {
		return org.mockito.ArgumentMatchers.eq(value);
	}

	private static Integer eqInt(int value) {
		return org.mockito.ArgumentMatchers.eq(value);
	}

	@Test
	@DisplayName("getItemNameByRegID should skip an absent or empty result row")
	void getItemNameByRegID_shouldSkipEmptyRow() {
		T_PatientIssue issue = new T_PatientIssue();
		issue.setBenRegID(101L);
		issue.setFacilityID(7);
		List<Object[]> results = rows(new Object[0]);
		results.add(null);
		when(patientReturnRepo.getItemNameByRegID(anyLong(), anyInt(), any(Timestamp.class))).thenReturn(results);

		assertTrue(service.getItemNameByRegID(issue).isEmpty());
	}

	@Test
	@DisplayName("getItemDetailByBen should build the full issue detail out of each result row")
	void getItemDetailByBen_shouldBuildFullIssueDetail() {
		ItemDetailModel probe = probe(101L, 11, 7);
		when(patientReturnRepo.getItemDetailByBen(101L, 11, 7)).thenReturn(rows(new Object[] {
				11, "Paracetamol", "B-1", 20, ISSUE_DATE, Boolean.FALSE, Boolean.FALSE,
				501L, 601L, 701L, 801L, 101L, 3, 7 }));

		List<ItemDetailModel> result = service.getItemDetailByBen(probe);

		assertEquals(1, result.size());
		ItemDetailModel detail = result.get(0);
		assertEquals(11, detail.getItemID());
		assertEquals("Paracetamol", detail.getItemName());
		assertEquals("B-1", detail.getBatchNo());
		assertEquals(20, detail.getIssuedQuantity());
		assertEquals(ISSUE_DATE, detail.getDateofIssue());
		assertEquals(Boolean.FALSE, detail.getDiscontinued());
		assertEquals(501L, detail.getItemStockExitID());
		assertEquals(601L, detail.getItemStockEntryID());
		assertEquals(101L, detail.getBenRegID());
		assertEquals(3, detail.getProviderServiceMapID());
		assertEquals(7, detail.getFacilityID());
	}

	@Test
	@DisplayName("getItemDetailByBen should skip an absent or empty result row")
	void getItemDetailByBen_shouldSkipEmptyRow() {
		ItemDetailModel probe = probe(null, null, null);
		List<Object[]> results = rows(new Object[0]);
		results.add(null);
		when(patientReturnRepo.getItemDetailByBen(null, null, null)).thenReturn(results);

		assertTrue(service.getItemDetailByBen(probe).isEmpty());
	}

	@Test
	@DisplayName("updateQuantityReturned should restock and un-issue each returned line, then log the return")
	void updateQuantityReturned_shouldRestockAndLogReturn() {
		ItemDetailModel returned = probe(101L, 11, 7);
		returned.setReturnQuantity(4);
		returned.setItemStockEntryID(601L);
		returned.setItemStockExitID(501L);
		returned.setProviderServiceMapID(3);
		returned.setVisitID(701L);
		returned.setVisitCode(801L);
		returned.setCreatedBy("tester");

		String result = service.updateQuantityReturned(new ItemDetailModel[] { returned });

		assertEquals("Quantity updated successfully", result);
		verify(patientReturnRepo).updateQuantityReturned(4, 601L);
		verify(patientReturnRepo).updateIssuedQuantity(4, 501L);

		ArgumentCaptor<List<ItemReturnEntry>> captor = ArgumentCaptor.forClass(List.class);
		verify(itemReturnEntryRepo).saveAll(captor.capture());
		ItemReturnEntry entry = captor.getValue().get(0);
		assertEquals(101L, entry.getBenRegID());
		assertEquals(501L, entry.getItemStockExitID());
		assertEquals(7, entry.getFacilityID());
		assertEquals(3, entry.getProviderServiceMapID());
		assertEquals(701L, entry.getVisitID());
		assertEquals(801L, entry.getVisitCode());
		assertEquals("tester", entry.getCreatedBy());
	}

	@Test
	@DisplayName("updateQuantityReturned should still record an empty batch without touching any stock")
	void updateQuantityReturned_shouldRecordEmptyBatch() {
		assertEquals("Quantity updated successfully", service.updateQuantityReturned(new ItemDetailModel[0]));

		verify(itemReturnEntryRepo).saveAll(new ArrayList<ItemReturnEntry>());
	}

	@Test
	@DisplayName("getBenReturnHistory should build a history row for each return in the window")
	void getBenReturnHistory_shouldBuildHistoryRows() {
		ItemReturnEntry probe = new ItemReturnEntry();
		probe.setFacilityID(7);
		probe.setFromDate(Timestamp.valueOf("2025-01-01 00:00:00"));
		probe.setToDate(Timestamp.valueOf("2025-01-31 23:59:00"));
		when(patientReturnRepo.getBenReturnHistory(7, probe.getFromDate(), probe.getToDate()))
				.thenReturn(rows(new Object[] { "Paracetamol", "B-1", 20, ISSUE_DATE, 701L, 801L,
						"Jane Doe", 34, "Female", ISSUE_DATE }));

		List<ReturnHistoryModel> result = service.getBenReturnHistory(probe);

		assertEquals(1, result.size());
		ReturnHistoryModel history = result.get(0);
		assertEquals("Paracetamol", history.getItemName());
		assertEquals("B-1", history.getBatchNo());
		assertEquals(20, history.getIssuedQuantity());
		assertEquals(701L, history.getVisitID());
		assertEquals(801L, history.getVisitCode());
		assertEquals("Jane Doe", history.getPatientName());
		assertEquals(34, history.getAge());
		assertEquals("Female", history.getGender());
	}

	@Test
	@DisplayName("getBenReturnHistory should leave the visit ids null when the return carries none")
	void getBenReturnHistory_shouldLeaveVisitIdsNullWhenAbsent() {
		ItemReturnEntry probe = new ItemReturnEntry();
		probe.setFacilityID(7);
		when(patientReturnRepo.getBenReturnHistory(7, null, null))
				.thenReturn(rows(new Object[] { "Paracetamol", "B-1", 20, ISSUE_DATE, null, null,
						"Jane Doe", 34, "Female", ISSUE_DATE }));

		ReturnHistoryModel history = service.getBenReturnHistory(probe).get(0);

		assertNull(history.getVisitID());
		assertNull(history.getVisitCode());
	}

	@Test
	@DisplayName("getBenReturnHistory should skip an absent or empty result row")
	void getBenReturnHistory_shouldSkipEmptyRow() {
		ItemReturnEntry probe = new ItemReturnEntry();
		List<Object[]> results = rows(new Object[0]);
		results.add(null);
		when(patientReturnRepo.getBenReturnHistory(null, null, null)).thenReturn(results);

		assertTrue(service.getBenReturnHistory(probe).isEmpty());
	}
}
