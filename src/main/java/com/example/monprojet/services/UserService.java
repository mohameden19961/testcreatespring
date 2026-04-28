package com.example.monprojet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.monprojet.data.entities.User;
import com.example.monprojet.data.repositories.UserRepository;
import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.LoginDTO;
import com.example.monprojet.dto.UserDTO;
import com.example.monprojet.security.CustomUserDetailsService;
import com.example.monprojet.security.JwtService;

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
        user.setEmail(userDTO.getEmail()); 
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); 
        // Par défaut, tout nouvel utilisateur est un simple USER
        // Seul l'admin pourra changer cela plus tard
        user.setRole("USER");

        User saved = userRepository.save(user);

        UserDTO result = new UserDTO();
        result.setId(saved.getId());
        result.setUsername(saved.getUsername());
        result.setEmail(saved.getEmail()); 
        result.setRole(saved.getRole());
        

        return new ApiResponse<>("User créé avec succès", true, result);
    }

    public ApiResponse<UserDTO> updateRole(Long userId, String newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        
        user.setRole(newRole);
        User saved = userRepository.save(user);

        UserDTO result = new UserDTO();
        result.setId(saved.getId());
        result.setUsername(saved.getUsername());
        result.setEmail(saved.getEmail());
        result.setRole(saved.getRole());

        return new ApiResponse<>("Rôle mis à jour avec succès", true, result);
    }

    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userRepository.findAll().stream()
                .map(user -> {
                    UserDTO dto = new UserDTO();
                    dto.setId(user.getId());
                    dto.setUsername(user.getUsername());
                    dto.setEmail(user.getEmail());
                    dto.setRole(user.getRole());
                    return dto;
                })
                .toList();
        return new ApiResponse<>("Liste des utilisateurs récupérée", true, users);
    }


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    public ApiResponse<String> login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(),
                loginDTO.getPassword()
            )
        );
        
        var user = userDetailsService.loadUserByUsername(loginDTO.getEmail());
        String token = jwtService.generateToken(user);
        
        return new ApiResponse<>("Login réussi", true, token);
    }
}