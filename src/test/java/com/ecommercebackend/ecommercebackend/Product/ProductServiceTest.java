package com.ecommercebackend.ecommercebackend.Product;

import com.ecommercebackend.ecommercebackend.Exceptions.EcommerceApplicationException;
import com.ecommercebackend.ecommercebackend.dto.ProductDto;
import com.ecommercebackend.ecommercebackend.models.Product;
import com.ecommercebackend.ecommercebackend.repository.ProductRepository;
import com.ecommercebackend.ecommercebackend.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;


    @Test
    void should_create_product(){
        Product product=new Product(
                "Book1",
                "14",
                "Best one",
                "1"
        );
        productService.create(product);
        then(product.getName()).isEqualTo("Book1");
    }

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
    void should_fail_add_to_chart_when_doesnt_have_unit_in_stock(){
        ProductDto productDto =new ProductDto(
                "Book",
                "12",
                "Best book",
                "0"
        );
        EcommerceApplicationException exception = assertThrows(EcommerceApplicationException.class, () -> {
            productService.addProductToChart(productDto);
        });

        assertEquals("Product doesn't have unit in stock", exception.getErrorMessage());
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
