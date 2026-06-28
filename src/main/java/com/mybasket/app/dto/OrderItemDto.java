package com.mybasket.app.dto;

import com.mybasket.app.entity.Order;
import com.mybasket.app.entity.Product;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;

    private ProductDto product;

    private Integer quantity;

}
