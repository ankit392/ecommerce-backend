package com.mybasket.app.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "my-basket-payment-method-info")
public class PaymentMethodInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Payment payment;

    @ManyToOne
    private  User user;

   @Embedded
    private  UPI upi;

   // private  Card upi;

  //  private  NetBanking upi;

    @Embedded
    private  COD cod;

}
