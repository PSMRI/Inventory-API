package com.iemr.inventory.controller.report;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.service.report.CRMReportService;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

@ExtendWith(MockitoExtension.class)
class CRMReportControllerTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Mock
	private CRMReportService crmReportService;

	@InjectMocks
	private CRMReportController crmReportController;

	@Test
	public void testGetInwardStockReport() {

		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getInwardStockReport(request);

		logger.info("getInwardStockReport request " + request.toString());
		OutputResponse response1 = new OutputResponse();
		response1.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getInwardStockReport(request));

	}

	@Test
	public void testGetInwardStockReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getInwardStockReport(request);

		Assertions.assertEquals(response, crmReportController.getInwardStockReport(request));
	}

	@Test
	public void testGetExpiryReport() {

		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getExpiryReport(request);

		logger.info("getExpiryReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getExpiryReport(request));
	}

	@Test
	public void testGetExpiryReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getExpiryReport(request);

		Assertions.assertEquals(response, crmReportController.getExpiryReport(request));
	}

	@Test
	public void testGetConsumptionReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getConsumptionReport(request);

		logger.info("getConsumptionReport request " + request.toString());
		OutputResponse response1 = new OutputResponse();
		response1.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getConsumptionReport(request));
	}

	@Test
	public void testGetConsumptionReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getConsumptionReport(request);

		Assertions.assertEquals(response, crmReportController.getConsumptionReport(request));
	}

	@Test
	public void testGetBenDrugIssueReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getBenDrugIssueReport(request);
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getBenDrugIssueReport(request));

	}

	@Test
	public void testGetBenDrugIssueReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getBenDrugIssueReport(request);

		Assertions.assertEquals(response, crmReportController.getBenDrugIssueReport(request));
	}

	@Test
	public void testGetDailyStockDetailReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getDailyStockDetailReport(request);

		logger.info("getDailyStockDetailReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getDailyStockDetailReport(request));
	}

	@Test
	public void testGetDailyStockDetailReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getDailyStockDetailReport(request);

		Assertions.assertEquals(response, crmReportController.getDailyStockDetailReport(request));
	}

	@Test
	public void testGetDailyStockSummaryReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getDailyStockSummaryReport(request);

		logger.info("getDailyStockSummaryReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getDailyStockSummaryReport(request));
	}

	@Test
	public void testGetDailyStockSummaryReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getDailyStockSummaryReport(request);

		Assertions.assertEquals(response, crmReportController.getDailyStockSummaryReport(request));
	}

	@Test
	public void testGetMonthlyReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getMonthlyReport(request);

		logger.info("getMonthlyReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getMonthlyReport(request));

	}

	@Test
	public void testGetMonthlyReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getMonthlyReport(request);

		Assertions.assertEquals(response, crmReportController.getMonthlyReport(request));
	}

	@Test
	public void testGetYearlyReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getYearlyReport(request);

		logger.info("getYearlyReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getYearlyReport(request));

	}

	@Test
	public void testGetYearlyReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getYearlyReport(request);

		Assertions.assertEquals(response, crmReportController.getYearlyReport(request));
	}

	@Test
	public void testGetShortExpiryReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getShortExpiryReport(request);

		logger.info("getShortExpiryReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getShortExpiryReport(request));

	}

	@Test
	public void testGetShortExpiryReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getShortExpiryReport(request);

		Assertions.assertEquals(response, crmReportController.getShortExpiryReport(request));
	}

	@Test
	public void testGetTransitReport() {
		ItemStockEntryReport report = new ItemStockEntryReport();
		report.setFactItemStockEntryID(3L);
		report.setItemStockEntryID(4L);
		report.setFacilityID(5);
		report.setFacilityName("Medicine");
		report.setFacilityDesc("For cure diseases");
		report.setFacilityCode("M63465E");
		report.setFacilityTypeID(6L);
		report.setIsMainFacility(true);
		report.setMainFacilityID(7);
		report.setItemID(8);
		report.setItemName("Underscore");
		report.setItemDesc("Under score healthcare");
		report.setItemCategoryID(9);
		report.setItemCategoryName("Health Care");
		report.setAlertBeforeDays("30 Days");
		report.setStrength("Strong");
		report.setBatchNo("B64563bv");
		report.setQuantity(100);
		report.setQuantityInHand(80);
		report.setUnitCostPrice(Double.valueOf("12"));
		report.setExpiryDate(Date.valueOf("2026-09-09"));
		report.setEntryTypeID(10L);
		report.setProviderServiceMapID(11);
		report.setCreatedBy("A K");
		report.setCreatedDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setStartDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setEndDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		report.setMonth(8);
		report.setMonthName("August");
		report.setYear(2022);

		String request = report.toString();

		String resp = crmReportController.getTransitReport(request);

		logger.info("getTransitReport request " + request.toString());
		OutputResponse response = new OutputResponse();
		response.setResponse(resp);

		Assertions.assertEquals(resp, crmReportController.getTransitReport(request));
	}

	@Test
	public void testGetTransitReport_Exception() {

		String request = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = crmReportController.getTransitReport(request);

		Assertions.assertEquals(response, crmReportController.getTransitReport(request));
	}
}
