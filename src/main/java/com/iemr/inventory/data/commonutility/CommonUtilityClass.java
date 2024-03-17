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
package com.iemr.inventory.data.commonutility;

public class CommonUtilityClass {
    private Long benFlowID;
    private Long beneficiaryID;
    private Integer sessionID;
    private Integer parkingPlaceID;
    private Integer vanID;

    private Long beneficiaryRegID;
    private Integer providerServiceMapID;
    private Long benVisitID;
    private Long visitCode;
    private Integer facilityID;

    public Long getBenFlowID() {
        return benFlowID;
    }

    public void setBenFlowID(Long benFlowID) {
        this.benFlowID = benFlowID;
    }

    public Long getBeneficiaryID() {
        return beneficiaryID;
    }

    public void setBeneficiaryID(Long beneficiaryID) {
        this.beneficiaryID = beneficiaryID;
    }

    public Integer getSessionID() {
        return sessionID;
    }

    public void setSessionID(Integer sessionID) {
        this.sessionID = sessionID;
    }

    public Integer getParkingPlaceID() {
        return parkingPlaceID;
    }

    public void setParkingPlaceID(Integer parkingPlaceID) {
        this.parkingPlaceID = parkingPlaceID;
    }

    public Integer getVanID() {
        return vanID;
    }

    public void setVanID(Integer vanID) {
        this.vanID = vanID;
    }

    public Long getBeneficiaryRegID() {
        return beneficiaryRegID;
    }

    public void setBeneficiaryRegID(Long beneficiaryRegID) {
        this.beneficiaryRegID = beneficiaryRegID;
    }

    public Integer getProviderServiceMapID() {
        return providerServiceMapID;
    }

    public void setProviderServiceMapID(Integer providerServiceMapID) {
        this.providerServiceMapID = providerServiceMapID;
    }

    public Long getBenVisitID() {
        return benVisitID;
    }

    public void setBenVisitID(Long benVisitID) {
        this.benVisitID = benVisitID;
    }

    public Long getVisitCode() {
        return visitCode;
    }

    public void setVisitCode(Long visitCode) {
        this.visitCode = visitCode;
    }

    public Integer getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(Integer facilityID) {
        this.facilityID = facilityID;
    }

}
