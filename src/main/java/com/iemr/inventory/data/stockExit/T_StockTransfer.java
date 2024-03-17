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
import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_stocktransfer")
@Data
public class T_StockTransfer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "StockTransferID")
    private Long stockTransferID;


    @Expose
    @Column(name = "VanSerialNo")
    private Long vanSerialNo;

    @Expose
    @Column(name = "TransferFromFacilityID")
    private Integer transferFromFacilityID;
    ;

    @Expose
    @Column(name = "SyncFacilityID")
    private Integer syncFacilityID;

    @Expose
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable = false, insertable = false, name = "transferFromFacilityID")
    private M_Facility transferFromFacility;

    @Expose
    @Column(name = "TransferToFacilityID")
    private Integer transferToFacilityID;

    @Expose
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(updatable = false, insertable = false, name = "transferToFacilityID")
    private M_Facility transferToFacility;

    @Expose
    @Column(name = "RefNo")
    private String refNo;


    @Expose
    @Column(name = "IssueType")
    private String issueType;

    @Transient
    @Expose
    private List<ItemStockExit> itemStockExit;


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
    @Column(name = "VanID")
    private Long vanID;

    @Expose
    @Column(name = "ToVanID")
    private Long toVanID;

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
