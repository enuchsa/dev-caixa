package com.gatitus.devcaixa.domain.product;

import com.gatitus.devcaixa.domain.type.Type;

public record ProductRequestDTO(String id, String name, String code, String brand,
                                Float price, Float buyPrice, Integer amount, Type type) {
}
