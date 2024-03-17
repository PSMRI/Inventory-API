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
package com.iemr.inventory.mapper.stockadjustment;

import java.util.ArrayList;
import java.util.List;

import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;
import com.iemr.inventory.data.stockentry.ItemStockEntry;

public abstract class StockAdjustmentItemDraftMapperDecorator implements StockAdjustmentItemDraftMapper {

    public StockAdjustmentItemDraftEdit getStockAdjustmentItemDraftEdit(
            StockAdjustmentItemDraft stockAdjustmentItemDraftEdit) {

        StockAdjustmentItemDraftEdit saItemDraftEdit = new StockAdjustmentItemDraftEdit();
        if (stockAdjustmentItemDraftEdit != null) {
            saItemDraftEdit.setSADraftItemMapID(stockAdjustmentItemDraftEdit.getSADraftItemMapID());
            saItemDraftEdit.setItemStockEntryID(stockAdjustmentItemDraftEdit.getItemStockEntryID());
            if (stockAdjustmentItemDraftEdit.getItemStockEntry() != null) {
                ItemStockEntry itemStockEntry = stockAdjustmentItemDraftEdit.getItemStockEntry();
                saItemDraftEdit.setBatchID(itemStockEntry.getBatchNo());
                if (itemStockEntry.getItem() != null) {
                    saItemDraftEdit.setItemName(itemStockEntry.getItem().getItemName());
                }

                saItemDraftEdit.setQuantityInHand(itemStockEntry.getQuantityInHand());
            }

            saItemDraftEdit.setAdjustedQuantity(stockAdjustmentItemDraftEdit.getAdjustedQuantity());
            saItemDraftEdit.setCreatedBy(stockAdjustmentItemDraftEdit.getCreatedBy());
            saItemDraftEdit.setProviderServiceMapID(stockAdjustmentItemDraftEdit.getProviderServiceMapID());
            saItemDraftEdit.setIsAdded(stockAdjustmentItemDraftEdit.getIsAdded());
            saItemDraftEdit.setDeleted(stockAdjustmentItemDraftEdit.getDeleted());
            saItemDraftEdit.setReason(stockAdjustmentItemDraftEdit.getReason());

        }
        return saItemDraftEdit;
    }

    public List<StockAdjustmentItemDraftEdit> getStockAdjustmentItemDraftEditList(
            List<StockAdjustmentItemDraft> stockAdjustmentItemDraftEdit) {

        List<StockAdjustmentItemDraftEdit> stockAdjustmentItemDraftEditlist = new ArrayList<StockAdjustmentItemDraftEdit>();

        stockAdjustmentItemDraftEdit.stream().forEach(action -> {
            if (action != null && !action.getDeleted()) {
                stockAdjustmentItemDraftEditlist.add(getStockAdjustmentItemDraftEdit(action));
            }
        });

        return stockAdjustmentItemDraftEditlist;

    }

    public StockAdjustmentItemDraftEdit getStockAdjustmentItemEdit(StockAdjustmentItem stockAdjustmentItemDraftEdit) {

        StockAdjustmentItemDraftEdit saItemDraftEdit = new StockAdjustmentItemDraftEdit();
        if (stockAdjustmentItemDraftEdit != null) {
            saItemDraftEdit.setSAItemMapID(stockAdjustmentItemDraftEdit.getSAItemMapID());
            saItemDraftEdit.setItemStockEntryID(stockAdjustmentItemDraftEdit.getItemStockEntryID());
            if (stockAdjustmentItemDraftEdit.getItemStockEntry() != null) {
                ItemStockEntry itemStockEntry = stockAdjustmentItemDraftEdit.getItemStockEntry();
                saItemDraftEdit.setBatchID(itemStockEntry.getBatchNo());
                if (itemStockEntry.getItem() != null) {
                    saItemDraftEdit.setItemName(itemStockEntry.getItem().getItemName());
                }

                saItemDraftEdit.setQuantityInHand(itemStockEntry.getQuantityInHand());
            }

            saItemDraftEdit.setAdjustedQuantity(stockAdjustmentItemDraftEdit.getAdjustedQuantity());
            saItemDraftEdit.setCreatedBy(stockAdjustmentItemDraftEdit.getCreatedBy());
            saItemDraftEdit.setProviderServiceMapID(stockAdjustmentItemDraftEdit.getProviderServiceMapID());
            saItemDraftEdit.setIsAdded(stockAdjustmentItemDraftEdit.getIsAdded());
            saItemDraftEdit.setDeleted(stockAdjustmentItemDraftEdit.getDeleted());
            saItemDraftEdit.setReason(stockAdjustmentItemDraftEdit.getReason());
        }
        return saItemDraftEdit;
    }

    public List<StockAdjustmentItemDraftEdit> getStockAdjustmentItemEditList(
            List<StockAdjustmentItem> stockAdjustmentItemDraftEdit) {

        List<StockAdjustmentItemDraftEdit> stockAdjustmentItemDraftEditlist = new ArrayList<StockAdjustmentItemDraftEdit>();

        stockAdjustmentItemDraftEdit.stream().forEach(action -> {
            stockAdjustmentItemDraftEditlist.add(getStockAdjustmentItemEdit(action));
        });

        return stockAdjustmentItemDraftEditlist;

    }
}