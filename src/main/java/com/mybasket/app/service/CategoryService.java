package com.mybasket.app.service;

import com.mybasket.app.dto.CategoryDto;

import java.util.List;

public interface CategoryService {


    CategoryDto create(CategoryDto categoryDto);


     List<CategoryDto> getAll();



    CategoryDto getSingle(Long categoryId);

    void delete(Long categoryId);
}
