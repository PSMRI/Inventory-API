/*
* AMRIT – Accessible Medical Records via Integrated Technology 
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iemr.inventory.data.stockadjustment.StockAdjustment;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.mapper.stockadjustment.StockAdjustmentItemDraftMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentDraftRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentItemDraftRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentItemRepo;
import com.iemr.inventory.repo.stockadjustment.StockAdjustmentRepo;
import com.iemr.inventory.utils.exception.InventoryException;

@Service
public class StockAdjustmentServiceImpl implements StockAdjustmentService {

	@Autowired(required=false)
	StockAdjustmentDraftRepo stockAdjustmentDraftRepo;

	@Autowired(required=false)
	StockAdjustmentItemDraftRepo stockAdjustmentItemDraftRepo;

	@Autowired(required=false)
	StockAdjustmentRepo stockAdjustmentRepo;

	@Autowired(required=false)
	StockAdjustmentItemRepo stockAdjustmentItemRepo;

	@Autowired(required=false)
	ItemStockEntryRepo itemStockEntryRepo;

	@Autowired(required=false)
	StockAdjustmentItemDraftMapper stockAdjustmentItemDraftMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StockAdjustmentDraft saveDraft(StockAdjustmentDraft stockAdjustmentDraft) {

		stockAdjustmentDraft.setIsCompleted(false);
		List<StockAdjustmentItemDraft> itemdraft = stockAdjustmentDraft.getStockAdjustmentItemDraft();
		stockAdjustmentDraft.setStockAdjustmentItemDraft(null);
		StockAdjustmentDraft stockdraft = new StockAdjustmentDraft();

		if (stockAdjustmentDraft.getStockAdjustmentDraftID() == null 
			    || (stockAdjustmentDraft.getStockAdjustmentDraftID() instanceof Number 
			        && ((Number) stockAdjustmentDraft.getStockAdjustmentDraftID()).longValue() == 0L)){

			stockdraft = stockAdjustmentDraftRepo.save(stockAdjustmentDraft);

		} else {
			stockAdjustmentDraftRepo.updateStock(stockAdjustmentDraft.getStockAdjustmentDraftID(),
					stockAdjustmentDraft.getDraftDesc(), stockAdjustmentDraft.getDraftName(),
					stockAdjustmentDraft.getRefNo(), stockAdjustmentDraft.getCreatedBy());
			stockdraft = stockAdjustmentDraftRepo.findById(stockAdjustmentDraft.getStockAdjustmentDraftID()).get();
			stockAdjustmentItemDraftRepo.updateDeleted(stockAdjustmentDraft.getStockAdjustmentDraftID());
		}

		Long stockdraftid = stockdraft.getStockAdjustmentDraftID();
		itemdraft.parallelStream().forEach(action -> {
			if (action.getSADraftItemMapID() != null) {
				StockAdjustmentItemDraft stockAdjustmentItemDraft = stockAdjustmentItemDraftRepo
						.findById(action.getSADraftItemMapID()).get();
				action.setDeleted(false);
				action.setModifiedBy(action.getCreatedBy());
				action.setCreatedDate(stockAdjustmentItemDraft.getCreatedDate());
				action.setProcessed(stockAdjustmentItemDraft.getProcessed());
			}
			action.setStockAdjustmentDraftID(stockdraftid);

		});
		itemdraft = (List<StockAdjustmentItemDraft>) stockAdjustmentItemDraftRepo.saveAll(itemdraft);
		stockdraft.setStockAdjustmentItemDraft(itemdraft);
		return stockdraft;
	}

	public List<StockAdjustmentDraft> getStockAjustmentDraftTransaction(ItemStockEntryinput itemStockEntryinput) {
		List<StockAdjustmentDraft> data = new ArrayList<StockAdjustmentDraft>();
		if (itemStockEntryinput.getFacilityID() != null) {
			if (itemStockEntryinput.getFromDate() != null) {
				Timestamp timestamp = itemStockEntryinput.getFromDate();
				timestamp.setHours(0);
				timestamp.setMinutes(0);
				itemStockEntryinput.setFromDate(timestamp);
				if (itemStockEntryinput.getToDate() != null) {
					Timestamp timestamp1 = itemStockEntryinput.getToDate();
					timestamp1.setHours(23);
					timestamp1.setMinutes(59);
					itemStockEntryinput.setToDate(timestamp1);
					data = stockAdjustmentDraftRepo
							.findByIsCompletedAndFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(false,
									itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
									itemStockEntryinput.getToDate());
				}
			}
		}

		return data;
	}

	public StockAdjustmentDraft getforeditStockAjustmentDraftTransaction(Long stockAdjustmentDraftID) {
		StockAdjustmentDraft stock = stockAdjustmentDraftRepo.getforedit(stockAdjustmentDraftID);
		List<StockAdjustmentItemDraftEdit> s = stockAdjustmentItemDraftMapper
				.getStockAdjustmentItemDraftEditList(stock.getStockAdjustmentItemDraft());
		stock.setStockAdjustmentItemDraftEdit(s);
		stock.setStockAdjustmentItemDraft(null);
		return stock;
	}

	// An identical adjustment for the same facility within this many seconds is treated
	// as a repeated submit (double click / retry) of one already saved.
	private static final int DUPLICATE_WINDOW_SECONDS = 120;

	private static final String ALREADY_SAVED = "This stock adjustment has already been saved";

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public StockAdjustment savetransaction(StockAdjustment stockAdjustment) throws InventoryException {
		List<StockAdjustmentItem> sd = stockAdjustment.getStockAdjustmentItem();
		if (sd == null || sd.isEmpty())
			throw new InventoryException("No item found to adjust");
		stockAdjustment.setStockAdjustmentItem(null);

		final Integer facID = stockAdjustment.getFacilityID();
		List<Long> entryIDs = new ArrayList<>();
		for (StockAdjustmentItem action : sd) {
			action.setFacilityID(facID);
			action.setSyncFacilityID(facID);
			entryIDs.add(action.getItemStockEntryID());
		}

		// Lock the batches first, so a repeated submit of this adjustment waits until this
		// one commits, and then sees it in the checks below.
		itemStockEntryRepo.lockForUpdate(entryIDs);

		// A draft can be turned into an adjustment only once.
		if (stockAdjustment.getStockAdjustmentDraftID() != null
				&& stockAdjustmentDraftRepo.markCompletedIfOpen(stockAdjustment.getStockAdjustmentDraftID()) == 0)
			throw new InventoryException(ALREADY_SAVED);

		if (isRepeatOfRecentAdjustment(facID, sd))
			throw new InventoryException(ALREADY_SAVED);

		// Keyed by Long: ItemStockEntry's id is an int, and a Map<Integer, ...> looked up with
		// the item's Long id never matched, so this check never ran before.
		Map<Long, ItemStockEntry> batches = itemStockEntryRepo.findByItemStockEntryIDIn(entryIDs).stream()
				.collect(Collectors.toMap(e -> (long) e.getItemStockEntryID(), Function.identity()));

		for (StockAdjustmentItem action : sd) {
			ItemStockEntry batch = batches.get(action.getItemStockEntryID());
			if (batch == null)
				throw new InventoryException("Batch " + action.getItemStockEntryID() + " not found");
			if (!Boolean.TRUE.equals(action.getIsAdded()) && batch.getQuantityInHand() != null
					&& action.getAdjustedQuantity() > batch.getQuantityInHand())
				throw new InventoryException("Adjustment quantity for issue cannot be more than the available quantity");
		}

		stockAdjustment.setSyncFacilityID(facID);
		stockAdjustmentRepo.save(stockAdjustment);
		stockAdjustmentRepo.updateVanSerialNo();

		// Sequential, not parallelStream: worker threads do not join this transaction, so
		// their stock updates were not rolled back when a later step failed.
		Long saID = stockAdjustment.getStockAdjustmentID();
		for (StockAdjustmentItem action : sd) {
			action.setStockAdjustmentID(saID);
			int updated = Boolean.TRUE.equals(action.getIsAdded())
					? itemStockEntryRepo.addStock(action.getItemStockEntryID(), action.getAdjustedQuantity())
					: itemStockEntryRepo.subtractStockIfAvailable(action.getItemStockEntryID(),
							action.getAdjustedQuantity());
			if (updated == 0)
				throw new InventoryException("Adjustment quantity for issue cannot be more than the available quantity");
		}

		stockAdjustmentItemRepo.saveAll(sd);
		stockAdjustmentItemRepo.updateVanSerialNo();
		stockAdjustment.setStockAdjustmentItem(sd);
		return stockAdjustment;
	}

	/**
	 * True when the facility already saved an adjustment with exactly these items
	 * (batch, receipt/issue, quantity) within the last DUPLICATE_WINDOW_SECONDS.
	 */
	private boolean isRepeatOfRecentAdjustment(Integer facID, List<StockAdjustmentItem> items) {
		if (facID == null)
			return false;

		List<String> incoming = new ArrayList<>();
		for (StockAdjustmentItem item : items)
			incoming.add(itemKey(item.getItemStockEntryID(), Boolean.TRUE.equals(item.getIsAdded()),
					item.getAdjustedQuantity()));
		Collections.sort(incoming);

		Map<String, List<String>> recent = new HashMap<>();
		for (Object[] row : stockAdjustmentItemRepo.findRecentAdjustmentItems(facID, DUPLICATE_WINDOW_SECONDS))
			recent.computeIfAbsent(String.valueOf(row[0]), k -> new ArrayList<>())
					.add(itemKey(row[1], isTrue(row[2]), row[3]));

		for (List<String> saved : recent.values()) {
			Collections.sort(saved);
			if (saved.equals(incoming))
				return true;
		}
		return false;
	}

	private static String itemKey(Object itemStockEntryID, boolean isAdded, Object quantity) {
		return itemStockEntryID + "|" + isAdded + "|" + quantity;
	}

	// IsAdded comes back from the native query as Boolean, a number or a BIT byte array.
	private static boolean isTrue(Object value) {
		if (value instanceof Boolean)
			return (Boolean) value;
		if (value instanceof Number)
			return ((Number) value).intValue() == 1;
		if (value instanceof byte[])
			return ((byte[]) value).length > 0 && ((byte[]) value)[0] == 1;
		return value != null && ("1".equals(value.toString()) || "true".equalsIgnoreCase(value.toString()));
	}

	public StockAdjustment getforeditStockAjustmentTransaction(Long stockAdjustmentID) {
		StockAdjustment stock = stockAdjustmentRepo.findById(stockAdjustmentID).get();
		
		stock.setStockAdjustmentItem(stockAdjustmentItemRepo.findByStockAdjustmentIDAndSyncFacilityID(stock.getVanSerialNo(),stock.getSyncFacilityID()));
		
		List<StockAdjustmentItemDraftEdit> s = stockAdjustmentItemDraftMapper
				.getStockAdjustmentItemEditList(stock.getStockAdjustmentItem());
		stock.setStockAdjustmentItemDraftEdit(s);
		stock.setStockAdjustmentItem(null);
		return stock;
	}

	public List<StockAdjustment> getStockAjustmentTransaction(ItemStockEntryinput itemStockEntryinput) {
		List<StockAdjustment> data = new ArrayList<StockAdjustment>();
		if (itemStockEntryinput.getFacilityID() != null) {
			if (itemStockEntryinput.getFromDate() != null) {
				Timestamp timestamp = itemStockEntryinput.getFromDate();
				timestamp.setHours(0);
				timestamp.setMinutes(0);
				itemStockEntryinput.setFromDate(timestamp);
				if (itemStockEntryinput.getToDate() != null) {
					Timestamp timestamp1 = itemStockEntryinput.getToDate();
					timestamp1.setHours(23);
					timestamp1.setMinutes(59);
					itemStockEntryinput.setToDate(timestamp1);
					data = stockAdjustmentRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
							itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
							itemStockEntryinput.getToDate());
				}
			}
		}

		return data;
	}
}
