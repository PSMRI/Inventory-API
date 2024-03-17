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
package com.iemr.inventory.repo.stockExit;

import com.iemr.inventory.data.stockExit.T_PatientIssue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PatientIssueRepo extends CrudRepository<T_PatientIssue, Long> {

    List<T_PatientIssue> findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(Integer faciilityID,
                                                                                     Timestamp fromDate, Timestamp toDate);

    @Transactional
    @Modifying
    @Query(value = " UPDATE db_iemr.i_ben_flow_outreach SET pharmacist_flag = 9, Processed = 'U' "
            + " WHERE beneficiary_reg_id = :benRegID AND beneficiary_visit_code = :benVisitCode ", nativeQuery = true)
    public int updateBenStatusFlowAfterPharma(@Param("benRegID") Long benRegID,
                                              @Param("benVisitCode") Long benVisitCode);

    @Transactional
    @Modifying
    @Query("update T_PatientIssue p set p.vanSerialNo=p.patientIssueID where p.vanSerialNo is null and p.patientIssueID>0")
    Integer updateVanSerialNo();

}
