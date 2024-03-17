package com.iemr.inventory.data.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class M_FacilityWithMapDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_FacilityWithMap#setCreatedBy(String)}
     *   <li>{@link M_FacilityWithMap#setCreatedDate(Date)}
     *   <li>{@link M_FacilityWithMap#setDeleted(Boolean)}
     *   <li>{@link M_FacilityWithMap#setFacilityCode(String)}
     *   <li>{@link M_FacilityWithMap#setFacilityDesc(String)}
     *   <li>{@link M_FacilityWithMap#setFacilityID(Integer)}
     *   <li>{@link M_FacilityWithMap#setFacilityName(String)}
     *   <li>{@link M_FacilityWithMap#setFacilityTypeID(Integer)}
     *   <li>{@link M_FacilityWithMap#setIsMainFacility(Boolean)}
     *   <li>{@link M_FacilityWithMap#setLastModDate(Date)}
     *   <li>{@link M_FacilityWithMap#setLocation(String)}
     *   <li>{@link M_FacilityWithMap#setMainFacilityID(Integer)}
     *   <li>{@link M_FacilityWithMap#setModifiedBy(String)}
     *   <li>{@link M_FacilityWithMap#setPhysicalLocation(String)}
     *   <li>{@link M_FacilityWithMap#setProcessed(Character)}
     *   <li>{@link M_FacilityWithMap#setProviderServiceMapID(Integer)}
     *   <li>{@link M_FacilityWithMap#setStatus(String)}
     *   <li>{@link M_FacilityWithMap#setStoreType(String)}
     *   <li>{@link M_FacilityWithMap#toString()}
     *   <li>{@link M_FacilityWithMap#getCreatedBy()}
     *   <li>{@link M_FacilityWithMap#getCreatedDate()}
     *   <li>{@link M_FacilityWithMap#getDeleted()}
     *   <li>{@link M_FacilityWithMap#getFacilityCode()}
     *   <li>{@link M_FacilityWithMap#getFacilityDesc()}
     *   <li>{@link M_FacilityWithMap#getFacilityID()}
     *   <li>{@link M_FacilityWithMap#getFacilityName()}
     *   <li>{@link M_FacilityWithMap#getFacilityTypeID()}
     *   <li>{@link M_FacilityWithMap#getIsMainFacility()}
     *   <li>{@link M_FacilityWithMap#getLastModDate()}
     *   <li>{@link M_FacilityWithMap#getLocation()}
     *   <li>{@link M_FacilityWithMap#getMainFacilityID()}
     *   <li>{@link M_FacilityWithMap#getModifiedBy()}
     *   <li>{@link M_FacilityWithMap#getPhysicalLocation()}
     *   <li>{@link M_FacilityWithMap#getProcessed()}
     *   <li>{@link M_FacilityWithMap#getProviderServiceMapID()}
     *   <li>{@link M_FacilityWithMap#getStatus()}
     *   <li>{@link M_FacilityWithMap#getStoreType()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_FacilityWithMap m_FacilityWithMap = new M_FacilityWithMap();

        // Act
        m_FacilityWithMap.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_FacilityWithMap.setCreatedDate(createdDate);
        m_FacilityWithMap.setDeleted(true);
        m_FacilityWithMap.setFacilityCode("Facility Code");
        m_FacilityWithMap.setFacilityDesc("Facility Desc");
        m_FacilityWithMap.setFacilityID(1);
        m_FacilityWithMap.setFacilityName("Facility Name");
        m_FacilityWithMap.setFacilityTypeID(1);
        m_FacilityWithMap.setIsMainFacility(true);
        Date lastModDate = mock(Date.class);
        m_FacilityWithMap.setLastModDate(lastModDate);
        m_FacilityWithMap.setLocation("Location");
        m_FacilityWithMap.setMainFacilityID(1);
        m_FacilityWithMap.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_FacilityWithMap.setPhysicalLocation("Physical Location");
        m_FacilityWithMap.setProcessed('A');
        m_FacilityWithMap.setProviderServiceMapID(1);
        m_FacilityWithMap.setStatus("Status");
        m_FacilityWithMap.setStoreType("Store Type");
        m_FacilityWithMap.toString();
        String actualCreatedBy = m_FacilityWithMap.getCreatedBy();
        Date actualCreatedDate = m_FacilityWithMap.getCreatedDate();
        Boolean actualDeleted = m_FacilityWithMap.getDeleted();
        String actualFacilityCode = m_FacilityWithMap.getFacilityCode();
        String actualFacilityDesc = m_FacilityWithMap.getFacilityDesc();
        Integer actualFacilityID = m_FacilityWithMap.getFacilityID();
        String actualFacilityName = m_FacilityWithMap.getFacilityName();
        Integer actualFacilityTypeID = m_FacilityWithMap.getFacilityTypeID();
        Boolean actualIsMainFacility = m_FacilityWithMap.getIsMainFacility();
        Date actualLastModDate = m_FacilityWithMap.getLastModDate();
        String actualLocation = m_FacilityWithMap.getLocation();
        Integer actualMainFacilityID = m_FacilityWithMap.getMainFacilityID();
        String actualModifiedBy = m_FacilityWithMap.getModifiedBy();
        String actualPhysicalLocation = m_FacilityWithMap.getPhysicalLocation();
        Character actualProcessed = m_FacilityWithMap.getProcessed();
        Integer actualProviderServiceMapID = m_FacilityWithMap.getProviderServiceMapID();
        String actualStatus = m_FacilityWithMap.getStatus();

        // Assert that nothing has changed
        assertEquals("Facility Code", actualFacilityCode);
        assertEquals("Facility Desc", actualFacilityDesc);
        assertEquals("Facility Name", actualFacilityName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Location", actualLocation);
        assertEquals("Physical Location", actualPhysicalLocation);
        assertEquals("Status", actualStatus);
        assertEquals("Store Type", m_FacilityWithMap.getStoreType());
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
