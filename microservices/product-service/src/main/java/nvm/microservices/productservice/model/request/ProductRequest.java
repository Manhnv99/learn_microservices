package nvm.microservices.productservice.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

}
