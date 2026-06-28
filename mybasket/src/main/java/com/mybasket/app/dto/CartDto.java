package com.mybasket.app.dto;

import com.mybasket.app.entity.CartItems;
import com.mybasket.app.entity.User;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {


    private Long id;

    private int cartTotalItems;

    private Double totalAmount;

    private UserDto user;

    private List<CartItemDTo> cartItems=new ArrayList<>();
}
