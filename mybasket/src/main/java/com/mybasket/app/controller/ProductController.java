package com.mybasket.app.controller;

import com.mybasket.app.dto.ProductDto;
import com.mybasket.app.entity.Product;
import com.mybasket.app.repository.ProductRepository;
import com.mybasket.app.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


     private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //resource:product





    @GetMapping
    public Page<ProductDto> getProducts(
         @RequestParam(value = "page",defaultValue = "0")   int page,
        @RequestParam(value = "size",defaultValue = "10")    int size,
                @RequestParam(value = "sortBy",defaultValue = "productId") String sortBy

    ){

        Page<ProductDto> all = productService.getAll(page, size,sortBy);

        return all;
    }

     @GetMapping("/{productId}")
    // @ResponseBody
     public ProductDto getSingleProduct(@PathVariable("productId") Long id ){

         return productService.get(id);


     }

     //create product
     //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productdto){

        ProductDto product1 = productService.createProduct(productdto);


        return new ResponseEntity<>(product1, HttpStatus.CREATED);


    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") Long id,@RequestBody ProductDto productto){

        ProductDto productDto = productService.updateProduct(id, productto);

        return  new ResponseEntity<>(productDto,HttpStatus.OK);


    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long productId ){

        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //getproductBycategory

    @GetMapping("/category/{categoryid}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable Long categoryid){

        List<ProductDto> productDtos = productService.getproductBycategory(categoryid);

      return new ResponseEntity<>(productDtos,HttpStatus.OK);

    }



}
