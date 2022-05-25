package com.smyunis.halite.web.caterer.removecateringmenuitem;

import com.smyunis.halite.application.cateringmenuitem.CateringMenuItemService;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/caterers")
public class RemoveCateringMenuItemEndpoint {
    private final CateringMenuItemService cateringMenuItemService;

    @Autowired
    public RemoveCateringMenuItemEndpoint(CateringMenuItemService cateringMenuItemService) {
        this.cateringMenuItemService = cateringMenuItemService;
    }

    @DeleteMapping("{catererId}/menu-items/{cateringMenuItemId}")
    @ResponseStatus(HttpStatus.OK)
    public void removeCateringMenuItem(@PathVariable String catererId,
                                       @PathVariable String cateringMenuItemId) {
        cateringMenuItemService.removeMenuItem(new CateringMenuItemId(cateringMenuItemId));
    }
}
