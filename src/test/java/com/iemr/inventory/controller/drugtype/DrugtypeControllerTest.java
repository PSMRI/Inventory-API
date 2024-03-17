package com.iemr.inventory.controller.drugtype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.drugtype.M_Drugtype;
import com.iemr.inventory.service.drugtype.DrugtypeInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class DrugtypeControllerTest {

	@Mock
	private DrugtypeInter drugtypeInter;

	@InjectMocks
	private DrugtypeController drugtypeController;

	@Test
	public void testCreateManufacturer() {

		OutputResponse expectedResponse = new OutputResponse();
		// Mocking the Input
		String createDrugtype = "[{\"drugTypeName\":\"Test Drug\",\"drugTypeDesc\":\"Test Description\",\"drugTypeCode\":\"TEST123\",\"status\":\"ACTIVE\",\"modifiedBy\":\"admin\"}]";

		// Mocking the Service Response
		ArrayList<M_Drugtype> mockSavedData = new ArrayList<>();
		M_Drugtype mockDrugtype = new M_Drugtype();
		mockDrugtype.setDrugTypeName("Test Drug");
		mockDrugtype.setDrugTypeDesc("Test Description");
		mockDrugtype.setDrugTypeCode("TEST123");
		mockDrugtype.setStatus("ACTIVE");
		mockDrugtype.setModifiedBy("admin");
		mockSavedData.add(mockDrugtype);

		// Mocking the service method
		when(drugtypeInter.createDrugtypeData(any())).thenReturn(mockSavedData);

		// Mocking the OutputResponse
		expectedResponse.setResponse(mockSavedData.toString());
		
		// Assertions
		assertEquals(expectedResponse.toString(), drugtypeController.createManufacturer(createDrugtype));
		

	}
	
	@Test
	public void testCreateManufacturerException() {
		
		String createDrugtype="[{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}]";
		
		when(drugtypeInter.createDrugtypeData(any())).thenThrow(NotFoundException.class);
		
		String response = drugtypeController.createManufacturer(createDrugtype);
		
		Assertions.assertEquals(response, drugtypeController.createManufacturer(createDrugtype));

	}


	@Test
	public void testGetManufacturer() {

		// Mocking the OutputResponse
		OutputResponse expectedResponse = new OutputResponse();
		// Mocking the Input
		String getDrugtype = "{\"providerServiceMapID\": 1}";

		// Mocking the Service Response
		ArrayList<M_Drugtype> mockGetData = new ArrayList<>();
		M_Drugtype mockDrugtype = new M_Drugtype();
		mockDrugtype.setProviderServiceMapID(1); // Matching the input parameter
		mockDrugtype.setDrugTypeName("Test Drug");
		mockDrugtype.setDrugTypeDesc("Test Description");
		mockDrugtype.setDrugTypeCode("TEST123");
		mockDrugtype.setStatus("ACTIVE");
		mockDrugtype.setModifiedBy("admin");
		mockGetData.add(mockDrugtype);

		// Mocking the service method
		when(drugtypeInter.getDrugtypeData(any())).thenReturn(mockGetData);

		expectedResponse.setResponse(mockGetData.toString());

		// Assertions
		assertEquals(expectedResponse.toString(), drugtypeController.getManufacturer(getDrugtype));

	}
	
	
	@Test
	public void testGetManufacturerException() {
		
		String getDrugtype="{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		when(drugtypeInter.getDrugtypeData(any())).thenThrow(NotFoundException.class);
		
		String response = drugtypeController.getManufacturer(getDrugtype);
		
		Assertions.assertEquals(response, drugtypeController.getManufacturer(getDrugtype));

	}

	@Test
	public void testEditManufacturer() {

		// Mocking the OutputResponse
		OutputResponse expectedResponse = new OutputResponse();
		// Mocking the Input
		String editDrugtype = "{\"drugTypeID\": 1, \"drugTypeName\":\"Updated Drug\", \"drugTypeDesc\":\"Updated Description\", \"drugTypeCode\":\"UPDATED123\", \"status\":\"ACTIVE\", \"modifiedBy\":\"admin\"}";

		// Mocking the Service Response
		M_Drugtype mockEditedData = new M_Drugtype();
		mockEditedData.setDrugTypeID(1); // Matching the input parameter
		mockEditedData.setDrugTypeName("Updated Drug");
		mockEditedData.setDrugTypeDesc("Updated Description");
		mockEditedData.setDrugTypeCode("UPDATED123");
		mockEditedData.setStatus("ACTIVE");
		mockEditedData.setModifiedBy("admin");

		// Mocking the service method
		when(drugtypeInter.editDrugtypeData(any())).thenReturn(mockEditedData);
		when(drugtypeInter.saveeditDrugtype(any())).thenReturn(mockEditedData);
		expectedResponse.setResponse(mockEditedData.toString());

		// Assertions
		assertEquals(expectedResponse.toString(), drugtypeController.editManufacturer(editDrugtype));
	}
	
	@Test
	public void testEditManufacturerException() {
		
		String editDrugtype="{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		when(drugtypeInter.editDrugtypeData(any())).thenThrow(NotFoundException.class);
		
		String response = drugtypeController.editManufacturer(editDrugtype);
		
		Assertions.assertEquals(response, drugtypeController.editManufacturer(editDrugtype));

	}

	@Test
	public void testDeleteManufacturer() {

		// Mocking the OutputResponse
		OutputResponse expectedResponse = new OutputResponse();

		// Mocking the Input
		String deleteDrugtype = "{\"drugTypeID\": 1, \"deleted\": true}";

		// Mocking the Service Response
		M_Drugtype mockEditedData = new M_Drugtype();
		mockEditedData.setDrugTypeID(1); // Matching the input parameter
		mockEditedData.setDeleted(true);

		// Mocking the service method
		when(drugtypeInter.editDrugtypeData(any())).thenReturn(mockEditedData);
		when(drugtypeInter.saveeditDrugtype(any())).thenReturn(mockEditedData);

		expectedResponse.setResponse(mockEditedData.toString());
		// Assertions
		assertEquals(expectedResponse.toString(), drugtypeController.deleteManufacturer(deleteDrugtype));
	}
	
	
	@Test
	public void testDeleteManufacturerException() {
		
		String deleteDrugtype="{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		when(drugtypeInter.editDrugtypeData(any())).thenThrow(NotFoundException.class);
		
		String response = drugtypeController.deleteManufacturer(deleteDrugtype);
		
		Assertions.assertEquals(response, drugtypeController.deleteManufacturer(deleteDrugtype));

	}
}
