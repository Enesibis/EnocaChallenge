package com.example.enoca.data.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "price_history")
public class ProductHistory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "change_date", nullable = false)
    private LocalDateTime changeData;


    public ProductHistory() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Product getProduct() {
        return product;
    }


    public void setProduct(Product product) {
        this.product = product;
    }




    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public LocalDateTime getChangeData() {
        return changeData;
    }


    public void setChangeData(LocalDateTime changeData) {
        this.changeData = changeData;
    }
}
