package com.mybasket.app.service.impl;

import com.mybasket.app.dto.CartDto;
import com.mybasket.app.entity.Cart;
import com.mybasket.app.entity.CartItems;
import com.mybasket.app.entity.Product;
import com.mybasket.app.entity.User;
import com.mybasket.app.exception.ResourceNotFoundException;
import com.mybasket.app.repository.CartRepository;
import com.mybasket.app.repository.ProductRepository;
import com.mybasket.app.repository.UserRepository;
import com.mybasket.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartDto addProductToCart(Long userId, Long productId, Long quantity) {


        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElse(null);

        if(cart==null){

            cart = new Cart();
           cart.setUser(user);
           cart.setCartTotalItems(0);
           cart.setTotalAmount(0.0);

        }

        CartItems cartItem=new CartItems();
        cartItem.setProduct(product);
        cartItem.setQuantity(Math.toIntExact(quantity));
         cartItem.setCart(cart);

         cart.getCartItems().add(cartItem);
         cart.setCartTotalItems((int) (cart.getCartTotalItems()+quantity));
         cart.setTotalAmount((product.getPrice()*quantity)+cart.getTotalAmount());

        Cart save = cartRepository.save(cart);

        return modelMapper.map(save,CartDto.class);


    }

    @Override
    public CartDto getUserByCart(Long userId) {


        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new ResourceNotFoundException("Resource Not found"));


        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found"));

        return modelMapper.map(cart,CartDto.class);


    }

    @Override
    public CartDto deleteProductById(Long useriId, Long productId) {


        User user = userRepository.findById(Math.toIntExact(useriId)).orElseThrow(() -> new ResourceNotFoundException("User Not found"));

        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found"));

        CartItems items=null;

        for(CartItems item:cart.getCartItems()){

            if(item.getProduct().getProductId().equals(productId)){
                items=item;
                break;

            }
        }
        if(items == null){
            throw new ResourceNotFoundException("Product not found in cart");
        }
        cart.getCartItems().remove(items);

        cart.setCartTotalItems(cart.getCartTotalItems()- items.getQuantity());
        cart.setTotalAmount(cart.getTotalAmount()-(items.getProduct().getPrice()* items.getQuantity()));


        Cart newsavedCart = cartRepository.save(cart);


        return modelMapper.map(newsavedCart,CartDto.class);

    }

    @Override
    public CartDto emptyCart(Long userId) {

        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found"));


        cart.getCartItems().clear();
        cart.setCartTotalItems(0);
        cart.setTotalAmount(0.0);

        Cart save = cartRepository.save(cart);

        return modelMapper.map(save,CartDto.class);


    }


}
