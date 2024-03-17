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

import java.util.List;

import org.springframework.stereotype.Service;

import com.iemr.inventory.data.stockExit.ItemStockExit;
import com.iemr.inventory.data.stockExit.ItemStockExitMap;
import com.iemr.inventory.data.stockExit.StoreSelfConsumption;
import com.iemr.inventory.data.stockExit.T_PatientIssue;
import com.iemr.inventory.data.stockExit.T_StockTransfer;
import com.iemr.inventory.data.stockentry.ItemStockEntryinput;
import com.iemr.inventory.utils.exception.InventoryException;

@Service
public interface StockExitService {

    Integer issuePatientDrugs(T_PatientIssue patientIssue) throws InventoryException;

    Integer saveItemExit(List<ItemStockExit> itemissueListUpdated, Long issueID, String issueType);

    List<ItemStockExit> getItemStockAndValidate(List<ItemStockExit> itemissueList, Integer facilityID,
                                                String createdBy, Long vanID, Long ppID);

    Integer storeSelfConsumption(StoreSelfConsumption storeSelfConsumption);

    Integer storeTransfer(T_StockTransfer stockTransfer);

//	 List<ItemStockEntry> getItemStockFromItemID(Integer facilityID,List<ItemStockExit> itemStockExit);

    List<ItemStockExitMap> getstoreSelfConsumptionItemList(ItemStockEntryinput itemStockinput);

    List<ItemStockExitMap> getpatientIssueItemLIst(ItemStockEntryinput itemStockinput);

    List<ItemStockExitMap> getStoreTransferItemEntry(ItemStockEntryinput itemStockinput);

    T_PatientIssue getPatientissueAllDetail(Long patientissueID);


}
