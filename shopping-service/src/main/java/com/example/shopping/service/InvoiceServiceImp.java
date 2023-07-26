package com.example.shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.dto.Customer;
import com.example.shopping.dto.Product;
import com.example.shopping.entity.Invoice;
import com.example.shopping.entity.InvoiceItem;
import com.example.shopping.feignClient.CustomerClient;
import com.example.shopping.feignClient.ProductClient;
import com.example.shopping.repository.InvoiceRepository;

@Service
public class InvoiceServiceImp implements InvoiceService{

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    CustomerClient customerClient;

    @Autowired
    ProductClient productClient;

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
        Invoice invoiceSaved = invoiceRepository.save(invoice);

        invoiceSaved.getItems().forEach( item -> {
            Product product = productClient.getProduct(item.getProductId()).getBody();
            productClient.updateStockProduct(item.getProductId(), product.getStock()-item.getQuantity());
        });

        return invoiceSaved;
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
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if(invoice == null)
            return null;

        Customer customer = customerClient.getCustomer(invoice.getId()).getBody();
        invoice.setCustomer(customer);

        List<InvoiceItem> items = invoice.getItems().stream().map(item->{
            Product product = productClient.getProduct(item.getProductId()).getBody();
            item.setProduct(product);
            return item;
        }).collect(Collectors.toList());
        invoice.setItems(items);

        return invoice;
    }
    
}
