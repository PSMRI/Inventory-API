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
package com.iemr.inventory.service.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockExit.StoreSelfConsumption;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.data.stockExit.T_StockTransfer;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.user.M_User;
import com.iemr.inventory.mapper.stockExit.ItemStockExitMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockExit.ItemStockExitRepo;
import com.iemr.inventory.repo.stockExit.PatientIssueRepo;
import com.iemr.inventory.repo.stockExit.StockTransferRepo;
import com.iemr.inventory.repo.stockExit.StoreSelfConsumptionRepo;
import com.iemr.inventory.repo.users.UserLoginRepo;
import com.iemr.inventory.service.item.ItemService;
import com.iemr.inventory.service.stockEntry.StockEntryService;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("StockExitServiceImpl Test Suite")
class StockExitServiceImplTest {

	@Mock
	private StockEntryService stockEntryService;
	@Mock
	private ItemStockExitRepo itemStockExitRepo;
	@Mock
	private ItemStockEntryRepo itemStockEntryRepo;
	@Mock
	private PatientIssueRepo patientIssueRepo;
	@Mock
	private UserLoginRepo userLoginRepo;
	@Mock
	private StoreSelfConsumptionRepo storeSelfConsumptionRepo;
	@Mock
	private StockTransferRepo stockTransferRepo;
	@Mock
	private ItemService itemService;
	@Mock
	private ItemStockExitMapper itemStockExitMapper;

	@InjectMocks
	private StockExitServiceImpl service;

	private static ItemStockExit exitLine(Long stockEntryID, Integer quantity) {
		ItemStockExit exit = new ItemStockExit();
		exit.setItemStockEntryID(stockEntryID);
		exit.setQuantity(quantity);
		return exit;
	}

	/**
	 * Builds a stock-in-hand row in the column order getAllItemBatchForStoreID returns:
	 * index 1 keys back to the requested batch, index 3 is the quantity in hand and index 4
	 * is the batch to draw from.
	 */
	private static Object[] stockRow(Long requestedEntryID, Integer inHand, Long drawFromEntryID) {
		return new Object[] { null, requestedEntryID, null, inHand, drawFromEntryID };
	}

	private static ArrayList<Object[]> rows(Object[]... values) {
		ArrayList<Object[]> list = new ArrayList<>();
		for (Object[] value : values) {
			list.add(value);
		}
		return list;
	}

	private static T_PatientIssue patientIssue(List<ItemStockExit> lines) {
		T_PatientIssue issue = new T_PatientIssue();
		issue.setPatientIssueID(88L);
		issue.setFacilityID(7);
		issue.setBenRegID(101L);
		issue.setVisitCode(5001L);
		issue.setCreatedBy("pharma.user");
		issue.setItemStockExit(lines);
		return issue;
	}

	private static ItemStockEntryinput window(Integer facilityID, String from, String to) {
		ItemStockEntryinput input = new ItemStockEntryinput();
		input.setFacilityID(facilityID);
		input.setFromDate(from == null ? null : Timestamp.valueOf(from));
		input.setToDate(to == null ? null : Timestamp.valueOf(to));
		return input;
	}

	@Test
	@DisplayName("issuePatientDrugs should refuse a request that carries neither lines nor a prescription")
	void issuePatientDrugs_shouldRefuseEmptyRequest() {
		T_PatientIssue issue = patientIssue(new ArrayList<>());

		InventoryException ex = assertThrows(InventoryException.class, () -> service.issuePatientDrugs(issue));
		assertEquals("No item found to dispense.", ex.getMessage());
	}

	@Test
	@DisplayName("issuePatientDrugs should close the visit flow when a prescription is dispensed with no lines")
	void issuePatientDrugs_shouldCloseFlowForEmptyPrescription() throws Exception {
		T_PatientIssue issue = patientIssue(new ArrayList<>());
		issue.setPrescriptionID(9001);
		when(patientIssueRepo.updateBenStatusFlowAfterPharma(101L, 5001L)).thenReturn(1);

		assertEquals(1, service.issuePatientDrugs(issue));
		verify(patientIssueRepo, never()).save(any(T_PatientIssue.class));
	}

	@Test
	@DisplayName("issuePatientDrugs should report failure when the visit flow could not be closed")
	void issuePatientDrugs_shouldReportFailureWhenFlowNotClosed() throws Exception {
		T_PatientIssue issue = patientIssue(new ArrayList<>());
		issue.setPrescriptionID(9001);
		when(patientIssueRepo.updateBenStatusFlowAfterPharma(101L, 5001L)).thenReturn(0);

		assertEquals(0, service.issuePatientDrugs(issue));
	}

	@Test
	@DisplayName("issuePatientDrugs should report failure for a prescription with no beneficiary or visit")
	void issuePatientDrugs_shouldReportFailureWithoutBeneficiary() throws Exception {
		T_PatientIssue issue = patientIssue(new ArrayList<>());
		issue.setPrescriptionID(9001);
		issue.setBenRegID(null);

		assertEquals(0, service.issuePatientDrugs(issue));
	}

	@Test
	@DisplayName("issuePatientDrugs should book the exit lines, close the flow and stamp the pharmacist")
	void issuePatientDrugs_shouldBookLinesAndStampPharmacist() throws Exception {
		T_PatientIssue issue = patientIssue(new ArrayList<>(List.of(exitLine(601L, 6))));
		M_User pharmacist = new M_User();
		pharmacist.setUserID(42);
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));
		when(patientIssueRepo.updateBenStatusFlowAfterPharma(101L, 5001L)).thenReturn(1);
		when(userLoginRepo.getUserByUserName("pharma.user")).thenReturn(pharmacist);

		assertEquals(1, service.issuePatientDrugs(issue));

		assertEquals(7, issue.getSyncFacilityID());
		verify(patientIssueRepo).save(issue);
		verify(patientIssueRepo).updateVanSerialNo();
		verify(itemStockExitRepo).saveAll(anyList());
		verify(stockEntryService).updateStocks(anyList());
		verify(patientIssueRepo).updatePharmacistID(42L, 101L, 5001L);
	}

	@Test
	@DisplayName("issuePatientDrugs should still dispense when the pharmacist username cannot be resolved")
	void issuePatientDrugs_shouldDispenseWithUnresolvablePharmacist() throws Exception {
		T_PatientIssue issue = patientIssue(new ArrayList<>(List.of(exitLine(601L, 6))));
		issue.setCreatedBy("   ");
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));
		when(patientIssueRepo.updateBenStatusFlowAfterPharma(101L, 5001L)).thenReturn(1);

		assertEquals(1, service.issuePatientDrugs(issue));
		verify(patientIssueRepo, never()).updatePharmacistID(anyLong(), anyLong(), anyLong());
	}

	@Test
	@DisplayName("issuePatientDrugs should not stamp a pharmacist that the user store does not know")
	void issuePatientDrugs_shouldNotStampUnknownPharmacist() throws Exception {
		T_PatientIssue issue = patientIssue(new ArrayList<>(List.of(exitLine(601L, 6))));
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));
		when(patientIssueRepo.updateBenStatusFlowAfterPharma(101L, 5001L)).thenReturn(1);
		when(userLoginRepo.getUserByUserName("pharma.user")).thenReturn(null);

		assertEquals(1, service.issuePatientDrugs(issue));
		verify(patientIssueRepo, never()).updatePharmacistID(anyLong(), anyLong(), anyLong());
	}

	@Test
	@DisplayName("issuePatientDrugs should book nothing when a line asks for more than the batch holds")
	void issuePatientDrugs_shouldBookNothingWhenStockShort() throws Exception {
		T_PatientIssue issue = patientIssue(new ArrayList<>(List.of(exitLine(601L, 500))));
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));

		assertEquals(0, service.issuePatientDrugs(issue));
		verify(patientIssueRepo, never()).save(any(T_PatientIssue.class));
	}

	@Test
	@DisplayName("getItemStockAndValidate should stamp the store details onto each dispensable line")
	void getItemStockAndValidate_shouldStampStoreDetails() {
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 610L)));

		List<ItemStockExit> result = service.getItemStockAndValidate(
				new ArrayList<>(List.of(exitLine(601L, 6))), 7, "tester", 4L, 2L);

		assertEquals(1, result.size());
		ItemStockExit line = result.get(0);
		assertEquals(610L, line.getItemStockEntryID(), "the line is redirected to the batch actually holding stock");
		assertEquals(40, line.getQuantityInHand());
		assertEquals("tester", line.getCreatedBy());
		assertEquals(4L, line.getVanID());
		assertEquals(2L, line.getParkingPlaceID());
		assertEquals(7, line.getFacilityID());
		assertEquals(7, line.getSyncFacilityID());
	}

	@Test
	@DisplayName("getItemStockAndValidate should ignore a stock row that matches no requested line")
	void getItemStockAndValidate_shouldIgnoreUnmatchedStockRow() {
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(999L, 40, 999L)));

		assertTrue(service.getItemStockAndValidate(
				new ArrayList<>(List.of(exitLine(601L, 6))), 7, "tester", 4L, 2L).isEmpty());
	}

	@Test
	@DisplayName("saveItemExit should tag every line with the issue type and id before saving")
	void saveItemExit_shouldTagLinesWithIssue() {
		List<ItemStockExit> lines = new ArrayList<>(List.of(exitLine(601L, 6)));

		assertEquals(1, service.saveItemExit(lines, 88L, "T_PatientIssue"));

		assertEquals("T_PatientIssue", lines.get(0).getExitType());
		assertEquals(88L, lines.get(0).getExitTypeID());
		verify(itemStockExitRepo).saveAll(lines);
		verify(itemStockExitRepo).updateVanSerialNo();
		verify(stockEntryService).updateStocks(lines);
	}

	@Test
	@DisplayName("storeSelfConsumption should book the consumption when every line is covered by stock")
	void storeSelfConsumption_shouldBookConsumption() {
		StoreSelfConsumption consumption = new StoreSelfConsumption();
		consumption.setConsumptionID(66L);
		consumption.setFacilityID(7);
		consumption.setCreatedBy("tester");
		consumption.setItemStockExit(new ArrayList<>(List.of(exitLine(601L, 6))));
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));

		assertEquals(1, service.storeSelfConsumption(consumption));

		assertEquals(7, consumption.getSyncFacilityID());
		verify(storeSelfConsumptionRepo).save(consumption);
		verify(storeSelfConsumptionRepo).updateVanSerialNo();
	}

	@Test
	@DisplayName("storeSelfConsumption should book nothing when a line asks for more than the batch holds")
	void storeSelfConsumption_shouldBookNothingWhenStockShort() {
		StoreSelfConsumption consumption = new StoreSelfConsumption();
		consumption.setFacilityID(7);
		consumption.setItemStockExit(new ArrayList<>(List.of(exitLine(601L, 500))));
		when(stockEntryService.getAllItemBatchForStoreID(eq(7), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));

		assertEquals(0, service.storeSelfConsumption(consumption));
		verify(storeSelfConsumptionRepo, never()).save(any(StoreSelfConsumption.class));
	}

	@Test
	@DisplayName("storeTransfer should move the stock out of the sending store and into the receiving one")
	void storeTransfer_shouldMoveStockBetweenStores() {
		T_StockTransfer transfer = new T_StockTransfer();
		transfer.setStockTransferID(99L);
		transfer.setTransferFromFacilityID(1);
		transfer.setTransferToFacilityID(2);
		transfer.setCreatedBy("tester");
		transfer.setItemStockExit(new ArrayList<>(List.of(exitLine(601L, 6))));
		when(stockTransferRepo.findVanIDByFacID(2)).thenReturn(4L);
		when(stockEntryService.getAllItemBatchForStoreID(eq(1), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));

		assertEquals(1, service.storeTransfer(transfer));

		assertEquals(4L, transfer.getToVanID());
		assertEquals(1, transfer.getSyncFacilityID());
		verify(stockTransferRepo).save(transfer);
		verify(stockTransferRepo).updateVanSerialNo();
		verify(stockEntryService).saveItemStockFromStockTransfer(anyList(), eq(99L), eq("T_StockTransfer"),
				eq(1), eq(2), eq(4L));
	}

	@Test
	@DisplayName("storeTransfer should move nothing when a line asks for more than the batch holds")
	void storeTransfer_shouldMoveNothingWhenStockShort() {
		T_StockTransfer transfer = new T_StockTransfer();
		transfer.setTransferFromFacilityID(1);
		transfer.setTransferToFacilityID(2);
		transfer.setItemStockExit(new ArrayList<>(List.of(exitLine(601L, 500))));
		when(stockEntryService.getAllItemBatchForStoreID(eq(1), any(Long[].class)))
				.thenReturn(rows(stockRow(601L, 40, 601L)));

		assertEquals(0, service.storeTransfer(transfer));
		verify(stockTransferRepo, never()).save(any(T_StockTransfer.class));
	}

	@Test
	@DisplayName("getStoreTransfer should widen the window to whole days before querying")
	void getStoreTransfer_shouldWidenWindowToWholeDays() {
		List<T_StockTransfer> found = List.of(new T_StockTransfer());
		Timestamp from = Timestamp.valueOf("2025-01-01 00:00:00");
		Timestamp to = Timestamp.valueOf("2025-01-31 23:59:00");
		when(stockTransferRepo
				.findByCreatedDateBetweenAndTransferFromFacilityIDOrCreatedDateBetweenAndTransferToFacilityIDOrderByCreatedDateDesc(
						from, to, 7, from, to, 7)).thenReturn(found);

		assertSame(found, service.getStoreTransfer(window(7, "2025-01-01 08:30:00", "2025-01-31 08:30:00")));
	}

	@Test
	@DisplayName("getStoreTransfer should return nothing when the window is incomplete")
	void getStoreTransfer_shouldReturnNothingForIncompleteWindow() {
		assertTrue(service.getStoreTransfer(window(null, null, null)).isEmpty());
		assertTrue(service.getStoreTransfer(window(7, null, null)).isEmpty());
		assertTrue(service.getStoreTransfer(window(7, "2025-01-01 08:30:00", null)).isEmpty());
	}

	@Test
	@DisplayName("getpatientIssue should widen the window to whole days before querying")
	void getpatientIssue_shouldWidenWindowToWholeDays() {
		List<T_PatientIssue> found = List.of(new T_PatientIssue());
		when(patientIssueRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				7, Timestamp.valueOf("2025-01-01 00:00:00"), Timestamp.valueOf("2025-01-31 23:59:00")))
				.thenReturn(found);

		assertSame(found, service.getpatientIssue(window(7, "2025-01-01 08:30:00", "2025-01-31 08:30:00")));
	}

	@Test
	@DisplayName("getpatientIssue should return nothing when the window is incomplete")
	void getpatientIssue_shouldReturnNothingForIncompleteWindow() {
		assertTrue(service.getpatientIssue(window(null, null, null)).isEmpty());
		assertTrue(service.getpatientIssue(window(7, null, null)).isEmpty());
		assertTrue(service.getpatientIssue(window(7, "2025-01-01 08:30:00", null)).isEmpty());
	}

	@Test
	@DisplayName("getstoreSelfConsumption should widen the window to whole days before querying")
	void getstoreSelfConsumption_shouldWidenWindowToWholeDays() {
		List<StoreSelfConsumption> found = List.of(new StoreSelfConsumption());
		when(storeSelfConsumptionRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				7, Timestamp.valueOf("2025-01-01 00:00:00"), Timestamp.valueOf("2025-01-31 23:59:00")))
				.thenReturn(found);

		assertSame(found, service.getstoreSelfConsumption(window(7, "2025-01-01 08:30:00", "2025-01-31 08:30:00")));
	}

	@Test
	@DisplayName("getstoreSelfConsumption should return nothing when the window is incomplete")
	void getstoreSelfConsumption_shouldReturnNothingForIncompleteWindow() {
		assertTrue(service.getstoreSelfConsumption(window(null, null, null)).isEmpty());
		assertTrue(service.getstoreSelfConsumption(window(7, null, null)).isEmpty());
		assertTrue(service.getstoreSelfConsumption(window(7, "2025-01-01 08:30:00", null)).isEmpty());
	}

	@Test
	@DisplayName("getstoreSelfConsumptionItemList should project the lines booked under that consumption")
	void getstoreSelfConsumptionItemList_shouldProjectBookedLines() {
		StoreSelfConsumption consumption = new StoreSelfConsumption();
		consumption.setVanSerialNo(66L);
		consumption.setSyncFacilityID(7);
		ItemStockEntryinput input = new ItemStockEntryinput();
		input.setConsumptionID(66L);
		List<ItemStockExitMap> projected = List.of(new ItemStockExitMap());
		List<ItemStockExit> lines = List.of(exitLine(601L, 6));
		when(storeSelfConsumptionRepo.findByConsumptionID(66L)).thenReturn(consumption);
		when(itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(66L, 7, "StoreSelfConsumption"))
				.thenReturn(lines);
		when(itemStockExitMapper.getItemStockExitMapList(lines)).thenReturn(projected);

		assertSame(projected, service.getstoreSelfConsumptionItemList(input));
	}

	@Test
	@DisplayName("getpatientIssueItemLIst should project the lines booked under that patient issue")
	void getpatientIssueItemLIst_shouldProjectBookedLines() {
		T_PatientIssue issue = new T_PatientIssue();
		issue.setVanSerialNo(88L);
		issue.setSyncFacilityID(7);
		ItemStockEntryinput input = new ItemStockEntryinput();
		input.setPatientIssueID(88L);
		List<ItemStockExitMap> projected = List.of(new ItemStockExitMap());
		List<ItemStockExit> lines = List.of(exitLine(601L, 6));
		when(patientIssueRepo.findById(88L)).thenReturn(Optional.of(issue));
		when(itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(88L, 7, "T_PatientIssue"))
				.thenReturn(lines);
		when(itemStockExitMapper.getItemStockExitMapList(lines)).thenReturn(projected);

		assertSame(projected, service.getpatientIssueItemLIst(input));
	}

	@Test
	@DisplayName("getStoreTransferItemEntry should project the batches booked into the receiving store")
	void getStoreTransferItemEntry_shouldProjectReceivedBatches() {
		T_StockTransfer transfer = new T_StockTransfer();
		transfer.setVanSerialNo(99L);
		transfer.setSyncFacilityID(1);
		ItemStockEntryinput input = new ItemStockEntryinput();
		input.setStockTransferID(99L);
		List<ItemStockExitMap> projected = List.of(new ItemStockExitMap());
		List<ItemStockEntry> batches = List.of(new ItemStockEntry());
		when(stockTransferRepo.findByStockTransferID(99L)).thenReturn(transfer);
		when(itemStockEntryRepo.findByEntryTypeIDAndSyncFacilityIDAndEntryType(99L, 1, "T_StockTransfer"))
				.thenReturn(batches);
		when(itemStockExitMapper.getItemStockEntryMapList(batches)).thenReturn(projected);

		assertSame(projected, service.getStoreTransferItemEntry(input));
	}

	@Test
	@DisplayName("getPatientissueAllDetail should attach the dispensed lines to the patient issue it loads")
	void getPatientissueAllDetail_shouldAttachDispensedLines() {
		T_PatientIssue issue = new T_PatientIssue();
		issue.setVanSerialNo(88L);
		issue.setSyncFacilityID(7);
		List<ItemStockExitMap> projected = List.of(new ItemStockExitMap());
		List<ItemStockExit> lines = List.of(exitLine(601L, 6));
		when(patientIssueRepo.findById(88L)).thenReturn(Optional.of(issue));
		when(itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(88L, 7, "T_PatientIssue"))
				.thenReturn(lines);
		when(itemStockExitMapper.getItemStockExitMapList(lines)).thenReturn(projected);

		assertSame(projected, service.getPatientissueAllDetail(88L).getItemStockExitMap());
	}
}
