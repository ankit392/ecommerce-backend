package com.mybasket.app.service.impl;

import com.mybasket.app.dto.CategoryDto;
import com.mybasket.app.entity.Category;
import com.mybasket.app.exception.ResourceNotFoundException;
import com.mybasket.app.repository.CategoryRepository;
import com.mybasket.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        //dto->entity

        Category categoryEntity = modelMapper.map(categoryDto, Category.class);

        Category savedCategoryEntity = categoryRepository.save(categoryEntity);


        return  modelMapper.map(savedCategoryEntity,CategoryDto.class);


    }

    @Override
    public List<CategoryDto> getAll() {

        List<Category> categories = categoryRepository.findAll();

        // convert categories to dto

        List<CategoryDto> listCategoryDto = categories.stream().map((category -> modelMapper.map(category, CategoryDto.class))).toList();
        return listCategoryDto;


    }

    @Override
    public CategoryDto getSingle(Long categoryId) {

        Category categoryEntity = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category not found with this id"+categoryId));

         return modelMapper.map(categoryEntity,CategoryDto.class);

    }

    @Override
    public void delete(Long categoryId) {

        Category catogoryItem = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("catogory item not found"));

        categoryRepository.delete(catogoryItem);


    }
}
