package com.ecomercebackend.ecomercebackend.Product;

import com.ecomercebackend.ecomercebackend.dto.ProductCreateRequest;
import com.ecomercebackend.ecomercebackend.dto.ProductUpdateRequest;
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


    @Test
    void should_add_product(){
        ProductCreateRequest productCreateRequest=new ProductCreateRequest(
                "Book",
                "12",
                "Best book",
                "12"
        );
        productService.addProduct(productCreateRequest);
        List<Product> allProducts=productRepository.findAll();
        then(allProducts).isNotEmpty();
    }

    @Test
    void should_update_product(){
        ProductCreateRequest productCreateRequest=new ProductCreateRequest(
                "BookTest",
                "121",
                "Best",
                "13"
        );
       Product existingProduct= productService.addProduct(productCreateRequest);

        ProductUpdateRequest productUpdateRequest = new ProductUpdateRequest(
                "book",
                "12",
                "test1",
                "14"
        );
        Product updateProduct=productService.updateProduct(existingProduct.getId(),productUpdateRequest);

        then(updateProduct).isNotNull();
        then(updateProduct.getName()).isEqualTo("book");
    }
}
