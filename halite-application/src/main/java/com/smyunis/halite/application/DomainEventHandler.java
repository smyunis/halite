package com.smyunis.halite.application;

import com.smyunis.halite.domain.DomainEvent;

@FunctionalInterface
public interface DomainEventHandler {
    void handleEvent(DomainEvent domainEvent);
}
