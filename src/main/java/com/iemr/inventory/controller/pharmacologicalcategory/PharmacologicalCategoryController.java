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
package com.iemr.inventory.controller.pharmacologicalcategory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.service.pharmacologicalcategory.PharmacologicalcategoryInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class PharmacologicalCategoryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private PharmacologicalcategoryInter pharmacologicalcategoryInter;

    @CrossOrigin()
    @Operation(summary = "Create pharmacological category")
    @PostMapping(value = "/createPharmacologicalcategory", headers = "Authorization", produces = {"application/json"})
    public String createPharmacologicalcategory(@RequestBody String createPharmacologicalcategory) {

        OutputResponse response = new OutputResponse();

        try {

            M_Pharmacologicalcategory[] Pharmacologicalcategory = InputMapper.gson()
                    .fromJson(createPharmacologicalcategory, M_Pharmacologicalcategory[].class);
            List<M_Pharmacologicalcategory> createPharmacologicaldata = Arrays.asList(Pharmacologicalcategory);

            ArrayList<M_Pharmacologicalcategory> saveData = pharmacologicalcategoryInter
                    .createPharmacologicalcategory(createPharmacologicaldata);

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get pharmacological category")
    @PostMapping(value = "/getPharmacologicalcategory", headers = "Authorization", produces = {"application/json"})
    public String getPharmacologicalcategory(@RequestBody String createPharmacologicalcategory) {

        OutputResponse response = new OutputResponse();

        try {

            M_Pharmacologicalcategory Pharmacologicalcategory = InputMapper.gson()
                    .fromJson(createPharmacologicalcategory, M_Pharmacologicalcategory.class);

            ArrayList<M_Pharmacologicalcategory> saveData = pharmacologicalcategoryInter
                    .getPharmacologicalcategory(Pharmacologicalcategory.getProviderServiceMapID());

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Edit pharmacological category")
    @PostMapping(value = "/editPharmacologicalcategory", headers = "Authorization", produces = {"application/json"})
    public String editPharmacologicalcategory(@RequestBody String editPharmacologicalcategory) {

        OutputResponse response = new OutputResponse();

        try {

            M_Pharmacologicalcategory Pharmacologicalcategory = InputMapper.gson().fromJson(editPharmacologicalcategory,
                    M_Pharmacologicalcategory.class);

            M_Pharmacologicalcategory saveData = pharmacologicalcategoryInter
                    .editPharmacologicalcategory(Pharmacologicalcategory.getPharmCategoryID());

            saveData.setPharmCategoryDesc(Pharmacologicalcategory.getPharmCategoryDesc());
            saveData.setModifiedBy(Pharmacologicalcategory.getModifiedBy());
            M_Pharmacologicalcategory saveEditedData = pharmacologicalcategoryInter.saveEditedPharData(saveData);

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Delete pharmacological category")
    @PostMapping(value = "/deletePharmacologicalcategory", headers = "Authorization", produces = {"application/json"})
    public String deletePharmacologicalcategory(@RequestBody String deletePharmacologicalcategory) {

        OutputResponse response = new OutputResponse();

        try {

            M_Pharmacologicalcategory Pharmacologicalcategory = InputMapper.gson()
                    .fromJson(deletePharmacologicalcategory, M_Pharmacologicalcategory.class);

            M_Pharmacologicalcategory saveData = pharmacologicalcategoryInter
                    .editPharmacologicalcategory(Pharmacologicalcategory.getPharmCategoryID());

            saveData.setDeleted(Pharmacologicalcategory.getDeleted());

            M_Pharmacologicalcategory saveEditedData = pharmacologicalcategoryInter.saveEditedPharData(saveData);

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

}
