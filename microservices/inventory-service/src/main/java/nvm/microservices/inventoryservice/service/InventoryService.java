package nvm.microservices.inventoryservice.service;


public interface InventoryService {

    boolean isinStock(String skuCode, Integer quantity);

}
