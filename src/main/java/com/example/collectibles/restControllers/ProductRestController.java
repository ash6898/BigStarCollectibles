package com.example.collectibles.restControllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.collectibles.dao.ProductRepository;
import com.example.collectibles.beans.Product;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductRestController {

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/bigstar/api/products")
    public List<Product> allProducts() {
        // List<Product> products = new ArrayList<>();
        return (List<Product>) productRepository.findAll();
    }

    @GetMapping("/bigstar/api/products/{id}")
    public Product getProductById(@PathVariable("id") String id) {
        return productRepository.findById(Integer.valueOf(id)).orElseThrow(() -> new ProductNotFoundException(id));
    }


}
