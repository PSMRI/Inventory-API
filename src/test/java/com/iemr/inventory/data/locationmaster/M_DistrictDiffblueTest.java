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

@ContextConfiguration(classes = {M_District.class})
@ExtendWith(SpringExtension.class)
class M_DistrictTest {
    @Autowired
    private M_District m_District;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_District#setCreatedBy(String)}
     *   <li>{@link M_District#setCreatedDate(Date)}
     *   <li>{@link M_District#setDeleted(Boolean)}
     *   <li>{@link M_District#setDistrictID(Integer)}
     *   <li>{@link M_District#setDistrictName(String)}
     *   <li>{@link M_District#setLastModDate(Date)}
     *   <li>{@link M_District#setModifiedBy(String)}
     *   <li>{@link M_District#setStateID(Integer)}
     *   <li>{@link M_District#toString()}
     *   <li>{@link M_District#getCreatedBy()}
     *   <li>{@link M_District#getCreatedDate()}
     *   <li>{@link M_District#getDeleted()}
     *   <li>{@link M_District#getDistrictID()}
     *   <li>{@link M_District#getDistrictName()}
     *   <li>{@link M_District#getLastModDate()}
     *   <li>{@link M_District#getModifiedBy()}
     *   <li>{@link M_District#getStateID()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_District m_District = new M_District();

        // Act
        m_District.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_District.setCreatedDate(createdDate);
        m_District.setDeleted(true);
        m_District.setDistrictID(1);
        m_District.setDistrictName("District Name");
        Date lastModDate = mock(Date.class);
        m_District.setLastModDate(lastModDate);
        m_District.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_District.setStateID(1);
        m_District.toString();
        String actualCreatedBy = m_District.getCreatedBy();
        Date actualCreatedDate = m_District.getCreatedDate();
        Boolean actualDeleted = m_District.getDeleted();
        Integer actualDistrictID = m_District.getDistrictID();
        String actualDistrictName = m_District.getDistrictName();
        Date actualLastModDate = m_District.getLastModDate();
        String actualModifiedBy = m_District.getModifiedBy();
        Integer actualStateID = m_District.getStateID();

        // Assert that nothing has changed
        assertEquals("District Name", actualDistrictName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals(1, actualDistrictID.intValue());
        assertEquals(1, actualStateID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: {@link M_District#M_District()}
     */
    @Test
    void testNewM_District() {
        // Arrange and Act
        M_District actualM_District = new M_District();

        // Assert
        assertNull(actualM_District.getDeleted());
        assertNull(actualM_District.getDistrictID());
        assertNull(actualM_District.getStateID());
        assertNull(actualM_District.getCreatedBy());
        assertNull(actualM_District.getDistrictName());
        assertNull(actualM_District.getModifiedBy());
        assertNull(actualM_District.getCreatedDate());
        assertNull(actualM_District.getLastModDate());
    }

    /**
     * Method under test: {@link M_District#M_District(Integer, String)}
     */
    @Test
    void testNewM_District2() {
        // Arrange and Act
        M_District actualM_District = new M_District(1, "District Name");

        // Assert
        assertEquals("District Name", actualM_District.getDistrictName());
        assertEquals(1, actualM_District.getDistrictID().intValue());
    }
}
