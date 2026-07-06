package com.ABC.productservice.repository;

import com.ABC.productservice.entity.Category;
import com.ABC.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByCategoryCode(String CategoryCode);
}
