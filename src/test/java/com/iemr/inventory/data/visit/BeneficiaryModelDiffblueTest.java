package com.iemr.inventory.data.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;

import org.junit.jupiter.api.Test;

class BeneficiaryModelTest {
    /**
     * Method under test: {@link BeneficiaryModel#getTimestampData(Timestamp)}
     */
    @Test
    void testGetTimestampData() {
        // TODO:  Cover was only able to create a partial test for this method:
        //    AI was unable to find a test

        // Arrange and Act
        BeneficiaryModel.getTimestampData(mock(Timestamp.class));
    }

    /**
     * Method under test: {@link BeneficiaryModel#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "{\"beneficiaryRegID\":null,\"benVisitDetail\":null,\"beneficiaryFlowStatus\":null,\"i_bendemographics\":null"
                        + ",\"beneficiaryID\":null,\"titleId\":null,\"titleName\":null,\"firstName\":null,\"middleName\":null,\"lastName\""
                        + ":null,\"statusID\":null,\"statusName\":null,\"genderID\":null,\"genderName\":null,\"m_gender\":null,\"maritalStatusID"
                        + "\":null,\"maritalStatusName\":null,\"sexualOrientationId\":null,\"sexualOrientationName\":null,\"dOB\":null,"
                        + "\"age\":null,\"fatherName\":null,\"spouseName\":null,\"govtIdentityNo\":null,\"govtIdentityTypeID\":null,"
                        + "\"registeredServiceID\":null,\"sourceOfInformation\":null,\"isHIVPos\":null,\"placeOfWork\":null,\"remarks\""
                        + ":null,\"changeInSelfDetails\":null,\"changeInAddress\":null,\"changeInContacts\":null,\"changeInIdentities\""
                        + ":null,\"changeInOtherDetails\":null,\"changeInFamilyDetails\":null,\"changeInAssociations\":null,\"is1097\""
                        + ":false,\"deleted\":null,\"createdBy\":null,\"createdDate\":null,\"modifiedBy\":null,\"lastModDate\":null}",
                (new BeneficiaryModel()).toString());
    }
}
