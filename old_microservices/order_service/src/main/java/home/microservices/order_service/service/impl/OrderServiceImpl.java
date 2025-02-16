package home.microservices.order_service.service.impl;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import home.microservices.order_service.config.openfeign.inventory.CallFeignClientInventory;
import home.microservices.order_service.entity.Order;
import home.microservices.order_service.entity.OrderLineItems;
import home.microservices.order_service.model.dto.InventoryReceiveDTO;
import home.microservices.order_service.model.dto.OrderLineItemsDTO;
import home.microservices.order_service.model.request.OrderRequest;
import home.microservices.order_service.repository.OrderRepository;
import home.microservices.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClient;

    private final EurekaClient eurekaClient;

    private final CallFeignClientInventory callFeignClientInventory;

    @Value("${domain.inventory_service}")
    private String domainInventory;

    @Override
    public ResponseEntity<?> placeOrder(OrderRequest request) {
        List<OrderLineItems> lineItems = request.getOrderLineItems()
                .stream()
                .map(this::mapToDTO)
                .toList();

        List<String> skuCodes = lineItems.stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //check the product is existing from inventory service
//        InventoryReceiveDTO[] inventoryReceiveDTOS = webClient.build().get()
//                .uri(domainInventory + "/is-in-stock", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
//                .retrieve() // specific this api have a return result
//                .bodyToMono(InventoryReceiveDTO[].class) // type of return from api
//                .block(); // sync request

        List<InventoryReceiveDTO> inventoryReceiveDTOS = callFeignClientInventory.checkIsInStock(skuCodes);

//        if (inventoryReceiveDTOS == null || inventoryReceiveDTOS.length == 0) {
//            return ResponseEntity.badRequest().body("Không có skuCode nào tồn tại!");
//        }
        if (inventoryReceiveDTOS.isEmpty()) {
            return ResponseEntity.badRequest().body("Không có skuCode nào tồn tại!");
        }

        boolean result = inventoryReceiveDTOS.stream().allMatch(InventoryReceiveDTO::isInStock);
//        boolean result = Arrays.stream(inventoryReceiveDTOS).allMatch(InventoryReceiveDTO::isInStock);

        if (result) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setOrderLineItems(lineItems);
            orderRepository.save(order);

            return ResponseEntity.ok().body("Place Order Success!");
        }

        return ResponseEntity.badRequest().body("Failed to place order");
    }

    @Override
    public ResponseEntity<?> getInstance(String service_name) {
        List<InstanceInfo> service = eurekaClient
                .getApplication(service_name)
                .getInstances();

        return ResponseEntity.ok().body(service);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItem) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setQuantity(orderLineItem.getQuantity());
        orderLineItems.setPrice(orderLineItem.getPrice());
        orderLineItems.setSkuCode(orderLineItem.getSkuCode());
        return orderLineItems;
    }

}
