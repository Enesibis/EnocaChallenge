package com.example.enoca.service;

import com.example.enoca.controller.dto.AddCustomer;
import com.example.enoca.data.entity.Customer;
import com.example.enoca.data.repository.CustomerRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImp implements CustomerService{

    @Autowired
    private final CustomerRep customerRepository;


    public CustomerServiceImp(CustomerRep customerRepository) {
        super();
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(AddCustomer customer) {
        Customer newCustomer = new Customer();
        newCustomer.setName(customer.getName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setPhoneNumber(customer.getPhoneNumber());
        return customerRepository.save(newCustomer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.getCustomerById(customerId);
    }

}