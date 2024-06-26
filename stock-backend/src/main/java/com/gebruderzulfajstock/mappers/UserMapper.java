package com.gebruderzulfajstock.mappers;

import com.gebruderzulfajstock.dtos.UserDto;
import com.gebruderzulfajstock.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User entity);

    List<UserDto> toDto(List<User> entities);

    @Mapping(target = "id", ignore = true) // Ignore id mapping to prevent overwriting
    void updateEntityFromDto(UserDto dto, @MappingTarget User entity);
}
