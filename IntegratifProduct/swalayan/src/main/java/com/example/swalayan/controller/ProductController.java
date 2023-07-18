package com.example.swalayan.controller;


import com.example.swalayan.model.Product;
import com.example.swalayan.model.ProductHistory;
import com.example.swalayan.repository.ProductRepository;
import com.example.swalayan.repository.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    public ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}/{version}")
    public ResponseEntity<Product> findProductByIdAndVersion(@PathVariable Long id, @PathVariable Long version) {
        Product product = productService.findProductByIdAndVersion(id, version);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/history/{id}/{version}")
    public ResponseEntity<ProductHistory> findProductHistoryByIdAndVersion(@PathVariable Long id, @PathVariable Long version) {
        ProductHistory productHistory = productService.findProductHistoryByIdAndVersion(id, version);
        if (productHistory != null) {
            return ResponseEntity.ok(productHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
