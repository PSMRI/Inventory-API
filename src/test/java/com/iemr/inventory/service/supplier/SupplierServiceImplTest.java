package com.iemr.inventory.service.supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.supplier.M_Supplier;
import com.iemr.inventory.data.supplier.M_Supplieraddress;
import com.iemr.inventory.repo.supplier.M_SupplieraddressRepo;
import com.iemr.inventory.repo.supplier.SupplierRepo;

@ExtendWith(MockitoExtension.class)
class SupplierServiceImplTest {

	@Mock
	private SupplierRepo supplierRepo;

	@Mock
	private M_SupplieraddressRepo m_SupplieraddressRepo;

	@InjectMocks
	private SupplierServiceImpl supplierService;

	@Test
	public void testCreateSupplier() {
		List<M_Supplier> suppliers = new ArrayList<>();
		// Populate with test data as necessary

		M_Supplier supplier1 = new M_Supplier();

		supplier1.setSupplierID(1);
		supplier1.setSupplierName("RAM");
		supplier1.setSupplierCode("24");
		supplier1.toString();

		suppliers.add(new M_Supplier());

		when(supplierRepo.saveAll(anyList())).thenReturn(suppliers);

		ArrayList<M_Supplier> result = supplierService.createSupplier(suppliers);

		assertNotNull(result, "The saved suppliers should not be null");
		assertNotEquals(0, result.size());
		assertEquals(suppliers.size(), result.size(), "The number of suppliers saved should match");

		verify(supplierRepo, times(1)).saveAll(anyList());

	}

	@Test
	public void testCreateSupplierAsNull() {

		List<M_Supplier> suppliers = new ArrayList<>();

		assertEquals(null, supplierService.createSupplier(suppliers));
	}

	@Test
	public void testGetSupplier() {
		Integer providerServiceMapID = 123; // Example ID
		ArrayList<M_Supplier> mockSuppliers = new ArrayList<>();

		// Create a mock supplier with test data
		M_Supplier mockSupplier = new M_Supplier();
		mockSupplier.setSupplierID(1);
		mockSupplier.setSupplierName("RAM");
		mockSupplier.setSupplierCode("24");
		mockSupplier.toString();

		mockSuppliers.add(mockSupplier);

		when(supplierRepo.getSupplierData(providerServiceMapID)).thenReturn(mockSuppliers);

		ArrayList<M_Supplier> result = supplierService.getSupplier(providerServiceMapID);

		assertNotNull(result, "The retrieved suppliers should not be null");
		assertFalse(result.isEmpty(), "The result should not be empty");
		assertEquals(mockSuppliers.size(), result.size(), "The number of retrieved suppliers should match");
		assertNotEquals(0, result.size());

		// Additional assertions to verify the data
		assertEquals(mockSupplier.getSupplierID(), result.get(0).getSupplierID(), "The supplier ID should match");
		assertEquals(mockSupplier.getSupplierName(), result.get(0).getSupplierName(), "The supplier name should match");

		verify(supplierRepo, times(1)).getSupplierData(providerServiceMapID);

	}

	@Test
	public void testGetSupplierAsNull() {

		Integer providerServiceMapID = null;

		assertEquals(null, supplierService.getSupplier(providerServiceMapID));
	}

	@Test
	public void testEditSupplier() {
		Integer supplierID = 1; // Example supplier ID
		M_Supplier mockSupplier = new M_Supplier(); // Create a mock M_Supplier object
		// Populate mockSupplier with test data as necessary
		mockSupplier.setSupplierID(1);
		mockSupplier.setSupplierName("RAM");
		mockSupplier.setSupplierCode("24");
		mockSupplier.toString();

		when(supplierRepo.geteditedData(supplierID)).thenReturn(mockSupplier);

		M_Supplier result = supplierService.editSupplier(supplierID);

		assertNotNull(result, "The retrieved supplier should not be null");
		assertEquals(mockSupplier, result, "The retrieved supplier should match the mock supplier");

		// Additional assertions can be added to verify specific fields
		assertEquals(supplierID, result.getSupplierID());
		assertEquals("RAM", result.getSupplierName());
		assertEquals("24", result.getSupplierCode());

		verify(supplierRepo, times(1)).geteditedData(supplierID);

	}

	@Test
	public void testSaveEditedData() {
		M_Supplier mockSupplier = new M_Supplier(); // Create a mock M_Supplier object
		// Populate mockSupplier with test data as necessary
		mockSupplier.setSupplierID(1);
		mockSupplier.setSupplierName("RAM");
		mockSupplier.setSupplierCode("24");
		mockSupplier.toString();

		when(supplierRepo.save(any(M_Supplier.class))).thenReturn(mockSupplier);

		M_Supplier result = supplierService.saveEditedData(mockSupplier);

		// Perform assertions
		assertNotNull(result, "The saved supplier data should not be null");
		assertEquals(mockSupplier.getSupplierID(), result.getSupplierID(), "The saved supplier ID should match");
		assertEquals(mockSupplier.getSupplierName(), result.getSupplierName(), "The saved supplier name should match");
		assertEquals(mockSupplier.getSupplierCode(), result.getSupplierCode(),
				"The saved supplier address should match");

		verify(supplierRepo, times(1)).save(any(M_Supplier.class));

	}

	@Test
	public void testCreateAddress() {
		List<M_Supplieraddress> mockAddresses = new ArrayList<>();
		mockAddresses.add(new M_Supplieraddress()); // Populate with test data as necessary

		M_Supplieraddress address1 = new M_Supplieraddress();
		address1.setSupplierAddressID(1);
		address1.setAddressLine1("123 Main St");
		address1.setPinCode("12345");
		address1.toString();
		mockAddresses.add(address1);

		when(m_SupplieraddressRepo.saveAll(anyList())).thenReturn(mockAddresses);

		ArrayList<M_Supplieraddress> result = supplierService.createAddress(mockAddresses);

		assertNotNull(result, "The saved addresses should not be null");
		assertEquals(mockAddresses.size(), result.size(), "The number of saved addresses should match the input");

		verify(m_SupplieraddressRepo, times(1)).saveAll(anyList());

	}

}
