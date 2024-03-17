package com.iemr.inventory.data.patientreturn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ItemDetailModel.class, String.class, Boolean.class, Long.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ItemDetailModelTest {
    @Autowired
    private ItemDetailModel itemDetailModel;

    @MockBean
    private Timestamp timestamp;

    /**
     * Method under test:
     * {@link ItemDetailModel#ItemDetailModel(Integer, String, String, Integer, Timestamp, Boolean, Boolean, Long, Long, Long, Long, Long, Integer, Integer)}
     */
    @Test
    void testNewItemDetailModel() {
        //    Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Timestamp dateofIssue = mock(Timestamp.class);

        // Act
        ItemDetailModel actualItemDetailModel = new ItemDetailModel(1, "Item Name", "Batch No", 1, dateofIssue, true, true,
                1L, 1L, 1L, 1L, 1L, 1, 1);

        // Assert
        assertEquals("Batch No", actualItemDetailModel.getBatchNo());
        assertEquals("Item Name", actualItemDetailModel.getItemName());
        assertEquals(1, actualItemDetailModel.getFacilityID().intValue());
        assertEquals(1, actualItemDetailModel.getIssuedQuantity().intValue());
        assertEquals(1, actualItemDetailModel.getItemID().intValue());
        assertEquals(1, actualItemDetailModel.getProviderServiceMapID().intValue());
        assertEquals(1L, actualItemDetailModel.getBenRegID().longValue());
        assertEquals(1L, actualItemDetailModel.getItemStockEntryID().longValue());
        assertEquals(1L, actualItemDetailModel.getItemStockExitID().longValue());
        assertEquals(1L, actualItemDetailModel.getVisitCode().longValue());
        assertEquals(1L, actualItemDetailModel.getVisitID().longValue());
        assertTrue(actualItemDetailModel.getDiscontinued());
        assertTrue(actualItemDetailModel.getItemDeleted());
        assertSame(dateofIssue, actualItemDetailModel.getDateofIssue());
    }

    /**
     * Method under test:
     * {@link ItemDetailModel#ItemDetailModel(Integer, String, String, Integer, Timestamp, Boolean, Boolean, Long, Long, Long, Long, Long, Integer, Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewItemDetailModel2() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.lang.Integer
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        new ItemDetailModel(1, "Item Name", "Batch No", 1, mock(Timestamp.class), true, true, 1L, 1L, 1L, 1L, 1L, 1, 1);

    }

    /**
     * Method under test: {@link ItemDetailModel#toString()}
     */
    @Test
    void testToString() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ItemDetailModel.batchNo
        //     ItemDetailModel.benRegID
        //     ItemDetailModel.createdBy
        //     ItemDetailModel.dateofIssue
        //     ItemDetailModel.discontinued
        //     ItemDetailModel.facilityID
        //     ItemDetailModel.issuedQuantity
        //     ItemDetailModel.itemDeleted
        //     ItemDetailModel.itemID
        //     ItemDetailModel.itemName
        //     ItemDetailModel.itemStockEntryID
        //     ItemDetailModel.itemStockExitID
        //     ItemDetailModel.outputMapper
        //     ItemDetailModel.providerServiceMapID
        //     ItemDetailModel.returnQuantity
        //     ItemDetailModel.visitCode
        //     ItemDetailModel.visitID

        // Arrange and Act
        (new ItemDetailModel(1, "Item Name", "Batch No", 1, mock(Timestamp.class), true, true, 1L, 1L, 1L, 1L, 1L, 1, 1))
                .toString();
    }
}
