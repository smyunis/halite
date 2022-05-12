package com.smyunis.halite.domain.cateringmenu;

import org.junit.jupiter.api.BeforeEach;

public class CateringMenuTest {

    private CateringMenu cateringMenu;
    private CateringMenuData data;

    @BeforeEach
    void setup() {
        data = new CateringMenuData();
        cateringMenu = new CateringMenu(data);
    }

}
