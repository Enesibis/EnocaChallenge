package com.example.enoca.data.repository;

import com.example.enoca.data.entity.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductHistoryRep extends JpaRepository<ProductHistory, Long> {
}
