package com.example.monprojet.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.monprojet.dto.ApiResponse;
import com.example.monprojet.dto.ProductDTO;
import com.example.monprojet.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDTO>>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDTO>> create(@jakarta.validation.Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(201).body(productService.save(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductDTO>> update(@PathVariable Long id, @jakarta.validation.Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.update(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(productService.delete(id));
    }
}