package com.iemr.inventory.to.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.iemr.inventory.data.locationmaster.M_District;
import com.iemr.inventory.data.locationmaster.ServiceMaster;
import com.iemr.inventory.data.locationmaster.State;
import com.iemr.inventory.data.locationmaster.StateServiceMapping1;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class ProviderServiceMappingTOTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ProviderServiceMappingTO#setAddress(String)}
     *   <li>{@link ProviderServiceMappingTO#setCityID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#setCountryID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#setCreatedBy(String)}
     *   <li>{@link ProviderServiceMappingTO#setCreatedDate(Timestamp)}
     *   <li>{@link ProviderServiceMappingTO#setDeleted(Boolean)}
     *   <li>{@link ProviderServiceMappingTO#setDistrictBlockID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#setDistrictID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#setLastModDate(Timestamp)}
     *   <li>{@link ProviderServiceMappingTO#setM_district(M_District)}
     *   <li>{@link ProviderServiceMappingTO#setModifiedBy(String)}
     *   <li>{@link ProviderServiceMappingTO#setProviderServiceMapID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#setServiceID(Integer[])}
     *   <li>{@link ProviderServiceMappingTO#setServiceProviderID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#setState(State)}
     *   <li>{@link ProviderServiceMappingTO#setStateID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#setStatusID(Integer)}
     *   <li>{@link ProviderServiceMappingTO#toString()}
     *   <li>{@link ProviderServiceMappingTO#getAddress()}
     *   <li>{@link ProviderServiceMappingTO#getCityID()}
     *   <li>{@link ProviderServiceMappingTO#getCountryID()}
     *   <li>{@link ProviderServiceMappingTO#getCreatedBy()}
     *   <li>{@link ProviderServiceMappingTO#getCreatedDate()}
     *   <li>{@link ProviderServiceMappingTO#getDeleted()}
     *   <li>{@link ProviderServiceMappingTO#getDistrictBlockID()}
     *   <li>{@link ProviderServiceMappingTO#getDistrictID()}
     *   <li>{@link ProviderServiceMappingTO#getLastModDate()}
     *   <li>{@link ProviderServiceMappingTO#getM_district()}
     *   <li>{@link ProviderServiceMappingTO#getModifiedBy()}
     *   <li>{@link ProviderServiceMappingTO#getProviderServiceMapID()}
     *   <li>{@link ProviderServiceMappingTO#getServiceID()}
     *   <li>{@link ProviderServiceMappingTO#getServiceProviderID()}
     *   <li>{@link ProviderServiceMappingTO#getState()}
     *   <li>{@link ProviderServiceMappingTO#getStateID()}
     *   <li>{@link ProviderServiceMappingTO#getStatusID()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        ProviderServiceMappingTO providerServiceMappingTO = new ProviderServiceMappingTO();

        // Act
        providerServiceMappingTO.setAddress("42 Main St");
        providerServiceMappingTO.setCityID(1);
        providerServiceMappingTO.setCountryID(1);
        providerServiceMappingTO.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        providerServiceMappingTO.setCreatedDate(createdDate);
        providerServiceMappingTO.setDeleted(true);
        providerServiceMappingTO.setDistrictBlockID(1);
        providerServiceMappingTO.setDistrictID(1);
        Timestamp lastModDate = mock(Timestamp.class);
        providerServiceMappingTO.setLastModDate(lastModDate);
        M_District m_district = new M_District();
        m_district.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        m_district.setCreatedDate(mock(Date.class));
        m_district.setDeleted(true);
        m_district.setDistrictID(1);
        m_district.setDistrictName("District Name");
        m_district.setLastModDate(mock(Date.class));
        m_district.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_district.setStateID(1);
        providerServiceMappingTO.setM_district(m_district);
        providerServiceMappingTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        providerServiceMappingTO.setProviderServiceMapID(1);
        Integer[] serviceID = new Integer[]{1};
        providerServiceMappingTO.setServiceID(serviceID);
        providerServiceMappingTO.setServiceProviderID(1);
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
        State state = new State();
        state.setCountryID(1);
        state.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        state.setCreatedDate(mock(Date.class));
        state.setDeleted(true);
        state.setLastModDate(mock(Date.class));
        state.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        state.setRoleMapping(roleMapping3);
        state.setStateCode('A');
        state.setStateID(1);
        state.setStateName("MD");
        providerServiceMappingTO.setState(state);
        providerServiceMappingTO.setStateID(1);
        providerServiceMappingTO.setStatusID(1);
        providerServiceMappingTO.toString();
        String actualAddress = providerServiceMappingTO.getAddress();
        Integer actualCityID = providerServiceMappingTO.getCityID();
        Integer actualCountryID = providerServiceMappingTO.getCountryID();
        String actualCreatedBy = providerServiceMappingTO.getCreatedBy();
        Timestamp actualCreatedDate = providerServiceMappingTO.getCreatedDate();
        Boolean actualDeleted = providerServiceMappingTO.getDeleted();
        Integer actualDistrictBlockID = providerServiceMappingTO.getDistrictBlockID();
        Integer actualDistrictID = providerServiceMappingTO.getDistrictID();
        Timestamp actualLastModDate = providerServiceMappingTO.getLastModDate();
        M_District actualM_district = providerServiceMappingTO.getM_district();
        String actualModifiedBy = providerServiceMappingTO.getModifiedBy();
        Integer actualProviderServiceMapID = providerServiceMappingTO.getProviderServiceMapID();
        Integer[] actualServiceID = providerServiceMappingTO.getServiceID();
        Integer actualServiceProviderID = providerServiceMappingTO.getServiceProviderID();
        State actualState = providerServiceMappingTO.getState();
        Integer actualStateID = providerServiceMappingTO.getStateID();
        Integer actualStatusID = providerServiceMappingTO.getStatusID();

        // Assert that nothing has changed
        assertEquals("42 Main St", actualAddress);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals(1, actualCityID.intValue());
        assertEquals(1, actualCountryID.intValue());
        assertEquals(1, actualDistrictBlockID.intValue());
        assertEquals(1, actualDistrictID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualServiceProviderID.intValue());
        assertEquals(1, actualStateID.intValue());
        assertEquals(1, actualStatusID.intValue());
        assertTrue(actualDeleted);
        assertSame(m_district, actualM_district);
        assertSame(state, actualState);
        assertSame(serviceID, actualServiceID);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }
}
