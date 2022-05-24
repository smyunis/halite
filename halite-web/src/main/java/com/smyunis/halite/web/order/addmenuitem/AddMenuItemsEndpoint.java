package com.smyunis.halite.web.order.addmenuitem;

import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.domain.order.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class AddMenuItemsEndpoint {
    private final OrderService orderService;

    @Autowired
    public AddMenuItemsEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("{id}/menu-items")
    public ResponseEntity<?> addCateringMenuItems(@PathVariable String id,
                                               @RequestBody List<OrderedCateringMenuItemPayload> payload) {
        for (var menuItem : payload) {
            orderService.addCateringMenuItem(new OrderId(id),
                    new CateringMenuItemId(menuItem.getOrderedMenuItemId()),
                    menuItem.getOrderedQuantity());
        }

        return ResponseEntity.created(getCreatedResourceLocation(id)).build();
    }

    private URI getCreatedResourceLocation(String orderId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replacePath("/{id}")
                .buildAndExpand(orderId)
                .toUri();
    }
}
