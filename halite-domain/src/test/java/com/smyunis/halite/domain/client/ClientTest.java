package com.smyunis.halite.domain.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    private ClientBuilder clientBuilder;

    @BeforeEach
    void setup() {
        clientBuilder = new ClientBuilder();
    }

   @Test
    void canSetAndGetClientName() {
        clientBuilder.setName(new FullName("James","Bond"));
        var client = clientBuilder.build();

        assertEquals("James Bond",client.getName().getFullName());
   }

   @Test
    void newClientHasAnRandomlyGeneratedNewId() {
        var client = clientBuilder.build();
        assertNotNull(client.getId().toString());
        assertNotEquals("",client.getId().toString());
   }

    @Test
    void canSetIdOnNewClient() {
        String newId = "42b32f8d-a52d-4dfe-800a-911936729730";
        clientBuilder.setId(newId);
        var client = clientBuilder.build();
        assertEquals(newId,client.getId().toString());
    }


}
