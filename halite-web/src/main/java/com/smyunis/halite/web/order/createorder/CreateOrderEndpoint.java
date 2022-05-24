package com.smyunis.halite.web.order.createorder;

import com.smyunis.halite.application.order.OrderService;
import com.smyunis.halite.domain.order.OrderData;
import com.smyunis.halite.web.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        URI location = getCreatedResourceLocation(newOrderData);
        return ResponseEntity.created(location).build();
    }

    private URI getCreatedResourceLocation(OrderData newOrderData) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newOrderData.getId())
                .toUri();
    }
}

