package com.smyunis.halite.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class DomainEntityId {
    private final String idString;

    protected DomainEntityId(String idString) {
        this.idString = idString;
    }

    protected DomainEntityId() {
        this(UUID.randomUUID().toString());
    }

    public String getIdString() {
        return idString;
    }

    @Override
    public String toString() {
        return idString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainEntityId that = (DomainEntityId) o;
        return idString.equals(that.idString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idString);
    }
}
