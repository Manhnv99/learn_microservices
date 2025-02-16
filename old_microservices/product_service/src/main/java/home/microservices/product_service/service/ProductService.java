package home.microservices.product_service.service;

import home.microservices.product_service.model.request.CreateProductRequest;
import home.microservices.product_service.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    String createProduct(CreateProductRequest request);

    List<ProductResponse> getAllProducts();

}
