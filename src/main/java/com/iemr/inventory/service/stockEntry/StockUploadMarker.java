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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.iemr.inventory.repo.stockEntry.ItemStockEntryRepo;

/**
 * StopTB camp laptops (stoptb.enforce.vanid=true): the camp's stock is reported on central from uploaded rows, so a
 * batch whose QuantityInHand changes (adjustment, stock exit, patient return, indent issue) is marked
 * Processed='N' and uploaded again; central updates its copy by VanSerialNo + store. Other deployments: no change.
 */
@Component
public class StockUploadMarker {

	@Value("${stoptb.enforce.vanid:false}")
	private boolean stopTBLaptop;

	@Autowired
	private ItemStockEntryRepo itemStockEntryRepo;

	public void batchChanged(Long itemStockEntryID) {
		if (stopTBLaptop && itemStockEntryID != null)
			itemStockEntryRepo.markForUpload(itemStockEntryID);
	}

	public void batchChangedByVanSerialNo(Long vanSerialNo, Integer facilityID) {
		if (stopTBLaptop && vanSerialNo != null && facilityID != null)
			itemStockEntryRepo.markForUploadByVanSerialNo(vanSerialNo, facilityID);
	}
}
