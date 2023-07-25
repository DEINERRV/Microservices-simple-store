package com.example.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.entity.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Long>{
    
}
