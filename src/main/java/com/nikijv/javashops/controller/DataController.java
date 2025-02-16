package com.nikijv.javashops.controller;

import com.nikijv.javashops.model.Product;
import com.nikijv.javashops.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DataController {

    private final ProductRepository productRepository;

    public DataController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> products() {
        return ResponseEntity.ok((List<Product>)productRepository.findAll());
    }

    @GetMapping("product/{id}")
    public ResponseEntity<Optional<Product>> product(@PathVariable Long id) {
        return ResponseEntity.ok(productRepository.findById(id));
    }
}
//http://localhost:8080/swagger-ui/index.html
