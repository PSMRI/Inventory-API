package com.iemr.inventory.data.pharmacologicalcategory;

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

@ContextConfiguration(classes = {M_Pharmacologicalcategory.class})
@ExtendWith(SpringExtension.class)
class M_PharmacologicalcategoryTest {
    @Autowired
    private M_Pharmacologicalcategory m_Pharmacologicalcategory;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_Pharmacologicalcategory#setCreatedBy(String)}
     *   <li>{@link M_Pharmacologicalcategory#setCreatedDate(Date)}
     *   <li>{@link M_Pharmacologicalcategory#setDeleted(Boolean)}
     *   <li>{@link M_Pharmacologicalcategory#setLastModDate(Date)}
     *   <li>{@link M_Pharmacologicalcategory#setModifiedBy(String)}
     *   <li>{@link M_Pharmacologicalcategory#setPharmCategoryCode(String)}
     *   <li>{@link M_Pharmacologicalcategory#setPharmCategoryDesc(String)}
     *   <li>{@link M_Pharmacologicalcategory#setPharmCategoryID(Integer)}
     *   <li>{@link M_Pharmacologicalcategory#setPharmCategoryName(String)}
     *   <li>{@link M_Pharmacologicalcategory#setProviderServiceMapID(Integer)}
     *   <li>{@link M_Pharmacologicalcategory#setStatus(String)}
     *   <li>{@link M_Pharmacologicalcategory#toString()}
     *   <li>{@link M_Pharmacologicalcategory#getCreatedBy()}
     *   <li>{@link M_Pharmacologicalcategory#getCreatedDate()}
     *   <li>{@link M_Pharmacologicalcategory#getDeleted()}
     *   <li>{@link M_Pharmacologicalcategory#getLastModDate()}
     *   <li>{@link M_Pharmacologicalcategory#getModifiedBy()}
     *   <li>{@link M_Pharmacologicalcategory#getPharmCategoryCode()}
     *   <li>{@link M_Pharmacologicalcategory#getPharmCategoryDesc()}
     *   <li>{@link M_Pharmacologicalcategory#getPharmCategoryID()}
     *   <li>{@link M_Pharmacologicalcategory#getPharmCategoryName()}
     *   <li>{@link M_Pharmacologicalcategory#getProviderServiceMapID()}
     *   <li>{@link M_Pharmacologicalcategory#getStatus()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_Pharmacologicalcategory m_Pharmacologicalcategory = new M_Pharmacologicalcategory();

        // Act
        m_Pharmacologicalcategory.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_Pharmacologicalcategory.setCreatedDate(createdDate);
        m_Pharmacologicalcategory.setDeleted(true);
        Date lastModDate = mock(Date.class);
        m_Pharmacologicalcategory.setLastModDate(lastModDate);
        m_Pharmacologicalcategory.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_Pharmacologicalcategory.setPharmCategoryCode("Pharm Category Code");
        m_Pharmacologicalcategory.setPharmCategoryDesc("Pharm Category Desc");
        m_Pharmacologicalcategory.setPharmCategoryID(1);
        m_Pharmacologicalcategory.setPharmCategoryName("Pharm Category Name");
        m_Pharmacologicalcategory.setProviderServiceMapID(1);
        m_Pharmacologicalcategory.setStatus("Status");
        m_Pharmacologicalcategory.toString();
        String actualCreatedBy = m_Pharmacologicalcategory.getCreatedBy();
        Date actualCreatedDate = m_Pharmacologicalcategory.getCreatedDate();
        Boolean actualDeleted = m_Pharmacologicalcategory.getDeleted();
        Date actualLastModDate = m_Pharmacologicalcategory.getLastModDate();
        String actualModifiedBy = m_Pharmacologicalcategory.getModifiedBy();
        String actualPharmCategoryCode = m_Pharmacologicalcategory.getPharmCategoryCode();
        String actualPharmCategoryDesc = m_Pharmacologicalcategory.getPharmCategoryDesc();
        Integer actualPharmCategoryID = m_Pharmacologicalcategory.getPharmCategoryID();
        String actualPharmCategoryName = m_Pharmacologicalcategory.getPharmCategoryName();
        Integer actualProviderServiceMapID = m_Pharmacologicalcategory.getProviderServiceMapID();

        // Assert that nothing has changed
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Pharm Category Code", actualPharmCategoryCode);
        assertEquals("Pharm Category Desc", actualPharmCategoryDesc);
        assertEquals("Pharm Category Name", actualPharmCategoryName);
        assertEquals("Status", m_Pharmacologicalcategory.getStatus());
        assertEquals(1, actualPharmCategoryID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link M_Pharmacologicalcategory}
     */
    @Test
    void testNewM_Pharmacologicalcategory() {
        // Arrange and Act
        M_Pharmacologicalcategory actualM_Pharmacologicalcategory = new M_Pharmacologicalcategory();

        // Assert
        assertNull(actualM_Pharmacologicalcategory.getDeleted());
        assertNull(actualM_Pharmacologicalcategory.getPharmCategoryID());
        assertNull(actualM_Pharmacologicalcategory.getPharmacologyCategoryID());
        assertNull(actualM_Pharmacologicalcategory.getProviderServiceMapID());
        assertNull(actualM_Pharmacologicalcategory.getCreatedBy());
        assertNull(actualM_Pharmacologicalcategory.getModifiedBy());
        assertNull(actualM_Pharmacologicalcategory.getPharmCategoryCode());
        assertNull(actualM_Pharmacologicalcategory.getPharmCategoryDesc());
        assertNull(actualM_Pharmacologicalcategory.getPharmCategoryName());
        assertNull(actualM_Pharmacologicalcategory.getStatus());
        assertNull(actualM_Pharmacologicalcategory.getCreatedDate());
        assertNull(actualM_Pharmacologicalcategory.getLastModDate());
    }
}
