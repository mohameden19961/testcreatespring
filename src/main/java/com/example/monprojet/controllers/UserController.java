package com.example.monprojet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDTO>>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@jakarta.validation.Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.register(userDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }

    @PostMapping("/{id}/role")
    public ResponseEntity<ApiResponse<UserDTO>> updateRole(@PathVariable Long id, @RequestBody String newRole) {
        return ResponseEntity.ok(userService.updateRole(id, newRole));
    }
}