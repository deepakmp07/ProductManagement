package com.ABC.InventoryManagement;

import com.ABC.InventoryManagement.entities.Inventory;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void updateQuantity(String productCode, Inventory request) {
        Inventory inventory = inventoryRepository
                .findByProductCodeForUpdate(productCode)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (inventory.getQuantity() < request.getQuantity()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insufficient Quantity");
        }

        inventory.setQuantity(
                inventory.getQuantity() - request.getQuantity());

        inventoryRepository.save(inventory);
    }

    public String createInventory(Inventory inventory) {
        if(!inventoryRepository.findById(inventory.getProductCode()).isPresent()) {
           inventoryRepository.save(inventory);
           return "success";
        }
        throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED, "Product Already Exists");
    }
}
