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

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.inventory.data.stockadjustment.StockAdjustmentItem;
import com.iemr.inventory.data.stockadjustment.StockAdjustmentItemDraft;

@Repository
public interface StockAdjustmentItemRepo extends CrudRepository<StockAdjustmentItem, Integer> {

	@Transactional
	@Modifying
	@Query("update StockAdjustmentItem p set p.vanSerialNo=p.sAItemMapID where p.vanSerialNo is null and p.sAItemMapID>0")
	Integer updateVanSerialNo();

	List<StockAdjustmentItem> findByStockAdjustmentIDAndSyncFacilityID(Long vanSerialNo, Integer syncFacilityID);

	// Items of the facility's adjustments saved in the last :seconds seconds. A locking
	// read, so it sees an adjustment another request committed a moment ago.
	@Query(value = "SELECT StockAdjustmentID, ItemStockEntryID, IsAdded, AdjustedQuantity FROM t_saitemmapping "
			+ "WHERE FacilityID = :facilityID AND IFNULL(Deleted, 0) = 0 "
			+ "AND CreatedDate >= NOW() - INTERVAL :seconds SECOND LOCK IN SHARE MODE", nativeQuery = true)
	List<Object[]> findRecentAdjustmentItems(@Param("facilityID") Integer facilityID, @Param("seconds") Integer seconds);

}
