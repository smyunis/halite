package com.smyunis.halite.domain.caterer;

import com.smyunis.halite.domain.cateringmenu.CateringMenuId;
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


    @Test
    void aHostCanAddReviewsToACatererWhoServedThatEvent () {
        Review review = new Review();
        caterer.addReview(review);

        assertTrue(caterer.getReviews().contains(review));
        assertFalse(caterer.getReviews().isEmpty());
    }

    @Test
    void addANewCateringMenu() {
        CateringMenuId cateringMenuId = new CateringMenuId();

        caterer.addCateringMenu(cateringMenuId);
        var menuIds = caterer.getCateringMenus();

        assertTrue(menuIds.contains(cateringMenuId));
    }

    @Test
    void removeACateringMenu() {
        CateringMenuId cateringMenuId = new CateringMenuId();
        caterer.addCateringMenu(cateringMenuId);

        caterer.removeCateringMenu(cateringMenuId);
        var menuIds = caterer.getCateringMenus();

        assertFalse(menuIds.contains(cateringMenuId));
    }

//    @Test
//    void updateCateringMenuEnsuresItIsInCaterersMenu() {
//        CateringMenu updatedCateringMenu = new CateringMenu();
//
//        caterer.updateCateringMenu(updatedCateringMenu);
//
//        assertTrue(caterer.getCateringMenus().contains(updatedCateringMenu.getId()));
//    }
//
//    @Test
//    void updateCateringMenuRaisesCateringMenuUpdatedDomainEvent() {
//        CateringMenu oldCateringMenu = new CateringMenu();
//        oldCateringMenu.setName("Gourmet Meal Menu");
//        caterer.addCateringMenu(oldCateringMenu.getId());
//
//        CateringMenu updatedCateringMenu = oldCateringMenu;
//        updatedCateringMenu.setName("New Fancy Meal Menu");
//        caterer.updateCateringMenu(updatedCateringMenu);
//        List<DomainEvent> domainEvents = caterer.getDomainEvents();
//
//        assertTrue(domainEvents.get(0) instanceof CateringMenuUpdatedEvent);
//    }




}
