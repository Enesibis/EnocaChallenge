package com.example.enoca.data.repository;

import com.example.enoca.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRep  extends JpaRepository<Customer, Long> {
    Customer getCustomerById(Long customerId);
}
