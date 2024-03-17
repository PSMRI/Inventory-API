package com.iemr.inventory.service.indent;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.repo.indent.IndentIssueRepo;
import com.iemr.inventory.repo.indent.IndentOrderRepo;
import com.iemr.inventory.repo.indent.IndentRepo;
import com.iemr.inventory.repo.indent.ItemfacilitymappingIndentRepo;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockExit.ItemStockExitRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@ExtendWith(MockitoExtension.class)
class IndentServiceImplTest {

	@InjectMocks
	IndentServiceImpl indentServiceImpl;

	@Mock
	private EntityManager mockEntityManager;

	@InjectMocks
	private IndentServiceImpl indentServiceImplUnderTest;

	@Mock
	EntityManager entityManager;

	@Mock
	ItemfacilitymappingIndentRepo itemfacilitymappingIndentRepo;

	@Mock
	IndentOrderRepo indentOrderRepo;

	@Mock
	IndentRepo indentRepo;

	@Mock
	IndentIssueRepo indentIssueRepo;

	@Mock
	ItemStockExitRepo itemStockExitRepo;

	@Mock
	ItemStockEntryRepo itemStockEntryRepo;

	@Mock
	private CriteriaBuilder criteriaBuilder;

	@Mock
	private CriteriaQuery<Indent> criteriaQuery;

	@Mock
	private Root<Indent> root;

	@Captor
	private ArgumentCaptor<Predicate[]> predicateCaptor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindItemIndent() {
		// Mock data
		List<Object[]> mockData = new ArrayList<>();
		mockData.add(new Object[] { 1, "Code1", "Item1", true, "Strength1", "UOM1", "Category1", "Form1",
				"PharmaCategory1", "Composition1", 123, new BigDecimal(10) });
		mockData.add(new Object[] { 2, "Code2", "Item2", true, "Strength2", "UOM2", "Category2", "Form2",
				"PharmaCategory2", "Composition2", 456, new BigDecimal(20) });

		when(itemfacilitymappingIndentRepo.findindentitem(anyInt(), anyString())).thenReturn(mockData);

		// Test method invocation
		List<ItemfacilitymappingIndent> result = indentServiceImpl.findItemIndent(1, "Item1");

		// Assertions
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Code1", result.get(0).getItemCode());
		assertEquals("Item1", result.get(0).getItemName());
		assertEquals(true, result.get(0).getIsMedical());
		assertEquals("Strength1", result.get(0).getStrength());
		assertEquals("UOM1", result.get(0).getUomName());
		assertEquals("Category1", result.get(0).getItemCategory());
		assertEquals("Form1", result.get(0).getItemForm());
		assertEquals("PharmaCategory1", result.get(0).getPharmacologicalCategoryName());
		assertEquals("Composition1", result.get(0).getComposition());
		assertEquals(123, result.get(0).getFacilityID());
		assertEquals(new BigDecimal(10), result.get(0).getQoh());
	}

	@Test
	public void getIndentHistory() {

		Indent indent = new Indent();

		indent.setSyncFacilityID(1);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(1L);
		indent.setFromFacilityID(3);

		indent.toString();

		List<Indent> indentList = new ArrayList<Indent>();

		when(indentOrderRepo.getIndentHistory(indent.getFromFacilityID())).thenReturn(indentList);

		// Assertions
		Assertions.assertEquals(indentList.toString(), indentServiceImpl.getIndentHistory(indent));

	}

	@Test
	public void testCreateIndentRequest_Success() {
		// Define the necessary objects and mock data
		Indent indent = new Indent();

		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setSyncFacilityID(3);
		indent.setFromFacilityName("Medicine");
		indent.setToFacilityID(1);
		indent.setRefNo("fehfgdh");
		indent.setOrderDate(new Timestamp(System.currentTimeMillis()));
		indent.setReason("hjdfgdjhg");
		indent.setUserID(10);
		indent.setProviderServiceMapID(6);
		indent.setStatus("Pending");
		indent.setStatusReason("dhghdhgkghfkjghj");
		indent.setDeleted(false);
		indent.setProcessed("N");
		indent.setCreatedBy("Ak Hossain");
		indent.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		indent.setModifiedBy("Ak Hossain");
		indent.setLastModDate(new Timestamp(System.currentTimeMillis()));
		indent.setVanID(9L);
		indent.setParkingPlaceID(7L);
		indent.setVanSerialNo(3L);

		indent.toString();

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setIndentID(5L);
		indentOrder.setFromFacilityID(3);
		indentOrder.setSyncFacilityID(3);
		indentOrder.setProviderServiceMapID(6);
		indentOrder.setStatus("Pending");
		indentOrder.setDeleted(false);
		indentOrder.setProcessed("N");
		indentOrder.setCreatedBy("Ak Hossain");
		indentOrder.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		indentOrder.setModifiedBy("Ak Hossain");
		indentOrder.setLastModDate(new Timestamp(System.currentTimeMillis()));
		indentOrder.setVanID(9L);
		indentOrder.setParkingPlaceID(7L);
		indentOrder.setVanSerialNo(3L);

		indentOrder.toString();

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();
		indentOrderList.add(indentOrder);

		indent.setIndentOrder(indentOrderList);

		Indent indentCreated = new Indent();

		// Mock the dependencies' behaviors
		when(indentRepo.save(indent)).thenReturn(indentCreated);
		when(indentRepo.updateVanSerialNo(indentCreated.getIndentID(), indentCreated.getFromFacilityID()))
				.thenReturn(3);
		when(indentOrderRepo.saveAll(indentOrderList)).thenReturn(indentOrderList);

		// Perform the test
		String result = indentServiceImpl.createIndentRequest(indent);

		// Verify the results
		verify(indentRepo, times(1)).save(indent);
		verify(indentRepo, times(1)).updateVanSerialNo(indentCreated.getIndentID(), indentCreated.getFromFacilityID());
		verify(indentOrderRepo, times(1)).saveAll(indentOrderList);
		assertNotNull(result);
	}

	@Test
	public void getOrdersByIndentID() {

		Indent indent = new Indent();

		indent.setSyncFacilityID(1);

		indent.toString();

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

		indentOrder.toString();

		List<IndentOrder> indentOrderList = new ArrayList<IndentOrder>();

		indentOrderList.add(indentOrder);

		when(indentRepo.findByIndentID(indentOrder.getIndentID())).thenReturn(indent);

		when(indentOrderRepo.getOrdersByIndentID(indent.getVanSerialNo(), indent.getSyncFacilityID()))
				.thenReturn(indentOrderList);

		// Assertions
		Assertions.assertEquals(indentOrderList.toString(), indentServiceImpl.getOrdersByIndentID(indentOrder));

	}

	@Test
	public void getIndentOrderWorklistTest() {

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
		indentOrder.setVanSerialNo(5L);

		indentOrder.toString();

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(1L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);

		indent.toString();

		List<IndentOrder> list1 = new ArrayList<IndentOrder>();

		list1.add(indentOrder);

		when(indentRepo.findByIndentID(indentOrder.getIndentID())).thenReturn(indent);

		when(indentOrderRepo.getOrdersByIndentID(indent.getVanSerialNo(), indent.getSyncFacilityID()))
				.thenReturn(list1);

		// Assertions
		Assertions.assertEquals(list1.toString(), indentServiceImpl.getIndentOrderWorklist(indentOrder));

	}

	@Test
	public void issueIndentDispenseTest() {

		IndentIssue indentIssue = new IndentIssue();

		indentIssue.setAction("Issued");
		indentIssue.setIndentIssueID(3L);
		indentIssue.setIndentOrderID(5L);
		indentIssue.setIndentID(5L);
		indentIssue.setItemID(3);
		indentIssue.setItemName("ARBAAZ");
		indentIssue.setIssuedQty(100);
		indentIssue.setIssueDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setRemarks("Excellent");
		indentIssue.setVanID(9L);
		indentIssue.setProviderServiceMapID(6);
		indentIssue.setCreatedBy("Ak Hossain");
		indentIssue.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setParkingPlaceID(7L);
		indentIssue.setStatus("Pending");
		indentIssue.setDeleted(false);
		indentIssue.setProcessed("N");
		indentIssue.setFromFacilityID(3);
		indentIssue.setVanSerialNo(5L);
		indentIssue.setModifiedBy("Ak Hossain");
		indentIssue.setLastModDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		indentIssue.setItemStockEntryID(5L);
		indentIssue.setBatchNo("B73673Tach");
		indentIssue.setToFacilityID(6);
		indentIssue.setUnitCostPrice(Double.valueOf("10000"));
		indentIssue.setSyncFacilityID(3);
		indentIssue.setIsManual(false);
		indentIssue.setRejectedReason("No Reason");

		indentIssue.toString();

		List<IndentIssue> list = new ArrayList<IndentIssue>();

		list.add(indentIssue);

		IndentIssue[] array = new IndentIssue[list.size()];
		array = list.toArray(array);

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);

		indent.toString();

		when(indentRepo.findByIndentID(list.get(0).getIndentID())).thenReturn(indent);

		String response = indentServiceImpl.issueIndent(array);

		assertTrue(response.contains("Dispensed successfully"));

	}

	@Test
	public void issueIndentRejectedTest() {

		IndentIssue indentIssue = new IndentIssue();

		indentIssue.setAction("Rejected");
		indentIssue.setIndentIssueID(3L);
		indentIssue.setIndentOrderID(5L);
		indentIssue.setIndentID(5L);
		indentIssue.setItemID(3);
		indentIssue.setItemName("ARBAAZ");
		indentIssue.setIssuedQty(100);
		indentIssue.setIssueDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setRemarks("Excellent");
		indentIssue.setVanID(9L);
		indentIssue.setProviderServiceMapID(6);
		indentIssue.setCreatedBy("Ak Hossain");
		indentIssue.setCreatedDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indentIssue.setParkingPlaceID(7L);
		indentIssue.setStatus("Pending");
		indentIssue.setDeleted(false);
		indentIssue.setProcessed("N");
		indentIssue.setFromFacilityID(3);
		indentIssue.setVanSerialNo(5L);
		indentIssue.setModifiedBy("Ak Hossain");
		indentIssue.setLastModDate(Timestamp.valueOf("2018-09-02 09:03:16"));
		indentIssue.setItemStockEntryID(5L);
		indentIssue.setBatchNo("B73673Tach");
		indentIssue.setToFacilityID(6);
		indentIssue.setUnitCostPrice(Double.valueOf("10000"));
		indentIssue.setSyncFacilityID(3);
		indentIssue.setIsManual(false);
		indentIssue.setRejectedReason("No Reason");

		indentIssue.toString();

		List<IndentIssue> list = new ArrayList<IndentIssue>();

		list.add(indentIssue);

		IndentIssue[] array = new IndentIssue[list.size()];
		array = list.toArray(array);

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);

		indent.toString();

		when(indentRepo.findByIndentID(list.get(0).getIndentID())).thenReturn(indent);

		String response = indentServiceImpl.issueIndent(array);

		assertTrue(response.contains("Rejected successfully"));

	}

	@Test
	public void cancelIndentOrderTest() {

		Indent indent1 = new Indent();

		indent1.setSyncFacilityID(3);
		indent1.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent1.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent1.setStatus("Pending");
		indent1.setProcessed("N");
		indent1.setIndentID(5L);
		indent1.setFromFacilityID(3);
		indent1.setVanSerialNo(5L);

		indent1.toString();

		Indent indent = indent1;

		when(indentRepo.findByIndentID(indent1.getIndentID())).thenReturn(indent);

		indentOrderRepo.cancelIndent(indent.getIndentID());
		indentOrderRepo.cancelIndentOrder(indent.getVanSerialNo(), indent.getSyncFacilityID());

		// Assertions
		Assertions.assertEquals("Cancelled successfully", indentServiceImpl.cancelIndentOrder(indent1));

	}

	@Test
	public void testGetIndentWorklist_Success() {
		// Define the necessary objects and mock data
		IndentOrder indentOrder = new IndentOrder();
		indentOrder.setFacilityID(1);
		indentOrder.setStartDateTime(Timestamp.valueOf("2022-01-01 10:09:56"));
		indentOrder.setEndDateTime(Timestamp.valueOf("2022-01-01 10:09:56"));
		indentOrder.setIndentFromID(2);
		CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);
		CriteriaQuery<Indent> criteriaQuery = Mockito.mock(CriteriaQuery.class);
		Root<Indent> root = Mockito.mock(Root.class);
		Join<Object, Object> facilityRoot = Mockito.mock(Join.class);
		Predicate predicate1 = Mockito.mock(Predicate.class);
		Predicate predicate2 = Mockito.mock(Predicate.class);
		Predicate predicate3 = Mockito.mock(Predicate.class);
		Predicate predicate4 = Mockito.mock(Predicate.class);
		List<Predicate> predicates = Arrays.asList(predicate1, predicate2, predicate3, predicate4);

		// Mock the dependencies' behaviors
		when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
		when(criteriaBuilder.createQuery(Indent.class)).thenReturn(criteriaQuery);
		when(criteriaQuery.from(Indent.class)).thenReturn(root);
		when(root.join("m_Facility", JoinType.INNER)).thenReturn(facilityRoot);
		when(criteriaBuilder.equal(root.get("status"), "Pending")).thenReturn(predicate1);
		when(criteriaBuilder.equal(facilityRoot.get("mainFacilityID"), indentOrder.getFacilityID()))
				.thenReturn(predicate2);
		when(criteriaBuilder.between(root.get("createdDate"), indentOrder.getStartDateTime(),
				indentOrder.getEndDateTime())).thenReturn(predicate3);
		when(criteriaBuilder.isNotNull(root.get("fromFacilityID"))).thenReturn(predicate4);
		when(criteriaBuilder.equal(root.get("fromFacilityID"), indentOrder.getIndentFromID())).thenReturn(predicate4);

		// Perform the test
		String result = indentServiceImpl.getIndentWorklist(indentOrder);

		// Verify the results
		verify(itemStockEntryRepo, times(1)).updateItemStockEntryVanSerialNo();
		assertNotNull(result);
	}

	@Test
	public void testReceiveIndent_Success() {
		// Define the necessary objects and mock data
		Indent indent = new Indent();
		indent.setIndentID(1L);
		indent.setFromFacilityID(2);
		Indent idn = new Indent();
		idn.setVanSerialNo(123L);
		idn.setSyncFacilityID(3);
		IndentIssue indentIssue = new IndentIssue();
		indentIssue.setItemID(1);
		indentIssue.setIssuedQty(10);
		indentIssue.setUnitCostPrice(Double.valueOf(100));
		indentIssue.setBatchNo("Batch123");
		indentIssue.setExpiryDate(Date.valueOf("2022-12-31"));
		indentIssue.setIndentID(1L);

		indentIssue.toString();

		List<IndentIssue> issuedList = Arrays.asList(indentIssue);
		ItemStockEntry stockEntry = new ItemStockEntry();
		stockEntry.setFacilityID(2);
		stockEntry.setItemID(1);
		stockEntry.setQuantity(10);
		stockEntry.setQuantityInHand(10);
		stockEntry.setTotalCostPrice(Double.valueOf(100));
		stockEntry.setBatchNo("Batch123");
		stockEntry.setExpiryDate(Date.valueOf("2022-12-31"));
		stockEntry.setEntryTypeID(1L);
		stockEntry.setEntryType("Indent");
		stockEntry.setCreatedBy("User");
		stockEntry.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		stockEntry.setSyncFacilityID(3);
		List<ItemStockEntry> itemStockList = Arrays.asList(stockEntry);

		// Mock the dependencies' behaviors
		when(indentRepo.findByIndentID(indent.getIndentID())).thenReturn(idn);
		when(indentOrderRepo.getIndentIssued(idn.getVanSerialNo(), idn.getToFacilityID())).thenReturn(issuedList);

		// Perform the test
		String result = indentServiceImpl.receiveIndent(indent);

		// Verify the results
		verify(indentOrderRepo).acceptIndent(indent.getIndentID(), indent.getFromFacilityID());

	}

	@Test
	public void updateIndentOrderTest() {

		Indent indent = new Indent();

		indent.setSyncFacilityID(3);
		indent.setOrderDate(Timestamp.valueOf("2018-09-01 09:01:16"));
		indent.setCreatedDate(Timestamp.valueOf("2018-10-02 09:01:16"));
		indent.setStatus("Pending");
		indent.setProcessed("N");
		indent.setIndentID(5L);
		indent.setFromFacilityID(3);
		indent.setVanSerialNo(5L);
		indent.setCreatedBy("AP Malhotra");
		indent.setVanID(1L);
		indent.setProviderServiceMapID(2);
		indent.setCreatedBy("AP Malhotra");

		indent.toString();

		Indent indentCreated = indent;
		when(indentRepo.save(indent)).thenReturn(indentCreated);

		IndentOrder indentOrder = new IndentOrder();

		indentOrder.setIndentID(indentCreated.getIndentID());
		indentOrder.setVanID(indentCreated.getVanID());
		indentOrder.setProviderServiceMapID(indentCreated.getProviderServiceMapID());
		indentOrder.setCreatedBy(indentCreated.getCreatedBy());
		indentOrder.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		indentOrder.setParkingPlaceID(indentCreated.getParkingPlaceID());
		indentOrder.setStatus("Pending");
		indentOrder.setProcessed("N");
		indentOrder.setFromFacilityID(indentCreated.getFromFacilityID());
		indentOrder.setSyncFacilityID(indentCreated.getSyncFacilityID());

		indentOrder.toString();

		List<IndentOrder> indentOrderL = new ArrayList<IndentOrder>();

		indentOrderL.add(indentOrder);

		indentCreated.setIndentOrder(indentOrderL);

		Assertions.assertNotNull(indentOrder);
		Assertions.assertNotNull(indentCreated);
		Assertions.assertNotNull(indentCreated.getSyncFacilityID());
		Assertions.assertEquals("Updated successfully", indentServiceImpl.updateIndentOrder(indent));

	}

}
