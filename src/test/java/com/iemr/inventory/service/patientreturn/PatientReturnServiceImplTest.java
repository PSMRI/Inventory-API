package com.iemr.inventory.service.patientreturn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.inventory.data.patientreturn.ItemDetailModel;
import com.iemr.inventory.data.patientreturn.PatientReturnModel;
import com.iemr.inventory.data.patientreturn.ReturnHistoryModel;
import com.iemr.inventory.data.stockExit.ItemReturnEntry;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.repository.patientreturn.ItemReturnEntryRepo;
import com.iemr.inventory.repository.patientreturn.PatientReturnRepo;
import com.iemr.inventory.utils.mapper.OutputMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatientReturnServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PatientReturnServiceImplTest {
    @MockBean
    private ItemReturnEntryRepo itemReturnEntryRepo;

    @MockBean
    private PatientReturnRepo patientReturnRepo;

    @Autowired
    private PatientReturnServiceImpl patientReturnServiceImpl;

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#getItemNameByRegID(T_PatientIssue)}
     */
    @Test
    void testGetItemNameByRegID() {
        // Arrange
        when(patientReturnRepo.getItemNameByRegID(Mockito.<Long>any(), Mockito.<Integer>any(), Mockito.<Timestamp>any()))
                .thenReturn(new ArrayList<>());

        T_PatientIssue patientIssue = new T_PatientIssue();
        patientIssue.setAge(1);
        patientIssue.setBenRegID(1L);
        patientIssue.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        patientIssue.setCreatedDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        patientIssue.setDeleted(true);
        patientIssue.setDoctorName("Doctor Name");
        patientIssue.setFacilityID(1);
        patientIssue.setGender("Gender");
        patientIssue.setIssueType("Issue Type");
        patientIssue.setIssuedBy("Issued By");
        patientIssue.setItemStockExit(new ArrayList<>());
        patientIssue.setItemStockExitMap(new ArrayList<>());
        patientIssue.setLastModDate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        patientIssue.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        patientIssue.setOutputMapper(new OutputMapper());
        patientIssue.setParkingPlaceID(1L);
        patientIssue.setPatientIssueID(1L);
        patientIssue.setPatientName("Patient Name");
        patientIssue.setPrescriptionID(1);
        patientIssue.setProcessed('A');
        patientIssue.setProviderServiceMapID(1);
        patientIssue.setReference("Reference");
        patientIssue.setSyncFacilityID(1);
        patientIssue.setVanID(1L);
        patientIssue.setVanSerialNo(1L);
        patientIssue.setVisitCode(1L);
        patientIssue.setVisitID(1);

        // Act
        List<PatientReturnModel> actualItemNameByRegID = patientReturnServiceImpl.getItemNameByRegID(patientIssue);

        // Assert
        verify(patientReturnRepo).getItemNameByRegID(Mockito.<Long>any(), Mockito.<Integer>any(), Mockito.<Timestamp>any());
        assertTrue(actualItemNameByRegID.isEmpty());
    }

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#getItemDetailByBen(ItemDetailModel)}
     */
    @Test
    void testGetItemDetailByBen() {
        // Arrange
        when(patientReturnRepo.getItemDetailByBen(Mockito.<Long>any(), Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(new ArrayList<>());

        // Act
        List<ItemDetailModel> actualItemDetailByBen = patientReturnServiceImpl.getItemDetailByBen(new ItemDetailModel(1,
                "Item Name", "Batch No", 1, mock(Timestamp.class), true, true, 1L, 1L, 1L, 1L, 1L, 1, 1));

        // Assert
        verify(patientReturnRepo).getItemDetailByBen(Mockito.<Long>any(), Mockito.<Integer>any(), Mockito.<Integer>any());
        assertTrue(actualItemDetailByBen.isEmpty());
    }

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#getItemDetailByBen(ItemDetailModel)}
     */
    @Test
    void testGetItemDetailByBen2() {
        // Arrange
        when(patientReturnRepo.getItemDetailByBen(Mockito.<Long>any(), Mockito.<Integer>any(), Mockito.<Integer>any()))
                .thenReturn(new ArrayList<>());
        ItemDetailModel itemDetailModel = mock(ItemDetailModel.class);
        when(itemDetailModel.getFacilityID()).thenReturn(1);
        when(itemDetailModel.getItemID()).thenReturn(1);
        when(itemDetailModel.getBenRegID()).thenReturn(1L);

        // Act
        List<ItemDetailModel> actualItemDetailByBen = patientReturnServiceImpl.getItemDetailByBen(itemDetailModel);

        // Assert
        verify(itemDetailModel).getBenRegID();
        verify(itemDetailModel).getFacilityID();
        verify(itemDetailModel).getItemID();
        verify(patientReturnRepo).getItemDetailByBen(Mockito.<Long>any(), Mockito.<Integer>any(), Mockito.<Integer>any());
        assertTrue(actualItemDetailByBen.isEmpty());
    }

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#updateQuantityReturned(ItemDetailModel[])}
     */
    @Test
    void testUpdateQuantityReturned() {
        // Arrange
        when(itemReturnEntryRepo.saveAll(Mockito.<Iterable<ItemReturnEntry>>any())).thenReturn(new ArrayList<>());
        when(patientReturnRepo.updateIssuedQuantity(Mockito.<Integer>any(), Mockito.<Long>any())).thenReturn(1);
        when(patientReturnRepo.updateQuantityReturned(Mockito.<Integer>any(), Mockito.<Long>any())).thenReturn(1);

        // Act
        String actualUpdateQuantityReturnedResult = patientReturnServiceImpl
                .updateQuantityReturned(new ItemDetailModel[]{new ItemDetailModel(1, "Item Name", "Batch No", 1,
                        mock(Timestamp.class), true, true, 1L, 1L, 1L, 1L, 1L, 1, 1)});

        // Assert
        verify(patientReturnRepo).updateIssuedQuantity(Mockito.<Integer>any(), Mockito.<Long>any());
        verify(patientReturnRepo).updateQuantityReturned(Mockito.<Integer>any(), Mockito.<Long>any());
        verify(itemReturnEntryRepo).saveAll(Mockito.<Iterable<ItemReturnEntry>>any());
        assertEquals("Quantity updated successfully", actualUpdateQuantityReturnedResult);
    }

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#updateQuantityReturned(ItemDetailModel[])}
     */
    @Test
    void testUpdateQuantityReturned2() {
        // Arrange
        when(itemReturnEntryRepo.saveAll(Mockito.<Iterable<ItemReturnEntry>>any())).thenReturn(new ArrayList<>());
        when(patientReturnRepo.updateIssuedQuantity(Mockito.<Integer>any(), Mockito.<Long>any())).thenReturn(1);
        when(patientReturnRepo.updateQuantityReturned(Mockito.<Integer>any(), Mockito.<Long>any())).thenReturn(1);
        ItemDetailModel itemDetailModel = mock(ItemDetailModel.class);
        when(itemDetailModel.getFacilityID()).thenReturn(1);
        when(itemDetailModel.getProviderServiceMapID()).thenReturn(1);
        when(itemDetailModel.getReturnQuantity()).thenReturn(1);
        when(itemDetailModel.getBenRegID()).thenReturn(1L);
        when(itemDetailModel.getItemStockEntryID()).thenReturn(1L);
        when(itemDetailModel.getItemStockExitID()).thenReturn(1L);
        when(itemDetailModel.getVisitCode()).thenReturn(1L);
        when(itemDetailModel.getVisitID()).thenReturn(1L);
        when(itemDetailModel.getCreatedBy()).thenReturn("Jan 1, 2020 8:00am GMT+0100");

        // Act
        String actualUpdateQuantityReturnedResult = patientReturnServiceImpl
                .updateQuantityReturned(new ItemDetailModel[]{itemDetailModel});

        // Assert
        verify(itemDetailModel).getBenRegID();
        verify(itemDetailModel).getCreatedBy();
        verify(itemDetailModel).getFacilityID();
        verify(itemDetailModel).getItemStockEntryID();
        verify(itemDetailModel, atLeast(1)).getItemStockExitID();
        verify(itemDetailModel).getProviderServiceMapID();
        verify(itemDetailModel, atLeast(1)).getReturnQuantity();
        verify(itemDetailModel).getVisitCode();
        verify(itemDetailModel).getVisitID();
        verify(patientReturnRepo).updateIssuedQuantity(Mockito.<Integer>any(), Mockito.<Long>any());
        verify(patientReturnRepo).updateQuantityReturned(Mockito.<Integer>any(), Mockito.<Long>any());
        verify(itemReturnEntryRepo).saveAll(Mockito.<Iterable<ItemReturnEntry>>any());
        assertEquals("Quantity updated successfully", actualUpdateQuantityReturnedResult);
    }

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#getBenReturnHistory(ItemReturnEntry)}
     */
    @Test
    void testGetBenReturnHistory() {
        // Arrange
        when(patientReturnRepo.getBenReturnHistory(Mockito.<Integer>any(), Mockito.<Timestamp>any(),
                Mockito.<Timestamp>any())).thenReturn(new ArrayList<>());

        ItemReturnEntry itemReturnEntry = new ItemReturnEntry();
        itemReturnEntry.setBenRegID(1L);
        itemReturnEntry.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemReturnEntry.setCreatedDate(mock(Timestamp.class));
        itemReturnEntry.setDeleted(true);
        itemReturnEntry.setFacilityID(1);
        itemReturnEntry.setFromDate(mock(Timestamp.class));
        itemReturnEntry.setItemStockExitID(1L);
        itemReturnEntry.setLastModDate(mock(Timestamp.class));
        itemReturnEntry.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemReturnEntry.setOutputMapper(new OutputMapper());
        itemReturnEntry.setPatientReturnID(1L);
        itemReturnEntry.setProviderServiceMapID(1);
        itemReturnEntry.setToDate(mock(Timestamp.class));
        itemReturnEntry.setVisitCode(1L);
        itemReturnEntry.setVisitID(1L);

        // Act
        List<ReturnHistoryModel> actualBenReturnHistory = patientReturnServiceImpl.getBenReturnHistory(itemReturnEntry);

        // Assert
        verify(patientReturnRepo).getBenReturnHistory(Mockito.<Integer>any(), Mockito.<Timestamp>any(),
                Mockito.<Timestamp>any());
        assertTrue(actualBenReturnHistory.isEmpty());
    }

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#getBenReturnHistory(ItemReturnEntry)}
     */
    @Test
    void testGetBenReturnHistory2() {
        // Arrange
        when(patientReturnRepo.getBenReturnHistory(Mockito.<Integer>any(), Mockito.<Timestamp>any(),
                Mockito.<Timestamp>any())).thenReturn(new ArrayList<>());
        ItemReturnEntry itemReturnEntry = mock(ItemReturnEntry.class);
        when(itemReturnEntry.getFacilityID()).thenReturn(1);
        when(itemReturnEntry.getFromDate()).thenReturn(mock(Timestamp.class));
        when(itemReturnEntry.getToDate()).thenReturn(mock(Timestamp.class));
        doNothing().when(itemReturnEntry).setBenRegID(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setCreatedBy(Mockito.<String>any());
        doNothing().when(itemReturnEntry).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setDeleted(Mockito.<Boolean>any());
        doNothing().when(itemReturnEntry).setFacilityID(Mockito.<Integer>any());
        doNothing().when(itemReturnEntry).setFromDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setItemStockExitID(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setModifiedBy(Mockito.<String>any());
        doNothing().when(itemReturnEntry).setOutputMapper(Mockito.<OutputMapper>any());
        doNothing().when(itemReturnEntry).setPatientReturnID(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setProviderServiceMapID(Mockito.<Integer>any());
        doNothing().when(itemReturnEntry).setToDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setVisitCode(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setVisitID(Mockito.<Long>any());
        itemReturnEntry.setBenRegID(1L);
        itemReturnEntry.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemReturnEntry.setCreatedDate(mock(Timestamp.class));
        itemReturnEntry.setDeleted(true);
        itemReturnEntry.setFacilityID(1);
        itemReturnEntry.setFromDate(mock(Timestamp.class));
        itemReturnEntry.setItemStockExitID(1L);
        itemReturnEntry.setLastModDate(mock(Timestamp.class));
        itemReturnEntry.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemReturnEntry.setOutputMapper(new OutputMapper());
        itemReturnEntry.setPatientReturnID(1L);
        itemReturnEntry.setProviderServiceMapID(1);
        itemReturnEntry.setToDate(mock(Timestamp.class));
        itemReturnEntry.setVisitCode(1L);
        itemReturnEntry.setVisitID(1L);

        // Act
        List<ReturnHistoryModel> actualBenReturnHistory = patientReturnServiceImpl.getBenReturnHistory(itemReturnEntry);

        // Assert
        verify(itemReturnEntry).getFacilityID();
        verify(itemReturnEntry).getFromDate();
        verify(itemReturnEntry).getToDate();
        verify(itemReturnEntry).setBenRegID(Mockito.<Long>any());
        verify(itemReturnEntry).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(itemReturnEntry).setCreatedDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setDeleted(Mockito.<Boolean>any());
        verify(itemReturnEntry).setFacilityID(Mockito.<Integer>any());
        verify(itemReturnEntry).setFromDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setItemStockExitID(Mockito.<Long>any());
        verify(itemReturnEntry).setLastModDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(itemReturnEntry).setOutputMapper(Mockito.<OutputMapper>any());
        verify(itemReturnEntry).setPatientReturnID(Mockito.<Long>any());
        verify(itemReturnEntry).setProviderServiceMapID(Mockito.<Integer>any());
        verify(itemReturnEntry).setToDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setVisitCode(Mockito.<Long>any());
        verify(itemReturnEntry).setVisitID(Mockito.<Long>any());
        verify(patientReturnRepo).getBenReturnHistory(Mockito.<Integer>any(), Mockito.<Timestamp>any(),
                Mockito.<Timestamp>any());
        assertTrue(actualBenReturnHistory.isEmpty());
    }

    /**
     * Method under test:
     * {@link PatientReturnServiceImpl#getBenReturnHistory(ItemReturnEntry)}
     */
    @Test
    void testGetBenReturnHistory3() {
        // Arrange
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        objectArrayList.add(new Object[]{});
        when(patientReturnRepo.getBenReturnHistory(Mockito.<Integer>any(), Mockito.<Timestamp>any(),
                Mockito.<Timestamp>any())).thenReturn(objectArrayList);
        ItemReturnEntry itemReturnEntry = mock(ItemReturnEntry.class);
        when(itemReturnEntry.getFacilityID()).thenReturn(1);
        when(itemReturnEntry.getFromDate()).thenReturn(mock(Timestamp.class));
        when(itemReturnEntry.getToDate()).thenReturn(mock(Timestamp.class));
        doNothing().when(itemReturnEntry).setBenRegID(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setCreatedBy(Mockito.<String>any());
        doNothing().when(itemReturnEntry).setCreatedDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setDeleted(Mockito.<Boolean>any());
        doNothing().when(itemReturnEntry).setFacilityID(Mockito.<Integer>any());
        doNothing().when(itemReturnEntry).setFromDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setItemStockExitID(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setLastModDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setModifiedBy(Mockito.<String>any());
        doNothing().when(itemReturnEntry).setOutputMapper(Mockito.<OutputMapper>any());
        doNothing().when(itemReturnEntry).setPatientReturnID(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setProviderServiceMapID(Mockito.<Integer>any());
        doNothing().when(itemReturnEntry).setToDate(Mockito.<Timestamp>any());
        doNothing().when(itemReturnEntry).setVisitCode(Mockito.<Long>any());
        doNothing().when(itemReturnEntry).setVisitID(Mockito.<Long>any());
        itemReturnEntry.setBenRegID(1L);
        itemReturnEntry.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemReturnEntry.setCreatedDate(mock(Timestamp.class));
        itemReturnEntry.setDeleted(true);
        itemReturnEntry.setFacilityID(1);
        itemReturnEntry.setFromDate(mock(Timestamp.class));
        itemReturnEntry.setItemStockExitID(1L);
        itemReturnEntry.setLastModDate(mock(Timestamp.class));
        itemReturnEntry.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        itemReturnEntry.setOutputMapper(new OutputMapper());
        itemReturnEntry.setPatientReturnID(1L);
        itemReturnEntry.setProviderServiceMapID(1);
        itemReturnEntry.setToDate(mock(Timestamp.class));
        itemReturnEntry.setVisitCode(1L);
        itemReturnEntry.setVisitID(1L);

        // Act
        List<ReturnHistoryModel> actualBenReturnHistory = patientReturnServiceImpl.getBenReturnHistory(itemReturnEntry);

        // Assert
        verify(itemReturnEntry).getFacilityID();
        verify(itemReturnEntry).getFromDate();
        verify(itemReturnEntry).getToDate();
        verify(itemReturnEntry).setBenRegID(Mockito.<Long>any());
        verify(itemReturnEntry).setCreatedBy(eq("Jan 1, 2020 8:00am GMT+0100"));
        verify(itemReturnEntry).setCreatedDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setDeleted(Mockito.<Boolean>any());
        verify(itemReturnEntry).setFacilityID(Mockito.<Integer>any());
        verify(itemReturnEntry).setFromDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setItemStockExitID(Mockito.<Long>any());
        verify(itemReturnEntry).setLastModDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setModifiedBy(eq("Jan 1, 2020 9:00am GMT+0100"));
        verify(itemReturnEntry).setOutputMapper(Mockito.<OutputMapper>any());
        verify(itemReturnEntry).setPatientReturnID(Mockito.<Long>any());
        verify(itemReturnEntry).setProviderServiceMapID(Mockito.<Integer>any());
        verify(itemReturnEntry).setToDate(Mockito.<Timestamp>any());
        verify(itemReturnEntry).setVisitCode(Mockito.<Long>any());
        verify(itemReturnEntry).setVisitID(Mockito.<Long>any());
        verify(patientReturnRepo).getBenReturnHistory(Mockito.<Integer>any(), Mockito.<Timestamp>any(),
                Mockito.<Timestamp>any());
        assertTrue(actualBenReturnHistory.isEmpty());
    }
}
