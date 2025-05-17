package org.uniara.ordersapi.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderProductPK {
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;
}
