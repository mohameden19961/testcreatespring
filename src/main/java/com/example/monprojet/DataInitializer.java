package com.example.monprojet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.monprojet.data.entities.User;
import com.example.monprojet.data.repositories.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si l'admin existe déjà
        String adminEmail = "24120@supnum.mr";
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setUsername("Ahmed");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("K2r42070038"));
            admin.setRole("ADMIN");
            
            userRepository.save(admin);
            System.out.println("Administrateur système créé avec succès !");
        }
    }
}
