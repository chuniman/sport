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

    @Id
    @Column(name = "id_cart_product")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCartProduct;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity idUser;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity idProduct;

    private Integer quantity;

}