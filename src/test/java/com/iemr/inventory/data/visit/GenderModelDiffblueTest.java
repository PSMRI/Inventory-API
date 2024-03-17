package com.iemr.inventory.data.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {GenderModel.class})
@ExtendWith(SpringExtension.class)
class GenderModelTest {
    @Autowired
    private GenderModel genderModel;

    /**
     * Method under test: {@link GenderModel#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange, Act and Assert
        assertFalse((new GenderModel()).canEqual("Other"));
    }

    /**
     * Method under test: {@link GenderModel#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        GenderModel genderModel2 = new GenderModel();

        GenderModel genderModel3 = new GenderModel();
        genderModel3.setGenderID(1);
        genderModel3.setGenderName("Gender Name");

        // Act and Assert
        assertTrue(genderModel2.canEqual(genderModel3));
    }

    /**
     * Method under test: {@link GenderModel#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(1);
        genderModel.setGenderName("Gender Name");

        // Act and Assert
        assertNotEquals(genderModel, null);
    }

    /**
     * Method under test: {@link GenderModel#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(1);
        genderModel.setGenderName("Gender Name");

        // Act and Assert
        assertNotEquals(genderModel, "Different type to GenderModel");
    }

    /**
     * Method under test: {@link GenderModel#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(2);
        genderModel.setGenderName("Gender Name");

        GenderModel genderModel2 = new GenderModel();
        genderModel2.setGenderID(1);
        genderModel2.setGenderName("Gender Name");

        // Act and Assert
        assertNotEquals(genderModel, genderModel2);
    }

    /**
     * Method under test: {@link GenderModel#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(null);
        genderModel.setGenderName("Gender Name");

        GenderModel genderModel2 = new GenderModel();
        genderModel2.setGenderID(1);
        genderModel2.setGenderName("Gender Name");

        // Act and Assert
        assertNotEquals(genderModel, genderModel2);
    }

    /**
     * Method under test: {@link GenderModel#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(1);
        genderModel.setGenderName(null);

        GenderModel genderModel2 = new GenderModel();
        genderModel2.setGenderID(1);
        genderModel2.setGenderName("Gender Name");

        // Act and Assert
        assertNotEquals(genderModel, genderModel2);
    }

    /**
     * Method under test: {@link GenderModel#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(1);
        genderModel.setGenderName("com.iemr.inventory.data.visit.GenderModel");

        GenderModel genderModel2 = new GenderModel();
        genderModel2.setGenderID(1);
        genderModel2.setGenderName("Gender Name");

        // Act and Assert
        assertNotEquals(genderModel, genderModel2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GenderModel#equals(Object)}
     *   <li>{@link GenderModel#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(1);
        genderModel.setGenderName("Gender Name");

        // Act and Assert
        assertEquals(genderModel, genderModel);
        int expectedHashCodeResult = genderModel.hashCode();
        assertEquals(expectedHashCodeResult, genderModel.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GenderModel#equals(Object)}
     *   <li>{@link GenderModel#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(1);
        genderModel.setGenderName("Gender Name");

        GenderModel genderModel2 = new GenderModel();
        genderModel2.setGenderID(1);
        genderModel2.setGenderName("Gender Name");

        // Act and Assert
        assertEquals(genderModel, genderModel2);
        int expectedHashCodeResult = genderModel.hashCode();
        assertEquals(expectedHashCodeResult, genderModel2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GenderModel#equals(Object)}
     *   <li>{@link GenderModel#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(null);
        genderModel.setGenderName("Gender Name");

        GenderModel genderModel2 = new GenderModel();
        genderModel2.setGenderID(null);
        genderModel2.setGenderName("Gender Name");

        // Act and Assert
        assertEquals(genderModel, genderModel2);
        int expectedHashCodeResult = genderModel.hashCode();
        assertEquals(expectedHashCodeResult, genderModel2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GenderModel#equals(Object)}
     *   <li>{@link GenderModel#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        GenderModel genderModel = new GenderModel();
        genderModel.setGenderID(1);
        genderModel.setGenderName(null);

        GenderModel genderModel2 = new GenderModel();
        genderModel2.setGenderID(1);
        genderModel2.setGenderName(null);

        // Act and Assert
        assertEquals(genderModel, genderModel2);
        int expectedHashCodeResult = genderModel.hashCode();
        assertEquals(expectedHashCodeResult, genderModel2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link GenderModel}
     *   <li>{@link GenderModel#setGenderID(Integer)}
     *   <li>{@link GenderModel#setGenderName(String)}
     *   <li>{@link GenderModel#toString()}
     *   <li>{@link GenderModel#getGenderID()}
     *   <li>{@link GenderModel#getGenderName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        GenderModel actualGenderModel = new GenderModel();
        actualGenderModel.setGenderID(1);
        actualGenderModel.setGenderName("Gender Name");
        String actualToStringResult = actualGenderModel.toString();
        Integer actualGenderID = actualGenderModel.getGenderID();

        // Assert that nothing has changed
        assertEquals("Gender Name", actualGenderModel.getGenderName());
        assertEquals("GenderModel(genderID=1, genderName=Gender Name)", actualToStringResult);
        assertEquals(1, actualGenderID.intValue());
    }
}
