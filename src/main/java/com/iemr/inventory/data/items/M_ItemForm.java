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
package com.iemr.inventory.data.items;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "m_itemform")
@Data
public class M_ItemForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "ItemFormID")
    private Integer itemFormID;

    @Expose
    @Column(name = "ItemFormName")
    private String itemForm;

    @Expose
    @Column(name = "ItemFormDesc")
    private String itemFormDesc;

    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;

    @Expose
    @Column(name = "Deleted", insertable = false, updatable = false)
    private Boolean deleted;

    @Expose
    @Column(name = "Processed", insertable = false, updatable = false)
    private Character processed;

    @Expose
    @Column(name = "CreatedBy")
    private String CreatedBy;

    @Expose
    @Column(name = "CreatedDate", insertable = false, updatable = false)
    private Date CreatedDate;

    @Expose
    @Column(name = "ModifiedBy")
    private String ModifiedBy;

    @Expose
    @Column(name = "LastModDate", insertable = false, updatable = false)
    private Date LastModDate;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }
}
