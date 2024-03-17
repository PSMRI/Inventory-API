package com.iemr.inventory.controller.visit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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

import com.iemr.inventory.data.visit.BenVisitDetail;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.service.visit.VisitService;
import com.iemr.inventory.service.visit.VisitServiceImpl;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

	@InjectMocks
	VisitController visitController;

	@Mock
	VisitService visitService;
	
	@Mock
	VisitServiceImpl visitServiceImpl;

	@Test
	public void getVisitFromBenRegIDTest() throws Exception {	

		HttpServletRequest httpRequest = mock(HttpServletRequest.class);

		String auth = httpRequest.getHeader("authorization");

		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();

		beneficiaryModel.setBeneficiaryRegID(123L);
		beneficiaryModel.setBeneficiaryID("B763567H");
		beneficiaryModel.setTitleId(Short.valueOf("3"));
		beneficiaryModel.setTitleName("Mr");
		beneficiaryModel.setFirstName("Anasua");
		beneficiaryModel.setMiddleName("Devi");
		beneficiaryModel.setLastName("Rani");
		beneficiaryModel.setStatusID(Short.valueOf("6"));
		beneficiaryModel.setStatusName("Doctor");
		beneficiaryModel.setGenderID(2);
		beneficiaryModel.setGenderName("Female");
		beneficiaryModel.setMaritalStatusID(Short.valueOf("2"));
		beneficiaryModel.setMaritalStatusName("Married");
		beneficiaryModel.setSexualOrientationId(Short.valueOf("1"));
		beneficiaryModel.setSexualOrientationName("Not Virgin");
		beneficiaryModel.setDOB(Timestamp.valueOf("2018-09-01 09:01:16"));
		beneficiaryModel.setAge(36);
		beneficiaryModel.setFatherName("Niran Kumar Ranajay");
		beneficiaryModel.setSpouseName("Baju Kumar Kundu");
		beneficiaryModel.setGovtIdentityNo("G65435P");
		beneficiaryModel.setGovtIdentityTypeID(3);
		beneficiaryModel.setRegisteredServiceID(Short.valueOf("6"));
		beneficiaryModel.setSourceOfInformation("Professional Website");
		beneficiaryModel.setIsHIVPos(false);
		beneficiaryModel.setPlaceOfWork("Pune");
		beneficiaryModel.setRemarks("Excellent Doctor");
		beneficiaryModel.setChangeInSelfDetails(false);
		beneficiaryModel.setChangeInAddress(false);
		beneficiaryModel.setChangeInContacts(false);
		beneficiaryModel.setChangeInFamilyDetails(false);
		beneficiaryModel.setChangeInIdentities(false);
		beneficiaryModel.setChangeInOtherDetails(true);
		beneficiaryModel.setChangeInAssociations(true);
		beneficiaryModel.setDeleted(false);
		beneficiaryModel.setCreatedBy("P Medical Company");
		beneficiaryModel.setCreatedDate(Timestamp.valueOf("2000-08-08 09:01:16"));
		beneficiaryModel.setModifiedBy("C Medical Company");
		beneficiaryModel.setLastModDate(Timestamp.valueOf("2001-09-09 09:01:16"));
		
		beneficiaryModel.toString();

		List<BeneficiaryModel> benModel = new ArrayList<BeneficiaryModel>();

		benModel.add(beneficiaryModel);
		
		BenVisitDetail benVisitDetail = new BenVisitDetail();

		benVisitDetail.setBenVisitID(1L);
		benVisitDetail.setBeneficiaryRegID(2L);
		benVisitDetail.setProviderServiceMapID(3);
		benVisitDetail.setServiceProviderName("MDC");
		benVisitDetail.setVisitDateTime(Timestamp.valueOf("2000-08-08 09:01:16"));
		benVisitDetail.setVisitCode("Visit1");
		benVisitDetail.setVisitNo(Short.valueOf("3"));
		benVisitDetail.setVisitReasonID(Short.valueOf("6"));
		benVisitDetail.setVisitReason("OPD");
		benVisitDetail.setVisitCategoryID(7);
		benVisitDetail.setVisitCategory("Monthly");
		benVisitDetail.setPregnancyStatus("Three months");
		benVisitDetail.setRCHID("P1N");
		benVisitDetail.setHealthFacilityType("Pill");
		benVisitDetail.setHealthFacilityLocation("Kolkata");
		benVisitDetail.setReportFilePath("PC Directory");
		benVisitDetail.setDeleted(false);
		benVisitDetail.setProcessed("Y");
		benVisitDetail.setCreatedBy("P Medical Company");
		benVisitDetail.setCreatedDate(Timestamp.valueOf("2000-08-08 09:01:16"));
		benVisitDetail.setModifiedBy("C Medical Company");
		benVisitDetail.setLastModDate(Timestamp.valueOf("2001-09-09 09:01:16"));
		benVisitDetail.setVisitFlowStatusFlag("ON");
		benVisitDetail.setVanSerialNo(3L);
		benVisitDetail.setVehicalNo("WB1232467");
		benVisitDetail.setParkingPlaceID(12);
		benVisitDetail.setSyncedBy("Sudama");
		benVisitDetail.setSyncedDate(Timestamp.valueOf("2001-09-09 09:01:16"));
		benVisitDetail.setReservedForChange("No");
		
		benVisitDetail.toString();
		
		BenVisitDetail newbenVisitDetail = benVisitDetail;

		newbenVisitDetail.setBeneficiaryID(benVisitDetail.getBeneficiaryID());
		
		BeneficiaryModel saveData=beneficiaryModel;

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());
		
		String resp=visitController.getVisitFromBenRegID(newbenVisitDetail, httpRequest);

		Assertions.assertEquals(resp,
				visitController.getVisitFromBenRegID(newbenVisitDetail, httpRequest));

	}

	@Test
	public void getVisitFromBenRegIDTestEXception() throws Exception {

		HttpServletRequest httpRequest = mock(HttpServletRequest.class);

		String auth = httpRequest.getHeader(
				"{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}");

		String response = visitController.getVisitFromBenRegID(any(), httpRequest);

		Assertions.assertEquals(response, visitController.getVisitFromBenRegID(any(), httpRequest));
	}

	@Test
	public void getVisitFromAdvanceSearchTest() throws IEMRException {

		HttpServletRequest httpRequest = mock(HttpServletRequest.class);

		String auth = httpRequest.getHeader("authorization");

		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();

		beneficiaryModel.setBeneficiaryRegID(123L);
		beneficiaryModel.setBeneficiaryID("B763567H");
		beneficiaryModel.setTitleId(Short.valueOf("3"));
		beneficiaryModel.setTitleName("Mr");
		beneficiaryModel.setFirstName("Anasua");
		beneficiaryModel.setMiddleName("Devi");
		beneficiaryModel.setLastName("Rani");
		beneficiaryModel.setStatusID(Short.valueOf("6"));
		beneficiaryModel.setStatusName("Doctor");
		beneficiaryModel.setGenderID(2);
		beneficiaryModel.setGenderName("Female");
		beneficiaryModel.setMaritalStatusID(Short.valueOf("2"));
		beneficiaryModel.setMaritalStatusName("Married");
		beneficiaryModel.setSexualOrientationId(Short.valueOf("1"));
		beneficiaryModel.setSexualOrientationName("Not Virgin");
		beneficiaryModel.setDOB(Timestamp.valueOf("2018-09-01 09:01:16"));
		beneficiaryModel.setAge(36);
		beneficiaryModel.setFatherName("Niran Kumar Ranajay");
		beneficiaryModel.setSpouseName("Baju Kumar Kundu");
		beneficiaryModel.setGovtIdentityNo("G65435P");
		beneficiaryModel.setGovtIdentityTypeID(3);
		beneficiaryModel.setRegisteredServiceID(Short.valueOf("6"));
		beneficiaryModel.setSourceOfInformation("Professional Website");
		beneficiaryModel.setIsHIVPos(false);
		beneficiaryModel.setPlaceOfWork("Pune");
		beneficiaryModel.setRemarks("Excellent Doctor");
		beneficiaryModel.setChangeInSelfDetails(false);
		beneficiaryModel.setChangeInAddress(false);
		beneficiaryModel.setChangeInContacts(false);
		beneficiaryModel.setChangeInFamilyDetails(false);
		beneficiaryModel.setChangeInIdentities(false);
		beneficiaryModel.setChangeInOtherDetails(true);
		beneficiaryModel.setChangeInAssociations(true);
		beneficiaryModel.setDeleted(false);
		beneficiaryModel.setCreatedBy("P Medical Company");
		beneficiaryModel.setCreatedDate(Timestamp.valueOf("2000-08-08 09:01:16"));
		beneficiaryModel.setModifiedBy("C Medical Company");
		beneficiaryModel.setLastModDate(Timestamp.valueOf("2001-09-09 09:01:16"));

		List<BeneficiaryModel> saveData = new ArrayList<BeneficiaryModel>();

		saveData.add(beneficiaryModel);

		String model = saveData.toString();

		when(visitService.getVisitFromAdvanceSearch(model, auth)).thenReturn(saveData);

		String resp = visitController.getVisitFromAdvanceSearch(model, httpRequest);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		Assertions.assertEquals(response.toString(), visitController.getVisitFromAdvanceSearch(model, httpRequest));
	}

	@Test
	public void getVisitFromAdvanceSearchTestEXception() throws Exception {

		HttpServletRequest httpRequest = mock(HttpServletRequest.class);

		String auth = httpRequest.getHeader(
				"{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}");

		String response = visitController.getVisitFromAdvanceSearch(any(), httpRequest);

		Assertions.assertEquals(response, visitController.getVisitFromAdvanceSearch(any(), httpRequest));
	}
}
