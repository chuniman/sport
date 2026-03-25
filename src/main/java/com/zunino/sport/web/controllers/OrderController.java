package com.zunino.sport.web.controllers;

import com.zunino.sport.persistence.dto.CreateOrderRequestDTO;
import com.zunino.sport.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/buy/{userId}")
    public ResponseEntity<?> createOrder(@PathVariable Long userId,@RequestParam Optional<String> newShippingAddress) {
        String result = orderService.createOrder(userId, newShippingAddress);
        return ResponseEntity.ok(Map.of("orderNumber", result));
    }

    @PostMapping("/buy/{userId}")
    public ResponseEntity<?> createOrder(
            @PathVariable Long userId,
            @RequestBody CreateOrderRequestDTO requestBody
    ) {

        String result = orderService.createOrder(userId,  Optional.ofNullable(requestBody.shippingAddress()));
        return ResponseEntity.ok(Map.of("orderNumber", result));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

}
