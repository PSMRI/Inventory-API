package com.iemr.inventory.data.patientreturn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
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

@ContextConfiguration(classes = {ReturnHistoryModel.class, String.class, Long.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ReturnHistoryModelTest {
    @Autowired
    private ReturnHistoryModel returnHistoryModel;

    @MockBean
    private Timestamp timestamp;

    /**
     * Method under test:
     * {@link ReturnHistoryModel#ReturnHistoryModel(String, String, Integer, Timestamp, Long, Long, String, Integer, String, Timestamp)}
     */
    @Test
    void testNewReturnHistoryModel() {
        //    Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        Timestamp dateofIssue = mock(Timestamp.class);
        Timestamp returnDate = mock(Timestamp.class);

        // Act
        ReturnHistoryModel actualReturnHistoryModel = new ReturnHistoryModel("Item Name", "Batch No", 1, dateofIssue, 1L,
                1L, "Patient Name", 1, "Gender", returnDate);

        // Assert
        assertEquals("Batch No", actualReturnHistoryModel.getBatchNo());
        assertEquals("Gender", actualReturnHistoryModel.getGender());
        assertEquals("Item Name", actualReturnHistoryModel.getItemName());
        assertEquals("Patient Name", actualReturnHistoryModel.getPatientName());
        assertEquals(1, actualReturnHistoryModel.getAge().intValue());
        assertEquals(1, actualReturnHistoryModel.getIssuedQuantity().intValue());
        assertEquals(1L, actualReturnHistoryModel.getVisitCode().longValue());
        assertEquals(1L, actualReturnHistoryModel.getVisitID().longValue());
        assertSame(dateofIssue, actualReturnHistoryModel.getDateofIssue());
        assertSame(returnDate, actualReturnHistoryModel.getReturnDate());
    }

    /**
     * Method under test:
     * {@link ReturnHistoryModel#ReturnHistoryModel(String, String, Integer, Timestamp, Long, Long, String, Integer, String, Timestamp)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewReturnHistoryModel2() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.lang.Integer
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        new ReturnHistoryModel("Item Name", "Batch No", 1, mock(Timestamp.class), 1L, 1L, "Patient Name", 1, "Gender",
                mock(Timestamp.class));

    }

    /**
     * Method under test: {@link ReturnHistoryModel#toString()}
     */
    @Test
    void testToString() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //    Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     ReturnHistoryModel.age
        //     ReturnHistoryModel.batchNo
        //     ReturnHistoryModel.dateofIssue
        //     ReturnHistoryModel.gender
        //     ReturnHistoryModel.issuedQuantity
        //     ReturnHistoryModel.itemName
        //     ReturnHistoryModel.outputMapper
        //     ReturnHistoryModel.patientName
        //     ReturnHistoryModel.returnDate
        //     ReturnHistoryModel.visitCode
        //     ReturnHistoryModel.visitID

        // Arrange and Act
        (new ReturnHistoryModel("Item Name", "Batch No", 1, mock(Timestamp.class), 1L, 1L, "Patient Name", 1, "Gender",
                mock(Timestamp.class))).toString();
    }
}
