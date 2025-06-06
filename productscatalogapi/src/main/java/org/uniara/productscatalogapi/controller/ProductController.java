package org.uniara.productscatalogapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.productscatalogapi.constant.Constant;
import org.uniara.productscatalogapi.consumers.AuthConsumer;
import org.uniara.productscatalogapi.model.Product;
import org.uniara.productscatalogapi.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    @Autowired
    private ProductService productService;

    private AuthConsumer authConsumer = new AuthConsumer();

    @GetMapping(Constant.API_PRODUCTS_URL)
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping(Constant.API_PRODUCTS_URL + "/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping(Constant.API_PRODUCTS_URL)
    public ResponseEntity<Product> save(@RequestHeader("Authorization") String token, @RequestBody Product product) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @PutMapping(Constant.API_PRODUCTS_URL)
    public ResponseEntity<Product> update(@RequestHeader("Authorization") String token, @RequestBody Product product) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping(Constant.API_PRODUCTS_URL + "/{id}")
    public ResponseEntity<Product> delete(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {

        if (!authConsumer.isAuthenticated(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "É preciso estar autenticado");
        }

        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
