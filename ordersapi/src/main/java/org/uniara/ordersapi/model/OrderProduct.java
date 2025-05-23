package org.uniara.ordersapi.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public class OrderProduct {
    @Id
    private Long id;
    @ManyToOne
    private Order order;
    private Long productId;
    private int amount;
    private BigDecimal price;
}
