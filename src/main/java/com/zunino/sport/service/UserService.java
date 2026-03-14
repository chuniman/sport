package com.zunino.sport.service;

import com.zunino.sport.persistence.dto.UserDto;
import com.zunino.sport.persistence.entity.UserEntity;
import com.zunino.sport.persistence.exception.UserNotFoundException;
import com.zunino.sport.persistence.mapper.UserMapper;
import com.zunino.sport.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDto getUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        return userMapper.toDto(userEntity.get());
     }



}
