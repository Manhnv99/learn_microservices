package nvm.microservices.productservice.controller;

import lombok.RequiredArgsConstructor;
import nvm.microservices.productservice.model.request.ProductRequest;
import nvm.microservices.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.addProduct(request));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequest request) {
        return ResponseEntity.ok(productService.updateProduct(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> detailProduct(@PathVariable String id) {
        return ResponseEntity.ok(productService.detailProduct(id));
    }

}
