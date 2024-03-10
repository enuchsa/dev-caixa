package com.gatitus.devcaixa.repositories;

import com.gatitus.devcaixa.domain.type.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, String> {
}
