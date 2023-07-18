package com.example.swalayan.repository;

import com.example.swalayan.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByIdAndVersion(Long id, Long version);
}

