package home.microservices.order_service.model.request;

import home.microservices.order_service.model.dto.OrderLineItemsDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class OrderRequest {

    private List<OrderLineItemsDTO> orderLineItems;

}
