package com.ecommercebackend.ecommercebackend.repository;

import com.ecommercebackend.ecommercebackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Boolean existsByUsername(String username);

    Boolean existsByUsernameAndEmail(String username, String email);

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
