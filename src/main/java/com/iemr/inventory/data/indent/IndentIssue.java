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
package com.iemr.inventory.data.indent;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "t_indentissue")
public class IndentIssue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "IndentIssueID")
    private Long indentIssueID;

    @Expose
    @Column(name = "IndentOrderID")
    private Long indentOrderID;

    @Expose
    @Column(name = "IndentID")
    private Long indentID;

    @Expose
    @Column(name = "ItemID")
    private Integer itemID;

    @Expose
    @Column(name = "itemName")
    private String itemName;

    @Expose
    @Column(name = "IssuedQty")
    private Integer issuedQty;

    @Expose
    @Column(name = "IssueDate")
    private Timestamp issueDate;

    @Expose
    @Column(name = "Remarks")
    private String remarks;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;

    @Expose
    @Column(name = "Status")
    private String status;

    @Expose
    @Column(name = "Deleted", updatable = true, insertable = false)
    private Boolean deleted;

    @Expose
    @Column(name = "Processed")
    private String processed;

    @Expose
    @Column(name = "CreatedBy")
    private String createdBy;

    @Expose
    @Column(name = "CreatedDate")
    private Timestamp createdDate;

    @Expose
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Expose
    @Column(name = "LastModDate", updatable = false, insertable = false)
    private Timestamp lastModDate;

    @Expose
    @Column(name = "VanID")
    private Long vanID;

    @Expose
    @Column(name = "ParkingPlaceID")
    private Long parkingPlaceID;

    @Expose
    @Column(name = "VanSerialNo", updatable = false, insertable = false)
    private Long vanSerialNo;

    @Transient
    private String action;

    @Transient
    private Long itemStockEntryID;

    @Expose
    @Column(name = "UnitCostPrice")
    private Double unitCostPrice;

    @Expose
    @Column(name = "BatchNo")
    private String batchNo;

    @Expose
    @Column(name = "ExpiryDate")
    private Date expiryDate;

    @Expose
    @Column(name = "FromFacilityID")
    private Integer fromFacilityID;

    @Expose
    @Column(name = "ToFacilityID")
    private Integer toFacilityID;

    @Expose
    @Column(name = "SyncFacilityID")
    private Integer syncFacilityID;

    @Expose
    @Column(name = "IsManual")
    private Boolean isManual;

    @Transient
    @Expose
    private String rejectedReason;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }
}
