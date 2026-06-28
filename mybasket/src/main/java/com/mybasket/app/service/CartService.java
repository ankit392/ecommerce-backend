package com.mybasket.app.service;

import com.mybasket.app.dto.CartDto;

public interface CartService {

    CartDto addProductToCart(Long userId, Long productId, Long quantity);


     CartDto getUserByCart(Long userId);

    CartDto deleteProductById(Long useriId, Long productId);

    CartDto emptyCart(Long userId);
}
