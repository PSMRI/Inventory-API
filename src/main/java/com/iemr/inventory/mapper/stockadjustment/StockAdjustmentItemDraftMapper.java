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
/* package com.iemr.inventory.mapper.stockadjustment;

import java.util.Date;
import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;

@Mapper(componentModel = "spring")
@DecoratedWith(StockAdjustmentItemDraftMapperDecorator.class)
public interface StockAdjustmentItemDraftMapper {

    StockAdjustmentItemDraftMapper INSTANCE = Mappers.getMapper(StockAdjustmentItemDraftMapper.class);

    @Mapping(target = "SADraftItemMapID", source = "SADraftItemMapID", qualifiedByName = "mapSADraftItemMapID")
    StockAdjustmentItemDraftEdit getStockAdjustmentItemDraftEdit(StockAdjustmentItemDraft stockAdjustmentItemDraftEdit);

    default Long mapSADraftItemMapID(Object value) {
        return (value != null) ? Long.valueOf(value.toString()) : null;
    }

    // Custom mapping method for createdDate
    default Date map(Object value) {
        // Implement your custom mapping logic here
        // For example, convert the Object to a Date
        // Replace the following line with your actual logic
        return (value != null) ? new Date() : null;
    }

    List<StockAdjustmentItemDraftEdit> getStockAdjustmentItemDraftEditList(
            List<StockAdjustmentItemDraft> stockAdjustmentItemDraft);

	List<StockAdjustmentItemDraftEdit> getStockAdjustmentItemEditList(List<StockAdjustmentItem> stockAdjustmentItem);
} */

package com.iemr.inventory.mapper.stockadjustment;

import java.util.Date;
import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraftEdit;

@Mapper(componentModel = "spring")
@DecoratedWith(StockAdjustmentItemDraftMapperDecorator.class)
public interface StockAdjustmentItemDraftMapper {

    StockAdjustmentItemDraftMapper INSTANCE = Mappers.getMapper(StockAdjustmentItemDraftMapper.class);

    @Mapping(target = "SADraftItemMapID", source = "SADraftItemMapID", qualifiedByName = "mapSADraftItemMapID")
    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "mapCreatedDate")
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "mapCreatedBy")
    StockAdjustmentItemDraftEdit getStockAdjustmentItemDraftEdit(StockAdjustmentItemDraft stockAdjustmentItemDraftEdit);

    @Named("mapSADraftItemMapID")
    default Long mapSADraftItemMapID(Object value) {
        return (value != null) ? Long.valueOf(value.toString()) : null;
    }

    @Named("mapCreatedDate")
    default Date mapCreatedDate(Object value) {
        // Implement your custom mapping logic for createdDate here
        return (value != null) ? new Date() : null;
    }

    @Named("mapCreatedBy")
    default String mapCreatedBy(Object value) {
        // Implement your custom mapping logic for createdBy here
        return (value != null) ? value.toString() : null;
    }

    List<StockAdjustmentItemDraftEdit> getStockAdjustmentItemDraftEditList(
            List<StockAdjustmentItemDraft> stockAdjustmentItemDraft);

    List<StockAdjustmentItemDraftEdit> getStockAdjustmentItemEditList(List<StockAdjustmentItem> stockAdjustmentItem);
}

