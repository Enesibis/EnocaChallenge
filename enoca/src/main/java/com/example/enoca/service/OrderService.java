package com.example.enoca.service;

import com.example.enoca.data.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByCustomerId(Long customerId);
    Order getOrder(Long orderId);
    Order placeOrder(Long cartId);
    Order placeOrderCustomer(Long customerId);
}
