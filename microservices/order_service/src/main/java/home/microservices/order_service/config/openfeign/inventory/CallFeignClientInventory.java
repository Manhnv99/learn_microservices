package home.microservices.order_service.config.openfeign.inventory;

import home.microservices.order_service.model.dto.InventoryReceiveDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface CallFeignClientInventory {

    @GetMapping("/api/inventory/is-in-stock")
    List<InventoryReceiveDTO> checkIsInStock(@RequestParam(name = "skuCode") List<String> skuCode);

}
