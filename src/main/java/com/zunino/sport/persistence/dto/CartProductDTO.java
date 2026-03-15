package com.zunino.sport.persistence.dto;

import jakarta.validation.constraints.NotNull;

//El request de GET productos del carrito
public record CartProductDTO(

		@NotNull
		Long userId,

		@NotNull
		Long productId,

		@NotNull
		Integer quantity

) {}
