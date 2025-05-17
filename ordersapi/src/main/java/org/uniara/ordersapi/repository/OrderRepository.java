package org.uniara.ordersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uniara.ordersapi.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
