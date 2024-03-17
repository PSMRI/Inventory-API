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
package com.iemr.inventory.service.visit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.repo.visit.BeneficiaryFlowStatusRepo;
import com.iemr.inventory.repo.visit.VisitRepo;
import com.iemr.inventory.utils.config.ConfigProperties;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.exception.InventoryException;
import com.iemr.inventory.utils.http.HttpUtils;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

@Service
public class VisitServiceImpl implements VisitService {

    private static final String COMMON_BASE_URL = "COMMON_BASE_URL";
    private static HttpUtils httpUtils = new HttpUtils();
    @Autowired(required = false)
    VisitRepo visitRepo;
    @Autowired(required = false)
    BeneficiaryFlowStatusRepo beneficiaryFlowStatusRepo;
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private InputMapper inputMapper = new InputMapper();
    private String identityBaseURL = ConfigProperties.getPropertyByName("common-api-url");

    @Override
    public BeneficiaryModel getVisitDetail(String benrID, Integer providerservicemapID, String auth) throws Exception {
        logger.info("Calling Common API : providerservicemapID" + providerservicemapID);
        logger.info("Calling Common API : benrID" + benrID);
        List<BeneficiaryModel> benModel = getBeneficiaryListByIDs(benrID, auth);
        logger.info("Got result from API");
        BeneficiaryModel beneficiaryModel = new BeneficiaryModel();
        if (benModel.size() > 0) {
            beneficiaryModel = benModel.get(0);
            logger.info("Getting visit Details");
            beneficiaryModel.setBenVisitDetail(visitRepo.findBybeneficiaryRegIDAndProviderServiceMapID(
                    beneficiaryModel.getBeneficiaryRegID(), providerservicemapID));
            logger.info("Getting visit flow Details");
            beneficiaryModel.setBeneficiaryFlowStatus(beneficiaryFlowStatusRepo
                    .findByBeneficiaryRegIDAndProviderServiceMapIdAndDoctorFlagInAndBenVisitIDNotNull(
                            beneficiaryModel.getBeneficiaryRegID(), providerservicemapID,
                            new Short[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}));// previously {9,2,3} as kumar said to change
            // it for any visit medicine dispense is
            // possible.
        } else {
            throw new InventoryException("Invalid Beneficiary ID");
        }
        return beneficiaryModel;
    }

    public List<BeneficiaryModel> getBeneficiaryListByIDs(String benrID, String auth) throws Exception {

        List<BeneficiaryModel> listBenDetailForOutboundDTO = new ArrayList<BeneficiaryModel>();

        JsonParser parser = new JsonParser();

        String result;
        HashMap<String, Object> header = new HashMap<>();
        if (auth != null) {
            header.put("Authorization", auth);
        }

        result = httpUtils.post(ConfigProperties.getPropertyByName("common-api-url-searchuserbyid")
                .replace(COMMON_BASE_URL, identityBaseURL), benrID, header);
        OutputResponse identityResponse = inputMapper.gson().fromJson(result, OutputResponse.class);
        if (identityResponse.getStatusCode() != 200) {
            throw new InventoryException("Invalid BeneficiaryID");
        }
        JsonObject responseObj = (JsonObject) parser.parse(result);

        JsonArray responseArray = responseObj.get("data").getAsJsonArray();

        for (JsonElement jsonElement : responseArray) {

            BeneficiaryModel callRequest = (BeneficiaryModel) inputMapper.gson().fromJson(jsonElement.toString(),
                    BeneficiaryModel.class);
            listBenDetailForOutboundDTO.add(callRequest);

        }
        return listBenDetailForOutboundDTO;
    }

    public List<BeneficiaryModel> getBeneficiaryListBySearch(String benrID, String auth) throws IEMRException {

        List<BeneficiaryModel> listBenDetailForOutboundDTO = new ArrayList<BeneficiaryModel>();

        JsonParser parser = new JsonParser();

        String result;
        HashMap<String, Object> header = new HashMap<>();
        if (auth != null) {
            header.put("Authorization", auth);
        }

        result = httpUtils.post(ConfigProperties.getPropertyByName("common-api-url-searchBeneficiary")
                .replace(COMMON_BASE_URL, identityBaseURL), benrID, header);
        OutputResponse identityResponse = inputMapper.gson().fromJson(result, OutputResponse.class);
        if (identityResponse.getStatusCode() == OutputResponse.USERID_FAILURE) {
            throw new IEMRException(identityResponse.getErrorMessage());
        }
        JsonObject responseObj = (JsonObject) parser.parse(result);

        JsonArray responseArray = responseObj.get("data").getAsJsonArray();

        for (JsonElement jsonElement : responseArray) {

            BeneficiaryModel callRequest = (BeneficiaryModel) inputMapper.gson().fromJson(jsonElement.toString(),
                    BeneficiaryModel.class);
            listBenDetailForOutboundDTO.add(callRequest);

        }
        return listBenDetailForOutboundDTO;
    }

    @Override
    public List<BeneficiaryModel> getVisitFromAdvanceSearch(String beneficiaryModel, String auth) throws IEMRException {

        return getBeneficiaryListBySearch(beneficiaryModel, auth);
    }
}
