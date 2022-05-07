package com.smyunis.halite.domain.client;

import java.util.UUID;

public class ClientId {
    private final UUID id;

    public ClientId() {
        id = UUID.randomUUID();
    }

    public ClientId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
