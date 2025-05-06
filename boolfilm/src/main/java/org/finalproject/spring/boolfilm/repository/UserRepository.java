package org.finalproject.spring.boolfilm.repository;

import java.util.Optional;

import org.finalproject.spring.boolfilm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
