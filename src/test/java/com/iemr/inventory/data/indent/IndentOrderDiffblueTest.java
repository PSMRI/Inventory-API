package com.iemr.inventory.data.indent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {IndentOrder.class})
@ExtendWith(SpringExtension.class)
class IndentOrderTest {
    @Autowired
    private IndentOrder indentOrder;

    /**
     * Method under test: {@link IndentOrder#IndentOrder()}
     */
    @Test
    void testNewIndentOrder() {
        // Arrange and Act
        IndentOrder actualIndentOrder = new IndentOrder();

        // Assert
        assertNull(actualIndentOrder.getIndent());
        assertNull(actualIndentOrder.getItem());
        assertNull(actualIndentOrder.getDeleted());
        assertNull(actualIndentOrder.getFacilityID());
        assertNull(actualIndentOrder.getFromFacilityID());
        assertNull(actualIndentOrder.getIndentFromID());
        assertNull(actualIndentOrder.getProviderServiceMapID());
        assertNull(actualIndentOrder.getSyncFacilityID());
        assertNull(actualIndentOrder.getIndentID());
        assertNull(actualIndentOrder.getIndentIssueID());
        assertNull(actualIndentOrder.getIndentIssueIDOrderID());
        assertNull(actualIndentOrder.getIndentIssueIDVanID());
        assertNull(actualIndentOrder.getIndentOrderID());
        assertNull(actualIndentOrder.getItemID());
        assertNull(actualIndentOrder.getParkingPlaceID());
        assertNull(actualIndentOrder.getQOH());
        assertNull(actualIndentOrder.getRequiredQty());
        assertNull(actualIndentOrder.getVanID());
        assertNull(actualIndentOrder.getVanSerialNo());
        assertNull(actualIndentOrder.getCreatedBy());
        assertNull(actualIndentOrder.getItemName());
        assertNull(actualIndentOrder.getModifiedBy());
        assertNull(actualIndentOrder.getProcessed());
        assertNull(actualIndentOrder.getRemarks());
        assertNull(actualIndentOrder.getStatus());
        assertNull(actualIndentOrder.getCreatedDate());
        assertNull(actualIndentOrder.getEndDateTime());
        assertNull(actualIndentOrder.getLastModDate());
        assertNull(actualIndentOrder.getStartDateTime());
    }

    /**
     * Method under test:
     * {@link IndentOrder#IndentOrder(Long, Long, Long, String, Long, Long, String, Integer, String, Boolean, String, String, Timestamp, String, Timestamp, Long, Long, Long)}
     */
    @Test
    void testNewIndentOrder2() {
        // Arrange
        Timestamp createdDate = mock(Timestamp.class);
        Timestamp lastModDate = mock(Timestamp.class);

        // Act
        IndentOrder actualIndentOrder = new IndentOrder(1L, 1L, 1L, "Item Name", 1L, 1L, "Remarks", 1, "Status", true,
                "Processed", "Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100", lastModDate, 1L, 1L,
                1L);

        // Assert
        assertEquals("Item Name", actualIndentOrder.getItemName());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualIndentOrder.getCreatedBy());
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualIndentOrder.getModifiedBy());
        assertEquals("Processed", actualIndentOrder.getProcessed());
        assertEquals("Remarks", actualIndentOrder.getRemarks());
        assertEquals("Status", actualIndentOrder.getStatus());
        assertEquals(1, actualIndentOrder.getProviderServiceMapID().intValue());
        assertEquals(1L, actualIndentOrder.getIndentID().longValue());
        assertEquals(1L, actualIndentOrder.getIndentIssueID().longValue());
        assertEquals(1L, actualIndentOrder.getIndentOrderID().longValue());
        assertEquals(1L, actualIndentOrder.getItemID().longValue());
        assertEquals(1L, actualIndentOrder.getParkingPlaceID().longValue());
        assertEquals(1L, actualIndentOrder.getQOH().longValue());
        assertEquals(1L, actualIndentOrder.getRequiredQty().longValue());
        assertEquals(1L, actualIndentOrder.getVanID().longValue());
        assertTrue(actualIndentOrder.getDeleted());
        assertSame(createdDate, actualIndentOrder.getCreatedDate());
        assertSame(lastModDate, actualIndentOrder.getLastModDate());
    }

    /**
     * Method under test: {@link IndentOrder#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"indentOrderID\":null,\"indentID\":null,\"itemID\":null,\"itemName\":null,\"qOH\":null,\"requiredQty\":null,"
                        + "\"remarks\":null,\"providerServiceMapID\":null,\"vanID\":null,\"status\":null,\"deleted\":null,\"processed\":null"
                        + ",\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null,\"parkingPlaceID\":null,"
                        + "\"vanSerialNo\":null,\"fromFacilityID\":null,\"syncFacilityID\":null,\"indentIssueID\":null,\"indentIssueIDOrderID"
                        + "\":null,\"indentIssueIDVanID\":null}",
                (new IndentOrder()).toString());
    }
}
