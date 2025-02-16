package nvm.microservices.orderservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "inventory", url = "http://localhost:8082/api/v1/inventory")
public interface InventoryClient {

    @GetMapping("/is-in-stock")
    boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity);

}
