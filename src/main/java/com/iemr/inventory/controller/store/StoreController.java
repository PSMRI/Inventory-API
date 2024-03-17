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
package com.iemr.inventory.controller.store;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.store.M_Van;
import com.iemr.inventory.service.store.StoreService;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class StoreController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private StoreService storeService;

    @CrossOrigin()
    @Operation(summary = "Create store")
    @PostMapping(value = "/createStore", headers = "Authorization", produces = {
            "application/json"})
    public String createStore(@RequestBody String store) {

        OutputResponse response = new OutputResponse();
        try {

            String saveData = "Invalid Store Type";

            M_Facility[] mainStore = InputMapper.gson().fromJson(store, M_Facility[].class);
            List<M_Facility> addMainStore = Arrays.asList(mainStore);

            saveData = storeService.addAllMainStore(addMainStore).toString();

            response.setResponse(saveData);

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Edit store")
    @PostMapping(value = "/editStore", headers = "Authorization", produces = {
            "application/json"})
    public String editStore(@RequestBody String store) {

        OutputResponse response = new OutputResponse();
        try {

            String saveData = "Invalid Store Type";

            M_Facility mainStore = InputMapper.gson().fromJson(store, M_Facility.class);
            M_Facility mainStoreUpdate = storeService.getMainStore(mainStore.getFacilityID());

            mainStoreUpdate.setFacilityDesc(mainStore.getFacilityDesc());

            mainStoreUpdate.setModifiedBy(mainStore.getModifiedBy());

            saveData = storeService.createMainStore(mainStoreUpdate).toString();

            response.setResponse(saveData);

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get all store")
    @PostMapping(value = "/getAllStore/{providerServiceMapID}", headers = "Authorization", produces = {"application/json"})
    public String getAllStore(@PathVariable("providerServiceMapID") Integer providerServiceMapID) {

        OutputResponse response = new OutputResponse();
        try {

            List<M_Facility> saveData = storeService.getAllMainStore(providerServiceMapID);
            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get all active store")
    @PostMapping(value = "/getAllActiveStore", headers = "Authorization", produces = {"application/json"})
    public String getAllActiveStore(@RequestBody M_Facility providerServiceMapID) {

        OutputResponse response = new OutputResponse();
        try {

            List<M_Facility> saveData = storeService.getAllActiveStore(providerServiceMapID);
            response.setResponse(saveData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get main facility")
    @PostMapping(value = "/getMainFacility", headers = "Authorization", produces = {
            "application/json"})
    public String getMainFacility(@RequestBody String getMainFacility) {

        OutputResponse response = new OutputResponse();
        try {

            M_Facility mainStore = InputMapper.gson().fromJson(getMainFacility, M_Facility.class);
            ArrayList<M_Facility> mainStoreUpdate = storeService.getMainFacility(mainStore.getProviderServiceMapID(),
                    mainStore.getIsMainFacility());

            response.setResponse(mainStoreUpdate.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get sub facility")
    @PostMapping(value = "/getsubFacility", headers = "Authorization", produces = {
            "application/json"})
    public String getsubFacility(@RequestBody String getMainFacility) {

        OutputResponse response = new OutputResponse();
        try {

            M_Facility mainStore = InputMapper.gson().fromJson(getMainFacility, M_Facility.class);
            ArrayList<M_Facility> mainStoreUpdate = storeService.getChildFacility(mainStore.getProviderServiceMapID(),
                    mainStore.getMainFacilityID());

            response.setResponse(mainStoreUpdate.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Delete store")
    @PostMapping(value = "/deleteStore", headers = "Authorization", produces = {
            "application/json"})
    public String deleteStore(@RequestBody M_Facility facility) {

        OutputResponse response = new OutputResponse();
        try {

            M_Facility mainStoreUpdate = storeService.deleteStore(facility);

            response.setResponse(mainStoreUpdate.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setResponse(e.toString());

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get store by id")
    @PostMapping(value = "/getStoreByID", headers = "Authorization", produces = {
            "application/json"})
    public String getStoreByID(@RequestBody M_Facility facility) {

        OutputResponse response = new OutputResponse();
        try {

            M_Facility mainStoreUpdate = storeService.getStoreByID(facility.getFacilityID());

            response.setResponse(mainStoreUpdate.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setResponse(e.toString());

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get van by store id")
    @PostMapping(value = "/getVanByStoreID/{storeID}", headers = "Authorization", produces = {"application/json"})
    public String getVanByStoreID(@PathVariable("storeID") Integer storeID) {

        OutputResponse response = new OutputResponse();
        try {

            M_Van saveData = storeService.getVanByStoreID(storeID);
            if (saveData != null) {
                response.setResponse(saveData.toString());
            } else {
                response.setResponse((new M_Van()).toString());
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }
}
