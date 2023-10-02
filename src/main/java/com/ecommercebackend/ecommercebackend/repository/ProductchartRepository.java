package com.ecommercebackend.ecommercebackend.repository;

import com.ecommercebackend.ecommercebackend.models.Productchart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductchartRepository extends JpaRepository<Productchart, UUID> {

}
