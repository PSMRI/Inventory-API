package com.iemr.inventory.data.locationmaster;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {DistrictBranchMapping.class})
@ExtendWith(SpringExtension.class)
class DistrictBranchMappingTest {
    @Autowired
    private DistrictBranchMapping districtBranchMapping;

    /**
     * Method under test: {@link DistrictBranchMapping#setDistrictBranchID(int)}
     */
    @Test
    void testSetDistrictBranchID() {
        // Arrange
        DistrictBranchMapping districtBranchMapping2 = new DistrictBranchMapping();

        // Act
        districtBranchMapping2.setDistrictBranchID(1);

        // Assert
        assertEquals(1, districtBranchMapping2.getDistrictBranchID().intValue());
    }

    /**
     * Method under test: {@link DistrictBranchMapping#setDistrictBranchID(int)}
     */
    @Test
    void testSetDistrictBranchID2() {
        // Arrange
        DistrictBranchMapping districtBranchMapping2 = new DistrictBranchMapping();
        districtBranchMapping2.setCreatedDate(mock(Timestamp.class));

        // Act
        districtBranchMapping2.setDistrictBranchID(1);

        // Assert
        assertEquals(1, districtBranchMapping2.getDistrictBranchID().intValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DistrictBranchMapping#setBlockID(Integer)}
     *   <li>{@link DistrictBranchMapping#setBlockName(String)}
     *   <li>{@link DistrictBranchMapping#setCreatedBy(String)}
     *   <li>{@link DistrictBranchMapping#setCreatedDate(Timestamp)}
     *   <li>{@link DistrictBranchMapping#setDeleted(Boolean)}
     *   <li>{@link DistrictBranchMapping#setGovSubDistrictID(Integer)}
     *   <li>{@link DistrictBranchMapping#setGovVillageID(Integer)}
     *   <li>{@link DistrictBranchMapping#setHabitat(String)}
     *   <li>{@link DistrictBranchMapping#setLastModDate(Timestamp)}
     *   <li>{@link DistrictBranchMapping#setModifiedBy(String)}
     *   <li>{@link DistrictBranchMapping#setPanchayatName(String)}
     *   <li>{@link DistrictBranchMapping#setPinCode(String)}
     *   <li>{@link DistrictBranchMapping#setVillageName(String)}
     *   <li>{@link DistrictBranchMapping#toString()}
     *   <li>{@link DistrictBranchMapping#getBlockID()}
     *   <li>{@link DistrictBranchMapping#getBlockName()}
     *   <li>{@link DistrictBranchMapping#getCreatedBy()}
     *   <li>{@link DistrictBranchMapping#getCreatedDate()}
     *   <li>{@link DistrictBranchMapping#getDeleted()}
     *   <li>{@link DistrictBranchMapping#getDistrictBranchID()}
     *   <li>{@link DistrictBranchMapping#getGovSubDistrictID()}
     *   <li>{@link DistrictBranchMapping#getGovVillageID()}
     *   <li>{@link DistrictBranchMapping#getHabitat()}
     *   <li>{@link DistrictBranchMapping#getLastModDate()}
     *   <li>{@link DistrictBranchMapping#getModifiedBy()}
     *   <li>{@link DistrictBranchMapping#getPanchayatName()}
     *   <li>{@link DistrictBranchMapping#getPinCode()}
     *   <li>{@link DistrictBranchMapping#getVillageName()}
     *   <li>{@link DistrictBranchMapping#isDeleted()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        DistrictBranchMapping districtBranchMapping = new DistrictBranchMapping();

        // Act
        districtBranchMapping.setBlockID((Integer) 1);
        districtBranchMapping.setBlockName("Block Name");
        districtBranchMapping.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        districtBranchMapping.setCreatedDate(createdDate);
        districtBranchMapping.setDeleted((Boolean) true);
        districtBranchMapping.setGovSubDistrictID(1);
        districtBranchMapping.setGovVillageID(1);
        districtBranchMapping.setHabitat("Habitat");
        Timestamp lastModDate = mock(Timestamp.class);
        districtBranchMapping.setLastModDate(lastModDate);
        districtBranchMapping.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        districtBranchMapping.setPanchayatName("Panchayat Name");
        districtBranchMapping.setPinCode("Pin Code");
        districtBranchMapping.setVillageName("Village Name");
        String actualToStringResult = districtBranchMapping.toString();
        Integer actualBlockID = districtBranchMapping.getBlockID();
        String actualBlockName = districtBranchMapping.getBlockName();
        String actualCreatedBy = districtBranchMapping.getCreatedBy();
        Timestamp actualCreatedDate = districtBranchMapping.getCreatedDate();
        Boolean actualDeleted = districtBranchMapping.getDeleted();
        districtBranchMapping.getDistrictBranchID();
        Integer actualGovSubDistrictID = districtBranchMapping.getGovSubDistrictID();
        Integer actualGovVillageID = districtBranchMapping.getGovVillageID();
        String actualHabitat = districtBranchMapping.getHabitat();
        Timestamp actualLastModDate = districtBranchMapping.getLastModDate();
        String actualModifiedBy = districtBranchMapping.getModifiedBy();
        String actualPanchayatName = districtBranchMapping.getPanchayatName();
        String actualPinCode = districtBranchMapping.getPinCode();
        String actualVillageName = districtBranchMapping.getVillageName();
        Boolean actualIsDeletedResult = districtBranchMapping.isDeleted();

        // Assert that nothing has changed
        assertEquals("Block Name", actualBlockName);
        assertEquals("Habitat", actualHabitat);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Panchayat Name", actualPanchayatName);
        assertEquals("Pin Code", actualPinCode);
        assertEquals("Village Name", actualVillageName);
        assertEquals(
                "{\"districtBranchID\":null,\"blockID\":1,\"districtBlock\":null,\"blockName\":\"Block Name\",\"panchayatName\":\"Panchayat"
                        + " Name\",\"villageName\":\"Village Name\",\"habitat\":\"Habitat\",\"pinCode\":\"Pin Code\",\"govVillageID\":1,"
                        + "\"govSubDistrictID\":1,\"deleted\":true,\"createdBy\":\"Jan 1, 2020 8:00am GMT+0100\"}",
                actualToStringResult);
        assertEquals(1, actualBlockID.intValue());
        assertEquals(1, actualGovSubDistrictID.intValue());
        assertEquals(1, actualGovVillageID.intValue());
        assertTrue(actualDeleted);
        assertTrue(actualIsDeletedResult);
        assertSame(createdDate, actualCreatedDate);
        assertSame(lastModDate, actualLastModDate);
    }

    /**
     * Method under test: {@link DistrictBranchMapping#setBlockID(int)}
     */
    @Test
    void testSetBlockID() {
        // Arrange
        DistrictBranchMapping districtBranchMapping2 = new DistrictBranchMapping();

        // Act
        districtBranchMapping2.setBlockID(1);

        // Assert
        assertEquals(1, districtBranchMapping2.getBlockID().intValue());
    }

    /**
     * Method under test: {@link DistrictBranchMapping#setBlockID(int)}
     */
    @Test
    void testSetBlockID2() {
        // Arrange
        DistrictBranchMapping districtBranchMapping2 = new DistrictBranchMapping();
        districtBranchMapping2.setCreatedDate(mock(Timestamp.class));

        // Act
        districtBranchMapping2.setBlockID(1);

        // Assert
        assertEquals(1, districtBranchMapping2.getBlockID().intValue());
    }

    /**
     * Method under test: {@link DistrictBranchMapping#setDeleted(boolean)}
     */
    @Test
    void testSetDeleted() {
        // Arrange
        DistrictBranchMapping districtBranchMapping2 = new DistrictBranchMapping();

        // Act
        districtBranchMapping2.setDeleted(true);

        // Assert
        assertTrue(districtBranchMapping2.getDeleted());
    }

    /**
     * Method under test: {@link DistrictBranchMapping#setDeleted(boolean)}
     */
    @Test
    void testSetDeleted2() {
        // Arrange
        DistrictBranchMapping districtBranchMapping2 = new DistrictBranchMapping();
        districtBranchMapping2.setCreatedDate(mock(Timestamp.class));

        // Act
        districtBranchMapping2.setDeleted(true);

        // Assert
        assertTrue(districtBranchMapping2.getDeleted());
    }

    /**
     * Method under test: {@link DistrictBranchMapping#DistrictBranchMapping()}
     */
    @Test
    void testNewDistrictBranchMapping() {
        // Arrange and Act
        DistrictBranchMapping actualDistrictBranchMapping = new DistrictBranchMapping();

        // Assert
        assertNull(actualDistrictBranchMapping.getDeleted());
        assertNull(actualDistrictBranchMapping.isDeleted());
        assertNull(actualDistrictBranchMapping.getBlockID());
        assertNull(actualDistrictBranchMapping.getDistrictBranchID());
        assertNull(actualDistrictBranchMapping.getGovSubDistrictID());
        assertNull(actualDistrictBranchMapping.getGovVillageID());
        assertNull(actualDistrictBranchMapping.getBlockName());
        assertNull(actualDistrictBranchMapping.getCreatedBy());
        assertNull(actualDistrictBranchMapping.getHabitat());
        assertNull(actualDistrictBranchMapping.getModifiedBy());
        assertNull(actualDistrictBranchMapping.getPanchayatName());
        assertNull(actualDistrictBranchMapping.getPinCode());
        assertNull(actualDistrictBranchMapping.getVillageName());
        assertNull(actualDistrictBranchMapping.getCreatedDate());
        assertNull(actualDistrictBranchMapping.getLastModDate());
    }

    /**
     * Method under test:
     * {@link DistrictBranchMapping#DistrictBranchMapping(Integer, Integer, String, String, String, String, String, Integer, Integer, Boolean)}
     */
    @Test
    void testNewDistrictBranchMapping2() {
        // Arrange and Act
        DistrictBranchMapping actualDistrictBranchMapping = new DistrictBranchMapping(1, 1, "Block Name", "Panchayat Name",
                "Village Name", "Habitat", "Pin Code", 1, 1, true);

        // Assert
        assertEquals("Block Name", actualDistrictBranchMapping.getBlockName());
        assertEquals("Habitat", actualDistrictBranchMapping.getHabitat());
        assertEquals("Panchayat Name", actualDistrictBranchMapping.getPanchayatName());
        assertEquals("Pin Code", actualDistrictBranchMapping.getPinCode());
        assertEquals("Village Name", actualDistrictBranchMapping.getVillageName());
        assertEquals(1, actualDistrictBranchMapping.getBlockID().intValue());
        assertEquals(1, actualDistrictBranchMapping.getDistrictBranchID().intValue());
        assertEquals(1, actualDistrictBranchMapping.getGovSubDistrictID().intValue());
        assertEquals(1, actualDistrictBranchMapping.getGovVillageID().intValue());
        assertTrue(actualDistrictBranchMapping.getDeleted());
    }

    /**
     * Method under test:
     * {@link DistrictBranchMapping#DistrictBranchMapping(Integer, String)}
     */
    @Test
    void testNewDistrictBranchMapping3() {
        // Arrange and Act
        DistrictBranchMapping actualDistrictBranchMapping = new DistrictBranchMapping(1, "Village Name");

        // Assert
        assertEquals("Village Name", actualDistrictBranchMapping.getVillageName());
        assertEquals(1, actualDistrictBranchMapping.getDistrictBranchID().intValue());
    }

    /**
     * Method under test:
     * {@link DistrictBranchMapping#DistrictBranchMapping(Integer, String, String, String, String)}
     */
    @Test
    void testNewDistrictBranchMapping4() {
        // Arrange and Act
        DistrictBranchMapping actualDistrictBranchMapping = new DistrictBranchMapping(1, "Village Name", "Panchayat Name",
                "Habitat", "Pin Code");

        // Assert
        assertEquals("Habitat", actualDistrictBranchMapping.getHabitat());
        assertEquals("Panchayat Name", actualDistrictBranchMapping.getPanchayatName());
        assertEquals("Pin Code", actualDistrictBranchMapping.getPinCode());
        assertEquals("Village Name", actualDistrictBranchMapping.getVillageName());
        assertEquals(1, actualDistrictBranchMapping.getDistrictBranchID().intValue());
    }
}
