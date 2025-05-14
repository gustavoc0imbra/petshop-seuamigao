package org.uniara.productscatalogapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uniara.productscatalogapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
