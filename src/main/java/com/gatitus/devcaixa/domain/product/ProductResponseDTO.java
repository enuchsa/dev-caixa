package com.gatitus.devcaixa.domain.product;

import com.gatitus.devcaixa.domain.type.Type;

public record ProductResponseDTO(String id, String name, String code, String brand,
                                 Float price, Float buyPrice, Integer amount, Type type) {
    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getCode(), product.getBrand(), product.getPrice(),
                product.getBuyPrice(), product.getAmount(), product.getType());
    }
}
