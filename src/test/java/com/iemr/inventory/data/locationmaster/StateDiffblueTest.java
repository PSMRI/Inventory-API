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

@ContextConfiguration(classes = {State.class})
@ExtendWith(SpringExtension.class)
class StateTest {
    @Autowired
    private State state;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link State#setCountryID(Integer)}
     *   <li>{@link State#setCreatedBy(String)}
     *   <li>{@link State#setCreatedDate(Date)}
     *   <li>{@link State#setDeleted(Boolean)}
     *   <li>{@link State#setLastModDate(Date)}
     *   <li>{@link State#setModifiedBy(String)}
     *   <li>{@link State#setRoleMapping(StateServiceMapping1)}
     *   <li>{@link State#setStateCode(char)}
     *   <li>{@link State#setStateID(Integer)}
     *   <li>{@link State#setStateName(String)}
     *   <li>{@link State#toString()}
     *   <li>{@link State#getCountryID()}
     *   <li>{@link State#getCreatedBy()}
     *   <li>{@link State#getCreatedDate()}
     *   <li>{@link State#getDeleted()}
     *   <li>{@link State#getLastModDate()}
     *   <li>{@link State#getModifiedBy()}
     *   <li>{@link State#getRoleMapping()}
     *   <li>{@link State#getStateCode()}
     *   <li>{@link State#getStateID()}
     *   <li>{@link State#getStateName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        State state = new State();

        // Act
        state.setCountryID(1);
        state.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        state.setCreatedDate(createdDate);
        state.setDeleted(true);
        Date lastModDate = mock(Date.class);
        state.setLastModDate(lastModDate);
        state.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ServiceMaster serviceMaster = new ServiceMaster();
        serviceMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster.setCreatedDate(mock(Date.class));
        serviceMaster.setDeleted(true);
        serviceMaster.setIsNational(true);
        serviceMaster.setLastModDate(mock(Date.class));
        serviceMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster.setRoleMapping(new StateServiceMapping1());
        serviceMaster.setServiceDesc("Service Desc");
        serviceMaster.setServiceID(1);
        serviceMaster.setServiceName("Service Name");
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
        roleMapping.setServiceMaster(serviceMaster);
        roleMapping.setServiceProviderID(1);
        roleMapping.setStateID(1);
        roleMapping.setStateMaster(stateMaster);
        roleMapping.setStatusID(1);
        roleMapping.setValidFrom(mock(Date.class));
        roleMapping.setValidTill(mock(Date.class));
        ServiceMaster serviceMaster2 = new ServiceMaster();
        serviceMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster2.setCreatedDate(mock(Date.class));
        serviceMaster2.setDeleted(true);
        serviceMaster2.setIsNational(true);
        serviceMaster2.setLastModDate(mock(Date.class));
        serviceMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster2.setRoleMapping(roleMapping);
        serviceMaster2.setServiceDesc("Service Desc");
        serviceMaster2.setServiceID(1);
        serviceMaster2.setServiceName("Service Name");
        ServiceMaster serviceMaster3 = new ServiceMaster();
        serviceMaster3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        serviceMaster3.setCreatedDate(mock(Date.class));
        serviceMaster3.setDeleted(true);
        serviceMaster3.setIsNational(true);
        serviceMaster3.setLastModDate(mock(Date.class));
        serviceMaster3.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        serviceMaster3.setRoleMapping(new StateServiceMapping1());
        serviceMaster3.setServiceDesc("Service Desc");
        serviceMaster3.setServiceID(1);
        serviceMaster3.setServiceName("Service Name");
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
        roleMapping2.setServiceMaster(serviceMaster3);
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
        roleMapping3.setServiceMaster(serviceMaster2);
        roleMapping3.setServiceProviderID(1);
        roleMapping3.setStateID(1);
        roleMapping3.setStateMaster(stateMaster3);
        roleMapping3.setStatusID(1);
        roleMapping3.setValidFrom(mock(Date.class));
        roleMapping3.setValidTill(mock(Date.class));
        state.setRoleMapping(roleMapping3);
        state.setStateCode('A');
        state.setStateID(1);
        state.setStateName("MD");
        state.toString();
        Integer actualCountryID = state.getCountryID();
        String actualCreatedBy = state.getCreatedBy();
        Date actualCreatedDate = state.getCreatedDate();
        Boolean actualDeleted = state.getDeleted();
        Date actualLastModDate = state.getLastModDate();
        String actualModifiedBy = state.getModifiedBy();
        StateServiceMapping1 actualRoleMapping = state.getRoleMapping();
        char actualStateCode = state.getStateCode();
        Integer actualStateID = state.getStateID();

        // Assert that nothing has changed
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("MD", state.getStateName());
        assertEquals('A', actualStateCode);
        assertEquals(1, actualCountryID.intValue());
        assertEquals(1, actualStateID.intValue());
        assertTrue(actualDeleted);
        assertSame(roleMapping3, actualRoleMapping);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of {@link State}
     */
    @Test
    void testNewState() {
        // Arrange and Act
        State actualState = new State();

        // Assert
        assertEquals('\u0000', actualState.getStateCode());
        assertNull(actualState.getRoleMapping());
        assertNull(actualState.getDeleted());
        assertNull(actualState.getCountryID());
        assertNull(actualState.getStateID());
        assertNull(actualState.getCreatedBy());
        assertNull(actualState.getModifiedBy());
        assertNull(actualState.getStateName());
        assertNull(actualState.getCreatedDate());
        assertNull(actualState.getLastModDate());
    }
}
