package com.mybasket.app.entity;


import jakarta.persistence.*;
import org.aspectj.weaver.ast.Or;

@Entity
@Table(name = "my-basket-payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name ="order_id" )
    private Order order;

    @Enumerated(EnumType.STRING)
    private  PaymentStatus paymentStatus=PaymentStatus.PENDING;

    @Enumerated
    private PaymentMethod paymentMethod=PaymentMethod.COD;



    @OneToOne(mappedBy = "payment")
    private  PaymentMethodInfo paymentMethodInfo;
}
