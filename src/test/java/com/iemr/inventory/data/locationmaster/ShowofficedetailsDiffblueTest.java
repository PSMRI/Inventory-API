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
@ContextConfiguration(classes = {Showofficedetails.class})
@ExtendWith(SpringExtension.class)
class ShowofficedetailsTest {
    @Autowired
    private Showofficedetails showofficedetails;

    /**
     * Method under test: {@link Showofficedetails#getpSAddMapID()}
     */
    @Test
    void testGetpSAddMapID() {
        // Arrange, Act and Assert
        assertNull((new Showofficedetails()).getpSAddMapID());
    }

    /**
     * Method under test: {@link Showofficedetails#getpSAddMapID()}
     */
    @Test
    void testGetpSAddMapID2() {
        // Arrange
        Showofficedetails showofficedetails2 = new Showofficedetails();
        showofficedetails2.setCreatedDate(mock(Date.class));

        // Act and Assert
        assertNull(showofficedetails2.getpSAddMapID());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Showofficedetails#setAddress(String)}
     *   <li>{@link Showofficedetails#setCreatedBy(String)}
     *   <li>{@link Showofficedetails#setCreatedDate(Date)}
     *   <li>{@link Showofficedetails#setDeleted(Boolean)}
     *   <li>{@link Showofficedetails#setDistrictID(Integer)}
     *   <li>{@link Showofficedetails#setDistrictName(String)}
     *   <li>{@link Showofficedetails#setLastModDate(Date)}
     *   <li>{@link Showofficedetails#setLocationName(String)}
     *   <li>{@link Showofficedetails#setModifiedBy(String)}
     *   <li>{@link Showofficedetails#setProviderServiceMapID(Integer)}
     *   <li>{@link Showofficedetails#setServiceID(Integer)}
     *   <li>{@link Showofficedetails#setServiceName(String)}
     *   <li>{@link Showofficedetails#setServiceProviderID(Integer)}
     *   <li>{@link Showofficedetails#setServiceProviderName(String)}
     *   <li>{@link Showofficedetails#setStateID(Integer)}
     *   <li>{@link Showofficedetails#setStateName(String)}
     *   <li>{@link Showofficedetails#setpSAddMapID(Integer)}
     *   <li>{@link Showofficedetails#toString()}
     *   <li>{@link Showofficedetails#getAddress()}
     *   <li>{@link Showofficedetails#getCreatedBy()}
     *   <li>{@link Showofficedetails#getCreatedDate()}
     *   <li>{@link Showofficedetails#getDeleted()}
     *   <li>{@link Showofficedetails#getDistrictID()}
     *   <li>{@link Showofficedetails#getDistrictName()}
     *   <li>{@link Showofficedetails#getLastModDate()}
     *   <li>{@link Showofficedetails#getLocationName()}
     *   <li>{@link Showofficedetails#getModifiedBy()}
     *   <li>{@link Showofficedetails#getProviderServiceMapID()}
     *   <li>{@link Showofficedetails#getServiceID()}
     *   <li>{@link Showofficedetails#getServiceName()}
     *   <li>{@link Showofficedetails#getServiceProviderID()}
     *   <li>{@link Showofficedetails#getServiceProviderName()}
     *   <li>{@link Showofficedetails#getStateID()}
     *   <li>{@link Showofficedetails#getStateName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Showofficedetails showofficedetails = new Showofficedetails();

        // Act
        showofficedetails.setAddress("42 Main St");
        showofficedetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Date createdDate = mock(Date.class);
        showofficedetails.setCreatedDate(createdDate);
        showofficedetails.setDeleted(true);
        showofficedetails.setDistrictID(1);
        showofficedetails.setDistrictName("District Name");
        Date lastModDate = mock(Date.class);
        showofficedetails.setLastModDate(lastModDate);
        showofficedetails.setLocationName("Location Name");
        showofficedetails.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        showofficedetails.setProviderServiceMapID(1);
        showofficedetails.setServiceID(1);
        showofficedetails.setServiceName("Service Name");
        showofficedetails.setServiceProviderID(1);
        showofficedetails.setServiceProviderName("Service Provider Name");
        showofficedetails.setStateID(1);
        showofficedetails.setStateName("MD");
        showofficedetails.setpSAddMapID(1);
        showofficedetails.toString();
        String actualAddress = showofficedetails.getAddress();
        String actualCreatedBy = showofficedetails.getCreatedBy();
        Date actualCreatedDate = showofficedetails.getCreatedDate();
        Boolean actualDeleted = showofficedetails.getDeleted();
        Integer actualDistrictID = showofficedetails.getDistrictID();
        String actualDistrictName = showofficedetails.getDistrictName();
        Date actualLastModDate = showofficedetails.getLastModDate();
        String actualLocationName = showofficedetails.getLocationName();
        String actualModifiedBy = showofficedetails.getModifiedBy();
        Integer actualProviderServiceMapID = showofficedetails.getProviderServiceMapID();
        Integer actualServiceID = showofficedetails.getServiceID();
        String actualServiceName = showofficedetails.getServiceName();
        Integer actualServiceProviderID = showofficedetails.getServiceProviderID();
        String actualServiceProviderName = showofficedetails.getServiceProviderName();
        Integer actualStateID = showofficedetails.getStateID();

        // Assert that nothing has changed
        assertEquals("42 Main St", actualAddress);
        assertEquals("District Name", actualDistrictName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Location Name", actualLocationName);
        assertEquals("MD", showofficedetails.getStateName());
        assertEquals("Service Name", actualServiceName);
        assertEquals("Service Provider Name", actualServiceProviderName);
        assertEquals(1, actualDistrictID.intValue());
        assertEquals(1, actualProviderServiceMapID.intValue());
        assertEquals(1, actualServiceID.intValue());
        assertEquals(1, actualServiceProviderID.intValue());
        assertEquals(1, actualStateID.intValue());
        assertTrue(actualDeleted);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: default or parameterless constructor of
     * {@link Showofficedetails}
     */
    @Test
    void testNewShowofficedetails() {
        // Arrange and Act
        Showofficedetails actualShowofficedetails = new Showofficedetails();

        // Assert
        assertNull(actualShowofficedetails.getDeleted());
        assertNull(actualShowofficedetails.getDistrictID());
        assertNull(actualShowofficedetails.getProviderServiceMapID());
        assertNull(actualShowofficedetails.getServiceID());
        assertNull(actualShowofficedetails.getServiceProviderID());
        assertNull(actualShowofficedetails.getStateID());
        assertNull(actualShowofficedetails.getAddress());
        assertNull(actualShowofficedetails.getCreatedBy());
        assertNull(actualShowofficedetails.getDistrictName());
        assertNull(actualShowofficedetails.getLocationName());
        assertNull(actualShowofficedetails.getModifiedBy());
        assertNull(actualShowofficedetails.getServiceName());
        assertNull(actualShowofficedetails.getServiceProviderName());
        assertNull(actualShowofficedetails.getStateName());
        assertNull(actualShowofficedetails.getCreatedDate());
        assertNull(actualShowofficedetails.getLastModDate());
    }
}
