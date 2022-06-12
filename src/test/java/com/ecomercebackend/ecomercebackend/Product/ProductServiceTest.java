package com.ecomercebackend.ecomercebackend.Product;

import com.ecomercebackend.ecomercebackend.dto.ProductDto;
import com.ecomercebackend.ecomercebackend.models.Product;
import com.ecomercebackend.ecomercebackend.repository.ProductRepository;
import com.ecomercebackend.ecomercebackend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


   //TODO test create product

    @Test
    void should_add_product_to_chart(){
        ProductDto productDto =new ProductDto(
                "Book",
                "12",
                "Best book",
                "12"
        );
        productService.addProductToChart(productDto);
        List<Product> allProducts=productRepository.findAll();
        then(allProducts).isNotEmpty();
    }

    @Test
    void should_update_product(){
        ProductDto productDto =new ProductDto(
                "BookTest",
                "121",
                "Best",
                "13"
        );
       Product existingProduct= productService.addProductToChart(productDto);

        ProductDto productToBeUpdated = new ProductDto(
                "book",
                "12",
                "test1",
                "14"
        );
        Product updateProduct=productService.updateProduct(existingProduct.getId(),productToBeUpdated);

        then(updateProduct).isNotNull();
        then(updateProduct.getName()).isEqualTo("book");
    }
}
