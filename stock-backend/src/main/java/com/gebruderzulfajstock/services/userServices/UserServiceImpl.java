package com.gebruderzulfajstock.services.userServices;

import com.gebruderzulfajstock.dtos.UserDto;
import com.gebruderzulfajstock.mappers.UserMapper;
import com.gebruderzulfajstock.models.User;
import com.gebruderzulfajstock.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void updateUser(Long id, UserDto user) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id not found " + id));

        entity.setUserName(user.getUserName());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());

        repository.save(entity);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        User entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id not found " + id));
        return mapper.toDto(entity);
    }

    @Override
    public List<UserDto> findAll() {
        return mapper.toDto(repository.findAll());
    }
}
