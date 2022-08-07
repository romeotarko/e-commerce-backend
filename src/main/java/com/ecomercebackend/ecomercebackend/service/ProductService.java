package com.ecomercebackend.ecomercebackend.service;

import com.ecomercebackend.ecomercebackend.Exceptions.EcommerceApplicationException;
import com.ecomercebackend.ecomercebackend.dto.ProductDto;
import com.ecomercebackend.ecomercebackend.dto.ProductSearchDto;
import com.ecomercebackend.ecomercebackend.models.Category;
import com.ecomercebackend.ecomercebackend.models.Product;
import com.ecomercebackend.ecomercebackend.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public Product addProductToChart(ProductDto productDto) {
        if (productRepository.existsByName(productDto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Product with name: " + productDto.getName() + " exists"));
        }
        if(productRepository.getUnitInStockFromProductName(productDto.getName()) == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Product name: " + productDto.getName() + " doesn't have unit in stock"));
        }

        Product product = new Product();
        product.setId(UUID.fromString(UUID.randomUUID().toString()));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setUnit_in_stock(productDto.getUnit_in_stock());

        return productRepository.saveAndFlush(product);
    }

    public Product updateProduct(UUID id, ProductDto productDto) {

        Product product = productRepository.getById(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setUnit_in_stock(productDto.getUnit_in_stock());

        return productRepository.saveAndFlush(product);
    }

    public void deleteProduct(UUID id) {
        checkIfProductExists(id);
        productRepository.deleteById(id);
    }

    public void checkIfProductExists(UUID ID) {
        if (!productRepository.existsById(ID)) {
            log.debug("Product with id: " + ID + " doesn't exist");
            throw new EcommerceApplicationException("Product with id: " + ID + " doesn't exist", HttpStatus.NOT_FOUND);
        }
    }
    public Product findById(UUID id) {
        return productRepository.findById(id).orElseThrow(() ->
                new EcommerceApplicationException("Product by id " + id + " was not found", HttpStatus.NOT_FOUND));
    }
    public Product addProductToCategory(UUID productId, UUID categoryId) {

        Product product =findById(productId);
        Category category=categoryService.findCategoryById(categoryId);

        product.setCategory(category);
        return productRepository.saveAndFlush(product);
    }

    public List<Product> search(ProductSearchDto productSearchDto) {
        return productRepository.findAllByNameOrDescription(productSearchDto.getName(),productSearchDto.getDescription());
    }
}