package com.smyunis.halite.domain.cateringmenuitem;

import com.smyunis.halite.domain.cateringmenuitem.domainevents.CateringMenuItemRemovedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CateringMenuItemTest {

    private CateringMenuItemData data;
    private CateringMenuItem cateringMenuItem;

    @BeforeEach
    void setup() {
        data = new CateringMenuItemData();
        cateringMenuItem = new CateringMenuItem(data);
    }

    @Test
    void canHandleCateringMenuItemRemovedEvent() {
        CateringMenuItem removedMenuItem = cateringMenuItem.asRemovedMenuItem();
        var events = removedMenuItem.getDomainEvents();

        assertEquals(CateringMenuItemRemovedEvent.class,events.get(0).getClass());
    }
}
