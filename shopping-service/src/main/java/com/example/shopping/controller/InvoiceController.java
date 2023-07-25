package com.example.shopping.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.shopping.entity.Invoice;
import com.example.shopping.service.InvoiceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<List<Invoice>> listAll(){
        List<Invoice> list = invoiceService.findInvoiceAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long id){
        Invoice invoice = invoiceService.getInvoice(id);
        if(invoice == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(invoice);
    }

    @PostMapping
    public ResponseEntity<Void> createInvoice(@Valid @RequestBody Invoice invoice, BindingResult result, UriComponentsBuilder ucb){
        if(result.hasErrors())
            return ResponseEntity.badRequest().build();

        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        URI locationOfNewInvoice = ucb
            .path("invoices/{id}")
            .buildAndExpand(createdInvoice.getId())
            .toUri();
        return ResponseEntity.created(locationOfNewInvoice).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @Valid @RequestBody Invoice invoice, BindingResult result){
        if(result.hasErrors())
            return ResponseEntity.badRequest().build();

        invoice.setId(id);
        Invoice updatedInvoice = invoiceService.updateInvoice(invoice);
        if(updatedInvoice == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedInvoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable Long id){
        Invoice deletedInvoice = invoiceService.deleteInvoice(id);
        if(deletedInvoice == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(deletedInvoice);
    }
}
