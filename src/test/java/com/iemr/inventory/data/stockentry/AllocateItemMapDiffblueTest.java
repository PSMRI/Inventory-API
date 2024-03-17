package com.iemr.inventory.data.stockentry;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AllocateItemMapDiffblueTest {
    /**
     * Method under test: {@link AllocateItemMap#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{\"facilityID\":null,\"itemID\":null,\"itemName\":null,\"itemBatchList\":null}",
                (new AllocateItemMap()).toString());
    }
}
