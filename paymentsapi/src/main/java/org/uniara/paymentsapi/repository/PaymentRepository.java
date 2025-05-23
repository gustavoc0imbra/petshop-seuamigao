package org.uniara.paymentsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uniara.paymentsapi.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
