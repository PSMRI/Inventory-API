package com.iemr.inventory.data.commonutility;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CommonUtilityClassTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CommonUtilityClass#setBenFlowID(Long)}
     *   <li>{@link CommonUtilityClass#setBenVisitID(Long)}
     *   <li>{@link CommonUtilityClass#setBeneficiaryID(Long)}
     *   <li>{@link CommonUtilityClass#setBeneficiaryRegID(Long)}
     *   <li>{@link CommonUtilityClass#setFacilityID(Integer)}
     *   <li>{@link CommonUtilityClass#setParkingPlaceID(Integer)}
     *   <li>{@link CommonUtilityClass#setProviderServiceMapID(Integer)}
     *   <li>{@link CommonUtilityClass#setSessionID(Integer)}
     *   <li>{@link CommonUtilityClass#setVanID(Integer)}
     *   <li>{@link CommonUtilityClass#setVisitCode(Long)}
     *   <li>{@link CommonUtilityClass#getBenFlowID()}
     *   <li>{@link CommonUtilityClass#getBenVisitID()}
     *   <li>{@link CommonUtilityClass#getBeneficiaryID()}
     *   <li>{@link CommonUtilityClass#getBeneficiaryRegID()}
     *   <li>{@link CommonUtilityClass#getFacilityID()}
     *   <li>{@link CommonUtilityClass#getParkingPlaceID()}
     *   <li>{@link CommonUtilityClass#getProviderServiceMapID()}
     *   <li>{@link CommonUtilityClass#getSessionID()}
     *   <li>{@link CommonUtilityClass#getVanID()}
     *   <li>{@link CommonUtilityClass#getVisitCode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        CommonUtilityClass commonUtilityClass = new CommonUtilityClass();

        // Act
        commonUtilityClass.setBenFlowID(1L);
        commonUtilityClass.setBenVisitID(1L);
        commonUtilityClass.setBeneficiaryID(1L);
        commonUtilityClass.setBeneficiaryRegID(1L);
        commonUtilityClass.setFacilityID(1);
        commonUtilityClass.setParkingPlaceID(1);
        commonUtilityClass.setProviderServiceMapID(1);
        commonUtilityClass.setSessionID(1);
        commonUtilityClass.setVanID(1);
        commonUtilityClass.setVisitCode(1L);
        Long actualBenFlowID = commonUtilityClass.getBenFlowID();
        Long actualBenVisitID = commonUtilityClass.getBenVisitID();
        Long actualBeneficiaryID = commonUtilityClass.getBeneficiaryID();
        Long actualBeneficiaryRegID = commonUtilityClass.getBeneficiaryRegID();
        Integer actualFacilityID = commonUtilityClass.getFacilityID();
        Integer actualParkingPlaceID = commonUtilityClass.getParkingPlaceID();
        Integer actualProviderServiceMapID = commonUtilityClass.getProviderServiceMapID();
        Integer actualSessionID = commonUtilityClass.getSessionID();
        Integer actualVanID = commonUtilityClass.getVanID();
        Long actualVisitCode = commonUtilityClass.getVisitCode();

        // Assert that nothing has changed
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualParkingPlaceID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualSessionID.intValue());
        assertEquals(1, actualVanID.intValue());
        assertEquals(1L, actualBenFlowID.longValue());
        assertEquals(1L, actualBenVisitID.longValue());
        assertEquals(1L, actualBeneficiaryID.longValue());
        assertEquals(1L, actualBeneficiaryRegID.longValue());
        assertEquals(1L, actualVisitCode.longValue());
    }
}
