package com.iemr.inventory.data.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;

class M_UserDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link M_User#setAadhaarNo(String)}
     *   <li>{@link M_User#setCreatedBy(String)}
     *   <li>{@link M_User#setCreatedDate(Timestamp)}
     *   <li>{@link M_User#setDOB(Timestamp)}
     *   <li>{@link M_User#setDOJ(Timestamp)}
     *   <li>{@link M_User#setEmailID(String)}
     *   <li>{@link M_User#setEmergencyContactNo(String)}
     *   <li>{@link M_User#setEmergencyContactPerson(String)}
     *   <li>{@link M_User#setFirstName(String)}
     *   <li>{@link M_User#setGenderID(Integer)}
     *   <li>{@link M_User#setLastModDate(Timestamp)}
     *   <li>{@link M_User#setLastName(String)}
     *   <li>{@link M_User#setMaritalStatusID(Integer)}
     *   <li>{@link M_User#setMiddleName(String)}
     *   <li>{@link M_User#setModifiedBy(String)}
     *   <li>{@link M_User#setPAN(String)}
     *   <li>{@link M_User#setPassword(String)}
     *   <li>{@link M_User#setProviderAdminDetails(ArrayList)}
     *   <li>{@link M_User#setQualificationID(Integer)}
     *   <li>{@link M_User#setStatusID(Integer)}
     *   <li>{@link M_User#setTitleID(Integer)}
     *   <li>{@link M_User#setUserID(int)}
     *   <li>{@link M_User#setUserName(String)}
     *   <li>{@link M_User#toString()}
     *   <li>{@link M_User#getAadhaarNo()}
     *   <li>{@link M_User#getCreatedBy()}
     *   <li>{@link M_User#getCreatedDate()}
     *   <li>{@link M_User#getDOB()}
     *   <li>{@link M_User#getDOJ()}
     *   <li>{@link M_User#getEmailID()}
     *   <li>{@link M_User#getEmergencyContactNo()}
     *   <li>{@link M_User#getEmergencyContactPerson()}
     *   <li>{@link M_User#getFirstName()}
     *   <li>{@link M_User#getGenderID()}
     *   <li>{@link M_User#getLastModDate()}
     *   <li>{@link M_User#getLastName()}
     *   <li>{@link M_User#getMaritalStatusID()}
     *   <li>{@link M_User#getMiddleName()}
     *   <li>{@link M_User#getModifiedBy()}
     *   <li>{@link M_User#getPAN()}
     *   <li>{@link M_User#getPassword()}
     *   <li>{@link M_User#getProviderAdminDetails()}
     *   <li>{@link M_User#getQualificationID()}
     *   <li>{@link M_User#getStatusID()}
     *   <li>{@link M_User#getTitleID()}
     *   <li>{@link M_User#getUserID()}
     *   <li>{@link M_User#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        M_User m_User = new M_User();

        // Act
        m_User.setAadhaarNo("Aadhaar No");
        m_User.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        m_User.setCreatedDate(createdDate);
        Timestamp dOB = mock(Timestamp.class);
        m_User.setDOB(dOB);
        Timestamp dOJ = mock(Timestamp.class);
        m_User.setDOJ(dOJ);
        m_User.setEmailID("jane.doe@example.org");
        m_User.setEmergencyContactNo("Emergency Contact No");
        m_User.setEmergencyContactPerson("Emergency Contact Person");
        m_User.setFirstName("Jane");
        m_User.setGenderID(1);
        Timestamp lastModDate = mock(Timestamp.class);
        m_User.setLastModDate(lastModDate);
        m_User.setLastName("Doe");
        m_User.setMaritalStatusID(1);
        m_User.setMiddleName("Middle Name");
        m_User.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        m_User.setPAN("P AN");
        m_User.setPassword("iloveyou");
        ArrayList<Map<String, Object>> providerAdminDetails = new ArrayList<>();
        m_User.setProviderAdminDetails(providerAdminDetails);
        m_User.setQualificationID(1);
        m_User.setStatusID(1);
        m_User.setTitleID(1);
        m_User.setUserID(1);
        m_User.setUserName("janedoe");
        String actualToStringResult = m_User.toString();
        String actualAadhaarNo = m_User.getAadhaarNo();
        String actualCreatedBy = m_User.getCreatedBy();
        Timestamp actualCreatedDate = m_User.getCreatedDate();
        Timestamp actualDOB = m_User.getDOB();
        Timestamp actualDOJ = m_User.getDOJ();
        String actualEmailID = m_User.getEmailID();
        String actualEmergencyContactNo = m_User.getEmergencyContactNo();
        String actualEmergencyContactPerson = m_User.getEmergencyContactPerson();
        String actualFirstName = m_User.getFirstName();
        Integer actualGenderID = m_User.getGenderID();
        Timestamp actualLastModDate = m_User.getLastModDate();
        String actualLastName = m_User.getLastName();
        Integer actualMaritalStatusID = m_User.getMaritalStatusID();
        String actualMiddleName = m_User.getMiddleName();
        String actualModifiedBy = m_User.getModifiedBy();
        String actualPAN = m_User.getPAN();
        String actualPassword = m_User.getPassword();
        ArrayList<Map<String, Object>> actualProviderAdminDetails = m_User.getProviderAdminDetails();
        Integer actualQualificationID = m_User.getQualificationID();
        Integer actualStatusID = m_User.getStatusID();
        Integer actualTitleID = m_User.getTitleID();
        int actualUserID = m_User.getUserID();

        // Assert that nothing has changed
        assertEquals("Aadhaar No", actualAadhaarNo);
        assertEquals("Doe", actualLastName);
        assertEquals("Emergency Contact No", actualEmergencyContactNo);
        assertEquals("Emergency Contact Person", actualEmergencyContactPerson);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualModifiedBy);
        assertEquals("Jane", actualFirstName);
        assertEquals("Middle Name", actualMiddleName);
        assertEquals("P AN", actualPAN);
        assertEquals("iloveyou", actualPassword);
        assertEquals("jane.doe@example.org", actualEmailID);
        assertEquals("janedoe", m_User.getUserName());
        assertEquals("{\"userID\":1,\"Deleted\":null,\"providerAdminDetails\":[]}", actualToStringResult);
        assertEquals(1, actualUserID);
        assertEquals(1, actualGenderID.intValue());
        assertEquals(1, actualMaritalStatusID.intValue());
        assertEquals(1, actualQualificationID.intValue());
        assertEquals(1, actualStatusID.intValue());
        assertEquals(1, actualTitleID.intValue());
        assertSame(providerAdminDetails, actualProviderAdminDetails);
        assertSame(createdDate, actualCreatedDate);
        assertSame(dOB, actualDOB);
        assertSame(dOJ, actualDOJ);
        assertSame(lastModDate, actualLastModDate);
    }
}
