package com.gatitus.devcaixa.repositories;

import com.gatitus.devcaixa.domain.sale.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, String> {
}
