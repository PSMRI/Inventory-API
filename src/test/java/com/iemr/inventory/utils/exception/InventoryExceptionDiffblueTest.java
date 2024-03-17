package com.iemr.inventory.utils.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.aot.DisabledInAotMode;

@DisabledInAotMode
class InventoryExceptionTest {
    @MockBean
    private InventoryException inventoryException;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link InventoryException#InventoryException(String)}
     *   <li>{@link InventoryException#getMessage()}
     *   <li>{@link InventoryException#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        InventoryException actualInventoryException = new InventoryException("An error occurred");
        String actualMessage = actualInventoryException.getMessage();

        // Assert
        assertEquals("An error occurred", actualMessage);
        assertEquals("An error occurred", actualInventoryException.toString());
    }

    /**
     * Method under test:
     * {@link InventoryException#InventoryException(String, Throwable)}
     */
    @Test
    void testNewInventoryException() {
        // Arrange, Act and Assert
        assertEquals("An error occurred",
                (new InventoryException("An error occurred", new Throwable())).getLocalizedMessage());
    }
}
