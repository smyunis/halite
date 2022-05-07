package com.smyunis.halite.domain.client;

import com.smyunis.halite.domain.AggregateBuilder;

import java.util.UUID;

public class ClientBuilder implements AggregateBuilder<Client> {

    private Client client = new Client();

    @Override
    public Client build() {
        return client;
    }

    public void setName(FullName fullName) {
        client.setName(fullName);
    }

    public void setId(String newId) {
        UUID id = UUID.fromString(newId);
        var clientId = new ClientId(id);
        client.setId(clientId);
    }
}
