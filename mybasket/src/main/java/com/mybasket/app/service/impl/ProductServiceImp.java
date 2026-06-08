package com.mybasket.app.service.impl;

import com.mybasket.app.dto.ProductDto;
import com.mybasket.app.entity.Product;
import com.mybasket.app.repository.ProductRepository;
import com.mybasket.app.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<ProductDto> getAll() {


        List<Product> all = productRepository.findAll();
        return all.stream()
                .map((p)->modelMapper.map(p,ProductDto.class)).toList();
    }

    @Override
    public ProductDto get(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        return modelMapper.map(product, ProductDto.class);


    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product productEntity = modelMapper.map(productDto, Product.class);


        Product saveproductEntity = productRepository.save(productEntity);

        return modelMapper.map(saveproductEntity,ProductDto.class);



    }

    @Override
    public ProductDto updateProduct(long productId, ProductDto product) {
        Product oldProduct = productRepository.findById(productId).
                orElseThrow(() -> new RuntimeException("PRODUCT NOT FOUND"));
        oldProduct.setTitle(product.getTitle());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setOutOfstock(product.isOutOfstock());
        oldProduct.setLive(product.isLive());
        oldProduct.setImageUrl(product.getImageUrl());
        oldProduct.setDescription(product.getShort_description());



        Product save = productRepository.save(oldProduct);
        return  modelMapper.map(save,ProductDto.class);

    }

    @Override
    public void delete(long productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("product not found"));

        productRepository.delete(product);


    }
}
