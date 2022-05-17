package com.smyunis.halite.application.cateringevent;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.cateringevent.CateringEvent;
import com.smyunis.halite.domain.cateringevent.CateringEventData;
import com.smyunis.halite.domain.cateringevent.CateringEventId;
import com.smyunis.halite.domain.cateringevent.CateringEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class CateringEventServiceTest {
    private CateringEventService cateringEventService;
    private CateringEventRepository cateringEventRepository;
    private DomainEventDispatcher eventDispatcher;


    @BeforeEach
    void setup() {
        cateringEventRepository = mock(CateringEventRepository.class);
        eventDispatcher = mock(DomainEventDispatcher.class);
        cateringEventService = new CateringEventService(eventDispatcher,cateringEventRepository);
    }

    @Test
    void canCreateNewCateringEvent() {
        CateringEventData cateringEventData = new CateringEventData();
        cateringEventService.createCateringEvent(cateringEventData);

        verify(cateringEventRepository).save(any(CateringEvent.class));
    }

    @Test
    void canCloseACateringEvent() {
        var cateringEventId = new CateringEventId();
        CateringEvent cateringEvent = new CateringEvent(new CateringEventData()
                .setId(cateringEventId));
        when(cateringEventRepository.get(cateringEventId))
                .thenReturn(cateringEvent);

        cateringEventService.closeCateringEvent(cateringEventId);

        verify(cateringEventRepository).get(cateringEventId);
        verify(cateringEventRepository).save(any(CateringEvent.class));
        verify(eventDispatcher).registerDomainEvents(anyList());
        verify(eventDispatcher).publish();
    }
}
