package com.zunino.sport.web.controllers;

import com.zunino.sport.persistence.dto.UpdateUserDto;
import com.zunino.sport.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserDto request) {
        userService.updateUser(userId, request);
        return ResponseEntity.ok(Map.of("message", "Usuario modificado exitosamente"));
    }




}
