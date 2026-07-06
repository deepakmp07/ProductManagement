package com.ABC.ProductOrchestrator;

import com.ABC.ProductOrchestrator.client.InventoryClient;
import com.ABC.ProductOrchestrator.client.ProductClient;
import com.ABC.ProductOrchestrator.dto.CreateProductRequest;
import com.ABC.ProductOrchestrator.dto.InventoryRequest;
import com.ABC.ProductOrchestrator.dto.ProductRequest;
import com.ABC.ProductOrchestrator.dto.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductOrchestratorService {

    private final ProductClient productClient;
    private final InventoryClient inventoryClient;

    public ProductOrchestratorService(ProductClient productClient, InventoryClient inventoryClient) {
        this.productClient = productClient;
        this.inventoryClient = inventoryClient;
    }

    public void createProduct(CreateProductRequest request) {
        ProductRequest productRequest = new ProductRequest();

        productRequest.setName(request.getName());
        productRequest.setDescription(request.getDescription());
        productRequest.setPrice(request.getPrice());
        productRequest.setTags(request.getTags());
        productRequest.setCategoryCode(request.getCategoryCode());

        ProductResponse response = productClient.createProduct(productRequest);
        InventoryRequest inventoryrequest = new  InventoryRequest();
        inventoryrequest.setProductCode(response.getProductId());
        inventoryrequest.setQuantity(request.getQuantity());

        String result = inventoryClient.createInventory(inventoryrequest);
        if(!result.equals("success")){
            throw new RuntimeException(result);
        }
    }

    public void reduceQuantity(CreateProductRequest request) {
        InventoryRequest inventoryRequest = new InventoryRequest();
        inventoryRequest.setProductCode(request.getProductCode());
        inventoryRequest.setQuantity(request.getQuantity());

        String result = inventoryClient.reduceQuantity(inventoryRequest);
        if(!result.equals("success")){
            throw new RuntimeException(result);
        }
    }
}
