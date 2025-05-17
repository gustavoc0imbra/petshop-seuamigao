package org.uniara.ordersapi.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class OrderProduct {
    @EmbeddedId
    private OrderProductPK order;
    private Long productId;
    private int amount;
    private BigDecimal price;
}
