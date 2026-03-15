package com.zunino.sport.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CartProductId implements Serializable {

    @Column(name = "id_user")
    private Long userId;

    @Column(name = "id_product")
    private Long productId;

}
