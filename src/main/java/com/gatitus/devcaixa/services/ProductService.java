package com.gatitus.devcaixa.services;

import com.gatitus.devcaixa.domain.product.Product;
import com.gatitus.devcaixa.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> list() {
        return this.repository.findAll();
    }

    public Optional<Product> find(String id) {
        return this.repository.findById(id);
    }

    public void insert(Product product) {
        this.repository.save(product);
    }

    public void update(Product product) {
        this.repository.saveAndFlush(product);
    }
}
