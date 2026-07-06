package com.ABC.ProductOrchestrator.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class CreateProductRequest {

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Schema(hidden = true)
    private String productCode;

    private String name;
    private String description;
    private Double price;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private List<String> tags;
    private String categoryCode;
    private Integer quantity;

    public CreateProductRequest(String name, String description, Double price,
                                String categoryCode, Integer quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryCode = categoryCode;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer initialQuantity) {
        this.quantity = initialQuantity;
    }
}