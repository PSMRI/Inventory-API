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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.iemr.inventory.data.report.ItemStockEntryReport;
import com.iemr.inventory.data.report.ItemStockExitReport;
import com.iemr.inventory.data.report.PatientIssueExitReport;

@Repository
public interface ItemStockReportRepo extends CrudRepository<ItemStockEntryReport, Long> {

	@Query(value = "Select * from db_reporting.fact_itemstockentry "
			+ "where CreatedDate >= :startDate and CreatedDate <= :endDate",nativeQuery = true)
	List<ItemStockEntryReport> getItemStockEntryReport(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate);

	@Query(value = "Select * from db_reporting.fact_itemstockentry "
			+ "where CreatedDate >= :startDate and CreatedDate <= :endDate and FacilityID = :facilityID",nativeQuery = true)
	List<ItemStockEntryReport> getItemStockEntryReportByFacilityID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

	@Query(value = "Select distinct FacilityName, ItemName, ItemCategoryName, Strength, BatchNo, UnitCostPrice, ExpiryDate, QuantityInHand  from db_reporting.fact_itemstockentry "
			+ "where ExpiryDate >= :startDate and ExpiryDate <= :endDate order by ExpiryDate asc",nativeQuery = true)
	List<Object[]> getExpiryReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "Select distinct FacilityName, ItemName, ItemCategoryName, Strength, BatchNo, UnitCostPrice, ExpiryDate, QuantityInHand from db_reporting.fact_itemstockentry "
			+ "where ExpiryDate >= :startDate and ExpiryDate <= :endDate and FacilityID = :facilityID order by ExpiryDate asc",nativeQuery = true)
	List<Object[]> getExpiryReportByFacilityID(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("facilityID") Integer facilityID);

	@Query(value="Select ISEx.*, db.BeneficiaryID, concat(IFNULL(db.Title,''),' ', IFNULL(db.FirstName,''),' ', " 
            + "IFNULL(db.MiddleName,''),' ', IFNULL(db.LastName,'')) Name from db_reporting.fact_itemstockexit ISEx left join db_iemr.t_patientissue PI "
            + "on ISEx.ExitTypeID = PI.VanSerialNo and ISEx.VanID = PI.VanID "
            + "and ISEx.ExitType = 'T_PatientIssue' "
            + "left join db_reporting.dim_beneficiary db on db.beneficiaryregid=PI.beneficiaryregid "
            + "where ISEx.createdDate >= :startDate and ISEx.createdDate <= :endDate",nativeQuery=true)
    List<Object[]> getItemStockExitReport(@Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate);

    @Query(value="Select ISEx.*, db.BeneficiaryID, concat(IFNULL(db.Title,''),' ', IFNULL(db.FirstName,''),' ', "
            + "IFNULL(db.MiddleName,''),' ', IFNULL(db.LastName,'')) Name from db_reporting.fact_itemstockexit ISEx left join   db_iemr.t_patientissue PI "
            + "on ISEx.ExitTypeID = PI.VanSerialNo and ISEx.VanID = PI.VanID "
            + "and ISEx.ExitType = 'T_PatientIssue' "
            + "left join db_reporting.dim_beneficiary db on db.beneficiaryregid=PI.beneficiaryregid "
            + "where ISEx.createdDate >= :startDate and ISEx.createdDate <= :endDate and ISEx.facilityID = :facilityID",nativeQuery=true)
    List<Object[]> getItemStockExitReportByFacilityID(@Param("startDate") Timestamp startDate,
            @Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

	@Query(value = "Select * from db_reporting.fact_patientissueexit "
			+ "where CreatedDate >= :startDate and CreatedDate <= :endDate",nativeQuery = true)
	List<Object[]> getPatientIssueExitReport(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate);

	@Query(value = "Select * from db_reporting.fact_patientissueexit "
			+ "where CreatedDate >= :startDate and CreatedDate <= :endDate and FacilityID = :facilityID",nativeQuery = true)
	List<Object[]> getPatientIssueExitReportByFacilityID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

	@Query(value = "call PR_StockDetail(:startDate, :endDate)", nativeQuery = true)
	List<Object[]> getDailyStockDetailReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "call PR_StockDetail(:startDate, :endDate, :facilityID)", nativeQuery = true)
	List<Object[]> getDailyStockDetailReportByFacilityID(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("facilityID") Integer facilityID);

	@Query(value = "call PR_StockSummary(:startDate, :endDate)", nativeQuery = true)
	List<Object[]> getDailyStockSummaryReport(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value = "call PR_StockSummary(:startDate, :endDate, :facilityID)", nativeQuery = true)
	List<Object[]> getDailyStockSummaryReportByFacilityID(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("facilityID") Integer facilityID);

	@Query(value="Select distinct FacilityName, ItemName, ItemCategoryName, Strength, BatchNo, UnitCostPrice, ExpiryDate, QuantityInHand from db_reporting.fact_itemstockentry "
			+ "where ExpiryDate >= :startDate and ExpiryDate <= adddate(:startDate,AlertBeforeDays) and Deleted=false order by ExpiryDate asc",nativeQuery=true)
	List<Object[]> getShortExpiryReport(@Param("startDate") Date startDate);

	@Query(value="Select distinct FacilityName, ItemName, ItemCategoryName, Strength, BatchNo, UnitCostPrice, ExpiryDate, QuantityInHand from db_reporting.fact_itemstockentry "
			+ "where ExpiryDate >= :startDate and ExpiryDate <= adddate(:startDate,AlertBeforeDays) and FacilityID = :facilityID and Deleted=false order by ExpiryDate asc",nativeQuery=true)
	List<Object[]> getShortExpiryReportByFacilityID(@Param("startDate") Date startDate,
			@Param("facilityID") Integer facilityID);

	@Query(nativeQuery = true, value = "Select indentissue.ItemName, indentissue.BatchNo, indentissue.UnitCostPrice, indentissue.ExpiryDate,"
			+ "facility.FacilityName, indent.FromFacilityName, indent.OrderDate, indentissue.IssueDate "
			+ "from t_indentissue  indentissue "
			+ "inner join t_indent indent on indentissue.IndentID=indent.VanSerialNo and indentissue.ToFacilityID=indent.FromFacilityID "
			+ "inner join m_facility facility on facility.FacilityID=indentissue.FromFacilityID "
			+ "where indentissue.CreatedDate >= :startDate and indentissue.CreatedDate <= :endDate and "
			+ "indent.Status='Issued' " + "order by indentissue.CreatedDate desc")
	List<Object[]> getTransitReport(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

	@Query(nativeQuery = true, value = "Select indentissue.ItemName, indentissue.BatchNo, indentissue.UnitCostPrice, indentissue.ExpiryDate,"
			+ "facility.FacilityName, indent.FromFacilityName, indent.OrderDate, indentissue.IssueDate "
			+ "from t_indentissue  indentissue "
			+ "inner join t_indent indent on indentissue.IndentID=indent.VanSerialNo and indentissue.ToFacilityID=indent.FromFacilityID "
			+ "inner join m_facility facility on facility.FacilityID=indentissue.FromFacilityID "
			+ "where indentissue.CreatedDate >= :startDate and indentissue.CreatedDate <= :endDate and indentissue.ToFacilityID=:facilityID and "
			+ "indent.Status='Issued' " + "order by indentissue.CreatedDate desc")
	List<Object[]> getTransitReportByFacilityID(@Param("startDate") Timestamp startDate,
			@Param("endDate") Timestamp endDate, @Param("facilityID") Integer facilityID);

}
