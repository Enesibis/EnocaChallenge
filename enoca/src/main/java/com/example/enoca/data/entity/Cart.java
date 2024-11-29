package com.example.enoca.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart extends BaseEntity{
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItemList = new ArrayList<>();

    @Setter
    @Getter
    private double totalPrice;


    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    private CartStatus status;


    public List<CartItem> getCartItems() {
        return cartItemList;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItemList = cartItems;
    }

}
