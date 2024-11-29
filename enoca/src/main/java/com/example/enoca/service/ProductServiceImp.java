package com.example.enoca.service;

import java.time.LocalDateTime;

import com.example.enoca.controller.dto.CreateProduct;
import com.example.enoca.data.entity.Product;
import com.example.enoca.data.entity.ProductHistory;
import com.example.enoca.data.repository.ProductHistoryRep;
import com.example.enoca.data.repository.ProductRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRep productRepository;

    @Autowired
    private ProductHistoryRep productPriceHistoryRepository;


    @Override
    public Product createProduct(CreateProduct createProduct) {
        Product product = new Product();
        product.setName(createProduct.getName());
        product.setPrice(createProduct.getPrice());
        product.setStock(createProduct.getStock());
        product.setCreateDate(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, CreateProduct updatedProduct) {

        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("not found: " + productId));


        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());


        Product savedProduct = productRepository.save(existingProduct);

        ProductHistory priceHistory = new ProductHistory();
        priceHistory.setProduct(savedProduct);
        priceHistory.setPrice(updatedProduct.getPrice());
        priceHistory.setQuantity(updatedProduct.getStock());
        priceHistory.setChangeData(LocalDateTime.now());


        productPriceHistoryRepository.save(priceHistory);


        return savedProduct;
    }



    @Override
    public void deleteProduct(Long productId) {

        productRepository.deleteById(productId);
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }
}
