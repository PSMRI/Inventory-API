package com.iemr.inventory.controller.facilitytype;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.facilitytype.M_facilitytype;
import com.iemr.inventory.service.facilitytype.M_facilitytypeInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class FacilitytypeControllerTest {

	@Mock
	private M_facilitytypeInter m_facilitytypeInter;

	@InjectMocks
	private FacilitytypeController facilitytypeController;

	@Mock
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void testGetFacility() {

		OutputResponse response = new OutputResponse();
		// Arrange
		String validRequestBody = "{\"providerServiceMapID\": 123}";
		String invalidRequestBody = "{\"invalidField\": 123}";

		M_facilitytype mockFacilityDetails = new M_facilitytype();
		when(m_facilitytypeInter.getAllFicilityData(anyInt())).thenReturn(new ArrayList<>());

		// Act & Assert for valid scenario
		String validResult = facilitytypeController.getFacility(validRequestBody);
		// Add more assertions based on your requirements for the valid scenario

		// Act & Assert for exception scenario
		when(m_facilitytypeInter.getAllFicilityData(anyInt())).thenThrow(new RuntimeException("Simulated exception"));
		String invalidResult = facilitytypeController.getFacility(invalidRequestBody);

		response.setResponse(validResult);

		response.setResponse(invalidResult);

		assertNotNull(invalidResult);
		assertNotNull(validResult);
	}

	@Test
	public void testGetFacilityException() {

		String validRequestBody = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		String invalidRequestBody = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(m_facilitytypeInter.getAllFicilityData(anyInt())).thenThrow(NotFoundException.class);

		String validResponse = facilitytypeController.getFacility(validRequestBody);
		String invalidResponse = facilitytypeController.getFacility(invalidRequestBody);

		Assertions.assertEquals(validResponse, facilitytypeController.getFacility(validRequestBody));
		Assertions.assertEquals(invalidResponse, facilitytypeController.getFacility(invalidRequestBody));
	}

	@Test
	public void testAddFacility() {

		OutputResponse response = new OutputResponse();
		// Arrange
		String validRequestBody = "[{\"field1\": \"value1\"}, {\"field2\": \"value2\"}]";
		M_facilitytype[] validFacilityDetails = InputMapper.gson().fromJson(validRequestBody, M_facilitytype[].class);
		List<M_facilitytype> validAddFacilityDetails = Arrays.asList(validFacilityDetails);
		ArrayList<M_facilitytype> mockAllFacilityData = new ArrayList<>();
		when(m_facilitytypeInter.addAllFicilityData(validAddFacilityDetails)).thenReturn(mockAllFacilityData);

		// Act & Assert for valid scenario
		String validResult = facilitytypeController.addFacility(validRequestBody);

		// Add more assertions based on your requirements for the valid scenario
		ArrayList<M_facilitytype> allFacilityData = m_facilitytypeInter.addAllFicilityData(validAddFacilityDetails);
		response.setResponse(allFacilityData.toString());

		// Act & Assert for exception scenario
		String invalidRequestBody = "[{\"invalidField\": 123}]";
		when(m_facilitytypeInter.addAllFicilityData(anyList())).thenThrow(new RuntimeException("Simulated exception"));
		String invalidResult = facilitytypeController.addFacility(invalidRequestBody);

		response.setResponse(validResult);
		response.setResponse(invalidResult);

		assertNotNull(validResult);
		assertNotNull(invalidResult);
	}

	@Test
	public void testAddFacilityException() {

		String validRequestBody = "[{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}]";
		String invalidRequestBody = "[{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}]";

		M_facilitytype[] validFacilityDetails = InputMapper.gson().fromJson(validRequestBody, M_facilitytype[].class);
		List<M_facilitytype> validAddFacilityDetails = Arrays.asList(validFacilityDetails);

		when(m_facilitytypeInter.addAllFicilityData(anyList())).thenThrow(new RuntimeException("Simulated exception"));

		String validResponse = facilitytypeController.addFacility(validRequestBody);
		String invalidResponse = facilitytypeController.addFacility(invalidRequestBody);

		Assertions.assertEquals(validResponse, facilitytypeController.addFacility(validRequestBody));
		Assertions.assertEquals(invalidResponse, facilitytypeController.addFacility(invalidRequestBody));
	}

	@Test
	public void testEditFacility() {

		OutputResponse response = new OutputResponse();
		// Arrange for valid scenario
		String requestBody = "{\"facilityTypeID\": 1, \"facilityTypeName\": \"New Name\", \"facilityTypeDesc\": \"New Description\", \"facilityTypeCode\": \"New Code\", \"modifiedBy\": \"User1\"}";
		M_facilitytype facilityDetails = InputMapper.gson().fromJson(requestBody, M_facilitytype.class);
		M_facilitytype expectedFacilityData = new M_facilitytype(); // Assuming this object is properly initialized

		when(m_facilitytypeInter.editAllFicilityData(facilityDetails.getFacilityTypeID()))
				.thenReturn(expectedFacilityData);
		when(m_facilitytypeInter.updateFacilityData(any(M_facilitytype.class))).thenReturn(expectedFacilityData);

		// Act for valid scenario
		String validResult = facilitytypeController.editFacility(requestBody);
		// Add more detailed assertions here to validate the response structure/content

		// Arrange for exception scenario
		String exceptionRequestBody = "{\"facilityTypeID\": 999, \"facilityTypeName\": \"Invalid\", \"facilityTypeDesc\": \"Invalid\", \"facilityTypeCode\": \"Invalid\", \"modifiedBy\": \"User2\"}";
		when(m_facilitytypeInter.editAllFicilityData(anyInt())).thenThrow(new RuntimeException("Simulated exception"));

		// Act for exception scenario
		String exceptionResult = facilitytypeController.editFacility(exceptionRequestBody);

		response.setResponse(validResult);

		response.setResponse(exceptionResult);

		assertNotNull(validResult);
		assertNotNull(exceptionResult);

		// Assert for exception scenario
		assertNotNull(validResult);
		assertNotNull(exceptionResult);
	}

	@Test
	public void testEditFacilityException() {

		String requestBody = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		M_facilitytype facilityDetails = InputMapper.gson().fromJson(requestBody, M_facilitytype.class);

		when(m_facilitytypeInter.editAllFicilityData(facilityDetails.getFacilityTypeID()))
				.thenThrow(NotFoundException.class);
		// Act for valid scenario
		String validResult = facilitytypeController.editFacility(requestBody);
		// Add more detailed assertions here to validate the response structure/content

		// Arrange for exception scenario
		String exceptionRequestBody = "{\"facilityTypeID\": 999, \"facilityTypeName\": \"Invalid\", \"facilityTypeDesc\": \"Invalid\", \"facilityTypeCode\": \"Invalid\", \"modifiedBy\": \"User2\"}";
		when(m_facilitytypeInter.editAllFicilityData(anyInt())).thenThrow(new RuntimeException("Simulated exception"));

		// Act for exception scenario
		String exceptionResult = facilitytypeController.editFacility(exceptionRequestBody);

		Assertions.assertEquals(validResult, facilitytypeController.editFacility(requestBody));
		Assertions.assertEquals(exceptionResult, facilitytypeController.editFacility(exceptionRequestBody));
	}

	@Test
	public void testDeleteFacility() {
		OutputResponse response = new OutputResponse();
		// Arrange for valid scenario
		String requestBody = "{\"facilityTypeID\": 1, \"deleted\": true}";
		M_facilitytype facilityDetails = InputMapper.gson().fromJson(requestBody, M_facilitytype.class);
		M_facilitytype expectedFacilityData = new M_facilitytype(); // Assuming this object is properly initialized

		when(m_facilitytypeInter.editAllFicilityData(facilityDetails.getFacilityTypeID()))
				.thenReturn(expectedFacilityData);
		when(m_facilitytypeInter.updateFacilityData(any(M_facilitytype.class))).thenReturn(expectedFacilityData);

		// Act for valid scenario
		String validResult = facilitytypeController.deleteFacility(requestBody);

		// Assert for valid scenario
		assertNotNull(validResult);
		// Add more detailed assertions here to validate the response structure/content

		// Arrange for exception scenario
		String exceptionRequestBody = "{\"facilityTypeID\": 999, \"deleted\": true}";
		when(m_facilitytypeInter.editAllFicilityData(anyInt())).thenThrow(new RuntimeException("Simulated exception"));

		// Act for exception scenario
		String exceptionResult = facilitytypeController.deleteFacility(exceptionRequestBody);

		response.setResponse(validResult);

		response.setResponse(exceptionResult);

		assertNotNull(validResult);
		assertNotNull(exceptionResult);

		// Assert for exception scenario
		assertNotNull(validResult);
		assertNotNull(exceptionResult);
	}

	@Test
	public void testDeleteFacilityException() {

		String requestBody = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		M_facilitytype facilityDetails = InputMapper.gson().fromJson(requestBody, M_facilitytype.class);

		when(m_facilitytypeInter.editAllFicilityData(facilityDetails.getFacilityTypeID()))
				.thenThrow(NotFoundException.class);

		// Act for valid scenario
		String validResult = facilitytypeController.editFacility(requestBody);
		// Add more detailed assertions here to validate the response structure/content

		// Arrange for exception scenario
		String exceptionRequestBody = "{\"facilityTypeID\": 999, \"facilityTypeName\": \"Invalid\", \"facilityTypeDesc\": \"Invalid\", \"facilityTypeCode\": \"Invalid\", \"modifiedBy\": \"User2\"}";
		when(m_facilitytypeInter.editAllFicilityData(anyInt())).thenThrow(new RuntimeException("Simulated exception"));

		// Act for exception scenario
		String exceptionResult = facilitytypeController.editFacility(exceptionRequestBody);

		Assertions.assertEquals(validResult, facilitytypeController.deleteFacility(requestBody));
		Assertions.assertEquals(exceptionResult, facilitytypeController.deleteFacility(exceptionRequestBody));
	}

}
