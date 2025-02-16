package nvm.microservices.inventoryservice.model.mapper;

import nvm.microservices.inventoryservice.entity.Inventory;
import nvm.microservices.inventoryservice.model.dto.PlaceOrderDTO;
import nvm.microservices.inventoryservice.model.request.PlaceOrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Inventory toEntity(PlaceOrderRequest orderDTO);

    PlaceOrderDTO toDTO(Inventory inventory);

}
