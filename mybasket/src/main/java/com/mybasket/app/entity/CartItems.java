package com.mybasket.app.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "my-basket-cart-items")
public class CartItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private int quantity;
}
