package com.zunino.sport.persistence.dto;

public record OrderItemDTO(
        ProductDTO product,
        Integer quantity,
        Double price
) {}
