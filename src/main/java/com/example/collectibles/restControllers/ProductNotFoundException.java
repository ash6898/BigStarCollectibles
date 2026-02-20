package com.example.collectibles.restControllers;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String id) {
        super(" Could not find product with id: " + id);
    }

}
