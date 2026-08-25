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
package com.iemr.inventory.service.uom;

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

import com.iemr.inventory.data.uom.M_Uom;
import com.iemr.inventory.repo.uom.UomRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("UomServiceImpl Test Suite")
class UomServiceImplTest {

	@Mock
	private UomRepo uomRepo;

	@InjectMocks
	private UomServiceImpl service;

	private static M_Uom row(Integer id) {
		M_Uom row = new M_Uom();
		row.setuOMID(id);
		return row;
	}

	@Test
	@DisplayName("createDrugtypeData should return the rows the repository saved")
	void createDrugtypeData_shouldReturnSavedRows() {
		List<M_Uom> input = List.of(row(1), row(2));
		ArrayList<M_Uom> saved = new ArrayList<>(input);
		when(uomRepo.saveAll(input)).thenReturn(saved);

		assertSame(saved, service.createDrugtypeData(input));
	}

	@Test
	@DisplayName("createDrugtypeData should return null when the repository saved nothing")
	void createDrugtypeData_shouldReturnNullWhenNothingSaved() {
		List<M_Uom> input = List.of();
		when(uomRepo.saveAll(input)).thenReturn(new ArrayList<M_Uom>());

		assertNull(service.createDrugtypeData(input));
	}

	@Test
	@DisplayName("createDrugtypeData should hand back the rows the repository found for the provider service map")
	void createDrugtypeData_shouldReturnRowsForProviderServiceMap() {
		ArrayList<M_Uom> found = new ArrayList<>(List.of(row(1)));
		when(uomRepo.getUom(3)).thenReturn(found);

		assertSame(found, service.createDrugtypeData(3));
	}

	@Test
	@DisplayName("createDrugtypeData should return null when the provider service map has no rows")
	void createDrugtypeData_shouldReturnNullWhenNoRows() {
		when(uomRepo.getUom(3)).thenReturn(new ArrayList<M_Uom>());

		assertNull(service.createDrugtypeData(3));
	}

	@Test
	@DisplayName("editDrugtypeData should hand back the row the repository looked up by id")
	void editDrugtypeData_shouldReturnRowById() {
		M_Uom found = row(1);
		when(uomRepo.geteditedData(1)).thenReturn(found);

		assertSame(found, service.editDrugtypeData(1));
	}

	@Test
	@DisplayName("editDrugtypeData should hand back null when no row carries that id")
	void editDrugtypeData_shouldReturnNullWhenRowMissing() {
		when(uomRepo.geteditedData(99)).thenReturn(null);

		assertNull(service.editDrugtypeData(99));
	}

	@Test
	@DisplayName("saveeditedData should persist the edited row and return what the repository stored")
	void saveeditedData_shouldPersistEditedRow() {
		M_Uom edited = row(1);
		when(uomRepo.save(edited)).thenReturn(edited);

		assertEquals(edited, service.saveeditedData(edited));
		verify(uomRepo).save(edited);
	}
}
