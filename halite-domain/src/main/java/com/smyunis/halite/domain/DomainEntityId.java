package com.smyunis.halite.domain;

import java.util.Objects;
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
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntityId that = (DomainEntityId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
