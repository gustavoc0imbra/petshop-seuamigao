package org.uniara.paymentsapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter @Setter
public class Payment {
    @Id
    private Long id;
    private Long orderId;
    private char status;
    private String paymentMethod;
    private BigDecimal totalAmount;
}
