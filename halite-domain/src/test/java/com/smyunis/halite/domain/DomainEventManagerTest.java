package com.smyunis.halite.domain;

import com.smyunis.halite.domain.billing.Bill;
import com.smyunis.halite.domain.billing.BillData;
import com.smyunis.halite.domain.billing.OutstandingAmount;
import com.smyunis.halite.domain.billing.domainevents.BillSettledEvent;
import com.smyunis.halite.domain.caterer.Caterer;
import com.smyunis.halite.domain.caterer.CatererData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DomainEventManagerTest {

    @Test
    void canAssignHandlerToAnEvent() {
        var domainEventRegistrar = new DomainEventsDispatcher();
        domainEventRegistrar.assignHandler(BillSettledEvent.class, (DomainEvent event) -> {});
    }

    @Test
    void assignAHandlerAndVerifyStateChangeForAShunt() {

        var domainEventManager = new DomainEventsDispatcher();

        var caterer = new Caterer(new CatererData());
        int rec = caterer.getRecommendationMetric();

        domainEventManager.assignHandler(BillSettledEvent.class,
                caterer.new BillSettledEventHandler());

        Bill bill = new Bill(new BillData().setOutstandingAmount(new OutstandingAmount(230000)));
        domainEventManager.registerDomainEvents(List.of(new BillSettledEvent(bill)));

        domainEventManager.publish();

        assertEquals(5,caterer.getRecommendationMetric());
    }


    @Test
    void canPublishEventsWhoHadHandlersAssignedToThem() {
        var domainEventRegistrar = new DomainEventsDispatcher();
        domainEventRegistrar.publish();
    }

    @Test
    void canRegisterDomainEvents() {
        var domainEventRegistrar = new DomainEventsDispatcher();
        domainEventRegistrar.registerDomainEvents(
                List.of(new BillSettledEvent(new Bill(new BillData())))
        );
    }

    @Test
    void publishARRegisteredEvent() {
        var domainEventManager = new DomainEventsDispatcher();
        StringBuilder sb = new StringBuilder("A");
        domainEventManager.assignHandler(BillSettledEvent.class,(event) -> {
            sb.append("B");
        });
        domainEventManager.registerDomainEvents(List.of(new BillSettledEvent(null)));

        domainEventManager.publish();

        assertEquals("AB",sb.toString());
    }


    @Test
    void canHandleMultipleHandlersForAnEvent() {
        var domainEventManager = new DomainEventsDispatcher();
        StringBuilder sb = new StringBuilder("A");
        domainEventManager.registerDomainEvents(List.of(new BillSettledEvent(new Bill(new BillData()
                .setOutstandingAmount(new OutstandingAmount(7000))))));
        domainEventManager.assignHandler(BillSettledEvent.class,(event) -> {
            sb.append("B");
        });
        domainEventManager.assignHandler(BillSettledEvent.class,(event) -> {
            sb.append("C");
        });

        domainEventManager.publish();

        assertEquals("ABC",sb.toString());
    }

    @Test
    void onceEventsArePublishedTheyGetClearedFromTheManager() {
        var domainEventManager = new DomainEventsDispatcher();
        StringBuilder sb = new StringBuilder("A");
        domainEventManager.registerDomainEvents(List.of(new BillSettledEvent(null)));
        domainEventManager.assignHandler(BillSettledEvent.class,(event) -> {
            sb.append("B");
        });
        domainEventManager.publish();
        // At this point sb should hold "AB"

        domainEventManager.publish();   // should do nothing

        assertEquals("AB",sb.toString());
        assertNotEquals("ABB",sb.toString());
    }
}
