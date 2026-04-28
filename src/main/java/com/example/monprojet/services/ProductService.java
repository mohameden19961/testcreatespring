package com.example.monprojet.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.monprojet.data.entities.Product;
import com.example.monprojet.data.repositories.ProductRepository;
import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.ProductDTO;
import com.example.monprojet.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    public ApiResponse<List<ProductDTO>> findAll() {
    List<Product> products = productRepository.findAll();

    List<ProductDTO> productDTOs = new ArrayList<>();
    for (int i = 0; i < products.size(); i++) {
        ProductDTO dto = new ProductDTO();
        dto.setId(products.get(i).getId());
        dto.setName(products.get(i).getName());
        dto.setPrice(products.get(i).getPrice());
        productDTOs.add(dto);
    }

    return new ApiResponse<>("Liste récupérée avec succès", true, productDTOs);
}
    

    public ApiResponse<ProductDTO> findById(Long id) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Erreur : Produit non trouvé avec l'id : " + id));

    ProductDTO dto = new ProductDTO();
    dto.setId(product.getId());
    dto.setName(product.getName());
    dto.setPrice(product.getPrice());

    return new ApiResponse<>("Produit trouvé", true, dto);
   }

    public ApiResponse<ProductDTO> save(ProductDTO productDTO) {

    // DTO → Entity
    Product product = new Product();
    product.setName(productDTO.getName());
    product.setPrice(productDTO.getPrice());

    Product saved = productRepository.save(product);

    // Entity → DTO
    ProductDTO result = new ProductDTO();
    result.setId(saved.getId());
    result.setName(saved.getName());
    result.setPrice(saved.getPrice());

    return new ApiResponse<>(
        "Le produit '" + result.getName() + "' a été créé avec succès !",
        true,
        result
    );
}

    public ApiResponse<ProductDTO> update(Long id, ProductDTO productDTO) {
        // findById lance l'exception si l'id n'existe pas, donc le message d'erreur est géré
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Echec de la mise à jour : l'id " + id + " n'existe pas"));
        
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        Product updated = productRepository.save(product);
        
        // Entity → DTO
        ProductDTO dto = new ProductDTO();
        dto.setId(updated.getId());
        dto.setName(updated.getName());
        dto.setPrice(updated.getPrice());

        return new ApiResponse<>("Le produit a été mis à jour avec succès", true, dto);
    }

    public ApiResponse<Void> delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Echec de la suppression : l'id " + id + " n'existe pas");
        }
        productRepository.deleteById(id);
        return new ApiResponse<>("Le produit avec l'ID " + id + " a été supprimé", true, null);
    }
}