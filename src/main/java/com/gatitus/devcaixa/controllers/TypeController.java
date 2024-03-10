package com.gatitus.devcaixa.controllers;

import com.gatitus.devcaixa.domain.type.Type;
import com.gatitus.devcaixa.domain.type.TypeRequestDTO;
import com.gatitus.devcaixa.domain.type.TypeResponseDTO;
import com.gatitus.devcaixa.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/type")
public class TypeController {

    private TypeService typeService;

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping
    public List<TypeResponseDTO> getAll() {
        return this.typeService.list().stream().map(TypeResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponseDTO> getOne(@PathVariable String id) {
        Optional<Type> type = this.typeService.find(id);

        return type.map(value -> ResponseEntity.ok().body(new TypeResponseDTO(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TypeResponseDTO> save(@RequestBody TypeRequestDTO typeRequestDTO) {
        Type type = new Type(typeRequestDTO);
        this.typeService.insert(type);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeResponseDTO> update(@PathVariable String id, @RequestBody TypeRequestDTO typeRequestDTO) {
        Optional<Type> optionalType = this.typeService.find(id);

        if (optionalType.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Type type = new Type(typeRequestDTO);
        type.setId(id);
        this.typeService.update(type);

        return ResponseEntity.status(201).build();
    }
}
