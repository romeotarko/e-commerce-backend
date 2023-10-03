package com.ecommercebackend.ecommercebackend.repository;
import com.ecommercebackend.ecommercebackend.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
