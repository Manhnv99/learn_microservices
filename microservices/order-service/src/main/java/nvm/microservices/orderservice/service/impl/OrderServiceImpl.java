package nvm.microservices.orderservice.service.impl;

import lombok.RequiredArgsConstructor;
import nvm.microservices.orderservice.entity.Order;
import nvm.microservices.orderservice.exception.ErrorException;
import nvm.microservices.orderservice.model.dto.PlaceOrderDTO;
import nvm.microservices.orderservice.model.mapper.OrderMapper;
import nvm.microservices.orderservice.model.request.PlaceOrderRequest;
import nvm.microservices.orderservice.openfeign.InventoryClient;
import nvm.microservices.orderservice.repository.OrderRepository;
import nvm.microservices.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final InventoryClient inventoryClient;

    @Override
    public PlaceOrderDTO placeOrder(PlaceOrderRequest request) {
        validatePlaceOrderRequest(request);
        Order order = orderMapper.toEntity(request);
        Order orderSaved = orderRepository.save(order);
        return orderMapper.toDTO(orderSaved);
    }

    private void validatePlaceOrderRequest(PlaceOrderRequest request) {
        List<Map<String, String>> errors = new ArrayList<>();

        if (request.getSkuCode().trim().isEmpty()) {
            errors.add(Map.of("skuCode", "SkuCode is required!"));
        }

        if (request.getPrice() == null) {
            errors.add(Map.of("price", "Price is required!"));
        }

        if (request.getQuantity() == null) {
            errors.add(Map.of("quantity", "Quantity is required!"));
        }

        boolean isInStock = inventoryClient.isInStock(request.getSkuCode(), request.getQuantity());
        if (!isInStock) {
            errors.add(Map.of("message", "Not in stock!"));
        }

        if (!errors.isEmpty()) {
            throw ErrorException.builder()
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .errors(errors)
                    .build();
        }
    }

}
