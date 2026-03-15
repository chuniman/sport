package com.zunino.sport.service;

import com.zunino.sport.persistence.dto.UpdateUserDto;
import com.zunino.sport.persistence.dto.UserDto;
import com.zunino.sport.persistence.entity.UserEntity;
import com.zunino.sport.persistence.exception.UserNotFoundException;
import com.zunino.sport.persistence.mapper.UserMapper;
import com.zunino.sport.persistence.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
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

     public boolean updateUser(Long userId, UpdateUserDto userDto) {
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        if(userDto.password() != null && !userDto.password().isEmpty()) {
            userEntity.setPassword(BCrypt.hashpw(userDto.password(), BCrypt.gensalt()));
        }
        userMapper.updateUserFromDto(userDto, userEntity);
        userRepository.save(userEntity);
        return true;
     }



}
