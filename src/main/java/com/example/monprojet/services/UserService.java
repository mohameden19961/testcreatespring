package com.example.monprojet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.monprojet.data.entities.User;
import com.example.monprojet.data.repositories.UserRepository;
import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.UserDTO;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApiResponse<UserDTO> register(UserDTO userDTO) {
        // DTO → Entity
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // ← تشفير
        user.setRole(userDTO.getRole());

        User saved = userRepository.save(user);

        // Entity → DTO
        UserDTO result = new UserDTO();
        result.setUsername(saved.getUsername());
        result.setRole(saved.getRole());
        

        return new ApiResponse<>("User créé avec succès", true, result);
    }
}