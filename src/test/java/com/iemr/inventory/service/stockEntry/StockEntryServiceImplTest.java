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
package com.iemr.inventory.service.stockEntry;

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
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemBatchList;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.mapper.stockExit.ItemBatchListMap;
import com.iemr.inventory.mapper.stockExit.ItemMasterWithQuantityMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockEntry.PhysicalStockEntryRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;
import com.iemr.inventory.service.item.ItemService;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("StockEntryServiceImpl Test Suite")
class StockEntryServiceImplTest {

	@Mock
	private PhysicalStockEntryRepo physicalStockEntryRepo;
	@Mock
	private ItemStockEntryRepo itemStockEntryRepo;
	@Mock
	private ItemService itemService;
	@Mock
	private M_itemfacilitymappingRepo m_itemfacilitymappingRepo;
	@Mock
	private ItemMasterWithQuantityMapper itemMasterWithQuantityMapper;
	@Mock
	private ItemBatchListMap itemBatchListMap;

	@InjectMocks
	private StockEntryServiceImpl service;

	private static ItemStockEntry entry(Integer id, Integer itemID, String batchNo, Integer inHand) {
		ItemStockEntry entry = new ItemStockEntry();
		if (id != null) {
			entry.setItemStockEntryID(id);
		}
		entry.setItemID(itemID);
		entry.setBatchNo(batchNo);
		entry.setQuantity(inHand);
		entry.setQuantityInHand(inHand);
		return entry;
	}

	private static PhysicalStockEntry physicalEntry(ItemStockEntry... items) {
		PhysicalStockEntry physical = new PhysicalStockEntry();
		physical.setPhyEntryID(77L);
		physical.setFacilityID(7);
		physical.setVanID(4L);
		physical.setParkingPlaceID(2L);
		physical.setCreatedBy("tester");
		physical.setItemStockEntry(new ArrayList<>(List.of(items)));
		return physical;
	}

	private static ItemStockExit exit(Long stockEntryID, Integer itemID, Integer quantity) {
		ItemStockExit exit = new ItemStockExit();
		exit.setItemStockEntryID(stockEntryID);
		exit.setItemID(itemID);
		exit.setQuantity(quantity);
		exit.setCreatedBy("tester");
		return exit;
	}

	private static ItemMaster itemWithIssueType(Integer itemID, String issueType) {
		M_ItemCategory category = new M_ItemCategory();
		category.setIssueType(issueType);

		ItemMaster item = new ItemMaster();
		item.setItemID(itemID);
		item.setItemName("Paracetamol");
		item.setFacilityID(7);
		item.setItemCategory(category);
		return item;
	}

	private static ArrayList<Object[]> rows(Object[]... values) {
		ArrayList<Object[]> list = new ArrayList<>();
		for (Object[] value : values) {
			list.add(value);
		}
		return list;
	}

	@Test
	@DisplayName("savePhysicalStockEntry should stamp the header details onto every batch it books in")
	void savePhysicalStockEntry_shouldStampHeaderOntoBatches() throws Exception {
		PhysicalStockEntry physical = physicalEntry(entry(null, 11, "B-1", 100));

		PhysicalStockEntry result = service.savePhysicalStockEntry(physical);

		ItemStockEntry booked = result.getItemStockEntry().get(0);
		assertEquals(7, result.getSyncFacilityID());
		assertEquals(77L, booked.getEntryTypeID());
		assertEquals("physicalStockEntry", booked.getEntryType());
		assertEquals(100, booked.getQuantityInHand());
		assertEquals(4L, booked.getVanID());
		assertEquals(2L, booked.getParkingPlaceID());
		assertEquals("tester", booked.getCreatedBy());
		assertEquals(7, booked.getSyncFacilityID());
		verify(itemStockEntryRepo).saveAll(physical.getItemStockEntry());
		verify(physicalStockEntryRepo).updatePhysicalStockEntryVanSerialNo();
		verify(itemStockEntryRepo).updateItemStockEntryVanSerialNo();
	}

	@Test
	@DisplayName("savePhysicalStockEntry should refuse a batch the facility already holds")
	void savePhysicalStockEntry_shouldRefuseDuplicateBatch() {
		PhysicalStockEntry physical = physicalEntry(entry(null, 11, "B-1", 100));
		when(itemStockEntryRepo.existsByFacilityIDAndItemIDAndBatchNoAndExpiryDateAndEntryTypeAndDeletedFalse(
				eq(7), eq(11), eq("B-1"), any(), eq("physicalStockEntry"))).thenReturn(true);

		InventoryException ex = assertThrows(InventoryException.class,
				() -> service.savePhysicalStockEntry(physical));
		assertTrue(ex.getMessage().contains("Duplicate stock entry"));
		verify(itemStockEntryRepo, never()).saveAll(anyList());
	}

	@Test
	@DisplayName("savePhysicalStockEntry should check the duplicate against the batch's own facility when it has one")
	void savePhysicalStockEntry_shouldUseBatchFacilityWhenPresent() throws Exception {
		ItemStockEntry batch = entry(null, 11, "B-1", 100);
		batch.setFacilityID(9);
		PhysicalStockEntry physical = physicalEntry(batch);

		service.savePhysicalStockEntry(physical);

		verify(itemStockEntryRepo).existsByFacilityIDAndItemIDAndBatchNoAndExpiryDateAndEntryTypeAndDeletedFalse(
				eq(9), eq(11), eq("B-1"), any(), eq("physicalStockEntry"));
	}

	@Test
	@DisplayName("getItemBatchForStoreID should ask for the live, unexpired batches of the item")
	void getItemBatchForStoreID_shouldAskForLiveBatches() {
		ItemStockEntry probe = entry(null, 11, null, null);
		probe.setFacilityID(7);
		List<ItemStockEntry> batches = List.of(entry(601, 11, "B-1", 40));
		when(itemStockEntryRepo.findByFacilityIDAndItemIDAndQuantityInHandGreaterThanAndDeletedAndExpiryDateAfter(
				eq(7), eq(11), eq(0), eq(false), any(Date.class))).thenReturn(batches);

		assertSame(batches, service.getItemBatchForStoreID(probe));
	}

	@Test
	@DisplayName("getAllItemBatchForStoreID should delegate the quantity roll-up to the repository")
	void getAllItemBatchForStoreID_shouldDelegate() {
		Long[] stockIDs = { 601L };
		ArrayList<Object[]> quantities = rows(new Object[] { 601L, 40L });
		when(itemStockEntryRepo.getQuantityOfStock(stockIDs, 7)).thenReturn(quantities);

		assertSame(quantities, service.getAllItemBatchForStoreID(7, stockIDs));
	}

	@Test
	@DisplayName("updateStocks should total the rows each exit line updated")
	void updateStocks_shouldTotalUpdatedRows() {
		ItemStockExit first = exit(601L, 11, 6);
		first.setFacilityID(7);
		ItemStockExit second = exit(602L, 12, 3);
		second.setFacilityID(7);
		when(itemStockEntryRepo.updateStock(7, 601L, 6)).thenReturn(1);
		when(itemStockEntryRepo.updateStock(7, 602L, 3)).thenReturn(1);

		assertEquals(2, service.updateStocks(List.of(first, second)));
	}

	@Test
	@DisplayName("the three ordered batch lookups should each use their own repository ordering")
	void orderedBatchLookups_shouldUseTheirOwnOrdering() {
		Date now = new Date();
		List<ItemStockEntry> byEntryAsc = List.of(entry(601, 11, "B-1", 40));
		List<ItemStockEntry> byEntryDesc = List.of(entry(602, 11, "B-2", 40));
		List<ItemStockEntry> byExpiryAsc = List.of(entry(603, 11, "B-3", 40));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						7, 11, false, 0, now)).thenReturn(byEntryAsc);
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByDesc(
						7, 11, false, 0, now)).thenReturn(byEntryDesc);
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
						7, 11, false, 0, now)).thenReturn(byExpiryAsc);

		assertSame(byEntryAsc, service.getItemStockForStoreIDOrderByEntryDateAsc(7, 11, now));
		assertSame(byEntryDesc, service.getItemStockForStoreIDOrderByEntryDateDesc(7, 11, now));
		assertSame(byExpiryAsc, service.getItemStockForStoreIDOrderByExpiryDateAsc(7, 11, now));
	}

	private void givenBatchMapperEchoes() {
		when(itemBatchListMap.getItemStockExitMapList(anyList())).thenAnswer(invocation -> {
			List<ItemStockEntry> input = invocation.getArgument(0);
			List<ItemBatchList> mapped = new ArrayList<>();
			for (ItemStockEntry stock : input) {
				ItemBatchList batch = new ItemBatchList();
				batch.setBatchNo(stock.getBatchNo());
				batch.setQuantity(stock.getQuantity());
				batch.setExpiryDate(stock.getExpiryDate());
				mapped.add(batch);
			}
			return mapped;
		});
	}

	@Test
	@DisplayName("getItemStockFromItemID should allocate across batches until the requested quantity is met")
	void getItemStockFromItemID_shouldAllocateAcrossBatches() throws Exception {
		when(itemService.getItemMasterCatByID(11)).thenReturn(itemWithIssueType(11, "First in First Out"));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class)))
				.thenReturn(new ArrayList<>(List.of(entry(601, 11, "B-1", 4), entry(602, 11, "B-2", 10))));
		givenBatchMapperEchoes();

		List<AllocateItemMap> result = service.getItemStockFromItemID(7, List.of(exit(null, 11, 6)));

		assertEquals(1, result.size());
		assertEquals("Paracetamol", result.get(0).getItemName());
		List<ItemBatchList> batches = result.get(0).getItemBatchList();
		assertEquals(2, batches.size());
		assertEquals(4, batches.get(0).getQuantity(), "the first batch is drained");
		assertEquals(2, batches.get(1).getQuantity(), "the second batch covers only the shortfall");
	}

	@Test
	@DisplayName("getItemStockFromItemID should pick the expiry ordering for a first-expiry-first-out item")
	void getItemStockFromItemID_shouldPickExpiryOrdering() throws Exception {
		when(itemService.getItemMasterCatByID(11)).thenReturn(itemWithIssueType(11, "First Expiry First Out"));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class)))
				.thenReturn(new ArrayList<>(List.of(entry(601, 11, "B-1", 40))));
		givenBatchMapperEchoes();

		service.getItemStockFromItemID(7, List.of(exit(null, 11, 6)));

		verify(itemStockEntryRepo)
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class));
	}

	@Test
	@DisplayName("getItemStockFromItemID should pick the newest-first ordering for a last-in-first-out item")
	void getItemStockFromItemID_shouldPickNewestFirstOrdering() throws Exception {
		when(itemService.getItemMasterCatByID(11)).thenReturn(itemWithIssueType(11, "Last in First Out"));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByDesc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class)))
				.thenReturn(new ArrayList<>(List.of(entry(601, 11, "B-1", 40))));
		givenBatchMapperEchoes();

		service.getItemStockFromItemID(7, List.of(exit(null, 11, 6)));

		verify(itemStockEntryRepo)
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByDesc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class));
	}

	@Test
	@DisplayName("getItemStockFromItemID should fall back to the oldest-first ordering for an unconfigured item")
	void getItemStockFromItemID_shouldFallBackToOldestFirst() throws Exception {
		when(itemService.getItemMasterCatByID(11)).thenReturn(itemWithIssueType(11, null));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class)))
				.thenReturn(new ArrayList<>(List.of(entry(601, 11, "B-1", 40))));
		givenBatchMapperEchoes();

		service.getItemStockFromItemID(7, List.of(exit(null, 11, 6)));

		verify(itemStockEntryRepo)
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class));
	}

	@Test
	@DisplayName("getItemStockFromItemID should fall back to the oldest-first ordering for an unknown issue type")
	void getItemStockFromItemID_shouldFallBackForUnknownIssueType() throws Exception {
		when(itemService.getItemMasterCatByID(11)).thenReturn(itemWithIssueType(11, "Something Else"));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class)))
				.thenReturn(new ArrayList<>(List.of(entry(601, 11, "B-1", 40))));
		givenBatchMapperEchoes();

		assertEquals(1, service.getItemStockFromItemID(7, List.of(exit(null, 11, 6))).size());
	}

	@Test
	@DisplayName("getItemStockFromItemID should push the cut-off date out by the requested course duration")
	void getItemStockFromItemID_shouldPushCutOffByCourseDuration() throws Exception {
		when(itemService.getItemMasterCatByID(11)).thenReturn(itemWithIssueType(11, "First in First Out"));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class)))
				.thenReturn(new ArrayList<>(List.of(entry(601, 11, "B-1", 40))));
		givenBatchMapperEchoes();

		for (String unit : new String[] { "Day(s)", "Month(s)", "Week(s)", "Hour(s)" }) {
			ItemStockExit request = exit(null, 11, 6);
			request.setDuration(2);
			request.setDurationUnit(unit);

			assertEquals(1, service.getItemStockFromItemID(7, List.of(request)).size(),
					"a course measured in " + unit + " must still allocate");
		}

		ArgumentCaptor<Date> captor = ArgumentCaptor.forClass(Date.class);
		verify(itemStockEntryRepo, org.mockito.Mockito.atLeastOnce())
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						eq(7), eq(11), eq(false), eq(0), captor.capture());
		assertTrue(captor.getValue().after(new Date(System.currentTimeMillis() - 1000)));
	}

	@Test
	@DisplayName("getItemStockFromItemID should report how many days each allocated batch has left")
	void getItemStockFromItemID_shouldReportDaysToExpiry() throws Exception {
		ItemStockEntry batch = entry(601, 11, "B-1", 40);
		// Half a day past the ten-day mark, so the whole-day division cannot land on nine.
		batch.setExpiryDate(new java.sql.Date(System.currentTimeMillis() + 10L * 86400000L + 43200000L));
		when(itemService.getItemMasterCatByID(11)).thenReturn(itemWithIssueType(11, "First in First Out"));
		when(itemStockEntryRepo
				.findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
						eq(7), eq(11), eq(false), eq(0), any(Date.class)))
				.thenReturn(new ArrayList<>(List.of(batch)));
		givenBatchMapperEchoes();

		ItemBatchList allocated = service.getItemStockFromItemID(7, List.of(exit(null, 11, 6)))
				.get(0).getItemBatchList().get(0);

		assertEquals(10L, allocated.getExpiresIn());
	}

	@Test
	@DisplayName("saveItemStockFromStockTransfer should book the transferred quantity into the receiving store")
	void saveItemStockFromStockTransfer_shouldBookIntoReceivingStore() {
		ItemStockEntry sending = entry(601, 11, "B-1", 40);
		sending.setExpiryDate(java.sql.Date.valueOf("2026-01-31"));
		when(itemStockEntryRepo.findByFacilityIDAndItemStockEntryIDIn(eq(1), any(Long[].class)))
				.thenReturn(List.of(sending));

		List<ItemStockEntry> result = service.saveItemStockFromStockTransfer(
				List.of(exit(601L, 11, 6)), 88L, "stockTransfer", 1, 2, 4L);

		assertEquals(1, result.size());
		ItemStockEntry booked = result.get(0);
		assertEquals(2, booked.getFacilityID());
		assertEquals(6, booked.getQuantity());
		assertEquals(6, booked.getQuantityInHand());
		assertEquals(11, booked.getItemID());
		assertEquals("B-1", booked.getBatchNo());
		assertEquals("stockTransfer", booked.getEntryType());
		assertEquals(88L, booked.getEntryTypeID());
		assertEquals(1, booked.getSyncFacilityID());
		assertEquals(4L, booked.getVanID());
		verify(itemStockEntryRepo).updateItemStockEntryVanSerialNo();
	}

	@Test
	@DisplayName("getPhysicalStockEntry should widen the window to whole days before querying")
	void getPhysicalStockEntry_shouldWidenWindowToWholeDays() {
		ItemStockEntryinput input = new ItemStockEntryinput();
		input.setFacilityID(7);
		input.setFromDate(Timestamp.valueOf("2025-01-01 08:30:00"));
		input.setToDate(Timestamp.valueOf("2025-01-31 08:30:00"));
		List<PhysicalStockEntry> found = List.of(physicalEntry());
		when(physicalStockEntryRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				7, Timestamp.valueOf("2025-01-01 00:00:00"), Timestamp.valueOf("2025-01-31 23:59:00")))
				.thenReturn(found);

		assertSame(found, service.getPhysicalStockEntry(input));
	}

	@Test
	@DisplayName("getPhysicalStockEntry should return nothing when the window is incomplete")
	void getPhysicalStockEntry_shouldReturnNothingForIncompleteWindow() {
		assertTrue(service.getPhysicalStockEntry(new ItemStockEntryinput()).isEmpty());

		ItemStockEntryinput facilityOnly = new ItemStockEntryinput();
		facilityOnly.setFacilityID(7);
		assertTrue(service.getPhysicalStockEntry(facilityOnly).isEmpty());

		ItemStockEntryinput noToDate = new ItemStockEntryinput();
		noToDate.setFacilityID(7);
		noToDate.setFromDate(Timestamp.valueOf("2025-01-01 08:30:00"));
		assertTrue(service.getPhysicalStockEntry(noToDate).isEmpty());
	}

	@Test
	@DisplayName("getItemMastersPartialSearch should resolve the matched ids into batches that still hold stock")
	void getItemMastersPartialSearch_shouldResolveMatchedIds() {
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(7, "Para"))
				.thenReturn(rows(new Object[] { 11, "Paracetamol" }));
		List<ItemStockEntry> batches = List.of(entry(601, 11, "B-1", 40));
		when(itemStockEntryRepo.findByItemIDInAndQuantityInHandGreaterThanAndFacilityID(any(Integer[].class), eq(0), eq(7)))
				.thenReturn(batches);

		assertSame(batches, service.getItemMastersPartialSearch("Para", 7));
	}

	@Test
	@DisplayName("getItemMastersPartialSearch should return nothing when the search matches no item")
	void getItemMastersPartialSearch_shouldReturnNothingWhenNoMatch() {
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(7, "zzz")).thenReturn(rows());

		assertTrue(service.getItemMastersPartialSearch("zzz", 7).isEmpty());
	}

	@Test
	@DisplayName("getPhysicalStockEntryItems should look the booked batches up by the header's sync keys")
	void getPhysicalStockEntryItems_shouldLookUpBySyncKeys() {
		PhysicalStockEntry header = physicalEntry();
		header.setVanSerialNo(99L);
		header.setSyncFacilityID(7);
		List<ItemStockEntry> batches = List.of(entry(601, 11, "B-1", 40));
		when(physicalStockEntryRepo.findById(77L)).thenReturn(Optional.of(header));
		when(itemStockEntryRepo.findByEntryTypeIDAndSyncFacilityIDAndEntryType(99L, 7, "physicalStockEntry"))
				.thenReturn(batches);

		assertSame(batches, service.getPhysicalStockEntryItems(77L));
	}

	@Test
	@DisplayName("getItemwithQuantityPartialSearch should fold the aggregated quantity onto each matched batch")
	void getItemwithQuantityPartialSearch_shouldFoldAggregatedQuantity() {
		ItemStockEntry batch = entry(601, 11, "B-1", 40);
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(7, "Para"))
				.thenReturn(rows(new Object[] { 11, "Paracetamol" }));
		when(itemStockEntryRepo.findByItemIDInQuantityInHandGreaterThanForFacilityID(
				any(Integer[].class), eq(0L), eq(7), any(Date.class)))
				.thenReturn(rows(new Object[] { batch, 55L }));
		List<ItemMasterWithQuantityMap> mapped = List.of(new ItemMasterWithQuantityMap());
		when(itemMasterWithQuantityMapper.getItemStockExitMapList(anyList())).thenReturn(mapped);

		assertSame(mapped, service.getItemwithQuantityPartialSearch("Para", 7));
		assertEquals(55, batch.getQuantityInHand(), "the aggregated quantity replaces the per-row one");
	}

	@Test
	@DisplayName("getItemwithQuantityPartialSearch should map an empty list when the search matches no item")
	void getItemwithQuantityPartialSearch_shouldMapEmptyListWhenNoMatch() {
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(7, "zzz")).thenReturn(rows());
		when(itemMasterWithQuantityMapper.getItemStockExitMapList(anyList())).thenReturn(List.of());

		assertTrue(service.getItemwithQuantityPartialSearch("zzz", 7).isEmpty());
	}

	@Test
	@DisplayName("getItemMastersPartialSearchWithZero should include the batches that hold no stock left")
	void getItemMastersPartialSearchWithZero_shouldIncludeEmptyBatches() {
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(7, "Para"))
				.thenReturn(rows(new Object[] { 11, "Paracetamol" }));
		List<ItemStockEntry> batches = List.of(entry(601, 11, "B-1", 0));
		when(itemStockEntryRepo.findByItemIDInAndFacilityIDOrderByItemStockEntryIDDesc(any(Integer[].class), eq(7)))
				.thenReturn(batches);

		assertSame(batches, service.getItemMastersPartialSearchWithZero("Para", 7));
	}

	@Test
	@DisplayName("getItemMastersPartialSearchWithZero should return nothing when the search matches no item")
	void getItemMastersPartialSearchWithZero_shouldReturnNothingWhenNoMatch() {
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(7, "zzz")).thenReturn(rows());

		assertTrue(service.getItemMastersPartialSearchWithZero("zzz", 7).isEmpty());
	}
}
