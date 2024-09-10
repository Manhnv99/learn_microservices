package home.microservices.product_service.service.impl;

import home.microservices.product_service.enitty.Product;
import home.microservices.product_service.model.request.CreateProductRequest;
import home.microservices.product_service.model.response.ProductResponse;
import home.microservices.product_service.repository.ProductRepository;
import home.microservices.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public String createProduct(CreateProductRequest request) {
        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        productRepository.save(product);

        log.info("Product created");

        return "Create Successfully!";
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponse> productResponses = new ArrayList<>();
        for (Product product : products) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setDescription(product.getDescription());
            productResponse.setPrice(product.getPrice());
            productResponses.add(productResponse);
        }

        return productResponses;
    }

}
