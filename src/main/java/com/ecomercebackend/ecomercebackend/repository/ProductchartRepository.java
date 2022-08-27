package com.ecomercebackend.ecomercebackend.repository;

import com.ecomercebackend.ecomercebackend.models.Category;
import com.ecomercebackend.ecomercebackend.models.Productchart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductchartRepository extends JpaRepository<Productchart, UUID> {

}
