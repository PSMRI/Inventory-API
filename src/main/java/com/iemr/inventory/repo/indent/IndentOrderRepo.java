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
package com.iemr.inventory.repo.indent;

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndentOrderRepo extends CrudRepository<IndentOrder, Long> {

    @Query("Select indent from Indent indent "
            + "where indent.fromFacilityID = :facilityID order by indent.createdDate desc")
    List<Indent> getIndentHistory(@Param("facilityID") Integer facilityID);

    @Query("Select indentOrder from IndentOrder indentOrder "
            + "where indentOrder.indentID = :indentID and indentOrder.syncFacilityID =:fromFacilityID and indentOrder.deleted =false order by indentOrder.createdDate desc")
    List<IndentOrder> getOrdersByIndentID(@Param("indentID") Long indentID, @Param("fromFacilityID") Object object);

    @Transactional
    @Modifying
    @Query("UPDATE Indent set status = :action , processed = 'U', statusReason=:reason where vanSerialNo = :vanSerialNo and syncFacilityID = :fromFacilityID")
    public int issueIndent(@Param("action") String action, @Param("vanSerialNo") Long vanSerialNo, @Param("fromFacilityID") Object object, @Param("reason") String reason);

    @Transactional
    @Modifying
    @Query("UPDATE IndentOrder set status = :action , processed = 'U' where indentID = :indentID and syncFacilityID = :fromFacilityID and deleted =false")
    public int issueIndentOrder(@Param("action") String action, @Param("indentID") Long indentID, @Param("fromFacilityID") Object object);

    @Transactional
    @Modifying
    @Query("UPDATE Indent set status = 'Cancelled' , deleted = true , processed = 'U' where indentID = :indentID")
    public int cancelIndent(@Param("indentID") Long indentID);

    @Transactional
    @Modifying
    @Query("UPDATE IndentOrder set status = 'Cancelled' , deleted = true , processed = 'U' where indentID = :indentID and syncFacilityID =:fromFacilityID and deleted =false")
    public int cancelIndentOrder(@Param("indentID") Long indentID, @Param("fromFacilityID") Object object);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ItemStockEntry itemStockEntry set itemStockEntry.quantityInHand = itemStockEntry.quantityInHand - :issuedQuantity "
            + "where itemStockEntry.itemStockEntryID =:itemStockEntryID and itemStockEntry.facilityID =:facilityID")
    int updateQuantityInStock(@Param("issuedQuantity") Integer issuedQuantity, @Param("itemStockEntryID") Long itemStockEntryID, @Param("facilityID") Integer facilityID);

    @Transactional
    @Modifying
    @Query("UPDATE Indent set status = 'Closed' where indentID = :indentID and fromFacilityID = :fromFacilityID")
    public int acceptIndent(@Param("indentID") Long indentID, @Param("fromFacilityID") Integer fromFacilityID);

    @Transactional
    @Modifying
    @Query("UPDATE IndentOrder set status = 'Closed' where indentOrderID = :indentOrderID and syncFacilityID = :fromFacilityID and deleted =false")
    public int acceptIndentOrder(@Param("indentOrderID") Long indentOrderID, @Param("fromFacilityID") Object object);

    @Query("Select indentIssue from IndentIssue indentIssue "
            + "where indentIssue.indentID = :indentID and indentIssue.syncFacilityID = :fromFacilityID")
    List<IndentIssue> getIndentIssued(@Param("indentID") Long indentID, @Param("fromFacilityID") Integer fromFacilityID);

    @Transactional
    @Modifying
    @Query("update IndentOrder p set p.vanSerialNo=p.indentOrderID where p.vanSerialNo is null and p.indentOrderID>0")
    public void updateVanSerialNo();


}
