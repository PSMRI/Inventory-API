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
package com.iemr.inventory.controller.manufacturer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.service.manufacturer.ManufacturerInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.annotations.ApiOperation;

@RestController
public class ManufacturerController {
	@Autowired
	private ManufacturerInter manufacturerInter;

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@CrossOrigin()
	@ApiOperation(value = "Create manufacturer", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/createManufacturer", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String createManufacturer(@RequestBody String createManufacturer) {

		OutputResponse response = new OutputResponse();

		try {

			M_Manufacturer[] Manufacturer = InputMapper.gson().fromJson(createManufacturer, M_Manufacturer[].class);
			List<M_Manufacturer> ManufacturerData = Arrays.asList(Manufacturer);

			ArrayList<M_Manufacturer> saveData = manufacturerInter.createManufacturer(ManufacturerData);

			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();
	}

	@CrossOrigin()
	@ApiOperation(value = "Get manufacturer", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/getManufacturer", headers = "Authorization", method = { RequestMethod.POST }, produces = {
			"application/json" })
	public String getManufacturer(@RequestBody String getManufacturer) {

		OutputResponse response = new OutputResponse();

		try {

			M_Manufacturer Manufacturer = InputMapper.gson().fromJson(getManufacturer, M_Manufacturer.class);

			ArrayList<M_Manufacturer> getData = manufacturerInter
					.createManufacturer(Manufacturer.getProviderServiceMapID());

			response.setResponse(getData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();

	}

	@CrossOrigin()
	@ApiOperation(value = "Edit manufacturer", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/editManufacturer", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String editManufacturer(@RequestBody String editManufacturer) {

		OutputResponse response = new OutputResponse();

		try {

			M_Manufacturer Manufacturer = InputMapper.gson().fromJson(editManufacturer, M_Manufacturer.class);

			M_Manufacturer editData = manufacturerInter.editManufacturer(Manufacturer.getManufacturerID());

			editData.setManufacturerName(Manufacturer.getManufacturerName());
			editData.setManufacturerDesc(Manufacturer.getManufacturerDesc());
			editData.setManufacturerCode(Manufacturer.getManufacturerCode());
			editData.setStatus(Manufacturer.getStatus());
			editData.setContactPerson(Manufacturer.getContactPerson());
			editData.setcST_GST_No(Manufacturer.getcST_GST_No());
			editData.setModifiedBy(Manufacturer.getModifiedBy());

			M_Manufacturer saveData = manufacturerInter.saveEditedData(editData);

			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);

		}
		return response.toString();

	}

	@CrossOrigin()
	@ApiOperation(value = "Delete manufacturer", consumes = "application/json", produces = "application/json")
	@RequestMapping(value = "/deleteManufacturer", headers = "Authorization", method = {
			RequestMethod.POST }, produces = { "application/json" })
	public String deleteManufacturer(@RequestBody String deleteManufacturer) {

		OutputResponse response = new OutputResponse();

		try {

			M_Manufacturer Manufacturer = InputMapper.gson().fromJson(deleteManufacturer, M_Manufacturer.class);

			M_Manufacturer editData = manufacturerInter.editManufacturer(Manufacturer.getManufacturerID());

			editData.setDeleted(Manufacturer.getDeleted());

			M_Manufacturer saveData = manufacturerInter.saveEditedData(editData);

			response.setResponse(saveData.toString());

		} catch (Exception e) {
			logger.error(e.getMessage());
			response.setError(e);
		}

		return response.toString();
	}

}
