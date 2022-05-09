package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {
    private Order order;

    @BeforeEach
    void setup() {
        order = new Order();
    }

    @Test
    void canAddCateringMenuItemToAnOrder() {
        CateringMenuItemId cateringMenuItemId = new CateringMenuItemId();

        assertThrows(InvalidValueException.class, () -> {
            order.addCateringMenuItem(cateringMenuItemId, -1);

        });
        assertDoesNotThrow(() -> {
            order.addCateringMenuItem(cateringMenuItemId, 10);
        });
    }

    @Test
    void canRemoveACateringMenuItemFromAnOrder() {
        CateringMenuItemId cateringMenuItemId = new CateringMenuItemId();
        order.addCateringMenuItem(cateringMenuItemId, 2);

        order.removeCateringMenuItem(cateringMenuItemId);

        assertFalse(order.getOrderedCateringMenuItems()
                .contains(new OrderedCateringMenuItem(cateringMenuItemId,2)));
        assertTrue(order.getOrderedCateringMenuItems().isEmpty());
    }



}
