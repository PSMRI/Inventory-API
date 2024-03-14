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
package com.iemr.inventory.repository.item;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.inventory.data.items.ItemMaster;

import jakarta.transaction.Transactional;

@Repository
public interface ItemRepo extends CrudRepository<ItemMaster, Integer> {

    List<ItemMaster> findByProviderServiceMapID(Integer providerServiceMapID);

    List<ItemMaster> findByDeletedAndProviderServiceMapID(Boolean deleted, Integer providerServiceMapID);

    @Transactional
    @Modifying
    @Query("UPDATE ItemMaster c SET c.deleted = :flag WHERE c.itemID = :itemid")
    Integer deleteItemMaster(@Param("itemid") Integer id, @Param("flag") Boolean flag);

    @Transactional
    @Modifying
    @Query("UPDATE ItemMaster c SET c.discontinued = :flag WHERE c.itemID = :itemid")
    Integer discontinueItemMaster(@Param("itemid") Integer id, @Param("flag") Boolean flag);


    @Query(value = "SELECT u FROM ItemMaster u WHERE u.providerServiceMapID=:providerServiceMapID AND u.itemCategoryID=:itemCategoryID AND deleted=0", nativeQuery = true)
    List<ItemMaster> getItemMasters(@Param("providerServiceMapID") Integer providerServiceMapID, @Param("itemCategoryID") Integer itemCategoryID);

    @Query("select u from ItemMaster u  where u.itemID=:itemid")
    ItemMaster findDetailOne(@Param("itemid") Integer itemid);

    List<ItemMaster> findByItemNameLikeOrItemCodeLike(String itemName, String itemCode);

    List<ItemMaster> findByItemNameContainingAndItemIDIn(String itemname, Integer[] itemids);

    List<ItemMaster> findByItemIDIn(Integer[] itemids);


}
