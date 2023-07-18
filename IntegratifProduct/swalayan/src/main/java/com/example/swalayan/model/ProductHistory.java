package com.example.swalayan.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "history_product")
public class ProductHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String product_name;
    private double price;
    private int stock;
    private String product_desc;
    private Long version;
    private Date changeDate;
}
