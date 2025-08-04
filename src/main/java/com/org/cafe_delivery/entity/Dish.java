package com.org.cafe_delivery.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "dishes", schema = "cafe_delivery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_seq")
    @SequenceGenerator(name = "dish_seq", schema = "cafe_delivery", sequenceName = "dish_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false, scale = 2)
    private BigDecimal price;

    @Column(length = 500)
    private String description;
}
