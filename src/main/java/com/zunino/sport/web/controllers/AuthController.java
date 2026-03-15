package com.zunino.sport.web.controllers;

import com.zunino.sport.persistence.dto.LoginDto;
import com.zunino.sport.persistence.dto.RegisterDto;
import com.zunino.sport.persistence.exception.TooYoungException;
import com.zunino.sport.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto user){
        authService.addUser(user);
        return ResponseEntity.ok(Map.of("message", "Usuario ingresado exitosamente"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
