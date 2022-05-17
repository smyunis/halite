package com.smyunis.halite.application.cateringevent;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.cateringevent.CateringEvent;
import com.smyunis.halite.domain.cateringevent.CateringEventData;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringevent.CateringEventRepository;

public class CateringEventService {
    private final DomainEventDispatcher eventDispatcher;
    private final CateringEventRepository cateringEventRepository;

    public CateringEventService(DomainEventDispatcher eventDispatcher, CateringEventRepository cateringEventRepository) {
        this.eventDispatcher = eventDispatcher;
        this.cateringEventRepository = cateringEventRepository;
    }

    public void createCateringEvent(CateringEventData cateringEventData) {
        CateringEvent cateringEvent = new CateringEvent(cateringEventData);
        cateringEventRepository.save(cateringEvent);
    }

    public void closeCateringEvent(CateringEventId cateringEventId) {
        CateringEvent closedCateringEvent = cateringEventRepository.get(cateringEventId);
        closedCateringEvent.closeCateringEvent();
        cateringEventRepository.save(closedCateringEvent);
        dispatchDomainEvents(closedCateringEvent);
    }

    private void dispatchDomainEvents(CateringEvent closedCateringEvent) {
        eventDispatcher.registerDomainEvents(closedCateringEvent.getDomainEvents());
        eventDispatcher.publish();
    }
}
