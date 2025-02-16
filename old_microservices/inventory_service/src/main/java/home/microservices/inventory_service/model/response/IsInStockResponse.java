package home.microservices.inventory_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class IsInStockResponse {

    private String skuCode;

    private boolean isInStock;

}
