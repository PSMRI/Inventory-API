package com.iemr.inventory.controller.uom;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.service.uom.UomInter;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasurementControllerTest {

	@InjectMocks
	private UnitOfMeasurementController uomController;

	@Mock
	private UomInter uomInter;

	@Mock
	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void testCreateUom() {

		M_Uom m_uom = new M_Uom();
		m_uom.setuOMID(1);
		m_uom.setuOMName("Test");
		m_uom.setuOMDesc("Test Decs");
		m_uom.setuOMCode("123Test");
		m_uom.setuOMCode("123abc");
		m_uom.setProcessed('Y');
		m_uom.setProviderServiceMapID(123);
		m_uom.setCreatedBy("Admin");
		m_uom.setCreatedDate(Date.valueOf("2024-01-01"));
		m_uom.setDeleted(false);
		m_uom.setLastModDate(Date.valueOf("2024-01-01"));
		m_uom.setStatus("Available");

		List<M_Uom> saveUomDataList = new ArrayList<M_Uom>();
		saveUomDataList.add(m_uom);

		M_Uom[] UomData = new M_Uom[saveUomDataList.size()];
		UomData = saveUomDataList.toArray(UomData);

		List<M_Uom> saveUomData = Arrays.asList(UomData);

		ArrayList<M_Uom> saveUomDataList1 = new ArrayList<M_Uom>();
		saveUomDataList1.add(m_uom);

		ArrayList<M_Uom> saveData = saveUomDataList1;

		when(uomInter.createDrugtypeData(saveUomData)).thenReturn(saveData);

		String createUom = saveData.toString();

		String res = uomController.createUom(createUom);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		Assertions.assertEquals(res, uomController.createUom(createUom));

	}

	@Test
	public void testCreateUomException() {

		String createUom = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = uomController.createUom(createUom);

		Assertions.assertEquals(response, uomController.createUom(createUom));
	}

	@Test
	public void testGetUom() {

		M_Uom m_uom = new M_Uom();
		m_uom.setuOMID(1);
		m_uom.setuOMName("Test");
		m_uom.setuOMDesc("Test Decs");
		m_uom.setuOMCode("123Test");
		m_uom.setuOMCode("123abc");
		m_uom.setProcessed('Y');
		m_uom.setProviderServiceMapID(123);
		m_uom.setCreatedBy("Admin");
		m_uom.setCreatedDate(Date.valueOf("2024-01-01"));
		m_uom.setDeleted(false);
		m_uom.setLastModDate(Date.valueOf("2024-01-01"));
		m_uom.setStatus("Available");

		ArrayList<M_Uom> getData = new ArrayList<M_Uom>();
		getData.add(m_uom);

		String getUom = getData.toString();

		String expectedResponse = uomController.getUom(getUom);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());

		Assertions.assertEquals(expectedResponse, uomController.getUom(getUom));

	}

	@Test
	public void testGetUomException() {

		String getUom = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = uomController.getUom(getUom);

		Assertions.assertEquals(response, uomController.getUom(getUom));
	}

	@Test
	public void testEditUom() {
		OutputResponse expectedResponse = new OutputResponse();

		M_Uom UomData = new M_Uom();

		UomData.setuOMName("Test");
		UomData.setuOMDesc("Test Decs");
		UomData.setuOMCode("123Test");
		UomData.setModifiedBy("Admin1");
		UomData.setStatus("Available");

		M_Uom geteditedData = new M_Uom();

		geteditedData.setuOMName(UomData.getuOMName());
		geteditedData.setuOMDesc(UomData.getuOMDesc());
		geteditedData.setuOMCode(UomData.getuOMCode());
		geteditedData.setStatus(UomData.getStatus());
		geteditedData.setModifiedBy(UomData.getModifiedBy());

		when(uomInter.editDrugtypeData(UomData.getuOMID())).thenReturn(geteditedData);

		M_Uom editedData = new M_Uom();

		when(uomInter.saveeditedData(geteditedData)).thenReturn(editedData);

		String editUom = editedData.toString();

		expectedResponse.setResponse(editedData.toString());

		Assertions.assertEquals(expectedResponse.toString(), uomController.editUom(editUom));
	}
	
	@Test
	public void testEditUomException() {

		String editUom = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		M_Uom UomData = new M_Uom();
		
		when(uomInter.editDrugtypeData(UomData.getuOMID())).thenThrow(NotFoundException.class);
		
		String response = uomController.editUom(editUom);

		Assertions.assertEquals(response, uomController.editUom(editUom));
	}

	@Test
	public void testDeleteUom() {

		OutputResponse expectedResponse = new OutputResponse();

		M_Uom UomData = new M_Uom();

		M_Uom geteditedData = new M_Uom();

		when(uomInter.editDrugtypeData(UomData.getuOMID())).thenReturn(geteditedData);

		geteditedData.setDeleted(UomData.getDeleted());

		M_Uom deletedData = new M_Uom();

		when(uomInter.saveeditedData(geteditedData)).thenReturn(deletedData);

		String editUom = deletedData.toString();

		expectedResponse.setResponse(deletedData.toString());

		Assertions.assertEquals(expectedResponse.toString(), uomController.deleteUom(editUom));
	}

	@Test
	public void testDeleteUomException() {

		String editUom = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		M_Uom UomData = new M_Uom();

		M_Uom geteditedData = new M_Uom();

		when(uomInter.editDrugtypeData(UomData.getuOMID())).thenThrow(NotFoundException.class);

		String response = uomController.deleteUom(editUom);

		Assertions.assertEquals(response, uomController.deleteUom(editUom));
	}

}
