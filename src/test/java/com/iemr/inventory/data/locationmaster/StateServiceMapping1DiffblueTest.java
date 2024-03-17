package com.iemr.inventory.data.locationmaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StateServiceMapping1.class})
@ExtendWith(SpringExtension.class)
class StateServiceMapping1Test {
    @Autowired
    private StateServiceMapping1 stateServiceMapping1;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link StateServiceMapping1#setAddress(String)}
     *   <li>{@link StateServiceMapping1#setCityID(Integer)}
     *   <li>{@link StateServiceMapping1#setCountryID(Integer)}
     *   <li>{@link StateServiceMapping1#setCreatedBy(String)}
     *   <li>{@link StateServiceMapping1#setCreatedDate(Date)}
     *   <li>{@link StateServiceMapping1#setDeleted(Boolean)}
     *   <li>{@link StateServiceMapping1#setDistrictBlockID(Integer)}
     *   <li>{@link StateServiceMapping1#setDistrictID(Integer)}
     *   <li>{@link StateServiceMapping1#setLastModDate(Date)}
     *   <li>{@link StateServiceMapping1#setModifiedBy(String)}
     *   <li>{@link StateServiceMapping1#setProviderServiceMapID(Integer)}
     *   <li>{@link StateServiceMapping1#setServiceID(Integer)}
     *   <li>{@link StateServiceMapping1#setServiceMaster(ServiceMaster)}
     *   <li>{@link StateServiceMapping1#setServiceProviderID(Integer)}
     *   <li>{@link StateServiceMapping1#setStateID(Integer)}
     *   <li>{@link StateServiceMapping1#setStateMaster(State)}
     *   <li>{@link StateServiceMapping1#setStatusID(Integer)}
     *   <li>{@link StateServiceMapping1#setValidFrom(Date)}
     *   <li>{@link StateServiceMapping1#setValidTill(Date)}
     *   <li>{@link StateServiceMapping1#toString()}
     *   <li>{@link StateServiceMapping1#getAddress()}
     *   <li>{@link StateServiceMapping1#getCityID()}
     *   <li>{@link StateServiceMapping1#getCountryID()}
     *   <li>{@link StateServiceMapping1#getCreatedBy()}
     *   <li>{@link StateServiceMapping1#getCreatedDate()}
     *   <li>{@link StateServiceMapping1#getDeleted()}
     *   <li>{@link StateServiceMapping1#getDistrictBlockID()}
     *   <li>{@link StateServiceMapping1#getDistrictID()}
     *   <li>{@link StateServiceMapping1#getLastModDate()}
     *   <li>{@link StateServiceMapping1#getModifiedBy()}
     *   <li>{@link StateServiceMapping1#getProviderServiceMapID()}
     *   <li>{@link StateServiceMapping1#getServiceID()}
     *   <li>{@link StateServiceMapping1#getServiceMaster()}
     *   <li>{@link StateServiceMapping1#getServiceProviderID()}
     *   <li>{@link StateServiceMapping1#getStateID()}
     *   <li>{@link StateServiceMapping1#getStateMaster()}
     *   <li>{@link StateServiceMapping1#getStatusID()}
     *   <li>{@link StateServiceMapping1#getValidFrom()}
     *   <li>{@link StateServiceMapping1#getValidTill()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        StateServiceMapping1 stateServiceMapping1 = new StateServiceMapping1();

        // Act
        stateServiceMapping1.setAddress("42 Main St");
        stateServiceMapping1.setCityID(1);
        stateServiceMapping1.setCountryID(1);
        stateServiceMapping1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        stateServiceMapping1.setCreatedDate(createdDate);
        stateServiceMapping1.setDeleted(true);
        stateServiceMapping1.setDistrictBlockID(1);
        stateServiceMapping1.setDistrictID(1);
        Date lastModDate = mock(Date.class);
        stateServiceMapping1.setLastModDate(lastModDate);
        stateServiceMapping1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateServiceMapping1.setProviderServiceMapID(1);
        stateServiceMapping1.setServiceID(1);
        StateServiceMapping1 roleMapping = new StateServiceMapping1();
        roleMapping.setAddress("42 Main St");
        roleMapping.setCityID(1);
        roleMapping.setCountryID(1);
        roleMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        roleMapping.setCreatedDate(mock(Date.class));
        roleMapping.setDeleted(true);
        roleMapping.setDistrictBlockID(1);
        roleMapping.setDistrictID(1);
        roleMapping.setLastModDate(mock(Date.class));
        roleMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        roleMapping.setProviderServiceMapID(1);
        roleMapping.setServiceID(1);
        roleMapping.setServiceMaster(new ServiceMaster());
        roleMapping.setServiceProviderID(1);
        roleMapping.setStateID(1);
        roleMapping.setStateMaster(new State());
        roleMapping.setStatusID(1);
        roleMapping.setValidFrom(mock(Date.class));
        roleMapping.setValidTill(mock(Date.class));
        ServiceMaster serviceMaster = new ServiceMaster();
        serviceMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster.setCreatedDate(mock(Date.class));
        serviceMaster.setDeleted(true);
        serviceMaster.setIsNational(true);
        serviceMaster.setLastModDate(mock(Date.class));
        serviceMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster.setRoleMapping(roleMapping);
        serviceMaster.setServiceDesc("Service Desc");
        serviceMaster.setServiceID(1);
        serviceMaster.setServiceName("Service Name");
        StateServiceMapping1 roleMapping2 = new StateServiceMapping1();
        roleMapping2.setAddress("42 Main St");
        roleMapping2.setCityID(1);
        roleMapping2.setCountryID(1);
        roleMapping2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        roleMapping2.setCreatedDate(mock(Date.class));
        roleMapping2.setDeleted(true);
        roleMapping2.setDistrictBlockID(1);
        roleMapping2.setDistrictID(1);
        roleMapping2.setLastModDate(mock(Date.class));
        roleMapping2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        roleMapping2.setProviderServiceMapID(1);
        roleMapping2.setServiceID(1);
        roleMapping2.setServiceMaster(new ServiceMaster());
        roleMapping2.setServiceProviderID(1);
        roleMapping2.setStateID(1);
        roleMapping2.setStateMaster(new State());
        roleMapping2.setStatusID(1);
        roleMapping2.setValidFrom(mock(Date.class));
        roleMapping2.setValidTill(mock(Date.class));
        State stateMaster = new State();
        stateMaster.setCountryID(1);
        stateMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster.setCreatedDate(mock(Date.class));
        stateMaster.setDeleted(true);
        stateMaster.setLastModDate(mock(Date.class));
        stateMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster.setRoleMapping(roleMapping2);
        stateMaster.setStateCode('A');
        stateMaster.setStateID(1);
        stateMaster.setStateName("MD");
        StateServiceMapping1 roleMapping3 = new StateServiceMapping1();
        roleMapping3.setAddress("42 Main St");
        roleMapping3.setCityID(1);
        roleMapping3.setCountryID(1);
        roleMapping3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        roleMapping3.setCreatedDate(mock(Date.class));
        roleMapping3.setDeleted(true);
        roleMapping3.setDistrictBlockID(1);
        roleMapping3.setDistrictID(1);
        roleMapping3.setLastModDate(mock(Date.class));
        roleMapping3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        roleMapping3.setProviderServiceMapID(1);
        roleMapping3.setServiceID(1);
        roleMapping3.setServiceMaster(serviceMaster);
        roleMapping3.setServiceProviderID(1);
        roleMapping3.setStateID(1);
        roleMapping3.setStateMaster(stateMaster);
        roleMapping3.setStatusID(1);
        roleMapping3.setValidFrom(mock(Date.class));
        roleMapping3.setValidTill(mock(Date.class));
        ServiceMaster serviceMaster2 = new ServiceMaster();
        serviceMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster2.setCreatedDate(mock(Date.class));
        serviceMaster2.setDeleted(true);
        serviceMaster2.setIsNational(true);
        serviceMaster2.setLastModDate(mock(Date.class));
        serviceMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster2.setRoleMapping(roleMapping3);
        serviceMaster2.setServiceDesc("Service Desc");
        serviceMaster2.setServiceID(1);
        serviceMaster2.setServiceName("Service Name");
        stateServiceMapping1.setServiceMaster(serviceMaster2);
        stateServiceMapping1.setServiceProviderID(1);
        stateServiceMapping1.setStateID(1);
        StateServiceMapping1 roleMapping4 = new StateServiceMapping1();
        roleMapping4.setAddress("42 Main St");
        roleMapping4.setCityID(1);
        roleMapping4.setCountryID(1);
        roleMapping4.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        roleMapping4.setCreatedDate(mock(Date.class));
        roleMapping4.setDeleted(true);
        roleMapping4.setDistrictBlockID(1);
        roleMapping4.setDistrictID(1);
        roleMapping4.setLastModDate(mock(Date.class));
        roleMapping4.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        roleMapping4.setProviderServiceMapID(1);
        roleMapping4.setServiceID(1);
        roleMapping4.setServiceMaster(new ServiceMaster());
        roleMapping4.setServiceProviderID(1);
        roleMapping4.setStateID(1);
        roleMapping4.setStateMaster(new State());
        roleMapping4.setStatusID(1);
        roleMapping4.setValidFrom(mock(Date.class));
        roleMapping4.setValidTill(mock(Date.class));
        ServiceMaster serviceMaster3 = new ServiceMaster();
        serviceMaster3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster3.setCreatedDate(mock(Date.class));
        serviceMaster3.setDeleted(true);
        serviceMaster3.setIsNational(true);
        serviceMaster3.setLastModDate(mock(Date.class));
        serviceMaster3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster3.setRoleMapping(roleMapping4);
        serviceMaster3.setServiceDesc("Service Desc");
        serviceMaster3.setServiceID(1);
        serviceMaster3.setServiceName("Service Name");
        StateServiceMapping1 roleMapping5 = new StateServiceMapping1();
        roleMapping5.setAddress("42 Main St");
        roleMapping5.setCityID(1);
        roleMapping5.setCountryID(1);
        roleMapping5.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        roleMapping5.setCreatedDate(mock(Date.class));
        roleMapping5.setDeleted(true);
        roleMapping5.setDistrictBlockID(1);
        roleMapping5.setDistrictID(1);
        roleMapping5.setLastModDate(mock(Date.class));
        roleMapping5.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        roleMapping5.setProviderServiceMapID(1);
        roleMapping5.setServiceID(1);
        roleMapping5.setServiceMaster(new ServiceMaster());
        roleMapping5.setServiceProviderID(1);
        roleMapping5.setStateID(1);
        roleMapping5.setStateMaster(new State());
        roleMapping5.setStatusID(1);
        roleMapping5.setValidFrom(mock(Date.class));
        roleMapping5.setValidTill(mock(Date.class));
        State stateMaster2 = new State();
        stateMaster2.setCountryID(1);
        stateMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster2.setCreatedDate(mock(Date.class));
        stateMaster2.setDeleted(true);
        stateMaster2.setLastModDate(mock(Date.class));
        stateMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster2.setRoleMapping(roleMapping5);
        stateMaster2.setStateCode('A');
        stateMaster2.setStateID(1);
        stateMaster2.setStateName("MD");
        StateServiceMapping1 roleMapping6 = new StateServiceMapping1();
        roleMapping6.setAddress("42 Main St");
        roleMapping6.setCityID(1);
        roleMapping6.setCountryID(1);
        roleMapping6.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        roleMapping6.setCreatedDate(mock(Date.class));
        roleMapping6.setDeleted(true);
        roleMapping6.setDistrictBlockID(1);
        roleMapping6.setDistrictID(1);
        roleMapping6.setLastModDate(mock(Date.class));
        roleMapping6.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        roleMapping6.setProviderServiceMapID(1);
        roleMapping6.setServiceID(1);
        roleMapping6.setServiceMaster(serviceMaster3);
        roleMapping6.setServiceProviderID(1);
        roleMapping6.setStateID(1);
        roleMapping6.setStateMaster(stateMaster2);
        roleMapping6.setStatusID(1);
        roleMapping6.setValidFrom(mock(Date.class));
        roleMapping6.setValidTill(mock(Date.class));
        State stateMaster3 = new State();
        stateMaster3.setCountryID(1);
        stateMaster3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster3.setCreatedDate(mock(Date.class));
        stateMaster3.setDeleted(true);
        stateMaster3.setLastModDate(mock(Date.class));
        stateMaster3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster3.setRoleMapping(roleMapping6);
        stateMaster3.setStateCode('A');
        stateMaster3.setStateID(1);
        stateMaster3.setStateName("MD");
        stateServiceMapping1.setStateMaster(stateMaster3);
        stateServiceMapping1.setStatusID(1);
        Date validFrom = mock(Date.class);
        stateServiceMapping1.setValidFrom(validFrom);
        Date validTill = mock(Date.class);
        stateServiceMapping1.setValidTill(validTill);
        stateServiceMapping1.toString();
        String actualAddress = stateServiceMapping1.getAddress();
        Integer actualCityID = stateServiceMapping1.getCityID();
        Integer actualCountryID = stateServiceMapping1.getCountryID();
        String actualCreatedBy = stateServiceMapping1.getCreatedBy();
        Date actualCreatedDate = stateServiceMapping1.getCreatedDate();
        Boolean actualDeleted = stateServiceMapping1.getDeleted();
        Integer actualDistrictBlockID = stateServiceMapping1.getDistrictBlockID();
        Integer actualDistrictID = stateServiceMapping1.getDistrictID();
        Date actualLastModDate = stateServiceMapping1.getLastModDate();
        String actualModifiedBy = stateServiceMapping1.getModifiedBy();
        Integer actualProviderServiceMapID = stateServiceMapping1.getProviderServiceMapID();
        Integer actualServiceID = stateServiceMapping1.getServiceID();
        ServiceMaster actualServiceMaster = stateServiceMapping1.getServiceMaster();
        Integer actualServiceProviderID = stateServiceMapping1.getServiceProviderID();
        Integer actualStateID = stateServiceMapping1.getStateID();
        State actualStateMaster = stateServiceMapping1.getStateMaster();
        Integer actualStatusID = stateServiceMapping1.getStatusID();
        Date actualValidFrom = stateServiceMapping1.getValidFrom();
        Date actualValidTill = stateServiceMapping1.getValidTill();

        // Assert that nothing has changed
        assertEquals("42 Main St", actualAddress);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals(1, actualCityID.intValue());
        assertEquals(1, actualCountryID.intValue());
        assertEquals(1, actualDistrictBlockID.intValue());
        assertEquals(1, actualDistrictID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualServiceID.intValue());
        assertEquals(1, actualServiceProviderID.intValue());
        assertEquals(1, actualStateID.intValue());
        assertEquals(1, actualStatusID.intValue());
        assertTrue(actualDeleted);
        assertSame(serviceMaster2, actualServiceMaster);
        assertSame(stateMaster3, actualStateMaster);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
        assertSame(validFrom, actualValidFrom);
        assertSame(validTill, actualValidTill);
    }

    /**
     * Method under test: {@link StateServiceMapping1#StateServiceMapping1()}
     */
    @Test
    void testNewStateServiceMapping1() {
        // Arrange and Act
        StateServiceMapping1 actualStateServiceMapping1 = new StateServiceMapping1();

        // Assert
        assertNull(actualStateServiceMapping1.getServiceMaster());
        assertNull(actualStateServiceMapping1.getStateMaster());
        assertNull(actualStateServiceMapping1.getDeleted());
        assertNull(actualStateServiceMapping1.getCityID());
        assertNull(actualStateServiceMapping1.getCountryID());
        assertNull(actualStateServiceMapping1.getDistrictBlockID());
        assertNull(actualStateServiceMapping1.getDistrictID());
        assertNull(actualStateServiceMapping1.getProviderServiceMapID());
        assertNull(actualStateServiceMapping1.getServiceID());
        assertNull(actualStateServiceMapping1.getServiceProviderID());
        assertNull(actualStateServiceMapping1.getStateID());
        assertNull(actualStateServiceMapping1.getStatusID());
        assertNull(actualStateServiceMapping1.getAddress());
        assertNull(actualStateServiceMapping1.getCreatedBy());
        assertNull(actualStateServiceMapping1.getModifiedBy());
        assertNull(actualStateServiceMapping1.getCreatedDate());
        assertNull(actualStateServiceMapping1.getLastModDate());
        assertNull(actualStateServiceMapping1.getValidFrom());
        assertNull(actualStateServiceMapping1.getValidTill());
    }

    /**
     * Method under test: {@link StateServiceMapping1#StateServiceMapping1(Integer)}
     */
    @Test
    void testNewStateServiceMapping12() {
        // Arrange, Act and Assert
        assertEquals(1, (new StateServiceMapping1(1)).getProviderServiceMapID().intValue());
    }

    /**
     * Method under test:
     * {@link StateServiceMapping1#StateServiceMapping1(Integer, Integer, String)}
     */
    @Test
    void testNewStateServiceMapping13() {
        // Arrange and Act
        StateServiceMapping1 actualStateServiceMapping1 = new StateServiceMapping1(1, 1, "Service Name");

        // Assert
        assertEquals(1, actualStateServiceMapping1.getProviderServiceMapID().intValue());
        assertEquals(1, actualStateServiceMapping1.getServiceID().intValue());
    }

    /**
     * Method under test:
     * {@link StateServiceMapping1#StateServiceMapping1(Integer, String, Integer)}
     */
    @Test
    void testNewStateServiceMapping14() {
        // Arrange and Act
        StateServiceMapping1 actualStateServiceMapping1 = new StateServiceMapping1(1, "MD", 1);

        // Assert
        assertEquals(1, actualStateServiceMapping1.getProviderServiceMapID().intValue());
        assertEquals(1, actualStateServiceMapping1.getStateID().intValue());
    }

    /**
     * Method under test:
     * {@link StateServiceMapping1#StateServiceMapping1(Integer, String, Integer, Integer)}
     */
    @Test
    void testNewStateServiceMapping15() {
        // Arrange and Act
        StateServiceMapping1 actualStateServiceMapping1 = new StateServiceMapping1(1, "MD", 1, 1);

        // Assert
        assertEquals(1, actualStateServiceMapping1.getProviderServiceMapID().intValue());
        assertEquals(1, actualStateServiceMapping1.getStateID().intValue());
        assertEquals(1, actualStateServiceMapping1.getStatusID().intValue());
    }
}
