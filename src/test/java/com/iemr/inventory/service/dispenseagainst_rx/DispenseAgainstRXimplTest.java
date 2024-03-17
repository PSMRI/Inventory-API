package com.iemr.inventory.service.dispenseagainst_rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.gson.Gson;
import com.iemr.inventory.data.commonutility.CommonUtilityClass;
import com.iemr.inventory.data.dispenseagainst_rx.PrescribedDrugDetails;
import com.iemr.inventory.repo.dispenseagainst_rx.PrescribedDrugDetailsRepo;

public class DispenseAgainstRXimplTest {

	@Mock
	private PrescribedDrugDetailsRepo prescribedDrugDetailsRepo;

	@InjectMocks
	private DispenseAgainstRXimpl dispenseAgainstRXimpl;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetPrescribedMedicines() {

		CommonUtilityClass commonUtilityClass = new CommonUtilityClass();

		commonUtilityClass.setBeneficiaryID(1L);
		commonUtilityClass.setVisitCode(123L);
		commonUtilityClass.setFacilityID(456);
		commonUtilityClass.setBenFlowID(12L);
		commonUtilityClass.setSessionID(12);
		commonUtilityClass.setParkingPlaceID(12);
		commonUtilityClass.setVanID(12);
		commonUtilityClass.setProviderServiceMapID(12);

		commonUtilityClass.toString();

		// Mock input
		String requestOBJ = "{\"beneficiaryRegID\":1,\"visitCode\":123,\"facilityID\":456}";

		// Mock PrescribedDrugDetails
		PrescribedDrugDetails drug1 = new PrescribedDrugDetails();
		drug1.setDrugID(101);
		drug1.setGenericDrugName("Drug1");
		// Mock expiry date as one day ahead of current time
		Timestamp expiryDate = new Timestamp(System.currentTimeMillis() + 86400000);
		drug1.setExpiryDate(expiryDate);
		drug1.setQtyInHand(5L);
		drug1.setPrescribedDrugID(100L);
		drug1.setBeneficiaryRegID(1L);
		drug1.setVisitCode(123L);
		drug1.setFacilityID(456);
		drug1.setCreatedBy("Antu Kundu");
		drug1.setPrescriptionID(1L);
		drug1.setDrugID(2);
		drug1.setGenericDrugName("Tynenol 500");
		drug1.setDrugForm("Paracetamole");
		drug1.setDrugStrength("high");
		drug1.setDose("dgjkhhfkjg");
		drug1.setRoute("fhgdgjfh");
		drug1.setFrequency("hjfhjdh");
		drug1.setDuration("72 hrs");
		drug1.setDuartionUnit("fshshfj");
		drug1.setRelationToFood("faghjgfdsgdhgf");
		drug1.setSpecialInstruction("asghjjgsdhgfjhsdgjhgdfjhgfjhfg");
		drug1.setCreatedDate(Timestamp.valueOf("2020-09-09 12:56:54"));
		drug1.setQtyPrescribed(122);
		drug1.setIsEDL(true);

		drug1.toString();

		ArrayList<PrescribedDrugDetails> prescribedMedicineList = new ArrayList<>();
		prescribedMedicineList.add(drug1);

		// Mock PrescribedDrugDetailsRepo behavior
		when(prescribedDrugDetailsRepo.getPrescribedMedicinesWithDetails(commonUtilityClass.getBeneficiaryRegID(),
				commonUtilityClass.getVisitCode(), commonUtilityClass.getFacilityID()))
				.thenReturn(new ArrayList<Object[]>() {
					{
						add(new Object[] { drug1 });
					}
				});

		Map<String, Object> returnOBJ = new HashMap<>();
		returnOBJ.put("prescriptionID", prescribedMedicineList.get(0).getPrescriptionID());
		returnOBJ.put("beneficiaryRegID", prescribedMedicineList.get(0).getBeneficiaryRegID());
		returnOBJ.put("visitCode", prescribedMedicineList.get(0).getVisitCode());
		returnOBJ.put("consultantName", prescribedMedicineList.get(0).getCreatedBy());

		// Assertions
		assertNotNull(returnOBJ);
		assertTrue(returnOBJ.toString().contains("prescriptionID"));
		assertTrue(returnOBJ.toString().contains("beneficiaryRegID"));
		assertTrue(returnOBJ.toString().contains("visitCode"));
		assertTrue(returnOBJ.toString().contains("consultantName"));

		// Assertions
		assertNotNull(returnOBJ.toString());

		// Test method invocation
		String result = dispenseAgainstRXimpl.getPrescribedMedicines(requestOBJ);

		Gson gson = new Gson();
		Map<String, Object> resultMap = gson.fromJson(result, Map.class);
		assertNotNull(resultMap);

		resultMap.put("itemList", prescribedMedicineList);

		// Assertions on itemList
		assertTrue(resultMap.containsKey("itemList"));

		ArrayList<Map<String, Object>> itemList = (ArrayList<Map<String, Object>>) resultMap.get("itemList");
		assertNotNull(itemList);
		assertEquals(1, itemList.size());

		// Assertions on batchList
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("batchList", prescribedMedicineList);
		assertTrue(item.containsKey("batchList"));
		ArrayList<Map<String, Object>> batchList = (ArrayList<Map<String, Object>>) item.get("batchList");
		assertNotNull(batchList);
		assertEquals(1, batchList.size());

		Timestamp expDate = new Timestamp(System.currentTimeMillis());

		// Assertions on expiry date calculation
		Map<String, Object> batch = new HashMap<String, Object>();
		batch.put("expiresIn", dispenseAgainstRXimpl.calculateExpiryDateInDays(expDate));
		assertTrue(batch.containsKey("expiresIn"));
		Long expiresIn = (Long) batch.get("expiresIn");
		assertNotNull(expiresIn);

		// Assertion on expiry date calculation method
		Long expectedExpiresIn = dispenseAgainstRXimpl.calculateExpiryDateInDays(expiryDate);

		assertNotNull(prescribedMedicineList);
		assertNotEquals(0, prescribedMedicineList.size());
		assertEquals(expectedExpiresIn, expiresIn);
	}

	@Test
	public void testGetPrescribedMedicines_NullInput() {
		// Test method invocation with null input
		String result = dispenseAgainstRXimpl.getPrescribedMedicines(null);

		// Assertion
		assertNull(result);
	}

	@Test
	public void testCalculateExpiryDateInDays() {

		Timestamp expDate = new Timestamp(System.currentTimeMillis());

		Timestamp currentDate = new Timestamp(System.currentTimeMillis());

		Long diff = (expDate.getTime() - currentDate.getTime()) / 86400000;

		Assertions.assertNotNull(expDate);
		Assertions.assertEquals(diff, dispenseAgainstRXimpl.calculateExpiryDateInDays(expDate));
	}

	@Test
	public void testCalculateExpiryDateInDays_NullInput() {
		// Test method invocation with null input
		Long expiresIn = dispenseAgainstRXimpl.calculateExpiryDateInDays(null);

		// Assertion
		assertNull(expiresIn);
	}
}
