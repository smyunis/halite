package com.smyunis.halite.web.order.rejectorder;

import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.order.OrderId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/orders")
public class RejectOrderEndpoint {
    private final OrderService orderService;

    @Autowired
    public RejectOrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("{orderId}/reject")
    @ResponseStatus(HttpStatus.CREATED)
    public void rejectOrder(@PathVariable String orderId) {
        orderService.rejectOrder(new OrderId(orderId));
    }
}
