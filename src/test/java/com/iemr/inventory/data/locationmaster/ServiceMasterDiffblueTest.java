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

@ContextConfiguration(classes = {ServiceMaster.class})
@ExtendWith(SpringExtension.class)
class ServiceMasterTest {
    @Autowired
    private ServiceMaster serviceMaster;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ServiceMaster#setCreatedBy(String)}
     *   <li>{@link ServiceMaster#setCreatedDate(Date)}
     *   <li>{@link ServiceMaster#setDeleted(Boolean)}
     *   <li>{@link ServiceMaster#setIsNational(Boolean)}
     *   <li>{@link ServiceMaster#setLastModDate(Date)}
     *   <li>{@link ServiceMaster#setModifiedBy(String)}
     *   <li>{@link ServiceMaster#setRoleMapping(StateServiceMapping1)}
     *   <li>{@link ServiceMaster#setServiceDesc(String)}
     *   <li>{@link ServiceMaster#setServiceID(Integer)}
     *   <li>{@link ServiceMaster#setServiceName(String)}
     *   <li>{@link ServiceMaster#toString()}
     *   <li>{@link ServiceMaster#getCreatedBy()}
     *   <li>{@link ServiceMaster#getCreatedDate()}
     *   <li>{@link ServiceMaster#getDeleted()}
     *   <li>{@link ServiceMaster#getIsNational()}
     *   <li>{@link ServiceMaster#getLastModDate()}
     *   <li>{@link ServiceMaster#getModifiedBy()}
     *   <li>{@link ServiceMaster#getRoleMapping()}
     *   <li>{@link ServiceMaster#getServiceDesc()}
     *   <li>{@link ServiceMaster#getServiceID()}
     *   <li>{@link ServiceMaster#getServiceName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ServiceMaster serviceMaster = new ServiceMaster();

        // Act
        serviceMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        serviceMaster.setCreatedDate(createdDate);
        serviceMaster.setDeleted(true);
        serviceMaster.setIsNational(true);
        Date lastModDate = mock(Date.class);
        serviceMaster.setLastModDate(lastModDate);
        serviceMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ServiceMaster serviceMaster2 = new ServiceMaster();
        serviceMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster2.setCreatedDate(mock(Date.class));
        serviceMaster2.setDeleted(true);
        serviceMaster2.setIsNational(true);
        serviceMaster2.setLastModDate(mock(Date.class));
        serviceMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster2.setRoleMapping(new StateServiceMapping1());
        serviceMaster2.setServiceDesc("Service Desc");
        serviceMaster2.setServiceID(1);
        serviceMaster2.setServiceName("Service Name");
        State stateMaster = new State();
        stateMaster.setCountryID(1);
        stateMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster.setCreatedDate(mock(Date.class));
        stateMaster.setDeleted(true);
        stateMaster.setLastModDate(mock(Date.class));
        stateMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster.setRoleMapping(new StateServiceMapping1());
        stateMaster.setStateCode('A');
        stateMaster.setStateID(1);
        stateMaster.setStateName("MD");
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
        roleMapping.setServiceMaster(serviceMaster2);
        roleMapping.setServiceProviderID(1);
        roleMapping.setStateID(1);
        roleMapping.setStateMaster(stateMaster);
        roleMapping.setStatusID(1);
        roleMapping.setValidFrom(mock(Date.class));
        roleMapping.setValidTill(mock(Date.class));
        ServiceMaster serviceMaster3 = new ServiceMaster();
        serviceMaster3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster3.setCreatedDate(mock(Date.class));
        serviceMaster3.setDeleted(true);
        serviceMaster3.setIsNational(true);
        serviceMaster3.setLastModDate(mock(Date.class));
        serviceMaster3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster3.setRoleMapping(roleMapping);
        serviceMaster3.setServiceDesc("Service Desc");
        serviceMaster3.setServiceID(1);
        serviceMaster3.setServiceName("Service Name");
        ServiceMaster serviceMaster4 = new ServiceMaster();
        serviceMaster4.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster4.setCreatedDate(mock(Date.class));
        serviceMaster4.setDeleted(true);
        serviceMaster4.setIsNational(true);
        serviceMaster4.setLastModDate(mock(Date.class));
        serviceMaster4.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster4.setRoleMapping(new StateServiceMapping1());
        serviceMaster4.setServiceDesc("Service Desc");
        serviceMaster4.setServiceID(1);
        serviceMaster4.setServiceName("Service Name");
        State stateMaster2 = new State();
        stateMaster2.setCountryID(1);
        stateMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster2.setCreatedDate(mock(Date.class));
        stateMaster2.setDeleted(true);
        stateMaster2.setLastModDate(mock(Date.class));
        stateMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster2.setRoleMapping(new StateServiceMapping1());
        stateMaster2.setStateCode('A');
        stateMaster2.setStateID(1);
        stateMaster2.setStateName("MD");
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
        roleMapping2.setServiceMaster(serviceMaster4);
        roleMapping2.setServiceProviderID(1);
        roleMapping2.setStateID(1);
        roleMapping2.setStateMaster(stateMaster2);
        roleMapping2.setStatusID(1);
        roleMapping2.setValidFrom(mock(Date.class));
        roleMapping2.setValidTill(mock(Date.class));
        State stateMaster3 = new State();
        stateMaster3.setCountryID(1);
        stateMaster3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster3.setCreatedDate(mock(Date.class));
        stateMaster3.setDeleted(true);
        stateMaster3.setLastModDate(mock(Date.class));
        stateMaster3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster3.setRoleMapping(roleMapping2);
        stateMaster3.setStateCode('A');
        stateMaster3.setStateID(1);
        stateMaster3.setStateName("MD");
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
        roleMapping3.setServiceMaster(serviceMaster3);
        roleMapping3.setServiceProviderID(1);
        roleMapping3.setStateID(1);
        roleMapping3.setStateMaster(stateMaster3);
        roleMapping3.setStatusID(1);
        roleMapping3.setValidFrom(mock(Date.class));
        roleMapping3.setValidTill(mock(Date.class));
        serviceMaster.setRoleMapping(roleMapping3);
        serviceMaster.setServiceDesc("Service Desc");
        serviceMaster.setServiceID(1);
        serviceMaster.setServiceName("Service Name");
        serviceMaster.toString();
        String actualCreatedBy = serviceMaster.getCreatedBy();
        Date actualCreatedDate = serviceMaster.getCreatedDate();
        Boolean actualDeleted = serviceMaster.getDeleted();
        Boolean actualIsNational = serviceMaster.getIsNational();
        Date actualLastModDate = serviceMaster.getLastModDate();
        String actualModifiedBy = serviceMaster.getModifiedBy();
        StateServiceMapping1 actualRoleMapping = serviceMaster.getRoleMapping();
        String actualServiceDesc = serviceMaster.getServiceDesc();
        Integer actualServiceID = serviceMaster.getServiceID();

        // Assert that nothing has changed
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Service Desc", actualServiceDesc);
        assertEquals("Service Name", serviceMaster.getServiceName());
        assertEquals(1, actualServiceID.intValue());
        assertTrue(actualDeleted);
        assertTrue(actualIsNational);
        assertSame(roleMapping3, actualRoleMapping);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link ServiceMaster}
     */
    @Test
    void testNewServiceMaster() {
        // Arrange and Act
        ServiceMaster actualServiceMaster = new ServiceMaster();

        // Assert
        assertNull(actualServiceMaster.getRoleMapping());
        assertNull(actualServiceMaster.getDeleted());
        assertNull(actualServiceMaster.getIsNational());
        assertNull(actualServiceMaster.getServiceID());
        assertNull(actualServiceMaster.getCreatedBy());
        assertNull(actualServiceMaster.getModifiedBy());
        assertNull(actualServiceMaster.getServiceDesc());
        assertNull(actualServiceMaster.getServiceName());
        assertNull(actualServiceMaster.getCreatedDate());
        assertNull(actualServiceMaster.getLastModDate());
    }
}
