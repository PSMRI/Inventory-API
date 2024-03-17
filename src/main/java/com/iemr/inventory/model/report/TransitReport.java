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
public class TransitReport {

    private Long slNo;

    private String fromFacilityName;

    private String tofacilityName;

    private String itemName;

    private String batchNo;

    private Double unitCostPrice;

    private Date expiryDate;

    private Timestamp OrderDate;

    private Timestamp IssueDate;

    public TransitReport(Long slNo, String itemName, String batchNo, Double unitCostPrice, Date expiryDate, String fromFacilityName,
                         String tofacilityName, Timestamp OrderDate, Timestamp IssueDate) {

        this.itemName = itemName;
        this.batchNo = batchNo;
        this.unitCostPrice = unitCostPrice;
        this.expiryDate = expiryDate;
        this.fromFacilityName = fromFacilityName;
        this.tofacilityName = tofacilityName;
        this.OrderDate = OrderDate;
        this.IssueDate = IssueDate;
        this.slNo = slNo;

    }

    @Override
    public String toString() {

        return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls()
                .setDateFormat("dd-MM-yyyy").create().toJson(this);

    }
}
