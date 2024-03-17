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

import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.Transient;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ItemStockEntryinput {

    private Long stockAdjustmentDraftID;

    private Long stockAdjustmentID;

    private Long consumptionID;

    private Long patientIssueID;

    private Long stockTransferID;

    private Long phyEntryID;

    private Integer facilityID;

    //	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp fromDate;
    //	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Timestamp toDate;

    @Transient
    private OutputMapper outputMapper = new OutputMapper();

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }


}
	

