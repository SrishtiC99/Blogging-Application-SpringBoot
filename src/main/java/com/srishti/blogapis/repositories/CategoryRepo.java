package com.srishti.blogapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srishti.blogapis.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    
}
