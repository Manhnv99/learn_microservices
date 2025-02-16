package home.microservices.inventory_service.service;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InventoryService {

    ResponseEntity<?> isInStock(List<String> skuCode);

}
