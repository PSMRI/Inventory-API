package com.iemr.inventory.data.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {M_Van.class})
@ExtendWith(SpringExtension.class)
class M_VanDiffblueTest {
    @Autowired
    private M_Van m_Van;

    /**
     * Method under test: {@link M_Van#M_Van()}
     */
    @Test
    void testNewM_Van() {
        // Arrange and Act
        M_Van actualM_Van = new M_Van();

        // Assert
        assertNull(actualM_Van.getDeleted());
        assertNull(actualM_Van.getIsFacility());
        assertNull(actualM_Van.getCountryID());
        assertNull(actualM_Van.getDistrictBlockID());
        assertNull(actualM_Van.getFacilityID());
        assertNull(actualM_Van.getParkingPlaceID());
        assertNull(actualM_Van.getProviderServiceMapID());
        assertNull(actualM_Van.getServiceProviderID());
        assertNull(actualM_Van.getStateID());
        assertNull(actualM_Van.getVanID());
        assertNull(actualM_Van.getVanTypeID());
        assertNull(actualM_Van.getCountryName());
        assertNull(actualM_Van.getCreatedBy());
        assertNull(actualM_Van.getModifiedBy());
        assertNull(actualM_Van.getParkingPlaceName());
        assertNull(actualM_Van.getProcessed());
        assertNull(actualM_Van.getStateName());
        assertNull(actualM_Van.getVanName());
        assertNull(actualM_Van.getVanType());
        assertNull(actualM_Van.getVehicalNo());
        assertNull(actualM_Van.getCreatedDate());
        assertNull(actualM_Van.getLastModDate());
    }

    /**
     * Method under test:
     * {@link M_Van#M_Van(Integer, String, String, Integer, String, Boolean, Integer, Integer, String, Integer, String, Integer, String, Integer)}
     */
    @Test
    void testNewM_Van2() {
        // Arrange and Act
        M_Van actualM_Van = new M_Van(1, "Van Name", "Vehical No", 1, "Van Type", true, 1, 1, "GB", 1, "MD", 1,
                "Parking Place Name", 1);

        // Assert
        assertEquals("GB", actualM_Van.getCountryName());
        assertEquals("MD", actualM_Van.getStateName());
        assertEquals("Parking Place Name", actualM_Van.getParkingPlaceName());
        assertEquals("Van Name", actualM_Van.getVanName());
        assertEquals("Van Type", actualM_Van.getVanType());
        assertEquals("Vehical No", actualM_Van.getVehicalNo());
        assertEquals(1, actualM_Van.getCountryID().intValue());
        assertEquals(1, actualM_Van.getDistrictBlockID().intValue());
        assertEquals(1, actualM_Van.getParkingPlaceID().intValue());
        assertEquals(1, actualM_Van.getProviderServiceMapID().intValue());
        assertEquals(1, actualM_Van.getStateID().intValue());
        assertEquals(1, actualM_Van.getVanID().intValue());
        assertEquals(1, actualM_Van.getVanTypeID().intValue());
        assertTrue(actualM_Van.getDeleted());
    }

    /**
     * Method under test: {@link M_Van#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"vanType\":null,\"countryName\":null,\"stateName\":null,\"parkingPlaceName\":null,\"vanID\":null,\"vanName\""
                        + ":null,\"vehicalNo\":null,\"vanTypeID\":null,\"providerServiceMapID\":null,\"serviceProviderID\":null,\"countryID"
                        + "\":null,\"stateID\":null,\"parkingPlaceID\":null,\"isFacility\":null,\"districtBlockID\":null,\"facilityID\""
                        + ":null,\"deleted\":null,\"processed\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate"
                        + "\":null}",
                (new M_Van()).toString());
    }
}
