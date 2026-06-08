package com.mybasket.app.service;

import com.mybasket.app.dto.ProductDto;
import com.mybasket.app.entity.Product;

import java.util.List;

public interface ProductService {


    List<ProductDto> getAll();

    ProductDto get(long productId);

    ProductDto createProduct(ProductDto product);
    ProductDto updateProduct(long productId, ProductDto product);

    void delete(long productId);

}
