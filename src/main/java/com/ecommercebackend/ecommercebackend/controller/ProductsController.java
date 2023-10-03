package com.ecommercebackend.ecommercebackend.controller;

import com.ecommercebackend.ecommercebackend.dto.ProductDto;
import com.ecommercebackend.ecommercebackend.dto.ProductSearchDto;
import com.ecommercebackend.ecommercebackend.models.Product;
import com.ecommercebackend.ecommercebackend.models.Productchart;
import com.ecommercebackend.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Product>> getAll() {
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Product>> search(@RequestBody ProductSearchDto productSearchDto){
        List<Product> products=productService.search(productSearchDto);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/message")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getMessage() {
        return "Hello";
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product createdProduct = productService.create(product);
        return ResponseEntity.ok(createdProduct);
    }

    @PostMapping("/addProductToChart")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Productchart> addProductToChart(@Valid @RequestBody String productName) {
        Productchart addProduct = productService.addProductToChart(productName);
        return new ResponseEntity<>(addProduct, HttpStatus.OK);
    }

    @DeleteMapping("/removeProductFromChart")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<?> removeProductFromChart(@PathVariable("ID") UUID id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //TODO finish this
    @PutMapping("/finishOrder")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity<Productchart> finishOrder(@PathVariable("ID") UUID id, @RequestBody Productchart productChart){
        Productchart submitRequest = productService.finishOrder(id, productChart);
        return new ResponseEntity<>(submitRequest, HttpStatus.OK);
    }

    @PutMapping("/updateProduct{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Product> updateProduct(@PathVariable("ID") UUID id, @RequestBody ProductDto productDto) {
        Product updateProduct = productService.updateProduct(id, productDto);
        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @PutMapping("/addProductToCategory/{productId}/category/{categoryId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProductToCategory(@PathVariable("productId") UUID productId, @PathVariable("categoryId") UUID categoryId){
        Product product = productService.addProductToCategory(productId,categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable("ID") UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
