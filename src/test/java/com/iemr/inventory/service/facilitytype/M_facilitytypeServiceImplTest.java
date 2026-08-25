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
package com.iemr.inventory.service.facilitytype;

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

import com.iemr.inventory.data.facilitytype.M_facilitytype;
import com.iemr.inventory.repository.facilitytype.M_facilitytypeRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("M_facilitytypeServiceImpl Test Suite")
class M_facilitytypeServiceImplTest {

	@Mock
	private M_facilitytypeRepo m_facilitytypeRepo;

	@InjectMocks
	private M_facilitytypeServiceImpl service;

	private static M_facilitytype row(Integer id) {
		M_facilitytype row = new M_facilitytype();
		row.setFacilityTypeID(id);
		return row;
	}

	@Test
	@DisplayName("getAllFicilityData should hand back the rows the repository found for the provider service map")
	void getAllFicilityData_shouldReturnRowsForProviderServiceMap() {
		ArrayList<M_facilitytype> found = new ArrayList<>(List.of(row(1)));
		when(m_facilitytypeRepo.getAllFicilityData(3)).thenReturn(found);

		assertSame(found, service.getAllFicilityData(3));
	}

	@Test
	@DisplayName("getAllFicilityData should pass an empty result through untouched")
	void getAllFicilityData_shouldPassEmptyResultThrough() {
		when(m_facilitytypeRepo.getAllFicilityData(3)).thenReturn(new ArrayList<M_facilitytype>());

		assertTrue(service.getAllFicilityData(3).isEmpty());
	}

	@Test
	@DisplayName("addAllFicilityData should return the rows the repository saved")
	void addAllFicilityData_shouldReturnSavedRows() {
		List<M_facilitytype> input = List.of(row(1), row(2));
		ArrayList<M_facilitytype> saved = new ArrayList<>(input);
		when(m_facilitytypeRepo.saveAll(input)).thenReturn(saved);

		assertSame(saved, service.addAllFicilityData(input));
	}

	@Test
	@DisplayName("editAllFicilityData should hand back the row the repository looked up by id")
	void editAllFicilityData_shouldReturnRowById() {
		M_facilitytype found = row(1);
		when(m_facilitytypeRepo.findByFacilityTypeID(1)).thenReturn(found);

		assertSame(found, service.editAllFicilityData(1));
	}

	@Test
	@DisplayName("editAllFicilityData should hand back null when no row carries that id")
	void editAllFicilityData_shouldReturnNullWhenRowMissing() {
		when(m_facilitytypeRepo.findByFacilityTypeID(99)).thenReturn(null);

		assertNull(service.editAllFicilityData(99));
	}

	@Test
	@DisplayName("updateFacilityData should persist the edited row and return what the repository stored")
	void updateFacilityData_shouldPersistEditedRow() {
		M_facilitytype edited = row(1);
		when(m_facilitytypeRepo.save(edited)).thenReturn(edited);

		assertSame(edited, service.updateFacilityData(edited));
		verify(m_facilitytypeRepo).save(edited);
	}
}
