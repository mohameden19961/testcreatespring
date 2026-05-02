package com.example.monprojet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        return ResponseEntity.ok(new ApiResponse<>("Liste des utilisateurs récupérée", true, userService.getAllUsers()));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@jakarta.validation.Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(new ApiResponse<>("User créé avec succès", true, userService.register(userDTO)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(new ApiResponse<>("Login réussi", true, userService.login(loginDTO)));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<ApiResponse<UserDTO>> updateRole(@PathVariable Long id, @RequestBody java.util.Map<String, String> request) {
        String newRole = request.get("role");
        return ResponseEntity.ok(new ApiResponse<>("Rôle mis à jour avec succès", true, userService.updateRole(id, newRole)));
    }
}