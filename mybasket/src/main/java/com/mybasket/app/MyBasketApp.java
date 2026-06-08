package com.mybasket.app;

import com.mybasket.app.entity.Product;
import com.mybasket.app.entity.User;
import com.mybasket.app.repository.ProductRepository;
import com.mybasket.app.repository.UserRepository;
import com.mybasket.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class MyBasketApp implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(MyBasketApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // System.out.println("app started");
//         User user = new User();
//
//         user.setEmail("dk@gmail.com");
//         user.setName("dheeraj");
//
//         //userService.saveuser(user);
//         userRepository.save(user);
        userRepository.findAll().forEach(item -> System.out.println(item));

//        Product product = new Product();
//
//        product.setTitle("iphone 17");
//        product.setDescription("new phone");
//        product.setPrice(677.99);
//        product.setOutOfstock(false);
//        productRepository.save(product);

//        productRepository.findByTitle("iphone 17").forEach(p -> System.out.println(p.getDescription() + "" + p.getTitle()));
//
//        productRepository.findByProductIdAndTitle(4, "iphone 17")
//                .ifPresentOrElse(
//                        p -> System.out.println(p.getDescription() + " " + p.getTitle()),
//                        () -> System.out.println("not found")
//                );

//        User user = userRepository.findById(1).get();
//        userRepository.delete(user);

    }
}