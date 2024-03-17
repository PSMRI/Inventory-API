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
package com.iemr.inventory.mapper.report;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.model.report.BenDrugIssueReport;
import com.iemr.inventory.model.report.ExpiryReport;
import com.iemr.inventory.model.report.InwardStockReport;

@Mapper(componentModel = "spring")
public interface InventoryReportMapper {

    InventoryReportMapper INSTANCE = Mappers.getMapper(InventoryReportMapper.class);

    @Mappings({@Mapping(target = "facilityName", expression = "java(entryReport.getFacilityName())"),
            @Mapping(target = "itemName", expression = "java(entryReport.getItemName())"),
            @Mapping(target = "itemCategory", expression = "java(entryReport.getItemCategoryName())"),
            @Mapping(target = "batchNo", expression = "java(entryReport.getBatchNo())"),
            @Mapping(target = "unitCostPrice", expression = "java(entryReport.getUnitCostPrice())"),
            @Mapping(target = "expiryDate", expression = "java(entryReport.getExpiryDate())"),
            @Mapping(target = "inwardDate", expression = "java(entryReport.getCreatedDate())"),
            @Mapping(target = "entryType", expression = "java(entryReport.getEntryType())"),
            @Mapping(target = "quantity", expression = "java(entryReport.getQuantity())")})
    InwardStockReport mapInwardStockReport(ItemStockEntryReport entryReport);

    @Mappings({@Mapping(target = "facilityName", expression = "java(entryReport.getFacilityName())"),
            @Mapping(target = "itemName", expression = "java(entryReport.getItemName())"),
            @Mapping(target = "itemCategory", expression = "java(entryReport.getItemCategoryName())"),
            @Mapping(target = "batchNo", expression = "java(entryReport.getBatchNo())"),
            @Mapping(target = "unitCostPrice", expression = "java(entryReport.getUnitCostPrice())"),
            @Mapping(target = "expiryDate", expression = "java(entryReport.getExpiryDate())"),
            @Mapping(target = "quantityInHand", expression = "java(entryReport.getQuantityInHand())")})
    ExpiryReport mapExpiryReport(ItemStockEntryReport entryReport);

//	@Mappings({ @Mapping(target = "facilityName", expression = "java(exitReport.getFacilityName())"),
//		@Mapping(target = "itemName", expression = "java(exitReport.getItemName())"),
//		@Mapping(target = "itemCategory", expression = "java(exitReport.getItemCategoryName())"),
//		@Mapping(target = "batchNo", expression = "java(exitReport.getBatchNo())"),
//		@Mapping(target = "unitCostPrice", expression = "java(exitReport.getUnitCostPrice())"),
//		@Mapping(target = "expiryDate", expression = "java(exitReport.getExpiryDate())"),
//		@Mapping(target = "consumptionDate", expression = "java(exitReport.getCreatedDate())"),
//		@Mapping(target = "consumptionQuantity", expression = "java(exitReport.getQuantity())"),
//		@Mapping(target = "consumptionType", expression = "java(exitReport.getExitType())")})
//	ConsumptionReport mapConsumptionReport(ItemStockExitReport exitReport);

    @Mappings({@Mapping(target = "date", expression = "java(exitReport.getCreatedDate())"),
            @Mapping(target = "beneficiaryName", expression = "java(exitReport.getPatientName())"),
            @Mapping(target = "gender", expression = "java(exitReport.getGender())"),
            @Mapping(target = "age", expression = "java(exitReport.getAge())"),
            @Mapping(target = "itemName", expression = "java(exitReport.getItemName())"),
            @Mapping(target = "itemCategory", expression = "java(exitReport.getItemCategoryName())"),
            @Mapping(target = "batchNo", expression = "java(exitReport.getBatchNo())"),
            @Mapping(target = "expiryDate", expression = "java(exitReport.getExpiryDate())"),
            @Mapping(target = "strength", expression = "java(exitReport.getStrength())"),
            @Mapping(target = "dispensedQuantity", expression = "java(exitReport.getQuantityGiven())")})
    BenDrugIssueReport mapBenDrugIssueReport(PatientIssueExitReport exitReport);

//	@Mappings({ @Mapping(target = "facilityName", expression = "java(entryReport.getFacilityName())"),
//		@Mapping(target = "itemName", expression = "java(entryReport.getItemName())"),
//		@Mapping(target = "itemCategory", expression = "java(entryReport.getItemCategoryName())"),
//		@Mapping(target = "batchNo", expression = "java(entryReport.getBatchNo())"),
//		@Mapping(target = "unitCostPrice", expression = "java(entryReport.getUnitCostPrice())"),
//		@Mapping(target = "expiryDate", expression = "java(entryReport.getExpiryDate())"),
//		@Mapping(target = "quantity", expression = "java(entryReport.getQuantity())")
//		})
//	ExpiryReport mapShortExpiryReport(ItemStockEntryReport entryReport);


}
