package org.uniara.ordersapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.ordersapi.constant.Constant;
import org.uniara.ordersapi.consumers.AuthConsumer;
import org.uniara.ordersapi.model.Order;
import org.uniara.ordersapi.model.OrderProduct;
import org.uniara.ordersapi.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    private AuthConsumer authConsumer = new AuthConsumer();

    @GetMapping(Constant.API_ORDERS_URL)
    public ResponseEntity<List<Order>> findAll(@RequestHeader("Authorization") String token) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping(Constant.API_ORDERS_URL + "/{id}")
    public ResponseEntity<Optional<Order>> findById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping(Constant.API_ORDERS_URL + "/{userId}/orders")
    public ResponseEntity<Optional<List<Order>>> findByUserId(@RequestHeader("Authorization") String token, @PathVariable("userId") Long userId) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        return ResponseEntity.ok(orderService.findByUserId(userId));
    }

    @PostMapping(Constant.API_ORDERS_URL)
    public ResponseEntity<Order> save(@RequestHeader("Authorization") String token, @RequestBody Order order) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        if (order.getOrderProducts().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        for (OrderProduct orderProduct : order.getOrderProducts()) {
            orderProduct.setOrder(order);
        }

        order.setOrderDate(new Date());

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }

    @PutMapping(Constant.API_ORDERS_URL)
    public ResponseEntity<Order> update(@RequestHeader("Authorization") String token, @RequestBody Order order) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        return ResponseEntity.ok(orderService.save(order));
    }

    @DeleteMapping(Constant.API_ORDERS_URL + "/{id}")
    public ResponseEntity<Order> delete(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        orderService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
