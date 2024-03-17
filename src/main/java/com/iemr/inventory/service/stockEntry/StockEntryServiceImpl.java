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
package com.iemr.inventory.service.stockEntry;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.AllocateItemMap;
import com.iemr.inventory.data.stockentry.ItemBatchList;
import com.iemr.inventory.data.stockentry.ItemMasterWithQuantityMap;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.data.stockentry.PhysicalStockEntry;
import com.iemr.inventory.mapper.stockExit.ItemBatchListMap;
import com.iemr.inventory.mapper.stockExit.ItemMasterWithQuantityMapper;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockEntry.PhysicalStockEntryRepo;
import com.iemr.inventory.repository.itemfacilitymapping.M_itemfacilitymappingRepo;
import com.iemr.inventory.service.item.ItemService;
import com.iemr.inventory.utils.exception.InventoryException;

@Service
public class StockEntryServiceImpl implements StockEntryService {

    private static final Logger logger = LoggerFactory.getLogger(StockEntryServiceImpl.class);
    @Autowired(required = false)
    PhysicalStockEntryRepo physicalStockEntryRepo;
    @Autowired(required = false)
    ItemStockEntryRepo itemStockEntryRepo;
    @Autowired(required = false)
    ItemService itemService;
    @Autowired(required = false)
    ItemMasterWithQuantityMapper itemMasterWithQuantityMapper;

    @Autowired(required = false)
    ItemBatchListMap itemBatchListMap;
    @Autowired(required = false)
    private M_itemfacilitymappingRepo m_itemfacilitymappingRepo;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public PhysicalStockEntry savePhysicalStockEntry(PhysicalStockEntry physicalStockEntry) throws Exception {
        physicalStockEntry.setSyncFacilityID(physicalStockEntry.getFacilityID());
        physicalStockEntryRepo.save(physicalStockEntry);
        ((Iterable<ItemStockEntry>) physicalStockEntry.getItemStockEntry()).forEach(stock -> {
            stock.setEntryTypeID(physicalStockEntry.getPhyEntryID());
            stock.setEntryType("physicalStockEntry");
            stock.setQuantityInHand(stock.getQuantity());
            stock.setVanID(physicalStockEntry.getVanID());
            stock.setParkingPlaceID(physicalStockEntry.getParkingPlaceID());
            stock.setCreatedBy(physicalStockEntry.getCreatedBy());
            stock.setSyncFacilityID(physicalStockEntry.getFacilityID());
        });

        itemStockEntryRepo.saveAll(physicalStockEntry.getItemStockEntry());

        physicalStockEntryRepo.updatePhysicalStockEntryVanSerialNo();
        itemStockEntryRepo.updateItemStockEntryVanSerialNo();

        return physicalStockEntry;
    }

    @Override
    public List<ItemStockEntry> getItemBatchForStoreID(ItemStockEntry itemStockEntry) {
        return itemStockEntryRepo.findByFacilityIDAndItemIDAndQuantityInHandGreaterThanAndDeletedAndExpiryDateAfter(
                itemStockEntry.getFacilityID(), itemStockEntry.getItemID(), 0, false, new Date());
    }

    public List<Object[]> getAllItemBatchForStoreID(Integer storeID, Long[] itemStockID) {

        return itemStockEntryRepo.getQuantityOfStock(itemStockID, storeID);
    }

    public Integer updateStocks(List<ItemStockExit> itemissueListUpdated) {
        Integer cnt = 0;
        for (ItemStockExit itemStockExit : itemissueListUpdated) {
            cnt = cnt + itemStockEntryRepo.updateStock(itemStockExit.getFacilityID(),
                    itemStockExit.getItemStockEntryID(), itemStockExit.getQuantity());
        }

        return cnt;

    }

    @Override
    public List<ItemStockEntry> getItemStockForStoreIDOrderByEntryDateAsc(Integer facilityID, Integer itemID,
                                                                          Date nowdate) {
        return itemStockEntryRepo
                .findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByAsc(
                        facilityID, itemID, false, 0, nowdate);
    }

    @Override
    public List<ItemStockEntry> getItemStockForStoreIDOrderByEntryDateDesc(Integer facilityID, Integer itemID,
                                                                           Date nowdate) {
        return itemStockEntryRepo
                .findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByCreatedByDesc(
                        facilityID, itemID, false, 0, nowdate);
    }

    @Override
    public List<ItemStockEntry> getItemStockForStoreIDOrderByExpiryDateAsc(Integer facilityID, Integer itemID,
                                                                           Date nowdate) {
        return itemStockEntryRepo
                .findByFacilityIDAndItemIDAndDeletedAndQuantityInHandGreaterThanAndExpiryDateAfterOrderByExpiryDateAsc(
                        facilityID, itemID, false, 0, nowdate);
    }

    ;

    public List<AllocateItemMap> getItemStockFromItemID(Integer facilityID, List<ItemStockExit> itemStockExitList)
            throws InventoryException {

        List<AllocateItemMap> allocateItemMapList = new ArrayList<AllocateItemMap>();

        for (ItemStockExit itemStockExit : itemStockExitList) {
            AllocateItemMap allocateItemMap = new AllocateItemMap();
            List<ItemStockEntry> itemStockList = new ArrayList<ItemStockEntry>();

            ItemMaster item = itemService.getItemMasterCatByID(itemStockExit.getItemID());

            allocateItemMap.setFacilityID(item.getFacilityID());
            allocateItemMap.setItemID(item.getItemID());
            allocateItemMap.setItemName(item.getItemName());

            List<ItemStockEntry> stockList = new ArrayList<ItemStockEntry>();
            String method = item.getItemCategory().getIssueType();
            Integer itemID = itemStockExit.getItemID();
            Date nowdate = new Date();

            if (itemStockExit.getDuration() != null && itemStockExit.getDuration() > 0
                    && itemStockExit.getDurationUnit() != null) {
                switch (itemStockExit.getDurationUnit()) {
                    case "Day(s)":
                        nowdate.setDate(nowdate.getDate() + itemStockExit.getDuration());
                        break;
                    case "Month(s)":
                        nowdate.setMonth(nowdate.getMonth() + itemStockExit.getDuration());
                        break;
                    case "Week(s)":
                        nowdate.setDate(nowdate.getDate() + (7 * itemStockExit.getDuration()));
                        break;
                    default:
                        break;
                }
            }

            if (method == null) {
                stockList = getItemStockForStoreIDOrderByEntryDateAsc(facilityID, itemID, nowdate);
            } else if (method.equalsIgnoreCase("First Expiry First Out")) {
                stockList = getItemStockForStoreIDOrderByExpiryDateAsc(facilityID, itemID, nowdate);
            } else if (method.equalsIgnoreCase("Last in First Out")) {
                stockList = getItemStockForStoreIDOrderByEntryDateDesc(facilityID, itemID, nowdate);
            } else if (method.equalsIgnoreCase("First in First Out")) {
                stockList = getItemStockForStoreIDOrderByEntryDateAsc(facilityID, itemID, nowdate);
            } else {
                stockList = getItemStockForStoreIDOrderByEntryDateAsc(facilityID, itemID, nowdate);
            }

            Integer totalQty = 0;

            for (ItemStockEntry stock : stockList) {
                totalQty = totalQty + stock.getQuantityInHand();
                itemStockList.add(stock);
                stock.setQuantity(stock.getQuantityInHand());
                if (totalQty >= itemStockExit.getQuantity()) {
                    stock.setQuantity(stock.getQuantityInHand() - totalQty + itemStockExit.getQuantity());
                    break;
                } else if (totalQty < itemStockExit.getQuantity()) {
                    logger.info("Insufficent Qty for '" + item.getItemName() + "'. Available Qty is : " + totalQty);
                    break;
                }

            }
//			if (totalQty < itemStockExit.getQuantity()) {
//				throw new InventoryException(
//						"Insufficent Qty for '" + item.getItemName() + "'. Available Qty is : " + totalQty);
//
//			}

            List<ItemBatchList> stockBatch = itemBatchListMap.getItemStockExitMapList(itemStockList);
//			if(stockBatch != null && stockBatch.size() > 0) {
            for (ItemBatchList objList : stockBatch) {
                if (objList.getExpiryDate() != null)
                    objList.setExpiresIn(calculateExpiryDateInDays(new Timestamp(objList.getExpiryDate().getTime())));
            }
            allocateItemMap.setItemBatchList(stockBatch);
            allocateItemMapList.add(allocateItemMap);
//			} else {
//				ItemBatchList obj = new ItemBatchList();
//				obj.setQuantity(0);
//				obj.setQuantityInHand(0);
//				obj.setItemID(itemID);
//				obj.setFacilityID(facilityID);
//				
//				List<ItemBatchList> stockbatchList = new ArrayList<>();
//				stockbatchList.add(obj);
//				allocateItemMap.setItemBatchList(stockbatchList);
//				allocateItemMapList.add(allocateItemMap);
//			}

        }

        return allocateItemMapList;
    }

    private Long calculateExpiryDateInDays(Timestamp expDate) {
        if (expDate != null) {
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());
            Long diff = (expDate.getTime() - currentDate.getTime()) / 86400000;
            return diff;
        } else
            return null;

    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public List<ItemStockEntry> saveItemStockFromStockTransfer(List<ItemStockExit> itemStockExit, Long insertID,
                                                               String insertType, Integer facilityFromID, Integer facilityToID, Long toVanID) {
        Map<Long, ItemStockExit> result = itemStockExit.stream()
                .collect(Collectors.toMap(ItemStockExit::getItemStockEntryID, Function.identity()));

        Long[] itemstockIDs = result.keySet().toArray(new Long[itemStockExit.size()]);

        List<ItemStockEntry> stockInHand = getItemStockEntryForStoreIDinStockEntryID(facilityFromID, itemstockIDs);

        Integer cnt = 0;
        ItemStockExit itemStockExitsingle = new ItemStockExit();
        List<ItemStockEntry> itemStockEntryupList = new ArrayList<>();
        for (ItemStockEntry itemStockEntry : stockInHand) {
            itemStockExitsingle = result.get(itemStockEntry.getItemStockEntryID());
            ItemStockEntry itemStockEntryup = new ItemStockEntry();
            itemStockEntryup.setFacilityID(facilityToID);
            itemStockEntryup.setQuantity(itemStockExitsingle.getQuantity());
            itemStockEntryup.setQuantityInHand(itemStockExitsingle.getQuantity());
            itemStockEntryup.setItemID(itemStockEntry.getItemID());
            itemStockEntryup.setBatchNo(itemStockEntry.getBatchNo());
            itemStockEntryup.setExpiryDate(itemStockEntry.getExpiryDate());
            itemStockEntryup.setEntryType(insertType);
            itemStockEntryup.setEntryTypeID(insertID);
            itemStockEntryup.setSyncFacilityID(facilityFromID);
            itemStockEntryup.setCreatedBy(itemStockExitsingle.getCreatedBy());
            itemStockEntryup.setCreatedBy(itemStockExitsingle.getCreatedBy());
            itemStockEntryup.setVanID(toVanID);
            itemStockEntryupList.add(itemStockEntryup);
        }
        itemStockEntryRepo.saveAll(itemStockEntryupList);
        itemStockEntryRepo.updateItemStockEntryVanSerialNo();
        return (List<ItemStockEntry>) itemStockEntryupList;
    }

    public List<ItemStockEntry> getItemStockEntryForStoreIDinStockEntryID(Integer facilityID, Long[] itemstockIDs) {
        return itemStockEntryRepo.findByFacilityIDAndItemStockEntryIDIn(facilityID, itemstockIDs);
    }

    public List<PhysicalStockEntry> getPhysicalStockEntry(ItemStockEntryinput itemStockEntryinput) {
        List<PhysicalStockEntry> data = new ArrayList<PhysicalStockEntry>();
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
                    data = physicalStockEntryRepo.findByFacilityIDAndCreatedDateBetweenOrderByCreatedDateDesc(
                            itemStockEntryinput.getFacilityID(), itemStockEntryinput.getFromDate(),
                            itemStockEntryinput.getToDate());
                }
            }
        }

        return data;

    }

    public List<ItemStockEntry> getItemMastersPartialSearch(String itemName, Integer facilityID) {
        List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();
        ArrayList<Object[]> resultSet = m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facilityID, itemName);
        if (resultSet.size() > 0) {
            Integer[] itemID = new Integer[resultSet.size()];
            Object[] objects;
            // for (Object[] objects : resultSet)
            for (int i = 0; i < resultSet.size(); i++) {
                objects = resultSet.get(i);
                if (objects != null && objects.length >= 2) {

                    itemID[i] = (Integer) objects[0];
                }

            }

            itemForsubStore = itemStockEntryRepo.findByItemIDInAndQuantityInHandGreaterThanAndFacilityID(itemID, 0,
                    facilityID);

        }

        return itemForsubStore;
    }

    public List<ItemStockEntry> getPhysicalStockEntryItems(Long phySEID) {

        PhysicalStockEntry pse = physicalStockEntryRepo.findById(phySEID).get();

        return itemStockEntryRepo.findByEntryTypeIDAndSyncFacilityIDAndEntryType(pse.getVanSerialNo(),
                pse.getSyncFacilityID(), "physicalStockEntry");

    }

    @Override
    public List<ItemMasterWithQuantityMap> getItemwithQuantityPartialSearch(String itemName, Integer facilityID) {
        List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();

        ArrayList<Object[]> resultSet = m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facilityID, itemName);
        if (resultSet.size() > 0) {
            Integer[] itemID = new Integer[resultSet.size()];
            Object[] objects;
            for (int i = 0; i < resultSet.size(); i++) {
                objects = resultSet.get(i);
                if (objects != null && objects.length >= 2) {

                    itemID[i] = (Integer) objects[0];
                }

            }
            Date now = new Date();
            List<Object[]> itemForsubStoreObj = itemStockEntryRepo
                    .findByItemIDInQuantityInHandGreaterThanForFacilityID(itemID, 0L, facilityID, now);

            itemForsubStoreObj.forEach(item -> {
                ItemStockEntry itemFor = new ItemStockEntry();
                itemFor = (ItemStockEntry) item[0];
                itemFor.setQuantityInHand(((Long) item[1]).intValue());
                itemForsubStore.add(itemFor);
            });
            logger.info(itemForsubStoreObj.toString());
        }

        return itemMasterWithQuantityMapper.getItemStockExitMapList(itemForsubStore);
    }

    public List<ItemStockEntry> getItemMastersPartialSearchWithZero(String itemName, Integer facilityID) {
        List<ItemStockEntry> itemForsubStore = new ArrayList<ItemStockEntry>();
        ArrayList<Object[]> resultSet = m_itemfacilitymappingRepo.getItemforStoreLikeItemName(facilityID, itemName);
        if (resultSet.size() > 0) {
            Integer[] itemID = new Integer[resultSet.size()];
            Object[] objects;
            for (int i = 0; i < resultSet.size(); i++) {
                objects = resultSet.get(i);
                if (objects != null && objects.length >= 2) {

                    itemID[i] = (Integer) objects[0];
                }

            }

            itemForsubStore = itemStockEntryRepo.findByItemIDInAndFacilityIDOrderByItemStockEntryIDDesc(itemID,
                    facilityID);

        }

        return itemForsubStore;
    }
}
