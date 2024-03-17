package com.iemr.inventory.controller.manufacturer;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

import com.iemr.inventory.data.manufacturer.M_Manufacturer;
import com.iemr.inventory.service.manufacturer.ManufacturerInter;

@ExtendWith(MockitoExtension.class)
class ManufacturerControllerTest {

    @Mock
    private ManufacturerInter manufacturerInter;

    @InjectMocks
    private ManufacturerController manufacturerController;
    
    
    @Test
    public void testCreateManufacturer_SuccessfulCreation() {
    	 // Given
        String createManufacturerRequest = "[{\"manufacturerID\": 1, \"manufacturerName\": \"Manufacturer1\"}, {\"manufacturerID\": 2, \"manufacturerName\": \"Manufacturer2\"}]";
        M_Manufacturer[] mockManufacturers = {
                new M_Manufacturer(), // assuming a default constructor or setters are available
                new M_Manufacturer()
        };

        mockManufacturers[0].setManufacturerID(1);
        mockManufacturers[0].setManufacturerName("Manufacturer1");

        mockManufacturers[1].setManufacturerID(2);
        mockManufacturers[1].setManufacturerName("Manufacturer2");

        List<M_Manufacturer> mockManufacturerList = Arrays.asList(mockManufacturers);

        when(manufacturerInter.createManufacturer(anyList())).thenReturn(new ArrayList<>(mockManufacturerList));

        // When
        String result = manufacturerController.createManufacturer(createManufacturerRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("manufacturerID"));
        Assertions.assertTrue(result.contains("Manufacturer1"));
        Assertions.assertTrue(result.contains("Manufacturer2"));
        verify(manufacturerInter, times(1)).createManufacturer(anyList());
    }
   

    @Test
    public void testCreateManufacturer_InvalidRequest() {
        // Given
        String invalidRequest = "{\"invalid\": \"request\"}";

        // When
        String result = manufacturerController.createManufacturer(invalidRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, never()).createManufacturer(anyList());
    }

    @Test
    public void testCreateManufacturer_Exception() {
        // Given
        String createManufacturerRequest = "[{\"manufacturerID\": 1, \"manufacturerName\": \"Manufacturer1\"}]";

        when(manufacturerInter.createManufacturer(anyList())).thenThrow(new RuntimeException("Simulating an exception"));

        // When
        String result = manufacturerController.createManufacturer(createManufacturerRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, times(1)).createManufacturer(anyList());
    }
    @Test
    public void testGetManufacturer_SuccessfulGet() {
        // Given
        String getManufacturerRequest = "{\"providerServiceMapID\": 1}";
        M_Manufacturer mockManufacturer = new M_Manufacturer();
        mockManufacturer.setManufacturerID(1);
        mockManufacturer.setManufacturerName("Manufacturer1");

        ArrayList<M_Manufacturer> mockData = new ArrayList<>();
        mockData.add(mockManufacturer);

        when(manufacturerInter.createManufacturer(anyInt())).thenReturn(mockData);

        // When
        String result = manufacturerController.getManufacturer(getManufacturerRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("manufacturerID"));
        Assertions.assertTrue(result.contains("Manufacturer1"));
        verify(manufacturerInter, times(1)).createManufacturer(anyInt());
    }
    
    @Test
    public void testGetManufacturer_InvalidRequest() {
        // Given
        String invalidRequest = "{\"invalid\": \"request\"}";

        // When
        String result = manufacturerController.getManufacturer(invalidRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, never()).createManufacturer(anyInt());
    }
    
    @Test
    public void testGetManufacturer_Exception() {
        // Given
        String getManufacturerRequest = "{\"providerServiceMapID\": 1}";

        when(manufacturerInter.createManufacturer(anyInt())).thenThrow(new RuntimeException("Simulating an exception"));

        // When
        String result = manufacturerController.getManufacturer(getManufacturerRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, times(1)).createManufacturer(anyInt());
    }

    
    @Test
    public void testEditManufacturer_SuccessfulEdit() {
        // Given
        String editManufacturerRequest = "{\"manufacturerID\": 1, \"manufacturerName\": \"UpdatedName\", \"manufacturerDesc\": \"UpdatedDesc\", \"manufacturerCode\": \"UpdatedCode\", \"status\": \"Active\", \"contactPerson\": \"UpdatedContactPerson\", \"cST_GST_No\": \"UpdatedGSTNo\", \"modifiedBy\": \"UpdatedUser\"}";
        M_Manufacturer mockManufacturer = new M_Manufacturer();
        mockManufacturer.setManufacturerID(1);
        mockManufacturer.setManufacturerName("TestName");
        mockManufacturer.setManufacturerDesc("TestDesc");
        mockManufacturer.setManufacturerCode("TestCode");
        mockManufacturer.setStatus("Inactive");
        mockManufacturer.setContactPerson("TestContactPerson");
        mockManufacturer.setcST_GST_No("TestGSTNo");
        mockManufacturer.setModifiedBy("TestUser");

        when(manufacturerInter.editManufacturer(anyInt())).thenReturn(mockManufacturer);
        when(manufacturerInter.saveEditedData(any(M_Manufacturer.class))).thenReturn(mockManufacturer);

        // When
        String result = manufacturerController.editManufacturer(editManufacturerRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("manufacturerID"));
        Assertions.assertTrue(result.contains("UpdatedName"));
        Assertions.assertTrue(result.contains("UpdatedDesc"));
        Assertions.assertTrue(result.contains("UpdatedCode"));
        Assertions.assertTrue(result.contains("Active"));
        Assertions.assertTrue(result.contains("UpdatedContactPerson"));
        Assertions.assertTrue(result.contains("UpdatedGSTNo"));
        Assertions.assertTrue(result.contains("UpdatedUser"));
        verify(manufacturerInter, times(1)).editManufacturer(anyInt());
        verify(manufacturerInter, times(1)).saveEditedData(any(M_Manufacturer.class));
    }
    
    @Test
    public void testEditManufacturer_InvalidRequest() {
        // Given
        String invalidRequest = "{\"invalid\": \"request\"}";

        // When
        String result = manufacturerController.editManufacturer(invalidRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, never()).editManufacturer(anyInt());
        verify(manufacturerInter, never()).saveEditedData(any(M_Manufacturer.class));
    }

    @Test
    public void testEditManufacturer_Exception() {
        // Given
        String editManufacturerRequest = "{\"manufacturerID\": 1, \"manufacturerName\": \"UpdatedName\", \"manufacturerDesc\": \"UpdatedDesc\", \"manufacturerCode\": \"UpdatedCode\", \"status\": \"Active\", \"contactPerson\": \"UpdatedContactPerson\", \"cST_GST_No\": \"UpdatedGSTNo\", \"modifiedBy\": \"UpdatedUser\"}";

        when(manufacturerInter.editManufacturer(anyInt())).thenThrow(new RuntimeException("Simulating an exception"));

        // When
        String result = manufacturerController.editManufacturer(editManufacturerRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, times(1)).editManufacturer(anyInt());
        verify(manufacturerInter, never()).saveEditedData(any(M_Manufacturer.class));
    }

    
    @Test
    public void testDeleteManufacturer_SuccessfulDeletion() {
        // Given
        String deleteManufacturerRequest = "{\"manufacturerID\": 1, \"deleted\": true}";
        M_Manufacturer mockManufacturer = new M_Manufacturer();
        mockManufacturer.setManufacturerID(1);
        mockManufacturer.setDeleted(true);

        when(manufacturerInter.editManufacturer(anyInt())).thenReturn(mockManufacturer);
        when(manufacturerInter.saveEditedData(any(M_Manufacturer.class))).thenReturn(mockManufacturer);

        // When
        String result = manufacturerController.deleteManufacturer(deleteManufacturerRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("manufacturerID"));
        Assertions.assertTrue(result.contains("deleted"));
        verify(manufacturerInter, times(1)).editManufacturer(anyInt());
        verify(manufacturerInter, times(1)).saveEditedData(any(M_Manufacturer.class));
    }

    @Test
    public void testDeleteManufacturer_InvalidRequest() {
        // Given
        String invalidRequest = "{\"invalid\": \"request\"}";

        // When
        String result = manufacturerController.deleteManufacturer(invalidRequest);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, never()).editManufacturer(anyInt());
        verify(manufacturerInter, never()).saveEditedData(any(M_Manufacturer.class));
    }

    @Test
    public void testDeleteManufacturer_Exception() {
        // Given
        String deleteManufacturerRequest = "{\"manufacturerID\": 1, \"deleted\": true}";

        when(manufacturerInter.editManufacturer(anyInt())).thenThrow(new RuntimeException("Simulating an exception"));

        // When
        String result = manufacturerController.deleteManufacturer(deleteManufacturerRequest);

        // Then
        Assertions. assertNotNull(result);
        Assertions.assertTrue(result.contains("error"));
        verify(manufacturerInter, times(1)).editManufacturer(anyInt());
        verify(manufacturerInter, never()).saveEditedData(any(M_Manufacturer.class));
    }

    
}


