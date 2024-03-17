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
package com.iemr.inventory.data.stockadjustment;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "t_SAItemMapping")
@Data
public class StockAdjustmentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "SAItemMapID")
    private Long sAItemMapID;

    @Expose
    @Column(name = "VanSerialNo")
    private Long vanSerialNo;

//	@Expose
//	@Column(name = "StockAdjustmentDraftID")
//	private Integer stockAdjustmentDraftID;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name = "ItemStockEntryID", insertable = false, updatable = false)
    @JoinColumns({
            @JoinColumn(updatable = false, insertable = false, name = "itemStockEntryID", referencedColumnName = "vanSerialNo"),
            @JoinColumn(updatable = false, insertable = false, name = "syncFacilityID", referencedColumnName = "syncFacilityID")
    })
    @Expose
    private ItemStockEntry itemStockEntry;

    @Expose
    @Column(name = "ItemStockEntryID")
    private Long itemStockEntryID;


    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;

    @Expose
    @Column(name = "SyncFacilityID")
    private Integer syncFacilityID;


    @Expose
    @Column(name = "IsAdded")
    private Boolean isAdded;

    @Expose
    @Column(name = "AdjustedQuantity")
    private Integer adjustedQuantity;

    @Expose
    @Column(name = "Reason")
    private String reason;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;

    @Expose
    @Column(name = "Status")
    private String status;

    @Expose
    @Column(name = "StockAdjustmentID")
    private Long stockAdjustmentID;

//	@JoinColumn(name = "stockAdjustmentDraftID", insertable = false, updatable = false)
//	@ManyToOne(fetch = FetchType.LAZY)
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "StockAdjustmentDraftID",insertable = false, updatable = false)
//	private StockAdjustmentDraft stockAdjustmentDraft;

    @Expose
    @Column(name = "VanID")
    private Long vanID;

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
