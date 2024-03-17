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
package com.iemr.inventory.service.manufacturer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.repo.manufacturer.ManufacturerRepo;

@Service
public class ManufacturerServiceImpl implements ManufacturerInter {

    @Autowired(required = false)
    private ManufacturerRepo manufacturerRepo;

    @Override
    public ArrayList<M_Manufacturer> createManufacturer(List<M_Manufacturer> manufacturerData) {
        ArrayList<M_Manufacturer> createData = (ArrayList<M_Manufacturer>) manufacturerRepo.saveAll(manufacturerData);
        if (createData.size() > 0)
            return createData;
        else
            return null;
    }

    @Override
    public ArrayList<M_Manufacturer> createManufacturer(Integer providerServiceMapID) {
        ArrayList<M_Manufacturer> getData = manufacturerRepo.getManufacturerData(providerServiceMapID);

        if (getData.size() > 0)
            return getData;
        else
            return null;
    }

    @Override
    public M_Manufacturer editManufacturer(Integer manufacturerID) {

        M_Manufacturer edit = manufacturerRepo.getEditData(manufacturerID);
        return edit;
    }

    @Override
    public M_Manufacturer saveEditedData(M_Manufacturer editData) {
        M_Manufacturer data = manufacturerRepo.save(editData);

        return data;
    }

}
