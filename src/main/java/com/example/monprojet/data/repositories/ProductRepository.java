package com.example.monprojet.data.repositories;

import com.example.monprojet.data.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Méthodes CRUD de base déjà incluses
}