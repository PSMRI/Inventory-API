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
package com.iemr.inventory.controller.facilitytype;

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

import com.iemr.inventory.data.facilitytype.M_facilitytype;
import com.iemr.inventory.service.facilitytype.M_facilitytypeInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class FacilitytypeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private M_facilitytypeInter m_facilitytypeInter;

    @CrossOrigin()
    @Operation(summary = "Get facility")
    @GetMapping(value = "/getFacility", headers = "Authorization", produces = {
            "application/json"})
    public String getFacility(@RequestBody String getFacility) {

        OutputResponse response = new OutputResponse();

        try {

            M_facilitytype facilityDetails = InputMapper.gson().fromJson(getFacility, M_facilitytype.class);

            ArrayList<M_facilitytype> allFacilityData = m_facilitytypeInter
                    .getAllFicilityData(facilityDetails.getProviderServiceMapID());

            response.setResponse(allFacilityData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Add facility")
    @PostMapping(value = "/addFacility", headers = "Authorization", produces = {
            "application/json"})
    public String addFacility(@RequestBody String addFacility) {

        OutputResponse response = new OutputResponse();

        try {

            M_facilitytype[] facilityDetails = InputMapper.gson().fromJson(addFacility, M_facilitytype[].class);
            List<M_facilitytype> addfacilityDetails = Arrays.asList(facilityDetails);

            ArrayList<M_facilitytype> allFacilityData = m_facilitytypeInter.addAllFicilityData(addfacilityDetails);

            response.setResponse(allFacilityData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Edit facility")
    @PostMapping(value = "/editFacility", headers = "Authorization", produces = {
            "application/json"})
    public String editFacility(@RequestBody String editFacility) {

        OutputResponse response = new OutputResponse();

        try {

            M_facilitytype facilityDetails = InputMapper.gson().fromJson(editFacility, M_facilitytype.class);

            M_facilitytype allFacilityData = m_facilitytypeInter
                    .editAllFicilityData(facilityDetails.getFacilityTypeID());

            allFacilityData.setFacilityTypeName(facilityDetails.getFacilityTypeName());
            allFacilityData.setFacilityTypeDesc(facilityDetails.getFacilityTypeDesc());
            allFacilityData.setFacilityTypeCode(facilityDetails.getFacilityTypeCode());
            allFacilityData.setModifiedBy(facilityDetails.getModifiedBy());

            M_facilitytype saveFacilityData = m_facilitytypeInter.updateFacilityData(allFacilityData);

            response.setResponse(saveFacilityData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Delete facility")
    @DeleteMapping(value = "/deleteFacility", headers = "Authorization", produces = {
            "application/json"})
    public String deleteFacility(@RequestBody String deleteFacility) {

        OutputResponse response = new OutputResponse();

        try {

            M_facilitytype facilityDetails = InputMapper.gson().fromJson(deleteFacility, M_facilitytype.class);

            M_facilitytype allFacilityData = m_facilitytypeInter
                    .editAllFicilityData(facilityDetails.getFacilityTypeID());
            allFacilityData.setDeleted(facilityDetails.getDeleted());

            M_facilitytype saveFacilityData = m_facilitytypeInter.updateFacilityData(allFacilityData);

            response.setResponse(saveFacilityData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }
}
