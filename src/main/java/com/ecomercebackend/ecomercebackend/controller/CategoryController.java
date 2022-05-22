package com.ecomercebackend.ecomercebackend.controller;


import com.ecomercebackend.ecomercebackend.dto.ProductIdCategoryId;
import com.ecomercebackend.ecomercebackend.models.Category;
import com.ecomercebackend.ecomercebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory=categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/addProductToCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addProductToCategory(@RequestBody ProductIdCategoryId productIdCategoryId){
        Category category = categoryService.addProduct(productIdCategoryId);
        return ResponseEntity.ok(category);
    }
}
