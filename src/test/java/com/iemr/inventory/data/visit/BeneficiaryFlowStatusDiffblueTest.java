package com.iemr.inventory.data.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class BeneficiaryFlowStatusTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link BeneficiaryFlowStatus#setProviderServiceMapID(Integer)}
     *   <li>{@link BeneficiaryFlowStatus#setProviderServiceMapId(Integer)}
     *   <li>{@link BeneficiaryFlowStatus#setRegistrationDate(Timestamp)}
     *   <li>{@link BeneficiaryFlowStatus#setServicePointName(String)}
     *   <li>{@link BeneficiaryFlowStatus#getProviderServiceMapID()}
     *   <li>{@link BeneficiaryFlowStatus#getProviderServiceMapId()}
     *   <li>{@link BeneficiaryFlowStatus#getRegistrationDate()}
     *   <li>{@link BeneficiaryFlowStatus#getServicePointName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        BeneficiaryFlowStatus beneficiaryFlowStatus = new BeneficiaryFlowStatus();

        // Act
        beneficiaryFlowStatus.setProviderServiceMapID(1);
        beneficiaryFlowStatus.setProviderServiceMapId(1);
        Timestamp registrationDate = mock(Timestamp.class);
        beneficiaryFlowStatus.setRegistrationDate(registrationDate);
        beneficiaryFlowStatus.setServicePointName("Service Point Name");
        Integer actualProviderServiceMapID = beneficiaryFlowStatus.getProviderServiceMapID();
        Integer actualProviderServiceMapId = beneficiaryFlowStatus.getProviderServiceMapId();
        Timestamp actualRegistrationDate = beneficiaryFlowStatus.getRegistrationDate();

        // Assert that nothing has changed
        assertEquals("Service Point Name", beneficiaryFlowStatus.getServicePointName());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualProviderServiceMapId.intValue());
        assertSame(registrationDate, actualRegistrationDate);
    }
}
