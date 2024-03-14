package com.gatitus.devcaixa.domain.sale;

import com.gatitus.devcaixa.domain.product.Product;

import java.util.Date;
import java.util.List;

public record SaleResponseDTO(String id, String code, List<Product> products, Float total, Date date) {
    public SaleResponseDTO(Sale sale) {
        this(sale.getId(), sale.getCode(), sale.getProducts(), sale.getTotal(), sale.getDate());
    }
}
