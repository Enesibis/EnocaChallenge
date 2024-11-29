package com.example.enoca.service;

import java.util.Optional;

import com.example.enoca.controller.dto.AddProduct;
import com.example.enoca.controller.dto.RemoveProduct;
import com.example.enoca.data.entity.*;
import com.example.enoca.data.repository.CartRep;
import com.example.enoca.data.repository.CustomerRep;
import com.example.enoca.data.repository.ProductRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRep cartRepository;

    @Autowired
    private ProductRep productRepository;

    @Autowired
    private CustomerRep customerRepository;


    @Override
    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElse(null);
    }


    @Override
    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }



    @Override
    public void emptyCart(Long id) {
        Cart cart = cartRepository.findById(id).orElse(null);
        if (cart != null) {
            cart.getCartItems().clear();
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
        }
    }




    @Override
    public void updateCartTotalPrice(Cart cart) {
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);
    }



    @Override
    public Cart addProductToCart(Long customerId, AddProduct addProductRequest) {

        Cart cart = cartRepository.findByCustomerId(customerId);

        if (cart == null) {

            cart = new Cart();


            Customer customer = customerRepository.findById(customerId).orElse(null);

            if (customer != null) {

                cart.setCustomer(customer);
                customer.setCart(cart);

            } else {

                return null;
            }
        }


        cart.setStatus(CartStatus.ACTIVE);


        Product product = productRepository.findById(addProductRequest.getProductId()).orElse(null);


        if (product != null && product.getStock() >= addProductRequest.getQuantity()) {

            CartItem existingCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getProduct().getId().equals(addProductRequest.getProductId()))
                    .findFirst()
                    .orElse(null);

            if (existingCartItem != null) {

                int newQuantity = existingCartItem.getQuantity() + addProductRequest.getQuantity();


                if (newQuantity <= product.getStock()) {
                    existingCartItem.setQuantity(newQuantity);
                    updateCartTotalPrice(cart);


                    product.setStock(product.getStock() - addProductRequest.getQuantity());
                    productRepository.save(product);

                    return cartRepository.save(cart);
                } else {

                    return null;
                }
            } else {

                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(addProductRequest.getQuantity());
                cartItem.setCart(cart);
                cart.getCartItems().add(cartItem);

                updateCartTotalPrice(cart);


                product.setStock(product.getStock() - addProductRequest.getQuantity());
                productRepository.save(product);

                return cartRepository.save(cart);
            }
        }


        return null;
    }


    @Override
    public Cart removeProductFromCart(Long customerId, RemoveProduct removeProductRequest) {
        Cart cart = cartRepository.findByCustomerId(customerId);
        Product product = productRepository.findById(removeProductRequest.getProductId()).orElse(null);

        if (cart != null && product != null) {

            Optional<CartItem> cartItemOptional = Optional.empty();
            for (CartItem item : cart.getCartItems()) {
                if (item.getProduct().getId().equals(product.getId())) {
                    cartItemOptional = Optional.of(item);
                    break;
                }
            }

            if (cartItemOptional.isPresent()) {
                CartItem cartItem = cartItemOptional.get();
                int requestedQuantity = removeProductRequest.getQuantity();
                int availableQuantity = cartItem.getQuantity();

                if (requestedQuantity <= availableQuantity) {
                    int newQuantity = availableQuantity - requestedQuantity;

                    if (newQuantity > 0) {
                        cartItem.setQuantity(newQuantity);
                    } else {

                        newQuantity = 0;
                        cart.getCartItems().remove(cartItem);
                    }

                    updateCartTotalPrice(cart);


                    product.setStock(product.getStock() + requestedQuantity);



                    return cartRepository.save(cart);
                } else {

                }
            }
        }

        return null;
    }








}