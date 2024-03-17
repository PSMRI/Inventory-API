package com.iemr.inventory.data.facilitytype;

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

@ContextConfiguration(classes = {M_facilitytype.class})
@ExtendWith(SpringExtension.class)
class M_facilitytypeTest {
    @Autowired
    private M_facilitytype m_facilitytype;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_facilitytype#setCreatedBy(String)}
     *   <li>{@link M_facilitytype#setCreatedDate(Date)}
     *   <li>{@link M_facilitytype#setDeleted(Boolean)}
     *   <li>{@link M_facilitytype#setFacilityTypeCode(String)}
     *   <li>{@link M_facilitytype#setFacilityTypeDesc(String)}
     *   <li>{@link M_facilitytype#setFacilityTypeID(Integer)}
     *   <li>{@link M_facilitytype#setFacilityTypeName(String)}
     *   <li>{@link M_facilitytype#setLastModDate(Date)}
     *   <li>{@link M_facilitytype#setModifiedBy(String)}
     *   <li>{@link M_facilitytype#setProviderServiceMapID(Integer)}
     *   <li>{@link M_facilitytype#setStatus(String)}
     *   <li>{@link M_facilitytype#toString()}
     *   <li>{@link M_facilitytype#getCreatedBy()}
     *   <li>{@link M_facilitytype#getCreatedDate()}
     *   <li>{@link M_facilitytype#getDeleted()}
     *   <li>{@link M_facilitytype#getFacilityTypeCode()}
     *   <li>{@link M_facilitytype#getFacilityTypeDesc()}
     *   <li>{@link M_facilitytype#getFacilityTypeID()}
     *   <li>{@link M_facilitytype#getFacilityTypeName()}
     *   <li>{@link M_facilitytype#getLastModDate()}
     *   <li>{@link M_facilitytype#getModifiedBy()}
     *   <li>{@link M_facilitytype#getProviderServiceMapID()}
     *   <li>{@link M_facilitytype#getStatus()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_facilitytype m_facilitytype = new M_facilitytype();

        // Act
        m_facilitytype.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_facilitytype.setCreatedDate(createdDate);
        m_facilitytype.setDeleted(true);
        m_facilitytype.setFacilityTypeCode("Facility Type Code");
        m_facilitytype.setFacilityTypeDesc("Facility Type Desc");
        m_facilitytype.setFacilityTypeID(1);
        m_facilitytype.setFacilityTypeName("Facility Type Name");
        Date lastModDate = mock(Date.class);
        m_facilitytype.setLastModDate(lastModDate);
        m_facilitytype.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_facilitytype.setProviderServiceMapID(1);
        m_facilitytype.setStatus("Status");
        m_facilitytype.toString();
        String actualCreatedBy = m_facilitytype.getCreatedBy();
        Date actualCreatedDate = m_facilitytype.getCreatedDate();
        Boolean actualDeleted = m_facilitytype.getDeleted();
        String actualFacilityTypeCode = m_facilitytype.getFacilityTypeCode();
        String actualFacilityTypeDesc = m_facilitytype.getFacilityTypeDesc();
        Integer actualFacilityTypeID = m_facilitytype.getFacilityTypeID();
        String actualFacilityTypeName = m_facilitytype.getFacilityTypeName();
        Date actualLastModDate = m_facilitytype.getLastModDate();
        String actualModifiedBy = m_facilitytype.getModifiedBy();
        Integer actualProviderServiceMapID = m_facilitytype.getProviderServiceMapID();

        // Assert that nothing has changed
        assertEquals("Facility Type Code", actualFacilityTypeCode);
        assertEquals("Facility Type Desc", actualFacilityTypeDesc);
        assertEquals("Facility Type Name", actualFacilityTypeName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Status", m_facilitytype.getStatus());
        assertEquals(1, actualFacilityTypeID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link M_facilitytype}
     */
    @Test
    void testNewM_facilitytype() {
        // Arrange and Act
        M_facilitytype actualM_facilitytype = new M_facilitytype();

        // Assert
        assertNull(actualM_facilitytype.getDeleted());
        assertNull(actualM_facilitytype.getFacilityTypeID());
        assertNull(actualM_facilitytype.getProviderServiceMapID());
        assertNull(actualM_facilitytype.getCreatedBy());
        assertNull(actualM_facilitytype.getFacilityTypeCode());
        assertNull(actualM_facilitytype.getFacilityTypeDesc());
        assertNull(actualM_facilitytype.getFacilityTypeName());
        assertNull(actualM_facilitytype.getModifiedBy());
        assertNull(actualM_facilitytype.getStatus());
        assertNull(actualM_facilitytype.getCreatedDate());
        assertNull(actualM_facilitytype.getLastModDate());
    }
}
