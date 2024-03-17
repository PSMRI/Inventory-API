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
package com.iemr.inventory.data.stockExit;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "t_patientreturn")
@Data
public class ItemReturnEntry {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "PatientReturnID")
    private Long patientReturnID;

    @Expose
    @Column(name = "BenneficiaryRegID")
    private Long benRegID;

    @Expose
    @Column(name = "VisitID")
    private Long visitID;

    @Expose
    @Column(name = "VisitCode")
    private Long visitCode;

    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;

    @Expose
    @Column(name = "ItemExitID")
    private Long itemStockExitID;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;

    @Expose
    @Column(name = "Deleted")
    private Boolean deleted;

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
    @Column(name = "LastModDate")
    private Timestamp lastModDate;

    @Transient
    private Timestamp fromDate;

    @Transient
    private Timestamp toDate;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }
}
