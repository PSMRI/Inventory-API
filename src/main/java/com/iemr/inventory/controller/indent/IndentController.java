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
package com.iemr.inventory.controller.indent;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemIndent;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.service.indent.IndentService;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@Controller
@RequestMapping("/indentController")
@RestController
public class IndentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    IndentService IndentService;

    @CrossOrigin()
    @Operation(summary = "Partial search indent items")
    @PostMapping(value = "/partialsearchindentitems", headers = "Authorization", produces = {"application/json"})
    public String createStore(
            @Param("{\"itemName\":\"String\", \"facilityID\":\"Integer\"}") @RequestBody ItemIndent item) {
        logger.info("partialsearchindentitems request " + item.toString());
        OutputResponse response = new OutputResponse();
        try {

            String saveData = "Invalid Store Type";
            List<ItemfacilitymappingIndent> s = IndentService.findItemIndent(item.getFacilityID(),
                    item.getItemName().trim());
            saveData = s.toString();

            response.setResponse(saveData);
            logger.info("partialsearchindentitems response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Create indent request")
    @PostMapping(value = "/createIndentRequest", headers = "Authorization", produces = {"application/json"})
    public String createIndentRequest(@RequestBody String request) {

        OutputResponse response = new OutputResponse();
        try {

            Indent indent = InputMapper.gson().fromJson(request, Indent.class);
            String res = IndentService.createIndentRequest(indent);
            response.setResponse(res);

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get indent history")
    @PostMapping(value = "/getIndentHistory", headers = "Authorization", produces = {"application/json"})
    public String getIndentHistory(@Param("{\"facilityID\":\"Integer\"}") @RequestBody String request) {
        logger.info("getIndentHistory request " + request);
        OutputResponse response = new OutputResponse();
        try {

            Indent indent = InputMapper.gson().fromJson(request, Indent.class);
            String res = IndentService.getIndentHistory(indent);
            response.setResponse(res);
            logger.info("getIndentHistory response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get order by indent id")
    @PostMapping(value = "/getOrdersByIndentID", headers = "Authorization", produces = {"application/json"})
    public String getOrdersByIndentID(@Param("{\"indentID\":\"Integer\"}") @RequestBody String request) {
        logger.info("getOrdersByIndentID request " + request);
        OutputResponse response = new OutputResponse();
        try {

            IndentOrder indentOrder = InputMapper.gson().fromJson(request, IndentOrder.class);
            String res = IndentService.getOrdersByIndentID(indentOrder);
            response.setResponse(res);
            logger.info("getOrdersByIndentID response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get indent worklist")
    @PostMapping(value = "/getIndentWorklist", headers = "Authorization", produces = {"application/json"})
    public String getIndentWorklist(@Param("{\"facilityID\":\"Integer\"}") @RequestBody String request) {
        logger.info("getIndentWorklist request " + request);
        OutputResponse response = new OutputResponse();
        try {

            IndentOrder indentOrder = InputMapper.gson().fromJson(request, IndentOrder.class);
            String res = IndentService.getIndentWorklist(indentOrder);
            response.setResponse(res);
            logger.info("getIndentWorklist response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Get indent order worklist")
    @PostMapping(value = "/getIndentOrderWorklist", headers = "Authorization", produces = {"application/json"})
    public String getIndentOrderWorklist(
            @Param("{\"indentID\":\"Integer\",\"vanID\":\"Integer\" }") @RequestBody String request) {
        logger.info("getIndentOrderWorklist request " + request);
        OutputResponse response = new OutputResponse();
        try {

            IndentOrder indentOrder = InputMapper.gson().fromJson(request, IndentOrder.class);
            String res = IndentService.getIndentOrderWorklist(indentOrder);
            response.setResponse(res);
            logger.info("getIndentOrderWorklist response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Issue indent")
    @PostMapping(value = "/issueIndent", headers = "Authorization", produces = {
            "application/json"})
    public String issueIndent(@Param("{\"facilityID\":\"Integer\"}") @RequestBody String request) {
        logger.info("issueIndent request " + request);
        OutputResponse response = new OutputResponse();
        try {

            IndentIssue[] array = InputMapper.gson().fromJson(request, IndentIssue[].class);
            String res = IndentService.issueIndent(array);
            response.setResponse(res);
            logger.info("issueIndent response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Cancel indent order")
    @PostMapping(value = "/cancelIndentOrder", headers = "Authorization", produces = {"application/json"})
    public String cancelIndentOrder(@Param("{\"indentID\":\"Integer\"}") @RequestBody String request) {
        logger.info("cancelIndentOrder request " + request);
        OutputResponse response = new OutputResponse();
        try {

            Indent indent = InputMapper.gson().fromJson(request, Indent.class);
            String res = IndentService.cancelIndentOrder(indent);
            response.setResponse(res);
            logger.info("cancelIndentOrder response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Receive indent")
    @PostMapping(value = "/receiveIndent", headers = "Authorization", produces = {
            "application/json"})
    public String receiveIndent(@RequestBody String request) {
        logger.info("receiveIndent request " + request);
        OutputResponse response = new OutputResponse();
        try {

            Indent indent = InputMapper.gson().fromJson(request, Indent.class);
            String res = IndentService.receiveIndent(indent);
            response.setResponse(res);
            logger.info("receiveIndent response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Update indent order")
    @PostMapping(value = "/updateIndentOrder", headers = "Authorization", produces = {"application/json"})
    public String updateIndentOrder(@RequestBody String request) {
        logger.info("updateIndentOrder request " + request);
        OutputResponse response = new OutputResponse();
        try {

            Indent indent = InputMapper.gson().fromJson(request, Indent.class);
            String res = IndentService.updateIndentOrder(indent);
            response.setResponse(res);
            logger.info("updateIndentOrder response " + response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);
        }
        /**
         * sending the response...
         */
        return response.toString();
    }
}
