package com.smyunis.halite.web.order.acceptorder;

import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.order.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class AcceptOrderEndpoint {

    private final OrderService orderService;

    @Autowired
    public AcceptOrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("{orderId}/accept")
    @ResponseStatus(HttpStatus.CREATED)
    public void acceptOrder(@PathVariable String orderId) {
        orderService.acceptOrder(new OrderId(orderId));
    }
}
