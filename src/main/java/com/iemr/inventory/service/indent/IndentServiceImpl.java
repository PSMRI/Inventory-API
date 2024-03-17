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
package com.iemr.inventory.service.indent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.indent.Indent;
import com.iemr.inventory.data.indent.IndentIssue;
import com.iemr.inventory.data.indent.IndentOrder;
import com.iemr.inventory.data.indent.ItemfacilitymappingIndent;
import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.repo.indent.IndentIssueRepo;
import com.iemr.inventory.repo.indent.IndentOrderRepo;
import com.iemr.inventory.repo.indent.IndentRepo;
import com.iemr.inventory.repo.indent.ItemfacilitymappingIndentRepo;
import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;
import com.iemr.inventory.repo.stockExit.ItemStockExitRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class IndentServiceImpl implements IndentService {

    private static final Logger logger = LoggerFactory.getLogger(IndentServiceImpl.class);
    @Autowired(required = false)
    ItemfacilitymappingIndentRepo itemfacilitymappingIndentRepo;
    @Autowired(required = false)
    IndentOrderRepo indentOrderRepo;
    @Autowired(required = false)
    IndentRepo indentRepo;
    @Autowired(required = false)
    IndentIssueRepo indentIssueRepo;
    @Autowired(required = false)
    ItemStockExitRepo itemStockExitRepo;
    @Autowired(required = false)
    ItemStockEntryRepo itemStockEntryRepo;
    @PersistenceContext
    private EntityManager entityManager;

    private static ItemfacilitymappingIndent getItemfacilitymappingIndentclass(Object[] obj) {

        ItemfacilitymappingIndent item = new ItemfacilitymappingIndent();

        item.setItemID((Integer) obj[0]);
        item.setItemCode((String) obj[1]);
        item.setItemName((String) obj[2]);
        item.setIsMedical((Boolean) obj[3]);
        item.setStrength((String) obj[4]);
        item.setUomName((String) obj[5]);
        item.setItemCategory((String) obj[6]);
        item.setItemForm((String) obj[7]);
        item.setPharmacologicalCategoryName((String) obj[8]);
        item.setComposition((String) obj[9]);
        item.setFacilityID((Integer) obj[10]);
        item.setQoh(obj[11] == null ? new BigDecimal(0) : (BigDecimal) obj[11]);

        return item;

    }

    public List<ItemfacilitymappingIndent> findItemIndent(Integer facilityID, String itemName) {
        List<Object[]> a = itemfacilitymappingIndentRepo.findindentitem(facilityID, itemName);

        List<ItemfacilitymappingIndent> itemfacilitymappingIndent = new ArrayList<ItemfacilitymappingIndent>();

        a.stream().forEach(action -> {

            itemfacilitymappingIndent.add(getItemfacilitymappingIndentclass(action));

        });
        return itemfacilitymappingIndent;
    }

    @Override
    public String createIndentRequest(Indent indent) {
        logger.info("Creating Indent - Start");

        indent.setSyncFacilityID(indent.getFromFacilityID());
        indent.setOrderDate(new Timestamp(System.currentTimeMillis()));
        indent.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        indent.setStatus("Pending");
        indent.setProcessed("N");
        Indent indentCreated = indentRepo.save(indent);
        indentRepo.updateVanSerialNo(indentCreated.getIndentID(), indentCreated.getFromFacilityID());
        indent.getIndentOrder().parallelStream().forEach(indentOrder -> {
            indentOrder.setSyncFacilityID(indent.getSyncFacilityID());
            indentOrder.setIndentID(indentCreated.getIndentID());
            indentOrder.setVanID(indentCreated.getVanID());
            indentOrder.setProviderServiceMapID(indentCreated.getProviderServiceMapID());
            indentOrder.setCreatedBy(indentCreated.getCreatedBy());
            indentOrder.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            indentOrder.setParkingPlaceID(indentCreated.getParkingPlaceID());
            indentOrder.setStatus("Pending");
            indentOrder.setProcessed("N");
            indentOrder.setFromFacilityID(indentCreated.getFromFacilityID());
        });

        indentCreated.setIndentOrder((List<IndentOrder>) indentOrderRepo.saveAll(indent.getIndentOrder()));

        indentOrderRepo.updateVanSerialNo();

        logger.info("Creating Indent - End");
        return indentCreated.toString();
    }
    
    

    @Override
    public String getIndentHistory(Indent indent) {
        logger.info("getIndentHistory- Start");

        List<Indent> list = indentOrderRepo.getIndentHistory(indent.getFromFacilityID());

        logger.info("getIndentHistory- End");
        return list.toString();
    }

    @Override
    public String getOrdersByIndentID(IndentOrder indentOrder) {
        logger.info("getOrdersByIndentID- Start");

        Indent indent = indentRepo.findByIndentID(indentOrder.getIndentID());

        List<IndentOrder> list = indentOrderRepo.getOrdersByIndentID(indent.getVanSerialNo(),
                indent.getSyncFacilityID());

        logger.info("getOrdersByIndentID- End");
        return list.toString();
    }

    @Override
    public String getIndentWorklist(IndentOrder indentOrder) {
        logger.info("getIndentWorklist- Start");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Indent> criteriaQuery = criteriaBuilder.createQuery(Indent.class);
        Root<Indent> root = criteriaQuery.from(Indent.class);
        List<Predicate> predicates = new ArrayList<Predicate>();

        Join<Indent, M_Facility> facilityRoot = root.join("m_Facility", JoinType.INNER);

        predicates.add(criteriaBuilder.equal(root.get("status"), "Pending"));
        predicates.add(criteriaBuilder.equal(facilityRoot.get("mainFacilityID"), indentOrder.getFacilityID()));

        if ((indentOrder.getStartDateTime() != null && indentOrder.getEndDateTime() != null)
                || indentOrder.getIndentFromID() != null) {
            if (indentOrder.getStartDateTime() != null && indentOrder.getEndDateTime() != null) {
                predicates.add(criteriaBuilder.between(root.get("createdDate"), indentOrder.getStartDateTime(),
                        indentOrder.getEndDateTime()));
            }

            if (indentOrder.getIndentFromID() != null) {
                predicates.add(criteriaBuilder.isNotNull(root.get("fromFacilityID")));
                predicates.add(criteriaBuilder.equal(root.get("fromFacilityID"), indentOrder.getIndentFromID()));
            }
        }
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[]{}))
                .orderBy(criteriaBuilder.desc(root.get("createdDate")));
        List<Indent> results = entityManager.createQuery(criteriaQuery).getResultList();

        logger.info("getIndentWorklist- End");
        return results.toString();
    }
    
    
    


    @Override
    public String getIndentOrderWorklist(IndentOrder indentOrder) {
        logger.info("getIndentOrderWorklist- Start");

        Indent indent = indentRepo.findByIndentID(indentOrder.getIndentID());

        List<IndentOrder> list = (List<IndentOrder>) indentOrderRepo.getOrdersByIndentID(indent.getVanSerialNo(),
                indent.getSyncFacilityID());

        logger.info("getIndentOrderWorklist- End");
        return list.toString();
    }

    @Override
    public String issueIndent(IndentIssue[] array) {
        logger.info("issueIndent - Start");
        List<IndentIssue> list = Arrays.asList(array);

        Indent indent = indentRepo.findByIndentID(list.get(0).getIndentID());
        indentOrderRepo.issueIndent(list.get(0).getAction(), indent.getVanSerialNo(), indent.getSyncFacilityID(),
                list.get(0).getRejectedReason());
        indentOrderRepo.issueIndentOrder(list.get(0).getAction(), indent.getVanSerialNo(), indent.getSyncFacilityID());

        for (IndentIssue indentIssue : list) {
            if (indentIssue.getAction().equalsIgnoreCase("Issued")) {
                indentOrderRepo.updateQuantityInStock(indentIssue.getIssuedQty(), indentIssue.getItemStockEntryID(),
                        indentIssue.getFromFacilityID());

                indentIssue.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                indentIssue.setIssueDate(new Timestamp(System.currentTimeMillis()));
                indentIssue.setProcessed("N");
                indentIssue.setSyncFacilityID(indentIssue.getFromFacilityID());
                indentIssueRepo.save(indentIssue);

                ItemStockExit itemStockExit = new ItemStockExit();
                itemStockExit.setItemStockEntryID(indentIssue.getItemStockEntryID());
                itemStockExit.setSyncFacilityID(indentIssue.getFromFacilityID());
                itemStockExit.setQuantity(indentIssue.getIssuedQty());
                itemStockExit.setExitTypeID(indentIssue.getIndentID());
                itemStockExit.setExitType("t_indent");
                itemStockExit.setCreatedBy(indentIssue.getCreatedBy());
                itemStockExit.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                itemStockExit.setVanID(indentIssue.getVanID());
                itemStockExit.setParkingPlaceID(indentIssue.getParkingPlaceID());
                itemStockExitRepo.save(itemStockExit);

            }
        }
        indentIssueRepo.updateVanSerialNo();
        itemStockExitRepo.updateVanSerialNo();
        logger.info("issueIndent - End");
        if (list.get(0).getAction().equalsIgnoreCase("Issued")) {
            return "Dispensed successfully";
        }
        return "Rejected successfully";
    }

    @Override
    public String cancelIndentOrder(Indent indent1) {
        logger.info("cancelIndentOrder - Start");

        Indent indent = indentRepo.findByIndentID(indent1.getIndentID());

        indentOrderRepo.cancelIndent(indent.getIndentID());
        indentOrderRepo.cancelIndentOrder(indent.getVanSerialNo(), indent.getSyncFacilityID());
        logger.info("cancelIndentOrder - End");
        return "Cancelled successfully";
    }

    @Override
    public String receiveIndent(Indent indent) {
        logger.info("receiveIndent - Start");

        Indent idn = indentRepo.findByIndentID(indent.getIndentID());

        indentOrderRepo.acceptIndent(indent.getIndentID(), indent.getFromFacilityID());
        indentOrderRepo.acceptIndentOrder(idn.getVanSerialNo(), idn.getSyncFacilityID());

        List<IndentIssue> issuedList = indentOrderRepo.getIndentIssued(idn.getVanSerialNo(), idn.getToFacilityID());
        List<ItemStockEntry> itemStockList = new ArrayList<ItemStockEntry>();
        for (IndentIssue indentIssue : issuedList) {
            if (indentIssue.getIssuedQty() > 0) {
                ItemStockEntry stockEntry = new ItemStockEntry();
                stockEntry.setFacilityID(indent.getFromFacilityID());
                stockEntry.setItemID(indentIssue.getItemID());
                stockEntry.setQuantity(indentIssue.getIssuedQty());
                stockEntry.setQuantityInHand(indentIssue.getIssuedQty());
                stockEntry.setTotalCostPrice(indentIssue.getUnitCostPrice());
                stockEntry.setBatchNo(indentIssue.getBatchNo());
                stockEntry.setExpiryDate(indentIssue.getExpiryDate());
                stockEntry.setEntryTypeID(indentIssue.getIndentID());
                stockEntry.setEntryType("Indent");
                stockEntry.setCreatedBy(indent.getCreatedBy());
                stockEntry.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                stockEntry.setSyncFacilityID(idn.getFromFacilityID());
                itemStockList.add(stockEntry);
            }
        }
        itemStockEntryRepo.saveAll(itemStockList);
        itemStockEntryRepo.updateItemStockEntryVanSerialNo();
        logger.info("receiveIndent - End");
        return "Received successfully";
    }

    @Override
    public String updateIndentOrder(Indent indent) {
        logger.info("Updating Indent - Start");

        indent.setSyncFacilityID(indent.getFromFacilityID());
        Indent indentCreated = indentRepo.save(indent);
        // indentRepo.updateVanSerialNo();
        indent.getIndentOrder().parallelStream().forEach(indentOrder -> {

            if (indentOrder.getIndentOrderID() == null) {
                indentOrder.setIndentID(indentCreated.getIndentID());
                indentOrder.setVanID(indentCreated.getVanID());
                indentOrder.setProviderServiceMapID(indentCreated.getProviderServiceMapID());
                indentOrder.setCreatedBy(indentCreated.getCreatedBy());
                indentOrder.setCreatedDate(new Timestamp(System.currentTimeMillis()));
                indentOrder.setParkingPlaceID(indentCreated.getParkingPlaceID());
                indentOrder.setStatus("Pending");
                indentOrder.setProcessed("N");
                indentOrder.setFromFacilityID(indentCreated.getFromFacilityID());
                indentOrder.setSyncFacilityID(indentCreated.getSyncFacilityID());
            } else {
                if (indentOrder != null && indentCreated != null && indentCreated.getSyncFacilityID() != null)
                    indentOrder.setSyncFacilityID(indentCreated.getSyncFacilityID());
            }
        });

        indentCreated.setIndentOrder((List<IndentOrder>) indentOrderRepo.saveAll(indent.getIndentOrder()));

        logger.info("Updating Indent - End");
        return "Updated successfully";
    }

}
