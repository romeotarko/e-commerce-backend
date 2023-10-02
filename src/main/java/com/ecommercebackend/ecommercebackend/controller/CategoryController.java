package com.ecommercebackend.ecommercebackend.controller;


import com.ecommercebackend.ecommercebackend.models.Category;
import com.ecommercebackend.ecommercebackend.service.CategoryService;
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


}
