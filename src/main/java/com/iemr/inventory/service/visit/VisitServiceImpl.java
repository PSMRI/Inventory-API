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
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.repo.visit.BeneficiaryFlowStatusRepo;
import com.iemr.inventory.repo.visit.VisitRepo;
import com.iemr.inventory.utils.CookieUtil;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.exception.InventoryException;
import com.iemr.inventory.utils.http.HttpUtils;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
@PropertySource("classpath:application.properties")
public class VisitServiceImpl implements VisitService {


	@Value("${common-api-url-searchBeneficiary}")
	private String commonApiUrlSearchBeneficiary;
	

	@Value("${common-api-url-searchuserbyid}")
	private String commonApiUrlSearchUserById;
	
	
	@Autowired(required = false)
	VisitRepo visitRepo;

	@Autowired(required = false)
	BeneficiaryFlowStatusRepo beneficiaryFlowStatusRepo;
	   
	@Autowired
	private CookieUtil cookieUtil;
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	private static HttpUtils httpUtils = new HttpUtils();
	private InputMapper inputMapper = new InputMapper();


	@Override
	public BeneficiaryModel getVisitDetail(String beneficiaryID, Integer providerservicemapID, String auth) throws Exception {
		logger.info("Calling Common API : providerservicemapID" + providerservicemapID);
		logger.info("Calling Common API : beneficiaryID" + beneficiaryID);
		List<BeneficiaryModel> benModel = getBeneficiaryListByIDs(beneficiaryID, auth);
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
							new Short[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }));// previously {9,2,3} as kumar said to change
																			// it for any visit medicine dispense is
																			// possible.
		} else {
			throw new InventoryException("Invalid Beneficiary ID");
		}
		return beneficiaryModel;
	}

	public List<BeneficiaryModel> getBeneficiaryListByIDs(String beneficiaryID, String auth) throws Exception {

	    List<BeneficiaryModel> listBenDetailForOutboundDTO = new ArrayList<>();

	    // Get JWT token from cookie
	    HttpServletRequest requestHeader = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
	            .getRequest();
	    String jwtTokenFromCookie = cookieUtil.getJwtTokenFromCookie(requestHeader);

	    // Prepare headers
	    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	    headers.add("Content-Type", "application/json");
	    if (auth != null) {
	        headers.add("Authorization", auth);
	    }
	    headers.add("Cookie", "Jwttoken=" + jwtTokenFromCookie);

	    // Prepare JSON body with Gson or plain String
	    String jsonBody = "{\"beneficiaryID\":" + beneficiaryID + "}";

	    HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);

	    RestTemplate restTemplate = new RestTemplate();

	    // Call the API
	    ResponseEntity<String> response = restTemplate.exchange(
	            commonApiUrlSearchUserById, HttpMethod.POST, requestEntity, String.class);

	    if (response.getStatusCodeValue() != 200 || !response.hasBody()) {
	        throw new InventoryException("No response or invalid status from beneficiary lookup service.");
	    }

	    String responseStr = response.getBody();
	    JsonObject responseObj = InputMapper.gson().fromJson(responseStr, JsonObject.class);

	    int statusCode = responseObj.get("statusCode").getAsInt();
	    if (statusCode != 200) {
	        throw new InventoryException("Invalid BeneficiaryRegID");
	    }

	    JsonArray responseArray = responseObj.getAsJsonArray("data");

	    for (JsonElement element : responseArray) {
	        BeneficiaryModel beneficiary = InputMapper.gson().fromJson(element.toString(), BeneficiaryModel.class);
	        listBenDetailForOutboundDTO.add(beneficiary);
	    }

	    return listBenDetailForOutboundDTO;
	}
	
	public List<BeneficiaryModel> getBeneficiaryListBySearch(String benrID, String auth) throws IEMRException {

	    List<BeneficiaryModel> listBenDetailForOutboundDTO = new ArrayList<>();

	    // Get JWT token from cookie
	    HttpServletRequest requestHeader = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
	            .getRequest();
	    String jwtTokenFromCookie = cookieUtil.getJwtTokenFromCookie(requestHeader);

	    // Prepare headers
	    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
	    headers.add("Content-Type", "application/json");
	    if (auth != null) {
	        headers.add("Authorization", auth);
	    }
	    headers.add("Cookie", "Jwttoken=" + jwtTokenFromCookie);

	    // Build request body as JSON
	    JsonObject jsonBody = new JsonObject();
	    jsonBody.addProperty("benrID", benrID);

	    HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody.toString(), headers);

		RestTemplate restTemplate = new RestTemplate();

	    // Send POST request
	    ResponseEntity<String> response = restTemplate.exchange(
	            commonApiUrlSearchBeneficiary, HttpMethod.POST, requestEntity, String.class);

	    if (response.getStatusCodeValue() != 200 || !response.hasBody()) {
	        throw new IEMRException("No response or invalid status from beneficiary search.");
	    }

	    String responseStr = response.getBody();

	    // Parse and validate response
	    JsonObject responseObj = InputMapper.gson().fromJson(responseStr, JsonObject.class);
	    OutputResponse identityResponse = InputMapper.gson().fromJson(responseStr, OutputResponse.class);

	    if (identityResponse.getStatusCode() == OutputResponse.USERID_FAILURE) {
	        throw new IEMRException(identityResponse.getErrorMessage());
	    }

	    JsonArray responseArray = responseObj.getAsJsonArray("data");

	    for (JsonElement jsonElement : responseArray) {
	        BeneficiaryModel beneficiary = InputMapper.gson().fromJson(jsonElement.toString(), BeneficiaryModel.class);
	        listBenDetailForOutboundDTO.add(beneficiary);
	    }

	    return listBenDetailForOutboundDTO;
	}



	@Override
	public List<BeneficiaryModel> getVisitFromAdvanceSearch(String beneficiaryModel, String auth) throws IEMRException {

		return getBeneficiaryListBySearch(beneficiaryModel, auth);
	}
}
