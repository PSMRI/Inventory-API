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
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "m_StockAdjustmentDraft")
@Data
public class StockAdjustmentDraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "StockAdjustmentDraftID")
    private Long stockAdjustmentDraftID;

    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;

    @Expose
    @Column(name = "DraftDesc")
    private String draftDesc;

    @Expose
    @Column(name = "DraftName")
    private String draftName;

    @Expose
    @Column(name = "RefNo")
    private String refNo;

    @Expose
    @Column(name = "IsCompleted")
    private Boolean isCompleted;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;

    @Expose
    @Column(name = "Deleted", insertable = false, updatable = true)
    private Boolean deleted;

    @Expose
    //@Column(name="VanID")
    @Transient
    private Long vanID;

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

    // @OneToMany(mappedBy = "stockAdjustmentDraft")
    // @OneToMany(fetch =
    // FetchType.EAGER,mappedBy="stockAdjustmentDraft",cascade =
    // CascadeType.ALL)
    @Expose
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "stockAdjustmentDraftID", insertable = false, updatable = false)
    private List<StockAdjustmentItemDraft> stockAdjustmentItemDraft;

    @Expose
    @Transient
    private List<StockAdjustmentItemDraftEdit> stockAdjustmentItemDraftEdit;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }

}
