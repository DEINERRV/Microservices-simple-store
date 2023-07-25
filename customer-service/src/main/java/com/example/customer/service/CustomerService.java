package com.example.customer.service;

import java.util.List;

import com.example.customer.entity.Customer;

public interface CustomerService {
    public List<Customer> findCustomerAll();
    public List<Customer> findCustomersByRegion(Long regionId);

    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Customer deleteCustomer(Long id);
    public  Customer getCustomer(Long id);
}
