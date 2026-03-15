package com.zunino.sport.persistence.dto;

public record ProductDTO(
		Long idProduct,
		String name,
		String imageUrl,
		String description,
		Double price,
		Integer stock
) {}
