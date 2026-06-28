package com.mybasket.app.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.mybasket.app.entity.*;
import jakarta.persistence.*;
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
public class OrderDto {

    private Long id;


    private UserDto user;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AddressDto address;


    private OrderStatus orderStatus;

    //extra
//    private String billingName;
//    private String billingPhone;
//    private String billingNotes;


    private List<OrderItemDto> orderItems=new ArrayList<>();


}
