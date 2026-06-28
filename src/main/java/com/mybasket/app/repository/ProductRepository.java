package com.mybasket.app.repository;

import com.mybasket.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitle(String title);

    Optional<Product> findByProductIdAndTitle(long productid,String title);

    // jpql
    @Query("select p from Product p where p.productId=?1")
    Product getProductByTitle(String title);



}
