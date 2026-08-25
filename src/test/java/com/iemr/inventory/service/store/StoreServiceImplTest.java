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
package com.iemr.inventory.service.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.store.M_Facility;
import com.iemr.inventory.data.store.M_Van;
import com.iemr.inventory.repository.store.MainStoreRepo;
import com.iemr.inventory.repository.store.VanMasterRepository;
import com.iemr.inventory.utils.exception.IEMRException;

@ExtendWith(MockitoExtension.class)
@DisplayName("StoreServiceImpl Test Suite")
class StoreServiceImplTest {

	@Mock
	private MainStoreRepo mainStoreRepo;
	@Mock
	private VanMasterRepository vanMasterRepository;

	@InjectMocks
	private StoreServiceImpl service;

	private static M_Facility facility(Integer id) {
		M_Facility facility = new M_Facility();
		facility.setFacilityID(id);
		return facility;
	}

	@Test
	@DisplayName("createMainStore should persist the store through the main store repository")
	void createMainStore_shouldPersist() {
		M_Facility store = facility(7);
		when(mainStoreRepo.save(store)).thenReturn(store);

		assertSame(store, service.createMainStore(store));
	}

	@Test
	@DisplayName("getMainStore should unwrap the store the repository found")
	void getMainStore_shouldUnwrapFoundStore() {
		M_Facility store = facility(7);
		when(mainStoreRepo.findById(7)).thenReturn(Optional.of(store));

		assertSame(store, service.getMainStore(7));
	}

	@Test
	@DisplayName("getMainStore should throw when no store carries that id")
	void getMainStore_shouldThrowWhenStoreMissing() {
		when(mainStoreRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> service.getMainStore(99));
	}

	@Test
	@DisplayName("getAllMainStore should return the stores of the provider service map")
	void getAllMainStore_shouldReturnStoresOfProviderServiceMap() {
		List<M_Facility> stores = List.of(facility(7));
		when(mainStoreRepo.findByProviderServiceMapID(3)).thenReturn(stores);

		assertSame(stores, service.getAllMainStore(3));
	}

	@Test
	@DisplayName("addAllMainStore should save the whole batch in one call")
	void addAllMainStore_shouldSaveBatch() {
		List<M_Facility> batch = List.of(facility(7), facility(8));
		when(mainStoreRepo.saveAll(batch)).thenReturn(batch);

		assertSame(batch, service.addAllMainStore(batch));
	}

	@Test
	@DisplayName("getMainFacility should return the main facilities of the provider service map")
	void getMainFacility_shouldReturnMainFacilities() {
		ArrayList<M_Facility> found = new ArrayList<>(List.of(facility(7)));
		when(mainStoreRepo.getAllMainFacility(3, true)).thenReturn(found);

		assertSame(found, service.getMainFacility(3, true));
	}

	@Test
	@DisplayName("getMainFacility should narrow to one parent facility when a parent id is supplied")
	void getMainFacility_shouldNarrowToParentFacility() {
		ArrayList<M_Facility> found = new ArrayList<>(List.of(facility(7)));
		when(mainStoreRepo.getAllMainFacility(3, true, 5)).thenReturn(found);

		assertSame(found, service.getMainFacility(3, true, 5));
	}

	@Test
	@DisplayName("getChildFacility should return the sub-stores of the parent facility")
	void getChildFacility_shouldReturnSubStores() {
		ArrayList<M_Facility> found = new ArrayList<>(List.of(facility(8)));
		when(mainStoreRepo.getChildFacility(3, 7)).thenReturn(found);

		assertSame(found, service.getChildFacility(3, 7));
	}

	@Test
	@DisplayName("deleteStore should deactivate a store that has no active children left")
	void deleteStore_shouldDeactivateStoreWithoutActiveChildren() throws Exception {
		M_Facility stored = facility(7);
		M_Facility request = facility(7);
		request.setDeleted(true);
		when(mainStoreRepo.findById(7)).thenReturn(Optional.of(stored));
		when(mainStoreRepo.findByMainFacilityIDAndDeleted(7, false)).thenReturn(new ArrayList<>());
		when(mainStoreRepo.save(stored)).thenReturn(stored);

		assertSame(stored, service.deleteStore(request));
		assertEquals(Boolean.TRUE, stored.getDeleted());
	}

	@Test
	@DisplayName("deleteStore should refuse to deactivate a store that still has active children")
	void deleteStore_shouldRefuseWhenChildrenStillActive() {
		M_Facility stored = facility(7);
		M_Facility request = facility(7);
		request.setDeleted(true);
		when(mainStoreRepo.findById(7)).thenReturn(Optional.of(stored));
		when(mainStoreRepo.findByMainFacilityIDAndDeleted(7, false)).thenReturn(new ArrayList<>(List.of(facility(8))));

		IEMRException ex = assertThrows(IEMRException.class, () -> service.deleteStore(request));
		assertEquals("Child Stores are still active", ex.getMessage());
		verify(mainStoreRepo, never()).save(any(M_Facility.class));
	}

	@Test
	@DisplayName("deleteStore should reactivate a sub-store whose parent is already active")
	void deleteStore_shouldReactivateSubStoreWithActiveParent() throws Exception {
		M_Facility stored = facility(8);
		stored.setMainFacilityID(7);
		M_Facility request = facility(8);
		request.setDeleted(false);
		when(mainStoreRepo.findById(8)).thenReturn(Optional.of(stored));
		when(mainStoreRepo.findByFacilityIDAndDeleted(7, false)).thenReturn(facility(7));
		when(mainStoreRepo.save(stored)).thenReturn(stored);

		assertSame(stored, service.deleteStore(request));
		assertEquals(Boolean.FALSE, stored.getDeleted());
	}

	@Test
	@DisplayName("deleteStore should refuse to reactivate a sub-store whose parent is still inactive")
	void deleteStore_shouldRefuseWhenParentStillInactive() {
		M_Facility stored = facility(8);
		stored.setMainFacilityID(7);
		M_Facility request = facility(8);
		request.setDeleted(false);
		when(mainStoreRepo.findById(8)).thenReturn(Optional.of(stored));
		when(mainStoreRepo.findByFacilityIDAndDeleted(7, false)).thenReturn(null);

		IEMRException ex = assertThrows(IEMRException.class, () -> service.deleteStore(request));
		assertEquals("Parent Stores are still inactive", ex.getMessage());
	}

	@Test
	@DisplayName("deleteStore should reactivate a top-level store with no parent to check")
	void deleteStore_shouldReactivateTopLevelStore() throws Exception {
		M_Facility stored = facility(7);
		M_Facility request = facility(7);
		request.setDeleted(false);
		when(mainStoreRepo.findById(7)).thenReturn(Optional.of(stored));
		when(mainStoreRepo.save(stored)).thenReturn(stored);

		assertSame(stored, service.deleteStore(request));
		assertEquals(Boolean.FALSE, stored.getDeleted());
	}

	@Test
	@DisplayName("deleteStore should refuse a request that carries no deleted flag")
	void deleteStore_shouldRefuseRequestWithoutDeletedFlag() {
		when(mainStoreRepo.findById(7)).thenReturn(Optional.of(facility(7)));

		IEMRException ex = assertThrows(IEMRException.class, () -> service.deleteStore(facility(7)));
		assertEquals("No store available", ex.getMessage());
	}

	@Test
	@DisplayName("getAllActiveStore should filter on the provider service map and deleted flag of the probe")
	void getAllActiveStore_shouldFilterOnProbe() {
		M_Facility probe = new M_Facility();
		probe.setProviderServiceMapID(3);
		probe.setDeleted(false);
		List<M_Facility> active = List.of(facility(7));
		when(mainStoreRepo.findByProviderServiceMapIDAndDeleted(3, false)).thenReturn(active);

		assertSame(active, service.getAllActiveStore(probe));
	}

	@Test
	@DisplayName("getStoreByID should return only a store that is not deleted")
	void getStoreByID_shouldReturnLiveStore() {
		M_Facility store = facility(7);
		when(mainStoreRepo.findByFacilityIDAndDeleted(7, false)).thenReturn(store);

		assertSame(store, service.getStoreByID(7));
	}

	@Test
	@DisplayName("getVanByStoreID should return only a van that is not deleted")
	void getVanByStoreID_shouldReturnLiveVan() {
		M_Van van = new M_Van();
		when(vanMasterRepository.findOneByFacilityIDAndDeleted(7, false)).thenReturn(van);

		assertSame(van, service.getVanByStoreID(7));
	}

	@Test
	@DisplayName("getVanByStoreID should return null when the store has no van attached")
	void getVanByStoreID_shouldReturnNullWhenNoVan() {
		when(vanMasterRepository.findOneByFacilityIDAndDeleted(7, false)).thenReturn(null);

		assertTrue(service.getVanByStoreID(7) == null);
	}
}
