package com.ABC.productservice.service;

import com.ABC.productservice.entity.Category;
import com.ABC.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategoryCode(Category category) {

        if(categoryRepository.existsById(category.getCategoryCode())){
            throw new RuntimeException("Category code already exists");
        }

        return categoryRepository.save(category);
    }
}
