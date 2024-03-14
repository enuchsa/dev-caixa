package com.gatitus.devcaixa.services;

import com.gatitus.devcaixa.domain.sale.Sale;
import com.gatitus.devcaixa.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    private SaleRepository repository;

    @Autowired
    public void setRepository(SaleRepository repository) {
        this.repository = repository;
    }

    public List<Sale> list() {
        return this.repository.findAll();
    }

    public Optional<Sale> find(String id) {
        return this.repository.findById(id);
    }

    public void insert(Sale sale) {
        this.repository.save(sale);
    }

    public void update(Sale sale) {
        this.repository.saveAndFlush(sale);
    }
}
