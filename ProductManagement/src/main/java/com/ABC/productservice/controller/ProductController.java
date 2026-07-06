package com.ABC.productservice.controller;

import com.ABC.productservice.dto.InventoryRequest;
import com.ABC.productservice.dto.ProductOrcRequest;
import com.ABC.productservice.dto.ProductOrcResponse;
import com.ABC.productservice.entity.Product;
import com.ABC.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/createProduct")
    public ProductOrcResponse createProduct(@Valid @RequestBody ProductOrcRequest product) {
        try {
            return productService.createProduct(product);
        }
        catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        productService.getAllProducts();
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PutMapping("/{productCode}")
    public ResponseEntity<String> updateProduct(@PathVariable String productCode, @RequestBody Product product) {
        try {
            productService.updateProduct(productCode, product);
            return ResponseEntity.ok().body("Product got Updated");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}