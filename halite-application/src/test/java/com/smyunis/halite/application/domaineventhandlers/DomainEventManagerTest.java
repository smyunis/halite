package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.application.caterer.CatererBillSettledEventHandler;
import com.smyunis.halite.domain.DomainEvent;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.caterer.CatererRepository;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DomainEventManagerTest {

    @Test
    void canAssignHandlerToAnEvent() {
        var domainEventRegistrar = new DomainEventDispatcher();
        domainEventRegistrar.assignHandler(BillSettledEvent.class, (DomainEvent event) -> {
        });
    }

    @Test
    void assignAHandlerAndVerifyStateChange() {

        var domainEventManager = new DomainEventDispatcher();

        var catererId = new CatererId();
        var caterer = new Caterer(new CatererData().setId(catererId));
        int rec = caterer.getRecommendationMetric();

        CatererRepository catererRepository = mock(CatererRepository.class);
        when(catererRepository.get(catererId)).thenReturn(caterer);

        domainEventManager.assignHandler(BillSettledEvent.class,
                new CatererBillSettledEventHandler(catererRepository));

        Order order = new Order(new OrderData().setCatererId(catererId));
        order.incrementBillOutstandingAmount(new MonetaryAmount(23000));

        domainEventManager.registerDomainEvents(List.of(new BillSettledEvent(order)));

        domainEventManager.publish();

        assertEquals(5, caterer.getRecommendationMetric());
    }


    @Test
    void canPublishEventsWhoHadHandlersAssignedToThem() {
        var domainEventRegistrar = new DomainEventDispatcher();
        domainEventRegistrar.publish();
    }

    @Test
    void canRegisterDomainEvents() {
        var domainEventRegistrar = new DomainEventDispatcher();
        domainEventRegistrar.registerDomainEvents(
                List.of(new BillSettledEvent(new Order(new OrderData())))
        );
    }

    @Test
    void publishARegisteredEvent() {
        var domainEventManager = new DomainEventDispatcher();
        StringBuilder sb = new StringBuilder("A");

        var event = new DomainEvent() {

        };

        domainEventManager.assignHandler(event.getClass(), e -> {
            sb.append("B");
        });
        domainEventManager.registerDomainEvents(List.of(event));

        domainEventManager.publish();

        assertEquals("AB", sb.toString());
    }


    @Test
    void canHandleMultipleHandlersForAnEvent() {
        var domainEventManager = new DomainEventDispatcher();
        StringBuilder sb = new StringBuilder("A");
        var event = new DomainEvent() {};


        domainEventManager.registerDomainEvents(List.of(event));
        domainEventManager.assignHandler(event.getClass(), (e) -> {
            sb.append("B");
        });
        domainEventManager.assignHandler(event.getClass(), (e) -> {
            sb.append("C");
        });

        domainEventManager.publish();

        assertEquals("ABC", sb.toString());
    }

    @Test
    void onceEventsArePublishedTheyGetClearedFromTheManager() {
        var domainEventManager = new DomainEventDispatcher();
        StringBuilder sb = new StringBuilder("A");
        domainEventManager.registerDomainEvents(List.of(new BillSettledEvent(null)));
        domainEventManager.assignHandler(BillSettledEvent.class, (event) -> {
            sb.append("B");
        });
        domainEventManager.publish();
        // At this point sb should hold "AB"

        domainEventManager.publish();   // should do nothing

        assertEquals("AB", sb.toString());
        assertNotEquals("ABB", sb.toString());
    }
}
