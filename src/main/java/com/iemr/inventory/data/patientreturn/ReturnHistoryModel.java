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

import java.sql.Timestamp;

@Data
public class ReturnHistoryModel {

    @Expose
    private String itemName;

    @Expose
    private String batchNo;

    @Expose
    private Timestamp dateofIssue;

    @Expose
    private Integer issuedQuantity;

    @Expose
    private Timestamp returnDate;

    @Expose
    private Long visitID;

    @Expose
    private Long visitCode;

    @Expose
    private String patientName;

    @Expose
    private Integer age;

    @Expose
    private String gender;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    public ReturnHistoryModel(String itemName, String batchNo, Integer issuedQuantity, Timestamp dateofIssue, Long visitID,
                              Long visitCode, String patientName, Integer age, String gender, Timestamp returnDate) {

        this.itemName = itemName;
        this.batchNo = batchNo;
        this.dateofIssue = dateofIssue;
        this.issuedQuantity = issuedQuantity;
        this.visitID = visitID;
        this.visitCode = visitCode;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.returnDate = returnDate;


    }

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }
}
