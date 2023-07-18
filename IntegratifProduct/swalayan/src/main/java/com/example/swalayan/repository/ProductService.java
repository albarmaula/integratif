package com.example.swalayan.repository;

import com.example.swalayan.model.Product;
import com.example.swalayan.model.ProductHistory;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductHistoryRepository productHistoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductHistoryRepository productHistoryRepository) {
        this.productRepository = productRepository;
        this.productHistoryRepository = productHistoryRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Transactional
    public Product findProductByIdAndVersion(Long productId, Long version) {
        return productRepository.findByIdAndVersion(productId, version);
    }

    @Transactional
    public ProductHistory findProductHistoryByIdAndVersion(Long productId, Long version) {
        return productHistoryRepository.findByProductIdAndVersion(productId, version);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct == null) {
            // Product not found, handle the case accordingly
            return null;
        }

        // Simpan versi sebelumnya pada entitas ProductHistory
        ProductHistory productHistory = new ProductHistory();
        productHistory.setProductId(existingProduct.getId());
        productHistory.setProduct_name(existingProduct.getProduct_name());
        productHistory.setPrice(existingProduct.getPrice());
        productHistory.setStock(existingProduct.getStock());
        productHistory.setProduct_desc(existingProduct.getProduct_desc());
        productHistory.setVersion(existingProduct.getVersion());
        productHistory.setChangeDate(new Date());
        productHistoryRepository.save(productHistory);

        // Perform the updates
        existingProduct.setProduct_name(updatedProduct.getProduct_name());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setProduct_desc(updatedProduct.getProduct_desc());

        // Save the updated product back to the database
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
