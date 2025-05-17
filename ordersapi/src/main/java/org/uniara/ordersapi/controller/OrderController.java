package org.uniara.ordersapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.ordersapi.constant.Constant;
import org.uniara.ordersapi.model.Order;
import org.uniara.ordersapi.service.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping(Constant.API_ORDERS_URL)
    public ResponseEntity<List<Order>> findAll(/*@RequestHeader("Authorization") String token*/) {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping(Constant.API_ORDERS_URL + "/{id}")
    public ResponseEntity<Optional<Order>> findById(/*@RequestHeader("Authorization") String token,*/ @PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping(Constant.API_ORDERS_URL)
    public ResponseEntity<Order> save(/*@RequestHeader("Authorization") String token,*/ @RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }

    @PutMapping(Constant.API_ORDERS_URL)
    public ResponseEntity<Order> update(/*@RequestHeader("Authorization") String token,*/ @RequestBody Order order) {
        return ResponseEntity.ok(orderService.save(order));
    }

    @DeleteMapping(Constant.API_ORDERS_URL + "{id}")
    public ResponseEntity<Order> delete(/*@RequestHeader("Authorization") String token,*/ @PathVariable("id") Long id) {
        orderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
