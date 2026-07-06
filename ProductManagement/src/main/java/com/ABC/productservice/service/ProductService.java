package com.ABC.productservice.service;

import com.ABC.productservice.dto.InventoryRequest;
import com.ABC.productservice.dto.ProductOrcRequest;
import com.ABC.productservice.dto.ProductOrcResponse;
import com.ABC.productservice.entity.Category;
import com.ABC.productservice.entity.Product;
import com.ABC.productservice.repository.CategoryRepository;
import com.ABC.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public String generateCode(Category category){
        long count = productRepository.count() + 1;
        return category.getCategoryCode() + (1000 + count);
    }

    public ProductOrcResponse createProduct(ProductOrcRequest serializeProduct) {

        Product product = new Product();
        product.setName(serializeProduct.getName());
        product.setDescription(serializeProduct.getDescription());
        product.setPrice(serializeProduct.getPrice());
        product.setTags(serializeProduct.getTags());

        Category deserilaizeCategory = new Category();
        deserilaizeCategory.setCategoryCode(serializeProduct.getCategoryCode());
        product.setCategory(deserilaizeCategory);

        // Business Validation
        if (product.getPrice() <= 0) {
            throw new RuntimeException("Price should be greater than 0");
        }

        String categoryCode = product.getCategory().getCategoryCode();

        Category category = categoryRepository.findByCategoryCode(categoryCode).orElseThrow(() -> new RuntimeException("Category Code not found"));

        product.setProductCode(generateCode(category));

        // Associate the product with the existing/new category
        product.setCategory(category);

        productRepository.save(product);
        ProductOrcResponse inventoryRequest = new ProductOrcResponse();
        inventoryRequest.setProductId(product.getProductCode());
        return inventoryRequest;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product updateProduct(String productCode, Product updatedProduct) {
        // Check whether the product exists
        Product existingProduct = productRepository.findById(productCode)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update the fields
        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }

        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }

        if (updatedProduct.getPrice() != null) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }

        return productRepository.save(existingProduct);

    }
}