package com.ecomercebackend.ecomercebackend.repository;

import com.ecomercebackend.ecomercebackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsByName(String name);

    @Query("select p from Product p where (:name is null or p.name like :name) and (:description is null or p.description like :description)")
    List<Product> findAllByNameOrDescription(String name, String description);


}