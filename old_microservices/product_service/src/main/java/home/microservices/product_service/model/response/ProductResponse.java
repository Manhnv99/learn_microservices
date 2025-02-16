package home.microservices.product_service.model.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

}
