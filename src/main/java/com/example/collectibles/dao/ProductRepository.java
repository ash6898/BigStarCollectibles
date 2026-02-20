package com.example.collectibles.dao;

import com.example.collectibles.beans.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    List<Product> searchByName(@Param("searchString") String keyword);

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    List<Product> searchById(@Param("id") String keyword);
    
    @Override
    Iterable<Product> findAll();

    @Query("SELECT p FROM Product p WHERE p.categoryId = :id")
    List<Product> searchByCategoryId(@Param("id") Integer keyword);

}
