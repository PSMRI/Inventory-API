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
package com.iemr.inventory.service.dispenseagainst_rx;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.iemr.inventory.data.commonutility.CommonUtilityClass;
import com.iemr.inventory.data.dispenseagainst_rx.PrescribedDrugDetails;
import com.iemr.inventory.repo.dispenseagainst_rx.PrescribedDrugDetailsRepo;
import com.iemr.inventory.utils.mapper.InputMapper;

@Service
public class DispenseAgainstRXimpl implements DispenseAgainstRX {

    @Autowired(required = false)
    private PrescribedDrugDetailsRepo prescribedDrugDetailsRepo;

    public String getPrescribedMedicines(String requestOBJ) {
        CommonUtilityClass commonUtilityClass = InputMapper.gson().fromJson(requestOBJ, CommonUtilityClass.class);
        if (commonUtilityClass != null) {
            Map<String, Object> returnOBJ = new HashMap<>();

            Map<String, Object> itemMap = null;
            ArrayList<Map<String, Object>> itemList = new ArrayList<>();

            Map<String, Object> batchMap = null;
            ArrayList<Map<String, Object>> batchList = null;

            ArrayList<Object[]> prescribedDrugDetailsListRS = prescribedDrugDetailsRepo
                    .getPrescribedMedicinesWithDetails(commonUtilityClass.getBeneficiaryRegID(),
                            commonUtilityClass.getVisitCode(), commonUtilityClass.getFacilityID());

            ArrayList<PrescribedDrugDetails> prescribedMedicineList = PrescribedDrugDetails
                    .getPrescribedMedicines(prescribedDrugDetailsListRS);

            if (prescribedMedicineList != null && prescribedMedicineList.size() > 0) {

                returnOBJ.put("prescriptionID", prescribedMedicineList.get(0).getPrescriptionID());
                returnOBJ.put("beneficiaryRegID", prescribedMedicineList.get(0).getBeneficiaryRegID());
                returnOBJ.put("visitCode", prescribedMedicineList.get(0).getVisitCode());
                returnOBJ.put("consultantName", prescribedMedicineList.get(0).getCreatedBy());

                for (PrescribedDrugDetails obj : prescribedMedicineList) {
                    if (itemMap == null
                            || (itemMap.containsKey("drugID") && !itemMap.get("drugID").equals(obj.getDrugID()))) {
                        itemMap = new HashMap<>();
                        itemMap.put("drugID", obj.getDrugID());
                        itemMap.put("genericDrugName", obj.getGenericDrugName());
                        itemMap.put("drugForm", obj.getDrugForm());
                        itemMap.put("drugStrength", obj.getDrugStrength());
                        itemMap.put("dose", obj.getDose());
                        itemMap.put("route", obj.getRoute());
                        itemMap.put("frequency", obj.getFrequency());
                        itemMap.put("duration", obj.getDuration());
                        itemMap.put("durationUnit", obj.getDuartionUnit());
                        itemMap.put("specialInstruction", obj.getSpecialInstruction());
                        itemMap.put("qtyPrescribed", obj.getQtyPrescribed());
                        itemMap.put("isEDL", obj.getIsEDL());
                        batchMap = new HashMap<>();
                        batchList = new ArrayList<>();
                        batchMap.put("batchNo", obj.getBatchNo());
                        batchMap.put("itemStockEntryID", obj.getItemStockEntryID());
                        batchMap.put("qty", obj.getQtyInHand());
                        batchMap.put("expiryDate", obj.getExpiryDate());

                        Long expiryDateInDays = calculateExpiryDateInDays(obj.getExpiryDate());
                        if (expiryDateInDays != null) {
                            batchMap.put("expiresIn", expiryDateInDays);

                            if (expiryDateInDays > 0 && obj.getQtyInHand() > 0)
                                batchList.add(batchMap);
                        }
                        itemMap.put("batchList", batchList);
                        itemList.add(itemMap);

                        returnOBJ.put("itemList", itemList);
                    } else {
                        batchMap = new HashMap<>();
                        batchMap.put("batchNo", obj.getBatchNo());
                        batchMap.put("itemStockEntryID", obj.getItemStockEntryID());
                        batchMap.put("qty", obj.getQtyInHand());
                        batchMap.put("expiryDate", obj.getExpiryDate());

                        Long expiryDateInDays = calculateExpiryDateInDays(obj.getExpiryDate());
                        if (expiryDateInDays != null) {
                            batchMap.put("expiresIn", expiryDateInDays);

                            if (expiryDateInDays > 0 && obj.getQtyInHand() > 0)
                                batchList.add(batchMap);
                        }
                        // itemMap.put("batchList", batchList);
                    }
                }
            } else {
            }

            return new Gson().toJson(returnOBJ);
        } else {
            return null;
        }

    }

    public Long calculateExpiryDateInDays(Timestamp expDate) {
        if (expDate != null) {
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            Long diff = (expDate.getTime() - currentDate.getTime()) / 86400000;
            return diff;
        } else
            return null;

    }
}
