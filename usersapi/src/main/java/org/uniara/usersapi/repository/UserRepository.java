package org.uniara.usersapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.uniara.usersapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
