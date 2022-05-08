package com.smyunis.halite.domain.cateringmenu;

import com.smyunis.halite.domain.caterer.domainevents.CateringMenuUpdatedEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CateringMenuTest {
    @Test
    void canHandleCateringMenuUpdatedDomainEventAndUpdatedAccordingly() {
        CateringMenu toBeUpdatedCateringMenu = new CateringMenu();
        toBeUpdatedCateringMenu.setName("Old Menu Name");

        CateringMenu newCateringMenu = new CateringMenu();
        newCateringMenu.setName("New Menu Name");

        toBeUpdatedCateringMenu
                .new DomainEventHandler()
                .handleCateringMenuUpdated(new CateringMenuUpdatedEvent(newCateringMenu));

        assertEquals("New Menu Name",toBeUpdatedCateringMenu.getName());
    }

    @Test
    void updateCateringMenu() {
        CateringMenu toBeUpdatedCateringMenu = new CateringMenu();
        toBeUpdatedCateringMenu.setName("Old Menu Name");

        CateringMenu newCateringMenu = new CateringMenu();
        newCateringMenu.setName("New Menu Name");

        toBeUpdatedCateringMenu.updateCateringMenu(newCateringMenu);

        assertEquals("New Menu Name",toBeUpdatedCateringMenu.getName());
    }

}
