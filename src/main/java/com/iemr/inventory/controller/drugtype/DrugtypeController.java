package com.iemr.inventory.controller.drugtype;

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

import com.iemr.inventory.data.drugtype.M_Drugtype;
import com.iemr.inventory.service.drugtype.DrugtypeInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

@RestController
public class DrugtypeController {
	@Autowired
	private DrugtypeInter drugtypeInter;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	
	@CrossOrigin()
	@RequestMapping(value =  "/createDrugtype" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String createManufacturer(@RequestBody String createDrugtype) {
		
		
		      OutputResponse response = new OutputResponse();
		try {
			

			M_Drugtype[] Drugtype = InputMapper.gson().fromJson(createDrugtype,
					M_Drugtype[].class);
		      List<M_Drugtype> DrugtypeData = Arrays.asList(Drugtype);
			
			ArrayList<M_Drugtype> saveData=drugtypeInter.createDrugtypeData(DrugtypeData);
			
			
			
			response.setResponse(saveData.toString());

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
	@RequestMapping(value =  "/getDrugtype",headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String getManufacturer(@RequestBody String getDrugtype) {
	
		OutputResponse response = new OutputResponse();

		try {

			M_Drugtype Drugtype = InputMapper.gson().fromJson(getDrugtype,
					M_Drugtype.class);
		    
			ArrayList<M_Drugtype> getedData=drugtypeInter.getDrugtypeData(Drugtype.getProviderServiceMapID());
			
			response.setResponse(getedData.toString());

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
	@RequestMapping(value =  "/editDrugtype" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String editManufacturer(@RequestBody String editDrugtype) {
		
		OutputResponse response = new OutputResponse();

		try {

			M_Drugtype Drugtype = InputMapper.gson().fromJson(editDrugtype,
					M_Drugtype.class);
		    
			M_Drugtype geteditedData=drugtypeInter.editDrugtypeData(Drugtype.getDrugTypeID());
			 
			geteditedData.setDrugTypeName(Drugtype.getDrugTypeName());
			geteditedData.setDrugTypeDesc(Drugtype.getDrugTypeDesc());
			geteditedData.setDrugTypeCode(Drugtype.getDrugTypeCode());
			geteditedData.setStatus(Drugtype.getStatus());
			geteditedData.setModifiedBy(Drugtype.getModifiedBy());
			
			M_Drugtype saveeditedData=drugtypeInter.saveeditDrugtype(geteditedData);
			
			response.setResponse(saveeditedData.toString());

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
	@RequestMapping(value =  "/deleteDrugtype" ,headers = "Authorization", method = { RequestMethod.POST }, produces = { "application/json" })
	public String deleteManufacturer(@RequestBody String deleteDrugtype) {
	
		OutputResponse response = new OutputResponse();

		try {

			M_Drugtype Drugtype = InputMapper.gson().fromJson(deleteDrugtype,
					M_Drugtype.class);
		   
			M_Drugtype geteditedData=drugtypeInter.editDrugtypeData(Drugtype.getDrugTypeID());
			 
			geteditedData.setDeleted(Drugtype.getDeleted());
			
			M_Drugtype deletedData=drugtypeInter.saveeditDrugtype(geteditedData);
			
			response.setResponse(deletedData.toString());

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
