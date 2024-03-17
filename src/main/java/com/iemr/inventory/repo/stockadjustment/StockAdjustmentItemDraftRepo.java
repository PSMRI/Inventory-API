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

import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAdjustmentItemDraftRepo extends CrudRepository<StockAdjustmentItemDraft, Long> {


    @Query(value = " SELECT sadi "
            + " FROM StockAdjustmentItemDraft sadi  "
            + " JOIN sadi.itemStockEntry ise "
            + " WHERE sadi.sADraftItemMapID =:id", nativeQuery = true)
    StockAdjustmentItemDraft getforedit(@Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE StockAdjustmentItemDraft c SET c.deleted = true WHERE c.stockAdjustmentDraftID = :id", nativeQuery = true)
    Integer updateDeleted(@Param("id") Long stockAdjustmentDraftID);


    //StockAdjustmentItemDraft findBySADraftItemMapID(Long saDraftItemMapID);


}
