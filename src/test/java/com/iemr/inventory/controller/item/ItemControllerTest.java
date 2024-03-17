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
package com.iemr.inventory.controller.item;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iemr.inventory.data.items.ItemMaster;
import com.iemr.inventory.data.items.M_ItemCategory;
import com.iemr.inventory.data.items.M_ItemForm;
import com.iemr.inventory.data.items.M_Route;
import com.iemr.inventory.service.item.ItemService;
import com.iemr.inventory.utils.response.OutputResponse;

import jakarta.ws.rs.NotFoundException;

@ExtendWith(MockitoExtension.class)
class ItemControllerTest {

	@InjectMocks
	ItemController itemController;

	@Mock
	ItemService itemService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Test
	public void getItemFormTest() {

		Integer providerservicemapID = 8;

		M_ItemForm m_ItemForm = new M_ItemForm();

		m_ItemForm.setProviderServiceMapID(providerservicemapID);

		List<M_ItemForm> saveData = new ArrayList<M_ItemForm>();

		saveData.add(m_ItemForm);

		when(itemService.getItemFormProviderServiceMapID(providerservicemapID)).thenReturn(saveData);

		String expResp = saveData.toString();

		OutputResponse response = new OutputResponse();

		response.setResponse(expResp);

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.getItemForm(providerservicemapID));
	}

	@Test
	public void getItemFormTest_Exception() {

		Integer providerservicemapID = null;

		when(itemService.getItemFormProviderServiceMapID(providerservicemapID)).thenThrow(NotFoundException.class);

		String response = itemController.getItemForm(providerservicemapID);

		Assertions.assertEquals(response, itemController.getItemForm(providerservicemapID));

	}

	@Test
	public void getItemRouteTest() {

		Integer providerservicemapID = 8;

		M_Route m_Route = new M_Route();

		m_Route.setProviderServiceMapID(providerservicemapID);

		List<M_Route> saveData = new ArrayList<M_Route>();

		saveData.add(m_Route);

		when(itemService.getItemRouteProviderServiceMapID(providerservicemapID)).thenReturn(saveData);

		OutputResponse response = new OutputResponse();
		response.setResponse(saveData.toString());

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.getItemRoute(providerservicemapID));
	}

	@Test
	public void getItemRouteTest_Exception() {

		Integer providerservicemapID = null;

		when(itemService.getItemRouteProviderServiceMapID(providerservicemapID)).thenThrow(NotFoundException.class);

		String response = itemController.getItemRoute(providerservicemapID);

		Assertions.assertEquals(response, itemController.getItemRoute(providerservicemapID));

	}

	@Test
	public void getItemCategoryTestAsZero() {

		Integer providerServicemapID = 8;

		Integer bool = 0;

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setProviderServiceMapID(providerServicemapID);

		List<M_ItemCategory> saveData = new ArrayList<M_ItemCategory>();

		when(itemService.getItemCategory(true, providerServicemapID)).thenReturn(saveData);

		OutputResponse response = new OutputResponse();
		response.setResponse(saveData.toString());
		// Assertions
		Assertions.assertEquals(response.toString(), itemController.getItemCategory(providerServicemapID, bool));
	}

	@Test
	public void getItemCategoryTestNotAsZero() {

		Integer providerServicemapID = 8;

		Integer bool = 1;

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setProviderServiceMapID(providerServicemapID);

		List<M_ItemCategory> saveData = new ArrayList<M_ItemCategory>();

		when(itemService.getItemCategory(false, providerServicemapID)).thenReturn(saveData);

		OutputResponse response = new OutputResponse();
		response.setResponse(saveData.toString());

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.getItemCategory(providerServicemapID, bool));
	}

	@Test
	public void createItemMasterTest() {

		ItemMaster itemObj = new ItemMaster();
		itemObj.setItemCategoryID(12);
		itemObj.setQuantity(100);
		itemObj.setLastModDate(Date.valueOf("2000-08-08"));
		itemObj.setModifiedBy("A Kundu");
		itemObj.setCreatedDate(Date.valueOf("2000-08-08"));
		itemObj.setCreatedBy("A Kundu");
		itemObj.setItemID(1);
		itemObj.setItemName("gxbb");
		itemObj.setItemDesc("ggkdvjxdhgkjfh");
		itemObj.setItemCode("dfjhghdj657");
		itemObj.setItemCategoryID(5);
		itemObj.setIsMedical(true);
		itemObj.setPharmacologyCategoryID(6);
		itemObj.setManufacturerID(12);
		itemObj.setStrength("Very Strong");
		itemObj.setUomID(13);
		itemObj.setRouteID(7);
		itemObj.setIsScheduledDrug(true);
		itemObj.setComposition("Paracetamole");
		itemObj.setProviderServiceMapID(14);
		itemObj.setProviderServiceMap(null);
		itemObj.setStatus("Ready");
		itemObj.setDiscontinued(false);
		itemObj.setDeleted(false);
		itemObj.setProcessed('P');

		List<ItemMaster> itemList = new ArrayList<ItemMaster>();

		itemList.add(itemObj);

		ItemMaster[] item = new ItemMaster[itemList.size()];
		item = itemList.toArray(item);

		List<ItemMaster> saveData = itemList;

		when(itemService.addAllItemMaster(itemList)).thenReturn(saveData);

		OutputResponse response = new OutputResponse();
		response.setResponse(saveData.toString());
		// Assertions
		Assertions.assertEquals(response.toString(), itemController.createItemMaster(item));
	}

	@Test
	public void createItemMasterTest_Exception() {

		ItemMaster[] item = new ItemMaster[0];

		List<ItemMaster> itemList = new ArrayList<ItemMaster>();

		when(itemService.addAllItemMaster(itemList)).thenThrow(NotFoundException.class);

		String response = itemController.createItemMaster(item);

		Assertions.assertEquals(response, itemController.createItemMaster(item));

	}

	@Test
	public void getItemMasterTest() {

		Integer providerServicemapID = 8;

		ItemMaster itemMaster = new ItemMaster();

		itemMaster.setProviderServiceMapID(providerServicemapID);

		List<ItemMaster> saveData = new ArrayList<ItemMaster>();

		saveData.add(itemMaster);

		when(itemService.getItemMaster(providerServicemapID)).thenReturn(saveData);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.getItemMaster(providerServicemapID));
	}

	@Test
	public void getItemMasterTest_Exception() {

		Integer providerServicemapID = null;

		when(itemService.getItemMaster(providerServicemapID)).thenThrow(NotFoundException.class);

		String response = itemController.getItemMaster(providerServicemapID);

		Assertions.assertEquals(response, itemController.getItemMaster(providerServicemapID));

	}

	@Test
	public void getActiveItemMasterTest() {

		OutputResponse response = new OutputResponse();

		ItemMaster providerServicemapID = new ItemMaster();

		providerServicemapID.setProviderServiceMapID(8);

		List<ItemMaster> saveData = new ArrayList<ItemMaster>();

		saveData.add(providerServicemapID);

		when(itemService.getActiveItemMaster(providerServicemapID)).thenReturn(saveData);

		response.setResponse(saveData.toString());

		Assertions.assertEquals(response.toString(), itemController.getActiveItemMaster(providerServicemapID));

	}

	@Test
	public void getActiveItemMasterTest_Exception() {

		ItemMaster providerServicemapID = new ItemMaster();

		when(itemService.getActiveItemMaster(providerServicemapID)).thenThrow(NotFoundException.class);

		String response = itemController.getActiveItemMaster(providerServicemapID);

		Assertions.assertEquals(response, itemController.getActiveItemMaster(providerServicemapID));

	}

	@Test
	public void blockItemMasterList() {

		Integer itemmasterID = 6;

		Boolean delete = true;

		Integer update = 1;

		when(itemService.blockItemMaster(itemmasterID, delete)).thenReturn(update);

		OutputResponse response = new OutputResponse();

		response.setResponse(update.toString());

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.blockItemMaster(itemmasterID, delete));
	}

	@Test
	public void blockItemMasterList_Exception() {

		Integer itemmasterID = null;

		Boolean delete = false;

		when(itemService.blockItemMaster(itemmasterID, delete)).thenThrow(NotFoundException.class);

		String response = itemController.blockItemMaster(itemmasterID, delete);

		Assertions.assertEquals(response, itemController.blockItemMaster(itemmasterID, delete));

	}

	@Test
	public void discontinueItemMasterTest() {

		Integer itemmasterID = 6;

		Boolean delete = true;

		Integer update = 1;

		when(itemService.discontinueItemMaster(itemmasterID, delete)).thenReturn(update);

		OutputResponse response = new OutputResponse();

		response.setResponse(update.toString());

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.discontinueItemMaster(itemmasterID, delete));
	}

	@Test
	public void discontinueItemMasterTest_Exception() {

		Integer itemmasterID = null;

		Boolean delete = false;

		when(itemService.discontinueItemMaster(itemmasterID, delete)).thenThrow(NotFoundException.class);

		String response = itemController.discontinueItemMaster(itemmasterID, delete);

		Assertions.assertEquals(response, itemController.discontinueItemMaster(itemmasterID, delete));

	}

	@Test
	public void editItemMasterTest() {

		ItemMaster item = new ItemMaster();
		item.setItemCategoryID(12);
		item.setQuantity(100);
		item.setLastModDate(Date.valueOf("2000-08-08"));
		item.setModifiedBy("A Kundu");
		item.setCreatedDate(Date.valueOf("2000-08-08"));
		item.setCreatedBy("A Kundu");
		item.setItemID(1);
		item.setItemName("gxbb");
		item.setItemDesc("ggkdvjxdhgkjfh");
		item.setItemCode("dfjhghdj657");
		item.setItemCategoryID(5);
		item.setIsMedical(true);
		item.setPharmacologyCategoryID(6);
		item.setManufacturerID(12);
		item.setStrength("Very Strong");
		item.setUomID(13);
		item.setRouteID(7);
		item.setIsScheduledDrug(true);
		item.setComposition("Paracetamole");
		item.setProviderServiceMapID(14);
		item.setProviderServiceMap(null);
		item.setStatus("Ready");
		item.setDiscontinued(false);
		item.setDeleted(false);
		item.setProcessed('P');

		ItemMaster saveData = item;

		when(itemService.getItemMasterByID(item.getItemID())).thenReturn(saveData);

		saveData.setItemDesc(item.getItemDesc());
		saveData.setModifiedBy(item.getModifiedBy());

		when(itemService.createItemMaster(saveData)).thenReturn(saveData);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.editItemMaster(item));
	}

	@Test
	public void editItemMasterTest_Exception() {

		ItemMaster item = new ItemMaster();

		when(itemService.getItemMasterByID(item.getItemID())).thenThrow(NotFoundException.class);

		String response = itemController.editItemMaster(item);

		Assertions.assertEquals(response, itemController.editItemMaster(item));

	}

	@Test
	public void configItemIssueTest() {

		M_ItemCategory m_ItemCategory = new M_ItemCategory();

		m_ItemCategory.setItemCategoryID(5);
		m_ItemCategory.setAlertBeforeDays(15);
		m_ItemCategory.setIssueType("Expiry");
		m_ItemCategory.setItemCategoryName("Paracitamol");
		m_ItemCategory.setItemCategoryDesc("It is a medicine for headache");
		m_ItemCategory.setItemCategoryCode("P01C78L");
		m_ItemCategory.setStatus("Approved and Useable");
		m_ItemCategory.setProviderServiceMapID(8);
		m_ItemCategory.setDeleted(false);
		m_ItemCategory.setProcessed('a');
		m_ItemCategory.setCreatedBy("P Medical Company");
		m_ItemCategory.setCreatedDate(Date.valueOf("2000-08-08"));
		m_ItemCategory.setModifiedBy("C Medical Company");
		m_ItemCategory.setLastModDate(Date.valueOf("2001-09-09"));

		List<M_ItemCategory> itemCategory = new ArrayList<M_ItemCategory>();

		itemCategory.add(m_ItemCategory);

		M_ItemCategory[] itemIssue = new M_ItemCategory[itemCategory.size()];

		List<M_ItemCategory> itemList = Arrays.asList(itemIssue);

		Integer saveData = 1;

		when(itemService.updateItemIssueConfig(itemList)).thenReturn(saveData);

		OutputResponse response = new OutputResponse();

		response.setResponse(saveData.toString());

		// Assertions
		Assertions.assertEquals(response.toString(), itemController.configItemIssue(itemIssue));
	}

	@Test
	public void configItemIssueTest_Exception() {

		M_ItemCategory[] itemIssue = new M_ItemCategory[0];

		List<M_ItemCategory> itemList = Arrays.asList(itemIssue);

		when(itemService.updateItemIssueConfig(itemList)).thenThrow(NotFoundException.class);

		String response = itemController.configItemIssue(itemIssue);

		Assertions.assertEquals(response, itemController.configItemIssue(itemIssue));

	}

	@Test
	public void getItemTest() {

		String getItem = "Medicine";

		ItemMaster item = new ItemMaster();
		item.setItemCategoryID(12);
		item.setQuantity(100);
		item.setLastModDate(Date.valueOf("2000-08-08"));
		item.setModifiedBy("A Kundu");
		item.setCreatedDate(Date.valueOf("2000-08-08"));
		item.setCreatedBy("A Kundu");
		item.setItemID(1);
		item.setItemName("gxbb");
		item.setItemDesc("ggkdvjxdhgkjfh");
		item.setItemCode("dfjhghdj657");
		item.setItemCategoryID(5);
		item.setIsMedical(true);
		item.setPharmacologyCategoryID(6);
		item.setManufacturerID(12);
		item.setStrength("Very Strong");
		item.setUomID(13);
		item.setRouteID(7);
		item.setIsScheduledDrug(true);
		item.setComposition("Paracetamole");
		item.setProviderServiceMapID(14);
		item.setProviderServiceMap(null);
		item.setStatus("Ready");
		item.setDiscontinued(false);
		item.setDeleted(false);
		item.setProcessed('P');
		item.setItemCategoryID(8);

		List<ItemMaster> getData = new ArrayList<ItemMaster>();

		getData.add(item);

		OutputResponse response = new OutputResponse();

		response.setResponse(getData.toString());
		
		String resp = itemController.getItem(getItem);

		// Assertions
		Assertions.assertEquals(resp, itemController.getItem(getItem));
	}
	
	@Test
	public void getItemTest_Exception() {

		String getItem = "{\"statusCode\":5000,\"errorMessage\":\"Failed with generic error\",\"status\":\"FAILURE\"}";

		String response = itemController.getItem(getItem);

		Assertions.assertEquals(response, itemController.getItem(getItem));

	}

}
