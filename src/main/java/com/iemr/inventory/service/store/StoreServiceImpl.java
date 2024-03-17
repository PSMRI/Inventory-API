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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.store.M_Van;
import com.iemr.inventory.repository.store.MainStoreRepo;
import com.iemr.inventory.repository.store.VanMasterRepository;
import com.iemr.inventory.utils.exception.IEMRException;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired(required = false)
    private MainStoreRepo mainStoreRepo;

    @Autowired(required = false)
    private VanMasterRepository vanMasterRepository;

    @Override
    public M_Facility createMainStore(M_Facility mainStoreFacility) {

        return mainStoreRepo.save(mainStoreFacility);
    }

    @Override
    public M_Facility getMainStore(Integer mainStoreID) {
        return mainStoreRepo.findById(mainStoreID).get();
    }

    @Override
    public List<M_Facility> getAllMainStore(Integer providerServiceMapID) {
        return (List<M_Facility>) mainStoreRepo.findByProviderServiceMapID(providerServiceMapID);
    }

    @Override

    public List<M_Facility> addAllMainStore(List<M_Facility> maniStore) {
        return (List<M_Facility>) mainStoreRepo.saveAll(maniStore);
    }

    @Override
    public ArrayList<M_Facility> getMainFacility(Integer providerServiceMapID, Boolean isMainFacility) {
        ArrayList<M_Facility> data = mainStoreRepo.getAllMainFacility(providerServiceMapID, isMainFacility);
        return data;
    }

    @Override
    public ArrayList<M_Facility> getMainFacility(Integer providerServiceMapID, Boolean isMainFacility,
                                                 Integer mainFacilityID) {
        ArrayList<M_Facility> data = mainStoreRepo.getAllMainFacility(providerServiceMapID, isMainFacility,
                mainFacilityID);
        return data;
    }

    @Override
    public ArrayList<M_Facility> getChildFacility(Integer providerServiceMapID, Integer mainFacilityID) {
        ArrayList<M_Facility> data = mainStoreRepo.getChildFacility(providerServiceMapID, mainFacilityID);
        return data;
    }

    @Override
    public M_Facility deleteStore(M_Facility facility) throws IEMRException {

        //M_Facility stores = mainStoreRepo.findById(facility.getFacilityID());
        M_Facility stores = mainStoreRepo.findById(facility.getFacilityID()).get();
        if (stores != null && facility.getDeleted() != null) {
            if (facility.getDeleted()) {
                List<M_Facility> childStore = mainStoreRepo.findByMainFacilityIDAndDeleted(facility.getFacilityID(),
                        false);
                if (childStore.size() == 0) {
                    stores.setDeleted(true);
                    stores = mainStoreRepo.save(stores);
                } else {
                    throw new IEMRException("Child Stores are still active");
                }
            } else {
                if (stores.getMainFacilityID() != null) {
                    M_Facility parentStore = mainStoreRepo.findByFacilityIDAndDeleted(stores.getMainFacilityID(),
                            false);
                    if (parentStore != null) {
                        stores.setDeleted(false);
                        stores = mainStoreRepo.save(stores);
                    } else {
                        throw new IEMRException("Parent Stores are still inactive");
                    }
                } else {
                    stores.setDeleted(false);
                    stores = mainStoreRepo.save(stores);
                }

            }
        } else {
            throw new IEMRException("No store available");
        }
        return stores;
    }

    @Override
    public List<M_Facility> getAllActiveStore(M_Facility facility) {
        return mainStoreRepo.findByProviderServiceMapIDAndDeleted(facility.getProviderServiceMapID(),
                facility.getDeleted());
    }

    @Override
    public M_Facility getStoreByID(Integer facility) {
        return mainStoreRepo.findByFacilityIDAndDeleted(facility, false);
    }

    @Override
    public M_Van getVanByStoreID(Integer storeID) {
        return vanMasterRepository.findOneByFacilityIDAndDeleted(storeID, false);
    }

}
