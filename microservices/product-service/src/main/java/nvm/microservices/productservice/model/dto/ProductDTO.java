package nvm.microservices.productservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String productId;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

}
