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
@Table(name = "db_reporting.fact_patientissueexit", schema = "db_reporting")
public class PatientIssueExitReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "Fact_PatientIssueExitID")
    private Long factPatientIssueExitID;

    @Expose
    @Column(name = "ItemStockExitID")
    private Long itemStockExitID;

    @Expose
    @Column(name = "ItemStockEntryID")
    private Long itemStockEntryID;

    @Expose
    @Column(name = "ItemID")
    private Integer itemID;

    @Expose
    @Column(name = "ItemName")
    private String itemName;

    @Expose
    @Column(name = "ItemCategoryName")
    private String itemCategoryName;

    @Expose
    @Column(name = "BatchNo")
    private String batchNo;

    @Expose
    @Column(name = "Strength")
    private String strength;

    @Expose
    @Column(name = "QuantityGiven")
    private Integer quantityGiven;

    @Expose
    @Column(name = "ExpiryDate")
    private Date expiryDate;

    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;

    @Expose
    @Column(name = "PatientName")
    private String patientName;

    @Expose
    @Column(name = "Age")
    private Integer age;

    @Expose
    @Column(name = "Gender")
    private String gender;

    @Expose
    @Column(name = "CreatedDate")
    private Timestamp createdDate;

    @Transient
    private Timestamp startDate;

    @Transient
    private Timestamp endDate;
}
