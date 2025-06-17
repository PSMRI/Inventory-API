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
package com.iemr.inventory.controller.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.service.report.CRMReportService;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/crmReportController", headers = "Authorization", consumes = "application/json", produces = "application/json")
@RestController
public class CRMReportController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	CRMReportService crmReportService;

	@ApiOperation(value = "Get inward stock report")
	@PostMapping(value = "/getInwardStockReport")
	public String getInwardStockReport(@RequestBody String request) {
		logger.info("getInwardStockReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getInwardStockReport(report);
			response.setResponse(res);
			logger.info("getInwardStockReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get expiry report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getExpiryReport", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String getExpiryReport(@RequestBody String request) {
		logger.info("getExpiryReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getExpiryReport(report);
			response.setResponse(res);
			logger.info("getExpiryReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get consumption report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getConsumptionReport", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getConsumptionReport(@RequestBody String request) {
		logger.info("getConsumptionReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockExitReport report = InputMapper.gson().fromJson(request, ItemStockExitReport.class);
			String res = crmReportService.getConsumptionReport(report);
			response.setResponse(res);
			logger.info("getConsumptionReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get benificiary drug issue report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getBenDrugIssueReport", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getBenDrugIssueReport(@RequestBody String request) {
		logger.info("getBenDrugIssueReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			PatientIssueExitReport report = InputMapper.gson().fromJson(request, PatientIssueExitReport.class);
			String res = crmReportService.getBenDrugIssueReport(report);
			response.setResponse(res);
			logger.info("getBenDrugIssueReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get daily stock detail report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getDailyStockDetailReport", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getDailyStockDetailReport(@RequestBody String request) {
		logger.info("getDailyStockDetailReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getDailyStockDetailsReport(report);
			response.setResponse(res);
			logger.info("getDailyStockDetailReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get daily stock summary report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getDailyStockSummaryReport", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getDailyStockSummaryReport(@RequestBody String request) {
		logger.info("getDailyStockSummaryReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getDailyStockSummaryReport(report);
			response.setResponse(res);
			logger.info("getDailyStockSummaryReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get monthly report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getMonthlyReport", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getMonthlyReport(@RequestBody String request) {
		logger.info("getMonthlyReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getMonthlyReport(report);
			response.setResponse(res);
			logger.info("getMonthlyReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get yearly report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getYearlyReport", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String getYearlyReport(@RequestBody String request) {
		logger.info("getYearlyReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getYearlyReport(report);
			response.setResponse(res);
			logger.info("getYearlyReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get short expiry report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getShortExpiryReport", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getShortExpiryReport(@RequestBody String request) {
		logger.info("getShortExpiryReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getShortExpiryReport(report);
			response.setResponse(res);
			logger.info("getShortExpiryReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}

	@ApiOperation(value = "Get transit report", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getTransitReport", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getTransitReport(@RequestBody String request) {
		logger.info("getTransitReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		try {

			ItemStockEntryReport report = InputMapper.gson().fromJson(request, ItemStockEntryReport.class);
			String res = crmReportService.getTransitReport(report);
			response.setResponse(res);
			logger.info("getTransitReport response " + response.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}
}
