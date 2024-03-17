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

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/***
 *
 * @author NE298657
 *
 */
@Entity
@Table(name = "i_ben_flow_outreach")
@Data
public class BeneficiaryFlowStatus {

    @Id
    @GeneratedValue
    @Expose
    @Column(name = "ben_flow_id")
    private Long benFlowID;

    @Expose
    @Column(name = "beneficiary_reg_id")
    private Long beneficiaryRegID;

    @Expose
    @Column(name = "beneficiary_visit_id")
    private Long benVisitID;

    @Expose
    @Column(name = "beneficiary_visit_code")
    private String benVisitCode;

    @Expose
    @Column(name = "visit_reason")
    private String VisitReason;

    @Expose
    @Column(name = "visit_category")
    private String VisitCategory;

    @Expose
    @Column(name = "visit_no")
    private Short benVisitNo;

    @Expose
    @Column(name = "nurse_flag")
    private Short nurseFlag;

    @Expose
    @Column(name = "doctor_flag")
    private Short doctorFlag;

    @Expose
    @Column(name = "pharmacist_flag")
    private Short pharmacist_flag;

    @Expose
    @Column(name = "lab_technician_flag")
    private Short lab_technician_flag;

    @Expose
    @Column(name = "radiologist_flag")
    private Short radiologist_flag;

    @Expose
    @Column(name = "oncologist_flag")
    private Short oncologist_flag;

    @Expose
    @Column(name = "specialist_flag")
    private Short specialist_flag;

    @Expose
    @Column(name = "created_by")
    private String agentId;

    @Expose
    @Column(name = "created_date", insertable = false, updatable = false)
    private Timestamp visitDate;

    @Expose
    @Column(name = "modified_by")
    private String modified_by;

    @Expose
    @Column(name = "modified_date", insertable = false)
    private Timestamp modified_date;

    @Expose
    @Column(name = "ben_name")
    private String benName;

    @Expose
    @Column(name = "deleted", insertable = false)
    private Boolean deleted;

    @Transient
    private String firstName;
    @Transient
    private String lastName;

    @Expose
    @Column(name = "ben_age")
    private String age;

    @Expose
    @Column(name = "ben_age_val")
    private Integer ben_age_val;

    @Expose
    @Column(name = "ben_dob")
    private Timestamp dOB;

    @Expose
    @Column(name = "ben_gender_val")
    private Short genderID;

    @Expose
    @Column(name = "ben_gender")
    private String genderName;

    @Expose
    @Column(name = "ben_phone_no")
    private String preferredPhoneNum;

    @Expose
    @Column(name = "father_name")
    private String fatherName;

    @Expose
    @Column(name = "spouse_name")
    private String spouseName;

    @Expose
    @Column(name = "district")
    private String districtName;

    @Expose
    @Column(name = "servicePoint")
    private String servicePointName;

    @Expose
    @Column(name = "registrationDate")
    private Timestamp registrationDate;
    @Expose
    @Column(name = "providerServiceMapID")
    private Integer providerServiceMapId;
    @Expose
    @Column(name = "village")
    private String villageName;
    @Expose
    @Column(name = "beneficiary_id")
    private Long beneficiaryID;
    @Expose
    @Column(name = "lab_iteration_cnt")
    private Short labIteration;
    //	@Transient
////	private I_bendemographics i_bendemographics;
//	@Transient
//	private List<BenPhoneMaps> benPhoneMaps;
    @Transient
    private String benImage;
    @Transient
    private Integer ageVal;
    @Transient
    private Timestamp serviceDate;
    @Transient
    private String beneficiaryName;
    @Transient
    private Timestamp createdDate;
    @Transient
    private String createdBy;
    //	@Transient
//	private GenderMaster m_gender;
    @Transient
    private Boolean passToNurse;

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getProviderServiceMapId() {
        return providerServiceMapId;
    }

    public void setProviderServiceMapId(Integer providerServiceMapId) {
        this.providerServiceMapId = providerServiceMapId;
    }

    public String getServicePointName() {
        return servicePointName;
    }

    public void setServicePointName(String servicePointName) {
        this.servicePointName = servicePointName;
    }

    public Integer getProviderServiceMapID() {
        return providerServiceMapId;
    }

    public void setProviderServiceMapID(Integer providerServiceMapId) {
        this.providerServiceMapId = providerServiceMapId;
    }
}
