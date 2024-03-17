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
package com.iemr.inventory.data.facilitytype;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "m_facilitytype")
public class M_facilitytype {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FacilityTypeID")
    @Expose
    private Integer facilityTypeID;

    @Column(name = "FacilityTypeName")
    @Expose
    private String facilityTypeName;

    @Column(name = "FacilityTypeDesc")
    @Expose
    private String facilityTypeDesc;

    @Column(name = "FacilityTypeCode")
    @Expose
    private String facilityTypeCode;

    @Column(name = "Status")
    @Expose
    private String status;

    @Column(name = "ProviderServiceMapID")
    @Expose
    private Integer providerServiceMapID;

    @Column(name = "Deleted", insertable = false, updatable = true)
    @Expose
    private Boolean deleted;

    @Column(name = "CreatedBy")
    @Expose
    private String createdBy;

    @Column(name = "CreatedDate", insertable = false, updatable = false)
    @Expose
    private Date createdDate;

    @Column(name = "ModifiedBy")
    @Expose
    private String modifiedBy;

    @Column(name = "LastModDate", insertable = false, updatable = false)
    @Expose
    private Date lastModDate;
    @Transient
    private OutputMapper outputMapper = new OutputMapper();


    public M_facilitytype() {
    }

    public Integer getFacilityTypeID() {
        return facilityTypeID;
    }

    public void setFacilityTypeID(Integer facilityTypeID) {
        this.facilityTypeID = facilityTypeID;
    }

    public String getFacilityTypeName() {
        return facilityTypeName;
    }

    public void setFacilityTypeName(String facilityTypeName) {
        this.facilityTypeName = facilityTypeName;
    }

    public String getFacilityTypeDesc() {
        return facilityTypeDesc;
    }

    public void setFacilityTypeDesc(String facilityTypeDesc) {
        this.facilityTypeDesc = facilityTypeDesc;
    }

    public String getFacilityTypeCode() {
        return facilityTypeCode;
    }

    public void setFacilityTypeCode(String facilityTypeCode) {
        this.facilityTypeCode = facilityTypeCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getProviderServiceMapID() {
        return providerServiceMapID;
    }

    public void setProviderServiceMapID(Integer providerServiceMapID) {
        this.providerServiceMapID = providerServiceMapID;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getLastModDate() {
        return lastModDate;
    }

    public void setLastModDate(Date lastModDate) {
        this.lastModDate = lastModDate;
    }

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }

}
