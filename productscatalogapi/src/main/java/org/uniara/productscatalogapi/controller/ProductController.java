package org.uniara.productscatalogapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uniara.productscatalogapi.model.Product;
import org.uniara.productscatalogapi.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(productService.findAll());
    }
}
