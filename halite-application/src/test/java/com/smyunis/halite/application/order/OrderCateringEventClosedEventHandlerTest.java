package com.smyunis.halite.application.order;

import com.smyunis.halite.domain.cateringevent.CateringEvent;
import com.smyunis.halite.domain.cateringevent.CateringEventData;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringevent.domainevents.CateringEventClosedEvent;
import com.smyunis.halite.domain.order.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderCateringEventClosedEventHandlerTest {
    private OrderCateringEventClosedEventHandler handler;
    private OrderRepository orderRepository;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        handler = new OrderCateringEventClosedEventHandler(orderRepository);
    }

    @Test
    void canHandleCateringEventClosedEvent() {
        var cateringEventId = new CateringEventId();
        var cateringEventData = new CateringEventData()
                .setId(cateringEventId);
        var cateringEvent = new CateringEvent(cateringEventData);
        var domainEvent = new CateringEventClosedEvent(cateringEvent);

        handler.handleEvent(domainEvent);

        verify(orderRepository).cancelAllOrdersByCateringEventId(cateringEventId);
    }

}
