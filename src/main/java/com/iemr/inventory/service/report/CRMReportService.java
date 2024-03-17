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
package com.iemr.inventory.service.report;

import org.springframework.stereotype.Service;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;

@Service
public interface CRMReportService {

    String getInwardStockReport(ItemStockEntryReport itemStockEntryReport);

    String getExpiryReport(ItemStockEntryReport entryReport);

    String getConsumptionReport(ItemStockExitReport exitReport);

    String getBenDrugIssueReport(PatientIssueExitReport exitReport);

    String getDailyStockSummaryReport(ItemStockEntryReport entryReport);

    String getDailyStockDetailsReport(ItemStockEntryReport entryReport);

    String getYearlyReport(ItemStockEntryReport entryReport);

    String getMonthlyReport(ItemStockEntryReport entryReport);

    String getShortExpiryReport(ItemStockEntryReport entryReport);

    String getTransitReport(ItemStockEntryReport entryReport);
}
