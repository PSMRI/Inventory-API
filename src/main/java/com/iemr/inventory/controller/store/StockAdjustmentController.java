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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.stockadjustment.StockAdjustment;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.service.stockadjustment.StockAdjustmentServiceImpl;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class StockAdjustmentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	StockAdjustmentServiceImpl stockAdjustmentServiceImpl;

	
	@ApiOperation(value = "Stock adjustment draft", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/stockadjustmentdraft", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String stockadjustmentdraft(@RequestBody String store) {

		OutputResponse response = new OutputResponse();
		try {

			String saveData = "Invalid Store Type";

			StockAdjustmentDraft stockAdjustmentDraft = InputMapper.gson().fromJson(store, StockAdjustmentDraft.class);

			saveData = stockAdjustmentServiceImpl.saveDraft(stockAdjustmentDraft).toString();

			response.setResponse(saveData);

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	
	@ApiOperation(value = "Get stock adjustment draft transaction", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getstockadjustmentdraftTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getstockadjustmentdraftTransaction(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {

			List<StockAdjustmentDraft> saveData = stockAdjustmentServiceImpl
					.getStockAjustmentDraftTransaction(itemStockinput);
			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	
	@ApiOperation(value = "Edit stock adjustment draft transaction", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getforEditsStockAdjustmentdraftTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getforEditsSockAdjustmentdraftTransaction(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {

			StockAdjustmentDraft saveData = stockAdjustmentServiceImpl
					.getforeditStockAjustmentDraftTransaction(itemStockinput.getStockAdjustmentDraftID());

			response.setResponse(saveData.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	
	@ApiOperation(value = "Stock adjustment", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/stockadjustment", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String stockadjustment(@RequestBody StockAdjustment StockAdjustment) {

		OutputResponse response = new OutputResponse();
		try {

			String saveData = "Invalid Store Type";

			saveData = stockAdjustmentServiceImpl.savetransaction(StockAdjustment).toString();

			response.setResponse(saveData);

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	
	@ApiOperation(value = "Get stock adjustment transaction", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getStockAdjustmentTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getforeditStockAdjustmentTransaction(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {

			List<StockAdjustment> saveData = stockAdjustmentServiceImpl.getStockAjustmentTransaction(itemStockinput);

			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	
	@ApiOperation(value = "Edit stock adjustment transaction", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getforEditsStockAdjustmentTransaction", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getforEditsStockAdjustmentTransaction(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();
		try {

			StockAdjustment saveData = stockAdjustmentServiceImpl
					.getforeditStockAjustmentTransaction(itemStockinput.getStockAdjustmentID());

			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}
		return response.toString();
	}
}
