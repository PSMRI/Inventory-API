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
package com.iemr.inventory.model.report;

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import lombok.Data;

@Data
public class LowStockReport {

	private Long slNo;

	private String facilityName;

	private Integer itemID;

	private String itemName;

	private String itemCategory;

	private String strength;

	private Integer minStock;

	private Integer quantityInHand;

	// "Out of stock" (nothing usable left) or "Low stock" (at or below the item's minimum stock)
	private String stockStatus;

	public LowStockReport() {

	}

	public LowStockReport(String facilityName, Integer itemID, String itemName, String itemCategory, String strength,
			Integer minStock, Integer quantityInHand) {

		this.facilityName = facilityName;
		this.itemID = itemID;
		this.itemName = itemName;
		this.itemCategory = itemCategory;
		this.strength = strength;
		this.minStock = minStock;
		this.quantityInHand = quantityInHand;
		this.stockStatus = (quantityInHand == null || quantityInHand <= 0) ? "Out of stock" : "Low stock";
	}

	@Override
	public String toString() {

		return new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).serializeNulls().create()
				.toJson(this);

	}

}
