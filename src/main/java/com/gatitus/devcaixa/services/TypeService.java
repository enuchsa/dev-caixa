package com.gatitus.devcaixa.services;

import com.gatitus.devcaixa.domain.type.Type;
import com.gatitus.devcaixa.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    private TypeRepository repository;

    @Autowired
    public void setRepository(TypeRepository repository) {
        this.repository = repository;
    }

    public List<Type> list() {
        return this.repository.findAll();
    }

    public Optional<Type> find(String id) {
        return this.repository.findById(id);
    }

    public void insert(Type type) {
        this.repository.save(type);
    }

    public void update(Type type) {
        this.repository.saveAndFlush(type);
    }
}
