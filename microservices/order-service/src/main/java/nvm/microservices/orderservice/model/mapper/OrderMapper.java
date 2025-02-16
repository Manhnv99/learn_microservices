package nvm.microservices.orderservice.model.mapper;

import nvm.microservices.orderservice.entity.Order;
import nvm.microservices.orderservice.model.dto.PlaceOrderDTO;
import nvm.microservices.orderservice.model.request.PlaceOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order toEntity(PlaceOrderRequest orderDTO);

    PlaceOrderDTO toDTO(Order order);

}
