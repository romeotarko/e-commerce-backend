package com.ecommercebackend.ecommercebackend.Category;

import com.ecommercebackend.ecommercebackend.models.Category;
import com.ecommercebackend.ecommercebackend.repository.CategoryRepository;
import com.ecommercebackend.ecommercebackend.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;


    @Test
    void should_create_category(){
        Category category=new Category("Furniture");

        Category categoryToCreate=categoryService.createCategory(category);
        then(categoryToCreate.getName()).isEqualTo("Furniture");
    }

}

