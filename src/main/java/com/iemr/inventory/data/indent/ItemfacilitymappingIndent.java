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

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "m_itemfacilitymapping")
@Data
public class ItemfacilitymappingIndent {

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
    private BigDecimal qoh;

    @Expose
    @Transient
    private String itemName;

    @Expose
    @Transient
    private String itemCode;

    @Expose
    @Transient
    private Integer itemCategoryID;

    @Transient
    @Expose
    private String itemCategory;

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
    private ItemIndent itemIndent;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ItemID", insertable = false, updatable = false)
    @Expose
    private ItemStockEntryIndent itemStockEntryIndent;

    @Transient
    @Expose
    private Boolean discontinued;

    @Transient
    @Expose
    private String itemForm;

    @Transient
    @Expose
    private String pharmacologicalCategoryName;

    @Transient
    @Expose
    private String strength;

    @Transient
    @Expose
    private String uomName;

    @Transient
    @Expose
    private String composition;

    @Transient
    @Expose
    private Boolean isMedical;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }

}
