package com.smyunis.halite.web.order.createorder;

import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.web.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/orders")
public class CreateOrderEndpoint {

    private final OrderService orderService;
    private final Mapper<CreateOrderRequestPayload, OrderData> mapper;

    @Autowired
    public CreateOrderEndpoint(OrderService orderService, Mapper<CreateOrderRequestPayload, OrderData> mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequestPayload payload) {
        OrderData newOrderData = mapper.map(payload);
        orderService.createOrder(newOrderData);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrderData.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
