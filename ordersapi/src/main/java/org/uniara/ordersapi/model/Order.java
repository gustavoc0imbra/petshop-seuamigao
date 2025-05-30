package org.uniara.ordersapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@ToString
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Date orderDate;
    private char status;
    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;
}
