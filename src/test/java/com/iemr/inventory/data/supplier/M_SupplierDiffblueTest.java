package com.iemr.inventory.data.supplier;

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
@ContextConfiguration(classes = {M_Supplier.class})
@ExtendWith(SpringExtension.class)
class M_SupplierDiffblueTest {
    @Autowired
    private M_Supplier m_Supplier;

    /**
     * Method under test: {@link M_Supplier#getcST_GST_No()}
     */
    @Test
    void testGetcST_GST_No() {
        // Arrange, Act and Assert
        assertNull((new M_Supplier()).getcST_GST_No());
    }

    /**
     * Method under test: {@link M_Supplier#getcST_GST_No()}
     */
    @Test
    void testGetcST_GST_No2() {
        // Arrange
        M_Supplier m_Supplier2 = new M_Supplier();
        m_Supplier2.setCreatedDate(mock(Date.class));

        // Act and Assert
        assertNull(m_Supplier2.getcST_GST_No());
    }

    /**
     * Method under test: {@link M_Supplier#gettIN_No()}
     */
    @Test
    void testGettIN_No() {
        // Arrange, Act and Assert
        assertNull((new M_Supplier()).gettIN_No());
    }

    /**
     * Method under test: {@link M_Supplier#gettIN_No()}
     */
    @Test
    void testGettIN_No2() {
        // Arrange
        M_Supplier m_Supplier2 = new M_Supplier();
        m_Supplier2.setCreatedDate(mock(Date.class));

        // Act and Assert
        assertNull(m_Supplier2.gettIN_No());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_Supplier#setContactPerson(String)}
     *   <li>{@link M_Supplier#setCreatedBy(String)}
     *   <li>{@link M_Supplier#setCreatedDate(Date)}
     *   <li>{@link M_Supplier#setDeleted(Boolean)}
     *   <li>{@link M_Supplier#setDrugLicenseNo(String)}
     *   <li>{@link M_Supplier#setEmail(String)}
     *   <li>{@link M_Supplier#setLastModDate(Date)}
     *   <li>{@link M_Supplier#setModifiedBy(String)}
     *   <li>{@link M_Supplier#setPhoneNo1(String)}
     *   <li>{@link M_Supplier#setPhoneNo2(String)}
     *   <li>{@link M_Supplier#setProviderServiceMapID(Integer)}
     *   <li>{@link M_Supplier#setStatus(String)}
     *   <li>{@link M_Supplier#setSupplierCode(String)}
     *   <li>{@link M_Supplier#setSupplierDesc(String)}
     *   <li>{@link M_Supplier#setSupplierID(Integer)}
     *   <li>{@link M_Supplier#setSupplierName(String)}
     *   <li>{@link M_Supplier#setcST_GST_No(String)}
     *   <li>{@link M_Supplier#settIN_No(String)}
     *   <li>{@link M_Supplier#toString()}
     *   <li>{@link M_Supplier#getContactPerson()}
     *   <li>{@link M_Supplier#getCreatedBy()}
     *   <li>{@link M_Supplier#getCreatedDate()}
     *   <li>{@link M_Supplier#getDeleted()}
     *   <li>{@link M_Supplier#getDrugLicenseNo()}
     *   <li>{@link M_Supplier#getEmail()}
     *   <li>{@link M_Supplier#getLastModDate()}
     *   <li>{@link M_Supplier#getModifiedBy()}
     *   <li>{@link M_Supplier#getPhoneNo1()}
     *   <li>{@link M_Supplier#getPhoneNo2()}
     *   <li>{@link M_Supplier#getProviderServiceMapID()}
     *   <li>{@link M_Supplier#getStatus()}
     *   <li>{@link M_Supplier#getSupplierCode()}
     *   <li>{@link M_Supplier#getSupplierDesc()}
     *   <li>{@link M_Supplier#getSupplierID()}
     *   <li>{@link M_Supplier#getSupplierName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_Supplier m_Supplier = new M_Supplier();

        // Act
        m_Supplier.setContactPerson("Contact Person");
        m_Supplier.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_Supplier.setCreatedDate(createdDate);
        m_Supplier.setDeleted(true);
        m_Supplier.setDrugLicenseNo("Drug License No");
        m_Supplier.setEmail("jane.doe@example.org");
        Date lastModDate = mock(Date.class);
        m_Supplier.setLastModDate(lastModDate);
        m_Supplier.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_Supplier.setPhoneNo1("6625550144");
        m_Supplier.setPhoneNo2("6625550144");
        m_Supplier.setProviderServiceMapID(1);
        m_Supplier.setStatus("Status");
        m_Supplier.setSupplierCode("Supplier Code");
        m_Supplier.setSupplierDesc("Supplier Desc");
        m_Supplier.setSupplierID(1);
        m_Supplier.setSupplierName("Supplier Name");
        m_Supplier.setcST_GST_No("C ST GST No");
        m_Supplier.settIN_No("T IN No");
        m_Supplier.toString();
        String actualContactPerson = m_Supplier.getContactPerson();
        String actualCreatedBy = m_Supplier.getCreatedBy();
        Date actualCreatedDate = m_Supplier.getCreatedDate();
        Boolean actualDeleted = m_Supplier.getDeleted();
        String actualDrugLicenseNo = m_Supplier.getDrugLicenseNo();
        String actualEmail = m_Supplier.getEmail();
        Date actualLastModDate = m_Supplier.getLastModDate();
        String actualModifiedBy = m_Supplier.getModifiedBy();
        String actualPhoneNo1 = m_Supplier.getPhoneNo1();
        String actualPhoneNo2 = m_Supplier.getPhoneNo2();
        Integer actualProviderServiceMapID = m_Supplier.getProviderServiceMapID();
        String actualStatus = m_Supplier.getStatus();
        String actualSupplierCode = m_Supplier.getSupplierCode();
        String actualSupplierDesc = m_Supplier.getSupplierDesc();
        Integer actualSupplierID = m_Supplier.getSupplierID();

        // Assert that nothing has changed
        assertEquals("6625550144", actualPhoneNo1);
        assertEquals("6625550144", actualPhoneNo2);
        assertEquals("Contact Person", actualContactPerson);
        assertEquals("Drug License No", actualDrugLicenseNo);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Status", actualStatus);
        assertEquals("Supplier Code", actualSupplierCode);
        assertEquals("Supplier Desc", actualSupplierDesc);
        assertEquals("Supplier Name", m_Supplier.getSupplierName());
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualSupplierID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of {@link M_Supplier}
     */
    @Test
    void testNewM_Supplier() {
        // Arrange and Act
        M_Supplier actualM_Supplier = new M_Supplier();

        // Assert
        assertNull(actualM_Supplier.getDeleted());
        assertNull(actualM_Supplier.getProviderServiceMapID());
        assertNull(actualM_Supplier.getSupplierID());
        assertNull(actualM_Supplier.getContactPerson());
        assertNull(actualM_Supplier.getCreatedBy());
        assertNull(actualM_Supplier.getDrugLicenseNo());
        assertNull(actualM_Supplier.getEmail());
        assertNull(actualM_Supplier.getModifiedBy());
        assertNull(actualM_Supplier.getPhoneNo1());
        assertNull(actualM_Supplier.getPhoneNo2());
        assertNull(actualM_Supplier.getStatus());
        assertNull(actualM_Supplier.getSupplierCode());
        assertNull(actualM_Supplier.getSupplierDesc());
        assertNull(actualM_Supplier.getSupplierName());
        assertNull(actualM_Supplier.getCreatedDate());
        assertNull(actualM_Supplier.getLastModDate());
    }
}
