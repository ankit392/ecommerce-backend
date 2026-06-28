package com.mybasket.app.service;

import com.mybasket.app.dto.ProductDto;
import com.mybasket.app.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {


    Page<ProductDto> getAll(int page, int size,String sortBy);

    ProductDto get(long productId);

    ProductDto createProduct(ProductDto product);
    ProductDto updateProduct(long productId, ProductDto product);

    void delete(long productId);

    List<ProductDto> getproductBycategory(Long categoryId);

}
