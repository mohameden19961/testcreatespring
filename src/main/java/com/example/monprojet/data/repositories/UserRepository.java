package com.example.monprojet.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.monprojet.data.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    User findByEmail(String email);
    boolean existsByEmail(String email);
}