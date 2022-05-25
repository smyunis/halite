package com.smyunis.halite.application.cateringmenuitem;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;

import java.util.List;

public class CateringMenuItemService {
    private final DomainEventDispatcher eventDispatcher;
    private final CateringMenuItemRepository cateringMenuItemRepository;

    public CateringMenuItemService(DomainEventDispatcher eventDispatcher, CateringMenuItemRepository cateringMenuItemRepository) {
        this.eventDispatcher = eventDispatcher;
        this.cateringMenuItemRepository = cateringMenuItemRepository;
    }

    public void addMenuItem(CateringMenuItemData menuItemPayload) {
        CateringMenuItem cateringMenuItem = new CateringMenuItem(menuItemPayload);
        cateringMenuItemRepository.save(cateringMenuItem);
    }

    public void removeMenuItem(CateringMenuItemId cateringMenuItemId) {
        CateringMenuItem menuItem = cateringMenuItemRepository.get(cateringMenuItemId);
        menuItem.asRemovedMenuItem();
        cateringMenuItemRepository.remove(cateringMenuItemId);

        dispatchDomainEvents(menuItem);
    }

    private void dispatchDomainEvents(CateringMenuItem menuItem) {
        eventDispatcher.registerDomainEvents(menuItem.getDomainEvents());
        eventDispatcher.publish();
    }

    public List<CateringMenuItem> getAllMenuItemsByCatererId(CatererId catererId){
        return cateringMenuItemRepository.getAllByCatererId(catererId);
    }
}
