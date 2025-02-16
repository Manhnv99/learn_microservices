package home.microservices.inventory_service.service.impl;

import home.microservices.inventory_service.entity.Inventory;
import home.microservices.inventory_service.model.response.IsInStockResponse;
import home.microservices.inventory_service.repository.InventoryRepository;
import home.microservices.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public ResponseEntity<?> isInStock(List<String> skuCodes) {
        List<IsInStockResponse> isInStockResponses = new ArrayList<>();
        for (String skuCode : skuCodes) {
            for (Inventory inventory : inventoryRepository.findInventoriesBySkuCode(skuCode)) {
                isInStockResponses.add(new IsInStockResponse(skuCode, inventory.getQuantity() > 0));
            }
        }

        return ResponseEntity.ok(isInStockResponses);
    }

}
