package com.mybasket.app.dto;

import com.mybasket.app.entity.Cart;
import com.mybasket.app.entity.Product;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDTo {

    private Long id;


    private ProductDto product;


  //  private CartDto cart;

    private int quantity;

}
