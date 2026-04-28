package com.example.monprojet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.LoginDTO;
import com.example.monprojet.dto.UserDTO;
import com.example.monprojet.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@jakarta.validation.Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }
}