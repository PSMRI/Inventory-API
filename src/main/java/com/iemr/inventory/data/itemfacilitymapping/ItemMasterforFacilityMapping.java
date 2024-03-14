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
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Entity
@Table(name = "m_item")
@Data
public class ItemMasterforFacilityMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(name = "ItemCode")
    private String itemCode;

    @Expose
    @Column(name = "ItemCategoryID")
    private Integer itemCategoryID;

    @Expose
    @Column(name = "IsMedical")
    private Boolean isMedical;

    @Expose
    @Column(name = "ItemFormID")
    private Integer itemFormID;

    @Expose
    @Column(name = "PharmacologyCategoryID")
    private Integer pharmacologyCategoryID;

    @Expose
    @Column(name = "ManufacturerID")
    private Integer manufacturerID;
    @Expose
    @Column(name = "Strength")
    private String strength;

    @Expose
    @Column(name = "UOMID")
    private Integer uomID;

    @Expose
    @Column(name = "isScheduledDrug")
    private Boolean IsScheduledDrug;

    @Expose
    @Column(name = "Composition")
    private String composition;

    @Expose
    @Column(name = "RouteID")
    private Integer routeID;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;
    @Expose
    @Column(name = "Status")
    private String status;

    @Expose
    @Column(name = "Discontinued", insertable = false, updatable = true)
    private Boolean discontinued;

    @Expose
    @Column(name = "Deleted", insertable = false, updatable = true)
    private Boolean deleted;

    @Expose
    @Column(name = "Processed", insertable = false, updatable = true)
    private Character processed;

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

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }
}
