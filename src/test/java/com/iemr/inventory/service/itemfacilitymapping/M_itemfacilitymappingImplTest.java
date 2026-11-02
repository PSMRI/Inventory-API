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
package com.iemr.inventory.service.itemfacilitymapping;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repository.item.ItemRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;
import com.iemr.inventory.repository.itemfacilitymapping.V_fetchItemFacilityMapRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("M_itemfacilitymappingImpl Test Suite")
class M_itemfacilitymappingImplTest {

	@Mock
	private M_itemfacilitymappingRepo m_itemfacilitymappingRepo;
	@Mock
	private V_fetchItemFacilityMapRepo v_fetchItemFacilityMapRepo;
	@Mock
	private ItemStockEntryRepo itemStockEntryRepo;
	@Mock
	private ItemRepo itemRepo;

	@InjectMocks
	private M_itemfacilitymappingImpl service;

	/** Wraps native-query rows, which are Object arrays that List.of() would otherwise flatten. */
	private static ArrayList<Object[]> rows(Object[]... values) {
		ArrayList<Object[]> list = new ArrayList<>();
		for (Object[] value : values) {
			list.add(value);
		}
		return list;
	}

	@Test
	@DisplayName("mapItemtoStore should save the whole mapping batch in one call")
	void mapItemtoStore_shouldSaveBatch() {
		List<M_itemfacilitymapping> batch = List.of(new M_itemfacilitymapping());
		ArrayList<M_itemfacilitymapping> saved = new ArrayList<>(batch);
		when(m_itemfacilitymappingRepo.saveAll(batch)).thenReturn(saved);

		assertSame(saved, service.mapItemtoStore(batch));
	}

	@Test
	@DisplayName("editdata should unwrap the mapping the repository found")
	void editdata_shouldUnwrapFoundMapping() {
		M_itemfacilitymapping mapping = new M_itemfacilitymapping();
		when(m_itemfacilitymappingRepo.findById(5)).thenReturn(Optional.of(mapping));

		assertSame(mapping, service.editdata(5));
	}

	@Test
	@DisplayName("editdata should throw when no mapping carries that id")
	void editdata_shouldThrowWhenMappingMissing() {
		when(m_itemfacilitymappingRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> service.editdata(99));
	}

	@Test
	@DisplayName("saveEditedItem should persist the edited mapping")
	void saveEditedItem_shouldPersistEditedMapping() {
		M_itemfacilitymapping mapping = new M_itemfacilitymapping();
		when(m_itemfacilitymappingRepo.save(mapping)).thenReturn(mapping);

		assertSame(mapping, service.saveEditedItem(mapping));
	}

	@Test
	@DisplayName("getsubitemforsubStote should build a mapping out of each result-set row")
	void getsubitemforsubStote_shouldBuildMappingPerRow() {
		when(m_itemfacilitymappingRepo.getItemforSubstore(3, 7)).thenReturn(rows(new Object[] { 11, "Paracetamol", Boolean.FALSE, 2 },
				new Object[] { 12, "Ibuprofen", Boolean.TRUE, 3 }));

		ArrayList<M_itemfacilitymapping> result = service.getsubitemforsubStote(3, 7);

		assertEquals(2, result.size());
		assertEquals(11, result.get(0).getItemID());
		assertEquals("Paracetamol", result.get(0).getItemName());
		assertEquals(Boolean.TRUE, result.get(1).getDiscontinued());
		assertEquals(3, result.get(1).getItemCategoryID());
	}

	@Test
	@DisplayName("getsubitemforsubStote should skip a short or absent result-set row")
	void getsubitemforsubStote_shouldSkipShortRow() {
		ArrayList<Object[]> rows = new ArrayList<>();
		rows.add(null);
		rows.add(new Object[] { 11, "Paracetamol" });
		when(m_itemfacilitymappingRepo.getItemforSubstore(3, 7)).thenReturn(rows);

		assertTrue(service.getsubitemforsubStote(3, 7).isEmpty());
	}

	@Test
	@DisplayName("getAllFacilityMappedData should delegate to the mapped-data view repository")
	void getAllFacilityMappedData_shouldDelegate() {
		ArrayList<V_fetchItemFacilityMap> mapped = new ArrayList<>(List.of(new V_fetchItemFacilityMap()));
		when(v_fetchItemFacilityMapRepo.getAllFacilityMappedData(3)).thenReturn(mapped);

		assertSame(mapped, service.getAllFacilityMappedData(3));
	}

	@Test
	@DisplayName("getItemMastersFromStoreID should look the quantities up for every item mapped to the store")
	void getItemMastersFromStoreID_shouldReturnQuantitiesPerItem() {
		when(m_itemfacilitymappingRepo.getItemforStore(7)).thenReturn(rows(new Object[] { 11, "Paracetamol" }, new Object[] { 12, "Ibuprofen" }));
		when(itemStockEntryRepo.getQuantity(any(Integer[].class), eq(7))).thenReturn(rows(new Object[] { 7, 11, "Paracetamol", 40L }));

		List<ItemInStore> result = service.getItemMastersFromStoreID(7);

		assertEquals(1, result.size());
		assertEquals(7, result.get(0).getFacilityID());
		assertEquals(11, result.get(0).getItemID());
		assertEquals("Paracetamol", result.get(0).getItemName());
		assertEquals(40L, result.get(0).getQuantity());

		ArgumentCaptor<Integer[]> captor = ArgumentCaptor.forClass(Integer[].class);
		verify(itemStockEntryRepo).getQuantity(captor.capture(), eq(7));
		assertEquals(2, captor.getValue().length);
	}

	@Test
	@DisplayName("getItemMastersFromStoreID should return nothing when the store has no mapped items")
	void getItemMastersFromStoreID_shouldReturnNothingWhenStoreEmpty() {
		when(m_itemfacilitymappingRepo.getItemforStore(7)).thenReturn(new ArrayList<>());

		assertTrue(service.getItemMastersFromStoreID(7).isEmpty());
		verify(itemStockEntryRepo, never()).getQuantity(any(Integer[].class), anyInt());
	}

	@Test
	@DisplayName("getItemMastersPartialSearch should resolve the matched ids into full item rows")
	void getItemMastersPartialSearch_shouldResolveMatchedIds() {
		when(m_itemfacilitymappingRepo.getItemforStorePartialSearch(7, "Para"))
				.thenReturn(rows(new Object[] { 11, "Paracetamol" }));
		List<ItemMaster> items = List.of(new ItemMaster());
		when(itemRepo.findByItemIDIn(any(Integer[].class))).thenReturn(items);

		assertSame(items, service.getItemMastersPartialSearch("Para", 7));
	}

	@Test
	@DisplayName("getItemMastersPartialSearch should return nothing when the search matches no item")
	void getItemMastersPartialSearch_shouldReturnNothingWhenNoMatch() {
		when(m_itemfacilitymappingRepo.getItemforStorePartialSearch(7, "zzz")).thenReturn(new ArrayList<>());

		assertTrue(service.getItemMastersPartialSearch("zzz", 7).isEmpty());
		verify(itemRepo, never()).findByItemIDIn(any(Integer[].class));
	}

	@Test
	@DisplayName("getItemBatchForStoreTransfer should keep only unexpired batches the receiving store also stocks")
	void getItemBatchForStoreTransfer_shouldNarrowToBatchesBothStoresStock() {
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(1, "Para"))
				.thenReturn(rows(new Object[] { 11, "Paracetamol" }));
		when(m_itemfacilitymappingRepo.getItemforStoreAndItemID(eq(2), any(Integer[].class)))
				.thenReturn(rows(new Object[] { 11, "Paracetamol" }));
		List<ItemStockEntry> batches = List.of(new ItemStockEntry());
		when(itemStockEntryRepo.findByFacilityIDAndItemIDInAndQuantityInHandGreaterThanAndExpiryDateAfter(
				eq(1), any(Integer[].class), eq(0), any(Date.class))).thenReturn(batches);

		assertSame(batches, service.getItemBatchForStoreTransfer(1, 2, "Para"));
	}

	@Test
	@DisplayName("getItemBatchForStoreTransfer should return nothing when the sending store stocks no such item")
	void getItemBatchForStoreTransfer_shouldReturnNothingWhenSendingStoreHasNoMatch() {
		when(m_itemfacilitymappingRepo.getItemforStoreLikeItemName(1, "zzz")).thenReturn(new ArrayList<>());

		assertTrue(service.getItemBatchForStoreTransfer(1, 2, "zzz").isEmpty());
		verify(itemStockEntryRepo, never())
				.findByFacilityIDAndItemIDInAndQuantityInHandGreaterThanAndExpiryDateAfter(
						anyInt(), any(Integer[].class), anyInt(), any(Date.class));
	}
}
