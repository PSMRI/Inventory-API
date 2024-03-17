package com.iemr.inventory.data.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {M_UserServiceRoleMapping.class})
@ExtendWith(SpringExtension.class)
class M_UserServiceRoleMappingTest {
    @Autowired
    private M_UserServiceRoleMapping m_UserServiceRoleMapping;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_UserServiceRoleMapping#setCreatedBy(String)}
     *   <li>{@link M_UserServiceRoleMapping#setCreatedDate(Timestamp)}
     *   <li>{@link M_UserServiceRoleMapping#setDeleted(Boolean)}
     *   <li>{@link M_UserServiceRoleMapping#setLastModDate(Timestamp)}
     *   <li>{@link M_UserServiceRoleMapping#setModifiedBy(String)}
     *   <li>{@link M_UserServiceRoleMapping#setProviderServiceMapID(Integer)}
     *   <li>{@link M_UserServiceRoleMapping#setRoleID(Integer)}
     *   <li>{@link M_UserServiceRoleMapping#setUSRMappingID(Integer)}
     *   <li>{@link M_UserServiceRoleMapping#setUserID(Integer)}
     *   <li>{@link M_UserServiceRoleMapping#toString()}
     *   <li>{@link M_UserServiceRoleMapping#getCreatedBy()}
     *   <li>{@link M_UserServiceRoleMapping#getCreatedDate()}
     *   <li>{@link M_UserServiceRoleMapping#getDeleted()}
     *   <li>{@link M_UserServiceRoleMapping#getLastModDate()}
     *   <li>{@link M_UserServiceRoleMapping#getModifiedBy()}
     *   <li>{@link M_UserServiceRoleMapping#getProviderServiceMapID()}
     *   <li>{@link M_UserServiceRoleMapping#getRoleID()}
     *   <li>{@link M_UserServiceRoleMapping#getUSRMappingID()}
     *   <li>{@link M_UserServiceRoleMapping#getUserID()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_UserServiceRoleMapping m_UserServiceRoleMapping = new M_UserServiceRoleMapping();

        // Act
        m_UserServiceRoleMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        m_UserServiceRoleMapping.setCreatedDate(createdDate);
        m_UserServiceRoleMapping.setDeleted(true);
        Timestamp lastModDate = mock(Timestamp.class);
        m_UserServiceRoleMapping.setLastModDate(lastModDate);
        m_UserServiceRoleMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_UserServiceRoleMapping.setProviderServiceMapID(1);
        m_UserServiceRoleMapping.setRoleID(1);
        m_UserServiceRoleMapping.setUSRMappingID(1);
        m_UserServiceRoleMapping.setUserID(1);
        String actualToStringResult = m_UserServiceRoleMapping.toString();
        String actualCreatedBy = m_UserServiceRoleMapping.getCreatedBy();
        Timestamp actualCreatedDate = m_UserServiceRoleMapping.getCreatedDate();
        Boolean actualDeleted = m_UserServiceRoleMapping.getDeleted();
        Timestamp actualLastModDate = m_UserServiceRoleMapping.getLastModDate();
        String actualModifiedBy = m_UserServiceRoleMapping.getModifiedBy();
        Integer actualProviderServiceMapID = m_UserServiceRoleMapping.getProviderServiceMapID();
        Integer actualRoleID = m_UserServiceRoleMapping.getRoleID();
        Integer actualUSRMappingID = m_UserServiceRoleMapping.getUSRMappingID();
        Integer actualUserID = m_UserServiceRoleMapping.getUserID();

        // Assert that nothing has changed
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("{}", actualToStringResult);
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualRoleID.intValue());
        assertEquals(1, actualUSRMappingID.intValue());
        assertEquals(1, actualUserID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test:
     * {@link M_UserServiceRoleMapping#M_UserServiceRoleMapping()}
     */
    @Test
    void testNewM_UserServiceRoleMapping() {
        // Arrange and Act
        M_UserServiceRoleMapping actualM_UserServiceRoleMapping = new M_UserServiceRoleMapping();

        // Assert
        assertNull(actualM_UserServiceRoleMapping.getDeleted());
        assertNull(actualM_UserServiceRoleMapping.getProviderServiceMapID());
        assertNull(actualM_UserServiceRoleMapping.getRoleID());
        assertNull(actualM_UserServiceRoleMapping.getUSRMappingID());
        assertNull(actualM_UserServiceRoleMapping.getUserID());
        assertNull(actualM_UserServiceRoleMapping.getCreatedBy());
        assertNull(actualM_UserServiceRoleMapping.getModifiedBy());
        assertNull(actualM_UserServiceRoleMapping.getCreatedDate());
        assertNull(actualM_UserServiceRoleMapping.getLastModDate());
    }

    /**
     * Method under test:
     * {@link M_UserServiceRoleMapping#M_UserServiceRoleMapping(Integer, Integer, Integer, Integer, Integer, Boolean, String, Timestamp, String, Timestamp)}
     */
    @Test
    void testNewM_UserServiceRoleMapping2() {
        // Arrange
        Timestamp createdDate = mock(Timestamp.class);
        Timestamp lastModDate = mock(Timestamp.class);

        // Act
        M_UserServiceRoleMapping actualM_UserServiceRoleMapping = new M_UserServiceRoleMapping(1, 1, 1, 1, 1, true,
                "Jan 1, 2020 8:00am GMT+0100", createdDate, "Jan 1, 2020 9:00am GMT+0100", lastModDate);

        // Assert
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualM_UserServiceRoleMapping.getCreatedBy());
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualM_UserServiceRoleMapping.getModifiedBy());
        assertEquals(1, actualM_UserServiceRoleMapping.getProviderServiceMapID().intValue());
        assertEquals(1, actualM_UserServiceRoleMapping.getRoleID().intValue());
        assertEquals(1, actualM_UserServiceRoleMapping.getUSRMappingID().intValue());
        assertEquals(1, actualM_UserServiceRoleMapping.getUserID().intValue());
        assertTrue(actualM_UserServiceRoleMapping.getDeleted());
        assertSame(createdDate, actualM_UserServiceRoleMapping.getCreatedDate());
        assertSame(lastModDate, actualM_UserServiceRoleMapping.getLastModDate());
    }
}
