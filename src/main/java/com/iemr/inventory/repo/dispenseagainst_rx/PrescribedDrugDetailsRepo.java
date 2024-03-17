/*
 * AMRIT – Accessible Medical Records via Integrated Technology
 * Integrated EHR (Electronic Health Records) Solution
 *
 * Copyright (C) "Piramal Swasthya Management and Research Institute"
 *
 * This file is part of AMRIT.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 */
package com.iemr.inventory.repo.dispenseagainst_rx;

import com.iemr.inventory.data.dispenseagainst_rx.PrescribedDrugDetails;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Repository
@Configuration
@ComponentScan
public interface PrescribedDrugDetailsRepo extends CrudRepository<PrescribedDrugDetails, Long> {
    @Query(" SELECT Distinct beneficiaryRegID, visitCode, prescriptionID, drugID, genericDrugName, drugForm,"
            + " drugStrength, dose, route, frequency, "
            + " duration, duartionUnit, relationToFood, specialInstruction, createdDate, createdBy, "
            + " itemStockEntryID, batchNo, quantityInHand, expiryDate, qtyPrescribed, isEDL  "
            + " FROM PrescribedDrugDetails  WHERE beneficiaryRegID =:benRegID "
            + " AND visitCode =:visitCode AND (facilityID =:facilityID or facilityID is null) ORDER BY drugID, createdDate DESC ")
    ArrayList<Object[]> getPrescribedMedicinesWithDetails(@Param("benRegID") Long benRegID,
                                                          @Param("visitCode") Long visitCode, @Param("facilityID") Integer facilityID);

}
