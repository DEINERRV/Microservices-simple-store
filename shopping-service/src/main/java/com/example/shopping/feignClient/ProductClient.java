package com.example.shopping.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.shopping.dto.Product;

@FeignClient(name = "microservice-product", path = "/products")
public interface ProductClient {
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id);

    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(value = "quantity", required = true) Double stock);

}
