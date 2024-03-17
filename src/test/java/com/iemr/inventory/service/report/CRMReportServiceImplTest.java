package com.iemr.inventory.service.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.mapper.report.InventoryReportMapper;
import com.iemr.inventory.repo.report.ItemStockReportRepo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CRMReportServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CRMReportServiceImplTest {
    @Autowired
    private CRMReportServiceImpl cRMReportServiceImpl;

    @MockBean
    private InventoryReportMapper inventoryReportMapper;

    @MockBean
    private ItemStockReportRepo itemStockReportRepo;

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getInwardStockReport(ItemStockEntryReport)}
     */
    @Test
    void testGetInwardStockReport() {
        // Arrange
        when(itemStockReportRepo.getItemStockEntryReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(mock(Timestamp.class));
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualInwardStockReport = cRMReportServiceImpl.getInwardStockReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getItemStockEntryReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any());
        assertEquals("[]", actualInwardStockReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getExpiryReport(ItemStockEntryReport)}
     */
    @Test
    void testGetExpiryReport() {
        // Arrange
        when(itemStockReportRepo.getExpiryReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        Timestamp endDate = mock(Timestamp.class);
        when(endDate.getTime()).thenReturn(10L);
        Timestamp startDate = mock(Timestamp.class);
        when(startDate.getTime()).thenReturn(10L);

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(endDate);
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(startDate);
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualExpiryReport = cRMReportServiceImpl.getExpiryReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getExpiryReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any());
        verify(endDate).getTime();
        verify(startDate).getTime();
        assertEquals("[]", actualExpiryReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getConsumptionReport(ItemStockExitReport)}
     */
    @Test
    void testGetConsumptionReport() {
        // Arrange
        when(itemStockReportRepo.getItemStockExitReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        ItemStockExitReport exitReport = new ItemStockExitReport();
        exitReport.setBatchNo("Batch No");
        exitReport.setCreatedDate(mock(Timestamp.class));
        exitReport.setEndDate(mock(Timestamp.class));
        exitReport.setExitType("Exit Type");
        exitReport.setExpiryDate(mock(Date.class));
        exitReport.setFacilityID(1);
        exitReport.setFacilityName("Facility Name");
        exitReport.setFactItemStockExitID(1L);
        exitReport.setItemCategoryName("Item Category Name");
        exitReport.setItemID(1);
        exitReport.setItemName("Item Name");
        exitReport.setItemStockEntryID(1L);
        exitReport.setItemStockExitID(1L);
        exitReport.setProviderServiceMapID(1);
        exitReport.setQuantity(1);
        exitReport.setStartDate(mock(Timestamp.class));
        exitReport.setUnitCostPrice(10.0d);

        // Act
        String actualConsumptionReport = cRMReportServiceImpl.getConsumptionReport(exitReport);

        // Assert
        verify(itemStockReportRepo).getItemStockExitReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any());
        assertEquals("[]", actualConsumptionReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getConsumptionReport(ItemStockExitReport)}
     */
    @Test
    void testGetConsumptionReport2() {
        // Arrange
        ArrayList<Object[]> objectArrayList = new ArrayList<>();
        objectArrayList.add(new Object[]{"42"});
        when(itemStockReportRepo.getItemStockExitReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any())).thenReturn(objectArrayList);

        ItemStockExitReport exitReport = new ItemStockExitReport();
        exitReport.setBatchNo("Batch No");
        exitReport.setCreatedDate(mock(Timestamp.class));
        exitReport.setEndDate(mock(Timestamp.class));
        exitReport.setExitType("Exit Type");
        exitReport.setExpiryDate(mock(Date.class));
        exitReport.setFacilityID(1);
        exitReport.setFacilityName("Facility Name");
        exitReport.setFactItemStockExitID(1L);
        exitReport.setItemCategoryName("Item Category Name");
        exitReport.setItemID(1);
        exitReport.setItemName("Item Name");
        exitReport.setItemStockEntryID(1L);
        exitReport.setItemStockExitID(1L);
        exitReport.setProviderServiceMapID(1);
        exitReport.setQuantity(1);
        exitReport.setStartDate(mock(Timestamp.class));
        exitReport.setUnitCostPrice(10.0d);

        // Act
        String actualConsumptionReport = cRMReportServiceImpl.getConsumptionReport(exitReport);

        // Assert
        verify(itemStockReportRepo).getItemStockExitReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any());
        assertEquals("[]", actualConsumptionReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getConsumptionReport(ItemStockExitReport)}
     */
    @Test
    void testGetConsumptionReport3() {
        // Arrange
        when(itemStockReportRepo.getItemStockExitReport(Mockito.<Timestamp>any(), Mockito.<Timestamp>any()))
                .thenReturn(new ArrayList<>());

        ItemStockExitReport exitReport = new ItemStockExitReport();
        exitReport.setBatchNo("Batch No");
        exitReport.setCreatedDate(mock(Timestamp.class));
        exitReport.setEndDate(mock(Timestamp.class));
        exitReport.setExitType("Exit Type");
        exitReport.setExpiryDate(mock(Date.class));
        exitReport.setFacilityID(null);
        exitReport.setFacilityName("Facility Name");
        exitReport.setFactItemStockExitID(1L);
        exitReport.setItemCategoryName("Item Category Name");
        exitReport.setItemID(1);
        exitReport.setItemName("Item Name");
        exitReport.setItemStockEntryID(1L);
        exitReport.setItemStockExitID(1L);
        exitReport.setProviderServiceMapID(1);
        exitReport.setQuantity(1);
        exitReport.setStartDate(mock(Timestamp.class));
        exitReport.setUnitCostPrice(10.0d);

        // Act
        String actualConsumptionReport = cRMReportServiceImpl.getConsumptionReport(exitReport);

        // Assert
        verify(itemStockReportRepo).getItemStockExitReport(Mockito.<Timestamp>any(), Mockito.<Timestamp>any());
        assertEquals("[]", actualConsumptionReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getBenDrugIssueReport(PatientIssueExitReport)}
     */
    @Test
    void testGetBenDrugIssueReport() {
        // Arrange
        when(itemStockReportRepo.getPatientIssueExitReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        PatientIssueExitReport exitReport = new PatientIssueExitReport();
        exitReport.setAge(1);
        exitReport.setBatchNo("Batch No");
        exitReport.setCreatedDate(mock(Timestamp.class));
        exitReport.setEndDate(mock(Timestamp.class));
        exitReport.setExpiryDate(mock(Date.class));
        exitReport.setFacilityID(1);
        exitReport.setFactPatientIssueExitID(1L);
        exitReport.setGender("Gender");
        exitReport.setItemCategoryName("Item Category Name");
        exitReport.setItemID(1);
        exitReport.setItemName("Item Name");
        exitReport.setItemStockEntryID(1L);
        exitReport.setItemStockExitID(1L);
        exitReport.setPatientName("Patient Name");
        exitReport.setQuantityGiven(1);
        exitReport.setStartDate(mock(Timestamp.class));
        exitReport.setStrength("Strength");

        // Act
        String actualBenDrugIssueReport = cRMReportServiceImpl.getBenDrugIssueReport(exitReport);

        // Assert
        verify(itemStockReportRepo).getPatientIssueExitReportByFacilityID(Mockito.<Timestamp>any(),
                Mockito.<Timestamp>any(), Mockito.<Integer>any());
        assertEquals("[]", actualBenDrugIssueReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getBenDrugIssueReport(PatientIssueExitReport)}
     */
    @Test
    void testGetBenDrugIssueReport2() {
        // Arrange
        when(itemStockReportRepo.getPatientIssueExitReport(Mockito.<Timestamp>any(), Mockito.<Timestamp>any()))
                .thenReturn(new ArrayList<>());

        PatientIssueExitReport exitReport = new PatientIssueExitReport();
        exitReport.setAge(1);
        exitReport.setBatchNo("Batch No");
        exitReport.setCreatedDate(mock(Timestamp.class));
        exitReport.setEndDate(mock(Timestamp.class));
        exitReport.setExpiryDate(mock(Date.class));
        exitReport.setFacilityID(null);
        exitReport.setFactPatientIssueExitID(1L);
        exitReport.setGender("Gender");
        exitReport.setItemCategoryName("Item Category Name");
        exitReport.setItemID(1);
        exitReport.setItemName("Item Name");
        exitReport.setItemStockEntryID(1L);
        exitReport.setItemStockExitID(1L);
        exitReport.setPatientName("Patient Name");
        exitReport.setQuantityGiven(1);
        exitReport.setStartDate(mock(Timestamp.class));
        exitReport.setStrength("Strength");

        // Act
        String actualBenDrugIssueReport = cRMReportServiceImpl.getBenDrugIssueReport(exitReport);

        // Assert
        verify(itemStockReportRepo).getPatientIssueExitReport(Mockito.<Timestamp>any(), Mockito.<Timestamp>any());
        assertEquals("[]", actualBenDrugIssueReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getDailyStockDetailsReport(ItemStockEntryReport)}
     */
    @Test
    void testGetDailyStockDetailsReport() {
        // Arrange
        when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        Timestamp startDate = mock(Timestamp.class);
        when(startDate.getTime()).thenReturn(10L);

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(startDate);
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualDailyStockDetailsReport = cRMReportServiceImpl.getDailyStockDetailsReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getDailyStockDetailReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any());
        verify(startDate).getTime();
        assertEquals("[]", actualDailyStockDetailsReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getDailyStockSummaryReport(ItemStockEntryReport)}
     */
    @Test
    void testGetDailyStockSummaryReport() {
        // Arrange
        when(itemStockReportRepo.getDailyStockSummaryReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        Timestamp startDate = mock(Timestamp.class);
        when(startDate.getTime()).thenReturn(10L);

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(startDate);
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualDailyStockSummaryReport = cRMReportServiceImpl.getDailyStockSummaryReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getDailyStockSummaryReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any());
        verify(startDate).getTime();
        assertEquals("[]", actualDailyStockSummaryReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getMonthlyReport(ItemStockEntryReport)}
     */
    @Test
    void testGetMonthlyReport() {
        // Arrange
        when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(mock(Timestamp.class));
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualMonthlyReport = cRMReportServiceImpl.getMonthlyReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getDailyStockDetailReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any());
        assertEquals("[]", actualMonthlyReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getYearlyReport(ItemStockEntryReport)}
     */
    @Test
    void testGetYearlyReport() {
        // Arrange
        when(itemStockReportRepo.getDailyStockDetailReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(mock(Timestamp.class));
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualYearlyReport = cRMReportServiceImpl.getYearlyReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getDailyStockDetailReportByFacilityID(Mockito.<Date>any(), Mockito.<Date>any(),
                Mockito.<Integer>any());
        assertEquals("[]", actualYearlyReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getShortExpiryReport(ItemStockEntryReport)}
     */
    @Test
    void testGetShortExpiryReport() {
        // Arrange
        when(itemStockReportRepo.getShortExpiryReportByFacilityID(Mockito.<Date>any(), Mockito.<Integer>any()))
                .thenReturn(new ArrayList<>());

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(mock(Timestamp.class));
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualShortExpiryReport = cRMReportServiceImpl.getShortExpiryReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getShortExpiryReportByFacilityID(Mockito.<Date>any(), Mockito.<Integer>any());
        assertEquals("[]", actualShortExpiryReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getShortExpiryReport(ItemStockEntryReport)}
     */
    @Test
    void testGetShortExpiryReport2() {
        // Arrange
        when(itemStockReportRepo.getShortExpiryReport(Mockito.<Date>any())).thenReturn(new ArrayList<>());

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(null);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(mock(Timestamp.class));
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualShortExpiryReport = cRMReportServiceImpl.getShortExpiryReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getShortExpiryReport(Mockito.<Date>any());
        assertEquals("[]", actualShortExpiryReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getTransitReport(ItemStockEntryReport)}
     */
    @Test
    void testGetTransitReport() {
        // Arrange
        when(itemStockReportRepo.getTransitReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any())).thenReturn(new ArrayList<>());

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(1);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(mock(Timestamp.class));
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualTransitReport = cRMReportServiceImpl.getTransitReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getTransitReportByFacilityID(Mockito.<Timestamp>any(), Mockito.<Timestamp>any(),
                Mockito.<Integer>any());
        assertEquals("[]", actualTransitReport);
    }

    /**
     * Method under test:
     * {@link CRMReportServiceImpl#getTransitReport(ItemStockEntryReport)}
     */
    @Test
    void testGetTransitReport2() {
        // Arrange
        when(itemStockReportRepo.getTransitReport(Mockito.<Timestamp>any(), Mockito.<Timestamp>any()))
                .thenReturn(new ArrayList<>());

        ItemStockEntryReport entryReport = new ItemStockEntryReport();
        entryReport.setAlertBeforeDays("Alert Before Days");
        entryReport.setBatchNo("Batch No");
        entryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        entryReport.setCreatedDate(mock(Timestamp.class));
        entryReport.setEndDate(mock(Timestamp.class));
        entryReport.setEntryType("Entry Type");
        entryReport.setEntryTypeID(1L);
        entryReport.setExpiryDate(mock(Date.class));
        entryReport.setFacilityCode("Facility Code");
        entryReport.setFacilityDesc("Facility Desc");
        entryReport.setFacilityID(null);
        entryReport.setFacilityName("Facility Name");
        entryReport.setFacilityTypeID(1L);
        entryReport.setFactItemStockEntryID(1L);
        entryReport.setIsMainFacility(true);
        entryReport.setItemCategoryID(1);
        entryReport.setItemCategoryName("Item Category Name");
        entryReport.setItemDesc("Item Desc");
        entryReport.setItemID(1);
        entryReport.setItemName("Item Name");
        entryReport.setItemStockEntryID(1L);
        entryReport.setMainFacilityID(1);
        entryReport.setMonth(1);
        entryReport.setMonthName("Month Name");
        entryReport.setProviderServiceMapID(1);
        entryReport.setQuantity(1);
        entryReport.setQuantityInHand(1);
        entryReport.setStartDate(mock(Timestamp.class));
        entryReport.setStrength("Strength");
        entryReport.setUnitCostPrice(10.0d);
        entryReport.setYear(1);

        // Act
        String actualTransitReport = cRMReportServiceImpl.getTransitReport(entryReport);

        // Assert
        verify(itemStockReportRepo).getTransitReport(Mockito.<Timestamp>any(), Mockito.<Timestamp>any());
        assertEquals("[]", actualTransitReport);
    }
}
