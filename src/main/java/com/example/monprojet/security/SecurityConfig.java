package com.example.monprojet.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Désactivé pour tester facilement l'API
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll() // Tout le monde voit
                .requestMatchers(HttpMethod.DELETE, "/api/products/**").hasRole("ADMIN") // Seul l'admin supprime
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults()); // Utilise l'authentification basique (nom d'utilisateur/mot de passe)
        
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // On crée un utilisateur en mémoire pour tester
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();        

        return new InMemoryUserDetailsManager(user, user2);
    }
}