package com.example.enoca.data.repository;

import com.example.enoca.controller.dto.CreateProduct;
import com.example.enoca.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRep extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long productId);

    Product save(CreateProduct product);
}
