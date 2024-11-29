package com.example.enoca.data.repository;

import com.example.enoca.data.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRep extends JpaRepository<CartItem, Long> {
}
