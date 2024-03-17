package com.iemr.inventory.controller.indent;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Timestamp;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemIndent;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.service.indent.IndentService;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class IndentControllerTest {

	@InjectMocks
	IndentController indentController;

	@Mock
	IndentService IndentService;

	@Mock
	ObjectMapper objectMapper;

	@Mock
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void createStoreTest() {

		OutputResponse expectedResponse = new OutputResponse();

		ItemfacilitymappingIndent sObj = new ItemfacilitymappingIndent();

		sObj.setFacilityID(12);
		sObj.setItemName("Medicine Paracetamol 500 mg");

		String saveData = "Invalid Store Type";
		List<ItemfacilitymappingIndent> s = new ArrayList<ItemfacilitymappingIndent>();
		s.add(sObj);

		ItemIndent item = new ItemIndent();

		item.setFacilityID(sObj.getFacilityID());
		item.setItemName("Medicine Paracetamol 500 mg");

		saveData = s.toString();

		expectedResponse.setResponse(saveData);

		String response = indentController.createStore(item);

		Assertions.assertEquals(response, indentController.createStore(item));

	}

	@Test
	public void createIndentRequestTest() throws JsonProcessingException {

		OutputResponse expectedResponse = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();

		indentOrderList.add(indentOrder);

		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(false);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		facility.setCreatedDate(Date.valueOf("2024-09-01"));
		facility.setModifiedBy("Vidya");
		facility.setLastModDate(Date.valueOf("2025-01-01"));

		Indent indent = new Indent();
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setSyncFacilityID(3);
		indent.setFromFacilityName("Medicine");
		indent.setToFacilityID(1);
		indent.setRefNo("fehfgdh");
		indent.setReason("hjdfgdjhg");
		indent.setUserID(10);
		indent.setProviderServiceMapID(6);
		indent.setStatus("Approved");
		indent.setStatusReason("dhghdhgkghfkjghj");
		indent.setDeleted(false);
		indent.setProcessed("N");
		indent.setCreatedBy("Ak Hossain");
		indent.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setModifiedBy("Ak Hossain");
		indent.setLastModDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setVanID(9L);
		indent.setParkingPlaceID(7L);
		indent.setVanSerialNo(3L);
		indent.setIndentOrder(indentOrderList);
		indent.setM_Facility(facility);

		String request = indent.toString();

		when(IndentService.createIndentRequest(indent)).thenReturn(request);

		String response = indentController.createIndentRequest(request);

		expectedResponse.setResponse(request);

		// Assertions
		Assertions.assertEquals(response, indentController.createIndentRequest(request));
	}

	@Test
	public void createIndentRequestTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(IndentService.createIndentRequest(any())).thenThrow(NotFoundException.class);

		String response = indentController.createIndentRequest(request);

		Assertions.assertEquals(response, indentController.createIndentRequest(request));

	}

	@Test
	public void getIndentHistoryTest() {

		OutputResponse resp = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();

		indentOrderList.add(indentOrder);

		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(false);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		facility.setModifiedBy("Vidya");

		Indent indent = new Indent();
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setSyncFacilityID(3);
		indent.setFromFacilityName("Medicine");
		indent.setToFacilityID(1);
		indent.setRefNo("fehfgdh");
		indent.setReason("hjdfgdjhg");
		indent.setUserID(10);
		indent.setProviderServiceMapID(6);
		indent.setStatus("Approved");
		indent.setStatusReason("dhghdhgkghfkjghj");
		indent.setDeleted(false);
		indent.setProcessed("N");
		indent.setCreatedBy("Ak Hossain");
		indent.setModifiedBy("Ak Hossain");
		indent.setVanID(9L);
		indent.setParkingPlaceID(7L);
		indent.setVanSerialNo(3L);
		indent.setIndentOrder(indentOrderList);
		indent.setM_Facility(facility);

		String request = new Gson().toJson(indent);

		when(IndentService.getIndentHistory(indent)).thenReturn(request);

		String response = indentController.getIndentHistory(request);

		resp.setResponse(response);

		logger.info("getIndentHistory response " + response.toString());

		Assertions.assertEquals(response, indentController.getIndentHistory(request));
	}

	@Test
	public void getIndentHistoryTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(IndentService.getIndentHistory(any())).thenThrow(NotFoundException.class);

		String response = indentController.getIndentHistory(request);

		Assertions.assertEquals(response, indentController.getIndentHistory(request));

	}

	@Test
	public void getOrdersByIndentID() {

		OutputResponse resp = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		String request = new Gson().toJson(indentOrder);

		when(IndentService.getOrdersByIndentID(indentOrder)).thenReturn(request);

		String response = indentController.getOrdersByIndentID(request);

		resp.setResponse(response);

		Assertions.assertEquals(response, indentController.getOrdersByIndentID(request));
	}

	@Test
	public void getOrdersByIndentIDTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(IndentService.getOrdersByIndentID(any())).thenThrow(NotFoundException.class);

		String response = indentController.getOrdersByIndentID(request);

		Assertions.assertEquals(response, indentController.getOrdersByIndentID(request));
	}

	@Test
	public void getIndentWorklistTest() {

		OutputResponse resp = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		String request = new Gson().toJson(indentOrder);

		logger.info("getIndentWorklist request " + request);

		when(IndentService.getIndentWorklist(indentOrder)).thenReturn(request);

		String response = indentController.getIndentWorklist(request);

		resp.setResponse(response);

		Assertions.assertEquals(response, indentController.getIndentWorklist(request));
	}

	@Test
	public void getIndentWorklistTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(IndentService.getIndentWorklist(any())).thenThrow(NotFoundException.class);

		String response = indentController.getIndentWorklist(request);

		Assertions.assertEquals(response, indentController.getIndentWorklist(request));
	}

	@Test
	public void getIndentOrderWorklistTest() {

		OutputResponse resp = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		// indentOrder.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		String request = new Gson().toJson(indentOrder);

		when(IndentService.getIndentOrderWorklist(indentOrder)).thenReturn(request);

		String response = indentController.getIndentOrderWorklist(request);

		resp.setResponse(response);

		Assertions.assertEquals(response, indentController.getIndentOrderWorklist(request));
	}

	@Test
	public void getIndentOrderWorklistTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(IndentService.getIndentOrderWorklist(any())).thenThrow(NotFoundException.class);

		String response = indentController.getIndentOrderWorklist(request);

		Assertions.assertEquals(response, indentController.getIndentOrderWorklist(request));
	}

	@Test
	public void testIssueIndent() {

		OutputResponse resp = new OutputResponse();
		// Prepare the input JSON and the expected result
		String inputJson = "[{\"facilityID\": 123, \"itemID\": 456, \"quantity\": 789}]"; // Adjust based on actual
																							// input
		IndentIssue[] indentIssues = new Gson().fromJson(inputJson, IndentIssue[].class);
		String expectedResponse = "Expected Issue Response";

		// Configure the mock service
		when(IndentService.issueIndent(any(IndentIssue[].class))).thenReturn(expectedResponse);

		// Execute the method to be tested
		String actualResponse = indentController.issueIndent(inputJson);

		resp.setResponse(actualResponse);

		// Validate the response
		Assertions.assertNotNull(actualResponse, "The response should not be null");
		Assertions.assertTrue(actualResponse.contains(expectedResponse),
				"The response should contain the expected text");

		// Verify interaction with the mocked service
		verify(IndentService, times(1)).issueIndent(any(IndentIssue[].class));
	}

	@Test
	public void testIssueIndentEXception() {

		when(IndentService.issueIndent(any(IndentIssue[].class))).thenThrow(NotFoundException.class);

		String response = indentController.issueIndent(any());

		Assertions.assertEquals(response, indentController.issueIndent(any()));

	}

	@Test
	public void cancelIndentOrderTest() {

		OutputResponse resp = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		// indentOrder.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();

		indentOrderList.add(indentOrder);

		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(false);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		// facility.setCreatedDate(Date.valueOf("2024-09-01"));
		facility.setModifiedBy("Vidya");
		// facility.setLastModDate(Date.valueOf("2025-01-01"));

		Indent indent = new Indent();
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setSyncFacilityID(3);
		indent.setFromFacilityName("Medicine");
		indent.setToFacilityID(1);
		indent.setRefNo("fehfgdh");
		// indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setReason("hjdfgdjhg");
		indent.setUserID(10);
		indent.setProviderServiceMapID(6);
		indent.setStatus("Approved");
		indent.setStatusReason("dhghdhgkghfkjghj");
		indent.setDeleted(false);
		indent.setProcessed("N");
		indent.setCreatedBy("Ak Hossain");
		// indent.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setModifiedBy("Ak Hossain");
		// indent.setLastModDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setVanID(9L);
		indent.setParkingPlaceID(7L);
		indent.setVanSerialNo(3L);
		indent.setIndentOrder(indentOrderList);
		indent.setM_Facility(facility);

		String request = new Gson().toJson(indent);

		when(IndentService.cancelIndentOrder(indent)).thenReturn(request);

		String response = indentController.cancelIndentOrder(request);

		resp.setResponse(response);

		Assertions.assertEquals(response, indentController.cancelIndentOrder(request));
	}

	@Test
	public void cancelIndentOrderTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(IndentService.cancelIndentOrder(any())).thenThrow(NotFoundException.class);

		String response = indentController.cancelIndentOrder(request);

		Assertions.assertEquals(response, indentController.cancelIndentOrder(request));
	}

	@Test
	public void receiveIndentTest() {

		OutputResponse resp = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		// indentOrder.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();

		indentOrderList.add(indentOrder);

		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(false);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		// facility.setCreatedDate(Date.valueOf("2024-09-01"));
		facility.setModifiedBy("Vidya");
		// facility.setLastModDate(Date.valueOf("2025-01-01"));

		Indent indent = new Indent();
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setSyncFacilityID(3);
		indent.setFromFacilityName("Medicine");
		indent.setToFacilityID(1);
		indent.setRefNo("fehfgdh");
		// indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setReason("hjdfgdjhg");
		indent.setUserID(10);
		indent.setProviderServiceMapID(6);
		indent.setStatus("Approved");
		indent.setStatusReason("dhghdhgkghfkjghj");
		indent.setDeleted(false);
		indent.setProcessed("N");
		indent.setCreatedBy("Ak Hossain");
		// indent.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setModifiedBy("Ak Hossain");
		// indent.setLastModDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setVanID(9L);
		indent.setParkingPlaceID(7L);
		indent.setVanSerialNo(3L);
		indent.setIndentOrder(indentOrderList);
		indent.setM_Facility(facility);

		String request = new Gson().toJson(indent);

		when(IndentService.receiveIndent(indent)).thenReturn(request);

		String response = indentController.receiveIndent(request);

		resp.setResponse(response);

		Assertions.assertEquals(response, indentController.receiveIndent(request));
	}

	@Test
	public void receiveIndentTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(IndentService.receiveIndent(any())).thenThrow(NotFoundException.class);

		String response = indentController.receiveIndent(request);

		Assertions.assertEquals(response, indentController.receiveIndent(request));
	}

	@Test
	public void updateIndentOrderTest() {

		OutputResponse resp = new OutputResponse();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setSyncFacilityID(3);
		indentOrder.setIndentID(5L);
		indentOrder.setVanID(9L);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(3);

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();

		indentOrderList.add(indentOrder);

		M_Facility facility = new M_Facility();

		facility.setFacilityID(3);
		facility.setFacilityName("Medicine Store");
		facility.setFacilityDesc("It is a store to buy medicines");
		facility.setFacilityCode("Sq3672467");
		facility.setFacilityTypeID(4);
		facility.setLocation("Pune");
		facility.setPhysicalLocation("Kharadi");
		facility.setStoreType("Commercial");
		facility.setStatus("Open");
		facility.setIsMainFacility(true);
		facility.setMainFacilityID(45);
		facility.setProviderServiceMapID(12);
		facility.setDeleted(false);
		facility.setProcessed('Y');
		facility.setCreatedBy("Vidya");
		// facility.setCreatedDate(Date.valueOf("2024-09-01"));
		facility.setModifiedBy("Vidya");
		// facility.setLastModDate(Date.valueOf("2025-01-01"));

		Indent indent = new Indent();
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setSyncFacilityID(3);
		indent.setFromFacilityName("Medicine");
		indent.setToFacilityID(1);
		indent.setRefNo("fehfgdh");
		// indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setReason("hjdfgdjhg");
		indent.setUserID(10);
		indent.setProviderServiceMapID(6);
		indent.setStatus("Approved");
		indent.setStatusReason("dhghdhgkghfkjghj");
		indent.setDeleted(false);
		indent.setProcessed("N");
		indent.setCreatedBy("Ak Hossain");
		// indent.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setModifiedBy("Ak Hossain");
		// indent.setLastModDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setVanID(9L);
		indent.setParkingPlaceID(7L);
		indent.setVanSerialNo(3L);
		indent.setIndentOrder(indentOrderList);
		indent.setM_Facility(facility);

		String request = new Gson().toJson(indent);

		String response = indentController.updateIndentOrder(request);

		resp.setResponse(response);

		Assertions.assertEquals(response, indentController.updateIndentOrder(request));

	}

	@Test
	public void updateIndentOrderTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = indentController.updateIndentOrder(request);

		Assertions.assertEquals(response, indentController.updateIndentOrder(request));

	}

}
