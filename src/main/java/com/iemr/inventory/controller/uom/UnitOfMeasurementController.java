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
package com.iemr.inventory.controller.uom;

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

import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.service.uom.UomInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class UnitOfMeasurementController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private UomInter uomInter;

    @CrossOrigin()
    @Operation(summary = "Create unit of measurement")
    @PostMapping(value = "/createUom", headers = "Authorization", produces = {
            "application/json"})
    public String createUom(@RequestBody String createUom) {

        OutputResponse response = new OutputResponse();

        try {

            M_Uom[] UomData = InputMapper.gson().fromJson(createUom, M_Uom[].class);
            List<M_Uom> saveUomData = Arrays.asList(UomData);

            ArrayList<M_Uom> saveData = uomInter.createDrugtypeData(saveUomData);

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get unit of measurement")
    @GetMapping(value = "/getUom", headers = "Authorization", produces = {
            "application/json"})
    public String getUom(@RequestBody String getUom) {

        OutputResponse response = new OutputResponse();

        try {

            M_Uom UomData = InputMapper.gson().fromJson(getUom, M_Uom.class);

            ArrayList<M_Uom> getData = uomInter.createDrugtypeData(UomData.getProviderServiceMapID());

            response.setResponse(getData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Edit unit of measurement")
    @PostMapping(value = "/editUom", headers = "Authorization", produces = {
            "application/json"})
    public String editUom(@RequestBody String editUom) {

        OutputResponse response = new OutputResponse();

        try {

            M_Uom UomData = InputMapper.gson().fromJson(editUom, M_Uom.class);

            M_Uom geteditedData = uomInter.editDrugtypeData(UomData.getuOMID());
            geteditedData.setuOMName(UomData.getuOMName());
            geteditedData.setuOMDesc(UomData.getuOMDesc());
            geteditedData.setuOMCode(UomData.getuOMCode());
            geteditedData.setStatus(UomData.getStatus());
            geteditedData.setModifiedBy(UomData.getModifiedBy());

            M_Uom editedData = uomInter.saveeditedData(geteditedData);

            response.setResponse(editedData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Delete unit of measurement")
    @DeleteMapping(value = "/deleteUom", headers = "Authorization", produces = {
            "application/json"})
    public String deleteUom(@RequestBody String editUom) {

        OutputResponse response = new OutputResponse();

        try {

            M_Uom UomData = InputMapper.gson().fromJson(editUom, M_Uom.class);

            M_Uom geteditedData = uomInter.editDrugtypeData(UomData.getuOMID());
            geteditedData.setDeleted(UomData.getDeleted());

            M_Uom deletedData = uomInter.saveeditedData(geteditedData);

            response.setResponse(deletedData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

}
