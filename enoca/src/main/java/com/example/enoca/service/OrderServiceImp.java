package com.example.enoca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.enoca.data.entity.*;
import com.example.enoca.data.repository.CartRep;
import com.example.enoca.data.repository.CustomerRep;
import com.example.enoca.data.repository.OrderRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderRep orderRepository;


    @Autowired
    private CartRep cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerRep customerRepository;


    @Override
    public Order placeOrder(Long cartId) {
        Cart cart = cartRepository.getById(cartId);

        if (cart != null) {
            Order order = new Order();
            order.setCustomer(cart.getCustomer());

            List<CartItem> cartItems = cart.getCartItems();
            List<OrderItem> orderItems = new ArrayList<>();
            double totalOrderPrice = 0.0;

            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());

                double itemTotalPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                orderItem.setPriceAtOrder(itemTotalPrice);

                totalOrderPrice += itemTotalPrice;
                orderItems.add(orderItem);
            }

            order.setItems(orderItems);
            order.setTotalPrice(totalOrderPrice);

            Order savedOrder = orderRepository.save(order);

            cartService.emptyCart(cartId);
            cart.setStatus(CartStatus.COMPLETED);
            cartRepository.save(cart);


            return savedOrder;
        }
        return null;
    }

    @Override
    public Order placeOrderCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null && customer.getCart() != null) {
            Cart cart = customer.getCart();

            Order order = new Order();
            order.setCustomer(customer);

            List<CartItem> cartItems = cart.getCartItems();
            List<OrderItem> orderItems = new ArrayList<>();
            double totalOrderPrice = 0.0;

            for (CartItem cartItem : cartItems) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());

                double itemTotalPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                orderItem.setPriceAtOrder(itemTotalPrice);

                totalOrderPrice += itemTotalPrice;
                orderItems.add(orderItem);
            }

            order.setItems(orderItems);
            order.setTotalPrice(totalOrderPrice);

            Order savedOrder = orderRepository.save(order);

            cartService.emptyCart(cart.getId());
            cart.setStatus(CartStatus.COMPLETED);
            cartRepository.save(cart);

            return savedOrder;
        }
        return null;
    }


    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        return optionalOrder.orElse(null);
    }

    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }





}