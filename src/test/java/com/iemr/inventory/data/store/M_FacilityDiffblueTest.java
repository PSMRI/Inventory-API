package com.iemr.inventory.data.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class M_FacilityDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_Facility#setCreatedBy(String)}
     *   <li>{@link M_Facility#setCreatedDate(Date)}
     *   <li>{@link M_Facility#setDeleted(Boolean)}
     *   <li>{@link M_Facility#setFacilityCode(String)}
     *   <li>{@link M_Facility#setFacilityDesc(String)}
     *   <li>{@link M_Facility#setFacilityID(Integer)}
     *   <li>{@link M_Facility#setFacilityName(String)}
     *   <li>{@link M_Facility#setFacilityTypeID(Integer)}
     *   <li>{@link M_Facility#setIsMainFacility(Boolean)}
     *   <li>{@link M_Facility#setLastModDate(Date)}
     *   <li>{@link M_Facility#setLocation(String)}
     *   <li>{@link M_Facility#setMainFacilityID(Integer)}
     *   <li>{@link M_Facility#setModifiedBy(String)}
     *   <li>{@link M_Facility#setPhysicalLocation(String)}
     *   <li>{@link M_Facility#setProcessed(Character)}
     *   <li>{@link M_Facility#setProviderServiceMapID(Integer)}
     *   <li>{@link M_Facility#setStatus(String)}
     *   <li>{@link M_Facility#setStoreType(String)}
     *   <li>{@link M_Facility#toString()}
     *   <li>{@link M_Facility#getCreatedBy()}
     *   <li>{@link M_Facility#getCreatedDate()}
     *   <li>{@link M_Facility#getDeleted()}
     *   <li>{@link M_Facility#getFacilityCode()}
     *   <li>{@link M_Facility#getFacilityDesc()}
     *   <li>{@link M_Facility#getFacilityID()}
     *   <li>{@link M_Facility#getFacilityName()}
     *   <li>{@link M_Facility#getFacilityTypeID()}
     *   <li>{@link M_Facility#getIsMainFacility()}
     *   <li>{@link M_Facility#getLastModDate()}
     *   <li>{@link M_Facility#getLocation()}
     *   <li>{@link M_Facility#getMainFacilityID()}
     *   <li>{@link M_Facility#getModifiedBy()}
     *   <li>{@link M_Facility#getPhysicalLocation()}
     *   <li>{@link M_Facility#getProcessed()}
     *   <li>{@link M_Facility#getProviderServiceMapID()}
     *   <li>{@link M_Facility#getStatus()}
     *   <li>{@link M_Facility#getStoreType()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_Facility m_Facility = new M_Facility();

        // Act
        m_Facility.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_Facility.setCreatedDate(createdDate);
        m_Facility.setDeleted(true);
        m_Facility.setFacilityCode("Facility Code");
        m_Facility.setFacilityDesc("Facility Desc");
        m_Facility.setFacilityID(1);
        m_Facility.setFacilityName("Facility Name");
        m_Facility.setFacilityTypeID(1);
        m_Facility.setIsMainFacility(true);
        Date lastModDate = mock(Date.class);
        m_Facility.setLastModDate(lastModDate);
        m_Facility.setLocation("Location");
        m_Facility.setMainFacilityID(1);
        m_Facility.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_Facility.setPhysicalLocation("Physical Location");
        m_Facility.setProcessed('A');
        m_Facility.setProviderServiceMapID(1);
        m_Facility.setStatus("Status");
        m_Facility.setStoreType("Store Type");
        m_Facility.toString();
        String actualCreatedBy = m_Facility.getCreatedBy();
        Date actualCreatedDate = m_Facility.getCreatedDate();
        Boolean actualDeleted = m_Facility.getDeleted();
        String actualFacilityCode = m_Facility.getFacilityCode();
        String actualFacilityDesc = m_Facility.getFacilityDesc();
        Integer actualFacilityID = m_Facility.getFacilityID();
        String actualFacilityName = m_Facility.getFacilityName();
        Integer actualFacilityTypeID = m_Facility.getFacilityTypeID();
        Boolean actualIsMainFacility = m_Facility.getIsMainFacility();
        Date actualLastModDate = m_Facility.getLastModDate();
        String actualLocation = m_Facility.getLocation();
        Integer actualMainFacilityID = m_Facility.getMainFacilityID();
        String actualModifiedBy = m_Facility.getModifiedBy();
        String actualPhysicalLocation = m_Facility.getPhysicalLocation();
        Character actualProcessed = m_Facility.getProcessed();
        Integer actualProviderServiceMapID = m_Facility.getProviderServiceMapID();
        String actualStatus = m_Facility.getStatus();

        // Assert that nothing has changed
        assertEquals("Facility Code", actualFacilityCode);
        assertEquals("Facility Desc", actualFacilityDesc);
        assertEquals("Facility Name", actualFacilityName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Location", actualLocation);
        assertEquals("Physical Location", actualPhysicalLocation);
        assertEquals("Status", actualStatus);
        assertEquals("Store Type", m_Facility.getStoreType());
        assertEquals('A', actualProcessed.charValue());
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualFacilityTypeID.intValue());
        assertEquals(1, actualMainFacilityID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertTrue(actualDeleted);
        assertTrue(actualIsMainFacility);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }
}
