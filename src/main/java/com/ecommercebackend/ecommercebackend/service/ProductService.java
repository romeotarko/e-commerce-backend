package com.ecommercebackend.ecommercebackend.service;

import com.ecommercebackend.ecommercebackend.Exceptions.EcommerceApplicationException;
import com.ecommercebackend.ecommercebackend.dto.ProductDto;
import com.ecommercebackend.ecommercebackend.dto.ProductSearchDto;
import com.ecommercebackend.ecommercebackend.models.Category;
import com.ecommercebackend.ecommercebackend.models.Product;
import com.ecommercebackend.ecommercebackend.models.Productchart;
import com.ecommercebackend.ecommercebackend.repository.OrdersRepository;
import com.ecommercebackend.ecommercebackend.repository.ProductRepository;
import com.ecommercebackend.ecommercebackend.repository.ProductchartRepository;
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
    @Autowired
    private ProductchartRepository productchartRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product create(Product product) {
        return productRepository.saveAndFlush(product);
    }

    public Productchart addProductToChart(String productName) {
        if (productRepository.existsByName(productName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Product with name: " + productName + " exists"));
        }
        if(productRepository.getUnitInStockFromProductName(productName) == 0){
            throw new EcommerceApplicationException("Product name: " + productName + " doesn't have unit in stock",HttpStatus.NOT_FOUND);
        }

        //TODO finish this
        Productchart productChart = new Productchart();
        productChart.setId(UUID.fromString(UUID.randomUUID().toString()));
        productChart.setProducts(productRepository.findByName(productName));
        productChart.setName(productName);

        return productchartRepository.saveAndFlush(productChart);
    }

    //TODO finish this
    public Productchart finishOrder(UUID id, Productchart productChart) {
       return null;
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