package nvm.microservices.productservice.model.mapper;

import nvm.microservices.productservice.entity.Product;
import nvm.microservices.productservice.model.dto.ProductDTO;
import nvm.microservices.productservice.model.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {

    Product toEntity(ProductRequest request);

    @Mapping(source = "id", target = "productId")
    @Mapping(source = "name", target = "productName")
    @Mapping(source = "description", target = "productDescription")
    @Mapping(source = "price", target = "productPrice")
    ProductDTO toDTO(Product product);

}
