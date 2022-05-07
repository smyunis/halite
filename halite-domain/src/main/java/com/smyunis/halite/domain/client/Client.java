package com.smyunis.halite.domain.client;

public class Client {
    private ClientId id = new ClientId();
    private FullName name;

    public ClientId getId() {
        return id;
    }

    void setId(ClientId id) {
        this.id = id;
    }

    public FullName getName() {
        return name;
    }

    void setName(FullName name) {
        this.name = name;
    }
}
