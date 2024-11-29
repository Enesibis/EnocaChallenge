package com.example.enoca.data.repository;

import com.example.enoca.data.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRep extends JpaRepository<Order, Long> {

    List<Order> findByCustomer_Id(Long customerId);
}
