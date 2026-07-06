package com.ABC.productservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductOrcRequest {
    private String name;
    private String description;
    private Double price;
    private List<String> tags;
    private String categoryCode;
}
