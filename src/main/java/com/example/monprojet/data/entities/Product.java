package com.example.monprojet.data.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // Génère les getters/setters (nécessite Lombok)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}