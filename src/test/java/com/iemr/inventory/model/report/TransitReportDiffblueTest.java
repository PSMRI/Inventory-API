package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class TransitReportTest {
    /**
     * Method under test:
     * {@link TransitReport#TransitReport(Long, String, String, Double, Date, String, String, Timestamp, Timestamp)}
     */
    @Test
    void testNewTransitReport() {
        // Arrange
        Date expiryDate = mock(Date.class);
        Timestamp OrderDate = mock(Timestamp.class);
        Timestamp IssueDate = mock(Timestamp.class);

        // Act
        TransitReport actualTransitReport = new TransitReport(1L, "Item Name", "Batch No", 10.0d, expiryDate,
                "jane.doe@example.org", "Tofacility Name", OrderDate, IssueDate);

        // Assert
        assertEquals("Batch No", actualTransitReport.getBatchNo());
        assertEquals("Item Name", actualTransitReport.getItemName());
        assertEquals("Tofacility Name", actualTransitReport.getTofacilityName());
        assertEquals("jane.doe@example.org", actualTransitReport.getFromFacilityName());
        assertEquals(10.0d, actualTransitReport.getUnitCostPrice().doubleValue());
        assertEquals(1L, actualTransitReport.getSlNo().longValue());
        assertSame(expiryDate, actualTransitReport.getExpiryDate());
        assertSame(IssueDate, actualTransitReport.getIssueDate());
        assertSame(OrderDate, actualTransitReport.getOrderDate());
    }
}
