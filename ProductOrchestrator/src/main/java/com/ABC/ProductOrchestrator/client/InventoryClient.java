package com.ABC.ProductOrchestrator.client;

import com.ABC.ProductOrchestrator.dto.InventoryRequest;
import com.ABC.ProductOrchestrator.dto.ProductRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String createInventory(InventoryRequest request) {

        return restTemplate.postForObject(
                "http://localhost:8082/inventory/createInventory",
                request,
                String.class
        );
    }

    public String reduceQuantity(InventoryRequest request) {

        String url = "http://localhost:8082/inventory/updatequantity/"+request.getProductCode();

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(request),
                String.class
        );

        return response.getBody();

    }
}