package com.smyunis.halite.web.order.getorder;

import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.order.Order;
import com.smyunis.halite.domain.order.OrderDataReadOnlyProxy;
import com.smyunis.halite.domain.order.OrderId;
import com.smyunis.halite.web.Mapper;
import com.smyunis.halite.web.order.getorder.GetOrderResponsePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class GetOrderEndpoint {

    private final OrderService orderService;
    private final Mapper<OrderDataReadOnlyProxy, GetOrderResponsePayload> mapper;

    @Autowired
    public GetOrderEndpoint(OrderService orderService, Mapper<OrderDataReadOnlyProxy, GetOrderResponsePayload> mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping("{orderId}")
    public GetOrderResponsePayload getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(new OrderId(orderId));
        var orderData = order.getDataReadOnlyProxy();
        return mapper.map(orderData);
    }


}
