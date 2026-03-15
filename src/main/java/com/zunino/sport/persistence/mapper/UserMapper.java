package com.zunino.sport.persistence.mapper;

import com.zunino.sport.persistence.dto.RegisterDto;
import com.zunino.sport.persistence.dto.UpdateUserDto;
import com.zunino.sport.persistence.dto.UserDto;
import com.zunino.sport.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDto toDto(UserEntity user);

    @Mapping(target = "idUser", ignore = true)
    UserEntity toEntity(RegisterDto registerDto);

    void updateUserFromDto(UpdateUserDto dto, @MappingTarget UserEntity user);

}
