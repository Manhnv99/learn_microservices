package nvm.microservices.inventoryservice.controller;

import lombok.RequiredArgsConstructor;
import nvm.microservices.inventoryservice.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/is-in-stock")
    public ResponseEntity<?> isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return ResponseEntity.ok(inventoryService.isinStock(skuCode, quantity));
    }

}
