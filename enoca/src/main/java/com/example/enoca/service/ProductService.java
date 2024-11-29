package com.example.enoca.service;

import com.example.enoca.controller.dto.CreateProduct;
import com.example.enoca.data.entity.Product;

public interface ProductService {
    Product createProduct(CreateProduct product);
    void deleteProduct(Long productId);
    Product getProduct(Long productId);
    Product updateProduct(Long productId, CreateProduct updatedProduct);
}
