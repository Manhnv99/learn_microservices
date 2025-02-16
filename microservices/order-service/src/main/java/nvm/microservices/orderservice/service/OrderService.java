package nvm.microservices.orderservice.service;

import nvm.microservices.orderservice.model.dto.PlaceOrderDTO;
import nvm.microservices.orderservice.model.request.PlaceOrderRequest;

public interface OrderService {

    PlaceOrderDTO placeOrder(PlaceOrderRequest request);

}
