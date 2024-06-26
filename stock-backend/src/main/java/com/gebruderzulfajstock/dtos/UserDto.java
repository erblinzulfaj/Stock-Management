package com.gebruderzulfajstock.dtos;

import com.gebruderzulfajstock.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String password;
    private Role role;
}
