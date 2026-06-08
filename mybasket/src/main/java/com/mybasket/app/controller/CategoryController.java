package com.mybasket.app.controller;



import com.mybasket.app.dto.CategoryDto;
import com.mybasket.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    //create

    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){

        CategoryDto categoryDto1 = categoryService.create(categoryDto);

        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);

    }

    //update




    //get all
    @GetMapping
    public  ResponseEntity<List<CategoryDto>> getAll(){

        List<CategoryDto> all = categoryService.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);

    }

    //get single


    @GetMapping("/{categoryId}")
    public  ResponseEntity<CategoryDto> get(@PathVariable Long categoryId){

        CategoryDto single = categoryService.getSingle(categoryId);

        return new ResponseEntity<>(single,HttpStatus.OK);


    }
    // delete

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){

           categoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
