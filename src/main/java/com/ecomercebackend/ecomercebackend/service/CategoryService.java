package com.ecomercebackend.ecomercebackend.service;

import com.ecomercebackend.ecomercebackend.Exceptions.EcommerceApplicationException;

import com.ecomercebackend.ecomercebackend.models.Category;
import com.ecomercebackend.ecomercebackend.models.Product;
import com.ecomercebackend.ecomercebackend.repository.CategoryRepository;
import com.ecomercebackend.ecomercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;


    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category){
        return categoryRepository.saveAndFlush(category);
    }

    public Category findCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EcommerceApplicationException("Category by id" + id + "was not found", HttpStatus.NOT_FOUND));
    }
}