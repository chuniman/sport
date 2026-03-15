package com.zunino.sport.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_products")
@Getter
@Setter
@NoArgsConstructor
public class CartProductEntity {

    @EmbeddedId
    private CartProductId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "id_product")
    private ProductEntity product;

    private Integer quantity;

}