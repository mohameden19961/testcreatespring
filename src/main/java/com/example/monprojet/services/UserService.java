package com.example.monprojet.services;

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

        // Entity → DTO
        UserDTO result = new UserDTO();
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
        result.setUsername(saved.getUsername());
        result.setEmail(saved.getEmail());
        result.setRole(saved.getRole());

        return new ApiResponse<>("Rôle mis à jour avec succès", true, result);
    }


    @Autowired
    private AuthenticationManager authenticationManager;

    public ApiResponse<String> login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(),
                loginDTO.getPassword()
            )
        );
        return new ApiResponse<>("Login réussi", true, loginDTO.getUsername());
    }
}