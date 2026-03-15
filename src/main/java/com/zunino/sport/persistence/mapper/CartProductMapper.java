package com.zunino.sport.persistence.mapper;

import com.zunino.sport.persistence.dto.CartResponseProductDTO;
import com.zunino.sport.persistence.entity.CartProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CartProductMapper {

    @Mapping(source = "product", target = "product")
    @Mapping(source = "quantity", target = "quantity")
    CartResponseProductDTO toDto(CartProductEntity cartProduct);
}
