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
package com.iemr.inventory.data.report;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "db_reporting.fact_itemstockentry", schema = "db_reporting")
public class ItemStockEntryReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "Fact_ItemStockEntryID")
    private Long factItemStockEntryID;

    @Expose
    @Column(name = "ItemStockEntryID")
    private Long itemStockEntryID;

    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;

    @Expose
    @Column(name = "FacilityName")
    private String facilityName;

    @Expose
    @Column(name = "FacilityDesc")
    private String facilityDesc;

    @Expose
    @Column(name = "FacilityCode")
    private String facilityCode;

    @Expose
    @Column(name = "FacilityTypeID")
    private Long facilityTypeID;

    @Expose
    @Column(name = "IsMainFacility")
    private Boolean isMainFacility;

    @Expose
    @Column(name = "MainFacilityID")
    private Integer mainFacilityID;

    @Expose
    @Column(name = "ItemID")
    private Integer itemID;

    @Expose
    @Column(name = "ItemName")
    private String itemName;

    @Expose
    @Column(name = "ItemDesc")
    private String itemDesc;

    @Expose
    @Column(name = "ItemCategoryID")
    private Integer itemCategoryID;

    @Expose
    @Column(name = "ItemCategoryName")
    private String itemCategoryName;

    @Expose
    @Column(name = "AlertBeforeDays")
    private String alertBeforeDays;

    @Expose
    @Column(name = "Strength")
    private String strength;

    @Expose
    @Column(name = "BatchNo")
    private String batchNo;

    @Expose
    @Column(name = "Quantity")
    private Integer quantity;

    @Expose
    @Column(name = "QuantityInHand")
    private Integer quantityInHand;

    @Expose
    @Column(name = "UnitCostPrice")
    private Double unitCostPrice;

    @Expose
    @Column(name = "ExpiryDate")
    private Date expiryDate;

    @Expose
    @Column(name = "EntryTypeID")
    private Long entryTypeID;

    @Expose
    @Column(name = "EntryType")
    private String entryType;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;

    @Expose
    @Column(name = "CreatedBy")
    private String createdBy;

    @Expose
    @Column(name = "CreatedDate")
    private Timestamp createdDate;

    @Transient
    private Timestamp startDate;

    @Transient
    private Timestamp endDate;

    @Transient
    private Integer month;

    @Transient
    private String monthName;

    @Transient
    private Integer year;

}
