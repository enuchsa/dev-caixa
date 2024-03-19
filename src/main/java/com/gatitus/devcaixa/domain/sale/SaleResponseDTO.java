package com.gatitus.devcaixa.domain.sale;

import com.gatitus.devcaixa.domain.product.Product;

import java.util.Date;
import java.util.List;

public record SaleResponseDTO(String id, String code, List<SaleDetail> saleDetails, Float total, Date date) {
    public SaleResponseDTO(Sale sale) {
        this(sale.getId(), sale.getCode(), sale.getSaleDetails(), sale.getTotal(), sale.getDate());
    }
}
