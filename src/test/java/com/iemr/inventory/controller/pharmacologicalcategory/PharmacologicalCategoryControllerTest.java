package com.iemr.inventory.controller.pharmacologicalcategory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.service.pharmacologicalcategory.PharmacologicalcategoryInter;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class PharmacologicalCategoryControllerTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@InjectMocks
	PharmacologicalCategoryController pharmacologicalCategoryController;

	@Mock
	PharmacologicalcategoryInter pharmacologicalcategoryInter;

	@Test
	public void createPharmacologicalcategoryTest() {

		OutputResponse response = new OutputResponse();

		String inputJson = "[{\"pharmacologyCategoryID\": 123, \"providerServiceMapID\": 12}]";

		M_Pharmacologicalcategory[] Pharmacologicalcategory = new Gson().fromJson(inputJson,
				M_Pharmacologicalcategory[].class);

		M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

		m_Pharmacologicalcategory.setPharmCategoryID(123);
		m_Pharmacologicalcategory.setPharmCategoryName("Cancer Type");
		m_Pharmacologicalcategory
				.setPharmCategoryDesc("For cancer patients all the required medicines can be found here");
		m_Pharmacologicalcategory.setPharmCategoryCode("CAN123");
		m_Pharmacologicalcategory.setStatus("Approved and Ready");
		m_Pharmacologicalcategory.setProviderServiceMapID(12);
		m_Pharmacologicalcategory.setDeleted(false);
		m_Pharmacologicalcategory.setCreatedBy("TATA CANC MUMBAI");
		m_Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI");
		m_Pharmacologicalcategory.setLastModDate(Date.valueOf("2010-01-10"));

		ArrayList<M_Pharmacologicalcategory> createPharmacologicaldata = new ArrayList<M_Pharmacologicalcategory>();
		createPharmacologicaldata.add(m_Pharmacologicalcategory);

		ArrayList<M_Pharmacologicalcategory> saveData = createPharmacologicaldata;

		String request = saveData.toString();

		when(pharmacologicalcategoryInter.createPharmacologicalcategory(createPharmacologicaldata))
				.thenReturn(saveData);

		String response1 = pharmacologicalCategoryController.createPharmacologicalcategory(request);

		response.setResponse(saveData.toString());

		Assertions.assertEquals(response1, pharmacologicalCategoryController.createPharmacologicalcategory(request));
	}

	@Test
	public void createPharmacologicalcategoryTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = pharmacologicalCategoryController.createPharmacologicalcategory(request);

		Assertions.assertEquals(response, pharmacologicalCategoryController.createPharmacologicalcategory(request));

	}

	@Test
	public void getPharmacologicalcategoryTest() {

		M_Pharmacologicalcategory Pharmacologicalcategory = new M_Pharmacologicalcategory();

		Pharmacologicalcategory.setPharmCategoryID(123);
		Pharmacologicalcategory.setPharmCategoryName("Cancer Type");
		Pharmacologicalcategory
				.setPharmCategoryDesc("For cancer patients all the required medicines can be found here");
		Pharmacologicalcategory.setPharmCategoryCode("CAN123");
		Pharmacologicalcategory.setStatus("Approved and Ready");
		Pharmacologicalcategory.setProviderServiceMapID(12);
		Pharmacologicalcategory.setDeleted(false);
		Pharmacologicalcategory.setCreatedBy("TATA CANC MUMBAI");
		Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI");
		Pharmacologicalcategory.setLastModDate(Date.valueOf("2010-01-10"));

		ArrayList<M_Pharmacologicalcategory> saveData = new ArrayList<M_Pharmacologicalcategory>();

		saveData.add(Pharmacologicalcategory);
		
		String createPharmacologicalcategory = saveData.toString();

		String response = pharmacologicalCategoryController.getPharmacologicalcategory(createPharmacologicalcategory);

		OutputResponse response1 = new OutputResponse();

		response1.setResponse(saveData.toString());

		Assertions.assertEquals(response,
				pharmacologicalCategoryController.getPharmacologicalcategory(createPharmacologicalcategory));
	}

	@Test
	public void getPharmacologicalcategoryTestException() {

		String createPharmacologicalcategory = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(pharmacologicalcategoryInter.getPharmacologicalcategory(any())).thenThrow(NotFoundException.class);

		String response = pharmacologicalCategoryController.getPharmacologicalcategory(createPharmacologicalcategory);

		Assertions.assertEquals(response,
				pharmacologicalCategoryController.getPharmacologicalcategory(createPharmacologicalcategory));

	}

	@Test
	public void editPharmacologicalcategoryTest() {

		M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

		m_Pharmacologicalcategory.setPharmCategoryID(123);
		m_Pharmacologicalcategory.setPharmCategoryName("Cancer Type");
		m_Pharmacologicalcategory
				.setPharmCategoryDesc("For cancer patients2 all the required medicines can be found here");
		m_Pharmacologicalcategory.setPharmCategoryCode("CAN123");
		m_Pharmacologicalcategory.setStatus("Approved and Ready");
		m_Pharmacologicalcategory.setProviderServiceMapID(12);
		m_Pharmacologicalcategory.setDeleted(false);
		m_Pharmacologicalcategory.setCreatedBy("TATA CANC MUMBAI");
		m_Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI2");
		m_Pharmacologicalcategory.setLastModDate(Date.valueOf("2010-01-10"));

		M_Pharmacologicalcategory saveData = m_Pharmacologicalcategory;

		saveData.setPharmCategoryDesc(m_Pharmacologicalcategory.getPharmCategoryDesc());
		saveData.setModifiedBy(m_Pharmacologicalcategory.getModifiedBy());

		M_Pharmacologicalcategory saveEditedData = saveData;

		String request = saveEditedData.toString();

		String response = pharmacologicalCategoryController.editPharmacologicalcategory(request);

		OutputResponse response1 = new OutputResponse();
		response1.setResponse(saveData.toString());

		Assertions.assertEquals(response, pharmacologicalCategoryController.editPharmacologicalcategory(request));
	}

	@Test
	public void editPharmacologicalcategoryTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = pharmacologicalCategoryController.editPharmacologicalcategory(request);
		Assertions.assertEquals(response, pharmacologicalCategoryController.editPharmacologicalcategory(request));

	}

	@Test
	public void deletePharmacologicalcategoryTest() {

		M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

		m_Pharmacologicalcategory.setPharmCategoryID(123);
		m_Pharmacologicalcategory.setPharmCategoryName("Cancer Type");
		m_Pharmacologicalcategory
				.setPharmCategoryDesc("For cancer patients2 all the required medicines can be found here");
		m_Pharmacologicalcategory.setPharmCategoryCode("CAN123");
		m_Pharmacologicalcategory.setStatus("Approved and Ready");
		m_Pharmacologicalcategory.setProviderServiceMapID(12);
		m_Pharmacologicalcategory.setDeleted(false);
		m_Pharmacologicalcategory.setCreatedBy("TATA CANC MUMBAI");
		m_Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI2");
		m_Pharmacologicalcategory.setLastModDate(Date.valueOf("2010-01-10"));

		M_Pharmacologicalcategory saveData = m_Pharmacologicalcategory;

		saveData.setDeleted(m_Pharmacologicalcategory.getDeleted());

		M_Pharmacologicalcategory saveEditedData = saveData;

		String request = saveEditedData.toString();

		String response = pharmacologicalCategoryController.deletePharmacologicalcategory(request);

		OutputResponse response1 = new OutputResponse();
		response1.setResponse(saveData.toString());

		Assertions.assertEquals(response, pharmacologicalCategoryController.deletePharmacologicalcategory(request));
	}

	@Test
	public void deletePharmacologicalcategoryTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = pharmacologicalCategoryController.deletePharmacologicalcategory(request);
		Assertions.assertEquals(response, pharmacologicalCategoryController.deletePharmacologicalcategory(request));
	}

}
