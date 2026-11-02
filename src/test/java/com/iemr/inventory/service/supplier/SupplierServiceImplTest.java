/*
* AMRIT - Accessible Medical Records via Integrated Technologies
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
package com.iemr.inventory.service.supplier;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.supplier.M_Supplier;
import com.iemr.inventory.data.supplier.M_Supplieraddress;
import com.iemr.inventory.repo.supplier.M_SupplieraddressRepo;
import com.iemr.inventory.repo.supplier.SupplierRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("SupplierServiceImpl Test Suite")
class SupplierServiceImplTest {

	@Mock
	private SupplierRepo supplierRepo;
	@Mock
	private M_SupplieraddressRepo m_SupplieraddressRepo;

	@InjectMocks
	private SupplierServiceImpl service;

	private static M_Supplier supplier(Integer id) {
		M_Supplier supplier = new M_Supplier();
		supplier.setSupplierID(id);
		return supplier;
	}

	@Test
	@DisplayName("createSupplier should return the rows the repository saved")
	void createSupplier_shouldReturnSavedRows() {
		List<M_Supplier> input = List.of(supplier(1), supplier(2));
		ArrayList<M_Supplier> saved = new ArrayList<>(input);
		when(supplierRepo.saveAll(input)).thenReturn(saved);

		assertSame(saved, service.createSupplier(input));
	}

	@Test
	@DisplayName("createSupplier should return null when the repository saved nothing")
	void createSupplier_shouldReturnNullWhenNothingSaved() {
		List<M_Supplier> input = List.of();
		when(supplierRepo.saveAll(input)).thenReturn(new ArrayList<M_Supplier>());

		assertNull(service.createSupplier(input));
	}

	@Test
	@DisplayName("getSupplier should hand back the rows found for the provider service map")
	void getSupplier_shouldReturnRowsForProviderServiceMap() {
		ArrayList<M_Supplier> found = new ArrayList<>(List.of(supplier(1)));
		when(supplierRepo.getSupplierData(3)).thenReturn(found);

		assertSame(found, service.getSupplier(3));
	}

	@Test
	@DisplayName("getSupplier should return null when the provider service map has no suppliers")
	void getSupplier_shouldReturnNullWhenNoRows() {
		when(supplierRepo.getSupplierData(3)).thenReturn(new ArrayList<M_Supplier>());

		assertNull(service.getSupplier(3));
	}

	@Test
	@DisplayName("editSupplier should hand back the supplier the repository looked up by id")
	void editSupplier_shouldReturnRowById() {
		M_Supplier found = supplier(1);
		when(supplierRepo.geteditedData(1)).thenReturn(found);

		assertSame(found, service.editSupplier(1));
	}

	@Test
	@DisplayName("editSupplier should hand back null when no supplier carries that id")
	void editSupplier_shouldReturnNullWhenRowMissing() {
		when(supplierRepo.geteditedData(99)).thenReturn(null);

		assertNull(service.editSupplier(99));
	}

	@Test
	@DisplayName("saveEditedData should persist the edited supplier and return what the repository stored")
	void saveEditedData_shouldPersistEditedRow() {
		M_Supplier edited = supplier(1);
		when(supplierRepo.save(edited)).thenReturn(edited);

		assertSame(edited, service.saveEditedData(edited));
		verify(supplierRepo).save(edited);
	}

	@Test
	@DisplayName("createAddress should persist the supplier addresses through the address repository")
	void createAddress_shouldPersistAddresses() {
		List<M_Supplieraddress> input = List.of(new M_Supplieraddress());
		ArrayList<M_Supplieraddress> saved = new ArrayList<>(input);
		when(m_SupplieraddressRepo.saveAll(input)).thenReturn(saved);

		assertSame(saved, service.createAddress(input));
		verify(m_SupplieraddressRepo).saveAll(input);
	}
}
