package com.soaint.exam.repository;

import com.soaint.exam.entities.Client;
import com.soaint.exam.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
