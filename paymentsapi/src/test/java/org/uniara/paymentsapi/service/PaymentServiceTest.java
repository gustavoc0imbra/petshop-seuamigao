package org.uniara.paymentsapi.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.uniara.paymentsapi.model.Payment;
import org.uniara.paymentsapi.repository.PaymentRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private Logger LOGGER = LogManager.getLogger();

    @Autowired
    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve retornar todos os pagamentos")
    void it_should_list_all_payments() {
        List<Payment> payments = setupPayments();

        when(paymentService.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.findAll();

        assertEquals(payments, result);
    }

    @Test
    @DisplayName("Deve retornar um pagamento pelo Id")
    void it_should_return_payment_by_id() {
        Payment payment = setupPayment();

        when(paymentService.findById(payment.getId())).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentService.findById(payment.getId());

        assertTrue(result.isPresent());
        assertEquals(payment, result.get());
    }

    @Test
    @DisplayName("Deve retornar vazio quando buscar pagamento inexistente pelo Id")
    void it_should_return_empty_when_not_found_payment_by_id() {
        Payment payment = setupPayment();

        when(paymentService.findById(payment.getId())).thenReturn(Optional.empty());

        Optional<Payment> result = paymentService.findById(payment.getId());

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Deve retornar pagamento salvo")
    void it_should_return_saved_payment() {
        Payment payment = setupPayment();

        when(paymentService.save(payment)).thenReturn(payment);

        Payment result = paymentService.save(payment);

        assertEquals(payment, result);
    }

    @Test
    @DisplayName("Deve deletar pagamento pelo Id")
    void deleteById() {
        List<Payment> payments = setupPayments();
        Payment payment = payments.get(0);

        when(paymentRepository.findById(payment.getId())).thenReturn(Optional.empty());

        paymentService.deleteById(payment.getId());

        Optional<Payment> result = paymentService.findById(payment.getId());

        assertTrue(result.isEmpty());
    }

    private List<Payment> setupPayments() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId(1L);
        payment.setStatus('P');
        payment.setTotalAmount(new BigDecimal(150));
        payment.setPaymentMethod("PIX");

        List<Payment> payments = new ArrayList<>();

        for (int i = 1; i <= 3; i++) {
            payment.setId(Long.parseLong("" + i));
            payment.setOrderId(Long.parseLong("" + i));
            payments.add(payment);
        }

        return payments;
    }

    private Payment setupPayment() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setOrderId(1L);
        payment.setStatus('P');
        payment.setTotalAmount(new BigDecimal(150));
        payment.setPaymentMethod("PIX");

        return payment;
    }
}