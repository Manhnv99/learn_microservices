package home.microservices.order_service.service;

import home.microservices.order_service.model.request.OrderRequest;
import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<?> placeOrder(OrderRequest request);

    ResponseEntity<?> getInstance(String service_name);

}
