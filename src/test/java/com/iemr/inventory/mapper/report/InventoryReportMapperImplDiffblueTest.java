package com.iemr.inventory.mapper.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.model.report.BenDrugIssueReport;
import com.iemr.inventory.model.report.ExpiryReport;
import com.iemr.inventory.model.report.InwardStockReport;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {InventoryReportMapperImpl.class})
@ExtendWith(SpringExtension.class)
class InventoryReportMapperImplTest {
    @Autowired
    private InventoryReportMapperImpl inventoryReportMapperImpl;

    /**
     * Method under test:
     * {@link InventoryReportMapperImpl#mapInwardStockReport(ItemStockEntryReport)}
     */
    @Test
    void testMapInwardStockReport() {
        // Arrange
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
        InwardStockReport actualMapInwardStockReportResult = inventoryReportMapperImpl.mapInwardStockReport(entryReport);

        // Assert
        assertEquals("Batch No", actualMapInwardStockReportResult.getBatchNo());
        assertEquals("Entry Type", actualMapInwardStockReportResult.getEntryType());
        assertEquals("Facility Name", actualMapInwardStockReportResult.getFacilityName());
        assertEquals("Item Category Name", actualMapInwardStockReportResult.getItemCategory());
        assertEquals("Item Name", actualMapInwardStockReportResult.getItemName());
        assertEquals(1, actualMapInwardStockReportResult.getQuantity().intValue());
        assertEquals(10.0d, actualMapInwardStockReportResult.getUnitCostPrice().doubleValue());
    }

    /**
     * Method under test:
     * {@link InventoryReportMapperImpl#mapExpiryReport(ItemStockEntryReport)}
     */
    @Test
    void testMapExpiryReport() {
        // Arrange
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
        ExpiryReport actualMapExpiryReportResult = inventoryReportMapperImpl.mapExpiryReport(entryReport);

        // Assert
        assertEquals("Batch No", actualMapExpiryReportResult.getBatchNo());
        assertEquals("Facility Name", actualMapExpiryReportResult.getFacilityName());
        assertEquals("Item Category Name", actualMapExpiryReportResult.getItemCategory());
        assertEquals("Item Name", actualMapExpiryReportResult.getItemName());
        assertEquals(1, actualMapExpiryReportResult.getQuantityInHand().intValue());
        assertEquals(10.0d, actualMapExpiryReportResult.getUnitCostPrice().doubleValue());
    }

    /**
     * Method under test:
     * {@link InventoryReportMapperImpl#mapBenDrugIssueReport(PatientIssueExitReport)}
     */
    @Test
    void testMapBenDrugIssueReport() {
        // Arrange
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
        BenDrugIssueReport actualMapBenDrugIssueReportResult = inventoryReportMapperImpl.mapBenDrugIssueReport(exitReport);

        // Assert
        assertEquals("Batch No", actualMapBenDrugIssueReportResult.getBatchNo());
        assertEquals("Gender", actualMapBenDrugIssueReportResult.getGender());
        assertEquals("Item Category Name", actualMapBenDrugIssueReportResult.getItemCategory());
        assertEquals("Item Name", actualMapBenDrugIssueReportResult.getItemName());
        assertEquals("Patient Name", actualMapBenDrugIssueReportResult.getBeneficiaryName());
        assertEquals("Strength", actualMapBenDrugIssueReportResult.getStrength());
        assertEquals(1, actualMapBenDrugIssueReportResult.getAge().intValue());
        assertEquals(1, actualMapBenDrugIssueReportResult.getDispensedQuantity().intValue());
    }
}
