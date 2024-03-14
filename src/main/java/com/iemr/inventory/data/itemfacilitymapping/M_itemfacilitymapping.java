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
package com.iemr.inventory.data.itemfacilitymapping;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "m_itemfacilitymapping")
@Data
public class M_itemfacilitymapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "ItemFacilityMapID")
    private Integer itemFacilityMapID;
    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;
    @Expose
    @Column(name = "ItemID")
    private Integer itemID;

    @Expose
    @Transient
    private Integer itemCategoryID;

    @Expose
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, insertable = false, name = "FacilityID")
    private M_Facility facility;

    @Expose
    @Column(name = "MappingType")
    private String mappingType;
    @Expose
    @Column(name = "Status")
    private String status;
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
    @Column(name = "CreatedDate", insertable = false, updatable = false)
    private Date createdDate;
    @Expose
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Expose
    @Column(name = "LastModDate", insertable = false, updatable = false)
    private Date lastModDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ItemID", insertable = false, updatable = false)
    @Expose
    private ItemMasterforFacilityMapping itemMasterforFacilityMapping;

    @Transient
    @Expose
    private String itemName;

    @Transient
    @Expose
    private Boolean discontinued;
    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    public M_itemfacilitymapping() {
        // TODO Auto-generated constructor stut
    }

    public M_itemfacilitymapping(Integer itemID, String itemName) {
        this.itemID = itemID;
        this.itemName = itemName;
    }

    public M_itemfacilitymapping(Integer itemID, String itemName, Boolean discontinued, Integer categoryID) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.discontinued = discontinued;
        this.itemCategoryID = categoryID;
    }

    public ItemMasterforFacilityMapping getItemMasterforFacilityMapping() {
        return itemMasterforFacilityMapping;
    }

    public void setItemMasterforFacilityMapping(ItemMasterforFacilityMapping itemMasterforFacilityMapping) {
        this.itemMasterforFacilityMapping = itemMasterforFacilityMapping;
    }

    public Integer getItemStoreMapID() {
        return itemFacilityMapID;
    }

    public void setItemStoreMapID(Integer itemStoreMapID) {
        this.itemFacilityMapID = itemStoreMapID;
    }

    public Integer getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(Integer facilityID) {
        this.facilityID = facilityID;
    }

    public Integer getItemID() {
        return itemID;
    }

    public void setItemID(Integer itemID) {
        this.itemID = itemID;
    }

    public String getMappingType() {
        return mappingType;
    }

    public void setMappingType(String mappingType) {
        this.mappingType = mappingType;
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
