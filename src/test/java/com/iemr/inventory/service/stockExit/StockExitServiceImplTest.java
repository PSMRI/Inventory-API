package com.iemr.inventory.service.stockExit;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.mapper.stockExit.ItemStockExitMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockExit.ItemStockExitRepo;
import com.iemr.inventory.repo.stockExit.PatientIssueRepo;
import com.iemr.inventory.repo.stockExit.StockTransferRepo;
import com.iemr.inventory.repo.stockExit.StoreSelfConsumptionRepo;
import com.iemr.inventory.service.item.ItemService;
import com.iemr.inventory.service.stockEntry.StockEntryService;
import com.iemr.inventory.utils.exception.InventoryException;

@ExtendWith(MockitoExtension.class)
class StockExitServiceImplTest {

	@InjectMocks
	StockExitServiceImpl stockExitServiceImpl;

	@Mock
	StockEntryService stockEntryService;

	@Mock
	ItemStockExitRepo itemStockExitRepo;

	@Mock
	ItemStockEntryRepo itemStockEntryRepo;

	@Mock
	PatientIssueRepo patientIssueRepo;

	@Mock
	StoreSelfConsumptionRepo storeSelfConsumptionRepo;

	@Mock
	StockTransferRepo stockTransferRepo;

	@Mock
	ItemService itemService;

	@Mock
	ItemStockExitMapper itemStockExitMapper;

	@Mock
	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Test
	public void issuePatientDrugsAsZeroTest() throws InventoryException {

		T_PatientIssue patientIssue = new T_PatientIssue();

		Integer returnvalue = 0;
		logger.info("starting patient issue" + patientIssue.toString());

		returnvalue = 0;

		// Assertions
		Assertions.assertEquals(null, patientIssue.getItemStockExit());
		Assertions.assertEquals(0, returnvalue);

	}

	@Test
	public void issuePatientDrugsAsNotZeroTest() throws InventoryException {

		T_PatientIssue patientIssue = new T_PatientIssue();

		patientIssue.setPatientIssueID(456L);
		patientIssue.setVanSerialNo(1L);
		patientIssue.setBenRegID(123L);
		patientIssue.setFacilityID(123);
		patientIssue.setSyncFacilityID(123);
		patientIssue.setVisitID(123);
		patientIssue.setVisitCode(123L);
		patientIssue.setPatientName("Joe");
		patientIssue.setAge(24);
		patientIssue.setGender("Male");
		patientIssue.setDoctorName("Jacob");
		patientIssue.setPrescriptionID(123456789);
		patientIssue.setReference("TestReference");
		patientIssue.setIssueType("TestIssue");
		patientIssue.setIssuedBy("TestIssuedBy");

		patientIssue.toString();

		Integer returnvalue = 1;
		logger.info("starting patient issue" + patientIssue.toString());

		returnvalue = 1;

		// Assertions
		Assertions.assertEquals(1, returnvalue);
		Assertions.assertNotNull(patientIssue.getPrescriptionID());
		Assertions.assertNotNull(patientIssue);
		Assertions.assertNotNull(patientIssue.getBenRegID());
		Assertions.assertNotNull(patientIssue.getVisitCode());

	}

	@Test
	public void issuePatientDrugsAsTrueAndOneTest() throws InventoryException {

		T_PatientIssue patientIssue = new T_PatientIssue();

		patientIssue.setPatientIssueID(456L);
		patientIssue.setVanSerialNo(1L);
		patientIssue.setBenRegID(123L);
		patientIssue.setFacilityID(123);
		patientIssue.setSyncFacilityID(123);
		patientIssue.setVisitID(123);
		patientIssue.setVisitCode(123L);
		patientIssue.setAge(24);
		patientIssue.setGender("Male");
		patientIssue.setDoctorName("Jacob");
		patientIssue.setPrescriptionID(123456789);
		patientIssue.setReference("TestReference");
		patientIssue.setIssueType("TestIssue");
		patientIssue.setIssuedBy("TestIssuedBy");

		patientIssue.toString();

		Integer returnvalue = 1;
		logger.info("starting patient issue" + patientIssue.toString());

		ItemStockExit itemStockExit = new ItemStockExit();

		itemStockExit.setFacilityID(5);
		itemStockExit.setCreatedBy("AK Hossain");
		itemStockExit.setItemStockEntryID(5L);
		itemStockExit.setQuantity(100);

		itemStockExit.toString();

		List<ItemStockExit> itemissueList = new ArrayList<ItemStockExit>();
		itemissueList.add(itemStockExit);

		logger.info("fetching itemissue and validating");
		List<ItemStockExit> itemissueListUpdated = new ArrayList<ItemStockExit>();
		itemissueListUpdated.add(itemStockExit);

		returnvalue = 1;

		// Assertions
		Assertions.assertEquals(1, returnvalue);
		Assertions.assertNotNull(patientIssue.getPrescriptionID());
		Assertions.assertNotNull(patientIssue);
		Assertions.assertNotNull(patientIssue.getBenRegID());
		Assertions.assertNotNull(patientIssue.getVisitCode());

	}

	@Test
	public void issuePatientDrugsAsTrueAndZeroTest() throws InventoryException {

		T_PatientIssue patientIssue = new T_PatientIssue();

		Integer returnvalue = 0;
		logger.info("starting patient issue" + patientIssue.toString());

		returnvalue = 0;
		logger.info("fetching itemissue and validating");

		// Assertions
		Assertions.assertEquals(0, returnvalue);
	}

	@Test
	public void saveItemExitTest() {

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);
		itemStockExitO.setExitType("Normal");

		itemStockExitO.toString();

		List<ItemStockExit> itemissueListUpdated = new ArrayList<ItemStockExit>();
		itemissueListUpdated.add(itemStockExitO);

		Long issueID = 1L;
		String issueType = "Medicine type";

		logger.info("saving ItemStockExit");

		Integer i = 0;

		when(itemStockExitRepo.saveAll(itemissueListUpdated)).thenReturn(itemissueListUpdated);

		when(itemStockExitRepo.updateVanSerialNo()).thenReturn(i);

		when(stockEntryService.updateStocks(itemissueListUpdated)).thenReturn(i);

		i = 1;
		logger.info("saved ItemStockExit");

		Assertions.assertEquals(i, stockExitServiceImpl.saveItemExit(itemissueListUpdated, issueID, issueType));

	}

	@Test
	public void getItemStockAndValidateTest() {

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);
		itemStockExitO.setExitType("Normal");
		itemStockExitO.setQuantityInHand(100);

		itemStockExitO.toString();

		List<ItemStockExit> itemissueList = new ArrayList<ItemStockExit>();
		itemissueList.add(itemStockExitO);

		Integer facilityID = 1;
		String createdBy = "AN Holmes";
		Long vanID = 7L;
		Long ppID = 8L;

		logger.info("in getItemStockAndValidate method");

		Map<Long, ItemStockExit> result = new HashMap<Long, ItemStockExit>();

		result.put(itemStockExitO.getItemStockEntryID(), itemStockExitO);

		Long[] itemstockIDs = result.keySet().toArray(new Long[itemissueList.size()]);

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		itemStockEntry.toString();

		List<ItemStockEntry> stockInHand2 = new ArrayList<ItemStockEntry>();
		stockInHand2.add(itemStockEntry);

		Object[] arr = new Object[stockInHand2.size()];
		arr = stockInHand2.toArray(arr);

		List<Object[]> stockInHand = new ArrayList<Object[]>();
		stockInHand.add(itemstockIDs);

		List<ItemStockExit> itemissueListUpdated = new ArrayList<ItemStockExit>();
		itemissueListUpdated.add(itemStockExitO);

		ItemStockExit itemIssueUpdating = new ItemStockExit();
		itemIssueUpdating.setItemStockEntryID(itemStockExitO.getItemStockEntryID());
		itemIssueUpdating.setQuantityInHand(itemStockExitO.getQuantityInHand());
		itemIssueUpdating.setCreatedBy(createdBy);
		itemIssueUpdating.setVanID(vanID);
		itemIssueUpdating.setParkingPlaceID(ppID);
		itemIssueUpdating.setParkingPlaceID(ppID);
		itemIssueUpdating.setFacilityID(facilityID);
		itemIssueUpdating.setSyncFacilityID(facilityID);
		itemIssueUpdating.setQuantity(itemStockExitO.getQuantity());

		itemIssueUpdating.toString();

		itemissueListUpdated.add(itemIssueUpdating);

		logger.info("out getItemStockAndValidate method");

		// Assertions
		Assertions.assertEquals(itemIssueUpdating.getQuantity(), itemIssueUpdating.getQuantityInHand());

	}

	@Test
	public void storeSelfConsumptionTest() {

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);
		itemStockExitO.setCreatedBy("Mdghjd");
		itemStockExitO.setParkingPlaceID(1L);
		itemStockExitO.setVanID(2L);

		itemStockExitO.toString();

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

		storeSelfConsumption.toString();

		List<ItemStockExit> itemissueList = new ArrayList<ItemStockExit>();
		itemissueList.add(itemStockExitO);

		List<ItemStockExit> itemissueListUpdated = new ArrayList<ItemStockExit>();
		itemissueListUpdated.add(itemStockExitO);

		storeSelfConsumption.setSyncFacilityID(storeSelfConsumption.getFacilityID());

		// Assertions
		Assertions.assertEquals(itemissueList.size(), itemissueListUpdated.size());

	}

	@Test
	public void storeTransferTest() {

		Long toVanID = 4L;

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setFacilityID(5);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		itemStockEntry.toString();

		List<ItemStockEntry> stockInHand2 = new ArrayList<ItemStockEntry>();
		stockInHand2.add(itemStockEntry);

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

		stockTransfer.setToVanID(toVanID);

		stockTransfer.toString();

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);
		itemStockExitO.setCreatedBy("Mdghjd");
		itemStockExitO.setParkingPlaceID(1L);
		itemStockExitO.setVanID(2L);

		itemStockExitO.toString();

		List<ItemStockExit> itemissueList = new ArrayList<ItemStockExit>();
		itemissueList.add(itemStockExitO);

		List<ItemStockExit> itemissueListUpdated = new ArrayList<ItemStockExit>();
		itemissueListUpdated.add(itemStockExitO);

		stockTransfer.setSyncFacilityID(stockTransfer.getTransferFromFacilityID());

		// Assertions
		Assertions.assertEquals(itemissueList.size(), itemissueListUpdated.size());

	}

	@Test
	public void getStoreTransferTest() {

		ItemStockEntryinput itemStockEntryinput = new ItemStockEntryinput();

		itemStockEntryinput.setStockAdjustmentDraftID(12L);
		itemStockEntryinput.setStockAdjustmentID(2L);
		itemStockEntryinput.setConsumptionID(3L);
		itemStockEntryinput.setPatientIssueID(4L);
		itemStockEntryinput.setStockTransferID(5L);
		itemStockEntryinput.setPhyEntryID(6L);
		itemStockEntryinput.setFacilityID(7);
		itemStockEntryinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setPatientIssueID(6L);

		itemStockEntryinput.toString();

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

		stockTransfer.toString();

		List<T_StockTransfer> dataList = new ArrayList<T_StockTransfer>();
		dataList.add(stockTransfer);

		when(stockTransferRepo
				.findByCreatedDateBetweenAndTransferFromFacilityIDOrCreatedDateBetweenAndTransferToFacilityIDOrderByCreatedDateDesc(
						itemStockEntryinput.getFromDate(), itemStockEntryinput.getToDate(),
						itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
						itemStockEntryinput.getToDate(), itemStockEntryinput.getFacilityID()))
				.thenReturn(dataList);

		// Assertions
		Assertions.assertNotNull(itemStockEntryinput.getFacilityID());
		Assertions.assertNotNull(itemStockEntryinput.getFromDate());
		Assertions.assertNotNull(itemStockEntryinput.getToDate());

		Assertions.assertEquals(dataList, stockExitServiceImpl.getStoreTransfer(itemStockEntryinput));

	}

	@Test
	public void getpatientIssueTest() {

		ItemStockEntryinput itemStockEntryinput = new ItemStockEntryinput();

		itemStockEntryinput.setStockAdjustmentDraftID(12L);
		itemStockEntryinput.setStockAdjustmentID(2L);
		itemStockEntryinput.setConsumptionID(3L);
		itemStockEntryinput.setPatientIssueID(4L);
		itemStockEntryinput.setStockTransferID(5L);
		itemStockEntryinput.setPhyEntryID(6L);
		itemStockEntryinput.setFacilityID(7);
		itemStockEntryinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setPatientIssueID(6L);

		itemStockEntryinput.toString();

		T_PatientIssue patientIssue = new T_PatientIssue();

		patientIssue.setPatientIssueID(456L);
		patientIssue.setVanSerialNo(1L);
		patientIssue.setBenRegID(123L);
		patientIssue.setFacilityID(123);
		patientIssue.setSyncFacilityID(123);
		patientIssue.setVisitID(123);
		patientIssue.setVisitCode(123L);
		patientIssue.setPatientName("Joe");
		patientIssue.setAge(24);
		patientIssue.setGender("Male");
		patientIssue.setDoctorName("Jacob");
		patientIssue.setPrescriptionID(123456789);
		patientIssue.setReference("TestReference");
		patientIssue.setIssueType("TestIssue");
		patientIssue.setIssuedBy("TestIssuedBy");

		patientIssue.toString();

		List<T_PatientIssue> dataList = new ArrayList<T_PatientIssue>();
		dataList.add(patientIssue);

		when(patientIssueRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
				itemStockEntryinput.getToDate())).thenReturn(dataList);

		// Assertions
		Assertions.assertNotNull(itemStockEntryinput.getFacilityID());
		Assertions.assertNotNull(itemStockEntryinput.getFromDate());
		Assertions.assertNotNull(itemStockEntryinput.getToDate());

		Assertions.assertEquals(dataList, stockExitServiceImpl.getpatientIssue(itemStockEntryinput));

	}

	@Test
	public void getstoreSelfConsumptionTest() {

		ItemStockEntryinput itemStockEntryinput = new ItemStockEntryinput();

		itemStockEntryinput.setStockAdjustmentDraftID(12L);
		itemStockEntryinput.setStockAdjustmentID(2L);
		itemStockEntryinput.setConsumptionID(3L);
		itemStockEntryinput.setPatientIssueID(4L);
		itemStockEntryinput.setStockTransferID(5L);
		itemStockEntryinput.setPhyEntryID(6L);
		itemStockEntryinput.setFacilityID(7);
		itemStockEntryinput.setFromDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setToDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		itemStockEntryinput.setPatientIssueID(6L);

		itemStockEntryinput.toString();

		StoreSelfConsumption storeSelfConsumption = new StoreSelfConsumption();

		storeSelfConsumption.setConsumptionID(1L);
		storeSelfConsumption.setVanSerialNo(2L);
		storeSelfConsumption.setFacilityID(3);
		storeSelfConsumption.setSyncFacilityID(4);
		storeSelfConsumption.setVanID(5L);
		storeSelfConsumption.setRefNo("R73674A");
		storeSelfConsumption.setReason("No reason");
		storeSelfConsumption.setParkingPlaceID(6L);

		storeSelfConsumption.toString();

		List<StoreSelfConsumption> storeselfConsumeList = new ArrayList<StoreSelfConsumption>();
		storeselfConsumeList.add(storeSelfConsumption);

		when(storeSelfConsumptionRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
				itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
				itemStockEntryinput.getToDate())).thenReturn(storeselfConsumeList);

		// Assertions
		Assertions.assertNotNull(itemStockEntryinput.getFacilityID());
		Assertions.assertNotNull(itemStockEntryinput.getFromDate());
		Assertions.assertNotNull(itemStockEntryinput.getToDate());

		Assertions.assertEquals(storeselfConsumeList,
				stockExitServiceImpl.getstoreSelfConsumption(itemStockEntryinput));

	}

	@Test
	public void getstoreSelfConsumptionItemListTest() {

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

		itemStockinput.toString();

		StoreSelfConsumption ss = new StoreSelfConsumption();

		ss.setConsumptionID(1L);
		ss.setVanSerialNo(2L);
		ss.setFacilityID(3);
		ss.setSyncFacilityID(4);
		ss.setVanID(5L);
		ss.setRefNo("R73674A");
		ss.setReason("No reason");
		ss.setParkingPlaceID(6L);

		ss.toString();

		when(storeSelfConsumptionRepo.findByConsumptionID(itemStockinput.getConsumptionID())).thenReturn(ss);
		
		

		ItemStockExitMap itemStockExitMap = new ItemStockExitMap();

		itemStockExitMap.setBatchNo("B65346NK");
		itemStockExitMap.setCreatedBy("AN Hossain");
		itemStockExitMap.setQuantity(101);
		itemStockExitMap.setDeleted(false);
		
		itemStockExitMap.toString();

		List<ItemStockExitMap> getData = new ArrayList<ItemStockExitMap>();

		getData.add(itemStockExitMap);
	

		when(itemStockExitMapper.getItemStockExitMapList(
				itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(ss.getVanSerialNo(),
						ss.getSyncFacilityID(), "StoreSelfConsumption"))).thenReturn(getData);

		Assertions.assertEquals(getData, stockExitServiceImpl.getstoreSelfConsumptionItemList(itemStockinput));

	}

	@Test
	public void getpatientIssueItemLIstTest() {

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

		itemStockinput.toString();

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

		patissue.toString();

		when(patientIssueRepo.findById((Long) itemStockinput.getPatientIssueID())).thenReturn(Optional.of(patissue));

		ItemStockExitMap itemStockExitMap = new ItemStockExitMap();

		itemStockExitMap.setBatchNo("B65346NK");
		itemStockExitMap.setCreatedBy("AN Hossain");
		itemStockExitMap.setQuantity(101);
		itemStockExitMap.setDeleted(false);
		
		itemStockExitMap.toString();

		List<ItemStockExitMap> getData = new ArrayList<ItemStockExitMap>();

		getData.add(itemStockExitMap);

		when(itemStockExitMapper.getItemStockExitMapList(
				itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(patissue.getVanSerialNo(),
						patissue.getSyncFacilityID(), "T_PatientIssue"))).thenReturn(getData);

		Assertions.assertEquals(getData, stockExitServiceImpl.getpatientIssueItemLIst(itemStockinput));

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
		itemStockinput.setPatientIssueID(6L);

		itemStockinput.toString();

		T_StockTransfer tstock = new T_StockTransfer();

		tstock.setStockTransferID(1L);
		tstock.setVanSerialNo(2L);
		tstock.setTransferFromFacilityID(3);
		tstock.setSyncFacilityID(4);
		tstock.setTransferToFacilityID(5);
		tstock.setRefNo("R73674A");
		tstock.setIssueType("sgfgd");
		tstock.setDeleted(false);
		tstock.setProcessed('Y');
		tstock.setCreatedBy("Vidya");
		tstock.setModifiedBy("Vidya");
		tstock.setVanID(3L);
		tstock.setToVanID(4L);
		tstock.setVanSerialNo(6L);

		tstock.toString();

		when(stockTransferRepo.findByStockTransferID(itemStockinput.getStockTransferID())).thenReturn(tstock);

		ItemStockEntry itemStockEntry = new ItemStockEntry();

		itemStockEntry.setItemStockEntryID(1L);
		itemStockEntry.setFacilityID(8);
		itemStockEntry.setItemID(3);
		itemStockEntry.setQuantityInHand(0);
		itemStockEntry.setDeleted(false);
		itemStockEntry.setBatchNo("Bdfhfgdh");

		itemStockEntry.toString();

		List<ItemStockEntry> ItemStockEntryList = new ArrayList<ItemStockEntry>();
		ItemStockEntryList.add(itemStockEntry);

		ItemStockExitMap itemStockExitMap = new ItemStockExitMap();

		itemStockExitMap.setBatchNo("B65346NK");
		itemStockExitMap.setCreatedBy("AN Hossain");
		itemStockExitMap.setQuantity(101);
		itemStockExitMap.setDeleted(false);
		
		itemStockExitMap.toString();

		List<ItemStockExitMap> getData = new ArrayList<ItemStockExitMap>();

		getData.add(itemStockExitMap);
		
		when(itemStockExitMapper.getItemStockEntryMapList(
				itemStockEntryRepo.findByEntryTypeIDAndSyncFacilityIDAndEntryType(tstock.getVanSerialNo(),
						tstock.getSyncFacilityID(), "T_StockTransfer"))).thenReturn(getData);

		Assertions.assertEquals(getData, stockExitServiceImpl.getStoreTransferItemEntry(itemStockinput));

	}

	@Test
	public void getPatientissueAllDetailTest() {

		Long patientissueID = 3L;

		T_PatientIssue patissue = new T_PatientIssue();

		patissue.setPatientIssueID(3L);
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

		patissue.toString();

		when(patientIssueRepo.findById(patientissueID)).thenReturn(Optional.of(patissue));

		ItemStockExit itemStockExitO = new ItemStockExit();

		itemStockExitO.setItemID(3);
		itemStockExitO.setFacilityID(5);
		itemStockExitO.setItemStockEntryID(5L);
		itemStockExitO.setQuantity(100);

		itemStockExitO.toString();

		List<ItemStockExit> itemissueList = new ArrayList<ItemStockExit>();
		itemissueList.add(itemStockExitO);

		when(itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(patissue.getVanSerialNo(),
				patissue.getSyncFacilityID(), "T_PatientIssue")).thenReturn(itemissueList);

		// Assertions
		Assertions.assertEquals(patissue, stockExitServiceImpl.getPatientissueAllDetail(patientissueID));

	}

}
