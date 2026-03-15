package com.zunino.sport.persistence.mapper;

import com.zunino.sport.persistence.dto.ProductDTO;
import com.zunino.sport.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    ProductDTO toDto(ProductEntity productEntity);

    @Mapping(target = "idProduct", ignore = true)
    ProductEntity toEntity(ProductDTO productDTO);

    List<ProductDTO> toDtoList(List<ProductEntity> entities);
}
