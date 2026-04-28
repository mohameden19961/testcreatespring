package com.example.monprojet.services;

import com.example.monprojet.data.entities.Product;
import com.example.monprojet.data.repositories.ProductRepository;
import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ApiResponse<List<Product>> findAll() {
        List<Product> products = productRepository.findAll();
        return new ApiResponse<>("Liste des produits récupérée avec succès", true, products);
    }

    public ApiResponse<Product> findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erreur : Produit non trouvé avec l'id : " + id));
        return new ApiResponse<>("Produit trouvé", true, product);
    }

    public ApiResponse<Product> save(Product product) {
        Product saved = productRepository.save(product);
        return new ApiResponse<>("Le produit '" + saved.getName() + "' a été créé avec succès !", true, saved);
    }

    public ApiResponse<Product> update(Long id, Product productDetails) {
        // findById lance l'exception si l'id n'existe pas, donc le message d'erreur est géré
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Echec de la mise à jour : l'id " + id + " n'existe pas"));
        
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        Product updated = productRepository.save(product);
        
        return new ApiResponse<>("Le produit a été mis à jour avec succès", true, updated);
    }

    public ApiResponse<Void> delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Echec de la suppression : l'id " + id + " n'existe pas");
        }
        productRepository.deleteById(id);
        return new ApiResponse<>("Le produit avec l'ID " + id + " a été supprimé", true, null);
    }
}