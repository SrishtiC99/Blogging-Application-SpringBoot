package com.srishti.blogapis.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srishti.blogapis.exceptions.ResourceNotFoundException;
import com.srishti.blogapis.models.Category;
import com.srishti.blogapis.payloads.CategoryDto;
import com.srishti.blogapis.repositories.CategoryRepo;
import com.srishti.blogapis.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.dtoToEntity(categoryDto);
        Category savedCategory = this.categoryRepo.save(category);

        return this.entityToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.entityToDto(updatedCategory);
        
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        
        return this.entityToDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(user -> this.entityToDto(user))
                .collect(Collectors.toList());

        return categoryDtos;

    }

    private Category dtoToEntity(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    private CategoryDto entityToDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }
    
}
