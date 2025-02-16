package nvm.microservices.orderservice.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PlaceOrderDTO {

    private String orderNumber;

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;

}
