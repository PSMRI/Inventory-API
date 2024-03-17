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
package com.iemr.inventory.service.itemfacilitymapping;

import java.util.ArrayList;
import java.util.List;

import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockentry.ItemStockEntry;

public interface M_itemfacilitymappingInter {

    ArrayList<M_itemfacilitymapping> mapItemtoStore(List<M_itemfacilitymapping> resList);

    M_itemfacilitymapping editdata(Integer itemStoreMapID);

    M_itemfacilitymapping saveEditedItem(M_itemfacilitymapping getdataforedit);

    ArrayList<M_itemfacilitymapping> getsubitemforsubStote(Integer providerServiceMapID, Integer facilityID);

    ArrayList<V_fetchItemFacilityMap> getAllFacilityMappedData(Integer providerServiceMapID);

    List<ItemInStore> getItemMastersFromStoreID(Integer storeID);

    List<ItemMaster> getItemMastersPartialSearch(String item, Integer storeID);


    List<ItemStockEntry> getItemBatchForStoreTransfer(Integer facFrom, Integer facTo, String item);
}
