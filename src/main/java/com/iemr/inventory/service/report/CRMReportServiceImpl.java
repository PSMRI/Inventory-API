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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import com.iemr.inventory.mapper.report.InventoryReportMapper;
import com.iemr.inventory.model.report.BenDrugIssueReport;
import com.iemr.inventory.model.report.ConsumptionReport;
import com.iemr.inventory.model.report.DailyStockDetails;
import com.iemr.inventory.model.report.DailyStockSummary;
import com.iemr.inventory.model.report.ExpiryReport;
import com.iemr.inventory.model.report.InwardStockReport;
import com.iemr.inventory.model.report.MonthlyReport;
import com.iemr.inventory.model.report.TransitReport;
import com.iemr.inventory.model.report.YearlyReport;
import com.iemr.inventory.repo.report.ItemStockReportRepo;

@Service
public class CRMReportServiceImpl implements CRMReportService {

    @Autowired(required = false)
    ItemStockReportRepo itemStockReportRepo;

    @Autowired(required = false)
    InventoryReportMapper mapper;

    @Autowired
    public CRMReportServiceImpl(ItemStockReportRepo itemStockReportRepo) {
        this.itemStockReportRepo = itemStockReportRepo;
    }

    @Override
    public String getInwardStockReport(ItemStockEntryReport entryReport) {

        List<ItemStockEntryReport> list = new ArrayList<ItemStockEntryReport>();
        List<InwardStockReport> reportList = new ArrayList<InwardStockReport>();
        if (entryReport.getFacilityID() != null) {
            list = itemStockReportRepo.getItemStockEntryReportByFacilityID(entryReport.getStartDate(),
                    entryReport.getEndDate(), entryReport.getFacilityID());
        } else {
            list = itemStockReportRepo.getItemStockEntryReport(entryReport.getStartDate(), entryReport.getEndDate());
        }

        Long slNo = 1L;
        for (ItemStockEntryReport reportData : list) {
            InwardStockReport report = mapper.mapInwardStockReport(reportData);
            report.setSlNo(slNo++);
            reportList.add(report);
        }
        return reportList.toString();
    }

    @Override
    public String getExpiryReport(ItemStockEntryReport entryReport) {

        List<Object[]> list = new ArrayList<Object[]>();
        List<ExpiryReport> reportList = new ArrayList<ExpiryReport>();
        Date startExpiry = new Date(entryReport.getStartDate().getTime());
        Date endExpiry = new Date(entryReport.getEndDate().getTime());
        if (entryReport.getFacilityID() != null) {
            list = itemStockReportRepo.getExpiryReportByFacilityID(startExpiry, endExpiry, entryReport.getFacilityID());
        } else {
            list = itemStockReportRepo.getExpiryReport(startExpiry, endExpiry);
        }
        Long slNo = 1L;
        for (Object[] object : list) {

            if (object != null) {
                ExpiryReport report = new ExpiryReport(object[0] != null ? object[0].toString() : null,
                        object[1] != null ? object[1].toString() : null,
                        object[2] != null ? object[2].toString() : null,
                        object[3] != null ? object[3].toString() : null,
                        object[4] != null ? ((Double) object[4]).doubleValue() : null,
                        (Date) (object[5] != null ? object[5] : null),
                        (Integer) (object[6] != null ? object[6] : null));

                report.setSlNo(slNo++);
                reportList.add(report);
            }
        }

        return reportList.toString();
    }

    @Override
    public String getConsumptionReport(ItemStockExitReport exitReport) {

        List<Object[]> list = new ArrayList<Object[]>();
        List<ConsumptionReport> reportList = new ArrayList<ConsumptionReport>();
        if (exitReport.getFacilityID() != null) {
            list = itemStockReportRepo.getItemStockExitReportByFacilityID(exitReport.getStartDate(),
                    exitReport.getEndDate(), exitReport.getFacilityID());
        } else {
            list = itemStockReportRepo.getItemStockExitReport(exitReport.getStartDate(), exitReport.getEndDate());
        }
        Long slNo = 1L;
        for (Object[] obj : list) {

            if (obj != null && obj.length >= 40) {
                ConsumptionReport report = new ConsumptionReport(obj[16] != null ? obj[16].toString() : null,
                        (Timestamp) (obj[32] != null ? obj[32] : null), (Integer) (obj[23] != null ? obj[23] : null),
                        obj[26] != null ? obj[26].toString() : null, (Date) (obj[20] != null ? obj[20] : null),
                        obj[4] != null ? obj[4].toString() : null, obj[14] != null ? obj[14].toString() : null,
                        obj[11] != null ? obj[11].toString() : null,
                        obj[19] != null ? ((BigDecimal) obj[19]).doubleValue() : null,
                        obj[39] != null ? obj[39].toString() : null,
                        obj[38] != null ? ((BigInteger) obj[38]).toString() : "");
                report.setSlNo(slNo++);
                reportList.add(report);
            }
        }

        return reportList.toString();
    }

    @Override
    public String getBenDrugIssueReport(PatientIssueExitReport exitReport) {

        List<PatientIssueExitReport> list = new ArrayList<PatientIssueExitReport>();
        List<BenDrugIssueReport> reportList = new ArrayList<BenDrugIssueReport>();
        if (exitReport.getFacilityID() != null) {
            list = itemStockReportRepo.getPatientIssueExitReportByFacilityID(exitReport.getStartDate(),
                    exitReport.getEndDate(), exitReport.getFacilityID());
        } else {
            list = itemStockReportRepo.getPatientIssueExitReport(exitReport.getStartDate(), exitReport.getEndDate());
        }

        Long slNo = 1L;
        for (PatientIssueExitReport reportData : list) {
            BenDrugIssueReport report = mapper.mapBenDrugIssueReport(reportData);
            report.setSlNo(slNo++);
            reportList.add(report);
        }
        return reportList.toString();
    }

    @Override
    public String getDailyStockDetailsReport(ItemStockEntryReport entryReport) {

        Date todaysDate = new Date(entryReport.getStartDate().getTime());

        List<DailyStockDetails> list = new ArrayList<DailyStockDetails>();

        List<Objects[]> reports = null;

        reports = itemStockReportRepo.getDailyStockDetailReportByFacilityID(todaysDate, todaysDate,
                entryReport.getFacilityID());

        Long slNo = 1L;
        for (Object[] objects : reports) {
            if (objects != null && objects.length > 0) {

                String batchNo = (String) objects[3];
                Long totalQuantityReceived = 0L;
                if (objects[4] != null) {
                    totalQuantityReceived = ((Number) objects[4]).longValue();
                }
                Double unitCostPrice = 0.0;
                if (objects[5] != null) {
                    unitCostPrice = ((Number) objects[5]).doubleValue();
                }
                Date expiryDate = (Date) objects[6];
                Long openingStock = 0L;
                if (objects[10] != null) {
                    openingStock = ((Number) objects[10]).longValue();
                }
                Long adjustedQuantity_FromDate = 0L;
                if (objects[11] != null) {
                    adjustedQuantity_FromDate = ((Number) objects[11]).longValue();
                }
                Long quantityDispanced = 0L;
                if (objects[12] != null) {
                    quantityDispanced = ((Number) objects[12]).longValue();
                }
                String itemName = (String) objects[13];
                String facilityName = (String) objects[14];
                String itemCategoryName = (String) objects[15];
                Long adjustedQuantity_ToDate = 0L;
                if (objects[16] != null) {
                    adjustedQuantity_ToDate = ((Number) objects[16]).longValue();
                }
                Long adjustedQuantity_ToDate_Receipt = 0L;
                if (objects[17] != null) {
                    adjustedQuantity_ToDate_Receipt = ((Number) objects[17]).longValue();
                }
                Long adjustedQuantity_ToDate_Issue = 0L;
                if (objects[18] != null) {
                    adjustedQuantity_ToDate_Issue = ((Number) objects[18]).longValue();
                }
                Long ClosingStock = 0L;
                if (objects[19] != null) {
                    ClosingStock = ((Number) objects[19]).longValue();
                }


//				Long actualOpening = openingStock + adjustedQuantity_FromDate;
                Long actualOpening = openingStock;
                Long actualDispensed = quantityDispanced;// - adjustedQuantity_ToDate;
                Long actualClosing = ClosingStock;
//				if (actualOpening == 0 || actualOpening == null) {
//					actualClosing = totalQuantityReceived - actualDispensed + adjustedQuantity_ToDate;
//				}
//
//				else {
//					actualClosing = actualOpening - actualDispensed + adjustedQuantity_ToDate;
//					totalQuantityReceived = 0L;
//				}

                Timestamp itemEnteredDate = null;
                if (objects[9] != null) {
                    itemEnteredDate = (Timestamp) objects[9];

                }
                DailyStockDetails stockDetail = new DailyStockDetails();
                stockDetail.setSlNo(slNo++);
                stockDetail.setDate(entryReport.getStartDate());
                stockDetail.setFacilityName(facilityName);
                stockDetail.setItemName(itemName);
                stockDetail.setItemCategory(itemCategoryName);
                stockDetail.setBatchNo(batchNo);
                stockDetail.setUnitCostPrice(unitCostPrice);
                stockDetail.setExpiryDate(expiryDate);
                stockDetail.setOpeningStock(actualOpening);
                stockDetail.setQuantityReceived(totalQuantityReceived);
                stockDetail.setDispensedQuantity(actualDispensed);
                stockDetail.setClosingStock(actualClosing);

                stockDetail.setItemEnteredDate(itemEnteredDate);

                stockDetail.setAdjustmentIssue(adjustedQuantity_ToDate_Issue);
                stockDetail.setAdjustmentReceipt(adjustedQuantity_ToDate_Receipt);
                list.add(stockDetail);
            }
        }

        return list.toString();
    }

    @Override
    public String getDailyStockSummaryReport(ItemStockEntryReport entryReport) {

        Date todaysDate = new Date(entryReport.getStartDate().getTime());

        List<DailyStockSummary> list = new ArrayList<DailyStockSummary>();

        List<Objects[]> reports = null;

        reports = itemStockReportRepo.getDailyStockSummaryReportByFacilityID(todaysDate, todaysDate,
                entryReport.getFacilityID());
        Long slNo = 1L;
        for (Object[] objects : reports) {
            if (objects != null && objects.length > 0) {

                Long totalQuantityReceived = 0L;
                if (objects[5] != null) {
                    totalQuantityReceived = ((Number) objects[5]).longValue();
                }
                Long openingStock = 0L;
                if (objects[6] != null) {
                    openingStock = ((Number) objects[6]).longValue();
                }
                Long adjustedQuantity_FromDate = 0L;
                if (objects[7] != null) {
                    adjustedQuantity_FromDate = ((Number) objects[7]).longValue();
                }
                Long quantityDispanced = 0L;
                if (objects[8] != null) {
                    quantityDispanced = ((Number) objects[8]).longValue();
                }
                String itemName = (String) objects[2];
                String facilityName = (String) objects[3];
                String itemCategoryName = (String) objects[4];
                Long adjustedQuantity_ToDate = 0L;
                if (objects[9] != null) {
                    adjustedQuantity_ToDate = ((Number) objects[9]).longValue();
                }
                Long adjustedQuantity_ToDate_Receipt = 0L;
                if (objects[10] != null) {
                    adjustedQuantity_ToDate_Receipt = ((Number) objects[10]).longValue();
                }
                Long adjustedQuantity_ToDate_Issue = 0L;
                if (objects[11] != null) {
                    adjustedQuantity_ToDate_Issue = ((Number) objects[11]).longValue();
                }

                Long ClosingStock = 0L;
                if (objects[12] != null) {
                    ClosingStock = ((Number) objects[12]).longValue();
                }
//				Long actualOpening = openingStock + adjustedQuantity_FromDate;
                Long actualOpening = openingStock;
                Long actualDispensed = quantityDispanced;// - adjustedQuantity_ToDate;
                Long actualClosing = ClosingStock;
//				if (actualOpening == 0 || actualOpening == null) {
//					actualClosing = totalQuantityReceived - actualDispensed + adjustedQuantity_ToDate;
//				} else {
//					actualClosing = actualOpening - actualDispensed + adjustedQuantity_ToDate;
//					totalQuantityReceived = 0L;
//				}

                DailyStockSummary stockDetail = new DailyStockSummary();
                stockDetail.setSlNo(slNo++);
                stockDetail.setDate(entryReport.getStartDate());
                stockDetail.setFacilityName(facilityName);
                stockDetail.setItemName(itemName);
                stockDetail.setItemCategory(itemCategoryName);
                stockDetail.setOpeningStock(actualOpening);
                stockDetail.setQuantityReceived(totalQuantityReceived);
                stockDetail.setQuantityDispensed(actualDispensed);
                stockDetail.setClosingStock(actualClosing);
                stockDetail.setAdjustmentIssue(adjustedQuantity_ToDate_Issue);
                stockDetail.setAdjustmentReceipt(adjustedQuantity_ToDate_Receipt);
                list.add(stockDetail);
            }
        }

        return list.toString();
    }

    @Override
    public String getMonthlyReport(ItemStockEntryReport entryReport) {

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, entryReport.getYear());
        cal.set(Calendar.MONTH, entryReport.getMonth());
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date startDate = new Date(cal.getTimeInMillis());

        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);

        Date endDate = new Date(cal.getTimeInMillis());

        List<MonthlyReport> list = new ArrayList<MonthlyReport>();

        List<Objects[]> reports = null;
        reports = itemStockReportRepo.getDailyStockDetailReportByFacilityID(startDate, endDate,
                entryReport.getFacilityID());

        Long slNo = 1L;
        for (Object[] objects : reports) {
            if (objects != null && objects.length > 0) {

                String batchNo = (String) objects[3];
                Long totalQuantityReceived = 0L;
                if (objects[4] != null) {
                    totalQuantityReceived = ((Number) objects[4]).longValue();
                }
                Double unitCostPrice = 0.0;
                if (objects[5] != null) {
                    unitCostPrice = ((Number) objects[5]).doubleValue();
                }
                Date expiryDate = (Date) objects[6];
                Long openingStock = 0L;
                if (objects[10] != null) {
                    openingStock = ((Number) objects[10]).longValue();
                }
                Long adjustedQuantity_FromDate = 0L;
                if (objects[11] != null) {
                    adjustedQuantity_FromDate = ((Number) objects[11]).longValue();
                }
                Long quantityDispanced = 0L;
                if (objects[12] != null) {
                    quantityDispanced = ((Number) objects[12]).longValue();
                }
                String itemName = (String) objects[13];
                String facilityName = (String) objects[14];
                String itemCategoryName = (String) objects[15];
                Long adjustedQuantity_ToDate = 0L;
                if (objects[16] != null) {
                    adjustedQuantity_ToDate = ((Number) objects[16]).longValue();
                }
                Long adjustedQuantity_ToDate_Receipt = 0L;
                if (objects[17] != null) {
                    adjustedQuantity_ToDate_Receipt = ((Number) objects[17]).longValue();
                }
                Long adjustedQuantity_ToDate_Issue = 0L;
                if (objects[18] != null) {
                    adjustedQuantity_ToDate_Issue = ((Number) objects[18]).longValue();
                }
                Long ClosingStock = 0L;
                if (objects[19] != null) {
                    ClosingStock = ((Number) objects[19]).longValue();
                }
//				Long actualOpening = openingStock + adjustedQuantity_FromDate;
                Long actualOpening = openingStock;
                Long actualDispensed = quantityDispanced;// - adjustedQuantity_ToDate;
                Long actualClosing = ClosingStock;
//				Long actualClosing = 0L;
//				if (actualOpening == 0 || actualOpening == null) {
//					actualClosing = totalQuantityReceived - actualDispensed + adjustedQuantity_ToDate;
//				} else {
//					actualClosing = actualOpening - actualDispensed + adjustedQuantity_ToDate;
//					totalQuantityReceived = 0L;
//				}

                Timestamp itemEnteredDate = null;
                if (objects[9] != null) {
                    itemEnteredDate = (Timestamp) objects[9];

                }
                MonthlyReport stockDetail = new MonthlyReport();
                stockDetail.setSlNo(slNo++);
                stockDetail.setMonth(entryReport.getMonthName());
                stockDetail.setYear(entryReport.getYear());
                stockDetail.setFacilityName(facilityName);
                stockDetail.setItemName(itemName);
                stockDetail.setItemCategory(itemCategoryName);
                stockDetail.setBatchNo(batchNo);
                stockDetail.setUnitCostPrice(unitCostPrice);
                stockDetail.setExpiryDate(expiryDate);
                stockDetail.setOpeningStock(actualOpening);
                stockDetail.setQuantityReceived(totalQuantityReceived);
                stockDetail.setDispensedQuantity(actualDispensed);
                stockDetail.setClosingStock(actualClosing);

                stockDetail.setItemEnteredDate(itemEnteredDate);

                stockDetail.setAdjustmentIssue(adjustedQuantity_ToDate_Issue);
                stockDetail.setAdjustmentReceipt(adjustedQuantity_ToDate_Receipt);
                list.add(stockDetail);
            }
        }

        return list.toString();
    }

    @Override
    public String getYearlyReport(ItemStockEntryReport entryReport) {

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.YEAR, entryReport.getYear());
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date startDate = new Date(cal.getTimeInMillis());

        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 31);

        Date endDate = new Date(cal.getTimeInMillis());

        List<YearlyReport> list = new ArrayList<YearlyReport>();

        List<Objects[]> reports = null;

        reports = itemStockReportRepo.getDailyStockDetailReportByFacilityID(startDate, endDate,
                entryReport.getFacilityID());
        Long slNo = 1L;
        for (Object[] objects : reports) {
            if (objects != null && objects.length > 0) {

                String batchNo = (String) objects[3];
                Long totalQuantityReceived = 0L;
                if (objects[4] != null) {
                    totalQuantityReceived = ((Number) objects[4]).longValue();
                }
                Double unitCostPrice = 0.0;
                if (objects[5] != null) {
                    unitCostPrice = ((Number) objects[5]).doubleValue();
                }
                Date expiryDate = (Date) objects[6];
                Long openingStock = 0L;
                if (objects[10] != null) {
                    openingStock = ((Number) objects[10]).longValue();
                }
                Long adjustedQuantity_FromDate = 0L;
                if (objects[11] != null) {
                    adjustedQuantity_FromDate = ((Number) objects[11]).longValue();
                }
                Long quantityDispanced = 0L;
                if (objects[12] != null) {
                    quantityDispanced = ((Number) objects[12]).longValue();
                }
                String itemName = (String) objects[13];
                String facilityName = (String) objects[14];
                String itemCategoryName = (String) objects[15];
                Long adjustedQuantity_ToDate = 0L;
                if (objects[16] != null) {
                    adjustedQuantity_ToDate = ((Number) objects[16]).longValue();
                }
                Long adjustedQuantity_ToDate_Receipt = 0L;
                if (objects[17] != null) {
                    adjustedQuantity_ToDate_Receipt = ((Number) objects[17]).longValue();
                }
                Long adjustedQuantity_ToDate_Issue = 0L;
                if (objects[18] != null) {
                    adjustedQuantity_ToDate_Issue = ((Number) objects[18]).longValue();
                }
                Long ClosingStock = 0L;
                if (objects[19] != null) {
                    ClosingStock = ((Number) objects[19]).longValue();
                }
//				Long actualOpening = openingStock + adjustedQuantity_FromDate;
                Long actualOpening = openingStock;
                Long actualDispensed = quantityDispanced;// - adjustedQuantity_ToDate;
                Long actualClosing = ClosingStock;
//				if (actualOpening == 0 || actualOpening == null) {
//					actualClosing = totalQuantityReceived - actualDispensed + adjustedQuantity_ToDate;
//				} else {
//					actualClosing = actualOpening - actualDispensed + adjustedQuantity_ToDate;
//					totalQuantityReceived = 0L;
//				}

                YearlyReport stockDetail = new YearlyReport();
                stockDetail.setSlNo(slNo++);
                stockDetail.setYear(entryReport.getYear());
                stockDetail.setFacilityName(facilityName);
                stockDetail.setItemName(itemName);
                stockDetail.setItemCategory(itemCategoryName);
                stockDetail.setBatchNo(batchNo);
                stockDetail.setUnitCostPrice(unitCostPrice);
                stockDetail.setExpiryDate(expiryDate);
                stockDetail.setOpeningStock(actualOpening);
                stockDetail.setQuantityReceived(totalQuantityReceived);
                stockDetail.setDispensedQuantity(actualDispensed);
                stockDetail.setClosingStock(actualClosing);
                stockDetail.setAdjustmentIssue(adjustedQuantity_ToDate_Issue);
                stockDetail.setAdjustmentReceipt(adjustedQuantity_ToDate_Receipt);
                list.add(stockDetail);
            }
        }

        return list.toString();
    }

    @Override
    public String getShortExpiryReport(ItemStockEntryReport entryReport) {

        List<Object[]> list = new ArrayList<Object[]>();
        List<ExpiryReport> reportList = new ArrayList<ExpiryReport>();

        Timestamp startDate = null;
        Timestamp endDate = null;
        Calendar cal = Calendar.getInstance();

        startDate = new Timestamp(cal.getTimeInMillis());

        cal.add(Calendar.DATE, 90);
        endDate = new Timestamp(cal.getTimeInMillis());

        Date startExpiry = new Date(startDate.getTime());
        Date endExpiry = new Date(endDate.getTime());

        if (entryReport.getFacilityID() != null) {
            list = itemStockReportRepo.getShortExpiryReportByFacilityID(startExpiry, entryReport.getFacilityID());
        } else {
            list = itemStockReportRepo.getShortExpiryReport(startExpiry);
        }

        Long slNo = 1L;

        for (Object[] object : list) {

            if (object != null) {
                ExpiryReport report = new ExpiryReport(object[0] != null ? object[0].toString() : null,
                        object[1] != null ? object[1].toString() : null,
                        object[2] != null ? object[2].toString() : null,
                        object[3] != null ? object[3].toString() : null,
                        object[4] != null ? ((Double) object[4]).doubleValue() : null,
                        (Date) (object[5] != null ? object[5] : null),
                        (Integer) (object[6] != null ? object[6] : null));

                report.setSlNo(slNo++);
                reportList.add(report);
            }
        }

        return reportList.toString();
    }

    @Override
    public String getTransitReport(ItemStockEntryReport entryReport) {

        List<TransitReport> list = new ArrayList<TransitReport>();

        List<Objects[]> reports = null;

        if (entryReport.getFacilityID() != null) {
            reports = itemStockReportRepo.getTransitReportByFacilityID(entryReport.getStartDate(),
                    entryReport.getEndDate(), entryReport.getFacilityID());
        } else {
            reports = itemStockReportRepo.getTransitReport(entryReport.getStartDate(), entryReport.getEndDate());
        }

        Long slNo = 1L;
        for (Object[] objects : reports) {
            if (objects != null && objects.length > 0) {
                list.add(new TransitReport(slNo++, (String) objects[0], (String) objects[1],
                        objects[2] != null ? ((Number) objects[2]).doubleValue() : null, (Date) objects[3],
                        (String) objects[4], (String) objects[5], (Timestamp) objects[6], (Timestamp) objects[7]));
            }

        }

        return list.toString();
    }

}
