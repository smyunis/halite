package com.smyunis.halite.domain.caterer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CatererTest {
    private Caterer caterer;

    @BeforeEach
    void setup() {
        caterer = new Caterer();
    }

    @Test
    void canSetAndGetId() {
        CatererId newId = new CatererId("Caterer-Id-001");

        caterer.setId(newId);
        CatererId id = caterer.getId();

        assertEquals(newId, id);
        assertEquals(newId.toString(),id.toString());
        assertNotNull(new Caterer().getId().toString());
        assertNotEquals("",new Caterer().getId().toString());
    }

}
