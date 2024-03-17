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
package com.iemr.inventory.mapper.stockExit;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;

@Mapper(componentModel = "spring")
public interface ItemStockExitMapper {

    

    @Mappings({
            @Mapping(target = "itemName", source = "itemStockExit.itemStockEntry.item.itemName"),
            @Mapping(target = "batchNo", source = "itemStockExit.itemStockEntry.batchNo"),
            @Mapping(target = "expiryDate", source = "itemStockExit.itemStockEntry.expiryDate"),
            @Mapping(target = "createdDate", source = "itemStockExit.createdDate"),
            @Mapping(target = "createdBy", source = "itemStockExit.createdBy"),
            @Mapping(target = "quantity", source = "itemStockExit.quantity"),
            @Mapping(target = "deleted", source = "itemStockExit.deleted"),
    })
    ItemStockExitMap getItemStockExitMap(ItemStockExit itemStockExit);

    List<ItemStockExitMap> getItemStockExitMapList(List<ItemStockExit> itemStockExit);

    @Mappings({
            @Mapping(target = "itemName", source = "itemStockEntry.item.itemName"),
            @Mapping(target = "batchNo", source = "itemStockEntry.batchNo"),
            @Mapping(target = "expiryDate", source = "itemStockEntry.expiryDate"),
            @Mapping(target = "createdDate", source = "itemStockEntry.createdDate"),
            @Mapping(target = "createdBy", source = "itemStockEntry.createdBy"),
            @Mapping(target = "quantity", source = "itemStockEntry.quantity"),
            @Mapping(target = "deleted", source = "itemStockEntry.deleted"),
    })
    ItemStockExitMap getItemStockEntryMapList(ItemStockEntry itemStockEntry);

    List<ItemStockExitMap> getItemStockEntryMapList(List<ItemStockEntry> itemStockEntry);
}
