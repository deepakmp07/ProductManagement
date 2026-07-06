package com.ABC.productservice.controller;

import com.ABC.productservice.entity.Category;
import com.ABC.productservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/addCategoryCode")
    public ResponseEntity<String> addCategoryCode(@RequestBody Category categoryCode){
        try {
            categoryService.addCategoryCode(categoryCode);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
