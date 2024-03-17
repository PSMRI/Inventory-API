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
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repository.item.ItemRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;
import com.iemr.inventory.repository.itemfacilitymapping.V_fetchItemFacilityMapRepo;

@Service
public class M_itemfacilitymappingImpl implements M_itemfacilitymappingInter {

    @Autowired(required = false)
    ItemStockEntryRepo itemStockEntryRepo;
    @Autowired(required = false)
    ItemRepo itemRepo;
    @Autowired(required = false)
    private V_fetchItemFacilityMapRepo v_fetchItemFacilityMapRepo;
    @Autowired(required = false)
    private M_itemfacilitymappingRepo m_itemfacilitymappingRepo;

    @Override
    public ArrayList<M_itemfacilitymapping> mapItemtoStore(List<M_itemfacilitymapping> resList) {
        ArrayList<M_itemfacilitymapping> data = (ArrayList<M_itemfacilitymapping>) m_itemfacilitymappingRepo
                .saveAll(resList);
        return data;
    }

    @Override
    public M_itemfacilitymapping editdata(Integer itemStoreMapID) {
        M_itemfacilitymapping data = m_itemfacilitymappingRepo.findById(itemStoreMapID).get();
        return data;
    }

    @Override
    public M_itemfacilitymapping saveEditedItem(M_itemfacilitymapping getdataforedit) {
        M_itemfacilitymapping data = m_itemfacilitymappingRepo.save(getdataforedit);
        return data;
    }

    @Override
    public ArrayList<M_itemfacilitymapping> getsubitemforsubStote(Integer providerServiceMapID, Integer facilityID) {

        ArrayList<M_itemfacilitymapping> itemForsubStore = new ArrayList<M_itemfacilitymapping>();
        ArrayList<Object[]> resultSet = m_itemfacilitymappingRepo.getItemforSubstore(providerServiceMapID, facilityID);
        for (Object[] objects : resultSet) {
            if (objects != null && objects.length >= 3) {
                itemForsubStore.add(new M_itemfacilitymapping((Integer) objects[0], (String) objects[1],
                        (Boolean) objects[2], (Integer) objects[3]));
            }

        }
        return itemForsubStore;
    }

    @Override
    public ArrayList<V_fetchItemFacilityMap> getAllFacilityMappedData(Integer providerServiceMapID) {
        ArrayList<V_fetchItemFacilityMap> data = v_fetchItemFacilityMapRepo
                .getAllFacilityMappedData(providerServiceMapID);
        return data;
    }

    @Override
    public List<ItemInStore> getItemMastersFromStoreID(Integer storeID) {
        ArrayList<ItemInStore> itemForsubStore = new ArrayList<ItemInStore>();
        ArrayList<Object[]> resultSet = m_itemfacilitymappingRepo.getItemforStore(storeID);
        if (resultSet.size() > 0) {
            Integer[] itemID = new Integer[resultSet.size()];
            Object[] objects;
            for (int i = 0; i < resultSet.size(); i++) {
                objects = resultSet.get(i);
                if (objects != null && objects.length >= 2) {

                    itemID[i] = (Integer) objects[0];
                }

            }

            ArrayList<Object[]> quant = itemStockEntryRepo.getQuantity(itemID, storeID);

            for (Object[] objects1 : quant) {
                if (objects1 != null && objects1.length >= 2) {
                    itemForsubStore.add(new ItemInStore((Integer) objects1[0], (Integer) objects1[1],
                            (String) objects1[2], (Long) objects1[3]));
                }

            }
        }

        return itemForsubStore;
    }

    ;

    public List<ItemMaster> getItemMastersPartialSearch(String item, Integer storeID) {
        List<ItemMaster> itemForsubStore = new ArrayList<ItemMaster>();
        ArrayList<Object[]> resultSet = m_itemfacilitymappingRepo.getItemforStorePartialSearch(storeID, item);
        if (resultSet.size() > 0) {
            Integer[] itemID = new Integer[resultSet.size()];
            Object[] objects;
            for (int i = 0; i < resultSet.size(); i++) {
                objects = resultSet.get(i);
                if (objects != null && objects.length >= 2) {

                    itemID[i] = (Integer) objects[0];
                }

            }

            itemForsubStore = itemRepo.findByItemIDIn(itemID);

        }

        return itemForsubStore;

    }

    @Override
    public List<ItemStockEntry> getItemBatchForStoreTransfer(Integer facFrom, Integer facTo, String item) {
        List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();
        ArrayList<Object[]> resultSet = m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facFrom, item);
        if (resultSet.size() > 0) {
            Integer[] itemID = new Integer[resultSet.size()];
            Object[] objects;
            for (int i = 0; i < resultSet.size(); i++) {
                objects = resultSet.get(i);
                if (objects != null && objects.length >= 2) {

                    itemID[i] = (Integer) objects[0];
                }

            }
            resultSet = m_itemfacilitymappingRepo.getItemforStoreAndItemID(facTo, itemID);
            itemID = new Integer[resultSet.size()];
            for (int i = 0; i < resultSet.size(); i++) {
                objects = resultSet.get(i);
                if (objects != null && objects.length >= 2) {

                    itemID[i] = (Integer) objects[0];
                }

            }
            itemForsubStore = itemStockEntryRepo
                    .findByFacilityIDAndItemIDInAndQuantityInHandGreaterThanAndExpiryDateAfter(facFrom, itemID, 0,
                            new Date());

        }
        return itemForsubStore;
    }
}
