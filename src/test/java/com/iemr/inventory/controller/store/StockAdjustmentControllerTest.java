package com.iemr.inventory.controller.store;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.stockadjustment.StockAdjustment;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.service.stockadjustment.StockAdjustmentServiceImpl;
import com.iemr.inventory.utils.exception.InventoryException;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class StockAdjustmentControllerTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@InjectMocks
	StockAdjustmentController stockAdjustmentController;

	@Mock
	StockAdjustmentServiceImpl stockAdjustmentServiceImpl;

	@Test
	public void stockadjustmentdraftTest() {

		String store = "Medicine store";

		Date date = new Date();

		StockAdjustmentDraft stockAdjustmentDraft = new StockAdjustmentDraft();
		stockAdjustmentDraft.setFacilityID(12);
		stockAdjustmentDraft.setDraftDesc("store draft");
		stockAdjustmentDraft.setDraftName("store");
		stockAdjustmentDraft.setRefNo("R87er89E");
		stockAdjustmentDraft.setIsCompleted(true);
		stockAdjustmentDraft.setProviderServiceMapID(6);
		stockAdjustmentDraft.setDeleted(false);
		stockAdjustmentDraft.setVanID(1L);
		stockAdjustmentDraft.setProcessed('Y');
		stockAdjustmentDraft.setCreatedBy("AK Kundu");
		stockAdjustmentDraft.setCreatedDate(date);
		stockAdjustmentDraft.setModifiedBy("AK Kundu");
		stockAdjustmentDraft.setLastModDate(date);
		stockAdjustmentDraft.setStockAdjustmentDraftID(12L);

		String resp = stockAdjustmentController.stockadjustmentdraft(store);

		OutputResponse response = new OutputResponse();

		String saveData = stockAdjustmentDraft.toString();

		response.setResponse(saveData);

		Assertions.assertEquals(resp, stockAdjustmentController.stockadjustmentdraft(store));
	}

	@Test
	public void stockadjustmentdraftTestException() {

		String store = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String resp = stockAdjustmentController.stockadjustmentdraft(store);

		Assertions.assertEquals(resp, stockAdjustmentController.stockadjustmentdraft(store));
	}

	@Test
	public void getstockadjustmentdraftTransactionTest() {

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

		StockAdjustmentDraft stockAdjustmentDraft = new StockAdjustmentDraft();
		stockAdjustmentDraft.setFacilityID(12);
		stockAdjustmentDraft.setDraftDesc("store draft");
		stockAdjustmentDraft.setDraftName("store");
		stockAdjustmentDraft.setRefNo("R87er89E");
		stockAdjustmentDraft.setIsCompleted(true);
		stockAdjustmentDraft.setProviderServiceMapID(6);
		stockAdjustmentDraft.setDeleted(false);
		stockAdjustmentDraft.setVanID(1L);
		stockAdjustmentDraft.setProcessed('Y');
		stockAdjustmentDraft.setCreatedBy("AK Kundu");
		stockAdjustmentDraft.setModifiedBy("AK Kundu");
		stockAdjustmentDraft.setStockAdjustmentDraftID(12L);

		List<StockAdjustmentDraft> saveData = new ArrayList<StockAdjustmentDraft>();

		saveData.add(stockAdjustmentDraft);

		when(stockAdjustmentServiceImpl.getStockAjustmentDraftTransaction(itemStockinput)).thenReturn(saveData);

		String resp = stockAdjustmentController.getstockadjustmentdraftTransaction(itemStockinput);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		Assertions.assertEquals(response.toString(),
				stockAdjustmentController.getstockadjustmentdraftTransaction(itemStockinput));
	}

	@Test
	public void getstockadjustmentdraftTransactionTestException() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockAdjustmentServiceImpl.getStockAjustmentDraftTransaction(itemStockinput))
				.thenThrow(NotFoundException.class);

		String resp = stockAdjustmentController.getstockadjustmentdraftTransaction(itemStockinput);

		Assertions.assertEquals(resp, stockAdjustmentController.getstockadjustmentdraftTransaction(itemStockinput));
	}

	@Test
	public void getforEditsSockAdjustmentdraftTransactionTest() {

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

		StockAdjustmentDraft saveData = new StockAdjustmentDraft();
		saveData.setFacilityID(12);
		saveData.setDraftDesc("store draft");
		saveData.setDraftName("store");
		saveData.setRefNo("R87er89E");
		saveData.setIsCompleted(true);
		saveData.setProviderServiceMapID(6);
		saveData.setDeleted(false);
		saveData.setVanID(1L);
		saveData.setProcessed('Y');
		saveData.setCreatedBy("AK Kundu");
		saveData.setModifiedBy("AK Kundu");
		saveData.setStockAdjustmentDraftID(12L);

		String resp = stockAdjustmentController.getstockadjustmentdraftTransaction(itemStockinput);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		Assertions.assertEquals(resp, stockAdjustmentController.getstockadjustmentdraftTransaction(itemStockinput));
	}

	@Test
	public void stockadjustmentTest() throws InventoryException {

		Date date = new Date();

		StockAdjustment StockAdjustment = new StockAdjustment();

		StockAdjustment.setFacilityID(12);
		StockAdjustment.setRefNo("R87er89E");
		StockAdjustment.setProviderServiceMapID(6);
		StockAdjustment.setDeleted(false);
		StockAdjustment.setVanID(1L);
		StockAdjustment.setProcessed('Y');
		StockAdjustment.setCreatedBy("AK Kundu");
		StockAdjustment.setCreatedDate(date);
		StockAdjustment.setModifiedBy("AK Kundu");
		StockAdjustment.setLastModDate(date);
		StockAdjustment.setStockAdjustmentDraftID(12L);

		when(stockAdjustmentServiceImpl.savetransaction(StockAdjustment)).thenReturn(StockAdjustment);

		String resp = stockAdjustmentController.stockadjustment(StockAdjustment);

		OutputResponse response = new OutputResponse();

		String saveData = StockAdjustment.toString();

		response.setResponse(saveData);

		Assertions.assertEquals(response.toString(), stockAdjustmentController.stockadjustment(StockAdjustment));
	}

	@Test
	public void stockadjustmentTestException() throws InventoryException {

		StockAdjustment StockAdjustment = new StockAdjustment();

		when(stockAdjustmentServiceImpl.savetransaction(StockAdjustment)).thenThrow(NotFoundException.class);

		String resp = stockAdjustmentController.stockadjustment(StockAdjustment);

		Assertions.assertEquals(resp, stockAdjustmentController.stockadjustment(StockAdjustment));
	}

	@Test
	public void getforeditStockAdjustmentTransactionTest() {

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

		StockAdjustment stockAdjustment = new StockAdjustment();

		stockAdjustment.setFacilityID(12);
		stockAdjustment.setRefNo("R87er89E");
		stockAdjustment.setProviderServiceMapID(6);
		stockAdjustment.setDeleted(false);
		stockAdjustment.setVanID(1L);
		stockAdjustment.setProcessed('Y');
		stockAdjustment.setCreatedBy("AK Kundu");
		stockAdjustment.setModifiedBy("AK Kundu");
		stockAdjustment.setStockAdjustmentDraftID(12L);

		List<StockAdjustment> saveData = new ArrayList<StockAdjustment>();

		saveData.add(stockAdjustment);

		when(stockAdjustmentServiceImpl.getStockAjustmentTransaction(itemStockinput)).thenReturn(saveData);

		String resp = stockAdjustmentController.getforeditStockAdjustmentTransaction(itemStockinput);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		Assertions.assertEquals(response.toString(),
				stockAdjustmentController.getforeditStockAdjustmentTransaction(itemStockinput));
	}

	@Test
	public void getforeditStockAdjustmentTransactionTestException() throws InventoryException {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockAdjustmentServiceImpl.getStockAjustmentTransaction(itemStockinput))
				.thenThrow(NotFoundException.class);

		String resp = stockAdjustmentController.getforeditStockAdjustmentTransaction(itemStockinput);

		Assertions.assertEquals(resp, stockAdjustmentController.getforeditStockAdjustmentTransaction(itemStockinput));
	}

	@Test
	public void getforEditsStockAdjustmentTransactionTest() {

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

		StockAdjustment saveData = new StockAdjustment();

		saveData.setFacilityID(12);
		saveData.setRefNo("R87er89E");
		saveData.setProviderServiceMapID(6);
		saveData.setDeleted(false);
		saveData.setVanID(1L);
		saveData.setProcessed('Y');
		saveData.setCreatedBy("AK Kundu");
		saveData.setModifiedBy("AK Kundu");
		saveData.setStockAdjustmentDraftID(12L);
		saveData.setStockAdjustmentID(1L);

		when(stockAdjustmentServiceImpl.getforeditStockAjustmentTransaction(itemStockinput.getStockAdjustmentID()))
				.thenReturn(saveData);

		String resp = stockAdjustmentController.getforEditsStockAdjustmentTransaction(itemStockinput);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		Assertions.assertEquals(response.toString(),
				stockAdjustmentController.getforEditsStockAdjustmentTransaction(itemStockinput));
	}

	@Test
	public void getforEditsStockAdjustmentTransactionTestException() {

		ItemStockEntryinput itemStockinput = new ItemStockEntryinput();

		when(stockAdjustmentServiceImpl.getforeditStockAjustmentTransaction(itemStockinput.getStockAdjustmentID()))
				.thenThrow(NotFoundException.class);

		String resp = stockAdjustmentController.getforEditsStockAdjustmentTransaction(itemStockinput);

		Assertions.assertEquals(resp, stockAdjustmentController.getforEditsStockAdjustmentTransaction(itemStockinput));
	}

}