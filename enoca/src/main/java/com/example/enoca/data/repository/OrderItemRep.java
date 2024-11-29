package com.example.enoca.data.repository;

import com.example.enoca.data.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRep extends JpaRepository<OrderItem, Long> {
}
