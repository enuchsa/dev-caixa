package com.gatitus.devcaixa.domain.type;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "types")
@Entity(name = "types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Type {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
}
