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
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemIndent;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.service.indent.IndentService;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/indentController")
@RestController
public class IndentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	IndentService IndentService;

	@ApiOperation(value = "Partial search indent items", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/partialsearchindentitems", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String createStore(
			@ApiParam("{\"itemName\":\"String\", \"facilityID\":\"Integer\"}") @RequestBody ItemIndent item) {
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

	@ApiOperation(value = "Create indent request", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/createIndentRequest", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
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

	@ApiOperation(value = "Get indent history", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getIndentHistory", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getIndentHistory(@ApiParam("{\"facilityID\":\"Integer\"}") @RequestBody String request) {
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

	@ApiOperation(value = "Get order by indent id", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getOrdersByIndentID", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getOrdersByIndentID(@ApiParam("{\"indentID\":\"Integer\"}") @RequestBody String request) {
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

	@ApiOperation(value = "Get indent worklist", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getIndentWorklist", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getIndentWorklist(@ApiParam("{\"facilityID\":\"Integer\"}") @RequestBody String request) {
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

	@ApiOperation(value = "Get indent order worklist", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getIndentOrderWorklist", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getIndentOrderWorklist(
			@ApiParam("{\"indentID\":\"Integer\",\"vanID\":\"Integer\" }") @RequestBody String request) {
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

	@ApiOperation(value = "Issue indent", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/issueIndent", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String issueIndent(@ApiParam("{\"facilityID\":\"Integer\"}") @RequestBody String request) {
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

	@ApiOperation(value = "Cancel indent order", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/cancelIndentOrder", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String cancelIndentOrder(@ApiParam("{\"indentID\":\"Integer\"}") @RequestBody String request) {
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

	@ApiOperation(value = "Receive indent", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/receiveIndent", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
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

	@ApiOperation(value = "Update indent order", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/updateIndentOrder", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
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
