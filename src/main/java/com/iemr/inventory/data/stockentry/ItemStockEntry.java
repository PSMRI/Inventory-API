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
package com.iemr.inventory.data.stockentry;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.utils.mapper.OutputMapper;

import lombok.Data;

@Entity
@Table(name = "t_itemstockentry")
@Data
public class ItemStockEntry implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	@Column(name = "ItemStockEntryID")
	private Long itemStockEntryID;

	@Expose
	@Column(name="VanSerialNo")
	private Long vanSerialNo;
	
	@Expose
	@Column(name = "FacilityID")
	private Integer facilityID;
	
	@Expose
	@Column(name="SyncFacilityID")
	private Integer syncFacilityID;

	@Expose
	@Column(name = "ItemID")
	private Integer itemID;

	// @Expose
	// @Transient
	// private Integer issueQuantity;

	@Expose
	@Column(name = "Quantity")
	private Integer quantity;

	@Expose
	@Column(name = "QuantityInHand")
	private Integer quantityInHand;

	@Expose
	@Column(name = "UnitCostPrice")
	private Double totalCostPrice;

	@Expose
	@Column(name = "BatchNo")
	private String batchNo;

	@Expose
	@Column(name = "ExpiryDate")
	private Date expiryDate;

	@Expose
	@Column(name = "EntryTypeID")
	private Long entryTypeID;

	@JoinColumn(name = "EntryTypeID", insertable = false, updatable = false)
	@OneToOne(fetch = FetchType.LAZY)
	private PhysicalStockEntry physicalStockEntry;

	@Expose
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(updatable = false, insertable = false, name = "itemID")
	private ItemMaster item;

	@Expose
	@Column(name = "EntryType")
	private String entryType;

	@Expose
	@Column(name = "Deleted", insertable = false, updatable = true)
	private Boolean deleted;

	@Expose
	@Column(name = "Processed", insertable = false, updatable = true)
	private Character processed;

	@Expose
	@Column(name = "CreatedBy")
	private String createdBy;

	@Expose
	@Column(name = "CreatedDate", insertable = false, updatable = false)
	private Date createdDate;

	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;

	@Expose
	@Column(name = "LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Transient
	private Long totalQuantity;

	@Expose
	@Column(name = "VanID")
	private Long vanID;

	@Expose
	@Column(name = "ParkingPlaceID")
	private Long parkingPlaceID;

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

	public void setSyncFacilityID(Object facilityID2) {
		// TODO Auto-generated method stub
		
	}

	public void setEntryTypeID(Object indentID) {
		// TODO Auto-generated method stub
		
	}

	public void setEntryType(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setCreatedBy(Object createdBy2) {
		// TODO Auto-generated method stub
		
	}

	
}
