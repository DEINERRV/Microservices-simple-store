package com.example.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.product.entity.Category;
import com.example.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
    public List<Product> findByCategory(Category category);
}
