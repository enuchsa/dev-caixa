package com.gatitus.devcaixa.domain.sale;

import com.gatitus.devcaixa.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "sale_details")
@Entity(name = "sale_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class SaleDetail {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Product product;
    private Integer amount;
    private Float total;

    public void calculateTotal() {
        this.total = product.getPrice() * amount;
    }
}
