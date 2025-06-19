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
package com.iemr.inventory.controller.itemfacilitymapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;
import com.iemr.inventory.data.itemfacilitymapping.V_fetchItemFacilityMap;
import com.iemr.inventory.data.items.ItemInStore;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockExit.StockTransferItem;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.service.itemfacilitymapping.M_itemfacilitymappingInter;
import com.iemr.inventory.to.itemfacilitymapping.ItemfacilityMappingTO;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class ItemfacilitymappingController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private M_itemfacilitymappingInter M_itemfacilitymappingInter;

	@ApiOperation(value = "Map item to store", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/mapItemtoStrore", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String mapItemtoStrore(@RequestBody String mapItemtoStrore) {
		OutputResponse response = new OutputResponse();

		try {

			M_itemfacilitymapping[] itemDetails1 = InputMapper.gson().fromJson(mapItemtoStrore,
					M_itemfacilitymapping[].class);

			List<M_itemfacilitymapping> itemDetails = Arrays.asList(itemDetails1);

			ItemfacilityMappingTO[] itemDetailsTo = InputMapper.gson().fromJson(mapItemtoStrore,
					ItemfacilityMappingTO[].class);

			List<ItemfacilityMappingTO> itemdeatails2 = Arrays.asList(itemDetailsTo);

			M_itemfacilitymapping resDataMap = null;
			List<M_itemfacilitymapping> resList = new ArrayList<M_itemfacilitymapping>();

			int itemDetailsIndex = 0;
			Integer[] itemID = null;
			for (ItemfacilityMappingTO blockingto : itemdeatails2) {
				itemID = blockingto.getItemID1();
				M_itemfacilitymapping mappingDetails = itemDetails.get(itemDetailsIndex);

				for (int i = 0; i < itemID.length; i++) {
					resDataMap = new M_itemfacilitymapping();

					resDataMap.setFacilityID(mappingDetails.getFacilityID());
					resDataMap.setItemID(itemID[i]);
					resDataMap.setMappingType(mappingDetails.getMappingType());
					resDataMap.setProviderServiceMapID(mappingDetails.getProviderServiceMapID());
					resDataMap.setStatus(mappingDetails.getStatus());
					resDataMap.setCreatedBy(mappingDetails.getCreatedBy());

					resList.add(resDataMap);
				}

			}

			ArrayList<M_itemfacilitymapping> data = M_itemfacilitymappingInter.mapItemtoStore(resList);

			response.setResponse(data.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Edit item to store", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/editItemtoStrore", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String editItemtoStrore(@RequestBody String editItemtoStrore) {
		OutputResponse response = new OutputResponse();

		try {

			M_itemfacilitymapping itemDetails = InputMapper.gson().fromJson(editItemtoStrore,
					M_itemfacilitymapping.class);

			M_itemfacilitymapping getdataforedit = M_itemfacilitymappingInter.editdata(itemDetails.getItemStoreMapID());

			getdataforedit.setFacilityID(itemDetails.getFacilityID());
			getdataforedit.setItemID(itemDetails.getItemID());
			getdataforedit.setMappingType(itemDetails.getMappingType());
			getdataforedit.setProviderServiceMapID(itemDetails.getProviderServiceMapID());
			getdataforedit.setStatus(itemDetails.getStatus());

			M_itemfacilitymapping data = M_itemfacilitymappingInter.saveEditedItem(getdataforedit);

			response.setResponse(data.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();

	}

	@ApiOperation(value = "Delete item to store", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/deleteItemtoStrore", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String deleteItemtoStrore(@RequestBody String deleteItemtoStrore) {
		OutputResponse response = new OutputResponse();

		try {

			M_itemfacilitymapping itemDetails = InputMapper.gson().fromJson(deleteItemtoStrore,
					M_itemfacilitymapping.class);

			M_itemfacilitymapping getdataforedit = M_itemfacilitymappingInter.editdata(itemDetails.getItemStoreMapID());

			getdataforedit.setDeleted(itemDetails.getDeleted());

			M_itemfacilitymapping data = M_itemfacilitymappingInter.saveEditedItem(getdataforedit);

			response.setResponse(data.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();

	}

	@ApiOperation(value = "Get sub store item", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getSubStoreitem", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String getSubStroreitem(@RequestBody String deleteItemtoStrore) {
		OutputResponse response = new OutputResponse();

		try {

			M_itemfacilitymapping itemDetails = InputMapper.gson().fromJson(deleteItemtoStrore,
					M_itemfacilitymapping.class);

			ArrayList<M_itemfacilitymapping> getsubstoreData = M_itemfacilitymappingInter
					.getsubitemforsubStote(itemDetails.getProviderServiceMapID(), itemDetails.getFacilityID());

			response.setResponse(getsubstoreData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();

	}

	@ApiOperation(value = "Get all facility mapped data", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getAllFacilityMappedData", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getAllFacilityMappedData(@RequestBody String getAllFacilityMappedData) {
		OutputResponse response = new OutputResponse();

		try {

			V_fetchItemFacilityMap itemDetails = InputMapper.gson().fromJson(getAllFacilityMappedData,
					V_fetchItemFacilityMap.class);

			ArrayList<V_fetchItemFacilityMap> getAllMappedData = M_itemfacilitymappingInter
					.getAllFacilityMappedData(itemDetails.getProviderServiceMapID());

			response.setResponse(getAllMappedData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();

	}

	@ApiOperation(value = "Get item from store id", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getItemFromStoreID/{storeID}", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getItemFromStoreID(@PathVariable("storeID") Integer storeID) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemInStore> getData = M_itemfacilitymappingInter.getItemMastersFromStoreID(storeID);

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Item partial search", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/itemPartialSearch", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String itemPartialSearch(@RequestBody ItemMaster getItem) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemMaster> getData = M_itemfacilitymappingInter.getItemMastersPartialSearch(getItem.getItemName(),
					getItem.getFacilityID());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@ApiOperation(value = "Get item batch for store transfer", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getItemBatchForStoreTransfer", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String getItemBatchForStoreTransfer(@RequestBody StockTransferItem stores) {

		OutputResponse response = new OutputResponse();

		try {

			List<ItemStockEntry> getData = M_itemfacilitymappingInter.getItemBatchForStoreTransfer(
					stores.getTransferFromFacilityID(), stores.getTransferToFacilityID(), stores.getItemName());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}
}
