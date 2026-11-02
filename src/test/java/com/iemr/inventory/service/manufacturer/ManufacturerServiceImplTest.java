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
package com.iemr.inventory.service.manufacturer;

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

import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.repo.manufacturer.ManufacturerRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("ManufacturerServiceImpl Test Suite")
class ManufacturerServiceImplTest {

	@Mock
	private ManufacturerRepo manufacturerRepo;

	@InjectMocks
	private ManufacturerServiceImpl service;

	private static M_Manufacturer row(Integer id) {
		M_Manufacturer row = new M_Manufacturer();
		row.setManufacturerID(id);
		return row;
	}

	@Test
	@DisplayName("createManufacturer should return the rows the repository saved")
	void createManufacturer_shouldReturnSavedRows() {
		List<M_Manufacturer> input = List.of(row(1), row(2));
		ArrayList<M_Manufacturer> saved = new ArrayList<>(input);
		when(manufacturerRepo.saveAll(input)).thenReturn(saved);

		assertSame(saved, service.createManufacturer(input));
	}

	@Test
	@DisplayName("createManufacturer should return null when the repository saved nothing")
	void createManufacturer_shouldReturnNullWhenNothingSaved() {
		List<M_Manufacturer> input = List.of();
		when(manufacturerRepo.saveAll(input)).thenReturn(new ArrayList<M_Manufacturer>());

		assertNull(service.createManufacturer(input));
	}

	@Test
	@DisplayName("createManufacturer should hand back the rows the repository found for the provider service map")
	void createManufacturer_shouldReturnRowsForProviderServiceMap() {
		ArrayList<M_Manufacturer> found = new ArrayList<>(List.of(row(1)));
		when(manufacturerRepo.getManufacturerData(3)).thenReturn(found);

		assertSame(found, service.createManufacturer(3));
	}

	@Test
	@DisplayName("createManufacturer should return null when the provider service map has no rows")
	void createManufacturer_shouldReturnNullWhenNoRows() {
		when(manufacturerRepo.getManufacturerData(3)).thenReturn(new ArrayList<M_Manufacturer>());

		assertNull(service.createManufacturer(3));
	}

	@Test
	@DisplayName("editManufacturer should hand back the row the repository looked up by id")
	void editManufacturer_shouldReturnRowById() {
		M_Manufacturer found = row(1);
		when(manufacturerRepo.getEditData(1)).thenReturn(found);

		assertSame(found, service.editManufacturer(1));
	}

	@Test
	@DisplayName("editManufacturer should hand back null when no row carries that id")
	void editManufacturer_shouldReturnNullWhenRowMissing() {
		when(manufacturerRepo.getEditData(99)).thenReturn(null);

		assertNull(service.editManufacturer(99));
	}

	@Test
	@DisplayName("saveEditedData should persist the edited row and return what the repository stored")
	void saveEditedData_shouldPersistEditedRow() {
		M_Manufacturer edited = row(1);
		when(manufacturerRepo.save(edited)).thenReturn(edited);

		assertEquals(edited, service.saveEditedData(edited));
		verify(manufacturerRepo).save(edited);
	}
}
