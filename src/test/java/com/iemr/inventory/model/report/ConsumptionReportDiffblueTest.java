package com.iemr.inventory.model.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class ConsumptionReportTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ConsumptionReport#ConsumptionReport()}
     *   <li>{@link ConsumptionReport#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"slNo\":null,\"facilityName\":null,\"itemName\":null,\"itemCategory\":null,\"batchNo\":null,\"unitCostPrice\""
                        + ":null,\"expiryDate\":null,\"consumptionDate\":null,\"consumptionQuantity\":null,\"consumptionType\":null,"
                        + "\"beneficiaryID\":null,\"beneficiaryName\":null}",
                (new ConsumptionReport()).toString());
    }

    /**
     * Method under test:
     * {@link ConsumptionReport#ConsumptionReport(String, Timestamp, Integer, String, Date, String, String, String, Double, String, String)}
     */
    @Test
    void testNewConsumptionReport() {
        // Arrange
        Timestamp consumptionDate = mock(Timestamp.class);
        Date expiryDate = mock(Date.class);

        // Act
        ConsumptionReport actualConsumptionReport = new ConsumptionReport("Batch No", consumptionDate, 1,
                "Consumption Type", expiryDate, "Facility Name", "Item Category", "Item Name", 10.0d, "Beneficiary Name",
                "Beneficiary ID");

        // Assert
        assertEquals("Batch No", actualConsumptionReport.getBatchNo());
        assertEquals("Beneficiary ID", actualConsumptionReport.getBeneficiaryID());
        assertEquals("Beneficiary Name", actualConsumptionReport.getBeneficiaryName());
        assertEquals("Consumption Type", actualConsumptionReport.getConsumptionType());
        assertEquals("Facility Name", actualConsumptionReport.getFacilityName());
        assertEquals("Item Category", actualConsumptionReport.getItemCategory());
        assertEquals("Item Name", actualConsumptionReport.getItemName());
        assertNull(actualConsumptionReport.getSlNo());
        assertEquals(1, actualConsumptionReport.getConsumptionQuantity().intValue());
        assertEquals(10.0d, actualConsumptionReport.getUnitCostPrice().doubleValue());
        assertSame(expiryDate, actualConsumptionReport.getExpiryDate());
        assertSame(consumptionDate, actualConsumptionReport.getConsumptionDate());
    }
}
