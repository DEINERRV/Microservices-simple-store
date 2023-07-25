package com.example.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.entity.Invoice;
import com.example.shopping.repository.InvoiceRepository;

@Service
public class InvoiceServiceImp implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> findInvoiceAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
        if(invoiceDB != null)
            return invoiceDB;

        invoice.setState("CREATED");
        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findById(invoice.getId()).orElse(null);
        if(invoiceDB == null)
            return null;

        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setItems(invoice.getItems());
        
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice deleteInvoice(Long invoiceId) {
        Invoice invoiceDB = invoiceRepository.findById(invoiceId).orElse(null);
        if(invoiceDB == null)
            return null;

        invoiceDB.setState("DELETED");
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
    
}
