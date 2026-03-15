package com.zunino.sport.web.controllers;

import com.zunino.sport.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/buy/{userId}")
    public ResponseEntity<?> createOrder(@PathVariable Long userId) {
        String result = orderService.createOrder(userId);
        return ResponseEntity.ok(Map.of("orderNumber", result));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getOrders(@PathVariable Long userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

}
