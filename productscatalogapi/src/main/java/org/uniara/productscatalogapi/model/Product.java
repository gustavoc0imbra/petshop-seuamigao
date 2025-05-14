package org.uniara.productscatalogapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private float weight;
    private BigDecimal price;
    private boolean isAvailable;
}
