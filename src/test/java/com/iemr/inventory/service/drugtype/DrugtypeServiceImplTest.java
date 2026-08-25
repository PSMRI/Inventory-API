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
package com.iemr.inventory.service.drugtype;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.iemr.inventory.data.drugtype.M_Drugtype;
import com.iemr.inventory.repo.drugtype.DrugtypeRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("DrugtypeServiceImpl Test Suite")
class DrugtypeServiceImplTest {

	@Mock
	private DrugtypeRepo drugtypeRepo;

	@InjectMocks
	private DrugtypeServiceImpl service;

	private static M_Drugtype row(Integer id) {
		M_Drugtype row = new M_Drugtype();
		row.setDrugTypeID(id);
		return row;
	}

	@Test
	@DisplayName("createDrugtypeData should return the rows the repository saved")
	void createDrugtypeData_shouldReturnSavedRows() {
		List<M_Drugtype> input = List.of(row(1), row(2));
		ArrayList<M_Drugtype> saved = new ArrayList<>(input);
		when(drugtypeRepo.saveAll(input)).thenReturn(saved);

		assertSame(saved, service.createDrugtypeData(input));
	}

	@Test
	@DisplayName("createDrugtypeData should return null when the repository saved nothing")
	void createDrugtypeData_shouldReturnNullWhenNothingSaved() {
		List<M_Drugtype> input = List.of();
		when(drugtypeRepo.saveAll(input)).thenReturn(new ArrayList<M_Drugtype>());

		assertNull(service.createDrugtypeData(input));
	}

	@Test
	@DisplayName("getDrugtypeData should hand back the rows the repository found for the provider service map")
	void getDrugtypeData_shouldReturnRowsForProviderServiceMap() {
		ArrayList<M_Drugtype> found = new ArrayList<>(List.of(row(1)));
		when(drugtypeRepo.getDrugtypeData(3)).thenReturn(found);

		assertSame(found, service.getDrugtypeData(3));
	}

	@Test
	@DisplayName("getDrugtypeData should return null when the provider service map has no rows")
	void getDrugtypeData_shouldReturnNullWhenNoRows() {
		when(drugtypeRepo.getDrugtypeData(3)).thenReturn(new ArrayList<M_Drugtype>());

		assertNull(service.getDrugtypeData(3));
	}

	@Test
	@DisplayName("editDrugtypeData should hand back the row the repository looked up by id")
	void editDrugtypeData_shouldReturnRowById() {
		M_Drugtype found = row(1);
		when(drugtypeRepo.geteditedData(1)).thenReturn(found);

		assertSame(found, service.editDrugtypeData(1));
	}

	@Test
	@DisplayName("editDrugtypeData should hand back null when no row carries that id")
	void editDrugtypeData_shouldReturnNullWhenRowMissing() {
		when(drugtypeRepo.geteditedData(99)).thenReturn(null);

		assertNull(service.editDrugtypeData(99));
	}

	@Test
	@DisplayName("saveeditDrugtype should persist the edited row and return what the repository stored")
	void saveeditDrugtype_shouldPersistEditedRow() {
		M_Drugtype edited = row(1);
		when(drugtypeRepo.save(edited)).thenReturn(edited);

		assertEquals(edited, service.saveeditDrugtype(edited));
		verify(drugtypeRepo).save(edited);
	}
}
