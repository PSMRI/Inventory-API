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

import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public interface ItemfacilitymappingIndentRepo extends CrudRepository<ItemfacilitymappingIndent, Integer> {


    @Query(nativeQuery = true, value = "select iti.ItemID,iti.ItemCode, iti.ItemName,iti.IsMedical,iti.Strength,"
            + "uom.UOMName,itcat.ItemCategoryName,itemform.ItemFormName,pharm.PharmCategoryName "
            + ",iti.Composition,u.FacilityID,sum(ise.quantityInHand) as qoh "
            + "from m_itemfacilitymapping u "
            + "left join t_itemstockentry ise on ise.itemid=u.itemid and ise.FacilityID=:facID and ise.ExpiryDate>now()"
            + "inner join m_item iti  on iti.itemid=u.itemid "
            + "left join m_itemcategory itcat on itcat.ItemCategoryID=iti.ItemCategoryID "
            + "left join m_itemform itemform on itemform.ItemFormID=iti.ItemFormID "
            + "left join m_pharmacologicalcategory pharm on pharm.PharmCategoryID=iti.PharmacologyCategoryID "
            + "left join m_uom uom on uom.UOMID=iti.UOMID "
            + "where u.facilityID=:facID and (iti.itemName like %:itemNamep% or iti.itemCode like %:itemNamep%) "
            + "  and iti.Deleted=false and iti.Discontinued=false "
            + " and u.deleted=false "
            + " group by u.itemID,u.facilityID order by iti.ItemName")
    List<Object[]> findindentitem(@Param("facID") Integer facilityID, @Param("itemNamep") String itemName);


}
