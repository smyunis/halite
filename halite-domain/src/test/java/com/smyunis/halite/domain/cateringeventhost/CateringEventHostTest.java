package com.smyunis.halite.domain.cateringeventhost;

import org.junit.jupiter.api.BeforeEach;

public class CateringEventHostTest {
    private CateringEventHost cateringEventHost;
    private CateringEventHostData cateringEventHostData;

    @BeforeEach
    void setup() {
        cateringEventHostData = new CateringEventHostData();
        cateringEventHost = new CateringEventHost(cateringEventHostData);
    }




}
