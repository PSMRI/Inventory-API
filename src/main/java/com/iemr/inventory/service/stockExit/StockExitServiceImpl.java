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
package com.iemr.inventory.service.stockExit;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockExit.StoreSelfConsumption;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.data.stockExit.T_StockTransfer;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.mapper.stockExit.ItemStockExitMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockExit.ItemStockExitRepo;
import com.iemr.inventory.repo.stockExit.PatientIssueRepo;
import com.iemr.inventory.repo.stockExit.StockTransferRepo;
import com.iemr.inventory.repo.stockExit.StoreSelfConsumptionRepo;
import com.iemr.inventory.service.item.ItemService;
import com.iemr.inventory.service.stockEntry.StockEntryService;
import com.iemr.inventory.utils.exception.InventoryException;

@Service
public class StockExitServiceImpl implements StockExitService {

    @Autowired(required = false)
    StockEntryService stockEntryService;

    @Autowired(required = false)
    ItemStockExitRepo itemStockExitRepo;

    @Autowired(required = false)
    ItemStockEntryRepo itemStockEntryRepo;

    @Autowired(required = false)
    PatientIssueRepo patientIssueRepo;

    @Autowired(required = false)
    StoreSelfConsumptionRepo storeSelfConsumptionRepo;
    @Autowired(required = false)
    StockTransferRepo stockTransferRepo;

    @Autowired(required = false)
    ItemService itemService;

    @Autowired(required = false)
    ItemStockExitMapper itemStockExitMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = SQLException.class)
    @Override
    public Integer issuePatientDrugs(T_PatientIssue patientIssue) throws InventoryException {
        Integer returnvalue = 0;
        logger.info("starting patient issue" + patientIssue.toString());
        if (patientIssue.getItemStockExit() == null || patientIssue.getItemStockExit().size() == 0) {
            if (patientIssue.getPrescriptionID() != null) {
                if (patientIssue != null && patientIssue.getBenRegID() != null && patientIssue.getVisitCode() != null) {
                    int i = updateBenFlowAfterPharmaTransaction(patientIssue);
                    if (i > 0)
                        returnvalue = 1;
                    else
                        returnvalue = 0;
                    return returnvalue;
                } else {
                    returnvalue = 0;
                    return returnvalue;
                }
            } else {
                throw new InventoryException("No item found to dispense.");
            }
        }
        if (true) {

            List<ItemStockExit> itemissueList = patientIssue.getItemStockExit();
            logger.info("fetching itemissue and validating");
            List<ItemStockExit> itemissueListUpdated = getItemStockAndValidate(itemissueList,
                    patientIssue.getFacilityID(), patientIssue.getCreatedBy(), patientIssue.getVanID(),
                    patientIssue.getParkingPlaceID());
            logger.info(itemissueList.size() + " saving ItemStockExit lhs rhs should be same "
                    + itemissueListUpdated.size());
            logger.info("itemissueList " + itemissueList.toString());
            logger.info("itemissueListUpdated " + itemissueListUpdated.toString());
            if (itemissueList.size() == itemissueListUpdated.size()) {
                patientIssue.setSyncFacilityID(patientIssue.getFacilityID());
                patientIssueRepo.save(patientIssue);
                patientIssueRepo.updateVanSerialNo();

                returnvalue = saveItemExit(itemissueListUpdated, patientIssue.getPatientIssueID(), "T_PatientIssue");
                if (returnvalue == 1) {
                    if (patientIssue != null && patientIssue.getBenRegID() != null
                            && patientIssue.getVisitCode() != null) {
                        int i = updateBenFlowAfterPharmaTransaction(patientIssue);
                        if (i > 0)
                            returnvalue = 1;
                        else
                            returnvalue = 0;
                    } else {
                        returnvalue = 0;
                    }
                }
            }

        }

        return returnvalue;
    }

    private int updateBenFlowAfterPharmaTransaction(T_PatientIssue patientIssue) throws InventoryException {
        int i = 0;
        i = patientIssueRepo.updateBenStatusFlowAfterPharma(patientIssue.getBenRegID(), patientIssue.getVisitCode());
        return i;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Integer saveItemExit(List<ItemStockExit> itemissueListUpdated, Long issueID, String issueType) {
        logger.info("saving ItemStockExit");
        Integer i = 0;
        for (ItemStockExit action : itemissueListUpdated) {
            action.setExitType(issueType);
            action.setExitTypeID(issueID);
        }
        itemStockExitRepo.saveAll(itemissueListUpdated);
        itemStockExitRepo.updateVanSerialNo();
        stockEntryService.updateStocks(itemissueListUpdated);
        i = 1;
        logger.info("saved ItemStockExit");
        return i;
    }

    public List<ItemStockExit> getItemStockAndValidate(List<ItemStockExit> itemissueList, Integer facilityID,
                                                       String createdBy, Long vanID, Long ppID) {
        logger.info("in getItemStockAndValidate method");
        Map<Long, ItemStockExit> result = itemissueList.stream()
                .collect(Collectors.toMap(ItemStockExit::getItemStockEntryID, Function.identity()));

        Long[] itemstockIDs = result.keySet().toArray(new Long[itemissueList.size()]);

        List<Object[]> stockInHand = stockEntryService.getAllItemBatchForStoreID(facilityID, itemstockIDs);

        List<ItemStockExit> itemissueListUpdated = new ArrayList<ItemStockExit>();

        for (Object[] action : stockInHand) {
            ItemStockExit itemIssueUpdating = result.get(action[1]);
            itemIssueUpdating.setItemStockEntryID(((Long) action[4]));
            itemIssueUpdating.setQuantityInHand(((Integer) action[3]));
            itemIssueUpdating.setCreatedBy(createdBy);
            itemIssueUpdating.setVanID(vanID);
            itemIssueUpdating.setParkingPlaceID(ppID);
            itemIssueUpdating.setParkingPlaceID(ppID);
            itemIssueUpdating.setFacilityID(facilityID);
            itemIssueUpdating.setSyncFacilityID(facilityID);
            if (itemIssueUpdating.getQuantity() <= itemIssueUpdating.getQuantityInHand()) {
                itemissueListUpdated.add(itemIssueUpdating);
            }
        }
        logger.info("out getItemStockAndValidate method");
        return itemissueListUpdated;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public Integer storeSelfConsumption(StoreSelfConsumption storeSelfConsumption) {
        Integer returnvalue = 0;
        if (true) {
            List<ItemStockExit> itemissueList = storeSelfConsumption.getItemStockExit();

            List<ItemStockExit> itemissueListUpdated = getItemStockAndValidate(itemissueList,
                    storeSelfConsumption.getFacilityID(), storeSelfConsumption.getCreatedBy(),
                    storeSelfConsumption.getVanID(), storeSelfConsumption.getParkingPlaceID());
            if (itemissueList.size() == itemissueListUpdated.size()) {
                storeSelfConsumption.setSyncFacilityID(storeSelfConsumption.getFacilityID());
                storeSelfConsumptionRepo.save(storeSelfConsumption);
                storeSelfConsumptionRepo.updateVanSerialNo();

                returnvalue = saveItemExit(itemissueListUpdated, storeSelfConsumption.getConsumptionID(),
                        "StoreSelfConsumption");
            }

        }

        return returnvalue;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public Integer storeTransfer(T_StockTransfer stockTransfer) {
        Integer returnvalue = 0;
        Long toVanID = stockTransferRepo.findVanIDByFacID(stockTransfer.getTransferToFacilityID());
        stockTransfer.setToVanID(toVanID);
        List<ItemStockExit> itemissueList = stockTransfer.getItemStockExit();

        List<ItemStockExit> itemissueListUpdated = getItemStockAndValidate(itemissueList,
                stockTransfer.getTransferFromFacilityID(), stockTransfer.getCreatedBy(), stockTransfer.getVanID(),
                null);

        if (itemissueList.size() == itemissueListUpdated.size()) {
            stockTransfer.setSyncFacilityID(stockTransfer.getTransferFromFacilityID());
            stockTransferRepo.save(stockTransfer);
            stockTransferRepo.updateVanSerialNo();

            saveItemExit(itemissueListUpdated, stockTransfer.getStockTransferID(), "T_StockTransfer");

            stockEntryService.saveItemStockFromStockTransfer(itemissueListUpdated, stockTransfer.getStockTransferID(),
                    "T_StockTransfer", stockTransfer.getTransferFromFacilityID(),
                    stockTransfer.getTransferToFacilityID(), toVanID);
            returnvalue = 1;
        }

        return returnvalue;
    }

    public List<T_StockTransfer> getStoreTransfer(ItemStockEntryinput itemStockEntryinput) {

        List<T_StockTransfer> dataList = new ArrayList<T_StockTransfer>();

        if (itemStockEntryinput.getFacilityID() != null) {
            if (itemStockEntryinput.getFromDate() != null) {
                Timestamp timestamp = itemStockEntryinput.getFromDate();
                timestamp.setHours(0);
                timestamp.setMinutes(0);
                itemStockEntryinput.setFromDate(timestamp);
                if (itemStockEntryinput.getToDate() != null) {
                    Timestamp timestamp1 = itemStockEntryinput.getToDate();
                    timestamp1.setHours(23);
                    timestamp1.setMinutes(59);
                    itemStockEntryinput.setToDate(timestamp1);
                    dataList = stockTransferRepo
                            .findByCreatedDateBetweenAndTransferFromFacilityIDOrCreatedDateBetweenAndTransferToFacilityIDOrderByCreatedDateDesc(
                                    itemStockEntryinput.getFromDate(), itemStockEntryinput.getToDate(),
                                    itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
                                    itemStockEntryinput.getToDate(), itemStockEntryinput.getFacilityID());
                }
            }
        }

        return dataList;

    }

    public List<T_PatientIssue> getpatientIssue(ItemStockEntryinput itemStockEntryinput) {

        List<T_PatientIssue> dataList = new ArrayList<T_PatientIssue>();

        if (itemStockEntryinput.getFacilityID() != null) {
            if (itemStockEntryinput.getFromDate() != null) {
                Timestamp timestamp = itemStockEntryinput.getFromDate();
                timestamp.setHours(0);
                timestamp.setMinutes(0);
                itemStockEntryinput.setFromDate(timestamp);

                if (itemStockEntryinput.getToDate() != null) {
                    Timestamp timestamp1 = itemStockEntryinput.getToDate();
                    timestamp1.setHours(23);
                    timestamp1.setMinutes(59);
                    itemStockEntryinput.setToDate(timestamp1);
                    dataList = patientIssueRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
                            itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
                            itemStockEntryinput.getToDate());
                }
            }
        }

        return dataList;

    }

    public List<StoreSelfConsumption> getstoreSelfConsumption(ItemStockEntryinput itemStockEntryinput) {

        List<StoreSelfConsumption> storeselfConsumeList = new ArrayList<StoreSelfConsumption>();
        if (itemStockEntryinput.getFacilityID() != null) {
            if (itemStockEntryinput.getFromDate() != null) {
                Timestamp timestamp = itemStockEntryinput.getFromDate();
                timestamp.setHours(0);
                timestamp.setMinutes(0);
                itemStockEntryinput.setFromDate(timestamp);
                if (itemStockEntryinput.getToDate() != null) {
                    Timestamp timestamp1 = itemStockEntryinput.getToDate();
                    timestamp1.setHours(23);
                    timestamp1.setMinutes(59);
                    itemStockEntryinput.setToDate(timestamp1);
                    storeselfConsumeList = storeSelfConsumptionRepo
                            .findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
                                    itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
                                    itemStockEntryinput.getToDate());
                }
            }
        }

        return storeselfConsumeList;

    }

    @Override
    public List<ItemStockExitMap> getstoreSelfConsumptionItemList(ItemStockEntryinput itemStockinput) {
        StoreSelfConsumption ss = storeSelfConsumptionRepo.findByConsumptionID(itemStockinput.getConsumptionID());
        return itemStockExitMapper.getItemStockExitMapList(
                itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(ss.getVanSerialNo(),
                        ss.getSyncFacilityID(), "StoreSelfConsumption"));
    }

    @Override
    public List<ItemStockExitMap> getpatientIssueItemLIst(ItemStockEntryinput itemStockinput) {
        T_PatientIssue patissue = patientIssueRepo.findById((Long) itemStockinput.getPatientIssueID()).get();

        return itemStockExitMapper.getItemStockExitMapList(
                itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(patissue.getVanSerialNo(),
                        patissue.getSyncFacilityID(), "T_PatientIssue"));
    }

    @Override
    public List<ItemStockExitMap> getStoreTransferItemEntry(ItemStockEntryinput itemStockinput) {
        T_StockTransfer tstock = stockTransferRepo.findByStockTransferID(itemStockinput.getStockTransferID());

        return itemStockExitMapper.getItemStockEntryMapList(
                itemStockEntryRepo.findByEntryTypeIDAndSyncFacilityIDAndEntryType(tstock.getVanSerialNo(),
                        tstock.getSyncFacilityID(), "T_StockTransfer"));
    }

    @Override
    public T_PatientIssue getPatientissueAllDetail(Long patientissueID) {
        T_PatientIssue patissue = patientIssueRepo.findById(patientissueID).get();

        patissue.setItemStockExitMap(itemStockExitMapper.getItemStockExitMapList(
                itemStockExitRepo.findByExitTypeIDAndSyncFacilityIDAndExitType(patissue.getVanSerialNo(),
                        patissue.getSyncFacilityID(), "T_PatientIssue")));
        return patissue;
    }
}
