package home.microservices.order_service.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderLineItemsDTO {

    private Long id;

    private String skuCode;

    private BigDecimal price;

    private Integer quantity;

}
