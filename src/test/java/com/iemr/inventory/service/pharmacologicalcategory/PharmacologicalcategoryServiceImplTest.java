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
package com.iemr.inventory.service.pharmacologicalcategory;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.iemr.inventory.data.pharmacologicalcategory.M_Pharmacologicalcategory;
import com.iemr.inventory.repo.pharmacologicalcategory.PharmacologicalcategoryRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("PharmacologicalcategoryServiceImpl Test Suite")
class PharmacologicalcategoryServiceImplTest {

	@Mock
	private PharmacologicalcategoryRepo pharmacologicalcategoryRepo;

	@InjectMocks
	private PharmacologicalcategoryServiceImpl service;

	private static M_Pharmacologicalcategory row(Integer id) {
		M_Pharmacologicalcategory row = new M_Pharmacologicalcategory();
		row.setPharmCategoryID(id);
		return row;
	}

	@Test
	@DisplayName("createPharmacologicalcategory should return the rows the repository saved")
	void createPharmacologicalcategory_shouldReturnSavedRows() {
		List<M_Pharmacologicalcategory> input = List.of(row(1), row(2));
		ArrayList<M_Pharmacologicalcategory> saved = new ArrayList<>(input);
		when(pharmacologicalcategoryRepo.saveAll(input)).thenReturn(saved);

		assertSame(saved, service.createPharmacologicalcategory(input));
	}

	@Test
	@DisplayName("createPharmacologicalcategory should return null when the repository saved nothing")
	void createPharmacologicalcategory_shouldReturnNullWhenNothingSaved() {
		List<M_Pharmacologicalcategory> input = List.of();
		when(pharmacologicalcategoryRepo.saveAll(input)).thenReturn(new ArrayList<M_Pharmacologicalcategory>());

		assertNull(service.createPharmacologicalcategory(input));
	}

	@Test
	@DisplayName("getPharmacologicalcategory should hand back whatever the repository found, including an empty list")
	void getPharmacologicalcategory_shouldPassRepositoryResultThrough() {
		ArrayList<M_Pharmacologicalcategory> found = new ArrayList<>(List.of(row(1)));
		when(pharmacologicalcategoryRepo.getPhormacologicalData(3)).thenReturn(found);

		assertSame(found, service.getPharmacologicalcategory(3));
	}

	@Test
	@DisplayName("getPharmacologicalcategory should return the empty list rather than null when nothing matches")
	void getPharmacologicalcategory_shouldReturnEmptyListWhenNoRows() {
		when(pharmacologicalcategoryRepo.getPhormacologicalData(3))
				.thenReturn(new ArrayList<M_Pharmacologicalcategory>());

		assertTrue(service.getPharmacologicalcategory(3).isEmpty());
	}

	@Test
	@DisplayName("editPharmacologicalcategory should hand back the row the repository looked up by id")
	void editPharmacologicalcategory_shouldReturnRowById() {
		M_Pharmacologicalcategory found = row(1);
		when(pharmacologicalcategoryRepo.editPhamacologicalData(1)).thenReturn(found);

		assertSame(found, service.editPharmacologicalcategory(1));
	}

	@Test
	@DisplayName("editPharmacologicalcategory should hand back null when no row carries that id")
	void editPharmacologicalcategory_shouldReturnNullWhenRowMissing() {
		when(pharmacologicalcategoryRepo.editPhamacologicalData(99)).thenReturn(null);

		assertNull(service.editPharmacologicalcategory(99));
	}

	@Test
	@DisplayName("saveEditedPharData should persist the edited row and return what the repository stored")
	void saveEditedPharData_shouldPersistEditedRow() {
		M_Pharmacologicalcategory edited = row(1);
		when(pharmacologicalcategoryRepo.save(edited)).thenReturn(edited);

		assertSame(edited, service.saveEditedPharData(edited));
		verify(pharmacologicalcategoryRepo).save(edited);
	}
}
