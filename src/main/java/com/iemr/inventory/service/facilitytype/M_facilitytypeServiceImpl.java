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
package com.iemr.inventory.service.facilitytype;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.facilitytype.M_facilitytype;
import com.iemr.inventory.repository.facilitytype.M_facilitytypeRepo;

@Service
public class M_facilitytypeServiceImpl implements M_facilitytypeInter {

    @Autowired(required = false)
    private M_facilitytypeRepo m_facilitytypeRepo;

    @Override
    public ArrayList<M_facilitytype> getAllFicilityData(Integer providerServiceMapID) {
        ArrayList<M_facilitytype> data = m_facilitytypeRepo.getAllFicilityData(providerServiceMapID);
        return data;
    }

    @Override
    public ArrayList<M_facilitytype> addAllFicilityData(List<M_facilitytype> addfacilityDetails) {
        ArrayList<M_facilitytype> data = (ArrayList<M_facilitytype>) m_facilitytypeRepo.saveAll(addfacilityDetails);
        return data;
    }

    @Override
    public M_facilitytype editAllFicilityData(Integer facilityTypeID) {
        M_facilitytype data = m_facilitytypeRepo.findByFacilityTypeID(facilityTypeID);
        return data;
    }

    @Override
    public M_facilitytype updateFacilityData(M_facilitytype allFacilityData) {
        M_facilitytype data = m_facilitytypeRepo.save(allFacilityData);
        return data;
    }

}
