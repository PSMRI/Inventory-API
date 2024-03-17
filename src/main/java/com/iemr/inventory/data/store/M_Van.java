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
package com.iemr.inventory.data.store;

import com.google.gson.annotations.Expose;
import com.iemr.inventory.utils.mapper.OutputMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Table(name = "m_van")
@Data
public class M_Van {

    //	@Expose
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(updatable = false, insertable = false, name = "VanTypeID")
//	private M_VanType m_vanType;
    @Expose
    @Transient
    String vanType;
    //	@Expose
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(updatable = false, insertable = false, name = "countryID")
//	private M_Country m_country;
    @Expose
    @Transient
    String countryName;
    //	@Expose
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(updatable = false, insertable = false, name = "stateID")
//	private State state;
    @Expose
    @Transient
    String stateName;
    //	@Expose
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(updatable = false, insertable = false, name = "parkingPlaceID")
//	private M_Parkingplace m_parkingplace;
    @Expose
    @Transient
    String parkingPlaceName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    @Column(name = "VanID")
    private Integer vanID;
    @Expose
    @Column(name = "VanName")
    private String vanName;
//	@Expose
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(updatable = false, insertable = false, name = "ProviderServiceMapID")
//	private M_ProviderServiceMapping m_providerServiceMapping;
    @Expose
    @Column(name = "VehicalNo")
    private String vehicalNo;
    @Expose
    @Column(name = "VanTypeID")
    private Integer vanTypeID;
    @Expose
    @Column(name = "ProviderServiceMapID")
    private Integer providerServiceMapID;
    @Expose
    @Transient
    private Integer serviceProviderID;
    @Expose
    @Column(name = "CountryID")
    private Integer countryID;

//	@Expose
//	@Column(name="DistrictID")
//	private Integer districtID;
//	@Expose
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(updatable = false, insertable = false, name = "districtID")
//	private M_District m_district;
//	@Expose
//	@Transient
//	String districtName;
    @Expose
    @Column(name = "StateID")
    private Integer stateID;
    @Expose
    @Column(name = "ParkingPlaceID")
    private Integer parkingPlaceID;
    @Expose
    @Column(name = "IsFacility")
    private Boolean isFacility;


    @Expose
    @Transient
    private Integer districtBlockID;

    @Expose
    @Column(name = "FacilityID")
    private Integer facilityID;


    @Expose
    @Column(name = "Deleted", insertable = false, updatable = true)
    private Boolean deleted;
    @Expose
    @Column(name = "Processed", insertable = false, updatable = true)
    private String processed;
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

    public M_Van() {
        super();
        // TODO Auto-generated constructor stub
    }

//	public M_Van(Integer vanID, String vanName, String vehicalNo, Integer vanTypeID, String vanType, Boolean deleted,
//			Integer providerServiceMapID, Integer countryID, String countryName, Integer stateID, String stateName,
//			Integer districtID, String districtName, Integer parkingPlaceID, String parkingPlaceName,Integer districtBlockID) {
//		super();
//		this.vanID = vanID;
//		this.vanName = vanName;
//		this.vehicalNo = vehicalNo;
//		this.vanTypeID = vanTypeID;
//		this.vanType = vanType;
//		this.providerServiceMapID = providerServiceMapID;
//		this.countryID = countryID;
//		this.countryName = countryName;
//		this.stateID = stateID;
//		this.stateName = stateName;
////		this.districtID = districtID;
////		this.districtName = districtName;
//		this.parkingPlaceID = parkingPlaceID;
//		this.parkingPlaceName = parkingPlaceName;
//		this.deleted = deleted;
//		this.districtBlockID=districtBlockID;
//	}

    public M_Van(Integer vanID, String vanName, String vehicalNo, Integer vanTypeID, String vanType, Boolean deleted,
                 Integer providerServiceMapID, Integer countryID, String countryName, Integer stateID, String stateName,
                 Integer parkingPlaceID, String parkingPlaceName, Integer districtBlockID) {
        super();
        this.vanID = vanID;
        this.vanName = vanName;
        this.vehicalNo = vehicalNo;
        this.vanTypeID = vanTypeID;
        this.vanType = vanType;
        this.providerServiceMapID = providerServiceMapID;
        this.countryID = countryID;
        this.countryName = countryName;
        this.stateID = stateID;
        this.stateName = stateName;
        this.parkingPlaceID = parkingPlaceID;
        this.parkingPlaceName = parkingPlaceName;
        this.deleted = deleted;
        this.districtBlockID = districtBlockID;
    }

    @Override
    public String toString() {
        return outputMapper.gson().toJson(this);
    }


}
