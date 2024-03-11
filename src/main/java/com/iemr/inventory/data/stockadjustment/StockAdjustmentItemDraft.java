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
package com.iemr.inventory.data.stockadjustment;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.data.stockentry.ItemStockEntry;
import com.iemr.inventory.utils.mapper.OutputMapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Table(name = "m_SADraftItemMapping")
@Data
public class StockAdjustmentItemDraft {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	@Column(name = "SADraftItemMapID")
	private Long sADraftItemMapID;

	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ItemStockEntryID", insertable = false, updatable = false)
	@Expose
	private ItemStockEntry itemStockEntry;

	@Expose
	@Column(name = "ItemStockEntryID")
	private Long itemStockEntryID;

	@Expose
	@Column(name = "IsAdded")
	private Boolean isAdded;

	@Expose
	@Column(name = "AdjustedQuantity")
	private Integer adjustedQuantity;

	@Expose
	@Column(name = "Reason")
	private String reason;

	@Expose
	@Column(name = "ProviderServiceMapID")
	private Integer providerServiceMapID;

	@Expose
	@Column(name = "Status")
	private String status;
	
	@Expose
	@Column(name = "StockAdjustmentDraftID")
	private Long stockAdjustmentDraftID;


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
	private Object createdDate;

	@Expose
	@Column(name = "ModifiedBy")
	private String modifiedBy;
	
	@Expose
	@Column(name="LastModDate", insertable = false, updatable = false)
	private Date lastModDate;

	@Transient
	private OutputMapper outputMapper = new OutputMapper();

	@Override
	public String toString() {
		return outputMapper.gson().toJson(this);
	}

	public Long getSADraftItemMapID() {
		
		return null;
	}

	public Object getCreatedBy() {
		
		return null;
	}

	public Object getCreatedDate() {
		
		return null;
	}

	public Object getProcessed() {
		
		return null;
	}

	public Object getItemStockEntryID() {
		
		return null;
	}

	public ItemStockEntry getItemStockEntry() {
		
		return null;
	}

	public Object getAdjustedQuantity() {
		
		return null;
	}

	public Object getProviderServiceMapID() {
		
		return null;
	}

	public Long getsADraftItemMapID() {
		return sADraftItemMapID;
	}

	public void setsADraftItemMapID(Long sADraftItemMapID) {
		this.sADraftItemMapID = sADraftItemMapID;
	}

	public void setCreatedDate(Object createdDate2) {
		this.createdDate=createdDate2;
		
	}
	
}	