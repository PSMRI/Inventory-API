package com.iemr.inventory.controller.stockExit;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockExit.StoreSelfConsumption;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.data.stockExit.T_StockTransfer;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.service.stockExit.StockExitServiceImpl;
import com.iemr.inventory.utils.exception.InventoryException;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class StockExitControllerTest {

	@InjectMocks
	StockExitController stockExitController;

	@Mock
	StockExitServiceImpl stockExitService;

	@Test
	public void patientIssueTest() throws InventoryException {

		OutputResponse output = new OutputResponse();

		T_PatientIssue patientIssue = new T_PatientIssue();

		patientIssue.setBenRegID(12L);
		patientIssue.setFacilityID(3);

		Integer value = 1;

		when(stockExitService.issuePatientDrugs(patientIssue)).thenReturn(value);

		String resp = stockExitController.patientIssue(patientIssue);

		output.setResponse("Successfully Created");

		Assertions.assertEquals(1, value);
		Assertions.assertEquals(output.toString(), stockExitController.patientIssue(patientIssue));
	}

	@Test
	public void patientIssueTestException() throws InventoryException {

		T_PatientIssue patientIssue = new T_PatientIssue();

		when(stockExitService.issuePatientDrugs(patientIssue)).thenThrow(NotFoundException.class);

		String resp = stockExitController.patientIssue(patientIssue);

		Assertions.assertEquals(resp, stockExitController.patientIssue(patientIssue));

	}
	

	@Test
	public void patientIssueTest_Exception() throws InventoryException {

		OutputResponse output = new OutputResponse();

		T_PatientIssue patientIssue = new T_PatientIssue();

		patientIssue.setBenRegID(12L);
		patientIssue.setFacilityID(3);

		Integer value = 0;

		when(stockExitService.issuePatientDrugs(patientIssue)).thenReturn(value);

		String resp = stockExitController.patientIssue(patientIssue);

		try {
				throw new Exception("Error occured while saving the request");
		} catch (Exception e) {
			output.setError(e);
		}

		Assertions.assertNotEquals(1, value);
		Assertions.assertEquals(resp, stockExitController.patientIssue(patientIssue));
	}

	@Test
	public void storeSelfConsumptionTest() {

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);

		List<ItemStockExit> itemStockExit = new ArrayList<ItemStockExit>();
		itemStockExit.add(itemStockExitO);

		StoreSelfConsumption storeSelfConsumption = new StoreSelfConsumption();

		storeSelfConsumption.setConsumptionID(1L);
		storeSelfConsumption.setVanSerialNo(2L);
		storeSelfConsumption.setFacilityID(3);
		storeSelfConsumption.setSyncFacilityID(4);
		storeSelfConsumption.setVanID(5L);
		storeSelfConsumption.setRefNo("R73674A");
		storeSelfConsumption.setReason("No reason");
		storeSelfConsumption.setParkingPlaceID(6L);
		storeSelfConsumption.setItemStockExit(itemStockExit);

		Integer value = 1;

		when(stockExitService.storeSelfConsumption(storeSelfConsumption)).thenReturn(value);

		String response = stockExitController.storeSelfConsumption(storeSelfConsumption);

		OutputResponse output = new OutputResponse();

		output.setResponse("Successfully Created");

		Assertions.assertEquals(1, value);
		Assertions.assertEquals(output.toString(), stockExitController.storeSelfConsumption(storeSelfConsumption));
	}

	@Test
	public void storeSelfConsumptionTestException() {

		StoreSelfConsumption storeSelfConsumption = new StoreSelfConsumption();

		when(stockExitService.storeSelfConsumption(storeSelfConsumption)).thenThrow(NotFoundException.class);

		String resp = stockExitController.storeSelfConsumption(storeSelfConsumption);

		Assertions.assertEquals(resp, stockExitController.storeSelfConsumption(storeSelfConsumption));

	}

	@Test
	public void storeSelfConsumptionTest_Exception() {

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);

		List<ItemStockExit> itemStockExit = new ArrayList<ItemStockExit>();
		itemStockExit.add(itemStockExitO);

		StoreSelfConsumption storeSelfConsumption = new StoreSelfConsumption();

		storeSelfConsumption.setConsumptionID(1L);
		storeSelfConsumption.setVanSerialNo(2L);
		storeSelfConsumption.setFacilityID(3);
		storeSelfConsumption.setSyncFacilityID(4);
		storeSelfConsumption.setVanID(5L);
		storeSelfConsumption.setRefNo("R73674A");
		storeSelfConsumption.setReason("No reason");
		storeSelfConsumption.setParkingPlaceID(6L);
		storeSelfConsumption.setItemStockExit(itemStockExit);

		Integer value = 0;

		when(stockExitService.storeSelfConsumption(storeSelfConsumption)).thenReturn(value);

		String response = stockExitController.storeSelfConsumption(storeSelfConsumption);

		OutputResponse output = new OutputResponse();

		try {
			throw new Exception("Error occured while saving the request");
		} catch (Exception e) {
			output.setError(e);
		}

		Assertions.assertNotEquals(1, value);
		Assertions.assertEquals(response, stockExitController.storeSelfConsumption(storeSelfConsumption));
	}

	@Test
	public void storeTransferTest() {

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);

		List<ItemStockExit> itemStockExit = new ArrayList<ItemStockExit>();
		itemStockExit.add(itemStockExitO);

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setModifiedBy("Vidya");

		T_StockTransfer stockTransfer = new T_StockTransfer();

		stockTransfer.setStockTransferID(1L);
		stockTransfer.setVanSerialNo(2L);
		stockTransfer.setTransferFromFacilityID(3);
		stockTransfer.setSyncFacilityID(4);
		stockTransfer.setTransferFromFacility(mainStoreFacility);
		stockTransfer.setTransferToFacilityID(5);
		stockTransfer.setTransferToFacility(mainStoreFacility);
		stockTransfer.setRefNo("R73674A");
		stockTransfer.setIssueType("sgfgd");
		stockTransfer.setDeleted(false);
		stockTransfer.setProcessed('Y');
		stockTransfer.setCreatedBy("Vidya");
		stockTransfer.setModifiedBy("Vidya");
		stockTransfer.setVanID(3L);
		stockTransfer.setToVanID(4L);

		Integer value = 1;

		when(stockExitService.storeTransfer(stockTransfer)).thenReturn(value);

		String response = stockExitController.storeTransfer(stockTransfer);

		OutputResponse output = new OutputResponse();

		output.setResponse("Successfully Created");

		Assertions.assertEquals(1, value);
		Assertions.assertEquals(output.toString(), stockExitController.storeTransfer(stockTransfer));
	}

	@Test
	public void storeTransferTestException() {

		T_StockTransfer stockTransfer = new T_StockTransfer();

		when(stockExitService.storeTransfer(stockTransfer)).thenThrow(NotFoundException.class);

		String resp = stockExitController.storeTransfer(stockTransfer);

		Assertions.assertEquals(resp, stockExitController.storeTransfer(stockTransfer));

	}

	@Test
	public void storeTransferTest_Exception() {

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);

		List<ItemStockExit> itemStockExit = new ArrayList<ItemStockExit>();
		itemStockExit.add(itemStockExitO);

		M_Facility mainStoreFacility = new M_Facility();

		mainStoreFacility.setFacilityID(3);
		mainStoreFacility.setFacilityName("Medicine Store");
		mainStoreFacility.setFacilityDesc("It is a store to buy medicines");
		mainStoreFacility.setFacilityCode("Sq3672467");
		mainStoreFacility.setFacilityTypeID(4);
		mainStoreFacility.setLocation("Pune");
		mainStoreFacility.setPhysicalLocation("Kharadi");
		mainStoreFacility.setStoreType("Commercial");
		mainStoreFacility.setStatus("Open");
		mainStoreFacility.setIsMainFacility(true);
		mainStoreFacility.setMainFacilityID(45);
		mainStoreFacility.setProviderServiceMapID(12);
		mainStoreFacility.setDeleted(false);
		mainStoreFacility.setProcessed('Y');
		mainStoreFacility.setCreatedBy("Vidya");
		mainStoreFacility.setModifiedBy("Vidya");

		T_StockTransfer stockTransfer = new T_StockTransfer();

		stockTransfer.setStockTransferID(1L);
		stockTransfer.setVanSerialNo(2L);
		stockTransfer.setTransferFromFacilityID(3);
		stockTransfer.setSyncFacilityID(4);
		stockTransfer.setTransferFromFacility(mainStoreFacility);
		stockTransfer.setTransferToFacilityID(5);
		stockTransfer.setTransferToFacility(mainStoreFacility);
		stockTransfer.setRefNo("R73674A");
		stockTransfer.setIssueType("sgfgd");
		stockTransfer.setDeleted(false);
		stockTransfer.setProcessed('Y');
		stockTransfer.setCreatedBy("Vidya");
		stockTransfer.setModifiedBy("Vidya");
		stockTransfer.setVanID(3L);
		stockTransfer.setToVanID(4L);

		Integer value = 0;

		when(stockExitService.storeTransfer(stockTransfer)).thenReturn(value);

		String response = stockExitController.storeTransfer(stockTransfer);

		OutputResponse output = new OutputResponse();

		try {
			throw new Exception("Error occured while saving the request");
		} catch (Exception e) {
			output.setError(e);
		}

		Assertions.assertEquals(response, stockExitController.storeTransfer(stockTransfer));
	}

	@Test
	public void getPatientissueAllDetailTest() {

		OutputResponse exp = new OutputResponse();

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentDraftID(12L);
		itemStockinput.setStockAdjustmentID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(7);
		itemStockinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockinput.setPatientIssueID(6L);

		T_PatientIssue getData = new T_PatientIssue();

		getData.setBenRegID(12L);
		getData.setFacilityID(3);
		getData.setPatientIssueID(itemStockinput.getPatientIssueID());

		when(stockExitService.getPatientissueAllDetail((Long) itemStockinput.getPatientIssueID())).thenReturn(getData);

		exp.setResponse(getData.toString());

		Assertions.assertEquals(exp.toString(), stockExitController.getPatientissueAllDetail(itemStockinput));
	}

	@Test
	public void getPatientissueAllDetailTestException() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockExitService.getPatientissueAllDetail((Long) itemStockinput.getPatientIssueID()))
				.thenThrow(NotFoundException.class);

		String resp = stockExitController.getPatientissueAllDetail(itemStockinput);

		Assertions.assertEquals(resp, stockExitController.getPatientissueAllDetail(itemStockinput));

	}

	@Test
	public void getPatientissueTest() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentID(1L);
		itemStockinput.setStockAdjustmentDraftID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(3);

		T_PatientIssue patissue = new T_PatientIssue();

		patissue.setPatientIssueID(456L);
		patissue.setVanSerialNo(1L);
		patissue.setBenRegID(123L);
		patissue.setFacilityID(123);
		patissue.setSyncFacilityID(123);
		patissue.setVisitID(123);
		patissue.setVisitCode(123L);
		patissue.setAge(24);
		patissue.setGender("Male");
		patissue.setDoctorName("Jacob");
		patissue.setPrescriptionID(123456789);
		patissue.setReference("TestReference");
		patissue.setIssueType("TestIssue");
		patissue.setIssuedBy("TestIssuedBy");

		List<T_PatientIssue> getData = new ArrayList<T_PatientIssue>();
		getData.add(patissue);

		when(stockExitService.getpatientIssue(itemStockinput)).thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockExitController.getPatientissue(itemStockinput));
	}

	@Test
	public void getPatientissueTestException() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockExitService.getpatientIssue(itemStockinput)).thenThrow(NotFoundException.class);

		String resp = stockExitController.getPatientissue(itemStockinput);

		Assertions.assertEquals(resp, stockExitController.getPatientissue(itemStockinput));

	}

	@Test
	public void getSelfConsumptionTest() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentID(1L);
		itemStockinput.setStockAdjustmentDraftID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(3);

		List<StoreSelfConsumption> getData = new ArrayList<StoreSelfConsumption>();

		StoreSelfConsumption storeSelfConsumption = new StoreSelfConsumption();

		storeSelfConsumption.setConsumptionID(1L);
		storeSelfConsumption.setVanSerialNo(2L);
		storeSelfConsumption.setFacilityID(3);
		storeSelfConsumption.setSyncFacilityID(4);
		storeSelfConsumption.setVanID(5L);
		storeSelfConsumption.setRefNo("R73674A");
		storeSelfConsumption.setReason("No reason");
		storeSelfConsumption.setParkingPlaceID(6L);

		getData.add(storeSelfConsumption);

		when(stockExitService.getstoreSelfConsumption(itemStockinput)).thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockExitController.getSelfConsumption(itemStockinput));

	}

	@Test
	public void getSelfConsumptionTestException() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockExitService.getstoreSelfConsumption(itemStockinput)).thenThrow(NotFoundException.class);

		String resp = stockExitController.getSelfConsumption(itemStockinput);

		Assertions.assertEquals(resp, stockExitController.getSelfConsumption(itemStockinput));

	}

	@Test
	public void getStoreTransferTest() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentID(1L);
		itemStockinput.setStockAdjustmentDraftID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(3);

		T_StockTransfer stockTransfer = new T_StockTransfer();

		stockTransfer.setStockTransferID(1L);
		stockTransfer.setVanSerialNo(2L);
		stockTransfer.setTransferFromFacilityID(3);
		stockTransfer.setSyncFacilityID(4);
		stockTransfer.setTransferToFacilityID(5);
		stockTransfer.setRefNo("R73674A");
		stockTransfer.setIssueType("sgfgd");
		stockTransfer.setDeleted(false);
		stockTransfer.setProcessed('Y');
		stockTransfer.setCreatedBy("Vidya");
		stockTransfer.setModifiedBy("Vidya");
		stockTransfer.setVanID(3L);
		stockTransfer.setToVanID(4L);

		List<T_StockTransfer> getData = new ArrayList<T_StockTransfer>();

		getData.add(stockTransfer);

		when(stockExitService.getStoreTransfer(itemStockinput)).thenReturn(getData);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockExitController.getStoreTransfer(itemStockinput));

	}

	@Test
	public void getStoreTransferTestException() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockExitService.getStoreTransfer(itemStockinput)).thenThrow(NotFoundException.class);

		String resp = stockExitController.getStoreTransfer(itemStockinput);

		Assertions.assertEquals(resp, stockExitController.getStoreTransfer(itemStockinput));

	}

	@Test
	public void getPatientissueItemEntryTest() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentDraftID(12L);
		itemStockinput.setStockAdjustmentID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(7);
		itemStockinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockinput.setPatientIssueID(6L);

		ItemStockExitMap itemStockExitMap = new ItemStockExitMap();

		itemStockExitMap.setBatchNo("B65346NK");
		itemStockExitMap.setCreatedBy("AN Hossain");
		itemStockExitMap.setQuantity(101);
		itemStockExitMap.setDeleted(false);

		List<ItemStockExitMap> getData = new ArrayList<ItemStockExitMap>();

		getData.add(itemStockExitMap);

		when(stockExitService.getpatientIssueItemLIst(itemStockinput)).thenReturn(getData);

		String resp = stockExitController.getPatientissueItemEntry(itemStockinput);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockExitController.getPatientissueItemEntry(itemStockinput));
	}

	@Test
	public void getPatientissueItemEntryTestException() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockExitService.getpatientIssueItemLIst(itemStockinput)).thenThrow(NotFoundException.class);

		String resp = stockExitController.getPatientissueItemEntry(itemStockinput);

		Assertions.assertEquals(resp, stockExitController.getPatientissueItemEntry(itemStockinput));

	}

	@Test
	public void getSelfConsumptionItemEntryTest() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentDraftID(12L);
		itemStockinput.setStockAdjustmentID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(7);
		itemStockinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));

		ItemStockExitMap itemStockExitMap = new ItemStockExitMap();

		itemStockExitMap.setBatchNo("B65346NK");
		itemStockExitMap.setCreatedBy("AN Hossain");
		itemStockExitMap.setQuantity(101);
		itemStockExitMap.setDeleted(false);

		List<ItemStockExitMap> getData = new ArrayList<ItemStockExitMap>();

		getData.add(itemStockExitMap);
		
		String input = getData.toString();

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());
		
		String resp=stockExitController.getSelfConsumptionItemEntry(input);

		Assertions.assertEquals(resp, stockExitController.getSelfConsumptionItemEntry(input));
	}
	
	
	@Test
	public void getSelfConsumptionItemEntryTestException() {
		
		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();
		
		String input = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		when(stockExitService.getstoreSelfConsumptionItemList(itemStockinput)).thenThrow(NotFoundException.class);
		
		String resp = stockExitController.getSelfConsumptionItemEntry(input);
		
		Assertions.assertEquals(resp, stockExitController.getSelfConsumptionItemEntry(input));
	}

	@Test
	public void getStoreTransferItemEntryTest() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		itemStockinput.setStockAdjustmentDraftID(12L);
		itemStockinput.setStockAdjustmentID(2L);
		itemStockinput.setConsumptionID(3L);
		itemStockinput.setPatientIssueID(4L);
		itemStockinput.setStockTransferID(5L);
		itemStockinput.setPhyEntryID(6L);
		itemStockinput.setFacilityID(7);
		itemStockinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));

		ItemStockExitMap itemStockExitMap = new ItemStockExitMap();

		itemStockExitMap.setBatchNo("B65346NK");
		itemStockExitMap.setCreatedBy("AN Hossain");
		itemStockExitMap.setQuantity(101);
		itemStockExitMap.setDeleted(false);

		List<ItemStockExitMap> getData = new ArrayList<ItemStockExitMap>();

		getData.add(itemStockExitMap);

		when(stockExitService.getStoreTransferItemEntry(itemStockinput)).thenReturn(getData);

		String resp = stockExitController.getStoreTransferItemEntry(itemStockinput);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(response.toString(), stockExitController.getStoreTransferItemEntry(itemStockinput));
	}
	
	@Test
	public void getStoreTransferItemEntryTestException() {
		
		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();
		
		when(stockExitService.getStoreTransferItemEntry(itemStockinput)).thenThrow(NotFoundException.class);
		
		String resp =stockExitController.getStoreTransferItemEntry(itemStockinput);
		
		Assertions.assertEquals(resp, stockExitController.getStoreTransferItemEntry(itemStockinput));
	}
	
}
