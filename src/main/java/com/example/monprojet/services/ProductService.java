package com.example.monprojet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monprojet.data.entities.Product;
import com.example.monprojet.data.entities.User;
import com.example.monprojet.data.repositories.ProductRepository;
import com.example.monprojet.data.repositories.UserRepository;
import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.ProductDTO;
import com.example.monprojet.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    


    private ProductDTO toDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }
        return dto;
    }
    
    public ApiResponse<List<ProductDTO>> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(toDTO(product));
        }
        return new ApiResponse<>("Liste des produits récupérée", true, productDTOs);
    }

    public ApiResponse<ProductDTO> findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Erreur : Produit non trouvé avec l'id : " + id));

        return new ApiResponse<>("Produit trouvé", true, toDTO(product));
    }

    public ApiResponse<ProductDTO> save(ProductDTO productDTO) {
        // DTO → Entity
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        if (productDTO.getUserId() != null) {
            User user = userRepository.findById(productDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + productDTO.getUserId()));
            product.setUser(user);
        }

        Product saved = productRepository.save(product);

        return new ApiResponse<>(
            "Le produit '" + saved.getName() + "' a été créé avec succès !",
            true,
            toDTO(saved)
        );
    }

    public ApiResponse<ProductDTO> update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Echec de la mise à jour : l'id " + id + " n'existe pas"));
        
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        if (productDTO.getUserId() != null) {
            User user = userRepository.findById(productDTO.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + productDTO.getUserId()));
            product.setUser(user);
        }
        
        Product updated = productRepository.save(product);
        
        return new ApiResponse<>("Le produit a été mis à jour avec succès", true, toDTO(updated));
    }

    public ApiResponse<Void> delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Echec de la suppression : l'id " + id + " n'existe pas");
        }
        productRepository.deleteById(id);
        return new ApiResponse<>("Le produit avec l'ID " + id + " a été supprimé", true, null);
    }
}