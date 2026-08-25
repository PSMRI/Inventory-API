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
package com.iemr.inventory.mapper.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;
import com.iemr.inventory.data.stockentry.ItemStockEntry;

@DisplayName("StockAdjustmentItemDraftMapper Test Suite")
class StockAdjustmentItemDraftMapperTest {

	private final StockAdjustmentItemDraftMapper mapper = StockAdjustmentItemDraftMapper.INSTANCE;

	private static ItemStockEntry stockEntry(String batchNo, String itemName, Integer inHand) {
		ItemStockEntry entry = new ItemStockEntry();
		entry.setBatchNo(batchNo);
		entry.setQuantityInHand(inHand);
		if (itemName != null) {
			ItemMaster item = new ItemMaster();
			item.setItemName(itemName);
			entry.setItem(item);
		}
		return entry;
	}

	private static StockAdjustmentItemDraft draftItem(Long mapID, boolean deleted, ItemStockEntry entry) {
		StockAdjustmentItemDraft draftItem = new StockAdjustmentItemDraft();
		draftItem.setSADraftItemMapID(mapID);
		draftItem.setItemStockEntryID(601L);
		draftItem.setAdjustedQuantity(4);
		draftItem.setCreatedBy("tester");
		draftItem.setProviderServiceMapID(3);
		draftItem.setIsAdded(true);
		draftItem.setDeleted(deleted);
		draftItem.setReason("damaged");
		draftItem.setItemStockEntry(entry);
		return draftItem;
	}

	private static StockAdjustmentItem adjustmentItem(Long mapID, ItemStockEntry entry) {
		StockAdjustmentItem item = new StockAdjustmentItem();
		item.setSAItemMapID(mapID);
		item.setItemStockEntryID(601L);
		item.setAdjustedQuantity(4);
		item.setCreatedBy("tester");
		item.setProviderServiceMapID(3);
		item.setIsAdded(false);
		item.setDeleted(false);
		item.setReason("expired");
		item.setItemStockEntry(entry);
		return item;
	}

	@Test
	@DisplayName("getStockAdjustmentItemDraftEdit should flatten the batch and item name onto the edit projection")
	void getStockAdjustmentItemDraftEdit_shouldFlattenBatchAndItemName() {
		StockAdjustmentItemDraftEdit edit =
				mapper.getStockAdjustmentItemDraftEdit(draftItem(9L, false, stockEntry("B-1", "Paracetamol", 40)));

		assertEquals(9L, edit.getSADraftItemMapID());
		assertEquals(601L, edit.getItemStockEntryID());
		assertEquals("B-1", edit.getBatchID());
		assertEquals("Paracetamol", edit.getItemName());
		assertEquals(40, edit.getQuantityInHand());
		assertEquals(4, edit.getAdjustedQuantity());
		assertEquals("tester", edit.getCreatedBy());
		assertEquals(3, edit.getProviderServiceMapID());
		assertEquals(Boolean.TRUE, edit.getIsAdded());
		assertEquals("damaged", edit.getReason());
	}

	@Test
	@DisplayName("getStockAdjustmentItemDraftEdit should leave the item name blank when the batch has no item")
	void getStockAdjustmentItemDraftEdit_shouldLeaveItemNameBlankWithoutItem() {
		StockAdjustmentItemDraftEdit edit =
				mapper.getStockAdjustmentItemDraftEdit(draftItem(9L, false, stockEntry("B-1", null, 40)));

		assertEquals("B-1", edit.getBatchID());
		assertNull(edit.getItemName());
	}

	@Test
	@DisplayName("getStockAdjustmentItemDraftEdit should leave the batch fields blank when no batch is attached")
	void getStockAdjustmentItemDraftEdit_shouldLeaveBatchFieldsBlankWithoutBatch() {
		StockAdjustmentItemDraftEdit edit = mapper.getStockAdjustmentItemDraftEdit(draftItem(9L, false, null));

		assertNull(edit.getBatchID());
		assertNull(edit.getQuantityInHand());
	}

	@Test
	@DisplayName("getStockAdjustmentItemDraftEdit should return an empty projection for a null draft item")
	void getStockAdjustmentItemDraftEdit_shouldReturnEmptyProjectionForNull() {
		StockAdjustmentItemDraftEdit edit = mapper.getStockAdjustmentItemDraftEdit(null);

		assertNull(edit.getSADraftItemMapID());
	}

	@Test
	@DisplayName("getStockAdjustmentItemDraftEditList should leave the soft-deleted draft rows out")
	void getStockAdjustmentItemDraftEditList_shouldSkipDeletedRows() {
		List<StockAdjustmentItemDraft> rows = new ArrayList<>(Arrays.asList(
				draftItem(9L, false, stockEntry("B-1", "Paracetamol", 40)),
				draftItem(10L, true, stockEntry("B-2", "Ibuprofen", 10))));

		List<StockAdjustmentItemDraftEdit> result = mapper.getStockAdjustmentItemDraftEditList(rows);

		assertEquals(1, result.size());
		assertEquals(9L, result.get(0).getSADraftItemMapID());
	}

	@Test
	@DisplayName("getStockAdjustmentItemDraftEditList should return nothing for an empty draft")
	void getStockAdjustmentItemDraftEditList_shouldReturnNothingForEmptyDraft() {
		assertTrue(mapper.getStockAdjustmentItemDraftEditList(new ArrayList<>()).isEmpty());
	}

	@Test
	@DisplayName("getStockAdjustmentItemEditList should project every posted adjustment line")
	void getStockAdjustmentItemEditList_shouldProjectEveryLine() {
		List<StockAdjustmentItem> rows = new ArrayList<>(Arrays.asList(
				adjustmentItem(21L, stockEntry("B-1", "Paracetamol", 40)),
				adjustmentItem(22L, null)));

		List<StockAdjustmentItemDraftEdit> result = mapper.getStockAdjustmentItemEditList(rows);

		assertEquals(2, result.size());
		assertEquals(21L, result.get(0).getSAItemMapID());
		assertEquals("Paracetamol", result.get(0).getItemName());
		assertEquals("expired", result.get(0).getReason());
		assertNull(result.get(1).getBatchID());
	}

	@Test
	@DisplayName("mapSADraftItemMapID should read any numeric-looking value as a long, and null as null")
	void mapSADraftItemMapID_shouldReadNumericValues() {
		assertEquals(9L, mapper.mapSADraftItemMapID("9"));
		assertEquals(9L, mapper.mapSADraftItemMapID(9));
		assertNull(mapper.mapSADraftItemMapID(null));
	}

	@Test
	@DisplayName("mapCreatedBy should stringify any value, and leave null as null")
	void mapCreatedBy_shouldStringifyValues() {
		assertEquals("tester", mapper.mapCreatedBy("tester"));
		assertEquals("9", mapper.mapCreatedBy(9));
		assertNull(mapper.mapCreatedBy(null));
	}

	@Test
	@DisplayName("mapCreatedDate should produce a date for any value, and null for null")
	void mapCreatedDate_shouldProduceDateForAnyValue() {
		assertTrue(mapper.mapCreatedDate("anything") != null);
		assertNull(mapper.mapCreatedDate(null));
	}

	/**
	 * The MapStruct-generated delegate that sits behind the hand-written decorator. The decorator
	 * overrides every method, so these are the only tests that exercise the generated mapping code.
	 */
	@org.junit.jupiter.api.Nested
	@DisplayName("Generated delegate")
	class GeneratedDelegateTests {

		private final StockAdjustmentItemDraftMapper delegate = new StockAdjustmentItemDraftMapperImpl_();

		@Test
		@DisplayName("the delegate should copy the draft item's own fields onto the edit projection")
		void delegate_shouldCopyDraftFields() {
			StockAdjustmentItemDraftEdit edit =
					delegate.getStockAdjustmentItemDraftEdit(draftItem(9L, false, stockEntry("B-1", "Paracetamol", 40)));

			assertEquals(9L, edit.getSADraftItemMapID());
			assertEquals(601L, edit.getItemStockEntryID());
			assertEquals(4, edit.getAdjustedQuantity());
			assertEquals("tester", edit.getCreatedBy());
			assertEquals(3, edit.getProviderServiceMapID());
			assertEquals("damaged", edit.getReason());
		}

		@Test
		@DisplayName("the delegate should return null for a null draft item")
		void delegate_shouldReturnNullForNullDraftItem() {
			assertNull(delegate.getStockAdjustmentItemDraftEdit(null));
		}

		@Test
		@DisplayName("the delegate should project every draft row, deleted ones included")
		void delegate_shouldProjectEveryDraftRow() {
			List<StockAdjustmentItemDraft> rows = new ArrayList<>(Arrays.asList(
					draftItem(9L, false, null), draftItem(10L, true, null)));

			assertEquals(2, delegate.getStockAdjustmentItemDraftEditList(rows).size());
		}

		@Test
		@DisplayName("the delegate should return null for a null draft list")
		void delegate_shouldReturnNullForNullDraftList() {
			assertNull(delegate.getStockAdjustmentItemDraftEditList(null));
		}

		@Test
		@DisplayName("the delegate should project every posted adjustment line")
		void delegate_shouldProjectEveryAdjustmentLine() {
			List<StockAdjustmentItem> rows = new ArrayList<>(Arrays.asList(
					adjustmentItem(21L, null), adjustmentItem(22L, null)));

			assertEquals(2, delegate.getStockAdjustmentItemEditList(rows).size());
		}

		@Test
		@DisplayName("the delegate should return null for a null adjustment list")
		void delegate_shouldReturnNullForNullAdjustmentList() {
			assertNull(delegate.getStockAdjustmentItemEditList(null));
		}
	}
}
