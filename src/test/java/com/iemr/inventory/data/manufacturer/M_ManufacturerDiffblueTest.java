package com.iemr.inventory.data.manufacturer;

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
@ContextConfiguration(classes = {M_Manufacturer.class})
@ExtendWith(SpringExtension.class)
class M_ManufacturerTest {
    @Autowired
    private M_Manufacturer m_Manufacturer;

    /**
     * Method under test: {@link M_Manufacturer#getcST_GST_No()}
     */
    @Test
    void testGetcST_GST_No() {
        // Arrange, Act and Assert
        assertNull((new M_Manufacturer()).getcST_GST_No());
    }

    /**
     * Method under test: {@link M_Manufacturer#getcST_GST_No()}
     */
    @Test
    void testGetcST_GST_No2() {
        // Arrange
        M_Manufacturer m_Manufacturer2 = new M_Manufacturer();
        m_Manufacturer2.setCreatedDate(mock(Date.class));

        // Act and Assert
        assertNull(m_Manufacturer2.getcST_GST_No());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_Manufacturer#setContactPerson(String)}
     *   <li>{@link M_Manufacturer#setCreatedBy(String)}
     *   <li>{@link M_Manufacturer#setCreatedDate(Date)}
     *   <li>{@link M_Manufacturer#setDeleted(Boolean)}
     *   <li>{@link M_Manufacturer#setLastModDate(Date)}
     *   <li>{@link M_Manufacturer#setManufacturerCode(String)}
     *   <li>{@link M_Manufacturer#setManufacturerDesc(String)}
     *   <li>{@link M_Manufacturer#setManufacturerID(Integer)}
     *   <li>{@link M_Manufacturer#setManufacturerName(String)}
     *   <li>{@link M_Manufacturer#setModifiedBy(String)}
     *   <li>{@link M_Manufacturer#setProviderServiceMapID(Integer)}
     *   <li>{@link M_Manufacturer#setStatus(String)}
     *   <li>{@link M_Manufacturer#setcST_GST_No(String)}
     *   <li>{@link M_Manufacturer#toString()}
     *   <li>{@link M_Manufacturer#getContactPerson()}
     *   <li>{@link M_Manufacturer#getCreatedBy()}
     *   <li>{@link M_Manufacturer#getCreatedDate()}
     *   <li>{@link M_Manufacturer#getDeleted()}
     *   <li>{@link M_Manufacturer#getLastModDate()}
     *   <li>{@link M_Manufacturer#getManufacturerCode()}
     *   <li>{@link M_Manufacturer#getManufacturerDesc()}
     *   <li>{@link M_Manufacturer#getManufacturerID()}
     *   <li>{@link M_Manufacturer#getManufacturerName()}
     *   <li>{@link M_Manufacturer#getModifiedBy()}
     *   <li>{@link M_Manufacturer#getProviderServiceMapID()}
     *   <li>{@link M_Manufacturer#getStatus()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_Manufacturer m_Manufacturer = new M_Manufacturer();

        // Act
        m_Manufacturer.setContactPerson("Contact Person");
        m_Manufacturer.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_Manufacturer.setCreatedDate(createdDate);
        m_Manufacturer.setDeleted(true);
        Date lastModDate = mock(Date.class);
        m_Manufacturer.setLastModDate(lastModDate);
        m_Manufacturer.setManufacturerCode("Manufacturer Code");
        m_Manufacturer.setManufacturerDesc("Manufacturer Desc");
        m_Manufacturer.setManufacturerID(1);
        m_Manufacturer.setManufacturerName("Manufacturer Name");
        m_Manufacturer.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_Manufacturer.setProviderServiceMapID(1);
        m_Manufacturer.setStatus("Status");
        m_Manufacturer.setcST_GST_No("C ST GST No");
        m_Manufacturer.toString();
        String actualContactPerson = m_Manufacturer.getContactPerson();
        String actualCreatedBy = m_Manufacturer.getCreatedBy();
        Date actualCreatedDate = m_Manufacturer.getCreatedDate();
        Boolean actualDeleted = m_Manufacturer.getDeleted();
        Date actualLastModDate = m_Manufacturer.getLastModDate();
        String actualManufacturerCode = m_Manufacturer.getManufacturerCode();
        String actualManufacturerDesc = m_Manufacturer.getManufacturerDesc();
        Integer actualManufacturerID = m_Manufacturer.getManufacturerID();
        String actualManufacturerName = m_Manufacturer.getManufacturerName();
        String actualModifiedBy = m_Manufacturer.getModifiedBy();
        Integer actualProviderServiceMapID = m_Manufacturer.getProviderServiceMapID();

        // Assert that nothing has changed
        assertEquals("Contact Person", actualContactPerson);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Manufacturer Code", actualManufacturerCode);
        assertEquals("Manufacturer Desc", actualManufacturerDesc);
        assertEquals("Manufacturer Name", actualManufacturerName);
        assertEquals("Status", m_Manufacturer.getStatus());
        assertEquals(1, actualManufacturerID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link M_Manufacturer}
     */
    @Test
    void testNewM_Manufacturer() {
        // Arrange and Act
        M_Manufacturer actualM_Manufacturer = new M_Manufacturer();

        // Assert
        assertNull(actualM_Manufacturer.getDeleted());
        assertNull(actualM_Manufacturer.getManufacturerID());
        assertNull(actualM_Manufacturer.getProviderServiceMapID());
        assertNull(actualM_Manufacturer.getContactPerson());
        assertNull(actualM_Manufacturer.getCreatedBy());
        assertNull(actualM_Manufacturer.getManufacturerCode());
        assertNull(actualM_Manufacturer.getManufacturerDesc());
        assertNull(actualM_Manufacturer.getManufacturerName());
        assertNull(actualM_Manufacturer.getModifiedBy());
        assertNull(actualM_Manufacturer.getStatus());
        assertNull(actualM_Manufacturer.getCreatedDate());
        assertNull(actualM_Manufacturer.getLastModDate());
    }
}
