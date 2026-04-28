package com.example.monprojet.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 4)
    private String password;

    private String role;
}