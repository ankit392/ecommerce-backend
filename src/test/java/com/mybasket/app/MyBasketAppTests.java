package com.mybasket.app;

import com.mybasket.app.service.impl.CalculatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class MyBasketAppTests {


   // @Autowired
    private CalculatorService calculatorService;


    @BeforeEach
    public void init(){

        calculatorService=new CalculatorService();

    }


    @Test
    public void testAddService(){


        System.out.println("Test started");
      int ares=  calculatorService.add(3,7);

      int expResult=11;


        Assertions.assertEquals(ares,expResult);

    }



}
