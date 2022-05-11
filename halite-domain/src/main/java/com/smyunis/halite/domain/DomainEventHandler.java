package com.smyunis.halite.domain;

public interface DomainEventHandler <T extends DomainEvent>{
    void handleEvent(T domainEvent);
}
