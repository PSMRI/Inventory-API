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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {M_ProviderServiceAddMapping.class})
@ExtendWith(SpringExtension.class)
class M_ProviderServiceAddMappingTest {
    @Autowired
    private M_ProviderServiceAddMapping m_ProviderServiceAddMapping;

    /**
     * Method under test: {@link M_ProviderServiceAddMapping#getpSAddMapID()}
     */
    @Test
    void testGetpSAddMapID() {
        // Arrange, Act and Assert
        assertNull((new M_ProviderServiceAddMapping()).getpSAddMapID());
    }

    /**
     * Method under test: {@link M_ProviderServiceAddMapping#getpSAddMapID()}
     */
    @Test
    void testGetpSAddMapID2() {
        // Arrange
        M_ProviderServiceAddMapping m_ProviderServiceAddMapping2 = new M_ProviderServiceAddMapping();
        m_ProviderServiceAddMapping2.setCreatedDate(mock(Date.class));

        // Act and Assert
        assertNull(m_ProviderServiceAddMapping2.getpSAddMapID());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_ProviderServiceAddMapping#setAddress(String)}
     *   <li>{@link M_ProviderServiceAddMapping#setCreatedBy(String)}
     *   <li>{@link M_ProviderServiceAddMapping#setCreatedDate(Date)}
     *   <li>{@link M_ProviderServiceAddMapping#setDeleted(Boolean)}
     *   <li>{@link M_ProviderServiceAddMapping#setDistrictID(Integer)}
     *   <li>{@link M_ProviderServiceAddMapping#setLastModDate(Date)}
     *   <li>{@link M_ProviderServiceAddMapping#setLocationName(String)}
     *   <li>{@link M_ProviderServiceAddMapping#setModifiedBy(String)}
     *   <li>{@link M_ProviderServiceAddMapping#setProviderServiceMapID(Integer)}
     *   <li>{@link M_ProviderServiceAddMapping#setpSAddMapID(Integer)}
     *   <li>{@link M_ProviderServiceAddMapping#toString()}
     *   <li>{@link M_ProviderServiceAddMapping#getAddress()}
     *   <li>{@link M_ProviderServiceAddMapping#getCreatedBy()}
     *   <li>{@link M_ProviderServiceAddMapping#getCreatedDate()}
     *   <li>{@link M_ProviderServiceAddMapping#getDeleted()}
     *   <li>{@link M_ProviderServiceAddMapping#getDistrictID()}
     *   <li>{@link M_ProviderServiceAddMapping#getLastModDate()}
     *   <li>{@link M_ProviderServiceAddMapping#getLocationName()}
     *   <li>{@link M_ProviderServiceAddMapping#getModifiedBy()}
     *   <li>{@link M_ProviderServiceAddMapping#getProviderServiceMapID()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_ProviderServiceAddMapping m_ProviderServiceAddMapping = new M_ProviderServiceAddMapping();

        // Act
        m_ProviderServiceAddMapping.setAddress("42 Main St");
        m_ProviderServiceAddMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_ProviderServiceAddMapping.setCreatedDate(createdDate);
        m_ProviderServiceAddMapping.setDeleted(true);
        m_ProviderServiceAddMapping.setDistrictID(1);
        Date lastModDate = mock(Date.class);
        m_ProviderServiceAddMapping.setLastModDate(lastModDate);
        m_ProviderServiceAddMapping.setLocationName("Location Name");
        m_ProviderServiceAddMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_ProviderServiceAddMapping.setProviderServiceMapID(1);
        m_ProviderServiceAddMapping.setpSAddMapID(1);
        m_ProviderServiceAddMapping.toString();
        String actualAddress = m_ProviderServiceAddMapping.getAddress();
        String actualCreatedBy = m_ProviderServiceAddMapping.getCreatedBy();
        Date actualCreatedDate = m_ProviderServiceAddMapping.getCreatedDate();
        Boolean actualDeleted = m_ProviderServiceAddMapping.getDeleted();
        Integer actualDistrictID = m_ProviderServiceAddMapping.getDistrictID();
        Date actualLastModDate = m_ProviderServiceAddMapping.getLastModDate();
        String actualLocationName = m_ProviderServiceAddMapping.getLocationName();
        String actualModifiedBy = m_ProviderServiceAddMapping.getModifiedBy();
        Integer actualProviderServiceMapID = m_ProviderServiceAddMapping.getProviderServiceMapID();

        // Assert that nothing has changed
        assertEquals("42 Main St", actualAddress);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Location Name", actualLocationName);
        assertEquals(1, actualDistrictID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link M_ProviderServiceAddMapping}
     */
    @Test
    void testNewM_ProviderServiceAddMapping() {
        // Arrange and Act
        M_ProviderServiceAddMapping actualM_ProviderServiceAddMapping = new M_ProviderServiceAddMapping();

        // Assert
        assertNull(actualM_ProviderServiceAddMapping.getDeleted());
        assertNull(actualM_ProviderServiceAddMapping.getDistrictID());
        assertNull(actualM_ProviderServiceAddMapping.getProviderServiceMapID());
        assertNull(actualM_ProviderServiceAddMapping.getAddress());
        assertNull(actualM_ProviderServiceAddMapping.getCreatedBy());
        assertNull(actualM_ProviderServiceAddMapping.getLocationName());
        assertNull(actualM_ProviderServiceAddMapping.getModifiedBy());
        assertNull(actualM_ProviderServiceAddMapping.getCreatedDate());
        assertNull(actualM_ProviderServiceAddMapping.getLastModDate());
    }
}
