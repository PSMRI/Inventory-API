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
package com.iemr.inventory.controller.Supplier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iemr.inventory.data.supplier.M_Supplier;
import com.iemr.inventory.data.supplier.M_Supplieraddress;
import com.iemr.inventory.service.supplier.SupplierInter;
import com.iemr.inventory.utils.mapper.InputMapper;
import com.iemr.inventory.utils.response.OutputResponse;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class SupplierMasterController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private SupplierInter supplierInter;

    @CrossOrigin()
    @Operation(summary = "Create supplier")
    @PostMapping(value = "/createSupplier", headers = "Authorization", produces = {
            "application/json"})
    public String createSupplier(@RequestBody String createSupplier) {

        OutputResponse response = new OutputResponse();

        try {

            M_Supplier[] Supplier = InputMapper.gson().fromJson(createSupplier, M_Supplier[].class);
            List<M_Supplier> SupplierData = Arrays.asList(Supplier);

            M_Supplieraddress[] Supplieraddress = InputMapper.gson().fromJson(createSupplier,
                    M_Supplieraddress[].class);
            List<M_Supplieraddress> SupplierAddressData = Arrays.asList(Supplieraddress);

            ArrayList<M_Supplier> createdData = supplierInter.createSupplier(SupplierData);

            M_Supplieraddress resDataMap1 = null;
            List<M_Supplieraddress> resList1 = new ArrayList<M_Supplieraddress>();
            int x = 0;
            for (M_Supplier ms : createdData) {
                resDataMap1 = new M_Supplieraddress();
                resDataMap1.setSupplierID(createdData.get(x).getSupplierID());
                resDataMap1.setAddressLine1(SupplierAddressData.get(x).getAddressLine1());
                resDataMap1.setAddressLine2(SupplierAddressData.get(x).getAddressLine2());
                resDataMap1.setDistrict(SupplierAddressData.get(x).getDistrict());
                resDataMap1.setState(SupplierAddressData.get(x).getState());
                resDataMap1.setCountry(SupplierAddressData.get(x).getCountry());
                resDataMap1.setPinCode(SupplierAddressData.get(x).getPinCode());
                resDataMap1.setProviderServiceMapID(SupplierAddressData.get(x).getProviderServiceMapID());
                resDataMap1.setCreatedBy(SupplierAddressData.get(x).getCreatedBy());
                resList1.add(resDataMap1);

            }
            x++;
            ArrayList<M_Supplieraddress> storedData = supplierInter.createAddress(resList1);

            response.setResponse(createdData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Get supplier")
    @GetMapping(value = "/getSupplier", headers = "Authorization", produces = {
            "application/json"})
    public String getSupplier(@RequestBody String getSupplier) {

        OutputResponse response = new OutputResponse();

        try {

            M_Supplier Supplier = InputMapper.gson().fromJson(getSupplier, M_Supplier.class);

            ArrayList<M_Supplier> getData = supplierInter.getSupplier(Supplier.getProviderServiceMapID());

            response.setResponse(getData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

    @CrossOrigin()
    @Operation(summary = "Edit supplier")
    @PostMapping(value = "/editSupplier", headers = "Authorization", produces = {
            "application/json"})
    public String editSupplier(@RequestBody String editSupplier) {

        OutputResponse response = new OutputResponse();

        try {

            M_Supplier Supplier = InputMapper.gson().fromJson(editSupplier, M_Supplier.class);

            M_Supplier editData = supplierInter.editSupplier(Supplier.getSupplierID());
            editData.setSupplierName(Supplier.getSupplierName());
            editData.setSupplierDesc(Supplier.getSupplierDesc());
            editData.setSupplierCode(Supplier.getSupplierCode());
            editData.setStatus(Supplier.getStatus());
            editData.setContactPerson(Supplier.getContactPerson());
            editData.setDrugLicenseNo(Supplier.getDrugLicenseNo());
            editData.setcST_GST_No(Supplier.getcST_GST_No());
            editData.settIN_No(Supplier.gettIN_No());
            editData.setEmail(Supplier.getEmail());
            editData.setPhoneNo1(Supplier.getPhoneNo1());
            editData.setPhoneNo2(Supplier.getPhoneNo2());
            editData.setModifiedBy(Supplier.getModifiedBy());

            M_Supplier editedData = supplierInter.saveEditedData(editData);

            response.setResponse(editedData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();
    }

    @CrossOrigin()
    @Operation(summary = "Delete supplier")
    @DeleteMapping(value = "/deleteSupplier", headers = "Authorization", produces = {
            "application/json"})
    public String deleteSupplier(@RequestBody String deleteSupplier) {

        OutputResponse response = new OutputResponse();

        try {

            M_Supplier Supplier = InputMapper.gson().fromJson(deleteSupplier, M_Supplier.class);

            M_Supplier editData = supplierInter.editSupplier(Supplier.getSupplierID());

            editData.setDeleted(Supplier.getDeleted());

            M_Supplier deletedData = supplierInter.saveEditedData(editData);

            response.setResponse(deletedData.toString());

        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setError(e);

        }
        return response.toString();

    }

}
