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
package com.iemr.inventory.repository.patientreturn;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.stockExit.T_PatientIssue;

import jakarta.transaction.Transactional;


@Service
@Repository
public interface PatientReturnRepo extends CrudRepository<T_PatientIssue, Long> {

    @Query(value = "SELECT patientIssue.BeneficiaryRegID, patientIssue.FacilityID, item.ItemID, item.ItemName "
            + "FROM t_patientissue patientIssue "
            + "join t_itemstockexit itemstockexit on patientIssue.PatientIssueID = itemstockexit.ExitTypeID "
            + "join t_itemstockentry itemstockentry on itemstockexit.ItemStockEntryID = itemstockentry.ItemStockEntryID "
            + "join m_item item on itemstockentry.ItemID = item.ItemID "
            + "join m_itemfacilitymapping facmapping on facmapping.ItemID=item.ItemID AND facmapping.FacilityID=patientIssue.FacilityID AND facmapping.deleted=0 "
            + "WHERE patientIssue.BeneficiaryRegID=:benRegID AND patientIssue.FacilityID=:facilityID AND patientIssue.deleted=0 "
            + "AND patientIssue.createdDate>= :createdDate group by item.ItemID", nativeQuery = true)
    List<Object[]> getItemNameByRegID(@Param("benRegID") Long benRegID, @Param("facilityID") Integer facilityID, @Param("createdDate") Timestamp createdDate);


    @Query(value = "SELECT item.ItemID, item.ItemName, itemstockentry.BatchNo, itemstockexit.Quantity, itemstockexit.CreatedDate, item.Discontinued, item.Deleted, "
            + "itemstockexit.ItemStockExitID, itemstockentry.ItemStockEntryID, patientIssue.BenVisitID, patientIssue.VisitCode, patientIssue.BeneficiaryRegID, "
            + "patientIssue.ProviderServiceMapID, patientIssue.FacilityID "
            + "FROM t_patientissue patientIssue "
            + "join t_itemstockexit itemstockexit on patientIssue.PatientIssueID = itemstockexit.ExitTypeID AND itemStockExit.ExitType='T_PatientIssue' "
            + "join t_itemstockentry itemstockentry on itemstockexit.ItemStockEntryID = itemstockentry.ItemStockEntryID "
            + "join m_item item on itemstockentry.ItemID = item.ItemID "
            + "join m_itemfacilitymapping facmapping on facmapping.ItemID=item.ItemID AND facmapping.FacilityID=patientIssue.FacilityID AND facmapping.deleted=0 "
            + "WHERE patientIssue.BeneficiaryRegID=:benRegID AND item.ItemID=:itemID AND patientIssue.FacilityID=:facilityID "
            + "AND patientIssue.deleted=0 order by itemstockexit.CreatedDate", nativeQuery = true)
    List<Object[]> getItemDetailByBen(@Param("benRegID") Long benRegID, @Param("itemID") Integer itemID, @Param("facilityID") Integer facilityID);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ItemStockEntry itemStockEntry set itemStockEntry.quantityInHand = itemStockEntry.quantityInHand + :returnQuantity where itemStockEntry.itemStockEntryID =:itemStockEntryID")
    int updateQuantityReturned(@Param("returnQuantity") Integer returnQuantity, @Param("itemStockEntryID") Long itemStockEntryID);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ItemStockExit itemStockExit set itemStockExit.quantity = itemStockExit.quantity - :returnQuantity where itemStockExit.itemStockExitID =:itemStockExitID")
    int updateIssuedQuantity(@Param("returnQuantity") Integer returnQuantity, @Param("itemStockExitID") Long itemStockExitID);

    @Query(value = "SELECT item.ItemName, itemstockentry.BatchNo, itemstockexit.Quantity, itemstockexit.CreatedDate as issueDate, "
            + "patientIssue.BenVisitID, patientIssue.VisitCode, patientIssue.PatientName, patientIssue.Age, patientIssue.Gender, patientreturn.CreatedDate as returnDate "
            + "FROM t_patientreturn patientreturn "
            + "inner join t_itemstockexit itemstockexit on patientreturn.ItemExitID = itemstockexit.ItemStockExitID "
            + "inner join t_patientissue patientIssue on patientIssue.PatientIssueID=itemstockexit.ExitTypeID "
            + "inner join t_itemstockentry itemstockentry on itemstockexit.ItemStockEntryID = itemstockentry.ItemStockEntryID "
            + "inner join m_item item on itemstockentry.ItemID = item.ItemID "
            + "WHERE patientreturn.FacilityID=:facilityID AND patientreturn.createdDate >=:fromDate AND patientreturn.createdDate <=:toDate "
            + "AND patientIssue.deleted=0 order by patientreturn.CreatedDate desc", nativeQuery = true)
    List<Object[]> getBenReturnHistory(@Param("facilityID") Integer facilityID, @Param("fromDate") Timestamp fromDate, @Param("toDate") Timestamp toDate);

}
