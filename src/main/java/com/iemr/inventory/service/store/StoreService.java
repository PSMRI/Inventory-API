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
package com.iemr.inventory.service.store;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.store.M_Van;
import com.iemr.inventory.utils.exception.IEMRException;


@Service
public interface StoreService {

    M_Facility createMainStore(M_Facility mainStoreFacility);

    M_Facility getMainStore(Integer mainStoreID);

    List<M_Facility> getAllMainStore(Integer providerServiceMapID);

    List<M_Facility> addAllMainStore(List<M_Facility> maniStore);

    ArrayList<M_Facility> getMainFacility(Integer providerServiceMapID, Boolean isMainFacility);

    ArrayList<M_Facility> getMainFacility(Integer providerServiceMapID, Boolean isMainFacility, Integer mainFacilityID);

    ArrayList<M_Facility> getChildFacility(Integer providerServiceMapID, Integer mainFacilityID);

    M_Facility deleteStore(M_Facility facility) throws IEMRException;

    List<M_Facility> getAllActiveStore(M_Facility facility);

    M_Facility getStoreByID(Integer facility);

    M_Van getVanByStoreID(Integer storeID);


}
