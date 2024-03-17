package com.iemr.inventory.data.drugtype;

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

@ContextConfiguration(classes = {M_Drugtype.class})
@ExtendWith(SpringExtension.class)
class M_DrugtypeTest {
    @Autowired
    private M_Drugtype m_Drugtype;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_Drugtype#setCreatedBy(String)}
     *   <li>{@link M_Drugtype#setCreatedDate(Date)}
     *   <li>{@link M_Drugtype#setDeleted(Boolean)}
     *   <li>{@link M_Drugtype#setDrugTypeCode(String)}
     *   <li>{@link M_Drugtype#setDrugTypeDesc(String)}
     *   <li>{@link M_Drugtype#setDrugTypeID(Integer)}
     *   <li>{@link M_Drugtype#setDrugTypeName(String)}
     *   <li>{@link M_Drugtype#setLastModDate(Date)}
     *   <li>{@link M_Drugtype#setModifiedBy(String)}
     *   <li>{@link M_Drugtype#setProviderServiceMapID(Integer)}
     *   <li>{@link M_Drugtype#setStatus(String)}
     *   <li>{@link M_Drugtype#toString()}
     *   <li>{@link M_Drugtype#getCreatedBy()}
     *   <li>{@link M_Drugtype#getCreatedDate()}
     *   <li>{@link M_Drugtype#getDeleted()}
     *   <li>{@link M_Drugtype#getDrugTypeCode()}
     *   <li>{@link M_Drugtype#getDrugTypeDesc()}
     *   <li>{@link M_Drugtype#getDrugTypeID()}
     *   <li>{@link M_Drugtype#getDrugTypeName()}
     *   <li>{@link M_Drugtype#getLastModDate()}
     *   <li>{@link M_Drugtype#getModifiedBy()}
     *   <li>{@link M_Drugtype#getProviderServiceMapID()}
     *   <li>{@link M_Drugtype#getStatus()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_Drugtype m_Drugtype = new M_Drugtype();

        // Act
        m_Drugtype.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_Drugtype.setCreatedDate(createdDate);
        m_Drugtype.setDeleted(true);
        m_Drugtype.setDrugTypeCode("Drug Type Code");
        m_Drugtype.setDrugTypeDesc("Drug Type Desc");
        m_Drugtype.setDrugTypeID(1);
        m_Drugtype.setDrugTypeName("Drug Type Name");
        Date lastModDate = mock(Date.class);
        m_Drugtype.setLastModDate(lastModDate);
        m_Drugtype.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_Drugtype.setProviderServiceMapID(1);
        m_Drugtype.setStatus("Status");
        m_Drugtype.toString();
        String actualCreatedBy = m_Drugtype.getCreatedBy();
        Date actualCreatedDate = m_Drugtype.getCreatedDate();
        Boolean actualDeleted = m_Drugtype.getDeleted();
        String actualDrugTypeCode = m_Drugtype.getDrugTypeCode();
        String actualDrugTypeDesc = m_Drugtype.getDrugTypeDesc();
        Integer actualDrugTypeID = m_Drugtype.getDrugTypeID();
        String actualDrugTypeName = m_Drugtype.getDrugTypeName();
        Date actualLastModDate = m_Drugtype.getLastModDate();
        String actualModifiedBy = m_Drugtype.getModifiedBy();
        Integer actualProviderServiceMapID = m_Drugtype.getProviderServiceMapID();

        // Assert that nothing has changed
        assertEquals("Drug Type Code", actualDrugTypeCode);
        assertEquals("Drug Type Desc", actualDrugTypeDesc);
        assertEquals("Drug Type Name", actualDrugTypeName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Status", m_Drugtype.getStatus());
        assertEquals(1, actualDrugTypeID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of {@link M_Drugtype}
     */
    @Test
    void testNewM_Drugtype() {
        // Arrange and Act
        M_Drugtype actualM_Drugtype = new M_Drugtype();

        // Assert
        assertNull(actualM_Drugtype.getDeleted());
        assertNull(actualM_Drugtype.getDrugTypeID());
        assertNull(actualM_Drugtype.getProviderServiceMapID());
        assertNull(actualM_Drugtype.getCreatedBy());
        assertNull(actualM_Drugtype.getDrugTypeCode());
        assertNull(actualM_Drugtype.getDrugTypeDesc());
        assertNull(actualM_Drugtype.getDrugTypeName());
        assertNull(actualM_Drugtype.getModifiedBy());
        assertNull(actualM_Drugtype.getStatus());
        assertNull(actualM_Drugtype.getCreatedDate());
        assertNull(actualM_Drugtype.getLastModDate());
    }
}
