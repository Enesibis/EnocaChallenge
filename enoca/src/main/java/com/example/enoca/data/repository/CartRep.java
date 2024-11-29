package com.example.enoca.data.repository;

import com.example.enoca.data.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRep extends JpaRepository<Cart, Long> {
    Cart findByCustomerId(Long customerId);

    Cart getById(Long cartId);

    Cart getCartByCustomerId(Long customerId);
}
