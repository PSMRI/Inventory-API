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
package com.iemr.inventory.repository.itemfacilitymapping;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.inventory.data.itemfacilitymapping.M_itemfacilitymapping;

@Repository
public interface M_itemfacilitymappingRepo extends CrudRepository<M_itemfacilitymapping, Integer> {

    @Query(value = "SELECT distinct mi.itemID,"
            + " imfm.itemName as itemName, "
            + " imfm.discontinued as discontinued,"
            + " imfm.itemCategoryID as itemCategoryID "
            + " FROM M_itemfacilitymapping mi "
            + " JOIN mi.itemMasterforFacilityMapping imfm  "
            + " WHERE mi.providerServiceMapID =:providerServiceMapID AND mi.facilityID=:facilityID AND mi.deleted=0 AND imfm.deleted=0 ORDER BY imfm.itemName ", nativeQuery = true)
    ArrayList<Object[]> getItemforSubstore(@Param("providerServiceMapID") Integer providerServiceMapID, @Param("facilityID") Integer facilityID);

    @Query(value = "SELECT distinct mi.itemID,"
            + " imfm.itemName as itemName "
            + " FROM M_itemfacilitymapping mi "
            + " JOIN mi.itemMasterforFacilityMapping imfm  "
            + " WHERE mi.facilityID=:facilityID AND mi.deleted=0 AND imfm.deleted=0 ORDER BY imfm.itemName ", nativeQuery = true)
    ArrayList<Object[]> getItemforStore(@Param("facilityID") Integer facilityID);


    @Query(value = "SELECT distinct mi.itemID,"
            + " imfm.itemName as itemName "
            + " FROM M_itemfacilitymapping mi "
            + " JOIN mi.itemMasterforFacilityMapping imfm  "
            + " WHERE mi.facilityID=:facilityID AND mi.deleted=0 AND imfm.deleted=0 AND (imfm.itemName Like %:itemName% OR imfm.itemCode Like %:itemName%) ORDER BY imfm.itemName ", nativeQuery = true)
    ArrayList<Object[]> getItemforStoreLikeItemName(@Param("facilityID") Integer facilityID, @Param("itemName") String itemName);


    @Query(value = "SELECT distinct mi.itemID,"
            + " imfm.itemName as itemName "
            + " FROM M_itemfacilitymapping mi "
            + " JOIN mi.itemMasterforFacilityMapping imfm  "
            + " WHERE mi.facilityID=:facilityID AND mi.itemID in (:itemIDs) AND mi.deleted=0 AND imfm.deleted=0 ORDER BY imfm.itemName ", nativeQuery = true)
    ArrayList<Object[]> getItemforStoreAndItemID(@Param("facilityID") Integer facilityID, @Param("itemIDs") Integer[] itemIDs);

    @Query(value = "SELECT distinct mi.itemID,"
            + " imfm.itemName as itemName "
            + " FROM M_itemfacilitymapping mi "
            + " JOIN mi.itemMasterforFacilityMapping imfm  "
            + " WHERE mi.facilityID=:facilityID AND mi.deleted=0 AND imfm.discontinued=0 AND imfm.deleted=0 AND (imfm.itemName Like %:itemName% OR imfm.itemCode Like %:itemName%)  ORDER BY imfm.itemName ", nativeQuery = true)
    ArrayList<Object[]> getItemforStorePartialSearch(@Param("facilityID") Integer facilityID, @Param("itemName") String itemName);


}
