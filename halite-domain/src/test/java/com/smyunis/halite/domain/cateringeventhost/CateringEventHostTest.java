package com.smyunis.halite.domain.cateringeventhost;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CateringEventHostTest {
    private CateringEventHost cateringEventHost;

    @BeforeEach
    void setup() {
        cateringEventHost = new CateringEventHost();
    }

    @Test
    void newClientHasARandomlyGeneratedNewId() {
        assertNotNull(cateringEventHost.getId().toString());
        assertNotEquals("", cateringEventHost.getId().toString());
    }

    @Test
    void canSetIdOnNewClient() {
        String newId = "42b32f8d-a52d-4dfe-800a-911936729730";

        cateringEventHost.setId(new CateringEventHostId(newId));

        assertEquals(newId, cateringEventHost.getId().toString());
    }

    @Test
    void canSetAndGetClientPhoneNumber() {
        String phoneNumber = "0929186232";

        cateringEventHost.setPhoneNumber(new PhoneNumber(phoneNumber));

        assertEquals(phoneNumber, cateringEventHost.getPhoneNumber().phoneNumber());

    }

}
