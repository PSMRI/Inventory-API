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
package com.iemr.inventory.service.stockEntry;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.utils.exception.InventoryException;

@Service
public interface StockEntryService {

    PhysicalStockEntry savePhysicalStockEntry(PhysicalStockEntry physicalStockEntry) throws Exception;

    List<ItemStockEntry> getItemBatchForStoreID(ItemStockEntry itemStockEntry);

    List<Object[]> getAllItemBatchForStoreID(Integer storeID, Long[] itemStockID);

    Integer updateStocks(List<ItemStockExit> itemissueListUpdated);

    List<ItemStockEntry> getItemStockForStoreIDOrderByEntryDateAsc(Integer facilityID, Integer itemID, Date nowdate);

    List<ItemStockEntry> getItemStockForStoreIDOrderByEntryDateDesc(Integer facilityID, Integer itemID, Date nowdate);

    List<ItemStockEntry> getItemStockForStoreIDOrderByExpiryDateAsc(Integer facilityID, Integer itemID, Date nowdate);

    List<AllocateItemMap> getItemStockFromItemID(Integer facilityID, List<ItemStockExit> itemStockExitList) throws InventoryException;

    List<ItemStockEntry> saveItemStockFromStockTransfer(List<ItemStockExit> itemStock, Long insertID,
                                                        String insertType, Integer facilityFromID, Integer facilityToID, Long tovanID);

    List<ItemStockEntry> getItemMastersPartialSearch(String itemName, Integer facilityID);

    List<ItemStockEntry> getPhysicalStockEntryItems(Long phySEID);

    List<ItemMasterWithQuantityMap> getItemwithQuantityPartialSearch(String itemName, Integer facilityID);
}
