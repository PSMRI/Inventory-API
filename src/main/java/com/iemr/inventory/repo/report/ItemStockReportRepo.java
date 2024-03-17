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
package com.iemr.inventory.repo.report;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
public interface ItemStockReportRepo extends CrudRepository<ItemStockEntryReport, Long> {

    @Query("Select entryReport from ItemStockEntryReport entryReport "
            + "where entryReport.createdDate >= :startDate and entryReport.createdDate <= :endDate")
    List<ItemStockEntryReport> getItemStockEntryReport(@Param("startDate") Timestamp startDate,
                                                       @Param("endDate") Timestamp endDate);

    @Query("Select entryReport from ItemStockEntryReport entryReport "
            + "where entryReport.createdDate >= :startDate and entryReport.createdDate <= :endDate and entryReport.facilityID = :facilityID")
    List<ItemStockEntryReport> getItemStockEntryReportByFacilityID(@Param("startDate") Timestamp startDate,
                                                                   @Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

    @Query("Select distinct entryReport.facilityName, entryReport.itemName, entryReport.itemCategoryName, entryReport.batchNo, entryReport.unitCostPrice, entryReport.expiryDate, entryReport.quantityInHand  from ItemStockEntryReport entryReport "
            + "where entryReport.expiryDate >= :startDate and entryReport.expiryDate <= :endDate order by entryReport.expiryDate asc")
    List<Object[]> getExpiryReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("Select distinct entryReport.facilityName, entryReport.itemName, entryReport.itemCategoryName, entryReport.batchNo, entryReport.unitCostPrice, entryReport.expiryDate, entryReport.quantityInHand from ItemStockEntryReport entryReport "
            + "where entryReport.expiryDate >= :startDate and entryReport.expiryDate <= :endDate and entryReport.facilityID = :facilityID order by entryReport.expiryDate asc")
    List<Object[]> getExpiryReportByFacilityID(@Param("startDate") Date startDate,
                                               @Param("endDate") Date endDate, @Param("facilityID") Integer facilityID);

    @Query(value = "Select ISEx.*, db.BeneficiaryID, concat(IFNULL(db.Title,''),' ', IFNULL(db.FirstName,''),' ', "
            + "IFNULL(db.MiddleName,''),' ', IFNULL(db.LastName,'')) Name from db_reporting.fact_itemstockexit ISEx left join db_iemr.t_patientissue PI "
            + "on ISEx.ExitTypeID = PI.VanSerialNo and ISEx.VanID = PI.VanID "
            + "and ISEx.ExitType = 'T_PatientIssue' "
            + "left join db_reporting.dim_beneficiary db on db.beneficiaryregid=PI.beneficiaryregid "
            + "where ISEx.createdDate >= :startDate and ISEx.createdDate <= :endDate", nativeQuery = true)
    List<Object[]> getItemStockExitReport(@Param("startDate") Timestamp startDate,
                                          @Param("endDate") Timestamp endDate);

    @Query(value = "Select ISEx.*, db.BeneficiaryID, concat(IFNULL(db.Title,''),' ', IFNULL(db.FirstName,''),' ', "
            + "IFNULL(db.MiddleName,''),' ', IFNULL(db.LastName,'')) Name from db_reporting.fact_itemstockexit ISEx left join   db_iemr.t_patientissue PI "
            + "on ISEx.ExitTypeID = PI.VanSerialNo and ISEx.VanID = PI.VanID "
            + "and ISEx.ExitType = 'T_PatientIssue' "
            + "left join db_reporting.dim_beneficiary db on db.beneficiaryregid=PI.beneficiaryregid "
            + "where ISEx.createdDate >= :startDate and ISEx.createdDate <= :endDate and ISEx.facilityID = :facilityID", nativeQuery = true)
    List<Object[]> getItemStockExitReportByFacilityID(@Param("startDate") Timestamp startDate,
                                                      @Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

    @Query("Select exitReport from PatientIssueExitReport exitReport "
            + "where exitReport.createdDate >= :startDate and exitReport.createdDate <= :endDate")
    List<PatientIssueExitReport> getPatientIssueExitReport(@Param("startDate") Timestamp startDate,
                                                           @Param("endDate") Timestamp endDate);

    @Query("Select exitReport from PatientIssueExitReport exitReport "
            + "where exitReport.createdDate >= :startDate and exitReport.createdDate <= :endDate and exitReport.facilityID = :facilityID")
    List<PatientIssueExitReport> getPatientIssueExitReportByFacilityID(@Param("startDate") Timestamp startDate,
                                                                       @Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

    @Query(value = "call PR_StockDetail(:startDate, :endDate)", nativeQuery = true)
    List<Objects[]> getDailyStockDetailReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "call PR_StockDetail(:startDate, :endDate, :facilityID)", nativeQuery = true)
    List<Objects[]> getDailyStockDetailReportByFacilityID(@Param("startDate") Date startDate,
                                                          @Param("endDate") Date endDate, @Param("facilityID") Integer facilityID);

    @Query(value = "call PR_StockSummary(:startDate, :endDate)", nativeQuery = true)
    List<Objects[]> getDailyStockSummaryReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "call PR_StockSummary(:startDate, :endDate, :facilityID)", nativeQuery = true)
    List<Objects[]> getDailyStockSummaryReportByFacilityID(@Param("startDate") Date startDate,
                                                           @Param("endDate") Date endDate, @Param("facilityID") Integer facilityID);

    @Query(value = "Select distinct entryReport.facilityName, entryReport.itemName, entryReport.itemCategoryName, entryReport.batchNo, entryReport.unitCostPrice, entryReport.expiryDate, entryReport.quantityInHand from ItemStockEntryReport entryReport "
            + "where entryReport.expiryDate >= :startDate and entryReport.expiryDate <= adddate(:startDate,entryReport.alertBeforeDays) order by entryReport.expiryDate asc", nativeQuery = true)
    List<Object[]> getShortExpiryReport(@Param("startDate") Date startDate);

    @Query(value = "Select distinct entryReport.facilityName, entryReport.itemName, entryReport.itemCategoryName, entryReport.batchNo, entryReport.unitCostPrice, entryReport.expiryDate, entryReport.quantityInHand from ItemStockEntryReport entryReport "
            + "where entryReport.expiryDate >= :startDate and entryReport.expiryDate <= adddate(:startDate,entryReport.alertBeforeDays) and entryReport.facilityID = :facilityID order by entryReport.expiryDate asc", nativeQuery = true)
    List<Object[]> getShortExpiryReportByFacilityID(@Param("startDate") Date startDate,
                                                    @Param("facilityID") Integer facilityID);

    @Query(nativeQuery = true, value = "Select indentissue.ItemName, indentissue.BatchNo, indentissue.UnitCostPrice, indentissue.ExpiryDate,"
            + "facility.FacilityName, indent.FromFacilityName, indent.OrderDate, indentissue.IssueDate "
            + "from t_indentissue  indentissue "
            + "inner join t_indent indent on indentissue.IndentID=indent.VanSerialNo and indentissue.ToFacilityID=indent.FromFacilityID "
            + "inner join m_facility facility on facility.FacilityID=indentissue.FromFacilityID "
            + "where indentissue.CreatedDate >= :startDate and indentissue.CreatedDate <= :endDate and "
            + "indent.Status='Issued' " + "order by indentissue.CreatedDate desc")
    List<Objects[]> getTransitReport(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    @Query(nativeQuery = true, value = "Select indentissue.ItemName, indentissue.BatchNo, indentissue.UnitCostPrice, indentissue.ExpiryDate,"
            + "facility.FacilityName, indent.FromFacilityName, indent.OrderDate, indentissue.IssueDate "
            + "from t_indentissue  indentissue "
            + "inner join t_indent indent on indentissue.IndentID=indent.VanSerialNo and indentissue.ToFacilityID=indent.FromFacilityID "
            + "inner join m_facility facility on facility.FacilityID=indentissue.FromFacilityID "
            + "where indentissue.CreatedDate >= :startDate and indentissue.CreatedDate <= :endDate and indentissue.ToFacilityID=:facilityID and "
            + "indent.Status='Issued' " + "order by indentissue.CreatedDate desc")
    List<Objects[]> getTransitReportByFacilityID(@Param("startDate") Timestamp startDate,
                                                 @Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

}
