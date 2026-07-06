package com.ABC.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryRequest {
    private String productId;
    private Integer quantity;
}
