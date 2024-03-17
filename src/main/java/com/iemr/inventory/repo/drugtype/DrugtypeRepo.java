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
package com.iemr.inventory.repo.drugtype;

import com.iemr.inventory.data.drugtype.M_Drugtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Repository
public interface DrugtypeRepo extends CrudRepository<M_Drugtype, Integer> {

    @Autowired(required = true)

    @Query("SELECT u FROM M_Drugtype u WHERE u.providerServiceMapID=:providerServiceMapID")
    ArrayList<M_Drugtype> getDrugtypeData(@Param("providerServiceMapID") Integer providerServiceMapID);


    @Query("SELECT u FROM M_Drugtype u WHERE u.drugTypeID=:drugTypeID")
    M_Drugtype geteditedData(@Param("drugTypeID") Integer drugTypeID);

}
