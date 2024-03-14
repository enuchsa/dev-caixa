package com.gatitus.devcaixa.domain.sale;

import com.gatitus.devcaixa.domain.product.Product;

import java.util.Date;
import java.util.List;

public record SaleRequestDTO(String code, List<Product> products, Float total, Date date) {
}
