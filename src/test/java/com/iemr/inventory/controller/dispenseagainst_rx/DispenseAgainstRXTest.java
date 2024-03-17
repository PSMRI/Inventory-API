package com.iemr.inventory.controller.dispenseagainst_rx;

import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.commonutility.CommonUtilityClass;
import com.iemr.inventory.data.dispenseagainst_rx.PrescribedDrugDetails;
import com.iemr.inventory.service.dispenseagainst_rx.DispenseAgainstRXimpl;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class DispenseAgainstRXTest {

	@Mock
	private DispenseAgainstRXimpl dispenseAgainstRXimpl;

	@InjectMocks
	private DispenseAgainstRX dispenseAgainstRXController;

	@Test
	public void getPrescribedMedicines() {
		OutputResponse response = new OutputResponse();

		CommonUtilityClass commonUtilityClass = new CommonUtilityClass();

		commonUtilityClass.setBenFlowID(1L);
		commonUtilityClass.setBeneficiaryRegID(2L);
		commonUtilityClass.setSessionID(3);
		commonUtilityClass.setParkingPlaceID(4);
		commonUtilityClass.setVanID(5);
		commonUtilityClass.setProviderServiceMapID(7);
		commonUtilityClass.setBenVisitID(8L);
		commonUtilityClass.setVisitCode(8L);
		commonUtilityClass.setFacilityID(9);

		PrescribedDrugDetails prescribedDrugDetails = new PrescribedDrugDetails();

		prescribedDrugDetails.setBeneficiaryRegID(2L);
		prescribedDrugDetails.setVisitCode(8L);
		prescribedDrugDetails.setPrescriptionID(2L);
		prescribedDrugDetails.setDrugID(3);
		prescribedDrugDetails.setGenericDrugName("Pantoprazole");
		prescribedDrugDetails.setDrugForm("Gas type");
		prescribedDrugDetails.setDrugStrength("Medium");
		prescribedDrugDetails.setDose("40 Mg");
		prescribedDrugDetails.setRoute("Route 1");
		prescribedDrugDetails.setFrequency("2 Times");
		prescribedDrugDetails.setDuration("30 days");
		prescribedDrugDetails.setDuartionUnit("1 month");
		prescribedDrugDetails.setRelationToFood("Before Breakfast and Dinner");
		prescribedDrugDetails.setSpecialInstruction("NA");
		prescribedDrugDetails.setCreatedBy("NSC");
		prescribedDrugDetails.setQtyPrescribed(30);
		prescribedDrugDetails.setIsEDL(true);
		prescribedDrugDetails.setItemStockEntryID(100);
		prescribedDrugDetails.setBatchNo("B2");
		prescribedDrugDetails.setQtyInHand(200L);
		prescribedDrugDetails.setFacilityID(commonUtilityClass.getFacilityID());
		prescribedDrugDetails.setItemStockEntryID(3);
		prescribedDrugDetails.setExpiryDate(new Timestamp(System.currentTimeMillis()));
		prescribedDrugDetails.setFacilityID(9);

		ArrayList<PrescribedDrugDetails> prescribedMedicineList = new ArrayList<PrescribedDrugDetails>();
		prescribedMedicineList.add(prescribedDrugDetails);

		Object[] objArr = { commonUtilityClass.getBeneficiaryRegID(), commonUtilityClass.getVisitCode(),
				commonUtilityClass.getFacilityID() };
		ArrayList<Object[]> prescribedDrugDetailsListRS = new ArrayList<Object[]>();
		prescribedDrugDetailsListRS.add(objArr);

		PrescribedDrugDetails obj = prescribedDrugDetails;

		Map<String, Object> returnOBJ = new HashMap<>();
		returnOBJ.put("prescriptionID", prescribedMedicineList.get(0).getPrescriptionID());
		returnOBJ.put("beneficiaryRegID", prescribedMedicineList.get(0).getBeneficiaryRegID());
		returnOBJ.put("facilityID", obj.getFacilityID());
		returnOBJ.put("visitCode", prescribedMedicineList.get(0).getVisitCode());
		returnOBJ.put("consultantName", prescribedMedicineList.get(0).getCreatedBy());
		returnOBJ.put("drugID", obj.getDrugID());
		returnOBJ.put("genericDrugName", obj.getGenericDrugName());
		returnOBJ.put("drugForm", obj.getDrugForm());
		returnOBJ.put("drugStrength", obj.getDrugStrength());
		returnOBJ.put("dose", obj.getDose());
		returnOBJ.put("route", obj.getRoute());
		returnOBJ.put("frequency", obj.getFrequency());
		returnOBJ.put("duration", obj.getDuration());
		returnOBJ.put("durationUnit", obj.getDuartionUnit());
		returnOBJ.put("specialInstruction", obj.getSpecialInstruction());
		returnOBJ.put("qtyPrescribed", obj.getQtyPrescribed());
		returnOBJ.put("isEDL", obj.getIsEDL());
		returnOBJ.put("batchNo", obj.getBatchNo());
		returnOBJ.put("itemStockEntryID", obj.getItemStockEntryID());
		returnOBJ.put("qty", obj.getQtyInHand());
		returnOBJ.put("expiryDate", obj.getExpiryDate());
		returnOBJ.put("createdDate", obj.getCreatedDate());
		returnOBJ.put("createdBy", obj.getCreatedBy());
		returnOBJ.put("benVisitID", obj.getBenVisitID());
		returnOBJ.put("batchNo", obj.getBatchNo());
		returnOBJ.put("itemStockEntryID", obj.getItemStockEntryID());
		returnOBJ.put("qty", obj.getQtyInHand());
		returnOBJ.put("expiryDate", obj.getExpiryDate());

		String s = returnOBJ.toString();

		String requestOBJ = s;

		when(dispenseAgainstRXimpl.getPrescribedMedicines(requestOBJ)).thenReturn(s);
		response.setResponse(s);

		Assertions.assertNotNull(requestOBJ);
		Assertions.assertNotNull(s);
		Assertions.assertEquals(response.toString(),
				dispenseAgainstRXController.getPrescribedMedicinesToDispense(requestOBJ));

	}

	@Test
	public void getPrescribedMedicinesAsNull() {

		OutputResponse response = new OutputResponse();

		String requestOBJ = null;

		response.setError(5000, "Invalid request");

		Assertions.assertEquals(response.toString(),
				dispenseAgainstRXController.getPrescribedMedicinesToDispense(requestOBJ));

	}

	@Test
	public void getPrescribedMedicinesException() {
		
		String requestOBJ="{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		when(dispenseAgainstRXimpl.getPrescribedMedicines(requestOBJ)).thenThrow(NotFoundException.class);
		
		String response = dispenseAgainstRXController.getPrescribedMedicinesToDispense(requestOBJ);
		
		Assertions.assertEquals(response, dispenseAgainstRXController.getPrescribedMedicinesToDispense(requestOBJ));
	}
}