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
package com.iemr.inventory.service.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_ItemForm;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.repository.item.ItemCategoryRepo;
import com.iemr.inventory.repository.item.ItemFormRepo;
import com.iemr.inventory.repository.item.ItemRepo;
import com.iemr.inventory.repository.item.RouteRepo;

@ExtendWith(MockitoExtension.class)
@DisplayName("ItemServiceImpl Test Suite")
class ItemServiceImplTest {

	@Mock
	private ItemRepo itemRepo;
	@Mock
	private ItemCategoryRepo itemCategoryRepo;
	@Mock
	private RouteRepo routeRepo;
	@Mock
	private ItemFormRepo itemFormRepo;

	@InjectMocks
	private ItemServiceImpl service;

	private static ItemMaster item(Integer id) {
		ItemMaster item = new ItemMaster();
		item.setItemID(id);
		return item;
	}

	private static M_ItemCategory category(Integer id, String issueType) {
		M_ItemCategory category = new M_ItemCategory();
		category.setItemCategoryID(id);
		category.setIssueType(issueType);
		return category;
	}

	@Test
	@DisplayName("createItemMaster should persist through the item repository")
	void createItemMaster_shouldPersist() {
		ItemMaster item = item(1);
		when(itemRepo.save(item)).thenReturn(item);

		assertSame(item, service.createItemMaster(item));
	}

	@Test
	@DisplayName("getItemCategory(all) should return every category for the provider service map")
	void getItemCategory_shouldReturnAllCategories() {
		List<M_ItemCategory> all = List.of(category(1, "Bulk"));
		when(itemCategoryRepo.findByProviderServiceMapID(3)).thenReturn(all);

		assertSame(all, service.getItemCategory(true, 3));
		verify(itemCategoryRepo, never()).findByDeletedAndProviderServiceMapID(false, 3);
	}

	@Test
	@DisplayName("getItemCategory(not all) should exclude the deleted categories")
	void getItemCategory_shouldExcludeDeletedCategories() {
		List<M_ItemCategory> live = List.of(category(1, "Bulk"));
		when(itemCategoryRepo.findByDeletedAndProviderServiceMapID(false, 3)).thenReturn(live);

		assertSame(live, service.getItemCategory(false, 3));
	}

	@Test
	@DisplayName("getItemCategory should return an empty list rather than query with a null provider service map")
	void getItemCategory_shouldReturnEmptyWhenProviderServiceMapNull() {
		assertTrue(service.getItemCategory(true, null).isEmpty());
		verify(itemCategoryRepo, never()).findByProviderServiceMapID(anyInt());
	}

	@Test
	@DisplayName("getItemRoute(all) should return every route")
	void getItemRoute_shouldReturnAllRoutes() {
		List<M_Route> all = List.of(new M_Route());
		when(routeRepo.getAll()).thenReturn(all);

		assertSame(all, service.getItemRoute(true));
	}

	@Test
	@DisplayName("getItemRoute(not all) should exclude the deleted routes")
	void getItemRoute_shouldExcludeDeletedRoutes() {
		List<M_Route> live = List.of(new M_Route());
		when(routeRepo.findByDeleted(false)).thenReturn(live);

		assertSame(live, service.getItemRoute(false));
	}

	@Test
	@DisplayName("getItemForm(all) should return every item form")
	void getItemForm_shouldReturnAllForms() {
		List<M_ItemForm> all = List.of(new M_ItemForm());
		when(itemFormRepo.getAll()).thenReturn(all);

		assertSame(all, service.getItemForm(true));
	}

	@Test
	@DisplayName("getItemForm(not all) should exclude the deleted item forms")
	void getItemForm_shouldExcludeDeletedForms() {
		List<M_ItemForm> live = List.of(new M_ItemForm());
		when(itemFormRepo.findByDeleted(false)).thenReturn(live);

		assertSame(live, service.getItemForm(false));
	}

	@Test
	@DisplayName("getItemMaster should return the items of the provider service map")
	void getItemMaster_shouldReturnItemsOfProviderServiceMap() {
		List<ItemMaster> items = List.of(item(1));
		when(itemRepo.findByProviderServiceMapID(3)).thenReturn(items);

		assertSame(items, service.getItemMaster(3));
	}

	@Test
	@DisplayName("blockItemMaster should pass the delete flag straight to the repository")
	void blockItemMaster_shouldPassDeleteFlagThrough() {
		when(itemRepo.deleteItemMaster(9, true)).thenReturn(1);

		assertEquals(1, service.blockItemMaster(9, true));
	}

	@Test
	@DisplayName("discontinueItemMaster should pass the discontinue flag straight to the repository")
	void discontinueItemMaster_shouldPassFlagThrough() {
		when(itemRepo.discontinueItemMaster(9, false)).thenReturn(1);

		assertEquals(1, service.discontinueItemMaster(9, false));
	}

	@Test
	@DisplayName("addAllItemMaster should save the whole batch in one call")
	void addAllItemMaster_shouldSaveBatch() {
		List<ItemMaster> batch = List.of(item(1), item(2));
		when(itemRepo.saveAll(batch)).thenReturn(batch);

		assertSame(batch, service.addAllItemMaster(batch));
	}

	@Test
	@DisplayName("getItemMasterByID should unwrap the item the repository found")
	void getItemMasterByID_shouldUnwrapFoundItem() {
		ItemMaster item = item(1);
		when(itemRepo.findById(1)).thenReturn(Optional.of(item));

		assertSame(item, service.getItemMasterByID(1));
	}

	@Test
	@DisplayName("getItemMasterByID should throw when no item carries that id")
	void getItemMasterByID_shouldThrowWhenItemMissing() {
		when(itemRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> service.getItemMasterByID(99));
	}

	@Test
	@DisplayName("getItemMasterCatByID should return the detailed row the repository built")
	void getItemMasterCatByID_shouldReturnDetailRow() {
		ItemMaster item = item(1);
		when(itemRepo.findDetailOne(1)).thenReturn(item);

		assertSame(item, service.getItemMasterCatByID(1));
	}

	@Test
	@DisplayName("updateItemIssueConfig should total the rows updated for every complete category")
	void updateItemIssueConfig_shouldTotalUpdatedRows() {
		when(itemCategoryRepo.updateIssueConfig(1, "Bulk")).thenReturn(1);
		when(itemCategoryRepo.updateIssueConfig(2, "Single")).thenReturn(1);

		assertEquals(2, service.updateItemIssueConfig(List.of(category(1, "Bulk"), category(2, "Single"))));
	}

	@Test
	@DisplayName("updateItemIssueConfig should skip categories missing an id or an issue type")
	void updateItemIssueConfig_shouldSkipIncompleteCategories() {
		assertEquals(0, service.updateItemIssueConfig(List.of(category(null, "Bulk"), category(2, null))));
		verify(itemCategoryRepo, never()).updateIssueConfig(anyInt(), org.mockito.ArgumentMatchers.anyString());
	}

	@Test
	@DisplayName("getItemRouteProviderServiceMapID should delegate to the route repository")
	void getItemRouteProviderServiceMapID_shouldDelegate() {
		List<M_Route> routes = List.of(new M_Route());
		when(routeRepo.findByProviderServiceMapID(3)).thenReturn(routes);

		assertSame(routes, service.getItemRouteProviderServiceMapID(3));
	}

	@Test
	@DisplayName("getItemFormProviderServiceMapID should delegate to the item form repository")
	void getItemFormProviderServiceMapID_shouldDelegate() {
		List<M_ItemForm> forms = List.of(new M_ItemForm());
		when(itemFormRepo.findByProviderServiceMapID(3)).thenReturn(forms);

		assertSame(forms, service.getItemFormProviderServiceMapID(3));
	}

	@Test
	@DisplayName("getItemMasters should narrow the items to one category of one provider service map")
	void getItemMasters_shouldNarrowByCategory() {
		List<ItemMaster> items = List.of(item(1));
		when(itemRepo.getItemMasters(3, 5)).thenReturn(items);

		assertSame(items, service.getItemMasters(3, 5));
	}

	@Test
	@DisplayName("getItemCategory(id) should unwrap the category the repository found")
	void getItemCategoryById_shouldUnwrapFoundCategory() {
		M_ItemCategory category = category(5, "Bulk");
		when(itemCategoryRepo.findById(5)).thenReturn(Optional.of(category));

		assertSame(category, service.getItemCategory(5));
	}

	@Test
	@DisplayName("getItemCategory(id) should throw when no category carries that id")
	void getItemCategoryById_shouldThrowWhenCategoryMissing() {
		when(itemCategoryRepo.findById(99)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> service.getItemCategory(99));
	}

	@Test
	@DisplayName("getActiveItemMaster should filter on the deleted flag carried by the probe item")
	void getActiveItemMaster_shouldFilterOnDeletedFlag() {
		ItemMaster probe = item(null);
		probe.setDeleted(false);
		probe.setProviderServiceMapID(3);
		List<ItemMaster> active = List.of(item(1));
		when(itemRepo.findByDeletedAndProviderServiceMapID(false, 3)).thenReturn(active);

		assertSame(active, service.getActiveItemMaster(probe));
	}
}
