package com.iemr.inventory.data.supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.iemr.inventory.utils.mapper.OutputMapper;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {M_Supplieraddress.class})
@ExtendWith(SpringExtension.class)
class M_SupplieraddressDiffblueTest {
    @Autowired
    private M_Supplieraddress m_Supplieraddress;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_Supplieraddress#setAddressLine1(String)}
     *   <li>{@link M_Supplieraddress#setAddressLine2(String)}
     *   <li>{@link M_Supplieraddress#setCountry(String)}
     *   <li>{@link M_Supplieraddress#setCreatedBy(String)}
     *   <li>{@link M_Supplieraddress#setCreatedDate(Date)}
     *   <li>{@link M_Supplieraddress#setDeleted(Boolean)}
     *   <li>{@link M_Supplieraddress#setDistrict(String)}
     *   <li>{@link M_Supplieraddress#setLastModDate(Date)}
     *   <li>{@link M_Supplieraddress#setModifiedBy(String)}
     *   <li>{@link M_Supplieraddress#setOutputMapper(OutputMapper)}
     *   <li>{@link M_Supplieraddress#setPinCode(String)}
     *   <li>{@link M_Supplieraddress#setProviderServiceMapID(Integer)}
     *   <li>{@link M_Supplieraddress#setState(String)}
     *   <li>{@link M_Supplieraddress#setSupplierAddressID(Integer)}
     *   <li>{@link M_Supplieraddress#setSupplierID(Integer)}
     *   <li>{@link M_Supplieraddress#toString()}
     *   <li>{@link M_Supplieraddress#getAddressLine1()}
     *   <li>{@link M_Supplieraddress#getAddressLine2()}
     *   <li>{@link M_Supplieraddress#getCountry()}
     *   <li>{@link M_Supplieraddress#getCreatedBy()}
     *   <li>{@link M_Supplieraddress#getCreatedDate()}
     *   <li>{@link M_Supplieraddress#getDeleted()}
     *   <li>{@link M_Supplieraddress#getDistrict()}
     *   <li>{@link M_Supplieraddress#getLastModDate()}
     *   <li>{@link M_Supplieraddress#getModifiedBy()}
     *   <li>{@link M_Supplieraddress#getOutputMapper()}
     *   <li>{@link M_Supplieraddress#getPinCode()}
     *   <li>{@link M_Supplieraddress#getProviderServiceMapID()}
     *   <li>{@link M_Supplieraddress#getState()}
     *   <li>{@link M_Supplieraddress#getSupplierAddressID()}
     *   <li>{@link M_Supplieraddress#getSupplierID()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_Supplieraddress m_Supplieraddress = new M_Supplieraddress();

        // Act
        m_Supplieraddress.setAddressLine1("42 Main St");
        m_Supplieraddress.setAddressLine2("42 Main St");
        m_Supplieraddress.setCountry("GB");
        m_Supplieraddress.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        m_Supplieraddress.setCreatedDate(createdDate);
        m_Supplieraddress.setDeleted(true);
        m_Supplieraddress.setDistrict("District");
        Date lastModDate = mock(Date.class);
        m_Supplieraddress.setLastModDate(lastModDate);
        m_Supplieraddress.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        OutputMapper outputMapper = new OutputMapper();
        m_Supplieraddress.setOutputMapper(outputMapper);
        m_Supplieraddress.setPinCode("Pin Code");
        m_Supplieraddress.setProviderServiceMapID(1);
        m_Supplieraddress.setState("MD");
        m_Supplieraddress.setSupplierAddressID(1);
        m_Supplieraddress.setSupplierID(1);
        m_Supplieraddress.toString();
        String actualAddressLine1 = m_Supplieraddress.getAddressLine1();
        String actualAddressLine2 = m_Supplieraddress.getAddressLine2();
        String actualCountry = m_Supplieraddress.getCountry();
        String actualCreatedBy = m_Supplieraddress.getCreatedBy();
        Date actualCreatedDate = m_Supplieraddress.getCreatedDate();
        Boolean actualDeleted = m_Supplieraddress.getDeleted();
        String actualDistrict = m_Supplieraddress.getDistrict();
        Date actualLastModDate = m_Supplieraddress.getLastModDate();
        String actualModifiedBy = m_Supplieraddress.getModifiedBy();
        OutputMapper actualOutputMapper = m_Supplieraddress.getOutputMapper();
        String actualPinCode = m_Supplieraddress.getPinCode();
        Integer actualProviderServiceMapID = m_Supplieraddress.getProviderServiceMapID();
        String actualState = m_Supplieraddress.getState();
        Integer actualSupplierAddressID = m_Supplieraddress.getSupplierAddressID();
        Integer actualSupplierID = m_Supplieraddress.getSupplierID();

        // Assert that nothing has changed
        assertEquals("42 Main St", actualAddressLine1);
        assertEquals("42 Main St", actualAddressLine2);
        assertEquals("District", actualDistrict);
        assertEquals("GB", actualCountry);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("MD", actualState);
        assertEquals("Pin Code", actualPinCode);
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualSupplierAddressID.intValue());
        assertEquals(1, actualSupplierID.intValue());
        assertTrue(actualDeleted);
        assertSame(outputMapper, actualOutputMapper);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link M_Supplieraddress}
     */
    @Test
    void testNewM_Supplieraddress() {
        // Arrange and Act
        M_Supplieraddress actualM_Supplieraddress = new M_Supplieraddress();

        // Assert
        assertNull(actualM_Supplieraddress.getDeleted());
        assertNull(actualM_Supplieraddress.getProviderServiceMapID());
        assertNull(actualM_Supplieraddress.getSupplierAddressID());
        assertNull(actualM_Supplieraddress.getSupplierID());
        assertNull(actualM_Supplieraddress.getAddressLine1());
        assertNull(actualM_Supplieraddress.getAddressLine2());
        assertNull(actualM_Supplieraddress.getCountry());
        assertNull(actualM_Supplieraddress.getCreatedBy());
        assertNull(actualM_Supplieraddress.getDistrict());
        assertNull(actualM_Supplieraddress.getModifiedBy());
        assertNull(actualM_Supplieraddress.getPinCode());
        assertNull(actualM_Supplieraddress.getState());
        assertNull(actualM_Supplieraddress.getCreatedDate());
        assertNull(actualM_Supplieraddress.getLastModDate());
    }
}
