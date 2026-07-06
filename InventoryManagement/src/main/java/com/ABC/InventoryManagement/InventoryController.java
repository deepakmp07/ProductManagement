package com.ABC.InventoryManagement;

import com.ABC.InventoryManagement.entities.Inventory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/createInventory")
    public String createInventory(@RequestBody Inventory inventory) {
        try{
            return inventoryService.createInventory(inventory);
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/updatequantity/{productCode}")
    public String updateInventory(@RequestBody Inventory inventory, @PathVariable String productCode) {
        try
        {
            inventoryService.updateQuantity(productCode, inventory);
            return "success";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }
}
