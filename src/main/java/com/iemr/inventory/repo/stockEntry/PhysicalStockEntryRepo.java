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
package com.iemr.inventory.repo.stockEntry;


import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PhysicalStockEntryRepo extends CrudRepository<PhysicalStockEntry, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE PhysicalStockEntry c SET c.deleted = :bool WHERE c.phyEntryID = :id")
    Integer updateDelete(@Param("id") Integer id, @Param("bool") Boolean bool);


    List<PhysicalStockEntry> findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(Integer facilityID, Timestamp fromDate, Timestamp toDate);

    @Transactional
    @Modifying
    @Query("update PhysicalStockEntry p set p.vanSerialNo=p.phyEntryID where p.vanSerialNo is null and p.phyEntryID>0")
    Integer updatePhysicalStockEntryVanSerialNo();


}
