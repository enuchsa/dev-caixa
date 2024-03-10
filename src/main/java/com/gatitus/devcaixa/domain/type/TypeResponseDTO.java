package com.gatitus.devcaixa.domain.type;

public record TypeResponseDTO(String id, String name) {
    public TypeResponseDTO(Type type) {
        this(type.getId(), type.getName());
    }
}
