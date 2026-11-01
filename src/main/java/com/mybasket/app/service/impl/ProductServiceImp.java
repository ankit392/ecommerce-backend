package com.mybasket.app.service.impl;

import com.mybasket.app.dto.ProductDto;
import com.mybasket.app.entity.Category;
import com.mybasket.app.entity.Product;
import com.mybasket.app.exception.ResourceNotFoundException;
import com.mybasket.app.repository.CategoryRepository;
import com.mybasket.app.repository.ProductRepository;
import com.mybasket.app.service.ProductService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;


    @Override
    public Page<ProductDto> getAll(int page,int size,String sortBy) {


        //sort
        Sort sort=Sort.by(sortBy).ascending();

        //pagination parent hold child ref & overloaded method, of static method
       Pageable pageable = PageRequest.of(page,size,sort);



        Page<Product> pageProducts = productRepository.findAll(pageable);
       // return pageProducts;


//        List<Product> all = productRepository.findAll();
         return pageProducts
           .map((p)->modelMapper.map(p,ProductDto.class));
    }

    @Override
    public ProductDto get(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        return modelMapper.map(product, ProductDto.class);


    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product productEntity = modelMapper.map(productDto, Product.class);

        Long categoryId = productEntity.getCategoryId();

        if (categoryId != null && categoryId > 0) {

            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            productEntity.getCategories().add(category);
            System.out.println(category.getProducts().size());
        }

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

    @Override
    public List<ProductDto> getproductBycategory(Long categoryId) {

        Category cayegoryFound = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("cayegory not found"));

        Set<Product> products = cayegoryFound.getProducts();

        return products.stream()
                .map(item->modelMapper.map(item,ProductDto.class)).toList();

    }
}
