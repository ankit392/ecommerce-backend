package com.mybasket.app.controller;


import com.mybasket.app.dto.OrderDto;
import com.mybasket.app.entity.OrderStatus;
import com.mybasket.app.service.OrderService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{userId}/{addressId}")
    public ResponseEntity<OrderDto> placeOrder(@PathVariable Long userId,@PathVariable Long addressId){

        OrderDto orderDto = orderService.placeOrder(userId, addressId);

        return new ResponseEntity<>(orderDto,HttpStatus.CREATED);


    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId){

        OrderDto orderById = orderService.getOrderById(orderId);

        return new ResponseEntity<>(orderById,HttpStatus.OK);

    }

    //updateorderByStatus

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId, @RequestParam OrderStatus status){


        OrderDto orderDto = orderService.updateOrderStatus(orderId, status);

        return new ResponseEntity<>(orderDto,HttpStatus.OK);

    }

    //getOrderByUser

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>>  getOrderByUser(@PathVariable Long userId){


        List<OrderDto> orderByUser = orderService.getOrderByUser(userId);
        return new ResponseEntity<>(orderByUser,HttpStatus.OK);

    }



}
