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
@ContextConfiguration(classes = {ItemStockExitReport.class})
@ExtendWith(SpringExtension.class)
class ItemStockExitReportDiffblueTest {
    @Autowired
    private ItemStockExitReport itemStockExitReport;

    /**
     * Method under test: {@link ItemStockExitReport#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new ItemStockExitReport()).canEqual("Other"));
    }

    /**
     * Method under test: {@link ItemStockExitReport#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();

        ItemStockExitReport itemStockExitReport3 = new ItemStockExitReport();
        itemStockExitReport3.setBatchNo("Batch No");
        itemStockExitReport3.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport3.setEndDate(mock(Timestamp.class));
        itemStockExitReport3.setExitType("Exit Type");
        itemStockExitReport3.setExpiryDate(mock(Date.class));
        itemStockExitReport3.setFacilityID(1);
        itemStockExitReport3.setFacilityName("Facility Name");
        itemStockExitReport3.setFactItemStockExitID(1L);
        itemStockExitReport3.setItemCategoryName("Item Category Name");
        itemStockExitReport3.setItemID(1);
        itemStockExitReport3.setItemName("Item Name");
        itemStockExitReport3.setItemStockEntryID(1L);
        itemStockExitReport3.setItemStockExitID(1L);
        itemStockExitReport3.setProviderServiceMapID(1);
        itemStockExitReport3.setQuantity(3);
        itemStockExitReport3.setStartDate(mock(Timestamp.class));
        itemStockExitReport3.setUnitCostPrice(10.0d);

        // Act and Assert
        assertTrue(itemStockExitReport2.canEqual(itemStockExitReport3));
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, null);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, "Different type to ItemStockExitReport");
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Facility Name");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo(null);
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(null);
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(2);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(null);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Item Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName(null);
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(2L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(null);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Facility Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName(null);
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(2);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(null);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals17() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Facility Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals18() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName(null);
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals19() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(2L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals20() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(null);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals21() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(2L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals22() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(null);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals23() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(2);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals24() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(null);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals25() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(3);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals26() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(null);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals27() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(null);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Method under test: {@link ItemStockExitReport#equals(Object)}
     */
    @Test
    void testEquals28() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(0.5d);

        ItemStockExitReport itemStockExitReport2 = new ItemStockExitReport();
        itemStockExitReport2.setBatchNo("Batch No");
        itemStockExitReport2.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport2.setEndDate(mock(Timestamp.class));
        itemStockExitReport2.setExitType("Exit Type");
        itemStockExitReport2.setExpiryDate(mock(Date.class));
        itemStockExitReport2.setFacilityID(1);
        itemStockExitReport2.setFacilityName("Facility Name");
        itemStockExitReport2.setFactItemStockExitID(1L);
        itemStockExitReport2.setItemCategoryName("Item Category Name");
        itemStockExitReport2.setItemID(1);
        itemStockExitReport2.setItemName("Item Name");
        itemStockExitReport2.setItemStockEntryID(1L);
        itemStockExitReport2.setItemStockExitID(1L);
        itemStockExitReport2.setProviderServiceMapID(1);
        itemStockExitReport2.setQuantity(1);
        itemStockExitReport2.setStartDate(mock(Timestamp.class));
        itemStockExitReport2.setUnitCostPrice(10.0d);

        // Act and Assert
        assertNotEquals(itemStockExitReport, itemStockExitReport2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ItemStockExitReport#equals(Object)}
     *   <li>{@link ItemStockExitReport#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        ItemStockExitReport itemStockExitReport = new ItemStockExitReport();
        itemStockExitReport.setBatchNo("Batch No");
        itemStockExitReport.setCreatedDate(mock(Timestamp.class));
        itemStockExitReport.setEndDate(mock(Timestamp.class));
        itemStockExitReport.setExitType("Exit Type");
        itemStockExitReport.setExpiryDate(mock(Date.class));
        itemStockExitReport.setFacilityID(1);
        itemStockExitReport.setFacilityName("Facility Name");
        itemStockExitReport.setFactItemStockExitID(1L);
        itemStockExitReport.setItemCategoryName("Item Category Name");
        itemStockExitReport.setItemID(1);
        itemStockExitReport.setItemName("Item Name");
        itemStockExitReport.setItemStockEntryID(1L);
        itemStockExitReport.setItemStockExitID(1L);
        itemStockExitReport.setProviderServiceMapID(1);
        itemStockExitReport.setQuantity(1);
        itemStockExitReport.setStartDate(mock(Timestamp.class));
        itemStockExitReport.setUnitCostPrice(10.0d);

        // Act and Assert
        assertEquals(itemStockExitReport, itemStockExitReport);
        int expectedHashCodeResult = itemStockExitReport.hashCode();
        assertEquals(expectedHashCodeResult, itemStockExitReport.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link ItemStockExitReport}
     *   <li>{@link ItemStockExitReport#setBatchNo(String)}
     *   <li>{@link ItemStockExitReport#setCreatedDate(Timestamp)}
     *   <li>{@link ItemStockExitReport#setEndDate(Timestamp)}
     *   <li>{@link ItemStockExitReport#setExitType(String)}
     *   <li>{@link ItemStockExitReport#setExpiryDate(Date)}
     *   <li>{@link ItemStockExitReport#setFacilityID(Integer)}
     *   <li>{@link ItemStockExitReport#setFacilityName(String)}
     *   <li>{@link ItemStockExitReport#setFactItemStockExitID(Long)}
     *   <li>{@link ItemStockExitReport#setItemCategoryName(String)}
     *   <li>{@link ItemStockExitReport#setItemID(Integer)}
     *   <li>{@link ItemStockExitReport#setItemName(String)}
     *   <li>{@link ItemStockExitReport#setItemStockEntryID(Long)}
     *   <li>{@link ItemStockExitReport#setItemStockExitID(Long)}
     *   <li>{@link ItemStockExitReport#setProviderServiceMapID(Integer)}
     *   <li>{@link ItemStockExitReport#setQuantity(Integer)}
     *   <li>{@link ItemStockExitReport#setStartDate(Timestamp)}
     *   <li>{@link ItemStockExitReport#setUnitCostPrice(Double)}
     *   <li>{@link ItemStockExitReport#toString()}
     *   <li>{@link ItemStockExitReport#getBatchNo()}
     *   <li>{@link ItemStockExitReport#getCreatedDate()}
     *   <li>{@link ItemStockExitReport#getEndDate()}
     *   <li>{@link ItemStockExitReport#getExitType()}
     *   <li>{@link ItemStockExitReport#getExpiryDate()}
     *   <li>{@link ItemStockExitReport#getFacilityID()}
     *   <li>{@link ItemStockExitReport#getFacilityName()}
     *   <li>{@link ItemStockExitReport#getFactItemStockExitID()}
     *   <li>{@link ItemStockExitReport#getItemCategoryName()}
     *   <li>{@link ItemStockExitReport#getItemID()}
     *   <li>{@link ItemStockExitReport#getItemName()}
     *   <li>{@link ItemStockExitReport#getItemStockEntryID()}
     *   <li>{@link ItemStockExitReport#getItemStockExitID()}
     *   <li>{@link ItemStockExitReport#getProviderServiceMapID()}
     *   <li>{@link ItemStockExitReport#getQuantity()}
     *   <li>{@link ItemStockExitReport#getStartDate()}
     *   <li>{@link ItemStockExitReport#getUnitCostPrice()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ItemStockExitReport actualItemStockExitReport = new ItemStockExitReport();
        actualItemStockExitReport.setBatchNo("Batch No");
        Timestamp createdDate = mock(Timestamp.class);
        actualItemStockExitReport.setCreatedDate(createdDate);
        Timestamp endDate = mock(Timestamp.class);
        actualItemStockExitReport.setEndDate(endDate);
        actualItemStockExitReport.setExitType("Exit Type");
        Date expiryDate = mock(Date.class);
        actualItemStockExitReport.setExpiryDate(expiryDate);
        actualItemStockExitReport.setFacilityID(1);
        actualItemStockExitReport.setFacilityName("Facility Name");
        actualItemStockExitReport.setFactItemStockExitID(1L);
        actualItemStockExitReport.setItemCategoryName("Item Category Name");
        actualItemStockExitReport.setItemID(1);
        actualItemStockExitReport.setItemName("Item Name");
        actualItemStockExitReport.setItemStockEntryID(1L);
        actualItemStockExitReport.setItemStockExitID(1L);
        actualItemStockExitReport.setProviderServiceMapID(1);
        actualItemStockExitReport.setQuantity(1);
        Timestamp startDate = mock(Timestamp.class);
        actualItemStockExitReport.setStartDate(startDate);
        actualItemStockExitReport.setUnitCostPrice(10.0d);
        actualItemStockExitReport.toString();
        String actualBatchNo = actualItemStockExitReport.getBatchNo();
        Timestamp actualCreatedDate = actualItemStockExitReport.getCreatedDate();
        Timestamp actualEndDate = actualItemStockExitReport.getEndDate();
        String actualExitType = actualItemStockExitReport.getExitType();
        Date actualExpiryDate = actualItemStockExitReport.getExpiryDate();
        Integer actualFacilityID = actualItemStockExitReport.getFacilityID();
        String actualFacilityName = actualItemStockExitReport.getFacilityName();
        Long actualFactItemStockExitID = actualItemStockExitReport.getFactItemStockExitID();
        String actualItemCategoryName = actualItemStockExitReport.getItemCategoryName();
        Integer actualItemID = actualItemStockExitReport.getItemID();
        String actualItemName = actualItemStockExitReport.getItemName();
        Long actualItemStockEntryID = actualItemStockExitReport.getItemStockEntryID();
        Long actualItemStockExitID = actualItemStockExitReport.getItemStockExitID();
        Integer actualProviderServiceMapID = actualItemStockExitReport.getProviderServiceMapID();
        Integer actualQuantity = actualItemStockExitReport.getQuantity();
        Timestamp actualStartDate = actualItemStockExitReport.getStartDate();
        Double actualUnitCostPrice = actualItemStockExitReport.getUnitCostPrice();

        // Assert that nothing has changed
        assertEquals("Batch No", actualBatchNo);
        assertEquals("Exit Type", actualExitType);
        assertEquals("Facility Name", actualFacilityName);
        assertEquals("Item Category Name", actualItemCategoryName);
        assertEquals("Item Name", actualItemName);
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualItemID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualQuantity.intValue());
        assertEquals(10.0d, actualUnitCostPrice.doubleValue());
        assertEquals(1L, actualFactItemStockExitID.longValue());
        assertEquals(1L, actualItemStockEntryID.longValue());
        assertEquals(1L, actualItemStockExitID.longValue());
        assertSame(expiryDate, actualExpiryDate);
        assertSame(createdDate, actualCreatedDate);
        assertSame(endDate, actualEndDate);
        assertSame(startDate, actualStartDate);
    }
}
