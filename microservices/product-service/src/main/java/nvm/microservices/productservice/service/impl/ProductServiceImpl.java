package nvm.microservices.productservice.service.impl;

import lombok.RequiredArgsConstructor;
import nvm.microservices.productservice.entity.Product;
import nvm.microservices.productservice.exception.ErrorException;
import nvm.microservices.productservice.model.dto.ProductDTO;
import nvm.microservices.productservice.model.mapper.ProductMapper;
import nvm.microservices.productservice.model.request.ProductRequest;
import nvm.microservices.productservice.repository.ProductRepository;
import nvm.microservices.productservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::toDTO)
                .toList();
    }

    @Override
    public ProductDTO addProduct(ProductRequest request) {
        List<Map<String, String>> errors = new ArrayList<>();
        validateRequest(request, errors);

        Product product = productMapper.toEntity(request);
        Product productSaved = productRepository.save(product);

        return productMapper.toDTO(productSaved);
    }

    @Override
    public ProductDTO updateProduct(ProductRequest request) {
        List<Map<String, String>> errors = new ArrayList<>();
        validateRequest(request, errors);

        Optional<Product> productOptional = productRepository.findById(request.getId());
        if (productOptional.isEmpty()) {
            errors.add(Map.of("product", "Product not found"));
            throw new ErrorException(HttpStatus.NOT_FOUND, errors);
        }

        Product productCopy = productMapper.toEntity(request);
        productCopy.setId(productOptional.get().getId());
        Product productUpdated = productRepository.save(productCopy);

        return productMapper.toDTO(productUpdated);
    }

    @Override
    public ProductDTO detailProduct(String id) {
        List<Map<String, String>> errors = new ArrayList<>();

        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            errors.add(Map.of("product", "Product not found"));
            throw new ErrorException(HttpStatus.NOT_FOUND, errors);
        }

        return productMapper.toDTO(productOptional.get());
    }

    private void validateRequest(ProductRequest request, List<Map<String, String>> errors) {
        if (request.getName() == null || request.getName().isEmpty()) {
            errors.add(Map.of("name", "Name is required"));
        }
        if (request.getDescription() == null || request.getDescription().isEmpty()) {
            errors.add(Map.of("description", "Name is required"));
        }
        if (request.getPrice() == null) {
            errors.add(Map.of("price", "Price is required"));
        }

        if (!errors.isEmpty()) {
            throw new ErrorException(HttpStatus.INTERNAL_SERVER_ERROR, errors);
        }
    }

}
