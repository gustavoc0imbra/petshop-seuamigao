package org.uniara.paymentsapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uniara.paymentsapi.model.Payment;
import org.uniara.paymentsapi.repository.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> findById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deleteById(Long id) {
        paymentRepository.deleteById(id);
    }
}
