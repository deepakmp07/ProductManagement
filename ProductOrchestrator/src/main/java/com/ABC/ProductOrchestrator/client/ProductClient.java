package com.ABC.ProductOrchestrator.client;

import com.ABC.ProductOrchestrator.dto.InventoryRequest;
import com.ABC.ProductOrchestrator.dto.ProductRequest;
import com.ABC.ProductOrchestrator.dto.ProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductResponse createProduct(ProductRequest request) {

        return restTemplate.postForObject(
                "http://localhost:8081/products/createProduct",
                request,
                ProductResponse.class
        );
    }

}