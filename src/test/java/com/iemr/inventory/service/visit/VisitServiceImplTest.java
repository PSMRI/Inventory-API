package com.iemr.inventory.service.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.ResourceAccessException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.iemr.inventory.data.visit.BenVisitDetail;
import com.iemr.inventory.data.visit.BeneficiaryFlowStatus;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.repo.visit.BeneficiaryFlowStatusRepo;
import com.iemr.inventory.repo.visit.VisitRepo;
import com.iemr.inventory.utils.config.ConfigProperties;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.http.HttpUtils;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

class VisitServiceImplTest {

	protected String identityBaseURL = ConfigProperties.getPropertyByName("common-api-url");
	private InputMapper inputMapper = new InputMapper();
	private static final String COMMON_BASE_URL = "COMMON_BASE_URL";

	static String auth = "token";

	@Mock
	HttpUtils httpUtils;

	@Mock
	VisitRepo visitRepo;

	@Mock
	BeneficiaryFlowStatusRepo beneficiaryFlowStatusRepo;

	@Mock
	private ConfigProperties configProperties;

	@InjectMocks
	VisitServiceImpl visitService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetVisitDetail() throws Exception {

		// Define the necessary objects and mock data
		String benrID = "BenrID";
		Integer providerservicemapID = 1;
		String auth = "Auth";

		BeneficiaryFlowStatus beneficiaryFlowStatus = new BeneficiaryFlowStatus();
		beneficiaryFlowStatus.setProviderServiceMapId(3);
		beneficiaryFlowStatus.setBeneficiaryRegID(2L);

		beneficiaryFlowStatus.toString();

		List<BeneficiaryFlowStatus> beneficiaryFlowStatusList = new ArrayList<BeneficiaryFlowStatus>();
		beneficiaryFlowStatusList.add(beneficiaryFlowStatus);

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

		List<BenVisitDetail> benVisitDetailList = new ArrayList<BenVisitDetail>();
		benVisitDetailList.add(benVisitDetail);

		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();
		beneficiaryModel.setBenVisitDetail(benVisitDetailList);
		beneficiaryModel.setBeneficiaryFlowStatus(beneficiaryFlowStatusList);

		beneficiaryModel.toString();

		List<BeneficiaryModel> benModel = new ArrayList<BeneficiaryModel>();
		benModel.add(beneficiaryModel);

		// Mock the dependencies' behaviors
		when(visitRepo.findBybeneficiaryRegIDAndProviderServiceMapID(beneficiaryModel.getBeneficiaryRegID(),
				providerservicemapID)).thenReturn(benVisitDetailList);

		when(beneficiaryFlowStatusRepo.findByBeneficiaryRegIDAndProviderServiceMapIdAndDoctorFlagInAndBenVisitIDNotNull(
				beneficiaryModel.getBeneficiaryRegID(), providerservicemapID,
				new Short[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 })).thenReturn(beneficiaryFlowStatusList);

		// Perform the test
		BeneficiaryModel result = visitService.getVisitDetail(benrID, providerservicemapID, auth);

		// Verify the results
		verify(visitRepo, times(1)).findBybeneficiaryRegIDAndProviderServiceMapID(
				beneficiaryModel.getBeneficiaryRegID(), providerservicemapID);
		verify(beneficiaryFlowStatusRepo, times(1))
				.findByBeneficiaryRegIDAndProviderServiceMapIdAndDoctorFlagInAndBenVisitIDNotNull(
						beneficiaryModel.getBeneficiaryRegID(), providerservicemapID,
						new Short[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		assertNotNull(result);
	}

	@Test
	public void testGetVisitDetail_InvalidBeneficiaryID() {
		// Arrange
		String benrID = "invalidID";
		Integer providerservicemapID = 456;
		String auth = "token";
		when(visitRepo.findBybeneficiaryRegIDAndProviderServiceMapID(any(), any()))
				.thenThrow(ResourceAccessException.class);

		// Act & Assert
		assertThrows(ResourceAccessException.class,
				() -> visitService.getVisitDetail(benrID, providerservicemapID, auth));
	}

	@Test
	public void testGetBeneficiaryListByIDs_Success() throws Exception {
		
	

		BeneficiaryFlowStatus beneficiaryFlowStatus = new BeneficiaryFlowStatus();
		beneficiaryFlowStatus.setProviderServiceMapId(3);
		beneficiaryFlowStatus.setBeneficiaryRegID(2L);

		beneficiaryFlowStatus.toString();

		List<BeneficiaryFlowStatus> beneficiaryFlowStatusList = new ArrayList<BeneficiaryFlowStatus>();
		beneficiaryFlowStatusList.add(beneficiaryFlowStatus);

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

		List<BenVisitDetail> benVisitDetailList = new ArrayList<BenVisitDetail>();
		benVisitDetailList.add(benVisitDetail);

		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();
		beneficiaryModel.setBenVisitDetail(benVisitDetailList);
		beneficiaryModel.setBeneficiaryFlowStatus(beneficiaryFlowStatusList);

		beneficiaryModel.toString();

		List<BeneficiaryModel> benModel = new ArrayList<BeneficiaryModel>();
		benModel.add(beneficiaryModel);

		// Define the necessary objects and mock data
		String benrID = "benrID";
		String auth = "auth";

		Map<String, String> header = new HashMap<>();

		header.put("Authorization", auth);

		List<BeneficiaryModel> result = new ArrayList<BeneficiaryModel>();
		result.add(beneficiaryModel);

		List<BeneficiaryModel> listBenDetailForOutboundDTO = result;

		when(httpUtils.post(ConfigProperties.getPropertyByName("common-api-url-searchuserbyid").replace(COMMON_BASE_URL,
				identityBaseURL), benrID)).thenReturn(listBenDetailForOutboundDTO.toString());

		// Verify the returned list contains expected data
		assertEquals(1, result.size());
		assertEquals(listBenDetailForOutboundDTO, visitService.getBeneficiaryListByIDs(benrID, auth));
	}

	@Test
	public void testGetBeneficiaryListByIDs_InvalidBeneficiaryID() {
		// Arrange
		String benrID = "invalidID";
		String auth = "token";

		// Act & Assert
		assertThrows(ResourceAccessException.class, () -> visitService.getBeneficiaryListByIDs(benrID, auth));
	}

	@Test
	public void testGetBeneficiaryListBySearch_Success() throws IEMRException {
		// Define the necessary objects and mock data
		String benrID = "BenrID";
		String auth = "Auth";

		BeneficiaryFlowStatus beneficiaryFlowStatus = new BeneficiaryFlowStatus();
		beneficiaryFlowStatus.setProviderServiceMapId(3);
		beneficiaryFlowStatus.setBeneficiaryRegID(2L);

		beneficiaryFlowStatus.toString();

		List<BeneficiaryFlowStatus> beneficiaryFlowStatusList = new ArrayList<BeneficiaryFlowStatus>();
		beneficiaryFlowStatusList.add(beneficiaryFlowStatus);

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

		List<BenVisitDetail> benVisitDetailList = new ArrayList<BenVisitDetail>();
		benVisitDetailList.add(benVisitDetail);

		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();
		beneficiaryModel.setBenVisitDetail(benVisitDetailList);
		beneficiaryModel.setBeneficiaryFlowStatus(beneficiaryFlowStatusList);

		beneficiaryModel.toString();

		// Mock the dependencies' behaviors
		String result = "{\"status\":\"success\",\"data\":[{\"beneficiaryName\":\"TestName\"}]}";
		Mockito.when(httpUtils.post(Mockito.anyString(), Mockito.anyString())).thenReturn(result);

		// Perform the test
		List<BeneficiaryModel> resultList = visitService.getBeneficiaryListBySearch(benrID, auth);

		// Verify the results
		Mockito.verify(httpUtils, Mockito.times(1)).post(Mockito.anyString(), Mockito.anyString());
		assertNotNull(resultList);
	}

	@Test
	public void testGetBeneficiaryListBySearch_InvalidUserID() {
		// Arrange
		String benrID = "invalidID";
		String auth = "token";

		// Act & Assert
		assertThrows(ResourceAccessException.class, () -> visitService.getBeneficiaryListBySearch(benrID, auth));
	}

	@Test
	public void testGetVisitFromAdvanceSearch() throws Exception {
		// Given
		String benrID = "123";
		String auth = "token";
		String resultJson = "{\"data\":[{\"id\":1,\"name\":\"John\"}]}";
		
		BeneficiaryFlowStatus beneficiaryFlowStatus = new BeneficiaryFlowStatus();
		beneficiaryFlowStatus.setProviderServiceMapId(3);
		beneficiaryFlowStatus.setBeneficiaryRegID(2L);

		beneficiaryFlowStatus.toString();

		List<BeneficiaryFlowStatus> beneficiaryFlowStatusList = new ArrayList<BeneficiaryFlowStatus>();
		beneficiaryFlowStatusList.add(beneficiaryFlowStatus);

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

		List<BenVisitDetail> benVisitDetailList = new ArrayList<BenVisitDetail>();
		benVisitDetailList.add(benVisitDetail);

		BeneficiaryModel beneficiaryModel = new BeneficiaryModel();
		beneficiaryModel.setBenVisitDetail(benVisitDetailList);
		beneficiaryModel.setBeneficiaryFlowStatus(beneficiaryFlowStatusList);

		beneficiaryModel.toString();

		// When
		List<BeneficiaryModel> result = visitService.getVisitFromAdvanceSearch(benrID, auth);

		// Then
		assertNotNull(result);
		assertEquals(1, result.size());
	}
}