package com.iemr.inventory.data.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
@ContextConfiguration(classes = {ItemStockEntryReport.class})
@ExtendWith(SpringExtension.class)
class ItemStockEntryReportDiffblueTest {
    @Autowired
    private ItemStockEntryReport itemStockEntryReport;

    /**
     * Method under test: {@link ItemStockEntryReport#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ItemStockEntryReport()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ItemStockEntryReport#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();

        ItemStockEntryReport itemStockEntryReport3 = new ItemStockEntryReport();
        itemStockEntryReport3.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport3.setBatchNo("Batch No");
        itemStockEntryReport3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport3.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport3.setEndDate(mock(Timestamp.class));
        itemStockEntryReport3.setEntryType("Entry Type");
        itemStockEntryReport3.setEntryTypeID(1L);
        itemStockEntryReport3.setExpiryDate(mock(Date.class));
        itemStockEntryReport3.setFacilityCode("Facility Code");
        itemStockEntryReport3.setFacilityDesc("Facility Desc");
        itemStockEntryReport3.setFacilityID(1);
        itemStockEntryReport3.setFacilityName("Facility Name");
        itemStockEntryReport3.setFacilityTypeID(1L);
        itemStockEntryReport3.setFactItemStockEntryID(1L);
        itemStockEntryReport3.setIsMainFacility(true);
        itemStockEntryReport3.setItemCategoryID(1);
        itemStockEntryReport3.setItemCategoryName("Item Category Name");
        itemStockEntryReport3.setItemDesc("Item Desc");
        itemStockEntryReport3.setItemID(1);
        itemStockEntryReport3.setItemName("Item Name");
        itemStockEntryReport3.setItemStockEntryID(1L);
        itemStockEntryReport3.setMainFacilityID(1);
        itemStockEntryReport3.setMonth(3);
        itemStockEntryReport3.setMonthName("Month Name");
        itemStockEntryReport3.setProviderServiceMapID(1);
        itemStockEntryReport3.setQuantity(3);
        itemStockEntryReport3.setQuantityInHand(3);
        itemStockEntryReport3.setStartDate(mock(Timestamp.class));
        itemStockEntryReport3.setStrength("Strength");
        itemStockEntryReport3.setUnitCostPrice(10.0d);
        itemStockEntryReport3.setYear(3);

        // Act and Assert
        assertTrue(itemStockEntryReport2.canEqual(itemStockEntryReport3));
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, null);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, "Different type to ItemStockEntryReport");
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Facility Name");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays(null);
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Facility Name");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo(null);
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(2L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(null);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(null);
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Name");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode(null);
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Name");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc(null);
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(2);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(null);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals17() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Desc");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals18() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName(null);
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals19() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(2L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals20() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(null);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals21() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(2L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals22() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(null);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals23() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(false);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals24() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(null);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals25() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(2);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals26() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(null);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals27() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Facility Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals28() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName(null);
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals29() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Facility Name");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals30() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc(null);
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals31() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(2);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals32() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(null);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals33() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Facility Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals34() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName(null);
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals35() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(2L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals36() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(null);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals37() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(2);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals38() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(null);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals39() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(3);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals40() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(null);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals41() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(2);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals42() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(null);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals43() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(3);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals44() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(null);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals45() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(3);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals46() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(null);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals47() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Facility Name");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals48() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength(null);
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals49() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(null);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals50() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(0.5d);
        itemStockEntryReport.setYear(1);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals51() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(3);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Method under test: {@link ItemStockEntryReport#equals(Object)}
     */
    @Test
    void testEquals52() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(null);

        ItemStockEntryReport itemStockEntryReport2 = new ItemStockEntryReport();
        itemStockEntryReport2.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport2.setBatchNo("Batch No");
        itemStockEntryReport2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport2.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport2.setEndDate(mock(Timestamp.class));
        itemStockEntryReport2.setEntryType("Entry Type");
        itemStockEntryReport2.setEntryTypeID(1L);
        itemStockEntryReport2.setExpiryDate(mock(Date.class));
        itemStockEntryReport2.setFacilityCode("Facility Code");
        itemStockEntryReport2.setFacilityDesc("Facility Desc");
        itemStockEntryReport2.setFacilityID(1);
        itemStockEntryReport2.setFacilityName("Facility Name");
        itemStockEntryReport2.setFacilityTypeID(1L);
        itemStockEntryReport2.setFactItemStockEntryID(1L);
        itemStockEntryReport2.setIsMainFacility(true);
        itemStockEntryReport2.setItemCategoryID(1);
        itemStockEntryReport2.setItemCategoryName("Item Category Name");
        itemStockEntryReport2.setItemDesc("Item Desc");
        itemStockEntryReport2.setItemID(1);
        itemStockEntryReport2.setItemName("Item Name");
        itemStockEntryReport2.setItemStockEntryID(1L);
        itemStockEntryReport2.setMainFacilityID(1);
        itemStockEntryReport2.setMonth(1);
        itemStockEntryReport2.setMonthName("Month Name");
        itemStockEntryReport2.setProviderServiceMapID(1);
        itemStockEntryReport2.setQuantity(1);
        itemStockEntryReport2.setQuantityInHand(1);
        itemStockEntryReport2.setStartDate(mock(Timestamp.class));
        itemStockEntryReport2.setStrength("Strength");
        itemStockEntryReport2.setUnitCostPrice(10.0d);
        itemStockEntryReport2.setYear(1);

        // Act and Assert
        assertNotEquals(itemStockEntryReport, itemStockEntryReport2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemStockEntryReport#equals(Object)}
     *   <li>{@link ItemStockEntryReport#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        ItemStockEntryReport itemStockEntryReport = new ItemStockEntryReport();
        itemStockEntryReport.setAlertBeforeDays("Alert Before Days");
        itemStockEntryReport.setBatchNo("Batch No");
        itemStockEntryReport.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemStockEntryReport.setCreatedDate(mock(Timestamp.class));
        itemStockEntryReport.setEndDate(mock(Timestamp.class));
        itemStockEntryReport.setEntryType("Entry Type");
        itemStockEntryReport.setEntryTypeID(1L);
        itemStockEntryReport.setExpiryDate(mock(Date.class));
        itemStockEntryReport.setFacilityCode("Facility Code");
        itemStockEntryReport.setFacilityDesc("Facility Desc");
        itemStockEntryReport.setFacilityID(1);
        itemStockEntryReport.setFacilityName("Facility Name");
        itemStockEntryReport.setFacilityTypeID(1L);
        itemStockEntryReport.setFactItemStockEntryID(1L);
        itemStockEntryReport.setIsMainFacility(true);
        itemStockEntryReport.setItemCategoryID(1);
        itemStockEntryReport.setItemCategoryName("Item Category Name");
        itemStockEntryReport.setItemDesc("Item Desc");
        itemStockEntryReport.setItemID(1);
        itemStockEntryReport.setItemName("Item Name");
        itemStockEntryReport.setItemStockEntryID(1L);
        itemStockEntryReport.setMainFacilityID(1);
        itemStockEntryReport.setMonth(1);
        itemStockEntryReport.setMonthName("Month Name");
        itemStockEntryReport.setProviderServiceMapID(1);
        itemStockEntryReport.setQuantity(1);
        itemStockEntryReport.setQuantityInHand(1);
        itemStockEntryReport.setStartDate(mock(Timestamp.class));
        itemStockEntryReport.setStrength("Strength");
        itemStockEntryReport.setUnitCostPrice(10.0d);
        itemStockEntryReport.setYear(1);

        // Act and Assert
        assertEquals(itemStockEntryReport, itemStockEntryReport);
        int expectedHashCodeResult = itemStockEntryReport.hashCode();
        assertEquals(expectedHashCodeResult, itemStockEntryReport.hashCode());
    }
}
