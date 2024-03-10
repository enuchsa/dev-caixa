package com.gatitus.devcaixa.controllers;

import com.gatitus.devcaixa.domain.product.Product;
import com.gatitus.devcaixa.domain.product.ProductRequestDTO;
import com.gatitus.devcaixa.domain.product.ProductResponseDTO;
import com.gatitus.devcaixa.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return this.productService.list().stream().map(ProductResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getOne(@PathVariable String id) {
        Optional<Product> product = productService.find(id);

        return product.map(value -> ResponseEntity.ok().body(new ProductResponseDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@RequestBody ProductRequestDTO productRequestDTO) {
        Product product = new Product(productRequestDTO);
        this.productService.insert(product);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> update(@PathVariable String id, @RequestBody ProductRequestDTO productRequestDTO) {
        Optional<Product> optionalProduct = this.productService.find(id);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Product product = new Product(productRequestDTO);
        product.setId(id);
        this.productService.update(product);

        return ResponseEntity.status(201).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateStock(@PathVariable String id, @RequestBody ProductRequestDTO productRequestDTO) {
        Optional<Product> optionalProduct = this.productService.find(id);

        if (optionalProduct.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Product product = optionalProduct.get();
        product.setId(id);
        product.setPrice(productRequestDTO.price());
        product.setBuyPrice(productRequestDTO.buyPrice());
        product.setAmount(productRequestDTO.amount());
        this.productService.update(product);

        return ResponseEntity.status(201).build();
    }


}
