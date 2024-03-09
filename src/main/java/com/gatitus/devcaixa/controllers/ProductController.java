package com.gatitus.devcaixa.controllers;

import com.gatitus.devcaixa.domain.product.Product;
import com.gatitus.devcaixa.domain.product.ProductRequestDTO;
import com.gatitus.devcaixa.domain.product.ProductResponseDTO;
import com.gatitus.devcaixa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return repository.findAll().stream().map(ProductResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getOne(@PathVariable String id) {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(new ProductResponseDTO(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = new Product(productRequestDTO);
        repository.save(product);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable String id, @RequestBody ProductRequestDTO productRequestDTO) {
        Product product = repository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.status(400).build();
        }

        product = new Product(productRequestDTO);
        product.setId(id);
        repository.saveAndFlush(product);

        return ResponseEntity.status(201).build();
    }


}
