package com.iemr.inventory.service.uom;

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

import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.repo.uom.UomRepo;

@ExtendWith(MockitoExtension.class)
class UomServiceImplTest {

	@InjectMocks
	UomServiceImpl uomServiceImpl;

	@Mock
	private UomRepo uomRepo;

	@Test
	public void createDrugtypeDataTest() {

		M_Uom m_Uom = new M_Uom();

		m_Uom.setuOMID(13);
		m_Uom.setuOMName("UOMN");
		m_Uom.setuOMDesc("Used for umon purpose");
		m_Uom.setuOMCode("U888M999N000N");
		m_Uom.setStatus("Ready");
		m_Uom.setProviderServiceMapID(3);
		m_Uom.setCreatedBy(null);
		m_Uom.setCreatedBy("P Medical Company");
		m_Uom.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Uom.setModifiedBy("C Medical Company");
		m_Uom.setLastModDate(Date.valueOf("2001-09-09"));
		m_Uom.toString();

		ArrayList<M_Uom> saveData = new ArrayList<M_Uom>();
		saveData.add(m_Uom);

		List<M_Uom> saveUomData = new ArrayList<M_Uom>();

		saveUomData = saveData;

		when(uomRepo.saveAll(saveUomData)).thenReturn(saveData);

		// Assertions
		Assertions.assertNotEquals(0, saveData.size());
		Assertions.assertEquals(saveData, uomServiceImpl.createDrugtypeData(saveUomData));

	}

	@Test
	public void createDrugtypeDataTestAsNull() {

		List<M_Uom> saveUomData = new ArrayList<M_Uom>();

		// Assertions
		Assertions.assertEquals(null, uomServiceImpl.createDrugtypeData(saveUomData));
	}

	@Test
	public void createDrugtypeDataTest2() {

		Integer providerServiceMapID = Integer.valueOf(3);

		M_Uom m_Uom = new M_Uom();

		m_Uom.setuOMID(13);
		m_Uom.setuOMName("UOMN");
		m_Uom.setuOMDesc("Used for umon purpose");
		m_Uom.setuOMCode("U888M999N000N");
		m_Uom.setStatus("Ready");
		m_Uom.setProviderServiceMapID(3);
		m_Uom.setCreatedBy(null);
		m_Uom.setCreatedBy("P Medical Company");
		m_Uom.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Uom.setModifiedBy("C Medical Company");
		m_Uom.setLastModDate(Date.valueOf("2001-09-09"));
		m_Uom.toString();

		ArrayList<M_Uom> getUomData = new ArrayList<M_Uom>();

		getUomData.add(m_Uom);

		when(uomRepo.getUom(providerServiceMapID)).thenReturn(getUomData);

		// Assertions
		Assertions.assertNotEquals(0, getUomData.size());
		Assertions.assertEquals(getUomData, uomServiceImpl.createDrugtypeData(providerServiceMapID));

	}

	@Test
	public void createDrugtypeDataTestAsNull2() {

		Integer providerServiceMapID = null;
		// Assertions
		Assertions.assertEquals(null, uomServiceImpl.createDrugtypeData(providerServiceMapID));
	}

	@Test
	public void editDrugtypeDataTest() {

		Integer uOMID = Integer.valueOf(13);

		M_Uom m_Uom = new M_Uom();

		m_Uom.setuOMID(13);
		m_Uom.setuOMName("UOMN");
		m_Uom.setuOMDesc("Used for umon purpose");
		m_Uom.setuOMCode("U888M999N000N");
		m_Uom.setStatus("Ready");
		m_Uom.setProviderServiceMapID(3);
		m_Uom.setCreatedBy(null);
		m_Uom.setCreatedBy("P Medical Company");
		m_Uom.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Uom.setModifiedBy("C Medical Company");
		m_Uom.setLastModDate(Date.valueOf("2001-09-09"));
		m_Uom.toString();

		M_Uom geteditedData = new M_Uom();

		geteditedData = m_Uom;

		when(uomRepo.geteditedData(uOMID)).thenReturn(geteditedData);

		// Assertions
		Assertions.assertEquals(geteditedData, uomServiceImpl.editDrugtypeData(uOMID));

	}

	@Test
	public void saveeditedData() {

		M_Uom m_Uom = new M_Uom();

		m_Uom.setuOMID(13);
		m_Uom.setuOMName("UOMN");
		m_Uom.setuOMDesc("Used for umon purpose");
		m_Uom.setuOMCode("U888M999N000N");
		m_Uom.setStatus("Ready");
		m_Uom.setProviderServiceMapID(3);
		m_Uom.setCreatedBy(null);
		m_Uom.setCreatedBy("P Medical Company");
		m_Uom.setCreatedDate(Date.valueOf("2000-08-08"));
		m_Uom.setModifiedBy("C Medical Company");
		m_Uom.setLastModDate(Date.valueOf("2001-09-09"));
		m_Uom.toString();

		M_Uom geteditedData = new M_Uom();

		geteditedData = m_Uom;

		M_Uom saveeditedData = geteditedData;

		when(uomRepo.save(geteditedData)).thenReturn(saveeditedData);

		// Assertions
		Assertions.assertEquals(saveeditedData, uomServiceImpl.saveeditedData(geteditedData));

	}

}
