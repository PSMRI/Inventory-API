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

import com.iemr.inventory.data.stockentry.ItemBatchList;
import com.iemr.inventory.data.stockentry.ItemStockEntry;

@Mapper(componentModel = "spring")
public interface ItemBatchListMap {

    ItemBatchListMap INSTANCE = Mappers.getMapper(ItemBatchListMap.class);

    @Mappings({
            @Mapping(target = "itemStockEntryID", source = "itemStockExit.itemStockEntryID"),
            @Mapping(target = "facilityID", source = "itemStockExit.facilityID"),
            @Mapping(target = "itemID", source = "itemStockExit.itemID"),
            @Mapping(target = "quantity", source = "itemStockExit.quantity"),
            @Mapping(target = "quantityInHand", source = "itemStockExit.quantityInHand"),
            @Mapping(target = "batchNo", source = "itemStockExit.batchNo"),
            @Mapping(target = "expiryDate", source = "itemStockExit.expiryDate"),
    })
    ItemBatchList getItemStockExitMap(ItemStockEntry itemStockExit);

    List<ItemBatchList> getItemStockExitMapList(List<ItemStockEntry> itemStockExit);
}
