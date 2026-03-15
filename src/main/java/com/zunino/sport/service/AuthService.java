package com.zunino.sport.service;

import com.zunino.sport.persistence.dto.LoginDto;
import com.zunino.sport.persistence.dto.RegisterDto;
import com.zunino.sport.persistence.entity.UserEntity;
import com.zunino.sport.persistence.exception.TooYoungException;
import com.zunino.sport.persistence.exception.UserNotValidException;
import com.zunino.sport.persistence.mapper.UserMapper;
import com.zunino.sport.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AuthService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private JwtService jwtService;

    public AuthService(UserRepository userRepository, UserMapper userMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    public boolean addUser(RegisterDto registerDto) {
        LocalDate today = LocalDate.now();

        int age = Period.between(registerDto.birthDate(), today).getYears();
        if (age < 18) {
            throw new TooYoungException("La Edad debe ser mayor a 18");
        }
        UserEntity userEntity = userMapper.toEntity(registerDto);
        userEntity.setPassword(BCrypt.hashpw(userEntity.getPassword(), BCrypt.gensalt()));

        userRepository.save(userEntity);
        return true;
    }

    public String login(LoginDto loginDto) {
        UserEntity userEntity = userRepository.findByEmail(loginDto.email()).
                orElseThrow(() -> new UserNotValidException("datos de login incorrectos"));

        if (!BCrypt.checkpw(loginDto.password(), userEntity.getPassword())) {
            throw new UserNotValidException("datos de login incorrectos");
        }
        return jwtService.generateToken(userEntity);
    }
}
