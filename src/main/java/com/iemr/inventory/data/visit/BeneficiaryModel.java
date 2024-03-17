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
package com.iemr.inventory.data.visit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.Transient;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class BeneficiaryModel {

    @Expose
    private Long beneficiaryRegID;

    @Expose
    private List<BenVisitDetail> benVisitDetail;

    @Expose
    private List<BeneficiaryFlowStatus> beneficiaryFlowStatus;

    @Expose
    private I_bendemographics i_bendemographics;


    @Expose
    private String beneficiaryID;

    @Expose
    private Short titleId;

    @Expose
    private String titleName;

    @Expose
    private String firstName;

    @Expose
    private String middleName;

    @Expose
    private String lastName;

    @Expose
    private Short statusID;

    @Expose
    private String statusName;

    @Expose
    private Integer genderID;

    @Expose
    private String genderName;

    @Expose
    private GenderModel m_gender;

    @Expose
    private Short maritalStatusID;

    @Expose
    private String maritalStatusName;

    @Expose
    private Short sexualOrientationId;

    @Expose
    private String sexualOrientationName;

    @Expose
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp dOB;

    @Expose
    private Integer age;

    @Expose
    private String fatherName;

    @Expose
    private String spouseName;

    @Expose
    private String govtIdentityNo;

    @Expose
    private Integer govtIdentityTypeID;

    @Expose
    private Short registeredServiceID;

    @Expose
    private String sourceOfInformation;

    @Expose
    private Boolean isHIVPos;

    @Expose
    private String placeOfWork;

    @Expose
    private String remarks;

    @Expose
    private Boolean changeInSelfDetails;

    @Expose
    private Boolean changeInAddress;

    @Expose
    private Boolean changeInContacts;

    @Expose
    private Boolean changeInIdentities;

    @Expose
    private Boolean changeInOtherDetails;

    @Expose
    private Boolean changeInFamilyDetails;

    @Expose
    private Boolean changeInAssociations;

    @Expose
    private Boolean is1097 = false;

    @Expose
    private Boolean deleted;

    @Expose
    private String createdBy;

    @Expose
    private Timestamp createdDate;

    @Expose
    private String modifiedBy;

    @Expose
    private Timestamp lastModDate;
    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    public static Timestamp getTimestampData(Timestamp timestamp) {
        return timestamp;
    }

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }

}
