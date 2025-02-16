package nvm.microservices.productservice.service;

import nvm.microservices.productservice.model.dto.ProductDTO;
import nvm.microservices.productservice.model.request.ProductRequest;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProducts();

    ProductDTO addProduct(ProductRequest request);

    ProductDTO updateProduct(ProductRequest request);

    ProductDTO detailProduct(String id);

}
