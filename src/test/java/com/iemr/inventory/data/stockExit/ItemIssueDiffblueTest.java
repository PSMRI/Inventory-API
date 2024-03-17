package com.iemr.inventory.data.stockExit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ItemIssueDiffblueTest {
    /**
     * Method under test: {@link ItemIssue#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("{}", (new ItemIssue()).toString());
    }
}
