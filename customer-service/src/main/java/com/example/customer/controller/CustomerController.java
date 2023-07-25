package com.example.customer.controller;

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

import com.example.customer.entity.Customer;
import com.example.customer.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> listAll(){
        List<Customer> customers = customerService.findCustomerAll();
        if(customers.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        if(customer == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(customer);
    }

    @GetMapping("/region/{regionId}")
    public ResponseEntity<List<Customer>> listAllByRegion(@PathVariable Long regionId){
        List<Customer> customers = customerService.findCustomersByRegion(regionId);
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody Customer customer, BindingResult result, UriComponentsBuilder ucb){
        if(result.hasErrors())
            return ResponseEntity.badRequest().build();
        
        Customer createdCustomer = customerService.createCustomer(customer);
        URI locationOfNewCustomer = ucb
            .path("customers/{id}")
            .buildAndExpand(createdCustomer.getId())
            .toUri();
        return ResponseEntity.created(locationOfNewCustomer).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customer, BindingResult result){
        if(result.hasErrors())
            return ResponseEntity.badRequest().build();

        customer.setId(id);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        if(updatedCustomer == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id){
        Customer deletedCustomer = customerService.deleteCustomer(id);
        if(deletedCustomer == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(deletedCustomer);
    }
}
