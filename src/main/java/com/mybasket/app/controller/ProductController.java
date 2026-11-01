package com.mybasket.app.controller;

import com.mybasket.app.entity.FileMetaData;
import com.mybasket.app.dto.ProductDto;
import com.mybasket.app.service.FileStorageService;
import com.mybasket.app.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


     private final ProductService productService;

    private final FileStorageService fileStorageService;


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
     @PreAuthorize("hasRole('ADMIN')")
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



    // Controller method to upload Image

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/image")
    public ResponseEntity<FileMetaData> uploadProductImage(

            @RequestParam("productImage")MultipartFile  file

            ) throws IOException {



//        System.out.println(file.getOriginalFilename());
        FileMetaData fileMetaData = fileStorageService.uploadFile(file);



        return ResponseEntity.ok(fileMetaData);
    }



}
