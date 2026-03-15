package com.zunino.sport.service;

import com.zunino.sport.persistence.dto.OrderDTO;
import com.zunino.sport.persistence.entity.CartProductEntity;
import com.zunino.sport.persistence.entity.OrderEntity;
import com.zunino.sport.persistence.entity.OrderItemEntity;
import com.zunino.sport.persistence.entity.UserEntity;
import com.zunino.sport.persistence.exception.UserNotFoundException;
import com.zunino.sport.persistence.mapper.OrderMapper;
import com.zunino.sport.persistence.repository.CartProductRepository;
import com.zunino.sport.persistence.repository.OrderRepository;
import com.zunino.sport.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private CartProductRepository cartProductRepository;
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private OrderMapper orderMapper;

    public OrderService(CartProductRepository cartProductRepository, OrderRepository orderRepository,
                        UserRepository userRepository, OrderMapper orderMapper) {
        this.cartProductRepository = cartProductRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    public String createOrder(Long userId) {
        List<CartProductEntity> cartProducts = cartProductRepository.findByIdUserId(userId);
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setUser(user);
        orderEntity.setOrderStatus("CREATED");
        String trackingNumber = UUID.randomUUID().toString();
        orderEntity.setOrderNumber(trackingNumber);
        orderEntity.setShippingAddress(user.getShippingAddress());
        orderEntity.setItems(new ArrayList<>());

        for (CartProductEntity cartProduct : cartProducts) {
            OrderItemEntity  orderItemEntity = new OrderItemEntity();
            orderItemEntity.setProduct(cartProduct.getProduct());
            orderItemEntity.setQuantity(cartProduct.getQuantity());
            orderItemEntity.setPrice(cartProduct.getProduct().getPrice());
            orderEntity.addOrderItem(orderItemEntity);
        }
        orderRepository.save(orderEntity);
        cartProductRepository.deleteAll(cartProducts);

        return orderEntity.getOrderNumber();
    }

    public List<OrderDTO> getOrdersByUserId(Long idUser) {
        List<OrderEntity> orderEntityList = orderRepository.findByUserIdUser(idUser);
        return orderMapper.toDtoList(orderEntityList);

    }


}
