package com.smyunis.halite.domain.client;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClientIdTest {

    @Test
    void canGetANewGeneratedIdThatIsNeitherNullNorEmpty() {
        ClientId id = new ClientId();
        assertIdIsNotNullNorEmpty(id);
    }

    @Test
    void canSetCustomIdThroughConstructor() {
        ClientId id = new ClientId(UUID.randomUUID());
        assertIdIsNotNullNorEmpty(id);
    }

    @Test
    void canSetCustomIdAndCanGetBackTheSameId() {
        UUID newId = UUID.randomUUID();
        String newIdString = newId.toString();
        ClientId id = new ClientId(newId);

        String idString = id.toString();

        assertEquals(newIdString, idString);
    }

    private void assertIdIsNotNullNorEmpty(ClientId id) {
        String idString = id.toString();
        assertNotNull(idString);
        assertNotEquals(idString, "");
    }
}
