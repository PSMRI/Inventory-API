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
package com.iemr.inventory.controller.stockExit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockExit.StoreSelfConsumption;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.data.stockExit.T_StockTransfer;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.service.stockExit.StockExitServiceImpl;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class StockExitController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	StockExitServiceImpl stockExitService;

	@ApiOperation(value = "Patient issue", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/patientIssue", headers = "Authorization", method = { RequestMethod.POST })
	public String patientIssue(@RequestBody T_PatientIssue patientIssue) {

		OutputResponse output = new OutputResponse();

		try {

			Integer value = stockExitService.issuePatientDrugs(patientIssue);
			if (value == 1) {
				output.setResponse("Successfully Created");
			} else {
				throw new Exception("Error occured while saving the request");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			output.setError(e);
		}
		return output.toString();
	}

	@ApiOperation(value = "Store self consumption", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/storeSelfConsumption", headers = "Authorization", method = { RequestMethod.POST })
	public String storeSelfConsumption(@RequestBody StoreSelfConsumption storeSelfConsumption) {

		OutputResponse output = new OutputResponse();

		try {

			Integer value = stockExitService.storeSelfConsumption(storeSelfConsumption);
			if (value == 1) {
				output.setResponse("Successfully Created");
			} else {
				throw new Exception("Error occured while saving the request");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			output.setError(e);
		}
		return output.toString();
	}

	@ApiOperation(value = "Store transfer", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/storeTransfer", headers = "Authorization", method = { RequestMethod.POST })
	public String storeTransfer(@RequestBody T_StockTransfer stockTransfer) {

		OutputResponse output = new OutputResponse();

		try {

			Integer value = stockExitService.storeTransfer(stockTransfer);
			if (value == 1) {
				output.setResponse("Successfully Created");
			} else {

				throw new Exception("Error occured while saving the request");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			output.setError(e);
		}
		return output.toString();
	}

	@ApiOperation(value = "Get patient issue", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getPatientissue", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String getPatientissue(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {
			List<T_PatientIssue> getData = stockExitService.getpatientIssue(itemStockinput);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get self consumption", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getSelfConsumption", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getSelfConsumption(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {

			List<StoreSelfConsumption> getData = stockExitService.getstoreSelfConsumption(itemStockinput);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get store transfer", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getStoreTransfer", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getStoreTransfer(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {
			List<T_StockTransfer> getData = stockExitService.getStoreTransfer(itemStockinput);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get patient issue all detail", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getPatientissueAllDetail", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getPatientissueAllDetail(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {

			T_PatientIssue getData = stockExitService
					.getPatientissueAllDetail((Long) itemStockinput.getPatientIssueID());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get patient issue item entry", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getPatientissueItemEntry", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getPatientissueItemEntry(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockExitMap> getData = stockExitService.getpatientIssueItemLIst(itemStockinput);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get self consumption item entry", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getSelfConsumptionItemEntry", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getSelfConsumptionItemEntry(@RequestBody String input) {

		OutputResponse response = new OutputResponse();

		try {
			ItemStockEntryinput itemStockinput = InputMapper.gson().fromJson(input, ItemStockEntryinput.class);

			List<ItemStockExitMap> getData = stockExitService.getstoreSelfConsumptionItemList(itemStockinput);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get store transfer item entry", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getStoreTransferItemEntry", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getStoreTransferItemEntry(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockExitMap> getData = stockExitService.getStoreTransferItemEntry(itemStockinput);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}
}
