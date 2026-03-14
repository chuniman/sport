package com.zunino.sport.persistence.mapper;

import com.zunino.sport.persistence.dto.RegisterDto;
import com.zunino.sport.persistence.dto.UserDto;
import com.zunino.sport.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity user);

    @Mapping(target = "idUser", ignore = true)
    UserEntity toEntity(RegisterDto registerDto);

}
