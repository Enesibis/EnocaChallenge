package com.example.enoca.service;

import com.example.enoca.controller.dto.AddCustomer;
import com.example.enoca.data.entity.Customer;

public interface CustomerService {
    Customer addCustomer(AddCustomer customer);
    Customer getCustomerById(Long customerId);
}