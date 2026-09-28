/*
* AMRIT - Accessible Medical Records via Integrated Technologies
* Integrated EHR (Electronic Health Records) Solution
*
* Copyright (C) "Piramal Swasthya Management and Research Institute"
*
* This file is part of AMRIT.
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see https://www.gnu.org/licenses/.
*/
package com.iemr.inventory.service.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.repo.indent.IndentIssueRepo;
import com.iemr.inventory.repo.indent.IndentOrderRepo;
import com.iemr.inventory.repo.indent.IndentRepo;
import com.iemr.inventory.repo.indent.ItemfacilitymappingIndentRepo;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockExit.ItemStockExitRepo;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("IndentServiceImpl Test Suite")
class IndentServiceImplTest {

	@Mock
	private ItemfacilitymappingIndentRepo itemfacilitymappingIndentRepo;
	@Mock
	private IndentOrderRepo indentOrderRepo;
	@Mock
	private IndentRepo indentRepo;
	@Mock
	private IndentIssueRepo indentIssueRepo;
	@Mock
	private ItemStockExitRepo itemStockExitRepo;
	@Mock
	private ItemStockEntryRepo itemStockEntryRepo;

	@Mock(answer = org.mockito.Answers.RETURNS_DEEP_STUBS)
	private jakarta.persistence.EntityManager entityManager;

	@InjectMocks
	private IndentServiceImpl service;

	private static ArrayList<Object[]> rows(Object[]... values) {
		ArrayList<Object[]> list = new ArrayList<>();
		for (Object[] value : values) {
			list.add(value);
		}
		return list;
	}

	private static Indent indent(Long indentID) {
		Indent indent = new Indent();
		indent.setIndentID(indentID);
		indent.setFromFacilityID(1);
		indent.setToFacilityID(2);
		indent.setVanID(4L);
		indent.setParkingPlaceID(2L);
		indent.setProviderServiceMapID(3);
		indent.setCreatedBy("tester");
		indent.setVanSerialNo(55L);
		indent.setSyncFacilityID(1);
		indent.setIndentOrder(new ArrayList<>());
		return indent;
	}

	private static IndentOrder order(Long orderID) {
		IndentOrder order = new IndentOrder();
		order.setIndentOrderID(orderID);
		order.setItemID(11L);
		order.setRequiredQty(20L);
		return order;
	}

	private static IndentIssue issue(String action, Integer issuedQty) {
		IndentIssue issue = new IndentIssue();
		issue.setIndentID(88L);
		issue.setIndentIssueID(9L);
		issue.setItemID(11);
		issue.setIssuedQty(issuedQty);
		issue.setItemStockEntryID(601L);
		issue.setFromFacilityID(1);
		issue.setToFacilityID(2);
		issue.setCreatedBy("tester");
		issue.setVanID(4L);
		issue.setParkingPlaceID(2L);
		issue.setUnitCostPrice(2.5d);
		issue.setBatchNo("B-1");
		issue.setExpiryDate(Date.valueOf("2026-01-31"));
		issue.setAction(action);
		issue.setRejectedReason("out of stock");
		return issue;
	}

	@Test
	@DisplayName("findItemIndent should build an indentable item out of each result row")
	void findItemIndent_shouldBuildItemPerRow() {
		when(itemfacilitymappingIndentRepo.findindentitem(1, "Para")).thenReturn(rows(new Object[] {
				11, "ITM-11", "Paracetamol", Boolean.TRUE, "500mg", "Tablet", "Analgesic", "Tablet",
				"NSAID", "paracetamol", 1, BigDecimal.valueOf(40) }));

		List<ItemfacilitymappingIndent> result = service.findItemIndent(1, "Para");

		assertEquals(1, result.size());
		ItemfacilitymappingIndent item = result.get(0);
		assertEquals(11, item.getItemID());
		assertEquals("ITM-11", item.getItemCode());
		assertEquals("Paracetamol", item.getItemName());
		assertEquals(Boolean.TRUE, item.getIsMedical());
		assertEquals("500mg", item.getStrength());
		assertEquals("Tablet", item.getUomName());
		assertEquals("Analgesic", item.getItemCategory());
		assertEquals("NSAID", item.getPharmacologicalCategoryName());
		assertEquals("paracetamol", item.getComposition());
		assertEquals(1, item.getFacilityID());
		assertEquals(BigDecimal.valueOf(40), item.getQoh());
	}

	@Test
	@DisplayName("findItemIndent should report a zero quantity on hand when the row carries none")
	void findItemIndent_shouldReportZeroQuantityWhenAbsent() {
		when(itemfacilitymappingIndentRepo.findindentitem(1, "Para")).thenReturn(rows(new Object[] {
				11, "ITM-11", "Paracetamol", Boolean.TRUE, "500mg", "Tablet", "Analgesic", "Tablet",
				"NSAID", "paracetamol", 1, null }));

		assertEquals(BigDecimal.ZERO, service.findItemIndent(1, "Para").get(0).getQoh());
	}

	@Test
	@DisplayName("createIndentRequest should open the indent as pending and stamp its order lines")
	void createIndentRequest_shouldOpenPendingIndent() {
		Indent request = indent(null);
		request.getIndentOrder().add(order(null));
		Indent persisted = indent(88L);
		when(indentRepo.save(request)).thenReturn(persisted);
		when(indentOrderRepo.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

		service.createIndentRequest(request);

		assertEquals(1, request.getSyncFacilityID());
		assertEquals("Pending", request.getStatus());
		assertEquals("N", request.getProcessed());
		IndentOrder stamped = request.getIndentOrder().get(0);
		assertEquals(88L, stamped.getIndentID());
		assertEquals(4L, stamped.getVanID());
		assertEquals(3, stamped.getProviderServiceMapID());
		assertEquals("tester", stamped.getCreatedBy());
		assertEquals("Pending", stamped.getStatus());
		assertEquals(1, stamped.getFromFacilityID());
		verify(indentRepo).updateVanSerialNo(88L, 1);
		verify(indentOrderRepo).updateVanSerialNo();
	}

	@Test
	@DisplayName("getIndentHistory should list the indents the requesting facility raised")
	void getIndentHistory_shouldListIndentsOfFacility() {
		when(indentOrderRepo.getIndentHistory(1)).thenReturn(List.of(indent(88L)));

		assertTrue(service.getIndentHistory(indent(88L)).contains("indentID"));
	}

	@Test
	@DisplayName("getOrdersByIndentID should resolve the indent first, then read its order lines by sync keys")
	void getOrdersByIndentID_shouldResolveIndentThenLines() {
		IndentOrder probe = order(null);
		probe.setIndentID(88L);
		when(indentRepo.findByIndentID(88L)).thenReturn(indent(88L));
		when(indentOrderRepo.getOrdersByIndentID(55L, 1)).thenReturn(List.of(order(9L)));

		assertTrue(service.getOrdersByIndentID(probe).contains("indentOrderID"));
		verify(indentOrderRepo).getOrdersByIndentID(55L, 1);
	}

	@Test
	@DisplayName("getIndentOrderWorklist should read the order lines of the resolved indent")
	void getIndentOrderWorklist_shouldReadOrderLines() {
		IndentOrder probe = order(null);
		probe.setIndentID(88L);
		when(indentRepo.findByIndentID(88L)).thenReturn(indent(88L));
		when(indentOrderRepo.getOrdersByIndentID(55L, 1)).thenReturn(List.of(order(9L)));

		assertTrue(service.getIndentOrderWorklist(probe).contains("indentOrderID"));
	}

	/** Points the deep-stubbed EntityManager at a canned result list for the criteria query. */
	private jakarta.persistence.criteria.CriteriaBuilder givenCriteriaQueryReturnsOneIndent() {
		jakarta.persistence.criteria.CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		jakarta.persistence.criteria.CriteriaQuery<Indent> query = builder.createQuery(Indent.class);
		when(entityManager.createQuery(query).getResultList()).thenReturn(List.of(indent(88L)));
		return builder;
	}

	@Test
	@DisplayName("getIndentWorklist should ask only for pending indents raised under the given main facility")
	void getIndentWorklist_shouldAskForPendingIndentsOfMainFacility() {
		IndentOrder probe = order(null);
		probe.setFacilityID(2);
		jakarta.persistence.criteria.CriteriaBuilder builder = givenCriteriaQueryReturnsOneIndent();

		assertTrue(service.getIndentWorklist(probe).contains("indentID"));

		verify(builder).equal(any(), eq("Pending"));
		verify(builder).equal(any(), eq(2));
		verify(builder, never()).between(any(jakarta.persistence.criteria.Expression.class),
				any(java.sql.Timestamp.class), any(java.sql.Timestamp.class));
		verify(builder, never()).isNotNull(any());
	}

	@Test
	@DisplayName("getIndentWorklist should narrow to a date window when both ends are supplied")
	void getIndentWorklist_shouldNarrowToDateWindow() {
		IndentOrder probe = order(null);
		probe.setFacilityID(2);
		probe.setStartDateTime(java.sql.Timestamp.valueOf("2025-01-01 00:00:00"));
		probe.setEndDateTime(java.sql.Timestamp.valueOf("2025-01-31 23:59:00"));
		jakarta.persistence.criteria.CriteriaBuilder builder = givenCriteriaQueryReturnsOneIndent();

		service.getIndentWorklist(probe);

		verify(builder).between(any(jakarta.persistence.criteria.Expression.class),
				eq(probe.getStartDateTime()), eq(probe.getEndDateTime()));
	}

	@Test
	@DisplayName("getIndentWorklist should narrow to one requesting facility when one is supplied")
	void getIndentWorklist_shouldNarrowToRequestingFacility() {
		IndentOrder probe = order(null);
		probe.setFacilityID(2);
		probe.setIndentFromID(1);
		jakarta.persistence.criteria.CriteriaBuilder builder = givenCriteriaQueryReturnsOneIndent();

		service.getIndentWorklist(probe);

		verify(builder).isNotNull(any());
		verify(builder).equal(any(), eq(1));
	}

	@Test
	@DisplayName("issueIndent should book the stock out and record the issue for an issued line")
	void issueIndent_shouldBookStockOutForIssuedLine() {
		when(indentRepo.findByIndentID(88L)).thenReturn(indent(88L));

		assertEquals("Dispensed successfully", service.issueIndent(new IndentIssue[] { issue("Issued", 6) }));

		verify(indentOrderRepo).issueIndent("Issued", 55L, 1, "out of stock");
		verify(indentOrderRepo).issueIndentOrder("Issued", 55L, 1);
		verify(indentOrderRepo).updateQuantityInStock(6, 601L, 1);
		verify(indentIssueRepo).save(any(IndentIssue.class));

		ArgumentCaptor<ItemStockExit> captor = ArgumentCaptor.forClass(ItemStockExit.class);
		verify(itemStockExitRepo).save(captor.capture());
		ItemStockExit exit = captor.getValue();
		assertEquals(601L, exit.getItemStockEntryID());
		assertEquals(1, exit.getSyncFacilityID());
		assertEquals(6, exit.getQuantity());
		assertEquals(88L, exit.getExitTypeID());
		assertEquals("t_indent", exit.getExitType());
		assertEquals("tester", exit.getCreatedBy());
		verify(indentIssueRepo).updateVanSerialNo();
		verify(itemStockExitRepo).updateVanSerialNo();
	}

	@Test
	@DisplayName("issueIndent should record a rejection without touching any stock")
	void issueIndent_shouldRecordRejectionWithoutTouchingStock() {
		when(indentRepo.findByIndentID(88L)).thenReturn(indent(88L));

		assertEquals("Rejected successfully", service.issueIndent(new IndentIssue[] { issue("Rejected", 0) }));

		verify(indentOrderRepo, never()).updateQuantityInStock(anyInt(), anyLong(), anyInt());
		verify(itemStockExitRepo, never()).save(any(ItemStockExit.class));
	}

	@Test
	@DisplayName("cancelIndentOrder should cancel both the indent and its order lines")
	void cancelIndentOrder_shouldCancelIndentAndLines() {
		Indent probe = indent(88L);
		when(indentRepo.findByIndentID(88L)).thenReturn(indent(88L));

		assertEquals("Cancelled successfully", service.cancelIndentOrder(probe));

		verify(indentOrderRepo).cancelIndent(88L);
		verify(indentOrderRepo).cancelIndentOrder(55L, 1);
	}

	@Test
	@DisplayName("receiveIndent should book the issued quantities into the receiving store")
	void receiveIndent_shouldBookIssuedQuantitiesIntoReceivingStore() {
		Indent request = indent(88L);
		when(indentRepo.findByIndentID(88L)).thenReturn(indent(88L));
		when(indentOrderRepo.getIndentIssued(55L, 2)).thenReturn(List.of(issue("Issued", 6)));

		assertEquals("Received successfully", service.receiveIndent(request));

		verify(indentOrderRepo).acceptIndent(88L, 1);
		verify(indentOrderRepo).acceptIndentOrder(55L, 1);

		ArgumentCaptor<List<ItemStockEntry>> captor = ArgumentCaptor.forClass(List.class);
		verify(itemStockEntryRepo).saveAll(captor.capture());
		ItemStockEntry booked = captor.getValue().get(0);
		assertEquals(1, booked.getFacilityID());
		assertEquals(11, booked.getItemID());
		assertEquals(6, booked.getQuantity());
		assertEquals(6, booked.getQuantityInHand());
		assertEquals(2.5d, booked.getTotalCostPrice());
		assertEquals("B-1", booked.getBatchNo());
		assertEquals(88L, booked.getEntryTypeID());
		assertEquals("Indent", booked.getEntryType());
		assertEquals("tester", booked.getCreatedBy());
		verify(itemStockEntryRepo).updateItemStockEntryVanSerialNo();
	}

	@Test
	@DisplayName("receiveIndent should book nothing for a line that was issued in zero quantity")
	void receiveIndent_shouldBookNothingForZeroQuantity() {
		Indent request = indent(88L);
		when(indentRepo.findByIndentID(88L)).thenReturn(indent(88L));
		when(indentOrderRepo.getIndentIssued(55L, 2)).thenReturn(List.of(issue("Issued", 0)));

		service.receiveIndent(request);

		ArgumentCaptor<List<ItemStockEntry>> captor = ArgumentCaptor.forClass(List.class);
		verify(itemStockEntryRepo).saveAll(captor.capture());
		assertTrue(captor.getValue().isEmpty());
	}

	@Test
	@DisplayName("updateIndentOrder should stamp a newly added order line with the indent's details")
	void updateIndentOrder_shouldStampNewOrderLine() {
		Indent request = indent(88L);
		request.getIndentOrder().add(order(null));
		when(indentRepo.save(request)).thenReturn(indent(88L));
		when(indentOrderRepo.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

		assertEquals("Updated successfully", service.updateIndentOrder(request));

		IndentOrder stamped = request.getIndentOrder().get(0);
		assertEquals(88L, stamped.getIndentID());
		assertEquals("Pending", stamped.getStatus());
		assertEquals("N", stamped.getProcessed());
		assertEquals(1, stamped.getSyncFacilityID());
	}

	@Test
	@DisplayName("updateIndentOrder should only refresh the sync facility on an order line that already exists")
	void updateIndentOrder_shouldOnlyRefreshSyncFacilityOnExistingLine() {
		Indent request = indent(88L);
		request.getIndentOrder().add(order(9L));
		when(indentRepo.save(request)).thenReturn(indent(88L));
		when(indentOrderRepo.saveAll(anyList())).thenAnswer(inv -> inv.getArgument(0));

		service.updateIndentOrder(request);

		IndentOrder existing = request.getIndentOrder().get(0);
		assertEquals(1, existing.getSyncFacilityID());
		assertTrue(existing.getStatus() == null, "an existing line keeps whatever status it already carried");
	}
}
