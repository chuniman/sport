package com.zunino.sport.persistence.mapper;

import com.zunino.sport.persistence.dto.OrderDTO;
import com.zunino.sport.persistence.dto.OrderItemDTO;
import com.zunino.sport.persistence.entity.OrderEntity;
import com.zunino.sport.persistence.entity.OrderItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface OrderMapper {
    @Mapping(source = "idOrder", target = "idOrder")
    OrderDTO toDto(OrderEntity order);

    List<OrderDTO> toDtoList(List<OrderEntity> orders);

    @Mapping(source = "product", target = "product")
    OrderItemDTO toItemDto(OrderItemEntity item);

    List<OrderItemDTO> toItemDtoList(List<OrderItemEntity> items);

}
