package com.example.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    public Customer findByNumberId(String numberId);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegionId(Long regionId);
}
