package com.fsa.firststepapp.service.user_service;

import com.fsa.firststepapp.models.User;
import com.fsa.firststepapp.models.dto.UserDto;
import com.fsa.firststepapp.models.exception.models.EntityNotFoundException;
import com.fsa.firststepapp.models.mappers.UserMapper;
import com.fsa.firststepapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new EntityNotFoundException("Users Not Found!!");
        }

        return userMapper.convertModelListToDtoList(users);
    }

    @Override
    public Optional<User> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
}
