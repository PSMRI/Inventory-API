package com.iemr.inventory.service.manufacturer;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.repo.manufacturer.ManufacturerRepo;

@ExtendWith(MockitoExtension.class)
class ManufacturerServiceImplTest {

	@InjectMocks
	ManufacturerServiceImpl manufacturerServiceImpl;

	@Mock
	private ManufacturerRepo manufacturerRepo;

	@Test
	public void createManufacturerListNotNullTest() {

		M_Manufacturer m_Manufacturer = new M_Manufacturer();

		m_Manufacturer.setManufacturerID(12);
		m_Manufacturer.setManufacturerName("Piramal PSMRI");
		m_Manufacturer.setManufacturerDesc("PSMRI Desc");
		m_Manufacturer.setStatus("Copyright Company CMMI 3");
		m_Manufacturer.setContactPerson("Mithun");
		m_Manufacturer.setcST_GST_No("143467374dfgsfhdghsgf876978");
		m_Manufacturer.setProviderServiceMapID(13);
		m_Manufacturer.setDeleted(false);
		m_Manufacturer.setCreatedBy("Piramal Sen");
		m_Manufacturer.setCreatedDate(Date.valueOf("1940-09-09"));
		m_Manufacturer.setModifiedBy("Piramal Sen");
		m_Manufacturer.setLastModDate(Date.valueOf("1990-09-09"));

		m_Manufacturer.toString();

		ArrayList<M_Manufacturer> createData = new ArrayList<M_Manufacturer>();

		createData.add(m_Manufacturer);

		ArrayList<M_Manufacturer> manufacturerData = createData;

		when(manufacturerRepo.saveAll(manufacturerData)).thenReturn(manufacturerData);

		// Assertions
		Assertions.assertNotEquals(0, createData.size());
		Assertions.assertEquals(createData, manufacturerServiceImpl.createManufacturer(manufacturerData));

	}

	@Test
	public void createManufacturerListAsNullTest() {

		M_Manufacturer m_Manufacturer = new M_Manufacturer();

		ArrayList<M_Manufacturer> createData = new ArrayList<M_Manufacturer>();

		ArrayList<M_Manufacturer> manufacturerData = createData;

		when(manufacturerRepo.saveAll(manufacturerData)).thenReturn(manufacturerData);

		// Assertions
		Assertions.assertEquals(0, createData.size());
		Assertions.assertEquals(null, manufacturerServiceImpl.createManufacturer(manufacturerData));

	}

	@Test
	public void createManufacturerDataNotNullTest() {

		Integer providerServiceMapID = Integer.valueOf(3);

		M_Manufacturer m_Manufacturer = new M_Manufacturer();

		m_Manufacturer.setProviderServiceMapID(providerServiceMapID);

		m_Manufacturer.toString();

		ArrayList<M_Manufacturer> getData = new ArrayList<M_Manufacturer>();

		getData.add(m_Manufacturer);

		when(manufacturerRepo.getManufacturerData(providerServiceMapID)).thenReturn(getData);

		// Assertions
		Assertions.assertNotEquals(0, getData.size());
		Assertions.assertEquals(getData, manufacturerServiceImpl.createManufacturer(providerServiceMapID));

	}

	@Test
	public void createManufacturerDataAsNullTest() {

		Integer providerServiceMapID = Integer.valueOf(3);

		M_Manufacturer m_Manufacturer = new M_Manufacturer();

		ArrayList<M_Manufacturer> getData = new ArrayList<M_Manufacturer>();

		when(manufacturerRepo.getManufacturerData(providerServiceMapID)).thenReturn(getData);

		// Assertions
		Assertions.assertEquals(0, getData.size());
		Assertions.assertEquals(null, manufacturerServiceImpl.createManufacturer(providerServiceMapID));

	}

	@Test
	public void editManufacturerTest() {

		Integer manufacturerID = Integer.valueOf(6);

		M_Manufacturer edit = new M_Manufacturer();

		edit.setManufacturerID(manufacturerID);

		edit.toString();

		when(manufacturerRepo.getEditData(manufacturerID)).thenReturn(edit);

		// Assertions
		Assertions.assertEquals(edit, manufacturerServiceImpl.editManufacturer(manufacturerID));

	}

	@Test
	public void saveEditedDataTest() {

		M_Manufacturer editData = new M_Manufacturer();

		editData.setManufacturerID(13);
		editData.setManufacturerName("Piramal PSMRI");
		editData.setManufacturerDesc("PSMRI Desc2");
		editData.setStatus("Copyright Company CMMI 3");
		editData.setContactPerson("Mithun");
		editData.setcST_GST_No("634873653hdgfhdsgh67456");
		editData.setProviderServiceMapID(14);
		editData.setDeleted(false);
		editData.setCreatedBy("Piramal Sen");
		editData.setCreatedDate(Date.valueOf("1940-09-09"));
		editData.setModifiedBy("Piramal Sen");
		editData.setLastModDate(Date.valueOf("1990-09-09"));

		editData.toString();

		M_Manufacturer data = editData;

		when(manufacturerRepo.save(editData)).thenReturn(data);

		// Assertions
		Assertions.assertEquals(data, manufacturerServiceImpl.saveEditedData(editData));

	}
}
