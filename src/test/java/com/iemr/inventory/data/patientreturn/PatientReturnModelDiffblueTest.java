package com.iemr.inventory.data.patientreturn;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PatientReturnModel.class, Integer.class, String.class})
@ExtendWith(SpringExtension.class)
class PatientReturnModelTest {
    @Autowired
    private PatientReturnModel patientReturnModel;

    /**
     * Method under test:
     * {@link PatientReturnModel#PatientReturnModel(Long, Integer, Integer, String)}
     */
    @Test
    void testNewPatientReturnModel() {
        //    Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        PatientReturnModel actualPatientReturnModel = new PatientReturnModel(1L, 1, 1, "Item Name");

        // Assert
        assertEquals("Item Name", actualPatientReturnModel.getItemName());
        assertEquals(1, actualPatientReturnModel.getFacilityID().intValue());
        assertEquals(1, actualPatientReturnModel.getItemID().intValue());
        assertEquals(1L, actualPatientReturnModel.getBenRegID().longValue());
    }

    /**
     * Method under test:
     * {@link PatientReturnModel#PatientReturnModel(Long, Integer, Integer, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testNewPatientReturnModel2() {
        // TODO:  Cover was only able to create a partial test for this method:
        //   Reason: Missing beans when creating Spring context.
        //   Failed to create Spring context due to missing beans
        //   in the current Spring profile:
        //       java.lang.Long
        //   See https://diff.blue/R027 to resolve this issue.

        // Arrange and Act
        new PatientReturnModel(1L, 1, 1, "Item Name");

    }

    /**
     * Method under test: {@link PatientReturnModel#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{\"benRegID\":1,\"facilityID\":1,\"itemID\":1,\"itemName\":\"Item Name\"}",
                (new PatientReturnModel(1L, 1, 1, "Item Name")).toString());
    }
}
