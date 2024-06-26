package com.gebruderzulfajstock.mappers;

public interface Cover<UserEntity, UserDto, UserCardDto> {
    UserEntity toEntity(UserDto item);
    UserDto toDto(UserEntity item);
    UserCardDto toCardDto(UserEntity item);
}
