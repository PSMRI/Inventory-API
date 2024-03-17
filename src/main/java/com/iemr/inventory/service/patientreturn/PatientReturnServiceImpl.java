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
package com.iemr.inventory.service.patientreturn;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.patientreturn.ItemDetailModel;
import com.iemr.inventory.data.patientreturn.PatientReturnModel;
import com.iemr.inventory.data.patientreturn.ReturnHistoryModel;
import com.iemr.inventory.data.stockExit.ItemReturnEntry;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.repository.patientreturn.ItemReturnEntryRepo;
import com.iemr.inventory.repository.patientreturn.PatientReturnRepo;

@Service
public class PatientReturnServiceImpl implements PatientReturnService {

    private static final Logger logger = LoggerFactory.getLogger(PatientReturnServiceImpl.class);

    @Autowired(required = false)
    PatientReturnRepo patientReturnRepo;

    @Autowired(required = false)
    ItemReturnEntryRepo itemReturnEntryRepo;

    @Override
    public List<PatientReturnModel> getItemNameByRegID(T_PatientIssue patientIssue) {
        logger.info("getItemNameByRegID - Start");
        List<Object[]> results = null;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, -90);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Timestamp criteriaDate = new Timestamp(cal.getTimeInMillis());
        results = patientReturnRepo.getItemNameByRegID(patientIssue.getBenRegID(), patientIssue.getFacilityID(), criteriaDate);

        List<PatientReturnModel> list = new ArrayList<PatientReturnModel>();
        for (Object[] objects : results) {
            if (objects != null && objects.length > 0) {

                list.add(
                        new PatientReturnModel(
                                ((Number) objects[0]).longValue(), (Integer) objects[1], (Integer) objects[2], (String) objects[3]));
            }
        }
        logger.info("getItemNameByRegID - End");
        return list;
    }

    @Override
    public List<ItemDetailModel> getItemDetailByBen(ItemDetailModel itemDetailModel) {
        logger.info("getItemDetailByBen - Start");
        List<Object[]> results = null;
        results = patientReturnRepo.getItemDetailByBen(itemDetailModel.getBenRegID(), itemDetailModel.getItemID(), itemDetailModel.getFacilityID());

        List<ItemDetailModel> list = new ArrayList<ItemDetailModel>();
        for (Object[] objects : results) {
            if (objects != null && objects.length > 0) {

                list.add(
                        new ItemDetailModel(
                                (Integer) objects[0], (String) objects[1], (String) objects[2], (Integer) objects[3], (Timestamp) objects[4]
                                , (Boolean) objects[5], (Boolean) objects[6], ((Number) objects[7]).longValue(), ((Number) objects[8]).longValue(),
                                ((Number) objects[9]).longValue(), ((Number) objects[10]).longValue(), ((Number) objects[11]).longValue(), (Integer) objects[12], (Integer) objects[13]));
            }
        }
        logger.info("getItemDetailByBen - End");
        return list;
    }

    @Override
    public String updateQuantityReturned(ItemDetailModel[] itemDetailModel) {
        logger.info("updateQuantityReturned - Start");
        List<ItemDetailModel> list = Arrays.asList(itemDetailModel);
        List<ItemReturnEntry> returnList = null;
        returnList = new ArrayList<ItemReturnEntry>();
        for (ItemDetailModel itemDetail : list) {
            int result = patientReturnRepo.updateQuantityReturned(itemDetail.getReturnQuantity(), itemDetail.getItemStockEntryID());
            patientReturnRepo.updateIssuedQuantity(itemDetail.getReturnQuantity(), itemDetail.getItemStockExitID());
            ItemReturnEntry itemReturnEntry = new ItemReturnEntry();
            itemReturnEntry.setCreatedBy(itemDetail.getCreatedBy());
            itemReturnEntry.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            itemReturnEntry.setBenRegID(itemDetail.getBenRegID());
            itemReturnEntry.setItemStockExitID(itemDetail.getItemStockExitID());
            itemReturnEntry.setFacilityID(itemDetail.getFacilityID());
            itemReturnEntry.setProviderServiceMapID(itemDetail.getProviderServiceMapID());
            itemReturnEntry.setVisitID(itemDetail.getVisitID());
            itemReturnEntry.setVisitCode(itemDetail.getVisitCode());

            returnList.add(itemReturnEntry);
        }
        itemReturnEntryRepo.saveAll(returnList);
        logger.info("updateQuantityReturned - End");
        return "Quantity updated successfully";
    }

    @Override
    public List<ReturnHistoryModel> getBenReturnHistory(ItemReturnEntry itemReturnEntry) {
        logger.info("getBenReturnHistory - Start");

        List<ReturnHistoryModel> returnList = new ArrayList<ReturnHistoryModel>();
        List<Object[]> results = null;
        results = patientReturnRepo.getBenReturnHistory(itemReturnEntry.getFacilityID(), itemReturnEntry.getFromDate(), itemReturnEntry.getToDate());

        for (Object[] objects : results) {
            if (objects != null && objects.length > 0) {
                Long visitCode = objects[5] != null ? ((Number) objects[5]).longValue() : null;
                Long visitID = objects[4] != null ? ((Number) objects[4]).longValue() : null;
                returnList.add(
                        new ReturnHistoryModel(
                                (String) objects[0], (String) objects[1], (Integer) objects[2], (Timestamp) objects[3], visitID
                                , visitCode, (String) objects[6], (Integer) objects[7], (String) objects[8], (Timestamp) objects[9]));
            }
        }

        logger.info("getBenReturnHistory - End");
        return returnList;
    }
}
