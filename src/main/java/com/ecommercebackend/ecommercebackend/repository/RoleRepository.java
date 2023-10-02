package com.ecommercebackend.ecommercebackend.repository;

import com.ecommercebackend.ecommercebackend.models.ERoleType;
import com.ecommercebackend.ecommercebackend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERoleType name);
}

