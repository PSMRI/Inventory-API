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
package com.iemr.inventory.model.report;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class ConsumptionReport {

    private Long slNo;

    private String facilityName;

    private String itemName;

    private String itemCategory;

    private String batchNo;

    private Double unitCostPrice;

    private Date expiryDate;

    private Timestamp consumptionDate;

    private Integer consumptionQuantity;

    private String consumptionType;

    private String beneficiaryID;

    private String beneficiaryName;

    public ConsumptionReport(String batchNo, Timestamp consumptionDate, Integer consumptionQuantity, String consumptionType,
                             Date expiryDate, String facilityName, String itemCategory, String itemName, Double unitCostPrice,
                             String beneficiaryName, String beneficiaryID) {
        this.batchNo = batchNo;
        this.consumptionDate = consumptionDate;
        this.consumptionQuantity = consumptionQuantity;
        this.consumptionType = consumptionType;
        this.expiryDate = expiryDate;
        this.facilityName = facilityName;
        this.itemCategory = itemCategory;
        this.itemName = itemName;
        this.unitCostPrice = unitCostPrice;
        this.beneficiaryName = beneficiaryName;
        this.beneficiaryID = beneficiaryID;

    }

    public ConsumptionReport() {

    }

    @Override
    public String toString() {

        return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls()
                .setDateFormat("dd-MM-yyyy").create().toJson(this);

    }
}
