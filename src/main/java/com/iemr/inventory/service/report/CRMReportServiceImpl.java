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

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import java.math.BigInteger;
import java.math.BigDecimal;

@Service
public class CRMReportServiceImpl implements CRMReportService {

	@Autowired(required=false)
	ItemStockReportRepo itemStockReportRepo;

	@Autowired(required=false)
	InventoryReportMapper mapper;

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private StockDetailRow mapStockDetailRow(Object[] row) {
		if (row == null || row.length < 19) {
			throw new IllegalArgumentException("PR_StockDetail returned an unexpected number of columns");
		}

		StockDetailRow detail = new StockDetailRow();
		detail.facilityName = text(row[3]);
		detail.itemName = text(row[5]);
		detail.strength = text(row[6]);
		detail.uom = text(row[7]);
		detail.itemCategoryName = text(row[8]);
		detail.batchNo = text(row[9]);
		detail.unitCostPrice = decimal(row[10]);
		detail.expiryDate = sqlDate(row[11]);
		detail.quantityReceived = wholeNumber(row[12]);
		detail.openingStock = wholeNumber(row[13]);
		detail.dispensedQuantity = wholeNumber(row[14]);
		detail.adjustmentReceipt = wholeNumber(row[15]);
		detail.adjustmentIssue = wholeNumber(row[16]);
		detail.closingStock = wholeNumber(row[17]);
		detail.itemEnteredDate = sqlTimestamp(row[18]);
		return detail;
	}

	private String text(Object value) {
		return value == null ? null : value.toString();
	}

	private Long wholeNumber(Object value) {
		return value == null ? 0L : Long.valueOf(value.toString());
	}

	private Double decimal(Object value) {
		return value == null ? 0.0 : Double.valueOf(value.toString());
	}

	private Date sqlDate(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Date) {
			return (Date) value;
		}
		try {
			return new Date(new SimpleDateFormat("dd-MM-yyyy").parse(value.toString()).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Unable to parse PR_StockDetail date: " + value, e);
		}
	}

	private Timestamp sqlTimestamp(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Timestamp) {
			return (Timestamp) value;
		}
		try {
			return new Timestamp(new SimpleDateFormat("dd-MM-yyyy").parse(value.toString()).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Unable to parse PR_StockDetail timestamp: " + value, e);
		}
	}

	private static class StockDetailRow {
		private String facilityName;
		private String itemName;
		private String strength;
		private String uom;
		private String itemCategoryName;
		private String batchNo;
		private Double unitCostPrice;
		private Date expiryDate;
		private Long quantityReceived;
		private Long openingStock;
		private Long dispensedQuantity;
		private Long adjustmentReceipt;
		private Long adjustmentIssue;
		private Long closingStock;
		private Timestamp itemEnteredDate;
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
						object[4] != null ? object[4].toString() : null,
						object[5] != null ? Double.valueOf(object[5].toString()) : null,
						(Date) (object[6] != null ? object[6] : null),
						(Integer) (object[7] != null ? object[7] : null));

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

		List<Object[]> list = new ArrayList<>();
		List<PatientIssueExitReport> resp = new ArrayList<>();
		List<BenDrugIssueReport> reportList = new ArrayList<BenDrugIssueReport>();
		if (exitReport.getFacilityID() != null) {
			list = itemStockReportRepo.getPatientIssueExitReportByFacilityID(exitReport.getStartDate(),
					exitReport.getEndDate(), exitReport.getFacilityID());
		} else {
			list = itemStockReportRepo.getPatientIssueExitReport(exitReport.getStartDate(), exitReport.getEndDate());
		}
		resp = convertObjectArrayToPatientIssueExitReport(list);
		Long slNo = 1L;
		for (PatientIssueExitReport reportData : resp) {
			BenDrugIssueReport report = mapper.mapBenDrugIssueReport(reportData);
			report.setSlNo(slNo++);
			reportList.add(report);
		}
		return reportList.toString();
	}

	private List<PatientIssueExitReport> convertObjectArrayToPatientIssueExitReport(List<Object[]> list) {
		List<PatientIssueExitReport> resp = new ArrayList<>();
		for (Object[] obj : list) {
			if(obj != null && obj.length > 0) {
			PatientIssueExitReport patientIssueExitReport = new PatientIssueExitReport();
			patientIssueExitReport.setFactPatientIssueExitID(obj[0] != null ? Long.valueOf(obj[0].toString()) : null);
			patientIssueExitReport.setItemStockExitID(obj[1] != null ? Long.valueOf(obj[1].toString()) : null);
			patientIssueExitReport.setItemStockEntryID(obj[2] != null ? Long.valueOf(obj[2].toString()) : null);
			patientIssueExitReport.setItemID(obj[3] != null ? (Integer)obj[3] : null);	
			patientIssueExitReport.setItemName(String.valueOf(obj[4]));
			patientIssueExitReport.setItemCategoryName(String.valueOf(obj[7]));
			patientIssueExitReport.setBatchNo(String.valueOf(obj[9]));
			patientIssueExitReport.setStrength(String.valueOf(obj[8]));
			patientIssueExitReport.setQuantityGiven(obj[14] != null ? (Integer)obj[14] : null);	
			patientIssueExitReport.setExpiryDate(obj[13] != null ? (Date)obj[13] : null);
			patientIssueExitReport.setFacilityID(obj[22] != null ? (Integer)obj[22] : null);
			patientIssueExitReport.setPatientName(String.valueOf(obj[23]));	
			patientIssueExitReport.setAge(obj[24] != null ? (Integer)obj[24] : null);
			patientIssueExitReport.setGender(String.valueOf(obj[25]));
			patientIssueExitReport.setCreatedDate(Timestamp.valueOf(obj[35].toString()));
			//patientIssueExitReport.setStartDate(null);
			//patientIssueExitReport.setEndDate(null);
			resp.add(patientIssueExitReport);
			}
			
		}
		return resp;
	}

	@Override
	public String getDailyStockDetailsReport(ItemStockEntryReport entryReport) {

		Date todaysDate = new Date(entryReport.getStartDate().getTime());

		List<DailyStockDetails> list = new ArrayList<DailyStockDetails>();

		List<Object[]> reports = null;

		reports = itemStockReportRepo.getDailyStockDetailReportByFacilityID(todaysDate, todaysDate,
				entryReport.getFacilityID());

		Long slNo = 1L;
		for (Object[] objects : reports) {
			if (objects != null && objects.length > 0) {

				StockDetailRow detail = mapStockDetailRow(objects);
				DailyStockDetails stockDetail = new DailyStockDetails();
				stockDetail.setSlNo(slNo++);
				stockDetail.setDate(entryReport.getStartDate());
				stockDetail.setFacilityName(detail.facilityName);
				stockDetail.setItemName(detail.itemName);
				stockDetail.setItemCategory(detail.itemCategoryName);
				stockDetail.setStrength(detail.strength);
				stockDetail.setUom(detail.uom);
				stockDetail.setBatchNo(detail.batchNo);
				stockDetail.setUnitCostPrice(detail.unitCostPrice);
				stockDetail.setExpiryDate(detail.expiryDate);
				stockDetail.setOpeningStock(detail.openingStock);
				stockDetail.setQuantityReceived(detail.quantityReceived);
				stockDetail.setDispensedQuantity(detail.dispensedQuantity);
				stockDetail.setClosingStock(detail.closingStock);
				stockDetail.setItemEnteredDate(detail.itemEnteredDate);
				stockDetail.setAdjustmentIssue(detail.adjustmentIssue);
				stockDetail.setAdjustmentReceipt(detail.adjustmentReceipt);
				list.add(stockDetail);
			}
		}

		return list.toString();
	}

	@Override
	public String getDailyStockSummaryReport(ItemStockEntryReport entryReport) {

		Date todaysDate = new Date(entryReport.getStartDate().getTime());

		List<DailyStockSummary> list = new ArrayList<DailyStockSummary>();

		List<Object[]> reports = null;

		reports = itemStockReportRepo.getDailyStockSummaryReportByFacilityID(todaysDate, todaysDate,
				entryReport.getFacilityID());
		Long slNo = 1L;
		for (Object[] objects : reports) {
			if (objects != null && objects.length > 0) {

				Long totalQuantityReceived = 0L;
				if (objects[5] != null) {
					totalQuantityReceived = Long.valueOf(objects[5].toString());
				}
				Long openingStock = 0L;
				if (objects[6] != null) {
					openingStock = Long.valueOf(objects[6].toString());
				}
				Long adjustedQuantity_FromDate = 0L;
				if (objects[7] != null) {
					adjustedQuantity_FromDate = Long.valueOf(objects[7].toString());
				}
				Long quantityDispanced = 0L;
				if (objects[8] != null) {
					quantityDispanced = Long.valueOf(objects[8].toString());
				}
				String itemName = objects[2] != null ? objects[2].toString() : null;
				String facilityName = objects[3] != null ? objects[3].toString() : null;
				String itemCategoryName = objects[4] != null ? objects[4].toString() : null;
				Long adjustedQuantity_ToDate = 0L;
				if (objects[9] != null) {
					adjustedQuantity_ToDate = Long.valueOf(objects[9].toString());
				}
				Long adjustedQuantity_ToDate_Receipt = 0L;
				if (objects[10] != null) {
					adjustedQuantity_ToDate_Receipt = Long.valueOf(objects[10].toString());
				}
				Long adjustedQuantity_ToDate_Issue = 0L;
				if (objects[11] != null) {
					adjustedQuantity_ToDate_Issue = Long.valueOf(objects[11].toString());
				}

				Long ClosingStock = 0L;
				if (objects[12] != null) {
					ClosingStock = Long.valueOf(objects[12].toString());
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

		List<Object[]> reports = null;
		reports = itemStockReportRepo.getDailyStockDetailReportByFacilityID(startDate, endDate,
				entryReport.getFacilityID());

		Long slNo = 1L;
		for (Object[] objects : reports) {
			if (objects != null && objects.length > 0) {

				StockDetailRow detail = mapStockDetailRow(objects);
				MonthlyReport stockDetail = new MonthlyReport();
				stockDetail.setSlNo(slNo++);
				stockDetail.setMonth(entryReport.getMonthName());
				stockDetail.setYear(entryReport.getYear());
				stockDetail.setFacilityName(detail.facilityName);
				stockDetail.setItemName(detail.itemName);
				stockDetail.setItemCategory(detail.itemCategoryName);
				stockDetail.setStrength(detail.strength);
				stockDetail.setUom(detail.uom);
				stockDetail.setBatchNo(detail.batchNo);
				stockDetail.setUnitCostPrice(detail.unitCostPrice);
				stockDetail.setExpiryDate(detail.expiryDate);
				stockDetail.setOpeningStock(detail.openingStock);
				stockDetail.setQuantityReceived(detail.quantityReceived);
				stockDetail.setDispensedQuantity(detail.dispensedQuantity);
				stockDetail.setClosingStock(detail.closingStock);
				stockDetail.setItemEnteredDate(detail.itemEnteredDate);
				stockDetail.setAdjustmentIssue(detail.adjustmentIssue);
				stockDetail.setAdjustmentReceipt(detail.adjustmentReceipt);
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

		List<Object[]> reports = null;

		reports = itemStockReportRepo.getDailyStockDetailReportByFacilityID(startDate, endDate,
				entryReport.getFacilityID());
		Long slNo = 1L;
		for (Object[] objects : reports) {
			if (objects != null && objects.length > 0) {

				StockDetailRow detail = mapStockDetailRow(objects);

				YearlyReport stockDetail = new YearlyReport();
				stockDetail.setSlNo(slNo++);
				stockDetail.setYear(entryReport.getYear());
				stockDetail.setFacilityName(detail.facilityName);
				stockDetail.setItemName(detail.itemName);
				stockDetail.setItemCategory(detail.itemCategoryName);
				stockDetail.setStrength(detail.strength);
				stockDetail.setUom(detail.uom);
				stockDetail.setBatchNo(detail.batchNo);
				stockDetail.setUnitCostPrice(detail.unitCostPrice);
				stockDetail.setExpiryDate(detail.expiryDate);
				stockDetail.setOpeningStock(detail.openingStock);
				stockDetail.setQuantityReceived(detail.quantityReceived);
				stockDetail.setDispensedQuantity(detail.dispensedQuantity);
				stockDetail.setClosingStock(detail.closingStock);
				stockDetail.setAdjustmentIssue(detail.adjustmentIssue);
				stockDetail.setAdjustmentReceipt(detail.adjustmentReceipt);
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

		logger.info("Short Expiry Report Start Date: " + startExpiry + " End Date: " + endExpiry);

		logger.info("Short Expiry Report List Size: " + list.size());

		Long slNo = 1L;

		for (Object[] object : list) {

			if (object != null) {
				ExpiryReport report = new ExpiryReport(object[0] != null ? object[0].toString() : null,
						object[1] != null ? object[1].toString() : null,
						object[2] != null ? object[2].toString() : null,
						object[3] != null ? object[3].toString() : null,
						object[4] != null ? object[4].toString() : null,
						object[5] != null ? ((BigDecimal) object[5]).doubleValue() : null,
						(Date) (object[6] != null ? object[6] : null),
						(Integer) (object[7] != null ? object[7] : null));

				report.setSlNo(slNo++);
				reportList.add(report);
			}
		}

		return reportList.toString();
	}

	@Override
	public String getTransitReport(ItemStockEntryReport entryReport) {

		List<TransitReport> list = new ArrayList<TransitReport>();

		List<Object[]> reports = null;

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
