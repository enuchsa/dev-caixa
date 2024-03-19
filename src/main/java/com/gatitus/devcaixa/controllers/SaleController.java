package com.gatitus.devcaixa.controllers;

import com.gatitus.devcaixa.domain.product.Product;
import com.gatitus.devcaixa.domain.sale.Sale;
import com.gatitus.devcaixa.domain.sale.SaleRequestDTO;
import com.gatitus.devcaixa.domain.sale.SaleResponseDTO;
import com.gatitus.devcaixa.services.ProductService;
import com.gatitus.devcaixa.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("sale")
public class SaleController {

    private SaleService saleService;
    private ProductService productService;

    @Autowired
    public void setSaleService(SaleService saleService) {
        this.saleService = saleService;
    }
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<SaleResponseDTO> getAll() {
        return this.saleService.list().stream().map(SaleResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getOne(@PathVariable String id) {
        Optional<Sale> product = this.saleService.find(id);

        return product.map(value -> ResponseEntity.ok().body(new SaleResponseDTO(value))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> save(@RequestBody SaleRequestDTO saleRequestDTO) {
        Sale sale = new Sale(saleRequestDTO);
        sale.setTotal(0F);

        sale.getSaleDetails().forEach(saleDetail -> {
            Optional<Product> optionalProduct = this.productService.find(saleDetail.getProduct().getId());
            saleDetail.getProduct().setPrice(optionalProduct.get().getPrice());

            optionalProduct.ifPresent(value -> {
                value.setAmount(value.getAmount() - saleDetail.getAmount());
                this.productService.update(value);
            });

            saleDetail.calculateTotal();
            sale.setTotal(sale.getTotal() + saleDetail.getTotal());
        });

        sale.setDate(new Date());
        this.saleService.insert(sale);

        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> update(@PathVariable String id, @RequestBody SaleRequestDTO saleRequestDTO) {
        Optional<Sale> optionalSale = this.saleService.find(id);

        if (optionalSale.isEmpty()) {
            return ResponseEntity.status(400).build();
        }

        Sale sale = new Sale(saleRequestDTO);
        sale.setId(id);
        sale.setDate(new Date());

        optionalSale.get().getSaleDetails().forEach( saleDetail -> {
            Optional<Product> optionalProduct = this.productService.find(saleDetail.getProduct().getId());

            optionalProduct.ifPresent(value -> {
                value.setAmount(saleDetail.getAmount() - value.getAmount());
                this.productService.update(value);
            });

            saleDetail.calculateTotal();
            sale.setTotal(sale.getTotal() + saleDetail.getTotal());
            }
        );

        this.saleService.update(sale);

        return ResponseEntity.status(201).build();
    }
}
