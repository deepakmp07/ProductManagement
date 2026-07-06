package com.ABC.productservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    private String productCode;

    @NotBlank(message = "Product name is required")
    @Column(unique = true)
    private String name;

    private List<String> tags;

    @NotNull(message = "Price is required")
    private Double price;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Schema(hidden = true)
    private Category category;
}
