package com.iemr.inventory.controller.patientreturn;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.gson.Gson;
import com.iemr.inventory.data.patientreturn.ItemDetailModel;
import com.iemr.inventory.data.patientreturn.PatientReturnModel;
import com.iemr.inventory.data.patientreturn.ReturnHistoryModel;
import com.iemr.inventory.data.stockExit.ItemReturnEntry;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.service.patientreturn.PatientReturnService;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class PatientReturnControllerTest {

	@InjectMocks
	PatientReturnController patientReturnController;

	@Mock
	PatientReturnService patientReturnService;

	@Test
	public void getItemNameByRegIDTest() {

		T_PatientIssue patientIssue = new T_PatientIssue();

		patientIssue.setPatientIssueID(456L);
		patientIssue.setVanSerialNo(1L);
		patientIssue.setBenRegID(123L);
		patientIssue.setFacilityID(123);
		patientIssue.setSyncFacilityID(123);
		patientIssue.setVisitID(123);
		patientIssue.setPatientName("Joe");
		patientIssue.setAge(24);
		patientIssue.setGender("Male");
		patientIssue.setDoctorName("Jacob");
		patientIssue.setPrescriptionID(123456789);
		patientIssue.setReference("TestReference");
		patientIssue.setIssueType("TestIssue");
		patientIssue.setIssuedBy("TestIssuedBy");

		PatientReturnModel patientReturnModel = new PatientReturnModel(123L, 123, 1, "John");

		List<PatientReturnModel> getData = new ArrayList<PatientReturnModel>();

		getData.add(patientReturnModel);

		String request = getData.toString();

		String input = request;

		String resp = patientReturnController.getItemNameByRegID(input);

		OutputResponse response = new OutputResponse();

		response.setResponse(request);

		Assertions.assertEquals(resp, patientReturnController.getItemNameByRegID(input));
	}

	@Test
	public void getItemNameByRegIDTestException() {

		String input = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(patientReturnService.getItemNameByRegID(any())).thenThrow(NotFoundException.class);

		String response = patientReturnController.getItemNameByRegID(input);

		Assertions.assertEquals(response, patientReturnController.getItemNameByRegID(input));

	}

	@Test
	public void getItemDetailByBenTest() {

		ItemDetailModel itemDetailModel = new ItemDetailModel(3, "itemName", "batchNo", 10,
				Timestamp.valueOf("2018-09-01 09:01:16"), false, false, 1L, 2L, 3L, 30L, 4L, 7, 9);

		itemDetailModel.setBenRegID(4L);
		itemDetailModel.setItemID(3);
		itemDetailModel.setFacilityID(9);

		List<ItemDetailModel> getData = new ArrayList<ItemDetailModel>();

		getData.add(itemDetailModel);

		String request = getData.toString();

		String input = request;

		String response = patientReturnController.getItemDetailByBen(input);

		OutputResponse response1 = new OutputResponse();

		response1.setResponse(getData.toString());

		Assertions.assertEquals(response, patientReturnController.getItemDetailByBen(input));
	}

	@Test
	public void getItemDetailByBenTestException() {

		String input = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(patientReturnService.getItemDetailByBen(any())).thenThrow(NotFoundException.class);

		String response = patientReturnController.getItemDetailByBen(input);

		Assertions.assertEquals(response, patientReturnController.getItemDetailByBen(input));

	}

	@Test
	public void updateQuantityReturnedTest() {

		String inputJson = "[{\"itemID\": 123, \"providerServiceMapID\": 12, \"facilityID\": 23, \"issuedQuantity\": 789}]";

		ItemDetailModel[] itemDetailModel = new Gson().fromJson(inputJson, ItemDetailModel[].class);

		String request = itemDetailModel.toString();

		String getData = request;

		String response = patientReturnController.updateQuantityReturned(request);

		OutputResponse response1 = new OutputResponse();

		response1.setResponse(getData.toString());

		Assertions.assertEquals(response, patientReturnController.updateQuantityReturned(request));
	}

	@Test
	public void updateQuantityReturnedTestException() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		String response = patientReturnController.updateQuantityReturned(request);

		Assertions.assertEquals(response, patientReturnController.updateQuantityReturned(request));

	}

	@Test
	public void getBenReturnHistoryTest() {

		ItemReturnEntry itemReturnEntry = new ItemReturnEntry();

		itemReturnEntry.setPatientReturnID(1L);
		itemReturnEntry.setBenRegID(12L);
		itemReturnEntry.setVisitCode(1L);
		itemReturnEntry.setVisitID(13L);
		itemReturnEntry.setFacilityID(56);
		itemReturnEntry.setItemStockExitID(123L);
		itemReturnEntry.setProviderServiceMapID(23);
		itemReturnEntry.setCreatedBy("Meetali Kundu");
		itemReturnEntry.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemReturnEntry.setModifiedBy("Susmita Kundu");
		itemReturnEntry.setLastModDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemReturnEntry.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemReturnEntry.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));

		String itemName = "Medicine";
		String batchNo = "M365E";
		Integer issuedQuantity = Integer.valueOf(3);

		ReturnHistoryModel returnHistoryModel = new ReturnHistoryModel(itemName, batchNo, issuedQuantity, null, null,
				null, null, null, null, null);

		returnHistoryModel.setItemName(itemName);
		returnHistoryModel.setBatchNo(batchNo);
		returnHistoryModel.setIssuedQuantity(issuedQuantity);

		List<ReturnHistoryModel> getData = new ArrayList<ReturnHistoryModel>();

		getData.add(returnHistoryModel);

		String input = getData.toString();

		String response = patientReturnController.getBenReturnHistory(input);

		OutputResponse response1 = new OutputResponse();

		response1.setResponse(getData.toString());

		Assertions.assertEquals(response, patientReturnController.getBenReturnHistory(input));
	}
	
	@Test
	public void getBenReturnHistoryTestException() {

		String input = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(patientReturnService.getBenReturnHistory(any())).thenThrow(NotFoundException.class);

		String response = patientReturnController.getBenReturnHistory(input);

		Assertions.assertEquals(response, patientReturnController.getBenReturnHistory(input));

	}
	
}
