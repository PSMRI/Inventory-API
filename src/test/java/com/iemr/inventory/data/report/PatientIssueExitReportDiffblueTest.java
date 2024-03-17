package com.iemr.inventory.data.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {PatientIssueExitReport.class})
@ExtendWith(SpringExtension.class)
class PatientIssueExitReportDiffblueTest {
    @Autowired
    private PatientIssueExitReport patientIssueExitReport;

    /**
     * Method under test: {@link PatientIssueExitReport#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new PatientIssueExitReport()).canEqual("Other"));
    }

    /**
     * Method under test: {@link PatientIssueExitReport#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();

        PatientIssueExitReport patientIssueExitReport3 = new PatientIssueExitReport();
        patientIssueExitReport3.setAge(3);
        patientIssueExitReport3.setBatchNo("Batch No");
        patientIssueExitReport3.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport3.setEndDate(mock(Timestamp.class));
        patientIssueExitReport3.setExpiryDate(mock(Date.class));
        patientIssueExitReport3.setFacilityID(1);
        patientIssueExitReport3.setFactPatientIssueExitID(1L);
        patientIssueExitReport3.setGender("Gender");
        patientIssueExitReport3.setItemCategoryName("Item Category Name");
        patientIssueExitReport3.setItemID(1);
        patientIssueExitReport3.setItemName("Item Name");
        patientIssueExitReport3.setItemStockEntryID(1L);
        patientIssueExitReport3.setItemStockExitID(1L);
        patientIssueExitReport3.setPatientName("Patient Name");
        patientIssueExitReport3.setQuantityGiven(3);
        patientIssueExitReport3.setStartDate(mock(Timestamp.class));
        patientIssueExitReport3.setStrength("Strength");

        // Act and Assert
        assertTrue(patientIssueExitReport2.canEqual(patientIssueExitReport3));
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, null);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, "Different type to PatientIssueExitReport");
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(3);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(null);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Item Name");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo(null);
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(null);
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(2);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(null);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(2L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(null);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName(null);
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(2);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(null);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals17() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Category Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals18() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName(null);
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals19() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(2L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals20() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(null);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals21() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(2L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals22() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(null);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals23() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(3);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals24() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(null);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals25() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Item Name");

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Method under test: {@link PatientIssueExitReport#equals(Object)}
     */
    @Test
    void testEquals26() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength(null);

        PatientIssueExitReport patientIssueExitReport2 = new PatientIssueExitReport();
        patientIssueExitReport2.setAge(1);
        patientIssueExitReport2.setBatchNo("Batch No");
        patientIssueExitReport2.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport2.setEndDate(mock(Timestamp.class));
        patientIssueExitReport2.setExpiryDate(mock(Date.class));
        patientIssueExitReport2.setFacilityID(1);
        patientIssueExitReport2.setFactPatientIssueExitID(1L);
        patientIssueExitReport2.setGender("Gender");
        patientIssueExitReport2.setItemCategoryName("Item Category Name");
        patientIssueExitReport2.setItemID(1);
        patientIssueExitReport2.setItemName("Item Name");
        patientIssueExitReport2.setItemStockEntryID(1L);
        patientIssueExitReport2.setItemStockExitID(1L);
        patientIssueExitReport2.setPatientName("Patient Name");
        patientIssueExitReport2.setQuantityGiven(1);
        patientIssueExitReport2.setStartDate(mock(Timestamp.class));
        patientIssueExitReport2.setStrength("Strength");

        // Act and Assert
        assertNotEquals(patientIssueExitReport, patientIssueExitReport2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PatientIssueExitReport#equals(Object)}
     *   <li>{@link PatientIssueExitReport#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
        patientIssueExitReport.setAge(1);
        patientIssueExitReport.setBatchNo("Batch No");
        patientIssueExitReport.setCreatedDate(mock(Timestamp.class));
        patientIssueExitReport.setEndDate(mock(Timestamp.class));
        patientIssueExitReport.setExpiryDate(mock(Date.class));
        patientIssueExitReport.setFacilityID(1);
        patientIssueExitReport.setFactPatientIssueExitID(1L);
        patientIssueExitReport.setGender("Gender");
        patientIssueExitReport.setItemCategoryName("Item Category Name");
        patientIssueExitReport.setItemID(1);
        patientIssueExitReport.setItemName("Item Name");
        patientIssueExitReport.setItemStockEntryID(1L);
        patientIssueExitReport.setItemStockExitID(1L);
        patientIssueExitReport.setPatientName("Patient Name");
        patientIssueExitReport.setQuantityGiven(1);
        patientIssueExitReport.setStartDate(mock(Timestamp.class));
        patientIssueExitReport.setStrength("Strength");

        // Act and Assert
        assertEquals(patientIssueExitReport, patientIssueExitReport);
        int expectedHashCodeResult = patientIssueExitReport.hashCode();
        assertEquals(expectedHashCodeResult, patientIssueExitReport.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link PatientIssueExitReport}
     *   <li>{@link PatientIssueExitReport#setAge(Integer)}
     *   <li>{@link PatientIssueExitReport#setBatchNo(String)}
     *   <li>{@link PatientIssueExitReport#setCreatedDate(Timestamp)}
     *   <li>{@link PatientIssueExitReport#setEndDate(Timestamp)}
     *   <li>{@link PatientIssueExitReport#setExpiryDate(Date)}
     *   <li>{@link PatientIssueExitReport#setFacilityID(Integer)}
     *   <li>{@link PatientIssueExitReport#setFactPatientIssueExitID(Long)}
     *   <li>{@link PatientIssueExitReport#setGender(String)}
     *   <li>{@link PatientIssueExitReport#setItemCategoryName(String)}
     *   <li>{@link PatientIssueExitReport#setItemID(Integer)}
     *   <li>{@link PatientIssueExitReport#setItemName(String)}
     *   <li>{@link PatientIssueExitReport#setItemStockEntryID(Long)}
     *   <li>{@link PatientIssueExitReport#setItemStockExitID(Long)}
     *   <li>{@link PatientIssueExitReport#setPatientName(String)}
     *   <li>{@link PatientIssueExitReport#setQuantityGiven(Integer)}
     *   <li>{@link PatientIssueExitReport#setStartDate(Timestamp)}
     *   <li>{@link PatientIssueExitReport#setStrength(String)}
     *   <li>{@link PatientIssueExitReport#toString()}
     *   <li>{@link PatientIssueExitReport#getAge()}
     *   <li>{@link PatientIssueExitReport#getBatchNo()}
     *   <li>{@link PatientIssueExitReport#getCreatedDate()}
     *   <li>{@link PatientIssueExitReport#getEndDate()}
     *   <li>{@link PatientIssueExitReport#getExpiryDate()}
     *   <li>{@link PatientIssueExitReport#getFacilityID()}
     *   <li>{@link PatientIssueExitReport#getFactPatientIssueExitID()}
     *   <li>{@link PatientIssueExitReport#getGender()}
     *   <li>{@link PatientIssueExitReport#getItemCategoryName()}
     *   <li>{@link PatientIssueExitReport#getItemID()}
     *   <li>{@link PatientIssueExitReport#getItemName()}
     *   <li>{@link PatientIssueExitReport#getItemStockEntryID()}
     *   <li>{@link PatientIssueExitReport#getItemStockExitID()}
     *   <li>{@link PatientIssueExitReport#getPatientName()}
     *   <li>{@link PatientIssueExitReport#getQuantityGiven()}
     *   <li>{@link PatientIssueExitReport#getStartDate()}
     *   <li>{@link PatientIssueExitReport#getStrength()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        PatientIssueExitReport actualPatientIssueExitReport = new PatientIssueExitReport();
        actualPatientIssueExitReport.setAge(1);
        actualPatientIssueExitReport.setBatchNo("Batch No");
        Timestamp createdDate = mock(Timestamp.class);
        actualPatientIssueExitReport.setCreatedDate(createdDate);
        Timestamp endDate = mock(Timestamp.class);
        actualPatientIssueExitReport.setEndDate(endDate);
        Date expiryDate = mock(Date.class);
        actualPatientIssueExitReport.setExpiryDate(expiryDate);
        actualPatientIssueExitReport.setFacilityID(1);
        actualPatientIssueExitReport.setFactPatientIssueExitID(1L);
        actualPatientIssueExitReport.setGender("Gender");
        actualPatientIssueExitReport.setItemCategoryName("Item Category Name");
        actualPatientIssueExitReport.setItemID(1);
        actualPatientIssueExitReport.setItemName("Item Name");
        actualPatientIssueExitReport.setItemStockEntryID(1L);
        actualPatientIssueExitReport.setItemStockExitID(1L);
        actualPatientIssueExitReport.setPatientName("Patient Name");
        actualPatientIssueExitReport.setQuantityGiven(1);
        Timestamp startDate = mock(Timestamp.class);
        actualPatientIssueExitReport.setStartDate(startDate);
        actualPatientIssueExitReport.setStrength("Strength");
        actualPatientIssueExitReport.toString();
        Integer actualAge = actualPatientIssueExitReport.getAge();
        String actualBatchNo = actualPatientIssueExitReport.getBatchNo();
        Timestamp actualCreatedDate = actualPatientIssueExitReport.getCreatedDate();
        Timestamp actualEndDate = actualPatientIssueExitReport.getEndDate();
        Date actualExpiryDate = actualPatientIssueExitReport.getExpiryDate();
        Integer actualFacilityID = actualPatientIssueExitReport.getFacilityID();
        Long actualFactPatientIssueExitID = actualPatientIssueExitReport.getFactPatientIssueExitID();
        String actualGender = actualPatientIssueExitReport.getGender();
        String actualItemCategoryName = actualPatientIssueExitReport.getItemCategoryName();
        Integer actualItemID = actualPatientIssueExitReport.getItemID();
        String actualItemName = actualPatientIssueExitReport.getItemName();
        Long actualItemStockEntryID = actualPatientIssueExitReport.getItemStockEntryID();
        Long actualItemStockExitID = actualPatientIssueExitReport.getItemStockExitID();
        String actualPatientName = actualPatientIssueExitReport.getPatientName();
        Integer actualQuantityGiven = actualPatientIssueExitReport.getQuantityGiven();
        Timestamp actualStartDate = actualPatientIssueExitReport.getStartDate();

        // Assert that nothing has changed
        assertEquals("Batch No", actualBatchNo);
        assertEquals("Gender", actualGender);
        assertEquals("Item Category Name", actualItemCategoryName);
        assertEquals("Item Name", actualItemName);
        assertEquals("Patient Name", actualPatientName);
        assertEquals("Strength", actualPatientIssueExitReport.getStrength());
        assertEquals(1, actualAge.intValue());
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualItemID.intValue());
        assertEquals(1, actualQuantityGiven.intValue());
        assertEquals(1L, actualFactPatientIssueExitID.longValue());
        assertEquals(1L, actualItemStockEntryID.longValue());
        assertEquals(1L, actualItemStockExitID.longValue());
        assertSame(expiryDate, actualExpiryDate);
        assertSame(createdDate, actualCreatedDate);
        assertSame(endDate, actualEndDate);
        assertSame(startDate, actualStartDate);
    }
}
