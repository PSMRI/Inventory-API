package com.iemr.inventory.service.pharmacologicalcategory;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.repo.pharmacologicalcategory.PharmacologicalcategoryRepo;

@ExtendWith(MockitoExtension.class)
class PharmacologicalcategoryServiceImplTest {

	@InjectMocks
	PharmacologicalcategoryServiceImpl pharmacologicalcategoryServiceImpl;

	@Mock
	PharmacologicalcategoryRepo pharmacologicalcategoryRepo;

	@Test
	public void createPharmacologicalcategoryTest() {

		M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

		List<M_Pharmacologicalcategory> createPharmacologicaldata = new ArrayList<M_Pharmacologicalcategory>();

		m_Pharmacologicalcategory.setPharmCategoryID(6);
		m_Pharmacologicalcategory.setPharmCategoryName("Cancer Type");
		m_Pharmacologicalcategory
				.setPharmCategoryDesc("For cancer patients all the required medicines can be found here");
		m_Pharmacologicalcategory.setPharmCategoryCode("CAN123");
		m_Pharmacologicalcategory.setStatus("Approved and Ready");
		m_Pharmacologicalcategory.setProviderServiceMapID(67);
		m_Pharmacologicalcategory.setDeleted(false);
		m_Pharmacologicalcategory.setCreatedBy("TATA CANC MUMBAI");
		m_Pharmacologicalcategory.setCreatedDate(Date.valueOf("2000-01-10"));
		m_Pharmacologicalcategory.setModifiedBy("TATA CNC CHENNAI");
		m_Pharmacologicalcategory.setLastModDate(Date.valueOf("2010-01-10"));

		m_Pharmacologicalcategory.toString();

		createPharmacologicaldata.add(m_Pharmacologicalcategory);

		ArrayList<M_Pharmacologicalcategory> data = new ArrayList<M_Pharmacologicalcategory>();

		data.add(m_Pharmacologicalcategory);

		when(pharmacologicalcategoryRepo.saveAll(createPharmacologicaldata)).thenReturn(data);

		// Assertions
		Assertions.assertNotEquals(0, data.size());
		Assertions.assertEquals(data,
				pharmacologicalcategoryServiceImpl.createPharmacologicalcategory(createPharmacologicaldata));

	}

	@Test
	public void createPharmacologicalcategoryNotNullTest() {

		List<M_Pharmacologicalcategory> createPharmacologicaldata = new ArrayList<M_Pharmacologicalcategory>();

		ArrayList<M_Pharmacologicalcategory> data = new ArrayList<M_Pharmacologicalcategory>();

		when(pharmacologicalcategoryRepo.saveAll(createPharmacologicaldata)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(0, data.size());
		Assertions.assertEquals(null,
				pharmacologicalcategoryServiceImpl.createPharmacologicalcategory(createPharmacologicaldata));
	}

	@Test
	public void getPharmacologicalcategoryTest() {

		Integer providerServiceMapID = Integer.valueOf(3);

		M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

		m_Pharmacologicalcategory.setProviderServiceMapID(providerServiceMapID);

		m_Pharmacologicalcategory.toString();

		ArrayList<M_Pharmacologicalcategory> getData = new ArrayList<M_Pharmacologicalcategory>();

		getData.add(m_Pharmacologicalcategory);

		when(pharmacologicalcategoryRepo.getPhormacologicalData(providerServiceMapID)).thenReturn(getData);

		// Assertions
		Assertions.assertNotEquals(0, getData.size());
		Assertions.assertEquals(getData,
				pharmacologicalcategoryServiceImpl.getPharmacologicalcategory(providerServiceMapID));

	}

	@Test
	public void editPharmacologicalcategoryTest() {

		Integer pharmCategoryID = Integer.valueOf(6);

		M_Pharmacologicalcategory editData = new M_Pharmacologicalcategory();

		editData.setPharmCategoryID(pharmCategoryID);

		when(pharmacologicalcategoryRepo.editPhamacologicalData(pharmCategoryID)).thenReturn(editData);

		// Assertions
		Assertions.assertEquals(editData,
				pharmacologicalcategoryServiceImpl.editPharmacologicalcategory(pharmCategoryID));

		editData.toString();
	}

	@Test
	public void saveEditedPharDataTest() {

		M_Pharmacologicalcategory saveDataphar = new M_Pharmacologicalcategory();

		saveDataphar.setPharmCategoryID(6);
		saveDataphar.setPharmCategoryName("Cancer Type1");
		saveDataphar.setPharmCategoryDesc("For cancer patients all the required medicines can be found here");
		saveDataphar.setPharmCategoryCode("CAN1234");
		saveDataphar.setStatus("Approved and Ready");
		saveDataphar.setProviderServiceMapID(671);
		saveDataphar.setDeleted(false);
		saveDataphar.setCreatedBy("TATA CANC MUMBAI");
		saveDataphar.setCreatedDate(Date.valueOf("2000-01-10"));
		saveDataphar.setModifiedBy("TATA CNC CHENNAI");
		saveDataphar.setLastModDate(Date.valueOf("2010-01-10"));

		saveDataphar.toString();

		M_Pharmacologicalcategory saveData = saveDataphar;

		when(pharmacologicalcategoryRepo.save(saveData)).thenReturn(saveDataphar);

		// Assertions
		Assertions.assertEquals(saveDataphar, pharmacologicalcategoryServiceImpl.saveEditedPharData(saveData));

	}

}
