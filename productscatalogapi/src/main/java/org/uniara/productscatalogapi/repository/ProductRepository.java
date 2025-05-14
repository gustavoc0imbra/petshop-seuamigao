package org.uniara.productscatalogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uniara.productscatalogapi.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
