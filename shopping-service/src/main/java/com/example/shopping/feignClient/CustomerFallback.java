package com.example.shopping.feignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.shopping.dto.Customer;


@Component
public class CustomerFallback implements CustomerClient{

    @Override
    public ResponseEntity<Customer> getCustomer(Long id) {
        Customer customer = Customer.builder()
            .firstName("none")
            .lastName("none")
            .email("none")
            .photoUrl("none")
            .build();
        
        return ResponseEntity.ok(customer);
    }
    
}
