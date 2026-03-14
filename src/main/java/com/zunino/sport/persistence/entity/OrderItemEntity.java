package com.zunino.sport.persistence.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
public class OrderItemEntity {

    @Id
    @Column(name = "id_order_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrderItem;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    @JsonBackReference
    private OrderEntity idOrder;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false)
    private ProductEntity idProduct;

    private Integer quantity;

    private Double price;
}
