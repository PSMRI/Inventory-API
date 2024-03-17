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
package com.iemr.inventory.data.supplier;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "m_supplieraddress")
public class M_Supplieraddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "SupplierAddressID")
    private Integer SupplierAddressID;
    @Expose
    @Column(name = "SupplierID")
    private Integer supplierID;
    @Expose
    @Column(name = "AddressLine1")
    private String addressLine1;
    @Expose
    @Column(name = "AddressLine2")
    private String addressLine2;
    @Expose
    @Column(name = "District")
    private String district;
    @Expose
    @Column(name = "State")
    private String state;
    @Expose
    @Column(name = "Country")
    private String country;
    @Expose
    @Column(name = "PinCode")
    private String pinCode;
    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;
    @Expose
    @Column(name = "Deleted", insertable = false, updatable = true)
    private Boolean deleted;
    @Expose
    @Column(name = "CreatedBy")
    private String createdBy;
    @Expose
    @Column(name = "CreatedDate", insertable = false, updatable = true)
    private Date createdDate;
    @Expose
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Expose
    @Column(name = "LastModDate", insertable = false, updatable = true)
    private Date lastModDate;
    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    public M_Supplieraddress() {
        // TODO Auto-generated constructor stub
    }

    public Integer getSupplierAddressID() {
        return SupplierAddressID;
    }

    public void setSupplierAddressID(Integer supplierAddressID) {
        SupplierAddressID = supplierAddressID;
    }

    public Integer getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(Integer supplierID) {
        this.supplierID = supplierID;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
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

    public OutputMapper getOutputMapper() {
        return outputMapper;
    }

    public void setOutputMapper(OutputMapper outputMapper) {
        this.outputMapper = outputMapper;
    }

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }

}
