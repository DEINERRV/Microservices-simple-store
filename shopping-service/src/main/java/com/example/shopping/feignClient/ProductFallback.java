package com.example.shopping.feignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.shopping.dto.Product;

@Component
public class ProductFallback implements ProductClient{

    @Override
    public ResponseEntity<Product> getProduct(Long id) {
        Product product = Product.builder()
            .name("none")
            .description("none")
            .price(0.0)
            .stock(0.0)
            .build();
        
        return ResponseEntity.ok(product);
    }

    @Override
    public ResponseEntity<Product> updateStockProduct(Long id, Double stock) {
        Product product = Product.builder()
            .name("none")
            .description("none")
            .price(0.0)
            .stock(0.0)
            .build();
        
        return ResponseEntity.ok(product);
    }
    
}
