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

    @Autowired(required = false)
    StockAdjustmentDraftRepo stockAdjustmentDraftRepo;

    @Autowired(required = false)
    StockAdjustmentItemDraftRepo stockAdjustmentItemDraftRepo;

    @Autowired(required = false)
    StockAdjustmentRepo stockAdjustmentRepo;

    @Autowired(required = false)
    StockAdjustmentItemRepo stockAdjustmentItemRepo;

    @Autowired(required = false)
    ItemStockEntryRepo itemStockEntryRepo;

    @Autowired(required = false)
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
                && ((Number) stockAdjustmentDraft.getStockAdjustmentDraftID()).longValue() == 0L)) {

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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public StockAdjustment savetransaction(StockAdjustment stockAdjustment) throws InventoryException {
        List<StockAdjustmentItem> sd = stockAdjustment.getStockAdjustmentItem();
        stockAdjustment.setStockAdjustmentItem(null);

        List<Long> comapreid = new ArrayList<>();
        final Integer facID = stockAdjustment.getFacilityID();
        sd.parallelStream().forEach(action -> {
            comapreid.add(action.getItemStockEntryID());
            action.setFacilityID(facID);
        });

        List<ItemStockEntry> compareobject = itemStockEntryRepo.findByItemStockEntryIDIn(comapreid);

        Map<Long, ItemStockEntry> result = compareobject.stream()
                .collect(Collectors.toMap(ItemStockEntry::getItemStockEntryID, Function.identity()));

        for (StockAdjustmentItem action : sd) {
            action.setSyncFacilityID(facID);
            ItemStockEntry itemStockEntry = result.get(action.getItemStockEntryID());

            if (!action.getIsAdded()) {
                if (action.getAdjustedQuantity() > itemStockEntry.getQuantityInHand()) {
                    throw new InventoryException(
                            "Adjustment Quantity for issue should be more than available quantity");
                }
            }
        }

        stockAdjustment.setSyncFacilityID(facID);
        stockAdjustmentRepo.save(stockAdjustment);
        stockAdjustmentRepo.updateVanSerialNo();

        Long saID = stockAdjustment.getStockAdjustmentID();
        sd.parallelStream().forEach(action -> {
            action.setStockAdjustmentID(saID);
            if (action.getIsAdded()) {
                itemStockEntryRepo.addStock(action.getItemStockEntryID(), action.getAdjustedQuantity());
            } else {
                itemStockEntryRepo.subtractStock(action.getItemStockEntryID(), action.getAdjustedQuantity());
            }

        });

        stockAdjustmentItemRepo.saveAll(sd);
        stockAdjustmentItemRepo.updateVanSerialNo();
        stockAdjustment.setStockAdjustmentItem(sd);
        if (stockAdjustment.getStockAdjustmentDraftID() != null) {
            stockAdjustmentDraftRepo.updatecompleted(stockAdjustment.getStockAdjustmentDraftID(), true);
        }
        return stockAdjustment;
    }

    public StockAdjustment getforeditStockAjustmentTransaction(Long stockAdjustmentID) {
        StockAdjustment stock = stockAdjustmentRepo.findById(stockAdjustmentID).get();

        stock.setStockAdjustmentItem(stockAdjustmentItemRepo.findByStockAdjustmentIDAndSyncFacilityID(stock.getVanSerialNo(), stock.getSyncFacilityID()));

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
