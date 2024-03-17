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

import com.iemr.inventory.data.stockExit.T_StockTransfer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StockTransferRepo extends CrudRepository<T_StockTransfer, Long> {


    List<T_StockTransfer> findByCreatedDateBetweenAndTransferFromFacilityIDOrCreatedDateBetweenAndTransferToFacilityIDOrderByCreatedDateDesc(Timestamp fromDate, Timestamp toDate, Integer fromfacilityID, Timestamp fromDate1, Timestamp toDate1, Integer tofacilityID);

    @Query(nativeQuery = true, value = "select van.vanID from m_facility fac left join m_van  van on fac.facilityID=van.facilityID"
            + " where fac.IsMainFacility is false and van.deleted is false and fac.FacilityID = :facID")
    Long findVanIDByFacID(@Param("facID") Integer transferToFacilityID);

    @Transactional
    @Modifying
    @Query("update T_StockTransfer p set p.vanSerialNo=p.stockTransferID where p.vanSerialNo is null and p.stockTransferID>0")
    Integer updateVanSerialNo();

    T_StockTransfer findByStockTransferID(Long stockTransferID);

}
