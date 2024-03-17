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
package com.iemr.inventory.data.patientreturn;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class PatientReturnModel {

    @Expose
    private Long benRegID;

    @Expose
    private Integer facilityID;

    @Expose
    private Integer itemID;

    @Expose
    private String itemName;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    public PatientReturnModel(Long benRegID, Integer facilityID, Integer itemID, String itemName) {
        this.benRegID = benRegID;
        this.facilityID = facilityID;
        this.itemID = itemID;
        this.itemName = itemName;

    }

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }
}
