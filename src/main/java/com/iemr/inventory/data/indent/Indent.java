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
import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "t_indent")
public class Indent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "IndentID")
    private Long indentID;

    @Expose
    @Column(name = "FromFacilityID")
    private Integer fromFacilityID;

    @Expose
    @Column(name = "SyncFacilityID")
    private Integer syncFacilityID;

    @Expose
    @Column(name = "FromFacilityName")
    private String fromFacilityName;

    @Expose
    @Column(name = "ToFacilityID")
    private Integer toFacilityID;

    @Expose
    @Column(name = "RefNo")
    private String refNo;

    @Expose
    @Column(name = "OrderDate")
    private Timestamp orderDate;

    @Expose
    @Column(name = "Reason")
    private String reason;

    @Expose
    @Column(name = "UserID")
    private Integer userID;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;

    @Expose
    @Column(name = "Status")
    private String status;

    @Expose
    @Column(name = "StatusReason")
    private String statusReason;

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
    @Column(name = "VanSerialNo", updatable = true, insertable = false)
    private Long vanSerialNo;

    @Expose
    @Transient
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "indent")
    private List<IndentOrder> indentOrder;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false, name = "fromFacilityID", referencedColumnName = "facilityID")
    private M_Facility m_Facility;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }

}
