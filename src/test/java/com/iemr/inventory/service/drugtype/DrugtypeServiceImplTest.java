package com.iemr.inventory.service.drugtype;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.drugtype.M_Drugtype;
import com.iemr.inventory.repo.drugtype.DrugtypeRepo;

@ExtendWith(MockitoExtension.class)
class DrugtypeServiceImplTest {

	@InjectMocks
	DrugtypeServiceImpl drugtypeServiceImpl;

	@Mock
	DrugtypeRepo drugtypeRepo;

	@Test
	public void createDrugtypeDataTest() {
		M_Drugtype m_Drugtype = new M_Drugtype();
		m_Drugtype.setDrugTypeID(3);
		m_Drugtype.setDrugTypeName("Pregency");
		m_Drugtype.setDrugTypeDesc("Pregenancy");
		m_Drugtype.setDrugTypeCode("1048476E");
		m_Drugtype.setStatus("Ready to use & Not expired");
		m_Drugtype.setProviderServiceMapID(4);
		m_Drugtype.setDeleted(false);
		m_Drugtype.setCreatedBy("Dr Chandrani Mallik");
		m_Drugtype.setCreatedDate(Date.valueOf("2024-01-01"));
		m_Drugtype.setModifiedBy("Dr Chandrani Mallik");
		m_Drugtype.setLastModDate(Date.valueOf("2024-01-15"));

		m_Drugtype.toString();

		ArrayList<M_Drugtype> savedData = new ArrayList<M_Drugtype>();

		savedData.add(m_Drugtype);

		when(drugtypeRepo.saveAll(savedData)).thenReturn(savedData);

		// Assertions
		Assertions.assertNotEquals(0, savedData.size());
		Assertions.assertEquals(savedData, drugtypeServiceImpl.createDrugtypeData(savedData));

	}

	@Test
	public void createDrugtypeDataTestAsNull() {

		ArrayList<M_Drugtype> savedData = new ArrayList<M_Drugtype>();

		when(drugtypeRepo.saveAll(savedData)).thenReturn(savedData);

		// Assertions
		Assertions.assertEquals(0, savedData.size());
		Assertions.assertEquals(null, drugtypeServiceImpl.createDrugtypeData(savedData));
	}

	@Test
	public void getDrugtypeDataTest() {
		M_Drugtype m_Drugtype = new M_Drugtype();
		m_Drugtype.setDrugTypeID(3);
		m_Drugtype.setDrugTypeName("Pregency");
		m_Drugtype.setDrugTypeDesc("Pregenancy");
		m_Drugtype.setDrugTypeCode("1048476E");
		m_Drugtype.setStatus("Ready to use & Not expired");
		m_Drugtype.setProviderServiceMapID(4);
		m_Drugtype.setDeleted(false);
		m_Drugtype.setCreatedBy("Dr Chandrani Mallik");
		m_Drugtype.setCreatedDate(Date.valueOf("2024-01-01"));
		m_Drugtype.setModifiedBy("Dr Chandrani Mallik");
		m_Drugtype.setLastModDate(Date.valueOf("2024-01-15"));

		m_Drugtype.toString();

		Integer providerServiceMapID = Integer.valueOf(5);

		ArrayList<M_Drugtype> getDrugtypeData = new ArrayList<M_Drugtype>();

		getDrugtypeData.add(m_Drugtype);

		when(drugtypeRepo.getDrugtypeData(providerServiceMapID)).thenReturn(getDrugtypeData);

		// Assertions
		Assertions.assertNotEquals(0, getDrugtypeData.size());
		Assertions.assertEquals(getDrugtypeData, drugtypeServiceImpl.getDrugtypeData(providerServiceMapID));

	}

	@Test
	public void getDrugtypeDataTestAsNull() {

		Integer providerServiceMapID = Integer.valueOf(5);

		ArrayList<M_Drugtype> getDrugtypeData = new ArrayList<M_Drugtype>();

		when(drugtypeRepo.getDrugtypeData(providerServiceMapID)).thenReturn(getDrugtypeData);

		// Assertions
		Assertions.assertEquals(0, getDrugtypeData.size());
		Assertions.assertEquals(null, drugtypeServiceImpl.getDrugtypeData(providerServiceMapID));
	}

	@Test
	public void editDrugtypeDataTest() {

		Integer drugTypeID = Integer.valueOf(6);

		M_Drugtype sendeditedData = new M_Drugtype();

		sendeditedData.setDrugTypeID(drugTypeID);

		sendeditedData.toString();

		when(drugtypeRepo.geteditedData(drugTypeID)).thenReturn(sendeditedData);

		// Assertions
		Assertions.assertEquals(sendeditedData, drugtypeServiceImpl.editDrugtypeData(drugTypeID));

	}

	@Test
	public void saveeditDrugtypeTest() {

		Integer drugTypeID = Integer.valueOf(6);

		M_Drugtype geteditedData = new M_Drugtype();

		geteditedData.setDrugTypeID(drugTypeID);

		geteditedData.toString();

		M_Drugtype updateeditedData = geteditedData;

		when(drugtypeRepo.save(geteditedData)).thenReturn(updateeditedData);

		// Assertions
		Assertions.assertEquals(updateeditedData, drugtypeServiceImpl.saveeditDrugtype(geteditedData));

	}
}
