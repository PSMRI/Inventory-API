package com.iemr.inventory.controller.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.users.MemoryUserDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iemr.inventory.data.supplier.M_Supplier;
import com.iemr.inventory.data.supplier.M_Supplieraddress;
import com.iemr.inventory.service.supplier.SupplierInter;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;

@ExtendWith(MockitoExtension.class)
class SupplierMasterControllerTest {

	@InjectMocks
	SupplierMasterController supplierMasterController;

	@Mock
	SupplierInter supplierInter;

	@Test
	public void createSupplierTest() {

		M_Supplier m_suppiler = new M_Supplier();

		m_suppiler.setSupplierID(1);
		m_suppiler.setSupplierName("RAM");
		m_suppiler.setSupplierCode("24");
		m_suppiler.setProviderServiceMapID(6);

		ArrayList<M_Supplier> SupplierData = new ArrayList<M_Supplier>();

		SupplierData.add(m_suppiler);

		M_Supplier[] Supplier = new M_Supplier[SupplierData.size()];
		Supplier = SupplierData.toArray(Supplier);

		M_Supplieraddress m_Supplieraddress = new M_Supplieraddress();

		m_Supplieraddress.setSupplierAddressID(3);
		m_Supplieraddress.setSupplierID(4);
		m_Supplieraddress.setAddressLine1("ghjxghjgdfhjdf");
		m_Supplieraddress.setAddressLine2("sagyfjdsgfdhjsghjsgd");
		m_Supplieraddress.setDistrict("WB");
		m_Supplieraddress.setState("WB");
		m_Supplieraddress.setCountry("India");
		m_Supplieraddress.setPinCode("411014");
		m_Supplieraddress.setProviderServiceMapID(5);
		m_Supplieraddress.setDeleted(false);
		m_Supplieraddress.setCreatedBy("Nysfg");
		m_Supplieraddress.setCreatedDate(Date.valueOf("2020-09-09"));
		m_Supplieraddress.setModifiedBy("Fhhdfj");
		m_Supplieraddress.setLastModDate(Date.valueOf("2020-09-09"));

		List<M_Supplieraddress> SupplierAddressData = new ArrayList<M_Supplieraddress>();
		SupplierAddressData.add(m_Supplieraddress);

		M_Supplieraddress[] Supplieraddress = new M_Supplieraddress[SupplierAddressData.size()];
		Supplieraddress = SupplierAddressData.toArray(Supplieraddress);

		ArrayList<M_Supplier> createdData = SupplierData;

		when(supplierInter.createSupplier(SupplierData)).thenReturn(createdData);
		
		int x=0;

		M_Supplieraddress resDataMap1 =  new M_Supplieraddress();
		
		resDataMap1.setSupplierID(createdData.get(x).getSupplierID());
		resDataMap1.setAddressLine1(SupplierAddressData.get(x).getAddressLine1());
		resDataMap1.setAddressLine2(SupplierAddressData.get(x).getAddressLine2());
		resDataMap1.setDistrict(SupplierAddressData.get(x).getDistrict());
		resDataMap1.setState(SupplierAddressData.get(x).getState());
		resDataMap1.setCountry(SupplierAddressData.get(x).getCountry());
		resDataMap1.setPinCode(SupplierAddressData.get(x).getPinCode());
		resDataMap1.setProviderServiceMapID(SupplierAddressData.get(x).getProviderServiceMapID());
		resDataMap1.setCreatedBy(SupplierAddressData.get(x).getCreatedBy());

		ArrayList<M_Supplieraddress> resList1 = new ArrayList<M_Supplieraddress>();

		resList1.add(resDataMap1);

		ArrayList<M_Supplieraddress> storedData = resList1;

		when(supplierInter.createAddress(resList1)).thenReturn(storedData);

		String createSupplier = storedData.toString();

		String resp = supplierMasterController.createSupplier(createSupplier);

		OutputResponse response = new OutputResponse();

		response.setResponse(createdData.toString());

		Assertions.assertEquals(resp, supplierMasterController.createSupplier(createSupplier));
	}

	@Test
	public void createSupplierTestException() {

		String createSupplier = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String resp = supplierMasterController.createSupplier(createSupplier);

		Assertions.assertEquals(resp, supplierMasterController.createSupplier(createSupplier));
	}

	@Test
	public void getSupplierTest() {

		OutputResponse response = new OutputResponse();

		M_Supplier Supplier = new M_Supplier();

		Supplier.setSupplierID(1);
		Supplier.setSupplierName("RAM");
		Supplier.setSupplierCode("24");
		Supplier.setProviderServiceMapID(6);

		ArrayList<M_Supplier> getData = new ArrayList<M_Supplier>();
		getData.add(Supplier);
		
		String getSupplier = getData.toString();

		response.setResponse(getData.toString());
		
		String resp = supplierMasterController.getSupplier(getSupplier);

		Assertions.assertEquals(resp, supplierMasterController.getSupplier(getSupplier));
	}
	
	@Test
	public void getSupplierTestException() {

		String getSupplier = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";
		
		when(supplierInter.getSupplier(any())).thenThrow(NotFoundException.class);
		
		String resp = supplierMasterController.getSupplier(getSupplier);

		Assertions.assertEquals(resp, supplierMasterController.getSupplier(getSupplier));
	}
	

	@Test
	public void editSupplierTest() {

		M_Supplier Supplier = new M_Supplier();

		Supplier.setSupplierID(1);
		Supplier.setSupplierName("RAM");
		Supplier.setSupplierDesc("Good Supplier");
		Supplier.setSupplierCode("24");
		Supplier.setStatus("Appreciated");
		Supplier.setContactPerson("Ram Chowdhury");
		Supplier.setDrugLicenseNo("Li657474345CENCE");
		Supplier.setcST_GST_No("ghjdjgfh");
		Supplier.settIN_No("91");
		Supplier.setEmail("ram.c123@gmail.com");
		Supplier.setPhoneNo1("9473564541");
		Supplier.setPhoneNo2("8538673874");
		Supplier.setModifiedBy("AN Basu");
		Supplier.setProviderServiceMapID(6);

		M_Supplier editData = Supplier;

		editData.setSupplierName(Supplier.getSupplierName());
		editData.setSupplierDesc(Supplier.getSupplierDesc());
		editData.setSupplierCode(Supplier.getSupplierCode());
		editData.setStatus(Supplier.getStatus());
		editData.setContactPerson(Supplier.getContactPerson());
		editData.setDrugLicenseNo(Supplier.getDrugLicenseNo());
		editData.setcST_GST_No(Supplier.getcST_GST_No());
		editData.settIN_No(Supplier.gettIN_No());
		editData.setEmail(Supplier.getEmail());
		editData.setPhoneNo1(Supplier.getPhoneNo1());
		editData.setPhoneNo2(Supplier.getPhoneNo2());
		editData.setModifiedBy(Supplier.getModifiedBy());

		M_Supplier editedData = editData;

		String editSupplier = editedData.toString();

		String resp = supplierMasterController.editSupplier(editSupplier);

		OutputResponse response = new OutputResponse();

		response.setResponse(editedData.toString());

		Assertions.assertEquals(resp, supplierMasterController.editSupplier(editSupplier));
	}

	@Test
	public void editSupplierTestException() {

		String editSupplier = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String resp = supplierMasterController.editSupplier(editSupplier);

		Assertions.assertEquals(resp, supplierMasterController.editSupplier(editSupplier));
	}

	@Test
	public void deleteSupplierTest() {

		M_Supplier Supplier = new M_Supplier();

		Supplier.setSupplierID(1);
		Supplier.setSupplierName("RAM");
		Supplier.setSupplierDesc("Good Supplier");
		Supplier.setSupplierCode("24");
		Supplier.setStatus("Appreciated");
		Supplier.setContactPerson("Ram Chowdhury");
		Supplier.setDrugLicenseNo("Li657474345CENCE");
		Supplier.setcST_GST_No("ghjdjgfh");
		Supplier.settIN_No("91");
		Supplier.setEmail("ram.c123@gmail.com");
		Supplier.setPhoneNo1("9473564541");
		Supplier.setPhoneNo2("8538673874");
		Supplier.setModifiedBy("AN Basu");
		Supplier.setProviderServiceMapID(6);

		M_Supplier editData = Supplier;

		when(supplierInter.editSupplier(Supplier.getSupplierID())).thenReturn(editData);

		editData.setDeleted(Supplier.getDeleted());

		M_Supplier deletedData = editData;

		when(supplierInter.saveEditedData(editData)).thenReturn(deletedData);

		String deleteSupplier = deletedData.toString();

		String resp = supplierMasterController.deleteSupplier(deleteSupplier);

		OutputResponse response = new OutputResponse();

		response.setResponse(deletedData.toString());

		Assertions.assertEquals(response.toString(), supplierMasterController.deleteSupplier(deleteSupplier));
	}

	@Test
	public void deleteSupplierTestException() {

		String deleteSupplier = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		when(supplierInter.editSupplier(any())).thenThrow(NotFoundException.class);

		String resp = supplierMasterController.deleteSupplier(deleteSupplier);

		Assertions.assertEquals(resp, supplierMasterController.deleteSupplier(deleteSupplier));
	}
	
	@Test
    void testCreateSupplier() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/createSupplier")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(supplierMasterController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SupplierMasterController#deleteSupplier(String)}
     */
    @Test
    void testDeleteSupplier() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.delete("/deleteSupplier")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(supplierMasterController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

   
    @Test
    void testEditSupplier() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/editSupplier")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(supplierMasterController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link SupplierMasterController#getSupplier(String)}
     */
    @Test
    void testGetSupplier() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/getSupplier")
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(supplierMasterController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
