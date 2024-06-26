package com.gebruderzulfajstock.services.userServices;

import com.gebruderzulfajstock.dtos.UserDto;

import java.util.List;

public interface UserService {

    void updateUser(Long id, UserDto user);

    void deleteUser(Long id);

    UserDto getUserById(Long id);
    List<UserDto> findAll();

}
