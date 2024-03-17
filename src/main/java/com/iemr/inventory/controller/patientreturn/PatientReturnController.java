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
package com.iemr.inventory.controller.patientreturn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.patientreturn.ItemDetailModel;
import com.iemr.inventory.data.patientreturn.PatientReturnModel;
import com.iemr.inventory.data.patientreturn.ReturnHistoryModel;
import com.iemr.inventory.data.stockExit.ItemReturnEntry;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.service.patientreturn.PatientReturnService;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RequestMapping("/patientReturnController")
@RestController
public class PatientReturnController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    PatientReturnService patientReturnService;

    @CrossOrigin()
    @Operation(summary = "Get list of item issued to patient ")
    @PostMapping(value = "/getItemNameByRegID", headers = "Authorization", produces = {"application/json"})
    public String getItemNameByRegID(
            @Param("{\"benRegID\":\"Integer\", \"facilityID\":\"Integer\"}") @RequestBody String input) {

        OutputResponse response = new OutputResponse();
        logger.info("getItemNameByRegID request " + input);
        try {
            T_PatientIssue patientIssue = InputMapper.gson().fromJson(input, T_PatientIssue.class);

            List<PatientReturnModel> getData = patientReturnService.getItemNameByRegID(patientIssue);

            response.setResponse(getData.toString());
            logger.info("getItemNameByRegID request " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Item details by beneficiary")
    @PostMapping(value = "/getItemDetailByBen", headers = "Authorization", produces = {"application/json"})
    public String getItemDetailByBen(
            @Param("{\"benRegID\":\"Integer\", \"facilityID\":\"Integer\", \"itemID\":\"Integer\"}") @RequestBody String input) {

        OutputResponse response = new OutputResponse();
        logger.info("getItemDetailByBen request " + input);
        try {
            ItemDetailModel itemDetailModel = InputMapper.gson().fromJson(input, ItemDetailModel.class);
            List<ItemDetailModel> getData = patientReturnService.getItemDetailByBen(itemDetailModel);

            response.setResponse(getData.toString());
            logger.info("getItemDetailByBen response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin
    @Operation(summary = "Update item details returned by patient")
    @PostMapping(value = "/updateQuantityReturned", headers = "Authorization", produces = "application/json")
    public String updateQuantityReturned(@RequestBody String request) {
        OutputResponse response = new OutputResponse();
        logger.info("updateQuantityReturned request " + request);
        try {
            ItemDetailModel[] itemDetailModel = InputMapper.gson().fromJson(request, ItemDetailModel[].class);
            String getData = patientReturnService.updateQuantityReturned(itemDetailModel);

            response.setResponse(getData.toString());
            logger.info("updateQuantityReturned response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get beneficiary return history")
    @PostMapping(value = "/getBenReturnHistory", headers = "Authorization", produces = {"application/json"})
    public String getBenReturnHistory(
            @Param("{\"benRegID\":\"Integer\", \"facilityID\":\"Integer\"}") @RequestBody String input) {

        OutputResponse response = new OutputResponse();
        logger.info("getBenReturnHistory request " + input);
        try {
            ItemReturnEntry itemReturnEntry = InputMapper.gson().fromJson(input, ItemReturnEntry.class);
            List<ReturnHistoryModel> getData = patientReturnService.getBenReturnHistory(itemReturnEntry);

            response.setResponse(getData.toString());
            logger.info("getBenReturnHistory response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }
}
