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
package com.iemr.inventory.data.patientreturn;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.Transient;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ItemDetailModel {

    @Expose
    private Integer itemID;

    @Expose
    private String itemName;

    @Expose
    private String batchNo;

    @Expose
    private Integer issuedQuantity;

    @Expose
    private Timestamp dateofIssue;

    @Expose
    private Integer returnQuantity;

    @Expose
    private Boolean discontinued;

    @Expose
    private Boolean itemDeleted;

    @Expose
    private Long itemStockExitID;

    @Expose
    private Long itemStockEntryID;

    @Expose
    private Long visitID;

    @Expose
    private Long visitCode;

    @Expose
    private Long benRegID;

    @Expose
    private Integer providerServiceMapID;

    @Expose
    private Integer facilityID;

    @Expose
    private String createdBy;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    public ItemDetailModel(Integer itemID, String itemName, String batchNo, Integer issuedQuantity, Timestamp dateofIssue, Boolean discontinued,
                           Boolean itemDeleted, Long itemStockExitID, Long itemStockEntryID, Long visitID, Long visitCode, Long benRegID,
                           Integer providerServiceMapID, Integer facilityID) {

        this.itemID = itemID;
        this.itemName = itemName;
        this.batchNo = batchNo;
        this.issuedQuantity = issuedQuantity;
        this.dateofIssue = dateofIssue;
        this.discontinued = discontinued;
        this.itemDeleted = itemDeleted;
        this.itemStockExitID = itemStockExitID;
        this.itemStockEntryID = itemStockEntryID;
        this.visitID = visitID;
        this.visitCode = visitCode;
        this.benRegID = benRegID;
        this.providerServiceMapID = providerServiceMapID;
        this.facilityID = facilityID;

    }

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }
}
