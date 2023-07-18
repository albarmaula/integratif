package com.example.swalayan.model;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
public class Product implements Serializable {

        private static final long serialVersionUID = 1L;
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        private String product_name;
        private double price;
        private int stock;
        private String product_desc;
        @Version
        private Long version;

}

