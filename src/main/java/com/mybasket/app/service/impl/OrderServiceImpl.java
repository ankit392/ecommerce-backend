package com.mybasket.app.service.impl;


import com.mybasket.app.dto.OrderDto;
import com.mybasket.app.entity.*;
import com.mybasket.app.exception.ResourceNotFoundException;
import com.mybasket.app.repository.AddressRepository;
import com.mybasket.app.repository.CartRepository;
import com.mybasket.app.repository.OrderRepository;
import com.mybasket.app.repository.UserRepository;
import com.mybasket.app.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.OrderedMapType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CartRepository cartRepository;
    private final AddressRepository addressRepository;

    @Override
    public OrderDto placeOrder(Long userId, Long addressId) {

        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("cart not found"));
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new ResourceNotFoundException("address not found"));


        Order order = new Order();
        order.setAddress(address);
        order.setUser(user);
        order.setOrderStatus(OrderStatus.PLACE);

              for(CartItems item:cart.getCartItems()){

                  //quantity,prodcut copy

                  OrderItem orderItem=new OrderItem();

                  orderItem.setQuantity(item.getQuantity());
                  orderItem.setProduct(item.getProduct());

                  orderItem.setOrder(order);
                  order.getOrderItems().add(orderItem);


              }

              cart.getCartItems().clear();
              cart.setCartTotalItems(0);
              cart.setTotalAmount(0.0);


        Order save = orderRepository.save(order);

              return modelMapper.map(save, OrderDto.class);

    }

    @Override
    public OrderDto getOrderById(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order not found"));

       return modelMapper.map(order,OrderDto.class);
    }

    @Override
    public OrderDto updateOrderStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("order not found"));

        order.setOrderStatus(status);
        Order save = orderRepository.save(order);

        return modelMapper.map(save, OrderDto.class);

    }

    @Override
    public List<OrderDto> getOrderByUser(Long userId) {


        User user = userRepository.findById(Math.toIntExact(userId)).orElseThrow(() -> new ResourceNotFoundException("user not found"));

        List<Order> orders = orderRepository.findByUser(user);

        return orders.stream()
                .map(item->modelMapper.map(item, OrderDto.class))
                .collect(Collectors.toList());



    }
}
