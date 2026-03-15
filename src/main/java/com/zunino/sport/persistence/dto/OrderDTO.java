package com.zunino.sport.persistence.dto;

import java.util.List;

public record OrderDTO(
        Long idOrder,
        String orderNumber,
        String orderStatus,
        String shippingAddress,
        List<OrderItemDTO> items

) {}