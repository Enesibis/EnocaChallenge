package com.example.enoca.controller;

import com.example.enoca.controller.dto.AddCustomer;
import com.example.enoca.data.entity.Customer;
import com.example.enoca.service.CustomerServiceImp;
import com.example.enoca.service.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private final CustomerServiceImp customerService;

    public CustomerController(CustomerServiceImp customerService, OrderServiceImp orderService) {
        super();
        this.customerService = customerService;

    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody AddCustomer customer) {

        Customer addedCustomer = customerService.addCustomer(customer);
        return ResponseEntity.ok(addedCustomer);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}