package com.zunino.sport.persistence.dto;

public record CreateOrderRequestDTO(    Long userId,
                                        String shippingAddress) {
}
