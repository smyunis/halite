package com.smyunis.halite.web.cateringmenuitem.getcateringmenuitems;

import com.smyunis.halite.application.cateringmenuitem.CateringMenuItemService;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/caterers/")
public class GetCateringMenuItemsEndpoint {

    private final CateringMenuItemService cateringMenuItemService;

    @Autowired
    public GetCateringMenuItemsEndpoint(CateringMenuItemService cateringMenuItemService) {
        this.cateringMenuItemService = cateringMenuItemService;
    }


    @GetMapping("{catererId}/menu-items")
    public List<GetCateringMenuItemResponsePayload> getAllCateringMenuItemsForACaterer(@PathVariable String catererId) {
        return cateringMenuItemService.getAllMenuItemsByCatererId(new CatererId(catererId))
                .stream()
                .map(item -> {
                    var itemData = item.getDataReadOnlyProxy();
                    return new GetCateringMenuItemResponsePayload()
                            .setName(itemData.getName())
                            .setPrice(itemData.getPrice().amount())
                            .setImages(itemData.getImages())
                            .setIngredients(itemData.getIngredients());
                })
                .collect(Collectors.toList());
    }
}
