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
package com.iemr.inventory.controller.stockEntry;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.service.stockEntry.StockEntryServiceImpl;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class StockEntryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private StockEntryServiceImpl stockEntryService;

	@ApiOperation(value = "Physical stock entry", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/physicalStockEntry", headers = "Authorization", method = { RequestMethod.POST })
	public String physicalStockEntry(@RequestBody PhysicalStockEntry physicalStockEntry) {

		OutputResponse output = new OutputResponse();

		try {
			physicalStockEntry = stockEntryService.savePhysicalStockEntry(physicalStockEntry);

			output.setResponse(physicalStockEntry.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			output.setError(e);
		}
		return output.toString();
	}

	@ApiOperation(value = "Get item batch for store id", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getItemBatchForStoreID", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getItemBatchForStoreID(@RequestBody ItemStockEntry itemStockEntry) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getItemBatchForStoreID(itemStockEntry);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Allocate stock from item id", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/allocateStockFromItemID/{facilityID}", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String allocateStockFromItemID(@PathVariable("facilityID") Integer facilityID,
			@RequestBody List<ItemStockExit> itemStockExitList) {

		OutputResponse response = new OutputResponse();

		try {

			List<AllocateItemMap> getData = stockEntryService.getItemStockFromItemID(facilityID, itemStockExitList);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get physical stock entry", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getPhysicalStockEntry", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getPhysicalStockEntry(@RequestBody ItemStockEntryinput itemStockinput) {

		OutputResponse response = new OutputResponse();

		try {

			List<PhysicalStockEntry> getData = stockEntryService.getPhysicalStockEntry(itemStockinput);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Item batch partial search", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/itemBatchPartialSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String itemPartialSearch(@RequestBody ItemMaster getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getItemMastersPartialSearch(getItem.getItemName(),
					getItem.getFacilityID());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Item batch with zero partial search", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/itemBatchWithZeroPartialSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String itemBatchWithZeroPartialSearch(@RequestBody ItemMaster getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getItemMastersPartialSearchWithZero(getItem.getItemName(),
					getItem.getFacilityID());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get physical stock entry items", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getPhysicalStockEntryItems", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getPhysicalStockEntryItems(@RequestBody ItemStockEntryinput getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = stockEntryService.getPhysicalStockEntryItems(getItem.getPhyEntryID());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get item with quantity partial search", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getItemwithQuantityPartialSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getItemwithQuantityPartialSearch(@RequestBody ItemMaster getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemMasterWithQuantityMap> getData = stockEntryService
					.getItemwithQuantityPartialSearch(getItem.getItemName(), getItem.getFacilityID());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

}
