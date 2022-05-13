package com.smyunis.halite.domain;

@FunctionalInterface
public interface DomainEventHandler <T extends DomainEvent>{
    void handleEvent(T domainEvent);
}
