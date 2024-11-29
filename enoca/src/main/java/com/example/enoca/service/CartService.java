package com.example.enoca.service;

import com.example.enoca.controller.dto.AddProduct;
import com.example.enoca.controller.dto.RemoveProduct;
import com.example.enoca.data.entity.Cart;

public interface CartService {


    Cart getCart(Long id);
    Cart updateCart(Cart cart);
    void emptyCart(Long id);


    void updateCartTotalPrice(Cart cart);
    Cart addProductToCart(Long customerId, AddProduct addProductRequest);
    Cart removeProductFromCart(Long customerId, RemoveProduct removeProductRequest);
}
