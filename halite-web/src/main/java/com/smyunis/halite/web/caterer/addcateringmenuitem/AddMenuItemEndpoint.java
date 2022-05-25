package com.smyunis.halite.web.caterer.addcateringmenuitem;

import com.smyunis.halite.application.cateringmenuitem.CateringMenuItemService;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.web.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/caterers/")
public class AddMenuItemEndpoint {
    private final CateringMenuItemService cateringMenuItemService;
    private final Mapper<AddMenuItemRequestPayload, CateringMenuItemData> menuItemDataMapper;

    @Autowired
    public AddMenuItemEndpoint(CateringMenuItemService cateringMenuItemService,
                               Mapper<AddMenuItemRequestPayload, CateringMenuItemData> menuItemDataMapper) {
        this.cateringMenuItemService = cateringMenuItemService;
        this.menuItemDataMapper = menuItemDataMapper;
    }

    @PostMapping("{catererId}/menu-items")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCateringMenuItem(@PathVariable String catererId, @RequestBody AddMenuItemRequestPayload payload) {
        CateringMenuItemData cateringMenuItemData = menuItemDataMapper.map(payload);
        cateringMenuItemData.setCatererId(new CatererId(catererId));

        cateringMenuItemService.addMenuItem(cateringMenuItemData);

        //TODO add location header
    }
}
