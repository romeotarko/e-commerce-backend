package com.ecomercebackend.ecomercebackend.service;

import com.ecomercebackend.ecomercebackend.dto.ProductIdCategoryId;
import com.ecomercebackend.ecomercebackend.models.Category;
import com.ecomercebackend.ecomercebackend.models.Product;
import com.ecomercebackend.ecomercebackend.repository.CategoryRepository;
import com.ecomercebackend.ecomercebackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Category addProduct(ProductIdCategoryId productIdCategoryId) {
        Product product = productRepository.getById(productIdCategoryId.getProductId());
        Category category =categoryRepository.getById(productIdCategoryId.getCategoryId());

        category.getProducts().add(product);
        return categoryRepository.saveAndFlush(category);
    }
}