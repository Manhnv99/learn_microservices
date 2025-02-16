package nvm.microservices.orderservice.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlaceOrderRequest {

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;

}
