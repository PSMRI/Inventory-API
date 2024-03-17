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
package com.iemr.inventory.data.dispenseagainst_rx;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.ArrayList;

@Entity
@Table(name = "v_getprescribeddrugswithdetails")
public class PrescribedDrugDetails {
    @Id
    @Expose
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Expose
    @Column(name = "PrescribedDrugID")
    private Long prescribedDrugID;
    @Expose
    @Column(name = "BeneficiaryRegID")
    private Long beneficiaryRegID;
    @Expose
    @Column(name = "BenVisitID")
    private Long benVisitID;
    @Expose
    @Column(name = "VisitCode")
    private Long visitCode;
    @Expose
    @Column(name = "PrescriptionID")
    private Long prescriptionID;
    @Expose
    @Column(name = "DrugID")
    private Integer drugID;
    @Expose
    @Column(name = "GenericDrugName")
    private String genericDrugName;
    @Expose
    @Column(name = "DrugForm")
    private String drugForm;
    @Expose
    @Column(name = "DrugStrength")
    private String drugStrength;
    @Expose
    @Column(name = "Dose")
    private String dose;
    @Expose
    @Column(name = "Route")
    private String route;
    @Expose
    @Column(name = "Frequency")
    private String frequency;
    @Expose
    @Column(name = "Duration")
    private String duration;
    @Expose
    @Column(name = "DuartionUnit")
    private String duartionUnit;
    @Expose
    @Column(name = "RelationToFood")
    private String relationToFood;
    @Expose
    @Column(name = "SpecialInstruction")
    private String specialInstruction;
    @Expose
    @Column(name = "QtyPrescribed")
    private Integer qtyPrescribed;
    @Expose
    @Column(name = "CreatedDate")
    private Timestamp createdDate;
    @Expose
    @Column(name = "CreatedBy")
    private String createdBy;
    @Expose
    @Column(name = "ItemStockEntryID")
    private Integer itemStockEntryID;
    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;
    @Expose
    @Column(name = "BatchNo")
    private String batchNo;
    @Expose
    @Column(name = "QuantityInHand")
    private Integer quantityInHand;
    @Expose
    @Column(name = "ExpiryDate")
    private Timestamp expiryDate;

    @Expose
    @Column(name = "isEDL")
    private Boolean isEDL;
    @Transient
    @Expose
    private Long qtyInHand;

    public PrescribedDrugDetails() {
    }

    public PrescribedDrugDetails(Long beneficiaryRegID, Long visitCode, Long prescriptionID, Integer drugID,
                                 String genericDrugName, String drugForm, String drugStrength, String dose, String route, String frequency,
                                 String duration, String duartionUnit, String relationToFood, String specialInstruction,
                                 Timestamp createdDate, String createdBy, Integer itemStockEntryID, String batchNo, Long qtyInHand,
                                 Timestamp expiryDate, Integer qtyPrescribed, Boolean isEDL) {
        super();
        this.beneficiaryRegID = beneficiaryRegID;
        this.visitCode = visitCode;
        this.prescriptionID = prescriptionID;
        this.drugID = drugID;
        this.genericDrugName = genericDrugName;
        this.drugForm = drugForm;
        this.drugStrength = drugStrength;
        this.dose = dose;
        this.route = route;
        this.frequency = frequency;
        this.duration = duration;
        this.duartionUnit = duartionUnit;
        this.relationToFood = relationToFood;
        this.specialInstruction = specialInstruction;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.itemStockEntryID = itemStockEntryID;
        this.batchNo = batchNo;
        this.qtyInHand = qtyInHand;
        this.expiryDate = expiryDate;
        this.qtyPrescribed = qtyPrescribed;
        this.isEDL = isEDL;
    }

    public PrescribedDrugDetails(Long beneficiaryRegID, Long visitCode, Long prescriptionID, Integer drugID,
                                 String genericDrugName, String drugForm, String drugStrength, String dose, String route, String frequency,
                                 String duration, String duartionUnit, String relationToFood, String specialInstruction,
                                 Timestamp createdDate, String createdBy, Integer qtyPrescribed, Boolean isEDL) {
        super();
        this.beneficiaryRegID = beneficiaryRegID;
        this.visitCode = visitCode;
        this.prescriptionID = prescriptionID;
        this.drugID = drugID;
        this.genericDrugName = genericDrugName;
        this.drugForm = drugForm;
        this.drugStrength = drugStrength;
        this.dose = dose;
        this.route = route;
        this.frequency = frequency;
        this.duration = duration;
        this.duartionUnit = duartionUnit;
        this.relationToFood = relationToFood;
        this.specialInstruction = specialInstruction;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.qtyPrescribed = qtyPrescribed;
        this.isEDL = isEDL;
    }

    public static ArrayList<PrescribedDrugDetails> getPrescribedMedicines(
            ArrayList<Object[]> prescribedDrugDetailsListRS) {
        PrescribedDrugDetails prescribedDrugDetails;
        ArrayList<PrescribedDrugDetails> prescribedMedicineList = new ArrayList<>();

        if (prescribedDrugDetailsListRS != null && prescribedDrugDetailsListRS.size() > 0) {
            for (Object[] objArr : prescribedDrugDetailsListRS) {
                if (objArr[16] != null)

                    prescribedDrugDetails = new PrescribedDrugDetails((Long) objArr[0], (Long) objArr[1], (Long) objArr[2],
                            (Integer) objArr[3], (String) objArr[4], (String) objArr[5], (String) objArr[6],
                            (String) objArr[7], (String) objArr[8], (String) objArr[9], (String) objArr[10],
                            (String) objArr[11], (String) objArr[12], (String) objArr[13], (Timestamp) objArr[14],
                            (String) objArr[15], (Integer) objArr[16], (String) objArr[17],
                            Long.valueOf((Integer) objArr[18]), (Timestamp) objArr[19], (Integer) objArr[20], (Boolean) objArr[21]);

                else
                    prescribedDrugDetails = new PrescribedDrugDetails((Long) objArr[0], (Long) objArr[1], (Long) objArr[2],
                            (Integer) objArr[3], (String) objArr[4], (String) objArr[5], (String) objArr[6],
                            (String) objArr[7], (String) objArr[8], (String) objArr[9], (String) objArr[10],
                            (String) objArr[11], (String) objArr[12], (String) objArr[13], (Timestamp) objArr[14],
                            (String) objArr[15], (Integer) objArr[20], (Boolean) objArr[21]);
                prescribedMedicineList.add(prescribedDrugDetails);
            }
        }
        return prescribedMedicineList;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        ID = iD;
    }

    public Long getPrescribedDrugID() {
        return prescribedDrugID;
    }

    public void setPrescribedDrugID(Long prescribedDrugID) {
        this.prescribedDrugID = prescribedDrugID;
    }

    public Long getBeneficiaryRegID() {
        return beneficiaryRegID;
    }

    public void setBeneficiaryRegID(Long beneficiaryRegID) {
        this.beneficiaryRegID = beneficiaryRegID;
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

    public Long getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(Long prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public Integer getDrugID() {
        return drugID;
    }

    public void setDrugID(Integer drugID) {
        this.drugID = drugID;
    }

    public Boolean getIsEDL() {
        return isEDL;
    }

    public void setIsEDL(Boolean isEDL) {
        this.isEDL = isEDL;
    }

    public String getGenericDrugName() {
        return genericDrugName;
    }

    public void setGenericDrugName(String genericDrugName) {
        this.genericDrugName = genericDrugName;
    }

    public String getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
    }

    public String getDrugStrength() {
        return drugStrength;
    }

    public void setDrugStrength(String drugStrength) {
        this.drugStrength = drugStrength;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuartionUnit() {
        return duartionUnit;
    }

    public void setDuartionUnit(String duartionUnit) {
        this.duartionUnit = duartionUnit;
    }

    public String getRelationToFood() {
        return relationToFood;
    }

    public void setRelationToFood(String relationToFood) {
        this.relationToFood = relationToFood;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }

    public void setSpecialInstruction(String specialInstruction) {
        this.specialInstruction = specialInstruction;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getItemStockEntryID() {
        return itemStockEntryID;
    }

    public void setItemStockEntryID(Integer itemStockEntryID) {
        this.itemStockEntryID = itemStockEntryID;
    }

    public Integer getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(Integer facilityID) {
        this.facilityID = facilityID;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getQuantityInHand() {
        return quantityInHand;
    }

    public void setQuantityInHand(Integer quantityInHand) {
        this.quantityInHand = quantityInHand;
    }

    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getQtyInHand() {
        return qtyInHand;
    }

    public void setQtyInHand(Long qtyInHand) {
        this.qtyInHand = qtyInHand;
    }

    public Integer getQtyPrescribed() {
        return qtyPrescribed;
    }

    public void setQtyPrescribed(Integer qtyPrescribed) {
        this.qtyPrescribed = qtyPrescribed;
    }

}
