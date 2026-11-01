package com.mybasket.app.controller;


import com.mybasket.app.dto.CartDto;
import com.mybasket.app.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {


    private final CartService cartService;


    @PostMapping("/{userId}/{productId}")
    public ResponseEntity<CartDto> addProductToCart(

            @PathVariable Long userId,
            @PathVariable Long productId,
            @RequestParam Long quantity
    )
    {

        CartDto cartDto = cartService.addProductToCart(userId, productId, quantity);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);

    }

    //Get User Cart
    @GetMapping("/{userId}")
    public ResponseEntity<CartDto> getUserByCart(@PathVariable Long userId){

        CartDto userByCart = cartService.getUserByCart(userId);
        return new ResponseEntity<>(userByCart,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{useriId}/{productId}")
    public  ResponseEntity<CartDto> deleteProductById(@PathVariable Long useriId,@PathVariable Long productId ){

 
        CartDto cartDto = cartService.deleteProductById(useriId, productId);

        return new ResponseEntity<>(cartDto,HttpStatus.NO_CONTENT);


    }

    @DeleteMapping("{userId}")
    public ResponseEntity<CartDto> empltyCart(@PathVariable Long userId){

        CartDto cartDto = cartService.emptyCart(userId);

        return new ResponseEntity<>(cartDto,HttpStatus.OK);


    }


}
