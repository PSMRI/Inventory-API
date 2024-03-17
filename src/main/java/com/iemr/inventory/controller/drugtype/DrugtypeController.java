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
package com.iemr.inventory.controller.drugtype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.drugtype.M_Drugtype;
import com.iemr.inventory.service.drugtype.DrugtypeInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class DrugtypeController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private DrugtypeInter drugtypeInter;

    @CrossOrigin()
    @Operation(summary = "Create drug type")
    @PostMapping(value = "/createDrugtype", headers = "Authorization", produces = {
            "application/json"})
    public String createManufacturer(@RequestBody String createDrugtype) {

        OutputResponse response = new OutputResponse();
        try {

            M_Drugtype[] Drugtype = InputMapper.gson().fromJson(createDrugtype, M_Drugtype[].class);
            List<M_Drugtype> DrugtypeData = Arrays.asList(Drugtype);

            ArrayList<M_Drugtype> saveData = drugtypeInter.createDrugtypeData(DrugtypeData);

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Get drug type")
    @GetMapping(value = "/getDrugtype", headers = "Authorization", produces = {
            "application/json"})
    public String getManufacturer(@RequestBody String getDrugtype) {

        OutputResponse response = new OutputResponse();

        try {

            M_Drugtype Drugtype = InputMapper.gson().fromJson(getDrugtype, M_Drugtype.class);

            ArrayList<M_Drugtype> getedData = drugtypeInter.getDrugtypeData(Drugtype.getProviderServiceMapID());

            response.setResponse(getedData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Edit drug type")
    @PostMapping(value = "/editDrugtype", headers = "Authorization", produces = {
            "application/json"})
    public String editManufacturer(@RequestBody String editDrugtype) {

        OutputResponse response = new OutputResponse();

        try {

            M_Drugtype Drugtype = InputMapper.gson().fromJson(editDrugtype, M_Drugtype.class);

            M_Drugtype geteditedData = drugtypeInter.editDrugtypeData(Drugtype.getDrugTypeID());

            geteditedData.setDrugTypeName(Drugtype.getDrugTypeName());
            geteditedData.setDrugTypeDesc(Drugtype.getDrugTypeDesc());
            geteditedData.setDrugTypeCode(Drugtype.getDrugTypeCode());
            geteditedData.setStatus(Drugtype.getStatus());
            geteditedData.setModifiedBy(Drugtype.getModifiedBy());

            M_Drugtype saveeditedData = drugtypeInter.saveeditDrugtype(geteditedData);

            response.setResponse(saveeditedData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Delete drug type")
    @DeleteMapping(value = "/deleteDrugtype", headers = "Authorization", produces = {
            "application/json"})
    public String deleteManufacturer(@RequestBody String deleteDrugtype) {

        OutputResponse response = new OutputResponse();

        try {

            M_Drugtype Drugtype = InputMapper.gson().fromJson(deleteDrugtype, M_Drugtype.class);

            M_Drugtype geteditedData = drugtypeInter.editDrugtypeData(Drugtype.getDrugTypeID());

            geteditedData.setDeleted(Drugtype.getDeleted());

            M_Drugtype deletedData = drugtypeInter.saveeditDrugtype(geteditedData);

            response.setResponse(deletedData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

}
