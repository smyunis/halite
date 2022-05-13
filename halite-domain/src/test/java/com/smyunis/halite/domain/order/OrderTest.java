package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {
    private Order order;
    private OrderData data;

    @BeforeEach
    void setup() {
        data = new OrderData();
        order = new Order(data);
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
                .contains(new OrderedCateringMenuItem(cateringMenuItemId, 2)));
        assertTrue(order.getOrderedCateringMenuItems().isEmpty());
    }

    @Test
    void acceptOrder() {
        order.accept();
    }

    @Test
    void canNotAcceptOrderThatIsPendingAcceptance() {
        data.setStatus(OrderStatus.ACCEPTED);
        assertThrows(InvalidOperationException.class, () -> {
            order.accept();
        });
    }

    @Test
    void canSetOrderStatusToAccepted() {
        order.accept();
        assertEquals(OrderStatus.ACCEPTED, data.getStatus());
    }

    @Test
    void rejectOrder() {
        order.reject();
    }

    @Test
    void canSetStatusWhenRejected() {
        order.reject();
        assertEquals(OrderStatus.REJECTED, data.getStatus());
    }

    @Test
    void canNotRejectAnAlreadyRejectedOrder() {
        order.reject();
        assertThrows(InvalidOperationException.class, () -> {
            order.reject();
        });
    }

    @Test
    void addingAMenuItemRaisesACateringMenuItemAddedToOrderEvent() {
        CateringMenuItemId itemId = new CateringMenuItemId();
        order.addCateringMenuItem(itemId, 1);

        List<DomainEvent> domainEvents = order.getDomainEvents();

        assertEquals(CateringMenuItemAddedToOrderEvent.class, domainEvents.get(0).getClass());
        assertEquals(itemId, ((CateringMenuItemAddedToOrderEvent) domainEvents.get(0)).getAddedItemId());
    }

    @Test
    void removingAMenuItemRaisesACateringMenuItemRemovedFromOrderEvent() {
        CateringMenuItemId itemId = new CateringMenuItemId();
        order.addCateringMenuItem(itemId, 1);

        order.removeCateringMenuItem(itemId);

        List<DomainEvent> domainEvents = order.getDomainEvents();

        assertEquals(CateringMenuItemRemovedFromOrderEvent.class,domainEvents.get(1).getClass());
        assertEquals(itemId, ((CateringMenuItemRemovedFromOrderEvent) domainEvents.get(1)).getRemovedItemId());
    }


}
