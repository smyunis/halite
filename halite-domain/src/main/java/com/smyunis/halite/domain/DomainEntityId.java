package com.smyunis.halite.domain;

import java.util.UUID;

public abstract class DomainEntityId {
    private final String id;
    protected DomainEntityId(String id) {
        this.id = id;
    }

    protected DomainEntityId() {
        this(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
