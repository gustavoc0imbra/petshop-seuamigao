package org.uniara.paymentsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.paymentsapi.constant.Constant;
import org.uniara.paymentsapi.model.Payment;
import org.uniara.paymentsapi.service.PaymentService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping(Constant.API_PAYMENTS_URL)
    public ResponseEntity<List<Payment>> findAll(/*@RequestHeader("Authorization") String token*/) {
        return ResponseEntity.ok(paymentService.findAll());
    }

    @GetMapping(Constant.API_PAYMENTS_URL + "/{id}")
    public ResponseEntity<Optional<Payment>> findById(/*@RequestHeader("Authorization") String token,*/ @PathVariable("id") Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping(Constant.API_PAYMENTS_URL)
    public ResponseEntity<Payment> save(/*@RequestHeader("Authorization") String token,*/ @RequestBody Payment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.save(payment));
    }

    @PutMapping(Constant.API_PAYMENTS_URL)
    public ResponseEntity<Payment> update(/*@RequestHeader("Authorization") String token,*/ @RequestBody Payment payment) {
        return ResponseEntity.ok(paymentService.save(payment));
    }

    @DeleteMapping(Constant.API_PAYMENTS_URL + "/{id}")
    public ResponseEntity<Payment> delete(/*@RequestHeader("Authorization") String token,*/ @PathVariable("id") Long id) {
        paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
