package com.example.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.entity.Invoice;


public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
    Boolean existsByNumberInvoice(String numberInvoice);
    Invoice findByNumberInvoice(String numberInvoice);
}
