package com.mybasket.app.service;

import com.mybasket.app.dto.OrderDto;
import com.mybasket.app.dto.OrderItemDto;
import com.mybasket.app.entity.OrderStatus;

import java.util.List;

public interface OrderService {


    OrderDto placeOrder(Long userId, Long addressId);

    OrderDto getOrderById(Long orderId);

    OrderDto updateOrderStatus(Long orderId, OrderStatus status);

    List<OrderDto> getOrderByUser(Long userId);
}
