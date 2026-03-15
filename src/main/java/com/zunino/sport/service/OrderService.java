package com.zunino.sport.service;

import com.zunino.sport.persistence.entity.CartProductEntity;
import com.zunino.sport.persistence.entity.OrderEntity;
import com.zunino.sport.persistence.repository.CartProductRepository;
import com.zunino.sport.persistence.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private CartProductRepository cartProductRepository;
    private OrderRepository orderRepository;

    public OrderService(CartProductRepository cartProductRepository, OrderRepository orderRepository) {
        this.cartProductRepository = cartProductRepository;
        this.orderRepository = orderRepository;
    }

    public Long createOrder(Long userId) {
        List<CartProductEntity> cartProducts = cartProductRepository.findByIdUserId(userId);


    }


}
