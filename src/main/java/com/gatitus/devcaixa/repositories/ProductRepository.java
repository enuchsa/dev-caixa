package com.gatitus.devcaixa.repositories;


import com.gatitus.devcaixa.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
