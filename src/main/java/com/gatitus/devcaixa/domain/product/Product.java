package com.gatitus.devcaixa.domain.product;

import com.gatitus.devcaixa.domain.type.Type;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String code;
    private String brand;
    private Float price;
    private Float buyPrice;
    private Integer amount;
    @ManyToOne(cascade = CascadeType.ALL)
    private Type type;

    public Product (ProductRequestDTO productRequestDTO) {
        this.name = productRequestDTO.name();
        this.code = productRequestDTO.code();
        this.brand = productRequestDTO.brand();
        this.price = productRequestDTO.price();
        this.buyPrice = productRequestDTO.buyPrice();
        this.amount = productRequestDTO.amount();
        this.type = productRequestDTO.type();
    }
}
