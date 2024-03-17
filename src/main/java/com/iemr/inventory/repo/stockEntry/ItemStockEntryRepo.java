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

import com.iemr.inventory.data.stockentry.ItemStockEntry;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public interface ItemStockEntryRepo extends CrudRepository<ItemStockEntry, Long> {

    @Query("SELECT ise.facilityID,ise.itemID,itm.itemName,sum(ise.quantityInHand) "
            + "from ItemStockEntry ise join ise.item itm where ise.itemID in (:itemID) and ise.facilityID=:storeID "
            + "group by ise.facilityID,ise.itemID")
    ArrayList<Object[]> getQuantity(@Param("itemID") Integer[] itemID, @Param("storeID") Integer storeID);

    @Query("SELECT ise.facilityID,ise.itemStockEntryID,itm.itemName,ise.quantityInHand,ise.vanSerialNo "
            + "from ItemStockEntry ise join ise.item itm where ise.itemStockEntryID in (:itemStockID) and ise.facilityID=:storeID ")
    ArrayList<Object[]> getQuantityOfStock(@Param("itemStockID") Long[] itemStockID, @Param("storeID") Integer storeID);

    List<ItemStockEntry> findByFacilityIDAndItemIDAndQuantityInHandGreaterThanAndDeleted(Integer facilityID,
                                                                                         Integer itemID, Integer quantityInHand, Boolean deleted);

    @Transactional
    @Modifying
    @Query("UPDATE ItemStockEntry c SET c.quantityInHand = c.quantityInHand - :dispQuant "
            + " WHERE c.vanSerialNo = :itemStockEntryId and c.facilityID = :facilityID")
    Integer updateStock(@Param("facilityID") Integer facilityID, @Param("itemStockEntryId") Long itemStockEntryId,
                        @Param("dispQuant") Integer dispQuant);

    List<ItemStockEntry> findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
            Integer facilityID, Integer itemID, Boolean deleted, Integer qin, Date now);

    List<ItemStockEntry> findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByDesc(
            Integer facilityID, Integer itemID, Boolean deleted, Integer qin, Date now);

    List<ItemStockEntry> findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
            Integer facilityID, Integer itemID, Boolean deleted, Integer qin, Date now);

    List<ItemStockEntry> findByFacilityIDAndItemStockEntryIDIn(Integer facilityID, Long[] itemstockIDs);

    List<ItemStockEntry> findByItemIDInAndQuantityInHandGreaterThanAndFacilityID(Integer[] itemIDs, Integer quantity,
                                                                                 Integer facilityID);

    List<ItemStockEntry> findByFacilityIDAndItemIDAndQuantityInHandGreaterThanAndDeletedAndExpiryDateAfter(
            Integer facilityID, Integer itemID, Integer quantityInHand, Boolean deleted, Date date);

    List<ItemStockEntry> findByFacilityIDAndItemIDInAndQuantityInHandGreaterThanAndExpiryDateAfter(Integer facFrom,
                                                                                                   Integer[] itemID, Integer quant, Date expirydate);

    @Query("SELECT ise,sum(ise.quantityInHand) as quantity "
            + "from ItemStockEntry ise join ise.item itm where ise.itemID in (:itemID) and ise.facilityID=:storeID and "
            + "(select sum(ise.quantityInHand) as quantity from ItemStockEntry iise where iise.itemID=ise.itemID "
            + "and iise.facilityID=ise.facilityID group by iise.facilityID,iise.itemID) >:quant and ise.expiryDate > :nowdate "
            + "group by ise.facilityID,ise.itemID")
    List<Object[]> findByItemIDInQuantityInHandGreaterThanForFacilityID(@Param("itemID") Integer[] itemID,
                                                                        @Param("quant") Long size, @Param("storeID") Integer facilityID, @Param("nowdate") Date datenow);

    List<ItemStockEntry> findByItemIDInAndFacilityIDOrderByItemStockEntryIDDesc(Integer[] itemID, Integer facilityID);

    @Transactional
    @Modifying
    @Query("UPDATE ItemStockEntry c SET c.quantityInHand = c.quantityInHand + :quant WHERE c.itemStockEntryID = :id")
    Integer addStock(@Param("id") Long id, @Param("quant") Integer quant);

    @Transactional
    @Modifying
    @Query("UPDATE ItemStockEntry c SET c.quantityInHand = c.quantityInHand - :quant WHERE c.itemStockEntryID = :id")
    Integer subtractStock(@Param("id") Long id, @Param("quant") Integer quant);

    List<ItemStockEntry> findByItemStockEntryIDIn(List<Long> comapreid);

    @Query(nativeQuery = true, value = "select van.vanID from m_facility fac left join m_van  van on fac.facilityID=van.facilityID"
            + " where fac.IsMainFacility is false and van.deleted is false and fac.FacilityID = :facID")
    Long findVanIDByFacID(@Param("facID") Integer transferToFacilityID);

    List<ItemStockEntry> findByEntryTypeIDAndSyncFacilityIDAndEntryType(Long vanSerialNo, Integer syncFacilityID,
                                                                        String string);

    @Transactional
    @Modifying
    @Query("update ItemStockEntry p set p.vanSerialNo=p.itemStockEntryID where p.vanSerialNo is null and p.itemStockEntryID>0")
    Integer updateItemStockEntryVanSerialNo();


}
