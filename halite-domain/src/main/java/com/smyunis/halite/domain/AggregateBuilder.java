package com.smyunis.halite.domain;

public interface AggregateBuilder<T> {
    AggregateBuilder<T> setId(DomainEntityId id);
    T build();
}
