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
package com.iemr.inventory.mapper.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemBatchList;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;

@DisplayName("Stock exit mapper Test Suite")
class StockExitMapperTest {

	private static final Date EXPIRY = Date.valueOf("2026-01-31");

	private static ItemStockEntry stockEntry() {
		ItemMaster item = new ItemMaster();
		item.setItemName("Paracetamol");
		item.setItemID(11);

		ItemStockEntry entry = new ItemStockEntry();
		entry.setItemStockEntryID(601);
		entry.setFacilityID(7);
		entry.setItemID(11);
		entry.setBatchNo("B-1");
		entry.setExpiryDate(EXPIRY);
		entry.setQuantity(100);
		entry.setQuantityInHand(40);
		entry.setCreatedBy("tester");
		entry.setDeleted(false);
		entry.setItem(item);
		return entry;
	}

	private static ItemStockExit stockExit() {
		ItemStockExit exit = new ItemStockExit();
		exit.setItemStockExitID(501L);
		exit.setItemStockEntryID(601L);
		exit.setFacilityID(7);
		exit.setItemID(11);
		exit.setQuantity(6);
		exit.setDeleted(false);
		exit.setCreatedBy("tester");
		exit.setItemStockEntry(stockEntry());
		return exit;
	}

	@Nested
	@DisplayName("ItemStockExitMapper")
	class ItemStockExitMapperTests {

		private final ItemStockExitMapper mapper = ItemStockExitMapper.INSTANCE;

		@Test
		@DisplayName("getItemStockExitMap should flatten the batch of the exit onto the projection")
		void getItemStockExitMap_shouldFlattenBatch() {
			ItemStockExitMap result = mapper.getItemStockExitMap(stockExit());

			assertEquals("Paracetamol", result.getItemName());
			assertEquals("B-1", result.getBatchNo());
			assertEquals(EXPIRY, result.getExpiryDate());
			assertEquals(6, result.getQuantity());
			assertEquals("tester", result.getCreatedBy());
			assertEquals(Boolean.FALSE, result.getDeleted());
		}

		@Test
		@DisplayName("getItemStockExitMap should return null for a null exit")
		void getItemStockExitMap_shouldReturnNullForNull() {
			assertNull(mapper.getItemStockExitMap((ItemStockExit) null));
		}

		@Test
		@DisplayName("getItemStockExitMapList should project every exit in the list")
		void getItemStockExitMapList_shouldProjectEveryExit() {
			List<ItemStockExitMap> result = mapper.getItemStockExitMapList(List.of(stockExit(), stockExit()));

			assertEquals(2, result.size());
			assertEquals("B-1", result.get(1).getBatchNo());
		}

		@Test
		@DisplayName("getItemStockEntryMapList should project an entry row the same way")
		void getItemStockEntryMapList_shouldProjectEntry() {
			ItemStockExitMap result = mapper.getItemStockEntryMapList(stockEntry());

			assertEquals("Paracetamol", result.getItemName());
			assertEquals("B-1", result.getBatchNo());
			assertEquals(100, result.getQuantity());
		}

		@Test
		@DisplayName("getItemStockEntryMapList should project every entry in the list")
		void getItemStockEntryMapList_shouldProjectEveryEntry() {
			assertEquals(2, mapper.getItemStockEntryMapList(List.of(stockEntry(), stockEntry())).size());
		}

		@Test
		@DisplayName("getItemStockEntryMapList should return null for a null list")
		void getItemStockEntryMapList_shouldReturnNullForNullList() {
			assertNull(mapper.getItemStockEntryMapList((List<ItemStockEntry>) null));
		}
	}

	@Nested
	@DisplayName("ItemBatchListMap")
	class ItemBatchListMapTests {

		private final ItemBatchListMap mapper = ItemBatchListMap.INSTANCE;

		@Test
		@DisplayName("getItemStockExitMap should carry the batch identity and both quantities across")
		void getItemStockExitMap_shouldCarryBatchIdentity() {
			ItemBatchList result = mapper.getItemStockExitMap(stockEntry());

			assertEquals(601L, result.getItemStockEntryID());
			assertEquals(7, result.getFacilityID());
			assertEquals(11, result.getItemID());
			assertEquals(100, result.getQuantity());
			assertEquals(40, result.getQuantityInHand());
			assertEquals("B-1", result.getBatchNo());
			assertEquals(EXPIRY, result.getExpiryDate());
		}

		@Test
		@DisplayName("getItemStockExitMap should return null for a null entry")
		void getItemStockExitMap_shouldReturnNullForNull() {
			assertNull(mapper.getItemStockExitMap(null));
		}

		@Test
		@DisplayName("getItemStockExitMapList should project every batch in the list")
		void getItemStockExitMapList_shouldProjectEveryBatch() {
			assertEquals(2, mapper.getItemStockExitMapList(List.of(stockEntry(), stockEntry())).size());
		}

		@Test
		@DisplayName("getItemStockExitMapList should return null for a null list")
		void getItemStockExitMapList_shouldReturnNullForNullList() {
			assertNull(mapper.getItemStockExitMapList(null));
		}
	}

	@Nested
	@DisplayName("ItemMasterWithQuantityMapper")
	class ItemMasterWithQuantityMapperTests {

		private final ItemMasterWithQuantityMapper mapper = ItemMasterWithQuantityMapper.INSTANCE;

		@Test
		@DisplayName("getItemStockExitMap should carry the batch identity and the item across")
		void getItemStockExitMap_shouldCarryItem() {
			ItemMasterWithQuantityMap result = mapper.getItemStockExitMap(stockEntry());

			assertEquals(601L, result.getItemStockEntryID());
			assertEquals(7, result.getFacilityID());
			assertEquals(11, result.getItemID());
			assertEquals(40, result.getQuantityInHand());
			assertEquals("Paracetamol", result.getItem().getItemName());
		}

		@Test
		@DisplayName("getItemStockExitMap should return null for a null entry")
		void getItemStockExitMap_shouldReturnNullForNull() {
			assertNull(mapper.getItemStockExitMap(null));
		}

		@Test
		@DisplayName("getItemStockExitMapList should project every entry in the list")
		void getItemStockExitMapList_shouldProjectEveryEntry() {
			assertEquals(2, mapper.getItemStockExitMapList(List.of(stockEntry(), stockEntry())).size());
		}

		@Test
		@DisplayName("getItemStockExitMapList should return null for a null list")
		void getItemStockExitMapList_shouldReturnNullForNullList() {
			assertNull(mapper.getItemStockExitMapList(null));
		}
	}

	@Nested
	@DisplayName("AllocateItemMapper")
	class AllocateItemMapperTests {

		private final AllocateItemMapper mapper = AllocateItemMapper.INSTANCE;

		@Test
		@DisplayName("getItemStockExitMap should carry the facility and item across")
		void getItemStockExitMap_shouldCarryFacilityAndItem() {
			AllocateItemMap result = mapper.getItemStockExitMap(stockExit());

			assertEquals(7, result.getFacilityID());
			assertEquals(11, result.getItemID());
		}

		@Test
		@DisplayName("getItemStockExitMap should return null for a null exit")
		void getItemStockExitMap_shouldReturnNullForNull() {
			assertNull(mapper.getItemStockExitMap(null));
		}

		@Test
		@DisplayName("getItemStockExitMapList should project every exit in the list")
		void getItemStockExitMapList_shouldProjectEveryExit() {
			assertEquals(2, mapper.getItemStockExitMapList(List.of(stockExit(), stockExit())).size());
		}

		@Test
		@DisplayName("getItemStockExitMapList should return null for a null list")
		void getItemStockExitMapList_shouldReturnNullForNullList() {
			assertNull(mapper.getItemStockExitMapList(null));
		}
	}
}
