package com.gatitus.devcaixa.domain.sale;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Table(name = "sales")
@Entity(name = "sales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Sale {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String code;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<SaleDetail> saleDetails;
    private Float total;
    private Date date;

    public Sale(SaleRequestDTO saleRequestDTO) {
        this.code = saleRequestDTO.code();
        this.saleDetails = saleRequestDTO.saleDetails();
    }
}
