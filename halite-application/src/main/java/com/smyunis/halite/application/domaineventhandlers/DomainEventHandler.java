package com.smyunis.halite.application.domaineventhandlers;

import com.smyunis.halite.domain.DomainEvent;

@FunctionalInterface
public interface DomainEventHandler <T extends DomainEvent>{
    void handleEvent(T domainEvent);
}
