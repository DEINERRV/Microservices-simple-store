package com.example.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customer.entity.Customer;
import com.example.customer.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findCustomerAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findCustomersByRegion(Long regionId) {
        return customerRepository.findByRegionId(regionId);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerDB = customerRepository.findByNumberId(customer.getNumberId());
        if(customerDB != null)
            return customerDB;
        
        customer.setState("CREATED");
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if(!customerRepository.existsById(customer.getId()))
            return null;

        return customerRepository.save(customer);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        Customer customerDB = getCustomer(id);
        if(customerDB == null)
            return null;

        customerDB.setState("DELETED");
        return customerRepository.save(customerDB);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    
}
