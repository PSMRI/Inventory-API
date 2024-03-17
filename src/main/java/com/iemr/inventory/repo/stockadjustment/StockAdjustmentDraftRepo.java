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
package com.iemr.inventory.repo.stockadjustment;

import com.iemr.inventory.data.stockadjustment.StockAdjustmentDraft;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StockAdjustmentDraftRepo extends CrudRepository<StockAdjustmentDraft, Long> {

    List<StockAdjustmentDraft> findByIsCompletedAndFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(Boolean com, Integer facilityID,
                                                                                                         Timestamp fromDate, Timestamp toDate);

    @Query("SELECT sad "
            + " FROM StockAdjustmentDraft sad "
            + " inner JOIN sad.stockAdjustmentItemDraft sadi  "
            + " inner JOIN sadi.itemStockEntry ise  "
            + " WHERE sad.stockAdjustmentDraftID = :id and sadi.deleted=false")
    StockAdjustmentDraft getforedit(@Param("id") Long stockAdjustmentDraftID);

    @Transactional
    @Modifying
    @Query("UPDATE StockAdjustmentDraft c SET c.draftDesc = :draftDesc, c.modifiedBy = :modifiedBy,c.refNo=:refNo,c.draftName=:draftName WHERE c.stockAdjustmentDraftID = :id")
    Integer updateStock(@Param("id") Long id, @Param("draftDesc") String draftDesc, @Param("draftName") String draftName,
                        @Param("refNo") String refNo, @Param("modifiedBy") String modifiedBy);

    @Transactional
    @Modifying
    @Query("UPDATE StockAdjustmentDraft c SET c.isCompleted = :isCompleted WHERE c.stockAdjustmentDraftID = :id")
    Integer updatecompleted(@Param("id") Long stockAdjustmentDraftID, @Param("isCompleted") boolean b);

}
