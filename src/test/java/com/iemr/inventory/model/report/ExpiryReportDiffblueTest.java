package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class ExpiryReportTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExpiryReport#ExpiryReport()}
     *   <li>{@link ExpiryReport#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"facilityName\":null,\"itemName\":null,\"itemCategory\":null,\"batchNo\":null,\"unitCostPrice\""
                        + ":null,\"expiryDate\":null,\"quantityInHand\":null}",
                (new ExpiryReport()).toString());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ExpiryReport#ExpiryReport(String, String, String, String, Double, Date, Integer)}
     *   <li>{@link ExpiryReport#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date expiryDate = mock(Date.class);

        // Act
        ExpiryReport actualExpiryReport = new ExpiryReport("Facility Name", "Item Name", "Item Category", "Batch No", 10.0d,
                expiryDate, 1);
        actualExpiryReport.toString();

        // Assert
        assertEquals("Batch No", actualExpiryReport.getBatchNo());
        assertEquals("Facility Name", actualExpiryReport.getFacilityName());
        assertEquals("Item Category", actualExpiryReport.getItemCategory());
        assertEquals("Item Name", actualExpiryReport.getItemName());
        assertNull(actualExpiryReport.getSlNo());
        assertEquals(1, actualExpiryReport.getQuantityInHand().intValue());
        assertEquals(10.0d, actualExpiryReport.getUnitCostPrice().doubleValue());
        assertSame(expiryDate, actualExpiryReport.getExpiryDate());
    }
}
