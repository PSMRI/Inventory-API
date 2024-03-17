package com.iemr.inventory.mapper.stockadjustment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StockAdjustmentItemDraftMapperImpl.class})
@ExtendWith(SpringExtension.class)
class StockAdjustmentItemDraftMapperTest {
    @Autowired
    private StockAdjustmentItemDraftMapper stockAdjustmentItemDraftMapper;

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapper#mapSADraftItemMapID(Object)}
     */
    @Test
    void testMapSADraftItemMapID() {
        // Arrange, Act and Assert
        assertEquals(42L, stockAdjustmentItemDraftMapper.mapSADraftItemMapID(42).longValue());
        assertNull(stockAdjustmentItemDraftMapper.mapSADraftItemMapID(null));
    }

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapper#mapCreatedDate(Object)}
     */
    @Test
    void testMapCreatedDate() {
        // Arrange, Act and Assert
        assertNull(stockAdjustmentItemDraftMapper.mapCreatedDate(null));
    }

    /**
     * Method under test:
     * {@link StockAdjustmentItemDraftMapper#mapCreatedBy(Object)}
     */
    @Test
    void testMapCreatedBy() {
        // Arrange, Act and Assert
        assertEquals("Value", stockAdjustmentItemDraftMapper.mapCreatedBy("Value"));
        assertNull(stockAdjustmentItemDraftMapper.mapCreatedBy(null));
    }
}
