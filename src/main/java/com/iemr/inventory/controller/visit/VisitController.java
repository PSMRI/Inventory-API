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
package com.iemr.inventory.controller.visit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.visit.BenVisitDetail;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.service.visit.VisitService;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RestController
public class VisitController {

    @Autowired
    VisitService visitService;

    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @CrossOrigin()
    @Operation(summary = "Get visit details from beneficary id")
    @PostMapping(value = "/getVisitFromBenID", headers = "Authorization", produces = {"application/json"})
    public String getVisitFromBenRegID(@RequestBody BenVisitDetail benVisitDetail, HttpServletRequest httpRequest) {

        OutputResponse response = new OutputResponse();
        String auth = httpRequest.getHeader("authorization");

        try {
            logger.info("getVisitFromBenRegID recieved object " + benVisitDetail.toString());
            BenVisitDetail newbenVisitDetail = new BenVisitDetail();
            newbenVisitDetail.setBeneficiaryID(benVisitDetail.getBeneficiaryID());
            BeneficiaryModel saveData = visitService.getVisitDetail(newbenVisitDetail.toString(),
                    benVisitDetail.getProviderServiceMapID(), auth);

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            response.setError(e);
            logger.error("Error in getVisitFromBenID", e.getMessage());

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Get visit details from advance search")
    @PostMapping(value = "/getVisitFromAdvanceSearch", headers = "Authorization", produces = {"application/json"})
    public String getVisitFromAdvanceSearch(@RequestBody String model, HttpServletRequest httpRequest) {

        OutputResponse response = new OutputResponse();
        String auth = httpRequest.getHeader("authorization");

        try {

            List<BeneficiaryModel> saveData = visitService.getVisitFromAdvanceSearch(model, auth);

            response.setResponse(saveData.toString());

        } catch (Exception e) {
            response.setError(e);
            logger.error("Error in getVisitFromBenID", e.getMessage());

        }
        return response.toString();

    }
}
