package com.iemr.inventory.service.facilitytype;

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

import com.iemr.inventory.data.facilitytype.M_facilitytype;
import com.iemr.inventory.repository.facilitytype.M_facilitytypeRepo;

@ExtendWith(MockitoExtension.class)
class M_facilitytypeServiceImplTest {

	@InjectMocks
	M_facilitytypeServiceImpl m_facilitytypeServiceImpl;

	@Mock
	M_facilitytypeRepo m_facilitytypeRepo;

	@Test
	public void getAllFicilityDataTest() {

		M_facilitytype m_facilitytype = new M_facilitytype();

		m_facilitytype.setFacilityTypeID(2);
		m_facilitytype.setFacilityTypeName("WBP");
		m_facilitytype.setFacilityTypeDesc("WBP as DESC");
		m_facilitytype.setFacilityTypeCode("1367367ER");
		m_facilitytype.setStatus("Approved");
		m_facilitytype.setProviderServiceMapID(123);
		m_facilitytype.setDeleted(false);
		m_facilitytype.setCreatedBy("Dr Adil Adnan");
		m_facilitytype.setModifiedBy("Dr Adil Adnan");
		m_facilitytype.setCreatedDate(Date.valueOf("2024-01-01"));
		m_facilitytype.setLastModDate(Date.valueOf("2024-01-22"));

		m_facilitytype.toString();

		ArrayList<M_facilitytype> data = new ArrayList<M_facilitytype>();

		data.add(m_facilitytype);

		Integer providerServiceMapID = Integer.valueOf(123);

		when(m_facilitytypeRepo.getAllFicilityData(providerServiceMapID)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_facilitytypeServiceImpl.getAllFicilityData(providerServiceMapID));

	}

	@Test
	public void addAllFicilityDataTest() {

		List<M_facilitytype> addfacilityDetails = new ArrayList<M_facilitytype>();

		M_facilitytype m_facilitytype = new M_facilitytype();

		m_facilitytype.setFacilityTypeID(2);
		m_facilitytype.setFacilityTypeName("WBP");
		m_facilitytype.setFacilityTypeDesc("WBP as DESC");
		m_facilitytype.setFacilityTypeCode("1367367ER");
		m_facilitytype.setStatus("Approved");
		m_facilitytype.setProviderServiceMapID(123);
		m_facilitytype.setDeleted(false);
		m_facilitytype.setCreatedBy("Dr Adil Adnan");
		m_facilitytype.setModifiedBy("Dr Adil Adnan");
		m_facilitytype.setCreatedDate(Date.valueOf("2024-01-01"));
		m_facilitytype.setLastModDate(Date.valueOf("2024-01-22"));

		m_facilitytype.toString();

		addfacilityDetails.add(m_facilitytype);

		ArrayList<M_facilitytype> data = new ArrayList<M_facilitytype>();

		data.addAll(addfacilityDetails);

		when(m_facilitytypeRepo.saveAll(addfacilityDetails)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_facilitytypeServiceImpl.addAllFicilityData(addfacilityDetails));

	}

	@Test
	public void editAllFicilityDataTest() {

		Integer facilityTypeID = Integer.valueOf(10);

		M_facilitytype data = new M_facilitytype();

		data.setFacilityTypeID(facilityTypeID);

		data.toString();

		when(m_facilitytypeRepo.findByFacilityTypeID(facilityTypeID)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_facilitytypeServiceImpl.editAllFicilityData(facilityTypeID));

	}

	@Test
	public void updateFacilityDataTest() {

		M_facilitytype allFacilityData = new M_facilitytype();

		allFacilityData.setFacilityTypeID(20);
		allFacilityData.setFacilityTypeName("WBP1");
		allFacilityData.setFacilityTypeDesc("WBP as DESC1");
		allFacilityData.setFacilityTypeCode("1367367ERS");
		allFacilityData.setStatus("Approved");
		allFacilityData.setProviderServiceMapID(123456);
		allFacilityData.setDeleted(false);
		allFacilityData.setCreatedBy("Dr Ranjit Mondal");
		allFacilityData.setModifiedBy("Dr Ranjit Mondal");
		allFacilityData.setCreatedDate(Date.valueOf("2024-01-02"));
		allFacilityData.setLastModDate(Date.valueOf("2024-01-23"));

		allFacilityData.toString();

		M_facilitytype data = allFacilityData;

		when(m_facilitytypeRepo.save(allFacilityData)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, m_facilitytypeServiceImpl.updateFacilityData(allFacilityData));

	}
}
