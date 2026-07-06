package com.ABC.productservice.client;

import com.ABC.productservice.dto.InventoryRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createInventory(InventoryRequest request) {
        restTemplate.postForObject(
                "http://localhost:8082/inventory/create",
                request,
                String.class
        );

    }
}
