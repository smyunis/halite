package com.smyunis.halite.domain.order;

import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.domainexceptions.InvalidOperationException;
import com.smyunis.halite.domain.domainexceptions.InvalidValueException;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemAddedToOrderEvent;
import com.smyunis.halite.domain.order.domainevents.CateringMenuItemRemovedFromOrderEvent;
import com.smyunis.halite.domain.order.domainevents.OrderFulfilledEvent;
import com.smyunis.halite.domain.order.domainevents.OrderRejectedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
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

        order.removeCateringMenuItem(cateringMenuItemId, 2);

        assertFalse(order.getOrderedCateringMenuItems().containsKey(cateringMenuItemId));
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
    void addingDuplicateMenuItemIncreasesQuantity() {
        CateringMenuItemId itemId = new CateringMenuItemId();

        order.addCateringMenuItem(itemId, 2);
        order.addCateringMenuItem(itemId, 2);

        assertEquals(Integer.valueOf(4), order.getOrderedCateringMenuItems().values().stream().mapToInt(Integer::valueOf).sum());
        assertEquals(1, order.getOrderedCateringMenuItems().size());
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

        order.removeCateringMenuItem(itemId, 1);

        List<DomainEvent> domainEvents = order.getDomainEvents();

        assertEquals(CateringMenuItemRemovedFromOrderEvent.class, domainEvents.get(1).getClass());
        assertEquals(itemId, ((CateringMenuItemRemovedFromOrderEvent) domainEvents.get(1)).getItemId());
    }

    @Test
    void canNotSetQuantityOfOrderedItemToBeLessThanOne() {
        var m = new HashMap<CateringMenuItemId, Integer>();
        m.put(new CateringMenuItemId(), -1);
        assertThrows(InvalidValueException.class, () -> {
            data.setOrderedCateringMenuItems(m);
        });
    }

    @Test
    void rejectingAnOrderRaisesOrderRejectedEvent() {
        order.reject();

        var events = order.getDomainEvents();
        assertEquals(OrderRejectedEvent.class, events.get(0).getClass());
    }

    @Test
    void removingAMenuItemWithQualityEqualToAvailableWillRemoveItem() {
        CateringMenuItemId itemId = new CateringMenuItemId();
        order.addCateringMenuItem(itemId,10);
        order.removeCateringMenuItem(itemId,10);

        assertNull(order.getOrderedCateringMenuItems().get(itemId));
        assertFalse(order.getOrderedCateringMenuItems().containsKey(itemId));
    }

    @Test
    void removingAMenuItemWithQualityLessThanAvailableWillDecreaseRemainingQuantity() {
        CateringMenuItemId itemId = new CateringMenuItemId();
        order.addCateringMenuItem(itemId,10);
        order.removeCateringMenuItem(itemId,5);

        assertEquals(5,order.getOrderedCateringMenuItems().get(itemId));
    }

    @Test
    void acceptedOrderCanGetFulfilled() {
        order.accept();
        order.fulfill();
        assertThrows(InvalidOperationException.class,() -> {
            order.fulfill();
        });
    }

    @Test
    void fulfillingAnOrderRaisesOrderFulfilledEvent(){
        order.accept();
        order.fulfill();

        var events = order.getDomainEvents();
        assertEquals(OrderFulfilledEvent.class,events.get(0).getClass());
    }

    @Test
    void canCancelAnAcceptedOrder() {
        order.accept();
        order.cancel();

        assertThrows(InvalidOperationException.class,() -> {
            order.cancel();
        });
    }

    @Test
    void canCancelAnOrderPendingAcceptance() {
       assertDoesNotThrow(() -> {
           order.cancel();
       });
    }


}
