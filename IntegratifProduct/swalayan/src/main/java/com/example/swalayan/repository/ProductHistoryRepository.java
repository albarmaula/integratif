package com.example.swalayan.repository;

import com.example.swalayan.model.Product;
import com.example.swalayan.model.ProductHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductHistoryRepository extends JpaRepository<ProductHistory, Long> {
    ProductHistory findByProductIdAndVersion(Long productId, Long version);
}