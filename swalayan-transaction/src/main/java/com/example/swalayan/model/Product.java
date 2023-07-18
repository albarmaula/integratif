package com.example.swalayan.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class Product {
    private Long id;
    private String product_name;
    private double price;
    private int stock;
    private String product_desc;
    private Long version;
}
