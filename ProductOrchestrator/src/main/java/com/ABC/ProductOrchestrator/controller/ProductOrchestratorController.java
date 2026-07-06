package com.ABC.ProductOrchestrator.controller;

import com.ABC.ProductOrchestrator.ProductOrchestratorService;
import com.ABC.ProductOrchestrator.dto.CreateProductRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orchestrator")

public class ProductOrchestratorController {

    ProductOrchestratorService productOrchestratorService;

    public ProductOrchestratorController(ProductOrchestratorService productOrchestratorService) {
        this.productOrchestratorService = productOrchestratorService;
    }

    @PostMapping("/createProduct")
    public ResponseEntity<String> createProduct(
            @RequestBody CreateProductRequest request) {
        try {
            productOrchestratorService.createProduct(request);

            return ResponseEntity.ok("Success");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reduceProductuantity")
    public ResponseEntity<String> reduceQuantity(
            @RequestBody CreateProductRequest request) {
        try {
            productOrchestratorService.reduceQuantity(request);

            return ResponseEntity.ok("Success");
        }
        catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
