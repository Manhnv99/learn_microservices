package nvm.microservices.inventoryservice.service.impl;

import lombok.RequiredArgsConstructor;
import nvm.microservices.inventoryservice.entity.Inventory;
import nvm.microservices.inventoryservice.exception.ErrorException;
import nvm.microservices.inventoryservice.model.dto.PlaceOrderDTO;
import nvm.microservices.inventoryservice.model.mapper.OrderMapper;
import nvm.microservices.inventoryservice.model.request.PlaceOrderRequest;
import nvm.microservices.inventoryservice.repository.InventoryRepository;
import nvm.microservices.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isinStock(String skuCode, Integer quantity) {
        return inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode, quantity);
    }

}
