package com.smyunis.halite.application.cateringmenuitem;

import com.smyunis.halite.application.domaineventhandlers.DomainEventDispatcher;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CateringMenuItemServiceTest {

    private CateringMenuItemService cateringMenuItemService;
    private CateringMenuItemData data;
    private CateringMenuItemRepository cateringMenuItemRepository;
    private CateringMenuItemId cateringMenuItemId;

    @BeforeEach
    void setup() {
        data = new CateringMenuItemData()
                .setId(cateringMenuItemId);
        cateringMenuItemId = new CateringMenuItemId();
        cateringMenuItemRepository = mock(CateringMenuItemRepository.class);
        when(cateringMenuItemRepository.get(cateringMenuItemId)).thenReturn(new CateringMenuItem(data));
        DomainEventDispatcher eventDispatcher = new DomainEventDispatcher();
        cateringMenuItemService = new CateringMenuItemService(eventDispatcher,cateringMenuItemRepository);
    }


    @Test
    void canAddNewCateringMenuItem() {
        cateringMenuItemService.addMenuItem(data);

        verify(cateringMenuItemRepository).save(any(CateringMenuItem.class));
    }

    @Test
    void canRemoveACateringMenuItem() {
        cateringMenuItemService.removeMenuItem(cateringMenuItemId);

        verify(cateringMenuItemRepository).get(cateringMenuItemId);
        verify(cateringMenuItemRepository).remove(cateringMenuItemId);
    }
}
