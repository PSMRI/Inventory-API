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
package com.iemr.inventory.service.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.inventory.data.stockadjustment.StockAdjustment;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.mapper.stockadjustment.StockAdjustmentItemDraftMapper;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentDraftRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentItemDraftRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentItemRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentRepo;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("StockAdjustmentServiceImpl Test Suite")
class StockAdjustmentServiceImplTest {

	@Mock
	private StockAdjustmentDraftRepo stockAdjustmentDraftRepo;
	@Mock
	private StockAdjustmentItemDraftRepo stockAdjustmentItemDraftRepo;
	@Mock
	private StockAdjustmentRepo stockAdjustmentRepo;
	@Mock
	private StockAdjustmentItemRepo stockAdjustmentItemRepo;
	@Mock
	private ItemStockEntryRepo itemStockEntryRepo;
	@Mock
	private StockAdjustmentItemDraftMapper stockAdjustmentItemDraftMapper;

	@InjectMocks
	private StockAdjustmentServiceImpl service;

	private static StockAdjustmentItemDraft itemDraft(Long mapID) {
		StockAdjustmentItemDraft draftItem = new StockAdjustmentItemDraft();
		draftItem.setSADraftItemMapID(mapID);
		draftItem.setCreatedBy("tester");
		return draftItem;
	}

	private static StockAdjustmentDraft draft(Long draftID, StockAdjustmentItemDraft... items) {
		StockAdjustmentDraft draft = new StockAdjustmentDraft();
		draft.setStockAdjustmentDraftID(draftID);
		draft.setFacilityID(7);
		draft.setStockAdjustmentItemDraft(new ArrayList<>(List.of(items)));
		return draft;
	}

	private static StockAdjustmentItem adjustmentItem(Long stockEntryID, boolean added, Integer quantity) {
		StockAdjustmentItem item = new StockAdjustmentItem();
		item.setItemStockEntryID(stockEntryID);
		item.setIsAdded(added);
		item.setAdjustedQuantity(quantity);
		return item;
	}

	private static ItemStockEntry stockEntry(Integer id, Integer inHand) {
		ItemStockEntry entry = new ItemStockEntry();
		entry.setItemStockEntryID(id);
		entry.setQuantityInHand(inHand);
		return entry;
	}

	private static ItemStockEntryinput window(Integer facilityID, String from, String to) {
		ItemStockEntryinput input = new ItemStockEntryinput();
		input.setFacilityID(facilityID);
		input.setFromDate(from == null ? null : Timestamp.valueOf(from));
		input.setToDate(to == null ? null : Timestamp.valueOf(to));
		return input;
	}

	@Test
	@DisplayName("saveDraft should insert a brand new draft and attach the saved item rows to it")
	void saveDraft_shouldInsertNewDraft() {
		StockAdjustmentDraft request = draft(null, itemDraft(null));
		StockAdjustmentDraft persisted = draft(55L);
		when(stockAdjustmentDraftRepo.save(request)).thenReturn(persisted);
		when(stockAdjustmentItemDraftRepo.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

		StockAdjustmentDraft result = service.saveDraft(request);

		assertSame(persisted, result);
		assertEquals(Boolean.FALSE, request.getIsCompleted());
		assertEquals(1, result.getStockAdjustmentItemDraft().size());
		assertEquals(55L, result.getStockAdjustmentItemDraft().get(0).getStockAdjustmentDraftID());
		verify(stockAdjustmentDraftRepo, never()).updateStock(anyLong(), any(), any(), any(), any());
	}

	@Test
	@DisplayName("saveDraft should treat a zero draft id as a brand new draft")
	void saveDraft_shouldTreatZeroIdAsNewDraft() {
		StockAdjustmentDraft request = draft(0L, itemDraft(null));
		when(stockAdjustmentDraftRepo.save(request)).thenReturn(draft(55L));
		when(stockAdjustmentItemDraftRepo.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

		service.saveDraft(request);

		verify(stockAdjustmentDraftRepo).save(request);
	}

	@Test
	@DisplayName("saveDraft should update an existing draft and soft-delete its previous item rows")
	void saveDraft_shouldUpdateExistingDraft() {
		StockAdjustmentDraft request = draft(55L, itemDraft(null));
		request.setDraftName("a name");
		request.setDraftDesc("a description");
		request.setRefNo("REF-1");
		request.setCreatedBy("tester");
		StockAdjustmentDraft persisted = draft(55L);
		when(stockAdjustmentDraftRepo.findById(55L)).thenReturn(java.util.Optional.of(persisted));
		when(stockAdjustmentItemDraftRepo.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

		service.saveDraft(request);

		verify(stockAdjustmentDraftRepo).updateStock(55L, "a description", "a name", "REF-1", "tester");
		verify(stockAdjustmentItemDraftRepo).updateDeleted(55L);
		verify(stockAdjustmentDraftRepo, never()).save(request);
	}

	@Test
	@DisplayName("saveDraft should revive an existing item row rather than replacing its audit trail")
	void saveDraft_shouldReviveExistingItemRow() {
		StockAdjustmentItemDraft stored = itemDraft(9L);
		stored.setCreatedDate(Timestamp.valueOf("2025-01-31 10:15:30"));
		stored.setProcessed('N');

		StockAdjustmentDraft request = draft(null, itemDraft(9L));
		when(stockAdjustmentDraftRepo.save(request)).thenReturn(draft(55L));
		when(stockAdjustmentItemDraftRepo.findById(9L)).thenReturn(java.util.Optional.of(stored));
		when(stockAdjustmentItemDraftRepo.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

		StockAdjustmentItemDraft saved = service.saveDraft(request).getStockAdjustmentItemDraft().get(0);

		assertEquals(Boolean.FALSE, saved.getDeleted());
		assertEquals("tester", saved.getModifiedBy());
		assertEquals(Timestamp.valueOf("2025-01-31 10:15:30"), saved.getCreatedDate());
		assertEquals('N', saved.getProcessed());
	}

	@Test
	@DisplayName("getStockAjustmentDraftTransaction should widen the window to whole days before querying")
	void getStockAjustmentDraftTransaction_shouldWidenWindowToWholeDays() {
		ItemStockEntryinput input = window(7, "2025-01-01 08:30:00", "2025-01-31 08:30:00");
		List<StockAdjustmentDraft> found = List.of(draft(55L));
		when(stockAdjustmentDraftRepo.findByIsCompletedAndFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				false, 7, Timestamp.valueOf("2025-01-01 00:00:00"), Timestamp.valueOf("2025-01-31 23:59:00")))
				.thenReturn(found);

		assertSame(found, service.getStockAjustmentDraftTransaction(input));
	}

	@Test
	@DisplayName("getStockAjustmentDraftTransaction should return nothing when the facility is missing")
	void getStockAjustmentDraftTransaction_shouldReturnNothingWithoutFacility() {
		assertTrue(service.getStockAjustmentDraftTransaction(
				window(null, "2025-01-01 08:30:00", "2025-01-31 08:30:00")).isEmpty());
	}

	@Test
	@DisplayName("getStockAjustmentDraftTransaction should return nothing when the from date is missing")
	void getStockAjustmentDraftTransaction_shouldReturnNothingWithoutFromDate() {
		assertTrue(service.getStockAjustmentDraftTransaction(window(7, null, "2025-01-31 08:30:00")).isEmpty());
	}

	@Test
	@DisplayName("getStockAjustmentDraftTransaction should return nothing when the to date is missing")
	void getStockAjustmentDraftTransaction_shouldReturnNothingWithoutToDate() {
		assertTrue(service.getStockAjustmentDraftTransaction(window(7, "2025-01-01 08:30:00", null)).isEmpty());
	}

	@Test
	@DisplayName("getforeditStockAjustmentDraftTransaction should swap the item rows for their edit projections")
	void getforeditStockAjustmentDraftTransaction_shouldSwapItemRowsForEditProjections() {
		StockAdjustmentDraft stored = draft(55L, itemDraft(9L));
		List<StockAdjustmentItemDraftEdit> projections = List.of(new StockAdjustmentItemDraftEdit());
		when(stockAdjustmentDraftRepo.getforedit(55L)).thenReturn(stored);
		when(stockAdjustmentItemDraftMapper.getStockAdjustmentItemDraftEditList(anyList())).thenReturn(projections);

		StockAdjustmentDraft result = service.getforeditStockAjustmentDraftTransaction(55L);

		assertSame(projections, result.getStockAdjustmentItemDraftEdit());
		assertNull(result.getStockAdjustmentItemDraft());
	}

	@Test
	@DisplayName("savetransaction should add stock for an addition and subtract it for an issue")
	void savetransaction_shouldAddAndSubtractStock() throws Exception {
		StockAdjustment adjustment = new StockAdjustment();
		adjustment.setFacilityID(7);
		adjustment.setStockAdjustmentID(88L);
		adjustment.setStockAdjustmentItem(new ArrayList<>(List.of(
				adjustmentItem(101L, true, 5), adjustmentItem(102L, false, 3))));
		when(itemStockEntryRepo.findByItemStockEntryIDIn(anyList()))
				.thenReturn(List.of(stockEntry(101, 50), stockEntry(102, 40)));

		StockAdjustment result = service.savetransaction(adjustment);

		assertEquals(7, result.getSyncFacilityID());
		assertEquals(2, result.getStockAdjustmentItem().size());
		verify(itemStockEntryRepo).addStock(101L, 5);
		verify(itemStockEntryRepo).subtractStock(102L, 3);
		verify(stockAdjustmentRepo).updateVanSerialNo();
		verify(stockAdjustmentItemRepo).updateVanSerialNo();
	}

	@Test
	@DisplayName("savetransaction does NOT currently stop an issue larger than the batch holds")
	void savetransaction_doesNotStopIssueBeyondAvailableStock() throws Exception {
		// The over-issue guard looks the batch up in a map keyed by ItemStockEntry.getItemStockEntryID(),
		// which is an Integer, using StockAdjustmentItem.getItemStockEntryID(), which is a Long. Those
		// keys never match, so the lookup always yields null and the guard never fires. This test pins
		// the behaviour as it stands today; fixing the key mismatch should make it start throwing.
		StockAdjustment adjustment = new StockAdjustment();
		adjustment.setFacilityID(7);
		adjustment.setStockAdjustmentID(88L);
		adjustment.setStockAdjustmentItem(new ArrayList<>(List.of(adjustmentItem(101L, false, 500))));
		when(itemStockEntryRepo.findByItemStockEntryIDIn(anyList())).thenReturn(List.of(stockEntry(101, 50)));

		service.savetransaction(adjustment);

		verify(itemStockEntryRepo).subtractStock(101L, 500);
	}

	@Test
	@DisplayName("savetransaction should close the originating draft once the adjustment is posted")
	void savetransaction_shouldCloseOriginatingDraft() throws Exception {
		StockAdjustment adjustment = new StockAdjustment();
		adjustment.setFacilityID(7);
		adjustment.setStockAdjustmentID(88L);
		adjustment.setStockAdjustmentDraftID(55L);
		adjustment.setStockAdjustmentItem(new ArrayList<>(List.of(adjustmentItem(101L, true, 5))));
		when(itemStockEntryRepo.findByItemStockEntryIDIn(anyList())).thenReturn(List.of(stockEntry(101, 50)));

		service.savetransaction(adjustment);

		verify(stockAdjustmentDraftRepo).updatecompleted(55L, true);
	}

	@Test
	@DisplayName("savetransaction should not close any draft when the adjustment did not come from one")
	void savetransaction_shouldNotCloseDraftWhenNoneSupplied() throws Exception {
		StockAdjustment adjustment = new StockAdjustment();
		adjustment.setFacilityID(7);
		adjustment.setStockAdjustmentID(88L);
		adjustment.setStockAdjustmentItem(new ArrayList<>(List.of(adjustmentItem(101L, true, 5))));
		when(itemStockEntryRepo.findByItemStockEntryIDIn(anyList())).thenReturn(List.of(stockEntry(101, 50)));

		service.savetransaction(adjustment);

		verify(stockAdjustmentDraftRepo, never()).updatecompleted(anyLong(), any(Boolean.class));
	}

	@Test
	@DisplayName("getforeditStockAjustmentTransaction should swap the item rows for their edit projections")
	void getforeditStockAjustmentTransaction_shouldSwapItemRowsForEditProjections() {
		StockAdjustment stored = new StockAdjustment();
		stored.setVanSerialNo(88L);
		stored.setSyncFacilityID(7);
		List<StockAdjustmentItem> items = List.of(adjustmentItem(101L, true, 5));
		List<StockAdjustmentItemDraftEdit> projections = List.of(new StockAdjustmentItemDraftEdit());
		when(stockAdjustmentRepo.findById(88L)).thenReturn(java.util.Optional.of(stored));
		when(stockAdjustmentItemRepo.findByStockAdjustmentIDAndSyncFacilityID(88L, 7)).thenReturn(items);
		when(stockAdjustmentItemDraftMapper.getStockAdjustmentItemEditList(items)).thenReturn(projections);

		StockAdjustment result = service.getforeditStockAjustmentTransaction(88L);

		assertSame(projections, result.getStockAdjustmentItemDraftEdit());
		assertNull(result.getStockAdjustmentItem());
	}

	@Test
	@DisplayName("getStockAjustmentTransaction should widen the window to whole days before querying")
	void getStockAjustmentTransaction_shouldWidenWindowToWholeDays() {
		ItemStockEntryinput input = window(7, "2025-01-01 08:30:00", "2025-01-31 08:30:00");
		List<StockAdjustment> found = List.of(new StockAdjustment());
		when(stockAdjustmentRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				7, Timestamp.valueOf("2025-01-01 00:00:00"), Timestamp.valueOf("2025-01-31 23:59:00")))
				.thenReturn(found);

		assertSame(found, service.getStockAjustmentTransaction(input));
	}

	@Test
	@DisplayName("getStockAjustmentTransaction should return nothing when the window is incomplete")
	void getStockAjustmentTransaction_shouldReturnNothingForIncompleteWindow() {
		assertTrue(service.getStockAjustmentTransaction(window(null, null, null)).isEmpty());
		assertTrue(service.getStockAjustmentTransaction(window(7, null, null)).isEmpty());
		assertTrue(service.getStockAjustmentTransaction(window(7, "2025-01-01 08:30:00", null)).isEmpty());
	}
}
