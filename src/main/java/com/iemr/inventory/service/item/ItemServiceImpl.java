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
package com.iemr.inventory.service.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_ItemForm;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.repository.item.ItemCategoryRepo;
import com.iemr.inventory.repository.item.ItemFormRepo;
import com.iemr.inventory.repository.item.ItemRepo;
import com.iemr.inventory.repository.item.RouteRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired(required = false)
    M_itemfacilitymappingRepo itemfacilitymappingRepo;
    @Autowired(required = false)
    private ItemRepo itemRepo;
    @Autowired(required = false)
    private ItemCategoryRepo itemCategoryRepo;
    @Autowired(required = false)
    private RouteRepo routeRepo;
    @Autowired(required = false)
    private ItemFormRepo itemFormRepo;

    @Override
    public ItemMaster createItemMaster(ItemMaster itemMaster) {
        return itemRepo.save(itemMaster);
    }

    public List<M_ItemCategory> getItemCategory(Boolean all, Integer providerServiceMapID) {
        List<M_ItemCategory> itemCategory = new ArrayList<M_ItemCategory>();
        if (providerServiceMapID != null) {
            if (all) {
                itemCategory = itemCategoryRepo.findByProviderServiceMapID(providerServiceMapID);
            } else {
                itemCategory = itemCategoryRepo.findByDeletedAndProviderServiceMapID(false, providerServiceMapID);
            }
        }
        return itemCategory;
    }

    public List<M_Route> getItemRoute(Boolean all) {

        List<M_Route> route = new ArrayList<M_Route>();

        if (all) {
            route = routeRepo.getAll();
        } else {
            route = routeRepo.findByDeleted(false);
        }

        return route;
    }

    public List<M_ItemForm> getItemForm(Boolean all) {

        List<M_ItemForm> route = new ArrayList<M_ItemForm>();

        if (all) {
            route = itemFormRepo.getAll();
        } else {
            route = itemFormRepo.findByDeleted(false);
        }

        return route;
    }

    public List<ItemMaster> getItemMaster(Integer providerServiceMapID) {

        return itemRepo.findByProviderServiceMapID(providerServiceMapID);

    }

    public Integer blockItemMaster(Integer itemmasterID, Boolean deleteflag) {
        return itemRepo.deleteItemMaster(itemmasterID, deleteflag);

    }

    ;

    public Integer discontinueItemMaster(Integer itemmasterID, Boolean continueflag) {
        return itemRepo.discontinueItemMaster(itemmasterID, continueflag);

    }

    @Override
    public List<ItemMaster> addAllItemMaster(List<ItemMaster> itemMaster) {

        return (List<ItemMaster>) itemRepo.saveAll(itemMaster);
    }

    @Override
    public ItemMaster getItemMasterByID(Integer itemMasterID) {

        return itemRepo.findById(itemMasterID).get();
    }

    public ItemMaster getItemMasterCatByID(Integer itemMasterID) {

        return itemRepo.findDetailOne(itemMasterID);
    }

    @Override
    public Integer updateItemIssueConfig(List<M_ItemCategory> itemCategory) {
        int cnt = 0;
        for (M_ItemCategory m_ItemCategory : itemCategory) {
            if (m_ItemCategory.getItemCategoryID() != null && m_ItemCategory.getIssueType() != null) {
                cnt = cnt + itemCategoryRepo.updateIssueConfig(m_ItemCategory.getItemCategoryID(),
                        m_ItemCategory.getIssueType());
            }
        }
        return cnt;
    }

    @Override
    public List<M_Route> getItemRouteProviderServiceMapID(Integer providerServiceMapID) {
        return routeRepo.findByProviderServiceMapID(providerServiceMapID);
    }

    @Override
    public List<M_ItemForm> getItemFormProviderServiceMapID(Integer providerServiceMapID) {

        return itemFormRepo.findByProviderServiceMapID(providerServiceMapID);

    }

    @Override
    public List<ItemMaster> getItemMasters(Integer providerServiceMapID, Integer itemCategoryID) {
        List<ItemMaster> data = itemRepo.getItemMasters(providerServiceMapID, itemCategoryID);
        return data;

    }

    @Override
    public M_ItemCategory getItemCategory(Integer catID) {
        return itemCategoryRepo.findById(catID).get();
    }

    @Override
    public List<ItemMaster> getActiveItemMaster(ItemMaster itemMaster) {
        return itemRepo.findByDeletedAndProviderServiceMapID(itemMaster.getDeleted(),
                itemMaster.getProviderServiceMapID());
    }
}
