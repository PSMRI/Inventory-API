package com.iemr.inventory.data.dispenseagainst_rx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class PrescribedDrugDetailsTest {
    /**
     * Method under test:
     * {@link PrescribedDrugDetails#getPrescribedMedicines(ArrayList)}
     */
    @Test
    void testGetPrescribedMedicines() {
        // Arrange and Act
        ArrayList<PrescribedDrugDetails> actualPrescribedMedicines = PrescribedDrugDetails
                .getPrescribedMedicines(new ArrayList<>());

        // Assert
        assertTrue(actualPrescribedMedicines.isEmpty());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PrescribedDrugDetails#PrescribedDrugDetails()}
     *   <li>{@link PrescribedDrugDetails#setBatchNo(String)}
     *   <li>{@link PrescribedDrugDetails#setBenVisitID(Long)}
     *   <li>{@link PrescribedDrugDetails#setBeneficiaryRegID(Long)}
     *   <li>{@link PrescribedDrugDetails#setCreatedBy(String)}
     *   <li>{@link PrescribedDrugDetails#setCreatedDate(Timestamp)}
     *   <li>{@link PrescribedDrugDetails#setDose(String)}
     *   <li>{@link PrescribedDrugDetails#setDrugForm(String)}
     *   <li>{@link PrescribedDrugDetails#setDrugID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setDrugStrength(String)}
     *   <li>{@link PrescribedDrugDetails#setDuartionUnit(String)}
     *   <li>{@link PrescribedDrugDetails#setDuration(String)}
     *   <li>{@link PrescribedDrugDetails#setExpiryDate(Timestamp)}
     *   <li>{@link PrescribedDrugDetails#setFacilityID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setFrequency(String)}
     *   <li>{@link PrescribedDrugDetails#setGenericDrugName(String)}
     *   <li>{@link PrescribedDrugDetails#setID(Long)}
     *   <li>{@link PrescribedDrugDetails#setIsEDL(Boolean)}
     *   <li>{@link PrescribedDrugDetails#setItemStockEntryID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setPrescribedDrugID(Long)}
     *   <li>{@link PrescribedDrugDetails#setPrescriptionID(Long)}
     *   <li>{@link PrescribedDrugDetails#setQtyInHand(Long)}
     *   <li>{@link PrescribedDrugDetails#setQtyPrescribed(Integer)}
     *   <li>{@link PrescribedDrugDetails#setQuantityInHand(Integer)}
     *   <li>{@link PrescribedDrugDetails#setRelationToFood(String)}
     *   <li>{@link PrescribedDrugDetails#setRoute(String)}
     *   <li>{@link PrescribedDrugDetails#setSpecialInstruction(String)}
     *   <li>{@link PrescribedDrugDetails#setVisitCode(Long)}
     *   <li>{@link PrescribedDrugDetails#getBatchNo()}
     *   <li>{@link PrescribedDrugDetails#getBenVisitID()}
     *   <li>{@link PrescribedDrugDetails#getBeneficiaryRegID()}
     *   <li>{@link PrescribedDrugDetails#getCreatedBy()}
     *   <li>{@link PrescribedDrugDetails#getCreatedDate()}
     *   <li>{@link PrescribedDrugDetails#getDose()}
     *   <li>{@link PrescribedDrugDetails#getDrugForm()}
     *   <li>{@link PrescribedDrugDetails#getDrugID()}
     *   <li>{@link PrescribedDrugDetails#getDrugStrength()}
     *   <li>{@link PrescribedDrugDetails#getDuartionUnit()}
     *   <li>{@link PrescribedDrugDetails#getDuration()}
     *   <li>{@link PrescribedDrugDetails#getExpiryDate()}
     *   <li>{@link PrescribedDrugDetails#getFacilityID()}
     *   <li>{@link PrescribedDrugDetails#getFrequency()}
     *   <li>{@link PrescribedDrugDetails#getGenericDrugName()}
     *   <li>{@link PrescribedDrugDetails#getID()}
     *   <li>{@link PrescribedDrugDetails#getIsEDL()}
     *   <li>{@link PrescribedDrugDetails#getItemStockEntryID()}
     *   <li>{@link PrescribedDrugDetails#getPrescribedDrugID()}
     *   <li>{@link PrescribedDrugDetails#getPrescriptionID()}
     *   <li>{@link PrescribedDrugDetails#getQtyInHand()}
     *   <li>{@link PrescribedDrugDetails#getQtyPrescribed()}
     *   <li>{@link PrescribedDrugDetails#getQuantityInHand()}
     *   <li>{@link PrescribedDrugDetails#getRelationToFood()}
     *   <li>{@link PrescribedDrugDetails#getRoute()}
     *   <li>{@link PrescribedDrugDetails#getSpecialInstruction()}
     *   <li>{@link PrescribedDrugDetails#getVisitCode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        PrescribedDrugDetails actualPrescribedDrugDetails = new PrescribedDrugDetails();
        actualPrescribedDrugDetails.setBatchNo("Batch No");
        actualPrescribedDrugDetails.setBenVisitID(1L);
        actualPrescribedDrugDetails.setBeneficiaryRegID(1L);
        actualPrescribedDrugDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        actualPrescribedDrugDetails.setCreatedDate(createdDate);
        actualPrescribedDrugDetails.setDose("Dose");
        actualPrescribedDrugDetails.setDrugForm("Drug Form");
        actualPrescribedDrugDetails.setDrugID(1);
        actualPrescribedDrugDetails.setDrugStrength("Drug Strength");
        actualPrescribedDrugDetails.setDuartionUnit("Duartion Unit");
        actualPrescribedDrugDetails.setDuration("Duration");
        Timestamp expiryDate = mock(Timestamp.class);
        actualPrescribedDrugDetails.setExpiryDate(expiryDate);
        actualPrescribedDrugDetails.setFacilityID(1);
        actualPrescribedDrugDetails.setFrequency("Frequency");
        actualPrescribedDrugDetails.setGenericDrugName("Generic Drug Name");
        actualPrescribedDrugDetails.setID(1L);
        actualPrescribedDrugDetails.setIsEDL(true);
        actualPrescribedDrugDetails.setItemStockEntryID(1);
        actualPrescribedDrugDetails.setPrescribedDrugID(1L);
        actualPrescribedDrugDetails.setPrescriptionID(1L);
        actualPrescribedDrugDetails.setQtyInHand(1L);
        actualPrescribedDrugDetails.setQtyPrescribed(1);
        actualPrescribedDrugDetails.setQuantityInHand(1);
        actualPrescribedDrugDetails.setRelationToFood("Relation To Food");
        actualPrescribedDrugDetails.setRoute("Route");
        actualPrescribedDrugDetails.setSpecialInstruction("Special Instruction");
        actualPrescribedDrugDetails.setVisitCode(1L);
        String actualBatchNo = actualPrescribedDrugDetails.getBatchNo();
        Long actualBenVisitID = actualPrescribedDrugDetails.getBenVisitID();
        Long actualBeneficiaryRegID = actualPrescribedDrugDetails.getBeneficiaryRegID();
        String actualCreatedBy = actualPrescribedDrugDetails.getCreatedBy();
        Timestamp actualCreatedDate = actualPrescribedDrugDetails.getCreatedDate();
        String actualDose = actualPrescribedDrugDetails.getDose();
        String actualDrugForm = actualPrescribedDrugDetails.getDrugForm();
        Integer actualDrugID = actualPrescribedDrugDetails.getDrugID();
        String actualDrugStrength = actualPrescribedDrugDetails.getDrugStrength();
        String actualDuartionUnit = actualPrescribedDrugDetails.getDuartionUnit();
        String actualDuration = actualPrescribedDrugDetails.getDuration();
        Timestamp actualExpiryDate = actualPrescribedDrugDetails.getExpiryDate();
        Integer actualFacilityID = actualPrescribedDrugDetails.getFacilityID();
        String actualFrequency = actualPrescribedDrugDetails.getFrequency();
        String actualGenericDrugName = actualPrescribedDrugDetails.getGenericDrugName();
        Long actualID = actualPrescribedDrugDetails.getID();
        Boolean actualIsEDL = actualPrescribedDrugDetails.getIsEDL();
        Integer actualItemStockEntryID = actualPrescribedDrugDetails.getItemStockEntryID();
        Long actualPrescribedDrugID = actualPrescribedDrugDetails.getPrescribedDrugID();
        Long actualPrescriptionID = actualPrescribedDrugDetails.getPrescriptionID();
        Long actualQtyInHand = actualPrescribedDrugDetails.getQtyInHand();
        Integer actualQtyPrescribed = actualPrescribedDrugDetails.getQtyPrescribed();
        Integer actualQuantityInHand = actualPrescribedDrugDetails.getQuantityInHand();
        String actualRelationToFood = actualPrescribedDrugDetails.getRelationToFood();
        String actualRoute = actualPrescribedDrugDetails.getRoute();
        String actualSpecialInstruction = actualPrescribedDrugDetails.getSpecialInstruction();
        Long actualVisitCode = actualPrescribedDrugDetails.getVisitCode();

        // Assert that nothing has changed
        assertEquals("Batch No", actualBatchNo);
        assertEquals("Dose", actualDose);
        assertEquals("Drug Form", actualDrugForm);
        assertEquals("Drug Strength", actualDrugStrength);
        assertEquals("Duartion Unit", actualDuartionUnit);
        assertEquals("Duration", actualDuration);
        assertEquals("Frequency", actualFrequency);
        assertEquals("Generic Drug Name", actualGenericDrugName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Relation To Food", actualRelationToFood);
        assertEquals("Route", actualRoute);
        assertEquals("Special Instruction", actualSpecialInstruction);
        assertEquals(1, actualDrugID.intValue());
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualItemStockEntryID.intValue());
        assertEquals(1, actualQtyPrescribed.intValue());
        assertEquals(1, actualQuantityInHand.intValue());
        assertEquals(1L, actualBenVisitID.longValue());
        assertEquals(1L, actualBeneficiaryRegID.longValue());
        assertEquals(1L, actualID.longValue());
        assertEquals(1L, actualPrescribedDrugID.longValue());
        assertEquals(1L, actualPrescriptionID.longValue());
        assertEquals(1L, actualQtyInHand.longValue());
        assertEquals(1L, actualVisitCode.longValue());
        assertTrue(actualIsEDL);
        assertSame(createdDate, actualCreatedDate);
        assertSame(expiryDate, actualExpiryDate);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link PrescribedDrugDetails#PrescribedDrugDetails(Long, Long, Long, Integer, String, String, String, String, String, String, String, String, String, String, Timestamp, String, Integer, Boolean)}
     *   <li>{@link PrescribedDrugDetails#setBatchNo(String)}
     *   <li>{@link PrescribedDrugDetails#setBenVisitID(Long)}
     *   <li>{@link PrescribedDrugDetails#setBeneficiaryRegID(Long)}
     *   <li>{@link PrescribedDrugDetails#setCreatedBy(String)}
     *   <li>{@link PrescribedDrugDetails#setCreatedDate(Timestamp)}
     *   <li>{@link PrescribedDrugDetails#setDose(String)}
     *   <li>{@link PrescribedDrugDetails#setDrugForm(String)}
     *   <li>{@link PrescribedDrugDetails#setDrugID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setDrugStrength(String)}
     *   <li>{@link PrescribedDrugDetails#setDuartionUnit(String)}
     *   <li>{@link PrescribedDrugDetails#setDuration(String)}
     *   <li>{@link PrescribedDrugDetails#setExpiryDate(Timestamp)}
     *   <li>{@link PrescribedDrugDetails#setFacilityID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setFrequency(String)}
     *   <li>{@link PrescribedDrugDetails#setGenericDrugName(String)}
     *   <li>{@link PrescribedDrugDetails#setID(Long)}
     *   <li>{@link PrescribedDrugDetails#setIsEDL(Boolean)}
     *   <li>{@link PrescribedDrugDetails#setItemStockEntryID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setPrescribedDrugID(Long)}
     *   <li>{@link PrescribedDrugDetails#setPrescriptionID(Long)}
     *   <li>{@link PrescribedDrugDetails#setQtyInHand(Long)}
     *   <li>{@link PrescribedDrugDetails#setQtyPrescribed(Integer)}
     *   <li>{@link PrescribedDrugDetails#setQuantityInHand(Integer)}
     *   <li>{@link PrescribedDrugDetails#setRelationToFood(String)}
     *   <li>{@link PrescribedDrugDetails#setRoute(String)}
     *   <li>{@link PrescribedDrugDetails#setSpecialInstruction(String)}
     *   <li>{@link PrescribedDrugDetails#setVisitCode(Long)}
     *   <li>{@link PrescribedDrugDetails#getBatchNo()}
     *   <li>{@link PrescribedDrugDetails#getBenVisitID()}
     *   <li>{@link PrescribedDrugDetails#getBeneficiaryRegID()}
     *   <li>{@link PrescribedDrugDetails#getCreatedBy()}
     *   <li>{@link PrescribedDrugDetails#getCreatedDate()}
     *   <li>{@link PrescribedDrugDetails#getDose()}
     *   <li>{@link PrescribedDrugDetails#getDrugForm()}
     *   <li>{@link PrescribedDrugDetails#getDrugID()}
     *   <li>{@link PrescribedDrugDetails#getDrugStrength()}
     *   <li>{@link PrescribedDrugDetails#getDuartionUnit()}
     *   <li>{@link PrescribedDrugDetails#getDuration()}
     *   <li>{@link PrescribedDrugDetails#getExpiryDate()}
     *   <li>{@link PrescribedDrugDetails#getFacilityID()}
     *   <li>{@link PrescribedDrugDetails#getFrequency()}
     *   <li>{@link PrescribedDrugDetails#getGenericDrugName()}
     *   <li>{@link PrescribedDrugDetails#getID()}
     *   <li>{@link PrescribedDrugDetails#getIsEDL()}
     *   <li>{@link PrescribedDrugDetails#getItemStockEntryID()}
     *   <li>{@link PrescribedDrugDetails#getPrescribedDrugID()}
     *   <li>{@link PrescribedDrugDetails#getPrescriptionID()}
     *   <li>{@link PrescribedDrugDetails#getQtyInHand()}
     *   <li>{@link PrescribedDrugDetails#getQtyPrescribed()}
     *   <li>{@link PrescribedDrugDetails#getQuantityInHand()}
     *   <li>{@link PrescribedDrugDetails#getRelationToFood()}
     *   <li>{@link PrescribedDrugDetails#getRoute()}
     *   <li>{@link PrescribedDrugDetails#getSpecialInstruction()}
     *   <li>{@link PrescribedDrugDetails#getVisitCode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        PrescribedDrugDetails actualPrescribedDrugDetails = new PrescribedDrugDetails(1L, 1L, 1L, 1, "Generic Drug Name",
                "Drug Form", "Drug Strength", "Dose", "Route", "Frequency", "Duration", "Duartion Unit", "Relation To Food",
                "Special Instruction", mock(Timestamp.class), "Jan 1, 2020 8:00am GMT+0100", 1, true);
        actualPrescribedDrugDetails.setBatchNo("Batch No");
        actualPrescribedDrugDetails.setBenVisitID(1L);
        actualPrescribedDrugDetails.setBeneficiaryRegID(1L);
        actualPrescribedDrugDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        actualPrescribedDrugDetails.setCreatedDate(createdDate);
        actualPrescribedDrugDetails.setDose("Dose");
        actualPrescribedDrugDetails.setDrugForm("Drug Form");
        actualPrescribedDrugDetails.setDrugID(1);
        actualPrescribedDrugDetails.setDrugStrength("Drug Strength");
        actualPrescribedDrugDetails.setDuartionUnit("Duartion Unit");
        actualPrescribedDrugDetails.setDuration("Duration");
        Timestamp expiryDate = mock(Timestamp.class);
        actualPrescribedDrugDetails.setExpiryDate(expiryDate);
        actualPrescribedDrugDetails.setFacilityID(1);
        actualPrescribedDrugDetails.setFrequency("Frequency");
        actualPrescribedDrugDetails.setGenericDrugName("Generic Drug Name");
        actualPrescribedDrugDetails.setID(1L);
        actualPrescribedDrugDetails.setIsEDL(true);
        actualPrescribedDrugDetails.setItemStockEntryID(1);
        actualPrescribedDrugDetails.setPrescribedDrugID(1L);
        actualPrescribedDrugDetails.setPrescriptionID(1L);
        actualPrescribedDrugDetails.setQtyInHand(1L);
        actualPrescribedDrugDetails.setQtyPrescribed(1);
        actualPrescribedDrugDetails.setQuantityInHand(1);
        actualPrescribedDrugDetails.setRelationToFood("Relation To Food");
        actualPrescribedDrugDetails.setRoute("Route");
        actualPrescribedDrugDetails.setSpecialInstruction("Special Instruction");
        actualPrescribedDrugDetails.setVisitCode(1L);
        String actualBatchNo = actualPrescribedDrugDetails.getBatchNo();
        Long actualBenVisitID = actualPrescribedDrugDetails.getBenVisitID();
        Long actualBeneficiaryRegID = actualPrescribedDrugDetails.getBeneficiaryRegID();
        String actualCreatedBy = actualPrescribedDrugDetails.getCreatedBy();
        Timestamp actualCreatedDate = actualPrescribedDrugDetails.getCreatedDate();
        String actualDose = actualPrescribedDrugDetails.getDose();
        String actualDrugForm = actualPrescribedDrugDetails.getDrugForm();
        Integer actualDrugID = actualPrescribedDrugDetails.getDrugID();
        String actualDrugStrength = actualPrescribedDrugDetails.getDrugStrength();
        String actualDuartionUnit = actualPrescribedDrugDetails.getDuartionUnit();
        String actualDuration = actualPrescribedDrugDetails.getDuration();
        Timestamp actualExpiryDate = actualPrescribedDrugDetails.getExpiryDate();
        Integer actualFacilityID = actualPrescribedDrugDetails.getFacilityID();
        String actualFrequency = actualPrescribedDrugDetails.getFrequency();
        String actualGenericDrugName = actualPrescribedDrugDetails.getGenericDrugName();
        Long actualID = actualPrescribedDrugDetails.getID();
        Boolean actualIsEDL = actualPrescribedDrugDetails.getIsEDL();
        Integer actualItemStockEntryID = actualPrescribedDrugDetails.getItemStockEntryID();
        Long actualPrescribedDrugID = actualPrescribedDrugDetails.getPrescribedDrugID();
        Long actualPrescriptionID = actualPrescribedDrugDetails.getPrescriptionID();
        Long actualQtyInHand = actualPrescribedDrugDetails.getQtyInHand();
        Integer actualQtyPrescribed = actualPrescribedDrugDetails.getQtyPrescribed();
        Integer actualQuantityInHand = actualPrescribedDrugDetails.getQuantityInHand();
        String actualRelationToFood = actualPrescribedDrugDetails.getRelationToFood();
        String actualRoute = actualPrescribedDrugDetails.getRoute();
        String actualSpecialInstruction = actualPrescribedDrugDetails.getSpecialInstruction();
        Long actualVisitCode = actualPrescribedDrugDetails.getVisitCode();

        // Assert that nothing has changed
        assertEquals("Batch No", actualBatchNo);
        assertEquals("Dose", actualDose);
        assertEquals("Drug Form", actualDrugForm);
        assertEquals("Drug Strength", actualDrugStrength);
        assertEquals("Duartion Unit", actualDuartionUnit);
        assertEquals("Duration", actualDuration);
        assertEquals("Frequency", actualFrequency);
        assertEquals("Generic Drug Name", actualGenericDrugName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Relation To Food", actualRelationToFood);
        assertEquals("Route", actualRoute);
        assertEquals("Special Instruction", actualSpecialInstruction);
        assertEquals(1, actualDrugID.intValue());
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualItemStockEntryID.intValue());
        assertEquals(1, actualQtyPrescribed.intValue());
        assertEquals(1, actualQuantityInHand.intValue());
        assertEquals(1L, actualBenVisitID.longValue());
        assertEquals(1L, actualBeneficiaryRegID.longValue());
        assertEquals(1L, actualID.longValue());
        assertEquals(1L, actualPrescribedDrugID.longValue());
        assertEquals(1L, actualPrescriptionID.longValue());
        assertEquals(1L, actualQtyInHand.longValue());
        assertEquals(1L, actualVisitCode.longValue());
        assertTrue(actualIsEDL);
        assertSame(createdDate, actualCreatedDate);
        assertSame(expiryDate, actualExpiryDate);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link PrescribedDrugDetails#PrescribedDrugDetails(Long, Long, Long, Integer, String, String, String, String, String, String, String, String, String, String, Timestamp, String, Integer, String, Long, Timestamp, Integer, Boolean)}
     *   <li>{@link PrescribedDrugDetails#setBatchNo(String)}
     *   <li>{@link PrescribedDrugDetails#setBenVisitID(Long)}
     *   <li>{@link PrescribedDrugDetails#setBeneficiaryRegID(Long)}
     *   <li>{@link PrescribedDrugDetails#setCreatedBy(String)}
     *   <li>{@link PrescribedDrugDetails#setCreatedDate(Timestamp)}
     *   <li>{@link PrescribedDrugDetails#setDose(String)}
     *   <li>{@link PrescribedDrugDetails#setDrugForm(String)}
     *   <li>{@link PrescribedDrugDetails#setDrugID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setDrugStrength(String)}
     *   <li>{@link PrescribedDrugDetails#setDuartionUnit(String)}
     *   <li>{@link PrescribedDrugDetails#setDuration(String)}
     *   <li>{@link PrescribedDrugDetails#setExpiryDate(Timestamp)}
     *   <li>{@link PrescribedDrugDetails#setFacilityID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setFrequency(String)}
     *   <li>{@link PrescribedDrugDetails#setGenericDrugName(String)}
     *   <li>{@link PrescribedDrugDetails#setID(Long)}
     *   <li>{@link PrescribedDrugDetails#setIsEDL(Boolean)}
     *   <li>{@link PrescribedDrugDetails#setItemStockEntryID(Integer)}
     *   <li>{@link PrescribedDrugDetails#setPrescribedDrugID(Long)}
     *   <li>{@link PrescribedDrugDetails#setPrescriptionID(Long)}
     *   <li>{@link PrescribedDrugDetails#setQtyInHand(Long)}
     *   <li>{@link PrescribedDrugDetails#setQtyPrescribed(Integer)}
     *   <li>{@link PrescribedDrugDetails#setQuantityInHand(Integer)}
     *   <li>{@link PrescribedDrugDetails#setRelationToFood(String)}
     *   <li>{@link PrescribedDrugDetails#setRoute(String)}
     *   <li>{@link PrescribedDrugDetails#setSpecialInstruction(String)}
     *   <li>{@link PrescribedDrugDetails#setVisitCode(Long)}
     *   <li>{@link PrescribedDrugDetails#getBatchNo()}
     *   <li>{@link PrescribedDrugDetails#getBenVisitID()}
     *   <li>{@link PrescribedDrugDetails#getBeneficiaryRegID()}
     *   <li>{@link PrescribedDrugDetails#getCreatedBy()}
     *   <li>{@link PrescribedDrugDetails#getCreatedDate()}
     *   <li>{@link PrescribedDrugDetails#getDose()}
     *   <li>{@link PrescribedDrugDetails#getDrugForm()}
     *   <li>{@link PrescribedDrugDetails#getDrugID()}
     *   <li>{@link PrescribedDrugDetails#getDrugStrength()}
     *   <li>{@link PrescribedDrugDetails#getDuartionUnit()}
     *   <li>{@link PrescribedDrugDetails#getDuration()}
     *   <li>{@link PrescribedDrugDetails#getExpiryDate()}
     *   <li>{@link PrescribedDrugDetails#getFacilityID()}
     *   <li>{@link PrescribedDrugDetails#getFrequency()}
     *   <li>{@link PrescribedDrugDetails#getGenericDrugName()}
     *   <li>{@link PrescribedDrugDetails#getID()}
     *   <li>{@link PrescribedDrugDetails#getIsEDL()}
     *   <li>{@link PrescribedDrugDetails#getItemStockEntryID()}
     *   <li>{@link PrescribedDrugDetails#getPrescribedDrugID()}
     *   <li>{@link PrescribedDrugDetails#getPrescriptionID()}
     *   <li>{@link PrescribedDrugDetails#getQtyInHand()}
     *   <li>{@link PrescribedDrugDetails#getQtyPrescribed()}
     *   <li>{@link PrescribedDrugDetails#getQuantityInHand()}
     *   <li>{@link PrescribedDrugDetails#getRelationToFood()}
     *   <li>{@link PrescribedDrugDetails#getRoute()}
     *   <li>{@link PrescribedDrugDetails#getSpecialInstruction()}
     *   <li>{@link PrescribedDrugDetails#getVisitCode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters3() {
        // Arrange and Act
        PrescribedDrugDetails actualPrescribedDrugDetails = new PrescribedDrugDetails(1L, 1L, 1L, 1, "Generic Drug Name",
                "Drug Form", "Drug Strength", "Dose", "Route", "Frequency", "Duration", "Duartion Unit", "Relation To Food",
                "Special Instruction", mock(Timestamp.class), "Jan 1, 2020 8:00am GMT+0100", 1, "Batch No", 1L,
                mock(Timestamp.class), 1, true);
        actualPrescribedDrugDetails.setBatchNo("Batch No");
        actualPrescribedDrugDetails.setBenVisitID(1L);
        actualPrescribedDrugDetails.setBeneficiaryRegID(1L);
        actualPrescribedDrugDetails.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        Timestamp createdDate = mock(Timestamp.class);
        actualPrescribedDrugDetails.setCreatedDate(createdDate);
        actualPrescribedDrugDetails.setDose("Dose");
        actualPrescribedDrugDetails.setDrugForm("Drug Form");
        actualPrescribedDrugDetails.setDrugID(1);
        actualPrescribedDrugDetails.setDrugStrength("Drug Strength");
        actualPrescribedDrugDetails.setDuartionUnit("Duartion Unit");
        actualPrescribedDrugDetails.setDuration("Duration");
        Timestamp expiryDate = mock(Timestamp.class);
        actualPrescribedDrugDetails.setExpiryDate(expiryDate);
        actualPrescribedDrugDetails.setFacilityID(1);
        actualPrescribedDrugDetails.setFrequency("Frequency");
        actualPrescribedDrugDetails.setGenericDrugName("Generic Drug Name");
        actualPrescribedDrugDetails.setID(1L);
        actualPrescribedDrugDetails.setIsEDL(true);
        actualPrescribedDrugDetails.setItemStockEntryID(1);
        actualPrescribedDrugDetails.setPrescribedDrugID(1L);
        actualPrescribedDrugDetails.setPrescriptionID(1L);
        actualPrescribedDrugDetails.setQtyInHand(1L);
        actualPrescribedDrugDetails.setQtyPrescribed(1);
        actualPrescribedDrugDetails.setQuantityInHand(1);
        actualPrescribedDrugDetails.setRelationToFood("Relation To Food");
        actualPrescribedDrugDetails.setRoute("Route");
        actualPrescribedDrugDetails.setSpecialInstruction("Special Instruction");
        actualPrescribedDrugDetails.setVisitCode(1L);
        String actualBatchNo = actualPrescribedDrugDetails.getBatchNo();
        Long actualBenVisitID = actualPrescribedDrugDetails.getBenVisitID();
        Long actualBeneficiaryRegID = actualPrescribedDrugDetails.getBeneficiaryRegID();
        String actualCreatedBy = actualPrescribedDrugDetails.getCreatedBy();
        Timestamp actualCreatedDate = actualPrescribedDrugDetails.getCreatedDate();
        String actualDose = actualPrescribedDrugDetails.getDose();
        String actualDrugForm = actualPrescribedDrugDetails.getDrugForm();
        Integer actualDrugID = actualPrescribedDrugDetails.getDrugID();
        String actualDrugStrength = actualPrescribedDrugDetails.getDrugStrength();
        String actualDuartionUnit = actualPrescribedDrugDetails.getDuartionUnit();
        String actualDuration = actualPrescribedDrugDetails.getDuration();
        Timestamp actualExpiryDate = actualPrescribedDrugDetails.getExpiryDate();
        Integer actualFacilityID = actualPrescribedDrugDetails.getFacilityID();
        String actualFrequency = actualPrescribedDrugDetails.getFrequency();
        String actualGenericDrugName = actualPrescribedDrugDetails.getGenericDrugName();
        Long actualID = actualPrescribedDrugDetails.getID();
        Boolean actualIsEDL = actualPrescribedDrugDetails.getIsEDL();
        Integer actualItemStockEntryID = actualPrescribedDrugDetails.getItemStockEntryID();
        Long actualPrescribedDrugID = actualPrescribedDrugDetails.getPrescribedDrugID();
        Long actualPrescriptionID = actualPrescribedDrugDetails.getPrescriptionID();
        Long actualQtyInHand = actualPrescribedDrugDetails.getQtyInHand();
        Integer actualQtyPrescribed = actualPrescribedDrugDetails.getQtyPrescribed();
        Integer actualQuantityInHand = actualPrescribedDrugDetails.getQuantityInHand();
        String actualRelationToFood = actualPrescribedDrugDetails.getRelationToFood();
        String actualRoute = actualPrescribedDrugDetails.getRoute();
        String actualSpecialInstruction = actualPrescribedDrugDetails.getSpecialInstruction();
        Long actualVisitCode = actualPrescribedDrugDetails.getVisitCode();

        // Assert that nothing has changed
        assertEquals("Batch No", actualBatchNo);
        assertEquals("Dose", actualDose);
        assertEquals("Drug Form", actualDrugForm);
        assertEquals("Drug Strength", actualDrugStrength);
        assertEquals("Duartion Unit", actualDuartionUnit);
        assertEquals("Duration", actualDuration);
        assertEquals("Frequency", actualFrequency);
        assertEquals("Generic Drug Name", actualGenericDrugName);
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualCreatedBy);
        assertEquals("Relation To Food", actualRelationToFood);
        assertEquals("Route", actualRoute);
        assertEquals("Special Instruction", actualSpecialInstruction);
        assertEquals(1, actualDrugID.intValue());
        assertEquals(1, actualFacilityID.intValue());
        assertEquals(1, actualItemStockEntryID.intValue());
        assertEquals(1, actualQtyPrescribed.intValue());
        assertEquals(1, actualQuantityInHand.intValue());
        assertEquals(1L, actualBenVisitID.longValue());
        assertEquals(1L, actualBeneficiaryRegID.longValue());
        assertEquals(1L, actualID.longValue());
        assertEquals(1L, actualPrescribedDrugID.longValue());
        assertEquals(1L, actualPrescriptionID.longValue());
        assertEquals(1L, actualQtyInHand.longValue());
        assertEquals(1L, actualVisitCode.longValue());
        assertTrue(actualIsEDL);
        assertSame(createdDate, actualCreatedDate);
        assertSame(expiryDate, actualExpiryDate);
    }
}
