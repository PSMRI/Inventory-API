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
package com.iemr.inventory.service.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedConstruction;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.iemr.inventory.data.visit.BeneficiaryFlowStatus;
import com.iemr.inventory.data.visit.BenVisitDetail;
import com.iemr.inventory.data.visit.BeneficiaryModel;
import com.iemr.inventory.repo.visit.BeneficiaryFlowStatusRepo;
import com.iemr.inventory.repo.visit.VisitRepo;
import com.iemr.inventory.utils.CookieUtil;
import com.iemr.inventory.utils.exception.IEMRException;
import com.iemr.inventory.utils.exception.InventoryException;

import jakarta.servlet.http.Cookie;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("VisitServiceImpl Test Suite")
class VisitServiceImplTest {

	private static final String AUTH = "test-session-key";
	private static final String LOOKUP_URL = "http://common-api/searchUserById";
	private static final String SEARCH_URL = "http://common-api/searchBeneficiary";

	private static final String ONE_BENEFICIARY =
			"{\"statusCode\":200,\"data\":[{\"beneficiaryRegID\":77,\"beneficiaryID\":\"12345\"}]}";

	@Mock
	private VisitRepo visitRepo;
	@Mock
	private BeneficiaryFlowStatusRepo beneficiaryFlowStatusRepo;
	@Mock
	private CookieUtil cookieUtil;

	@InjectMocks
	private VisitServiceImpl service;

	@BeforeEach
	@DisplayName("Bind a request carrying a Jwttoken cookie and point the service at the stub URLs")
	void setUp() {
		ReflectionTestUtils.setField(service, "commonApiUrlSearchUserById", LOOKUP_URL);
		ReflectionTestUtils.setField(service, "commonApiUrlSearchBeneficiary", SEARCH_URL);

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setCookies(new Cookie("Jwttoken", "jwt-value"));
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	@AfterEach
	@DisplayName("Unbind the request so it cannot leak into the next test")
	void tearDown() {
		RequestContextHolder.resetRequestAttributes();
	}

	/** Stubs every RestTemplate the service builds internally with a single canned response. */
	private MockedConstruction<RestTemplate> restTemplateReturning(ResponseEntity<String> response) {
		return mockConstruction(RestTemplate.class, (mock, context) ->
				when(mock.exchange(any(String.class), eq(HttpMethod.POST), any(HttpEntity.class), eq(String.class)))
						.thenReturn(response));
	}

	@Test
	@DisplayName("getVisitDetail should attach the visit and flow rows to the beneficiary the lookup returned")
	void getVisitDetail_shouldAttachVisitAndFlowRows() throws Exception {
		List<BenVisitDetail> visits = List.of(new BenVisitDetail());
		List<BeneficiaryFlowStatus> flow = List.of(new BeneficiaryFlowStatus());
		when(visitRepo.findBybeneficiaryRegIDAndProviderServiceMapID(77L, 3)).thenReturn(visits);
		when(beneficiaryFlowStatusRepo
				.findByBeneficiaryRegIDAndProviderServiceMapIdAndDoctorFlagInAndBenVisitIDNotNull(eq(77L), eq(3),
						any(Short[].class)))
				.thenReturn(flow);

		try (MockedConstruction<RestTemplate> ignored =
				restTemplateReturning(new ResponseEntity<>(ONE_BENEFICIARY, HttpStatus.OK))) {

			BeneficiaryModel result = service.getVisitDetail("12345", 3, AUTH);

			assertEquals(77L, result.getBeneficiaryRegID());
			assertEquals(visits, result.getBenVisitDetail());
			assertEquals(flow, result.getBeneficiaryFlowStatus());
		}
	}

	@Test
	@DisplayName("getVisitDetail should reject a beneficiary id the lookup service does not know")
	void getVisitDetail_shouldRejectUnknownBeneficiary() {
		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>("{\"statusCode\":200,\"data\":[]}", HttpStatus.OK))) {

			InventoryException ex = assertThrows(InventoryException.class,
					() -> service.getVisitDetail("12345", 3, AUTH));
			assertEquals("Invalid Beneficiary ID", ex.getMessage());
		}
	}

	@Test
	@DisplayName("getBeneficiaryListByIDs should map every beneficiary the lookup service returned")
	void getBeneficiaryListByIDs_shouldMapEveryBeneficiary() throws Exception {
		String body = "{\"statusCode\":200,\"data\":[{\"beneficiaryRegID\":77},{\"beneficiaryRegID\":78}]}";

		try (MockedConstruction<RestTemplate> ignored =
				restTemplateReturning(new ResponseEntity<>(body, HttpStatus.OK))) {

			List<BeneficiaryModel> result = service.getBeneficiaryListByIDs("12345", AUTH);

			assertEquals(2, result.size());
			assertEquals(78L, result.get(1).getBeneficiaryRegID());
		}
	}

	@Test
	@DisplayName("getBeneficiaryListByIDs should work without an Authorization header")
	void getBeneficiaryListByIDs_shouldWorkWithoutAuthorizationHeader() throws Exception {
		try (MockedConstruction<RestTemplate> ignored =
				restTemplateReturning(new ResponseEntity<>(ONE_BENEFICIARY, HttpStatus.OK))) {

			assertEquals(1, service.getBeneficiaryListByIDs("12345", null).size());
		}
	}

	@Test
	@DisplayName("getBeneficiaryListByIDs should reject a non-OK response from the lookup service")
	void getBeneficiaryListByIDs_shouldRejectNonOkResponse() {
		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(ONE_BENEFICIARY, HttpStatus.INTERNAL_SERVER_ERROR))) {

			InventoryException ex = assertThrows(InventoryException.class,
					() -> service.getBeneficiaryListByIDs("12345", AUTH));
			assertTrue(ex.getMessage().contains("No response or invalid status"));
		}
	}

	@Test
	@DisplayName("getBeneficiaryListByIDs should reject a body whose own status code is not 200")
	void getBeneficiaryListByIDs_shouldRejectFailingBodyStatus() {
		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>("{\"statusCode\":5002,\"data\":[]}", HttpStatus.OK))) {

			InventoryException ex = assertThrows(InventoryException.class,
					() -> service.getBeneficiaryListByIDs("12345", AUTH));
			assertEquals("Invalid BeneficiaryRegID", ex.getMessage());
		}
	}

	@Test
	@DisplayName("getVisitFromAdvanceSearch should map every beneficiary the search service returned")
	void getVisitFromAdvanceSearch_shouldMapEveryBeneficiary() throws Exception {
		String body = "{\"statusCode\":200,\"data\":[{\"beneficiaryRegID\":77},{\"beneficiaryRegID\":78}]}";

		try (MockedConstruction<RestTemplate> ignored =
				restTemplateReturning(new ResponseEntity<>(body, HttpStatus.OK))) {

			List<BeneficiaryModel> result = service.getVisitFromAdvanceSearch("12345", AUTH);

			assertEquals(2, result.size());
		}
	}

	@Test
	@DisplayName("getVisitFromAdvanceSearch should reject a non-OK response from the search service")
	void getVisitFromAdvanceSearch_shouldRejectNonOkResponse() {
		try (MockedConstruction<RestTemplate> ignored = restTemplateReturning(
				new ResponseEntity<>(ONE_BENEFICIARY, HttpStatus.BAD_GATEWAY))) {

			IEMRException ex = assertThrows(IEMRException.class,
					() -> service.getVisitFromAdvanceSearch("12345", AUTH));
			assertTrue(ex.getMessage().contains("No response or invalid status"));
		}
	}

	@Test
	@DisplayName("getVisitFromAdvanceSearch should surface the user-id failure the search service reported")
	void getVisitFromAdvanceSearch_shouldSurfaceUserIdFailure() {
		String body = "{\"statusCode\":5002,\"errorMessage\":\"Invalid session\",\"data\":[]}";

		try (MockedConstruction<RestTemplate> ignored =
				restTemplateReturning(new ResponseEntity<>(body, HttpStatus.OK))) {

			IEMRException ex = assertThrows(IEMRException.class,
					() -> service.getVisitFromAdvanceSearch("12345", AUTH));
			assertEquals("Invalid session", ex.getMessage());
		}
	}

	@Test
	@DisplayName("getVisitFromAdvanceSearch should work without an Authorization header")
	void getVisitFromAdvanceSearch_shouldWorkWithoutAuthorizationHeader() throws Exception {
		try (MockedConstruction<RestTemplate> ignored =
				restTemplateReturning(new ResponseEntity<>(ONE_BENEFICIARY, HttpStatus.OK))) {

			assertEquals(1, service.getVisitFromAdvanceSearch("12345", null).size());
		}
	}
}
